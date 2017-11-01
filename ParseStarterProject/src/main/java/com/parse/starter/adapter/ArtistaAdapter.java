package com.parse.starter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.parse.ParseObject;
import com.parse.starter.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcelomenezes on 19/09/17.
 */

public class ArtistaAdapter extends ArrayAdapter<ParseObject> {

    private Context context;
    private ArrayList<ParseObject> postagens;

    public ArtistaAdapter(Context c, ArrayList<ParseObject> objects) {
        super(c, 0, objects);
        this.context = c;
        this.postagens = objects;
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


        return view;
    }
}
