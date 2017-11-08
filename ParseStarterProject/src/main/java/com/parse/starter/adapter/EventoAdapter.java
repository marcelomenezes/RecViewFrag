package com.parse.starter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.parse.ParseObject;
import com.parse.starter.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by marcelomenezes on 04/11/17.
 */

public class EventoAdapter extends ArrayAdapter<ParseObject> {

    private Context context;
    private ArrayList<ParseObject> eventos_listados;

    public EventoAdapter(Context c, ArrayList<ParseObject> objects) {
        super(c, 0, objects);
        this.context = c;
        this.eventos_listados = objects;
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
            view = inflater.inflate(R.layout.lista_postagem, parent, false);
        }

        //verifica se existe artistas listados
        if(eventos_listados.size() > 0){


            //recuperar componentes de tela
            ImageView artistaPostagem = (ImageView) view.findViewById(R.id.image_lista_postagem);

            ParseObject parseObject = eventos_listados.get(position);

            Picasso.with(context)
                    .load( parseObject.getParseFile("imagem").getUrl())
                    .fit()
                    .into(artistaPostagem);
        }



        return view;
    }
}
