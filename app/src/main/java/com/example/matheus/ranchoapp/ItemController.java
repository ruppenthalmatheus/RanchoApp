package com.example.matheus.ranchoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ItemController {

    private SQLiteDatabase db;
    private CriaBanco banco;

    public ItemController(Context context) {
        banco = new CriaBanco(context);
    }

    public String add(String pNome, String pQuantidade, String pPreco) {
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriaBanco.NOME, pNome);
        valores.put(CriaBanco.QUANTIDADE, pQuantidade);
        valores.put(CriaBanco.PRECO, pPreco);

        resultado = db.insert(CriaBanco.TABELA, null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir o item";
        else
            return "Item adicionado ao carrinho!";

    }

    public ArrayList<Item> load() {

        ArrayList<Item> lista = new ArrayList<>();
        Cursor cursor;
        String[] campos = {CriaBanco.ID, CriaBanco.NOME, CriaBanco.QUANTIDADE, CriaBanco.PRECO};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA, campos, null, null, null, null, null, null);

        while(cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String nome = cursor.getString(1);
            int quantidade = cursor.getInt(2);
            double preco = cursor.getDouble(3);

            Item item = new Item(id, nome, quantidade, preco);
            lista.add(item);
        }

        db.close();
        return lista;
    }

    public void update(int id, String pNome, String pQuantidade, String pPreco) {
        ContentValues valores;
        String where;

        db = banco.getReadableDatabase();

        where = CriaBanco.ID + "=" + id;

        valores = new ContentValues();
        valores.put(CriaBanco.NOME, pNome);
        valores.put(CriaBanco.QUANTIDADE, pQuantidade);
        valores.put(CriaBanco.PRECO, pPreco);

        db.update(CriaBanco.TABELA, valores, where, null);
        db.close();
    }

    public double getTotal() {

        double total = 0;
        db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(" + CriaBanco.PRECO + ") as Total FROM " + CriaBanco.TABELA, null);

        if (cursor.moveToFirst()) {
            total = cursor.getDouble(cursor.getColumnIndex("Total"));
        }
        return total;
    }

    public String remove(Item item){
        String where = CriaBanco.ID + "=" + item.getmId();
        db = banco.getWritableDatabase();
        db.delete(CriaBanco.TABELA,where,null);
        db.close();
        return item.getmNome()+ " REMOVIDO";
    }
}
