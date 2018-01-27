package br.com.pedro.projetodesign;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.appcompat.BuildConfig;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class LoginActivity extends ActionBarActivity {

    ProgressDialog progressDialog;


    private EditText edtUsuario;
    private EditText edtSenha, edtCodAcesso;
    private Button btnLogin, but_chave_entrada;

    private TextView txtVersao;

    private ImageView imgLogo;

    public static final String PREFS_NAME = "Nome Login";
    public static final String PREFS_NAME_COD = "Senha";

    String login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        assignViews();
        initListeners();

        txtVersao.setText("Versão " + BuildConfig.VERSION_CODE);

        //Recuperar login e colocar no Edittext Login
        try {
            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            edtUsuario.setText(settings.getString("PrefUsuario", ""));

        } catch (Exception e) {
            e.printStackTrace();
            edtUsuario.setText("");
        }

        try {
            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            edtSenha.setText(settings.getString("Senha", ""));

        } catch (Exception e) {
            e.printStackTrace();
            edtSenha.setText("");
        }


    }

    private void assignViews() {
        edtUsuario = (EditText) findViewById(R.id.edtUsuario);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        imgLogo = (ImageView) findViewById(R.id.imgLogo);

        txtVersao = (TextView) findViewById(R.id.tvVersao);
    }

    private void initListeners() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtUsuario.getEditableText().toString().isEmpty() || !(android.util.Patterns.EMAIL_ADDRESS.matcher(edtUsuario.getEditableText().toString()).matches())) {
                    edtUsuario.setError("Digite o endereço de email");
                } else if (edtSenha.getEditableText().toString().isEmpty()) {
                    edtSenha.setError("Digite a senha");
                } else {
                    String valor_versao = BuildConfig.VERSION_NAME;
                    valor_versao = valor_versao.replace(".", "");
                    Log.d("valor versão con", "" + valor_versao);


                    SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("PrefUsuario", edtUsuario.getText().toString());

                    editor.putString("Senha", edtSenha.getText().toString());

                    editor.commit();


                    Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
                    LoginActivity.this.startActivity(myIntent);

                    finish();

                }
            }
        });


    }


    /* Função para verificar existência de conexão com a internet
 */
    public boolean verificaConexao() {
        boolean conectado;
        ConnectivityManager conectivtyManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        if (conectivtyManager.getActiveNetworkInfo() != null
                && conectivtyManager.getActiveNetworkInfo().isAvailable()
                && conectivtyManager.getActiveNetworkInfo().isConnected()) {
            conectado = true;
        } else {
            conectado = false;
        }
        return conectado;
    }


}

