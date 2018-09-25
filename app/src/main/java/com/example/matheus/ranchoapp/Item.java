package com.example.matheus.ranchoapp;

import java.io.Serializable;

public class Item implements Serializable {

    private int mId;
    private String mNome;
    private int mQuantidade;
    private double mPreco;

    public int getmId() {
        return mId;
    }

    public String getmNome() {
        return mNome;
    }

    public int getmQuantidade() {
        return mQuantidade;
    }

    public double getmPreco() {
        return mPreco;
    }

    public Item(String mNome, int mQuantidade, double mPreco) {
        this.mNome = mNome;
        this.mQuantidade = mQuantidade;
        this.mPreco = mPreco;
    }

    public Item(int mId, String mNome, int mQuantidade, double mPreco) {
        this.mId = mId;
        this.mNome = mNome;
        this.mQuantidade = mQuantidade;
        this.mPreco = mPreco;
    }

    @Override
    public String toString() {
        return mNome;
    }
}
