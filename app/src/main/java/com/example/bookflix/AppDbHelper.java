package com.example.bookflix;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AppDbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "bookflix.db";
    public static final int DB_VERSION = 3;


    // Tabela LIVROS
    public static final String T_LIVROS = "livros";
    public static final String L_ID = "_id";
    public static final String L_TITULO = "titulo";
    public static final String L_AUTOR = "autor";
    public static final String L_GENERO = "genero";
    public static final String L_ANO = "ano";

    // Tabela SALVOS (referencia LIVROS)
    public static final String T_SALVOS = "salvos";
    public static final String S_ID = "_id";
    public static final String S_LIVRO_ID = "livro_id";

    public AppDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // LIVROS
        db.execSQL("CREATE TABLE " + T_LIVROS + " (" +
                L_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                L_TITULO + " TEXT NOT NULL UNIQUE, " +
                L_AUTOR + " TEXT NOT NULL, " +
                L_GENERO + " TEXT NOT NULL, " +
                L_ANO + " INTEGER NOT NULL" +
                ");");

        // SALVOS (UNIQUE por livro_id para evitar duplicatas)
        db.execSQL("CREATE TABLE " + T_SALVOS + " (" +
                S_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                S_LIVRO_ID + " INTEGER NOT NULL UNIQUE, " +
                "FOREIGN KEY(" + S_LIVRO_ID + ") REFERENCES " + T_LIVROS + "(" + L_ID + ") ON DELETE CASCADE" +
                ");");

        // PRÉ-POPULA os 6 livros
        db.execSQL("INSERT INTO " + T_LIVROS + " (" + L_TITULO + "," + L_AUTOR + "," + L_GENERO + "," + L_ANO + ") VALUES " +
                "('Verity','Colleen Hoover','Suspense psicológico',2018)," +
                "('Os Sete Maridos de Evelyn Hugo','Taylor Jenkins Reid','Drama/Romance',2017)," +
                "('É Assim que Acaba','Colleen Hoover','Romance/Drama',2016)," +
                "('É Assim que Começa','Colleen Hoover','Romance/Drama',2022)," +
                "('O Verão Que Mudou a Minha Vida','Jenny Han','Romance/Juvenil',2009)," +
                "('Minha Vida Fora de Série','Paula Pimenta','Romance Juvenil',2011)" +
                ";");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS " + T_SALVOS);
        db.execSQL("DROP TABLE IF EXISTS " + T_LIVROS);
        onCreate(db);
    }
}
