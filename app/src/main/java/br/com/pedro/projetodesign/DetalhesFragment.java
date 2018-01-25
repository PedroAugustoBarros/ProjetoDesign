package br.com.pedro.projetodesign;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
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

public class DetalhesFragment extends Fragment {

//    private OnFragmentInteractionListener mListener;

    TextView textView_det_titulo;
    TextView textView_det_corpo;
    ImageView imageView_det;
    RatingBar ratingBar_detalhes_total;

    int posiçãoID;
    String myValue;

    Button butt_avaliar;

    public DetalhesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();
        myValue = bundle.getString("message");
        Log.d("Valor",""+myValue);


        posiçãoID=Integer.parseInt(myValue);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =inflater.inflate(R.layout.fragment_detalhes, container, false);

        textView_det_titulo = (TextView) view.findViewById(R.id.textView_det_titulo);
        textView_det_corpo = (TextView) view.findViewById(R.id.textView_det_corpo);
        imageView_det = (ImageView) view.findViewById(R.id.imageView_det);

        ratingBar_detalhes_total = (RatingBar)view.findViewById(R.id.ratingBar_detalhes_total);

        String valor_nota =""+posiçãoID;
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        ratingBar_detalhes_total.setRating(sharedPref.getInt(valor_nota, 0));
        Log.d("nota_final_detalhes",""+sharedPref.getInt(valor_nota, 0));


        textView_det_titulo.setText(MyData.nameArray[posiçãoID]);
        textView_det_corpo.setText(MyData.versionArray[posiçãoID]);
        imageView_det.setImageResource(MyData.drawableArray[posiçãoID]);







        butt_avaliar = (Button) view.findViewById(R.id.butt_avaliar);
        butt_avaliar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                FragmentManager mFragmentManager;
                mFragmentManager = getFragmentManager();
                FragmentTransaction ft = mFragmentManager.beginTransaction();

                AvaliarFragment avaliFragment = (AvaliarFragment) getFragmentManager().findFragmentByTag("avaliar");

                if (getFragmentManager().findFragmentByTag("avaliar") != null) {
                    ft.remove(avaliFragment);
                    Log.d("entrou1 ","Entrou1");
                }

                if (avaliFragment == null) {
                    avaliFragment = new AvaliarFragment();

                }

                ft.replace(R.id.frameLayoutFragment, avaliFragment, "avaliar");
                ft.addToBackStack("avaliar");
                ft.commit();
            }
        });


        return view;

    }


//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
