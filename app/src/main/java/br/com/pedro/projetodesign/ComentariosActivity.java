package br.com.pedro.projetodesign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by lucaspasquier on 27/01/18.
 */

public class ComentariosActivity extends AppCompatActivity {

    private int posicao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_comentario);

        posicao = getIntent().getIntExtra("posicao", 0);
    }
}
