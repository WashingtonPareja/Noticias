package com.example.noticias;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdaptadorPais extends ArrayAdapter<Banderitas> {
    public AdaptadorPais(Context context, ArrayList<Banderitas> datos) {
        super(context, R.layout.ly_pais, datos);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.ly_pais, null);

        TextView lblTitulo = (TextView)item.findViewById(R.id.LblTitulo);
        lblTitulo.setText(getItem(position).getTitulo());

        TextView lblSubtitulo = (TextView)item.findViewById(R.id.LblSubTitulo);
        lblSubtitulo.setText(getItem(position).getSubtitulo());

        ImageView imageView = (ImageView)item.findViewById(R.id.imgbandera);
        Glide.with(this.getContext())
                .load(getItem(position).getUrl())
                //.error(R.drawable.imgnotfound)
                .into(imageView);
        return(item);
    }
}
