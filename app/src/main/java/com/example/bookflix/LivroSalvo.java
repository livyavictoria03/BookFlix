package com.example.bookflix;

public class LivroSalvo {
    private long id;       // id da tabela SALVOS (_id)
    private String titulo; // título do livro (da tabela LIVROS)

    public LivroSalvo(long id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }
}
