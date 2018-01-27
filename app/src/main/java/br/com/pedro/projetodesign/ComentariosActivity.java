package br.com.pedro.projetodesign;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

/**
 * Created by lucaspasquier on 27/01/18.
 */

public class ComentariosActivity extends AppCompatActivity {

    String comentariosTudo;
    TextView textView_titulo_comentar;
    TextView textView_corpo_comentar;
    TextView textView_Comentarios_tudo;
    ImageView imageView_comentar;
    String nomeUsuario;

    Toolbar toolbar;

    String nomeArquivo;
    RatingBar ratingBar_comentar_total;

    private int posicao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentario);

        posicao = getIntent().getIntExtra("posicao", 0);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        try {
            SharedPreferences settings = getSharedPreferences("Nome Login", 0);
            nomeUsuario = (settings.getString("PrefUsuario", ""));

        } catch (Exception e) {
            e.printStackTrace();
//                            edtUsuario.setText("");
        }

        textView_titulo_comentar = (TextView) findViewById(R.id.textView_titulo_comentar);
        textView_corpo_comentar = (TextView) findViewById(R.id.textView_corpo_comentar);
        imageView_comentar = (ImageView) findViewById(R.id.imageView_comentar);

        textView_titulo_comentar.setText(MyData.nameArray[posicao]);
        textView_corpo_comentar.setText(MyData.versionArray[posicao]);
        imageView_comentar.setImageResource(MyData.drawableArray[posicao]);

        ratingBar_comentar_total = (RatingBar) findViewById(R.id.ratingBar_comentar_total);

        String valor_nota = "" + posicao;
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        ratingBar_comentar_total.setRating(sharedPref.getInt(valor_nota, 0));


        textView_Comentarios_tudo = (TextView) findViewById(R.id.textView_Comentarios_tudo);

        //-----------------------------------------
        //Ler texto do .TXT
        FileInputStream fis = null;

        nomeArquivo = posicao + ".txt";
        try {

            fis = getApplicationContext().openFileInput(nomeArquivo);
            InputStreamReader isr = new InputStreamReader(fis);
            // READ STRING OF UNKNOWN LENGTH
            StringBuilder sb = new StringBuilder();
            char[] inputBuffer = new char[2048];
            int l;
            // FILL BUFFER WITH DATA
            while ((l = isr.read(inputBuffer)) != -1) {
                sb.append(inputBuffer, 0, l);
            }
            // CONVERT BYTES TO STRING
            String readString = sb.toString();

//            comentariosTudo=readString;

            textView_Comentarios_tudo.setText(readString);



            fis.close();

        } catch (Exception e) {

        } finally {
            if (fis != null) {
                fis = null;
            }
        }




        Button butt_comentar = (Button) findViewById(R.id.butt_comentar);
        butt_comentar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                AlertDialog.Builder mensagem = new AlertDialog.Builder(ComentariosActivity.this);
                mensagem.setTitle("Comentário");
                mensagem.setMessage("Digite o seu comentário");
                // DECLARACAO DO EDITTEXT
                final EditText input = new EditText(ComentariosActivity.this);
                mensagem.setView(input);
                mensagem.setNeutralButton("OK", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

//                Toast.makeText(getApplicationContext(), input.getText().toString().trim(),Toast.LENGTH_SHORT).show();




                        FileOutputStream fos = null;
                        try {
                            comentariosTudo=textView_Comentarios_tudo.getText().toString();
                            String texto=comentariosTudo+"\n\n"+nomeUsuario+"\n"+input.getText().toString()+"\n_____________________________________\n";
                            textView_Comentarios_tudo.setText(comentariosTudo+"\n"+nomeUsuario+"\n"+input.getText().toString()+"\n_____________________________________\n");

                            fos = ComentariosActivity.this.openFileOutput(nomeArquivo, Context.MODE_PRIVATE);
                            fos.write(texto.getBytes());
                            fos.flush();
                            fos.close();

                        } catch (Exception e) {

                        } finally {
                            if (fos != null) {
                                fos = null;
                            }
                        }

                    }

                });

                mensagem.show();

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
