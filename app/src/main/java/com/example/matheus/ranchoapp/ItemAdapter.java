package com.example.matheus.ranchoapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item> {
    public ItemAdapter (@NonNull Context context, @NonNull List<Item> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.item_layout, parent, false);
        }

        Item current = getItem(position);

        TextView txtNomeItem = listItemView.findViewById(R.id.txtName);
        TextView txtQuantidade = listItemView.findViewById(R.id.txtQuantidade);
        TextView txtPreco = listItemView.findViewById(R.id.txtValor);

        txtNomeItem.setText(current.getmNome());
        txtQuantidade.setText((String.valueOf(current.getmQuantidade())));
        txtPreco.setText((String.valueOf(current.getmPreco())));

        return  listItemView;

    }
}
