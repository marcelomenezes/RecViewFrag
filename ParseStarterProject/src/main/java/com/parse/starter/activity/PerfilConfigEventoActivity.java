package com.parse.starter.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.starter.R;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PerfilConfigEventoActivity extends AppCompatActivity {

    private Button botaoEscolherImagem;
    private Button botaoSalvarEvento;

    private EditText cidadeNomeEditText;
    private EditText descricaoEditText;
    private EditText enderecoEditText;
    private EditText dataEventoSalva;

    private ImageView imagemEventoConfig;

    private Toolbar toolbar;

    private String cidadeNome;
    private String descricao;

    private ParseObject imagemPassada;

    private Date dataEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_config_evento);

        botaoEscolherImagem = (Button) findViewById(R.id.botao_escolher_imagem_evento);
        botaoSalvarEvento = (Button) findViewById(R.id.botao_salvar_evento);

        cidadeNomeEditText = (EditText) findViewById(R.id.text_config_cidade_evento);
        descricaoEditText = (EditText) findViewById(R.id.text_config_introducao_evento);
        enderecoEditText = (EditText) findViewById(R.id.endereco_evento);

        imagemEventoConfig = (ImageView) findViewById(R.id.imagem_evento_config);


        botaoEscolherImagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        });

        //configurar toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar_perfil_config_evento);
        toolbar.setTitle("Adicionar Evento");
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left);
        setSupportActionBar(toolbar);



        //associando o textview aos valores passados pelo intent
        cidadeNomeEditText.setText(cidadeNome);
        descricaoEditText.setText(descricao);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Log.i("onActivityResult", "onActivityResult");

        //Testar processor de retorno dos dados
        if(requestCode == 1 && resultCode == RESULT_OK && data != null){


            //recuperar local do recurso
            Uri localImagemSelecionada = data.getData();
            Toast.makeText(getApplicationContext(), "Imagem do evento foi selecionada.", Toast.LENGTH_LONG).show();
            //recupera imagem do local selecionado
            try {
                Bitmap imagem = MediaStore.Images.Media.getBitmap(getContentResolver(), localImagemSelecionada);

                //comprimir no formato PNG
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                imagem.compress(Bitmap.CompressFormat.PNG, 75, stream);

                //Cria um array de bytes da imagem
                byte[] byteArray = stream.toByteArray();

                //Criar um arquivo com formato próprio do parse
                SimpleDateFormat dateFormat = new SimpleDateFormat("ddmmaaaahhmmss");
                String nomeImagem = dateFormat.format( new Date());
                final ParseFile arquivoParse = new ParseFile( nomeImagem + "imagem.png", byteArray);


                //Monta o objeto para salvar no parse


                /*
                ParseObject parseObject = new ParseObject("Imagem");
                parseObject.put("username", ParseUser.getCurrentUser().getUsername());
                parseObject.put("imagem", arquivoParse);
                */

                botaoSalvarEvento.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Monta o objeto para salvar no parse
                        ParseObject parseEvento = new ParseObject("Evento");
                        parseEvento.put("imagem", arquivoParse);

                        cidadeNome = cidadeNomeEditText.getText().toString();
                        descricao = descricaoEditText.getText().toString();
                        //dataEvento = dataEventoSalva.getText().

                        parseEvento.put("username", ParseUser.getCurrentUser());
                        parseEvento.put("cidade", cidadeNome);
                        parseEvento.put("introducao", descricao);

                        //salvar os dados
                        parseEvento.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {

                                if (e == null) { //sucesso
                                    Toast.makeText(getApplicationContext(), "Seu evento foi postado.", Toast.LENGTH_LONG).show();
                                    finish();
                            /*
                            //atualizar a lista de novos eventos adicionados
                            TabsAdapter adapterNovo = (TabsAdapter) viewPager.getAdapter();
                            EventoFragment eventoFragmentoNovo = (EventoFragment) adapterNovo.getFragment(1);
                            eventoFragmentoNovo.atualizaEventos();
                               */
                                } else {//erro
                                    Toast.makeText(getApplicationContext(), "Erro ao postar evento - Tente Novamente!",
                                            Toast.LENGTH_LONG).show();
                                }

                            }
                        });
                    }
                });

            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}
