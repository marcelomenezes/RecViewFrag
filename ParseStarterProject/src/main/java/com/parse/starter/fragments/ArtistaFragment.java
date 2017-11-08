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

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArtistaFragment extends Fragment {


    private ListView listView;
    private ArrayList<ParseUser> artistas;
    private ArrayAdapter<ParseUser> adapter;
    private ParseQuery<ParseUser> query;



    public ArtistaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_artista, container, false);


        //Montar ListView e adapter
        artistas = new ArrayList<>();
        listView = (ListView) view.findViewById(R.id.lista_artistas);
        adapter = new ArtistaAdapter(getActivity(), artistas);
        listView.setAdapter(adapter);

        getArtistas();

        return view;
    }

    private void getArtistas(){

        //Query para selecionar todos artistas cadastrados, com exceção do usuário logado
        query = ParseUser.getQuery();
        query.whereNotEqualTo("username", ParseUser.getCurrentUser().getUsername());
        query.orderByAscending("username");

        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {

                if(e == null){//sucesso

                    //verifica se existem artistas listados
                    if(objects.size() > 0) {

                        artistas.clear();
                        for (ParseUser parseUser : objects){
                            artistas.add(parseUser);
                        }
                        adapter.notifyDataSetChanged();
                    }
                }else{//erro

                    e.printStackTrace();
                }
            }
        });

    }
}
