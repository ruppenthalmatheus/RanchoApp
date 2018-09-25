package com.example.matheus.ranchoapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;

public class CriaBanco extends SQLiteOpenHelper {

    public static final String NOME_BANCO = "banco.db";
    public static final String TABELA = "itens";
    public static final String ID = "_id";
    public static final String NOME = "nome";
    public static final String QUANTIDADE = "quantidade";
    public static final String PRECO = "preco";
    public static final int VERSAO = 1;

    public CriaBanco(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE "+ TABELA + "("
                + ID + " integer primary key autoincrement,"
                + NOME + " text,"
                + QUANTIDADE + " integer,"
                + PRECO + " real"
                +")";

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);

    }

    public void clearDB(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + this.TABELA);
        db.close();
    }

}


