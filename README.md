# BookFlix1

BookFlix – Aplicativo Android
Integrantes

Lívya Victória Pereira Ferreira e Daniely de Sá Cadette

1. Objetivo do Aplicativo

O BookFlix é um aplicativo Android desenvolvido como projeto prático da disciplina de Computação Móvel.
O objetivo é permitir que o usuário visualize livros, acesse suas sinopses, salve seus favoritos e gerencie esses itens em uma interface simples e intuitiva.

2. Tecnologias Utilizadas

Android Studio

Linguagem Java

SQLite para persistência de dados

RecyclerView para exibição das listas

ConstraintLayout para construção das telas

3. Estrutura do Banco de Dados
Tabela: livros

Campos:

id – inteiro, chave primária

titulo – texto

autor – texto

ano – inteiro

genero – texto

salvo – inteiro (0 ou 1), indicando se o usuário salvou o livro

Tabela: salvos

Campos:

id – inteiro, chave primária

titulo – texto

autor – texto

ano – inteiro

genero – texto

4. Funcionalidades Implementadas (CRUD Completo)
Create (Inserção de Dados)

O usuário pode salvar um livro ao clicar no botão disponível na página de detalhes do livro.

As informações são inseridas no banco de dados SQLite.

Read (Leitura de Dados)

A tela de livros salvos exibe todos os itens cadastrados utilizando RecyclerView.

A tela inicial exibe os títulos disponíveis e permite pesquisa dinâmica.

Delete (Exclusão de Dados)

O usuário pode remover um livro salvo através de um alerta de confirmação.

Após a exclusão, a lista é atualizada automaticamente.

Update

O update não foi implementado porque não há necessidade de editar livros neste contexto.

5. Funcionalidades Gerais do App

Navegação entre telas

Pesquisa de livros com sugestões automáticas

Exibição da sinopse de cada livro

Salvamento e remoção de livros

Perfil do usuário com armazenamento local via SharedPreferences

Ícone de Home nas telas internas para retornar à página principal

6. Diário de Bordo 

Durante o desenvolvimento, foram aplicados conceitos aprendidos em aula, incluindo a criação do banco de dados, implementação de CRUD e interface gráfica.
Ao longo do processo, ajustes foram feitos para corrigir erros de navegação, aprimorar o visual e garantir que todos os requisitos fossem atendidos.
