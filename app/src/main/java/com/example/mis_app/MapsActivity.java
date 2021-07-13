package com.example.mis_app;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    List<Integer> LatList = new ArrayList<Integer>();
    List<Integer> LonList = new ArrayList<Integer>();

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Map<String, Object> conversion = new HashMap<>();
    CollectionReference conversionRef = db.collection("conversions");
    List<String> conversionList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retrieve the content view that renders the map.
        setContentView(R.layout.activity_maps);
        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //brazil
        LatList.add(-15);
        LonList.add(-47);
        //canada
        LatList.add(45);
        LonList.add(-75);
        //switzerland
        LatList.add(46);
        LonList.add(6);
        //denmark
        LatList.add(55);
        LonList.add(12);
        //europe(germany)
        LatList.add(52);
        LonList.add(13);
        //UK
        LatList.add(51);
        LonList.add(0);
        //philippines
        LatList.add(14);
        LonList.add(120);
        //russia
        LatList.add(55);
        LonList.add(37);
        //sweden
        LatList.add(59);
        LonList.add(18);
        //usa
        LatList.add(38);
        LonList.add(77);


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
        mMap = googleMap;
        db.collection("conversions")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String baseCurrency = document.getId();
                                String rateValue = (String)document.get("rate");

                                conversionList.add(baseCurrency + " = " + rateValue);

                                String toastSTR = baseCurrency + " = " + rateValue;
                                //Toast toast = Toast.makeText(getApplicationContext(),  toastSTR, Toast.LENGTH_SHORT);
                                //toast.show();
                            }
                        }
                        TextView label = (TextView)findViewById(R.id.rate1Val);
                        int listSize = conversionList.size();
                        if (!conversionList.isEmpty()) {
                            LatLng marker = getCoords(conversionList.get(0).toString());

                            mMap.addMarker(new MarkerOptions().position(marker).title(conversionList.get(0).toString()));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));
                        }
                        if (listSize >= 2) {
                            LatLng marker = getCoords(conversionList.get(1).toString());

                            mMap.addMarker(new MarkerOptions().position(marker).title(conversionList.get(1).toString()));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));
                        }
                        if (listSize >= 3) {
                            LatLng marker = getCoords(conversionList.get(2).toString());

                            mMap.addMarker(new MarkerOptions().position(marker).title(conversionList.get(2).toString()));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));
                        }
                        if (listSize >= 4) {
                            LatLng marker = getCoords(conversionList.get(3).toString());

                            mMap.addMarker(new MarkerOptions().position(marker).title(conversionList.get(3).toString()));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));
                        }
                        if (listSize >= 5) {
                            LatLng marker = getCoords(conversionList.get(4).toString());

                            mMap.addMarker(new MarkerOptions().position(marker).title(conversionList.get(4).toString()));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));
                        }
                        if (listSize >= 6) {
                            LatLng marker = getCoords(conversionList.get(5).toString());

                            mMap.addMarker(new MarkerOptions().position(marker).title(conversionList.get(5).toString()));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));
                        }
                        if (listSize >= 7) {
                            LatLng marker = getCoords(conversionList.get(6).toString());

                            mMap.addMarker(new MarkerOptions().position(marker).title(conversionList.get(6).toString()));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));
                        }
                        if (listSize >= 8) {
                            LatLng marker = getCoords(conversionList.get(7).toString());

                            mMap.addMarker(new MarkerOptions().position(marker).title(conversionList.get(7).toString()));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));
                        }
                        if (listSize >= 9) {
                            LatLng marker = getCoords(conversionList.get(8).toString());

                            mMap.addMarker(new MarkerOptions().position(marker).title(conversionList.get(8).toString()));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));
                        }
                        if (listSize >= 10) {
                            LatLng marker = getCoords(conversionList.get(9).toString());

                            mMap.addMarker(new MarkerOptions().position(marker).title(conversionList.get(9).toString()));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));
                        }
                    }
                });

    }

    public LatLng getCoords(String val) {
        String newVal = val.substring(0,3);
        LatLng output;
        if (newVal.equals("BRL")) {
            output = new LatLng(-15,-47);
            return output;
        }
        if (newVal.equals("CAD")) {
            output = new LatLng(45,-75);
            return output;
        }
        if (newVal.equals("CHF")) {
            output = new LatLng(47,8);
            return output;
        }
        if (newVal.equals("DKK")) {
            output = new LatLng(55,12);
            return output;
        }
        if (newVal.equals("EUR")) {
            output = new LatLng(52,13);
            return output;
        }
        if (newVal.equals("GBP")) {
            output = new LatLng(51,0);
            return output;
        }
        if (newVal.equals("PHP")) {
            output = new LatLng(14,120);
            return output;
        }
        if (newVal.equals("RUB")) {
            output = new LatLng(55,37);
            return output;
        }
        if (newVal.equals("SEK")) {
            output = new LatLng(59,18);
            return output;
        }
        if (newVal.equals("USD")) {
            output = new LatLng(38,-77);
            return output;
        }
        if (newVal.equals("NZD")) {
            output = new LatLng(-41,174);
            return output;
        }
        output = new LatLng(0,0);
        return output;
    }
}
