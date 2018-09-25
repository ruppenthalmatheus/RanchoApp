package com.example.matheus.ranchoapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ActionMode;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ItemAdapter adapter;
    double totalCarrinho;
    TextView total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(),AddItem.class);
                    startActivity(intent);
            }
        });

        refresh();
        ListView list = findViewById(R.id.listaItens);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item item = (Item) parent.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(),AlteraItem.class);
                intent.putExtra("item", item);
                startActivity(intent);
            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Item item = (Item) parent.getItemAtPosition(position);
                ItemController itemRemovido = new ItemController(getApplicationContext());
                String msg = itemRemovido.remove(item);
                refresh();
                Toast.makeText(getApplicationContext(), msg , Toast.LENGTH_LONG).show();
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_clear_all, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_clear) {
            CriaBanco obj = new CriaBanco(getApplicationContext());
            obj.clearDB();
            refresh();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestart() {
        final ItemController pegaItem = new ItemController(getApplicationContext());
        refresh();
        super.onRestart();
    }

    private void refresh() {

        ItemController pegaItem = new ItemController(getApplicationContext());
        total = (TextView) findViewById(R.id.total);
        totalCarrinho = pegaItem.getTotal();
        total.setText(String.valueOf(totalCarrinho));
        ArrayList<Item> lista = pegaItem.load();
        adapter = new ItemAdapter(getApplicationContext(), lista);
        ListView list = findViewById(R.id.listaItens);
        list.setAdapter(adapter);
    }

}
