package com.example.bookflix;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class LivrosDao {

    private final AppDbHelper helper;

    public LivrosDao(Context ctx) {
        this.helper = new AppDbHelper(ctx);
    }

    /** Lista todos os títulos (ordem alfabética, ignore case). */
    public List<String> listarTitulos() {
        List<String> lista = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = null;
        try {
            c = db.query(AppDbHelper.T_LIVROS,
                    new String[]{AppDbHelper.L_TITULO},
                    null, null, null, null,
                    AppDbHelper.L_TITULO + " COLLATE NOCASE ASC");
            while (c.moveToNext()) {
                lista.add(c.getString(0));
            }
        } finally {
            if (c != null) c.close();
            db.close();
        }
        return lista;
    }

    /** Busca ID do livro pelo título. Retorna -1 se não achar. */
    public long getIdByTitulo(String titulo) {
        long id = -1;
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = null;
        try {
            c = db.query(AppDbHelper.T_LIVROS,
                    new String[]{AppDbHelper.L_ID},
                    AppDbHelper.L_TITULO + "=?",
                    new String[]{titulo},
                    null, null, null);
            if (c.moveToFirst()) id = c.getLong(0);
        } finally {
            if (c != null) c.close();
            db.close();
        }
        return id;
    }

    /** (Opcional) Busca dados completos do livro. */
    public Livro buscarPorTitulo(String titulo) {
        Livro l = null;
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = null;
        try {
            c = db.query(AppDbHelper.T_LIVROS,
                    new String[]{AppDbHelper.L_ID, AppDbHelper.L_TITULO, AppDbHelper.L_AUTOR, AppDbHelper.L_GENERO, AppDbHelper.L_ANO},
                    AppDbHelper.L_TITULO + "=?",
                    new String[]{titulo}, null, null, null);
            if (c.moveToFirst()) {
                l = new Livro();
                l.id = c.getLong(0);
                l.titulo = c.getString(1);
                l.autor = c.getString(2);
                l.genero = c.getString(3);
                l.ano = c.getInt(4);
            }
        } finally {
            if (c != null) c.close();
            db.close();
        }
        return l;
    }
}
