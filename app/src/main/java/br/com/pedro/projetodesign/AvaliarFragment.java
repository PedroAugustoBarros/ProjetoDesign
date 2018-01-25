package br.com.pedro.projetodesign;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


public class AvaliarFragment extends Fragment {

   String valorSelecionado;

    TextView textView_titulo_avaliar;
    TextView textView_corpo_avaliar;
    ImageView imageView_avaliar;

    RatingBar ratingBar_avaliar_total;

    int posiçãoID;

    public AvaliarFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       valorSelecionado= ((MainActivity) getActivity()).valorSelecionado();
        posiçãoID=Integer.parseInt(valorSelecionado);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_avaliar, container, false);



        textView_titulo_avaliar = (TextView) view.findViewById(R.id.textView_titulo_avaliar);
        textView_corpo_avaliar = (TextView) view.findViewById(R.id.textView_corpo_avaliar);
        imageView_avaliar = (ImageView) view.findViewById(R.id.imageView_avaliar);

        final RatingBar ratingBar_estacionamento = (RatingBar)view.findViewById(R.id.ratingBar_estacionamento);
        final RatingBar ratingBar_banheiros = (RatingBar)view.findViewById(R.id.ratingBar_banheiros);
        final RatingBar ratingBar_rampa = (RatingBar)view.findViewById(R.id.ratingBar_rampa);
        final RatingBar ratingBar_mobiliario = (RatingBar)view.findViewById(R.id.ratingBar_mobiliario);
        final RatingBar ratingBar_circulacao = (RatingBar)view.findViewById(R.id.ratingBar_circulacao);


        textView_titulo_avaliar.setText(MyData.nameArray[posiçãoID]);
        textView_corpo_avaliar.setText(MyData.versionArray[posiçãoID]);
        imageView_avaliar.setImageResource(MyData.drawableArray[posiçãoID]);




        ratingBar_avaliar_total = (RatingBar)view.findViewById(R.id.ratingBar_avaliar_total);

        String valor_nota =""+posiçãoID;
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        ratingBar_avaliar_total.setRating(sharedPref.getInt(valor_nota, 0));
//        Log.d("nota_final_detalhes",""+sharedPref.getInt(valor_nota, 0));




        Button button = (Button) view.findViewById(R.id.button_avaliar_nota);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int nota_estacionamento = (int)Math.round(ratingBar_estacionamento.getRating());
                int nota_banheiro = (int)Math.round(ratingBar_banheiros.getRating());
                int nota_rampa = (int)Math.round(ratingBar_rampa.getRating());
                int nota_mobiliario = (int)Math.round(ratingBar_mobiliario.getRating());
                int nota_circulação = (int)Math.round(ratingBar_circulacao.getRating());

                int nota_final=(nota_estacionamento+nota_banheiro+nota_rampa+nota_mobiliario+nota_circulação)/5;
                Log.d("nota_final",""+nota_final);


                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                String valor_nota =""+posiçãoID;



                int valNotaTotal=(sharedPref.getInt(valor_nota, 0));
                Log.d("nota_TOTAS",""+sharedPref.getInt(valor_nota, 0));

                if(valNotaTotal!=0){
                    nota_final=(nota_final+valNotaTotal)/2;
                    Log.d("NOVA_nota_final",""+sharedPref.getInt(valor_nota, 0));
                }

//                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                String valor =""+posiçãoID;
                editor.putInt(valor, nota_final);
                editor.commit();

                getActivity().onBackPressed();
            }
        });
        return view;
    }







}
