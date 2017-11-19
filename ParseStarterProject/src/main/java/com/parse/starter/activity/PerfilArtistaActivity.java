package com.parse.starter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.parse.starter.R;

public class PerfilArtistaActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private String artistaNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_artista);


        //Recupera dados da intent ArtistaFragment
        Intent intent = getIntent();
        artistaNome = intent.getStringExtra("nomeArtista");

        //configurar toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar_perfil_artista);
        toolbar.setTitle(artistaNome);
        toolbar.setTitleTextColor(R.color.preta);

    }
}
