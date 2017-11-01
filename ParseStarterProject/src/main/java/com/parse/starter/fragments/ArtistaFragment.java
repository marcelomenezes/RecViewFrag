package com.parse.starter.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.parse.starter.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArtistaFragment extends Fragment {


    private ListView listView;

    public ArtistaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_artista, container, false);


        //Montar Listview e adapter

        listView = (ListView) view.findViewById(R.id.list_item);

        return view;
    }

}
