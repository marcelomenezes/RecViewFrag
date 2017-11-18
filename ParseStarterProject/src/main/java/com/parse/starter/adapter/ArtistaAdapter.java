package com.parse.starter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.starter.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by marcelomenezes on 19/09/17.
 */

public class ArtistaAdapter extends ArrayAdapter<ParseObject> {

    private Context context;
    private ArrayList<ParseObject> artistas;

    public ArtistaAdapter(Context c, ArrayList<ParseObject> objects) {
        super(c, 0, objects);
        this.context = c;
        this.artistas = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View view = convertView;


        /*
        Verifica se não tem view criada
        */
        if( view == null){

            //Inicializa o objeto para montagem do layout
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            //Monta a partir do xml
            view = inflater.inflate(R.layout.lista_artistas, parent, false);
        }

        //Recuperar elementos para exibição
        TextView textArtistas = (TextView) view.findViewById(R.id.text_artistas);

        //Configurar textview para exibir artistas
        ParseObject parseObject = artistas.get(position);
        textArtistas.setText(parseObject.getString("nomeArtista"));

        return view;
    }
}
