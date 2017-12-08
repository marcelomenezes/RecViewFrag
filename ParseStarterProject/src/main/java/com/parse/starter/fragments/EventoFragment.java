package com.parse.starter.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.starter.R;
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
