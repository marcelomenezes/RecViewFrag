package com.parse.starter.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.starter.R;
import com.parse.starter.activity.PerfilEventoActivity;
import com.parse.starter.adapter.ArtistaAdapter;
import com.parse.starter.adapter.EventoAdapter;

import java.util.ArrayList;
import java.util.List;



/**
 * A simple {@link Fragment} subclass.
 */
public class EventoFragment extends Fragment {


    private ListView listView;
    private ArrayList<ParseObject> eventos_listados;
    private ArrayAdapter<ParseObject> adapter;
    private ParseQuery<ParseObject> query;

    public EventoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_evento, container, false);


        //Montar Listview e adapter

        eventos_listados = new ArrayList<>();
        listView = (ListView) view.findViewById(R.id.lista_eventos);
        adapter = new EventoAdapter(getActivity(), eventos_listados);
        listView.setAdapter(adapter);

        getEventosListados();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //Recupera os dados da listview
                ParseObject parseObject = eventos_listados.get(position);

                //Enviar os daos para a PerfilEventoActivity
                Intent intent = new Intent(getActivity(), PerfilEventoActivity.class);
                intent.putExtra("imagem", parseObject.getParseFile("imagem").getUrl());
                intent.putExtra("nomeEvento", parseObject.getString("nomeEvento"));
                intent.putExtra("detalhesEvento", parseObject.getString("detalhesEvento"));
                intent.putExtra("enderecoEvento", parseObject.getString("enderecoEvento"));

                startActivity(intent);

            }
        });

        return view;
    }

    private void getEventosListados(){

        //Recupera imagens das postagens
        query = ParseQuery.getQuery("Evento");
        //query.whereEqualTo("username", ParseUser.getCurrentUser().getUsername());
        query.orderByDescending("createdAt");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if(e==null){//sucesso

                    if(objects.size()>0){
                        eventos_listados.clear();
                        for (ParseObject parseObject : objects){
                            eventos_listados.add(parseObject);
                        }
                        adapter.notifyDataSetChanged();
                    }

                }else {//erro

                    e.printStackTrace();
                }

            }
        });


    }

    public void atualizaEventos(){
        getEventosListados();
    }



}
