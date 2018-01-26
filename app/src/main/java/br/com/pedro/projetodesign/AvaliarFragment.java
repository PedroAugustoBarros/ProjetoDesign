package br.com.pedro.projetodesign;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
    Button butt_avaliar_comentario;


    TextView txtView_estacionamento_sinopse;
    TextView txtView_banheiros_sinopse;
    TextView txtView_rampa_sinopse;
    TextView txtView_mobiliario_sinopse;
    TextView txtView_circulacao_sinopse;

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

        butt_avaliar_comentario = (Button) view.findViewById(R.id.butt_avaliar_comentario);

        final RatingBar ratingBar_estacionamento = (RatingBar)view.findViewById(R.id.ratingBar_estacionamento);
        final RatingBar ratingBar_banheiros = (RatingBar)view.findViewById(R.id.ratingBar_banheiros);
        final RatingBar ratingBar_rampa = (RatingBar)view.findViewById(R.id.ratingBar_rampa);
        final RatingBar ratingBar_mobiliario = (RatingBar)view.findViewById(R.id.ratingBar_mobiliario);
        final RatingBar ratingBar_circulacao = (RatingBar)view.findViewById(R.id.ratingBar_circulacao);


        txtView_estacionamento_sinopse = (TextView) view.findViewById(R.id.txtView_estacionamento_sinopse);
        txtView_banheiros_sinopse = (TextView) view.findViewById(R.id.txtView_banheiros_sinopse);
        txtView_rampa_sinopse = (TextView) view.findViewById(R.id.txtView_rampa_sinopse);
        txtView_mobiliario_sinopse = (TextView) view.findViewById(R.id.txtView_mobiliario_sinopse);
        txtView_circulacao_sinopse = (TextView) view.findViewById(R.id.txtView_circulacao_sinopse);



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


        butt_avaliar_comentario.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FragmentManager mFragmentManager;
                mFragmentManager = getFragmentManager();
                FragmentTransaction ft = mFragmentManager.beginTransaction();

                ComentarioFragment comentFragment = (ComentarioFragment) getFragmentManager().findFragmentByTag("comentario");

                if (getFragmentManager().findFragmentByTag("comentario") != null) {
                    ft.remove(comentFragment);
                }

                if (comentFragment == null) {
                    comentFragment = new ComentarioFragment();

                }
                ft.replace(R.id.frameLayoutFragment, comentFragment, "comentario");
                ft.addToBackStack("comentario");
                ft.commit();
            }
        });


        //----------------------------------------------------


        ratingBar_estacionamento.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                int nota = (int)Math.round(ratingBar_estacionamento.getRating());

                if (nota==0){
                    txtView_estacionamento_sinopse.setText("");
                }else if (nota==1){
                    txtView_estacionamento_sinopse.setText("NÃO TEM");
                }else if (nota==2){
                    txtView_estacionamento_sinopse.setText("RUIM");
                }else if (nota==3){
                    txtView_estacionamento_sinopse.setText("REGULAR");
                }else if (nota==4){
                    txtView_estacionamento_sinopse.setText("BOM");
                }else if (nota==5){
                    txtView_estacionamento_sinopse.setText("MUITO BOM");
                }
            }
        });

        ratingBar_banheiros.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                int nota = (int)Math.round(ratingBar_banheiros.getRating());

                if (nota==0){
                    txtView_banheiros_sinopse.setText("");
                }else if (nota==1){
                    txtView_banheiros_sinopse.setText("NÃO TEM");
                }else if (nota==2){
                    txtView_banheiros_sinopse.setText("RUIM");
                }else if (nota==3){
                    txtView_banheiros_sinopse.setText("REGULAR");
                }else if (nota==4){
                    txtView_banheiros_sinopse.setText("BOM");
                }else if (nota==5){
                    txtView_banheiros_sinopse.setText("MUITO BOM");
                }
            }
        });

        ratingBar_rampa.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                int nota = (int)Math.round(ratingBar_rampa.getRating());

                if (nota==0){
                    txtView_rampa_sinopse.setText("");
                }else if (nota==1){
                    txtView_rampa_sinopse.setText("NÃO TEM");
                }else if (nota==2){
                    txtView_rampa_sinopse.setText("RUIM");
                }else if (nota==3){
                    txtView_rampa_sinopse.setText("REGULAR");
                }else if (nota==4){
                    txtView_rampa_sinopse.setText("BOM");
                }else if (nota==5){
                    txtView_rampa_sinopse.setText("MUITO BOM");
                }
            }
        });

        ratingBar_mobiliario.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                int nota = (int)Math.round(ratingBar_mobiliario.getRating());

                if (nota==0){
                    txtView_mobiliario_sinopse.setText("");
                }else if (nota==1){
                    txtView_mobiliario_sinopse.setText("NÃO TEM");
                }else if (nota==2){
                    txtView_mobiliario_sinopse.setText("RUIM");
                }else if (nota==3){
                    txtView_mobiliario_sinopse.setText("REGULAR");
                }else if (nota==4){
                    txtView_mobiliario_sinopse.setText("BOM");
                }else if (nota==5){
                    txtView_mobiliario_sinopse.setText("MUITO BOM");
                }
            }
        });

        ratingBar_circulacao.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                int nota = (int)Math.round(ratingBar_circulacao.getRating());

                if (nota==0){
                    txtView_circulacao_sinopse.setText("");
                }else if (nota==1){
                    txtView_circulacao_sinopse.setText("NÃO TEM");
                }else if (nota==2){
                    txtView_circulacao_sinopse.setText("RUIM");
                }else if (nota==3){
                    txtView_circulacao_sinopse.setText("REGULAR");
                }else if (nota==4){
                    txtView_circulacao_sinopse.setText("BOM");
                }else if (nota==5){
                    txtView_circulacao_sinopse.setText("MUITO BOM");
                }
            }
        });





        return view;
    }







}
