package com.example.bookflix;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class SalvosDao {

    private final AppDbHelper helper;
    private final LivrosDao livrosDao;

    public SalvosDao(Context context) {
        helper = new AppDbHelper(context);
        livrosDao = new LivrosDao(context);
    }

    // Usado nas telas dos livros:
    // salvosDao.inserirPorTitulo("Verity");
    public boolean inserirPorTitulo(String titulo) {
        // 1) Descobre o ID do livro pela tabela LIVROS
        long livroId = livrosDao.getIdByTitulo(titulo);
        if (livroId == -1) {
            // não achou o livro
            return false;
        }

        SQLiteDatabase db = helper.getWritableDatabase();
        long idInserido;
        try {
            ContentValues cv = new ContentValues();
            cv.put(AppDbHelper.S_LIVRO_ID, livroId);
            idInserido = db.insert(AppDbHelper.T_SALVOS, null, cv);
        } catch (SQLiteConstraintException e) {
            // caiu aqui porque já existe um registro com esse livro_id (UNIQUE)
            idInserido = -1;
        } finally {
            db.close();
        }

        // true = inseriu; false = já existia ou deu erro
        return idInserido != -1;
    }

    // Lista todos os salvos (join SALVOS + LIVROS)
    public List<LivroSalvo> listar() {
        List<LivroSalvo> lista = new ArrayList<>();

        SQLiteDatabase db = helper.getReadableDatabase();

        String sql =
                "SELECT s." + AppDbHelper.S_ID + ", l." + AppDbHelper.L_TITULO + " " +
                        "FROM " + AppDbHelper.T_SALVOS + " s " +
                        "JOIN " + AppDbHelper.T_LIVROS + " l " +
                        "ON l." + AppDbHelper.L_ID + " = s." + AppDbHelper.S_LIVRO_ID + " " +
                        "ORDER BY s." + AppDbHelper.S_ID + " DESC";

        Cursor c = db.rawQuery(sql, null);

        while (c.moveToNext()) {
            long idSalvo = c.getLong(0);
            String titulo = c.getString(1);
            lista.add(new LivroSalvo(idSalvo, titulo));
        }

        c.close();
        db.close();
        return lista;
    }

    // Excluir um registro dos salvos
    public void excluir(long idSalvo) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete(
                AppDbHelper.T_SALVOS,
                AppDbHelper.S_ID + " = ?",
                new String[]{ String.valueOf(idSalvo) }
        );
        db.close();
    }
}
