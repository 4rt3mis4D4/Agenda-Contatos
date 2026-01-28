import java.io.IOException;

import java.nio.file.*;

import java.util.Collections;

import java.util.List;

import java.util.Scanner;



public class AgendaContatos {



    private static final Path CAMINHO_ARQUIVO = Paths.get("agenda.txt"); /* Caminho constante para o arquivo */

    private static final Scanner scanner = new Scanner(System.in); /* Scanner para ler a entrada do usuário */



    public static void main(String[] args) {

        int opcao;



        do {

            /* Exibe o menu e lê a opção do usuário */

            System.out.println("\n-- Agenda de contatos --\n");

            System.out.println("[1] Adicionar contato");

            System.out.println("[2] Editar contato");

            System.out.println("[3] Excluir contato");

            System.out.println("[4] Listar contatos");

            System.out.println("[5] Sair");

            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();

            scanner.nextLine(); /* Consumir a nova linha após a leitura do número */



            switch (opcao) {

                case 1:

                    /* Adicionando um novo contato - nome e telefone entrada do usuário */

                    System.out.println("Adicionando Contato:");

                    System.out.println("Digite o nome do contato: ");

                    String nome = scanner.nextLine();

                    System.out.println("Digite o telefone do contato: ");

                    String telefone = scanner.nextLine();



                    adicionarContato(nome, telefone);



                    break;

                case 2:

                    /* Editando um contato - baseado no número atribuído na listagem */


                    System.out.println("Edicao de contato:");

                    System.out.println("Digite o numero do contato a ser editado: ");

                    int num = Integer.parseInt(scanner.nextLine());



                    editarContato(num);



                    break;

                case 3:

                    /* Excluindo um contato - baseado no número atribuído na listagem */


                    System.out.println("Excluir Contato: ");

                    System.out.println("Digite o numero do contato a ser excluido: ");

                    int contato = Integer.parseInt(scanner.nextLine());



                    excluirContato(contato);

                    break;

                case 4:

                    /* Listando todos os contatos */

                    listarContatos();

                    break;

                case 5:

                    scanner.close();

                    return;

                default:

                    System.out.println("Opção inválida!");

            }

        } while (opcao != 5);

    }



    private static void adicionarContato(String nome, String telefone) {

        String linha = nome + ";" + telefone; /* Formata os dados */



        try {

      /* Escreve a linha no arquivo:

        CREATE: cria o arquivo se não existir

        APPEND: adiciona ao final do arquivo

        System.lineSeparator(): adiciona quebra de linha correta para o sistema operacional */

            Files.write(CAMINHO_ARQUIVO,

                    (linha + System.lineSeparator()).getBytes(),

                    StandardOpenOption.CREATE,

                    StandardOpenOption.APPEND);

            System.out.println("Contato adicionado: " + nome);

        } catch (IOException e) {

            System.out.println("Erro ao adicionar contato: " + e.getMessage());

        }

    }



    private static void editarContato(int numeroContato) {

        try {

            if (!Files.exists(CAMINHO_ARQUIVO)) {

                System.out.println("Arquivo ainda nao existe.");

                return;

            }



            List<String> linhas = Files.readAllLines(CAMINHO_ARQUIVO);

            boolean encontrado = false;

            String novoTelefone = "";

            String nomeParaEditar = "";



            for (int i = 0; i < linhas.size(); i++) {

                String[] partes = linhas.get(i).split(";");

                if (numeroContato == i + 1) {

                    System.out.printf("\nDigite o novo telefone para %s: ", partes[0]);

                    novoTelefone = scanner.nextLine();

                    nomeParaEditar = partes[0];

                    linhas.set(i, partes[0] + ";" + novoTelefone); /* Substitui o telefone */

                    encontrado = true;

                    break; /* Interrompe quando o contato é encontrado */

                }

            }



            if (encontrado) {

                /* Sobrescreve o arquivo com os dados atualizados */

                Files.write(CAMINHO_ARQUIVO, linhas, StandardOpenOption.TRUNCATE_EXISTING);

                System.out.println("Telefone de " + nomeParaEditar + " atualizado para " + novoTelefone);

            } else {

                System.out.println("Contato " + nomeParaEditar + " nao encontrado.");

            }



        } catch (IOException e) {

            System.out.println("Erro ao editar contato: " + e.getMessage());

        }

    }



    private static void excluirContato(int numeroExcluir) {



        try {

            if (!Files.exists(CAMINHO_ARQUIVO)) {

                System.out.println("Arquivo ainda nao existe.");

                return;

            }



            List<String> linhas = Files.readAllLines(CAMINHO_ARQUIVO);

            boolean encontrado = false;

            String nomeParaExcluir = "";



            /* Itera sobre a lista e remove o contato com o nome correspondente */

            for (int i = 0; i < linhas.size(); i++) {

                String[] partes = linhas.get(i).split(";");

                if (numeroExcluir == i + 1) {

                    nomeParaExcluir = partes[0];

                    linhas.remove(i); /* Remove o contato */

                    encontrado = true;

                    break; /* Interrompe quando o contato é encontrado */

                }

            }



            if (encontrado) {

                /* Sobrescreve o arquivo com os dados atualizados */

                Files.write(CAMINHO_ARQUIVO, linhas, StandardOpenOption.TRUNCATE_EXISTING);

                System.out.println("Contato removido: " + nomeParaExcluir);

            } else {

                System.out.println("Numero de contato " + numeroExcluir + " nao encontrado.");

            }



        } catch (IOException e) {

            System.out.println("Erro ao excluir contato: " + e.getMessage());

        }

    }



    private static void listarContatos() {

        if (!Files.exists(CAMINHO_ARQUIVO)) {

            System.out.println("Arquivo ainda nao existe.");

            return;

        }



        try {

            List<String> linhas = Files.readAllLines(CAMINHO_ARQUIVO);



            /* Verifica se há contatos */

            if (linhas.isEmpty()) {

                System.out.println("Nenhum contato salvo.");

            } else {

                /* Ordena os contatos alfabeticamente */

                Collections.sort(linhas);



                /* Exibe os títulos e os contatos formatados */

                System.out.println("\n-- Listagem de Contatos --\n");



                //1 - Na listagem de contatos exibe dinamicamente o número do contato.

                System.out.printf("%-3s %-20s %-15s\n", "N.", "Nome", "Telefone");



                for (int i = 0; i < linhas.size(); i++) {

                    String[] partes = linhas.get(i).split(";");

                    System.out.printf("%-3d %-20s %-15s\n", i+1, partes[0], partes[1]);

                }



                /* Sobrescreve o arquivo com os dados ordenados */

                Files.write(CAMINHO_ARQUIVO, linhas, StandardOpenOption.TRUNCATE_EXISTING);

            }

        } catch (IOException e) {

            System.out.println("Erro ao ler contatos: " + e.getMessage());

        }

    }

}
