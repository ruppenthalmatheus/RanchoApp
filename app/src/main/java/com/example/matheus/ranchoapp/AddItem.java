package com.example.matheus.ranchoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddItem extends AppCompatActivity {

    EditText mNome;
    EditText mQuantidade;
    EditText mPreco;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            ItemController crud = new ItemController(getBaseContext());
            mNome = findViewById(R.id.txtItemName);
            mQuantidade = findViewById(R.id.txtItemQuantity);
            mPreco = findViewById(R.id.txtItemValue);
            String nome = mNome.getText().toString();
            String qtdeString = mQuantidade.getText().toString();
            int quantidade = Integer.parseInt(mQuantidade.getText().toString());
            float valor = (Float.parseFloat(mPreco.getText().toString()))*quantidade;
            String preco = Float.toString(valor);
            String resultado;

            resultado = crud.add(nome, qtdeString, preco);

            Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_SHORT).show();
            onBackPressed();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
