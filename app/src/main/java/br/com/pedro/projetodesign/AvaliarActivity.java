package br.com.pedro.projetodesign;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by lucaspasquier on 27/01/18.
 */

public class AvaliarActivity extends AppCompatActivity {

    TextView textView_titulo_avaliar;
    TextView textView_corpo_avaliar;
    TextView txtView_estacionamento_sinopse;
    TextView txtView_banheiros_sinopse;
    TextView txtView_rampa_sinopse;
    TextView txtView_mobiliario_sinopse;
    TextView txtView_circulacao_sinopse;

    Button butt_avaliar_comentario;
    Button button_avaliar_nota;
    RatingBar ratingBar_avaliar_total;

    ImageView imageView_avaliar;

    RatingBar ratingBar_estacionamento;
    RatingBar ratingBar_banheiros;
    RatingBar ratingBar_rampa;
    RatingBar ratingBar_mobiliario;
    RatingBar ratingBar_circulacao;

    Toolbar toolbar;


    private int posicao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliar);

        posicao = getIntent().getIntExtra("posicao", 0);

        initView();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textView_titulo_avaliar.setText(MyData.nameArray[posicao]);
        textView_corpo_avaliar.setText(MyData.versionArray[posicao]);
        imageView_avaliar.setImageResource(MyData.drawableArray[posicao]);

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        ratingBar_avaliar_total.setRating(sharedPref.getInt(String.valueOf(posicao), 0));

        button_avaliar_nota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nota_estacionamento = (int) Math.round(ratingBar_estacionamento.getRating());
                int nota_banheiro = (int) Math.round(ratingBar_banheiros.getRating());
                int nota_rampa = (int) Math.round(ratingBar_rampa.getRating());
                int nota_mobiliario = (int) Math.round(ratingBar_mobiliario.getRating());
                int nota_circulação = (int) Math.round(ratingBar_circulacao.getRating());

                int nota_final = (nota_estacionamento + nota_banheiro + nota_rampa + nota_mobiliario + nota_circulação) / 5;
                Log.d("nota_final", "" + nota_final);


                SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                String valor_nota = "" + posicao;


                int valNotaTotal = (sharedPref.getInt(valor_nota, 0));
                Log.d("nota_TOTAS", "" + sharedPref.getInt(valor_nota, 0));

                if (valNotaTotal != 0) {
                    nota_final = (nota_final + valNotaTotal) / 2;
                    Log.d("NOVA_nota_final", "" + sharedPref.getInt(valor_nota, 0));
                }

                SharedPreferences.Editor editor = sharedPref.edit();
                String valor = "" + posicao;
                editor.putInt(valor, nota_final);
                editor.commit();

                finish();
            }
        });

        butt_avaliar_comentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rating();
    }

    private void rating() {
        ratingBar_estacionamento.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                int nota = (int) Math.round(ratingBar_estacionamento.getRating());

                if (nota == 0) {
                    txtView_estacionamento_sinopse.setText("");
                } else if (nota == 1) {
                    txtView_estacionamento_sinopse.setText("NÃO TEM");
                } else if (nota == 2) {
                    txtView_estacionamento_sinopse.setText("RUIM");
                } else if (nota == 3) {
                    txtView_estacionamento_sinopse.setText("REGULAR");
                } else if (nota == 4) {
                    txtView_estacionamento_sinopse.setText("BOM");
                } else if (nota == 5) {
                    txtView_estacionamento_sinopse.setText("MUITO BOM");
                }
            }
        });

        ratingBar_banheiros.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                int nota = (int) Math.round(ratingBar_banheiros.getRating());

                if (nota == 0) {
                    txtView_banheiros_sinopse.setText("");
                } else if (nota == 1) {
                    txtView_banheiros_sinopse.setText("NÃO TEM");
                } else if (nota == 2) {
                    txtView_banheiros_sinopse.setText("RUIM");
                } else if (nota == 3) {
                    txtView_banheiros_sinopse.setText("REGULAR");
                } else if (nota == 4) {
                    txtView_banheiros_sinopse.setText("BOM");
                } else if (nota == 5) {
                    txtView_banheiros_sinopse.setText("MUITO BOM");
                }
            }
        });

        ratingBar_rampa.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                int nota = (int) Math.round(ratingBar_rampa.getRating());

                if (nota == 0) {
                    txtView_rampa_sinopse.setText("");
                } else if (nota == 1) {
                    txtView_rampa_sinopse.setText("NÃO TEM");
                } else if (nota == 2) {
                    txtView_rampa_sinopse.setText("RUIM");
                } else if (nota == 3) {
                    txtView_rampa_sinopse.setText("REGULAR");
                } else if (nota == 4) {
                    txtView_rampa_sinopse.setText("BOM");
                } else if (nota == 5) {
                    txtView_rampa_sinopse.setText("MUITO BOM");
                }
            }
        });

        ratingBar_mobiliario.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                int nota = (int) Math.round(ratingBar_mobiliario.getRating());

                if (nota == 0) {
                    txtView_mobiliario_sinopse.setText("");
                } else if (nota == 1) {
                    txtView_mobiliario_sinopse.setText("NÃO TEM");
                } else if (nota == 2) {
                    txtView_mobiliario_sinopse.setText("RUIM");
                } else if (nota == 3) {
                    txtView_mobiliario_sinopse.setText("REGULAR");
                } else if (nota == 4) {
                    txtView_mobiliario_sinopse.setText("BOM");
                } else if (nota == 5) {
                    txtView_mobiliario_sinopse.setText("MUITO BOM");
                }
            }
        });

        ratingBar_circulacao.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                int nota = (int) Math.round(ratingBar_circulacao.getRating());

                if (nota == 0) {
                    txtView_circulacao_sinopse.setText("");
                } else if (nota == 1) {
                    txtView_circulacao_sinopse.setText("NÃO TEM");
                } else if (nota == 2) {
                    txtView_circulacao_sinopse.setText("RUIM");
                } else if (nota == 3) {
                    txtView_circulacao_sinopse.setText("REGULAR");
                } else if (nota == 4) {
                    txtView_circulacao_sinopse.setText("BOM");
                } else if (nota == 5) {
                    txtView_circulacao_sinopse.setText("MUITO BOM");
                }
            }
        });

    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        button_avaliar_nota = (Button) findViewById(R.id.button_avaliar_nota);

        textView_titulo_avaliar = (TextView) findViewById(R.id.textView_titulo_avaliar);
        textView_corpo_avaliar = (TextView) findViewById(R.id.textView_corpo_avaliar);
        imageView_avaliar = (ImageView) findViewById(R.id.imageView_avaliar);

        butt_avaliar_comentario = (Button) findViewById(R.id.butt_avaliar_comentario);

        ratingBar_estacionamento = (RatingBar) findViewById(R.id.ratingBar_estacionamento);
        ratingBar_banheiros = (RatingBar) findViewById(R.id.ratingBar_banheiros);
        ratingBar_rampa = (RatingBar) findViewById(R.id.ratingBar_rampa);
        ratingBar_mobiliario = (RatingBar) findViewById(R.id.ratingBar_mobiliario);
        ratingBar_circulacao = (RatingBar) findViewById(R.id.ratingBar_circulacao);


        txtView_estacionamento_sinopse = (TextView) findViewById(R.id.txtView_estacionamento_sinopse);
        txtView_banheiros_sinopse = (TextView) findViewById(R.id.txtView_banheiros_sinopse);
        txtView_rampa_sinopse = (TextView) findViewById(R.id.txtView_rampa_sinopse);
        txtView_mobiliario_sinopse = (TextView) findViewById(R.id.txtView_mobiliario_sinopse);
        txtView_circulacao_sinopse = (TextView) findViewById(R.id.txtView_circulacao_sinopse);

        ratingBar_avaliar_total = (RatingBar) findViewById(R.id.ratingBar_avaliar_total);
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
