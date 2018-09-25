package com.example.matheus.ranchoapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AlteraItem extends AppCompatActivity {

    EditText mNome;
    EditText mQuantidade;
    EditText mPreco;
    String codigo;
    double total;
    String preco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Intent intent = getIntent();
        Item item = (Item) intent.getSerializableExtra("item");

        mNome = (EditText) findViewById(R.id.txtItemName);
        mQuantidade = (EditText) findViewById(R.id.txtItemQuantity);
        mPreco = (EditText) findViewById(R.id.txtItemValue);

        mNome.setText(item.getmNome());
        mQuantidade.setText(String.valueOf(item.getmQuantidade()));
        mPreco.setText(String.valueOf(item.getmPreco()));
        codigo = String.valueOf(item.getmId());
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
            total = Integer.parseInt(mQuantidade.getText().toString())*Double.parseDouble(mPreco.getText().toString());
            preco = String.valueOf(total);
            crud.update(Integer.parseInt(codigo), mNome.getText().toString(),mQuantidade.getText().toString(), preco);
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
            onBackPressed();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
