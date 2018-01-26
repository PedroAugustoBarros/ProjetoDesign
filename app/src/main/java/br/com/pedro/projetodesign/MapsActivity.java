package br.com.pedro.projetodesign;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    int posiçãoID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Intent intent = getIntent();
        String myValue = intent.getStringExtra("key"); //if it's a string you stored.


        posiçãoID = Integer.parseInt(myValue);
        Log.d("posiçãoID",""+posiçãoID);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {


//        CameraUpdate zoom=CameraUpdateFactory.zoomTo(15);

        mMap = googleMap;

            String nomeLocal=MyData.nameArray[posiçãoID];
            Double Lat=MyData.Lat[posiçãoID];
            Double Long=MyData.Long[posiçãoID];

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(Lat,Long);
//        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title(nomeLocal));
//        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        mMap.setMinZoomPreference(16.0f);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }
}