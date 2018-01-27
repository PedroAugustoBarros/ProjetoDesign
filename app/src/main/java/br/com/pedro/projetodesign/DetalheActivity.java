package br.com.pedro.projetodesign;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by lucaspasquier on 27/01/18.
 */

public class DetalheActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView textView_det_titulo;
    private TextView textView_det_corpo;
    private TextView textView_det_endereco;
    private ImageView imageView_det;
    private RatingBar ratingBar_detalhes_total;
    private Button butt_avaliar;
    private ImageButton imageButton_mapa;

    private int posicao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        this.textView_det_titulo = (TextView) findViewById(R.id.textView_det_titulo);
        this.textView_det_corpo = (TextView) findViewById(R.id.textView_det_corpo);
        this.textView_det_endereco = (TextView) findViewById(R.id.textView_det_endereco);
        this.imageView_det = (ImageView) findViewById(R.id.imageView_det);
        this.ratingBar_detalhes_total = (RatingBar) findViewById(R.id.ratingBar_detalhes_total);
        this.butt_avaliar = (Button) findViewById(R.id.butt_avaliar);
        this.imageButton_mapa = (ImageButton) findViewById(R.id.imageButton_mapa);
        imageButton_mapa = (ImageButton) findViewById(R.id.imageButton_mapa);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        posicao = getIntent().getIntExtra("message", 0);


        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        ratingBar_detalhes_total.setRating(sharedPref.getInt(String.valueOf(posicao), 0));

        textView_det_titulo.setText(MyData.nameArray[posicao]);
        textView_det_corpo.setText(MyData.versionArray[posicao]);
        imageView_det.setImageResource(MyData.drawableArray[posicao]);
        textView_det_endereco.setText(MyData.enderecoArray[posicao]);


        butt_avaliar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(DetalheActivity.this, AvaliarActivity.class);
                intent.putExtra("posicao", posicao);

                startActivity(intent);
            }
        });


        imageButton_mapa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(DetalheActivity.this, MapsActivity.class);
                myIntent.putExtra("key", posicao);
                startActivity(myIntent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        supportFinishAfterTransition();
    }
}
