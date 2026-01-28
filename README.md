# ☎️ Agenda de Contatos — Java

## Visão Geral

Este projeto consiste em uma **agenda de contatos em Java**, executada via terminal, que permite **cadastrar, editar, excluir e listar contatos** utilizando **persistência em arquivo texto**. O sistema foi desenvolvido com foco em lógica de programação, manipulação de arquivos e interação com o usuário.

Os dados são armazenados no arquivo `agenda.txt`, garantindo que as informações persistam mesmo após o encerramento do programa.

---

## 🎯 Funcionalidades

O sistema oferece um menu interativo com as seguintes opções:

1. **Adicionar contato** — cadastra nome e telefone
2. **Editar contato** — altera o telefone de um contato existente
3. **Excluir contato** — remove um contato da agenda
4. **Listar contatos** — exibe todos os contatos ordenados alfabeticamente
5. **Sair** — encerra a aplicação

---

## 🛠️ Tecnologias Utilizadas

* **Java (JDK 8+)**
* **java.nio.file** — leitura e escrita de arquivos
* **Scanner** — entrada de dados via terminal
* **Collections** — ordenação de dados

---

## 📂 Estrutura de Arquivos

```text
AgendaContatos.java
agenda.txt
```

* `AgendaContatos.java`: classe principal contendo toda a lógica da aplicação
* `agenda.txt`: arquivo gerado automaticamente para armazenar os contatos

Cada linha do arquivo segue o formato:

```text
nome;telefone
```

---

## ⚙️ Funcionamento do Sistema

### 📌 Persistência de Dados

* O arquivo `agenda.txt` é criado automaticamente caso não exista
* Cada contato é salvo em uma nova linha
* As operações de edição e exclusão sobrescrevem o arquivo para manter a consistência

### 📌 Organização dos Contatos

* Os contatos são **ordenados alfabeticamente** pelo nome ao serem listados
* A numeração exibida é dinâmica, baseada na posição do contato na lista

### 📌 Tratamento de Erros

* Verificação da existência do arquivo antes de leitura ou escrita
* Tratamento de exceções `IOException`
* Validação básica das opções do menu

---

## 👩‍💻 Autora

**Gabriela Pedroso dos Santos Pontes**
Projeto acadêmico — Java | Manipulação de Arquivos e Lógica de Programação

---

> Projeto ideal para estudos de Java básico, arquivos, estruturas de controle e aplicações em modo console.
