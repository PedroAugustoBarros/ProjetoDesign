package br.com.pedro.projetodesign;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;


public class ComentarioFragment extends Fragment {
    String valorSelecionado;
    String comentariosTudo;
    TextView textView_titulo_comentar;
    TextView textView_corpo_comentar;
    TextView textView_Comentarios_tudo;
    ImageView imageView_comentar;
    String nomeUsuario;

    String nomeArquivo;
    int posicao;

    RatingBar ratingBar_comentar_total;

    public ComentarioFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        valorSelecionado = ((MainActivity) getActivity()).valorSelecionado();
        posicao = Integer.parseInt(valorSelecionado);


        try {
            SharedPreferences settings = getActivity().getSharedPreferences("Nome Login", 0);
            nomeUsuario = (settings.getString("PrefUsuario", ""));

        } catch (Exception e) {
            e.printStackTrace();
//                            edtUsuario.setText("");
        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_comentario, container, false);

        textView_titulo_comentar = (TextView) view.findViewById(R.id.textView_titulo_comentar);
        textView_corpo_comentar = (TextView) view.findViewById(R.id.textView_corpo_comentar);
        imageView_comentar = (ImageView) view.findViewById(R.id.imageView_comentar);

        textView_titulo_comentar.setText(MyData.nameArray[posicao]);
        textView_corpo_comentar.setText(MyData.versionArray[posicao]);
        imageView_comentar.setImageResource(MyData.drawableArray[posicao]);

        ratingBar_comentar_total = (RatingBar) view.findViewById(R.id.ratingBar_comentar_total);

        String valor_nota = "" + posicao;
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        ratingBar_comentar_total.setRating(sharedPref.getInt(valor_nota, 0));


        textView_Comentarios_tudo = (TextView) view.findViewById(R.id.textView_Comentarios_tudo);

        //-----------------------------------------
        //Ler texto do .TXT
        FileInputStream fis = null;

        nomeArquivo = valorSelecionado + ".txt";
        try {

            fis = getActivity().getApplicationContext().openFileInput(nomeArquivo);
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




        Button butt_comentar = (Button) view.findViewById(R.id.butt_comentar);
        butt_comentar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                AlertDialog.Builder mensagem = new AlertDialog.Builder(getActivity());
                mensagem.setTitle("Comentário");
                mensagem.setMessage("Digite o seu comentário");
                // DECLARACAO DO EDITTEXT
                final EditText input = new EditText(getActivity());
                mensagem.setView(input);
                mensagem.setNeutralButton("OK", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

//                Toast.makeText(getApplicationContext(), input.getText().toString().trim(),Toast.LENGTH_SHORT).show();




                        FileOutputStream fos = null;
                        try {
                            comentariosTudo=textView_Comentarios_tudo.getText().toString();
                            String texto=comentariosTudo+"\n\n"+nomeUsuario+"\n"+input.getText().toString()+"\n_____________________________________\n";
                            textView_Comentarios_tudo.setText(comentariosTudo+"\n"+nomeUsuario+"\n"+input.getText().toString()+"\n_____________________________________\n");

                            fos = getActivity().openFileOutput(nomeArquivo, Context.MODE_PRIVATE);
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




        return view;
    }

}
