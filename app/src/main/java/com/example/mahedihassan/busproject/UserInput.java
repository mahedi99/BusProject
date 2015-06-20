package com.example.mahedihassan.busproject;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class UserInput extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    Double latitude=0.0;
    Double longitude=0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_input);


        //Bundle bundle = getIntent().getExtras();
        //bundle.getString("LATI");
        Intent intent = this.getIntent();
        latitude = intent.getDoubleExtra("LATI",0.0);
        longitude = intent.getDoubleExtra("LONGI",0.0);

        String str1 = String.valueOf(latitude);
        String str2 = String.valueOf(latitude);
        Toast.makeText(getApplicationContext(),str1,Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), str2,Toast.LENGTH_LONG).show();



        //latitude = bundle.getDouble("LATI");
        //latitude = bundle.getDouble("LONGI");

        setUpMapIfNeeded();

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        if (longitude!=0.0) {
            mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("Marker"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 8));
            mMap.setMyLocationEnabled(true);
        }
        else {
            //Toast.makeText(getApplicationContext(),"null value",Toast.LENGTH_LONG).show();
            mMap.addMarker(new MarkerOptions().position(new LatLng(25.74389, 89.27523)).title("Marker"));

            mMap.addMarker(new MarkerOptions().position(new LatLng(22.70000, 90.36667)).title("Marker"));

            mMap.addMarker(new MarkerOptions().position(new LatLng(22.81667, 89.55000)).title("Marker"));

            mMap.addMarker(new MarkerOptions().position(new LatLng(23.80625, 90.41269)).title("Marker"));
            mMap.addMarker(new MarkerOptions().position(new LatLng(23.81010, 90.41080)).title("Marker"));
            mMap.addMarker(new MarkerOptions().position(new LatLng(23.80829, 90.41295)).title("Marker"));
            mMap.addMarker(new MarkerOptions().position(new LatLng(23.80672, 90.41252)).title("Marker"));
            mMap.addMarker(new MarkerOptions().position(new LatLng(23.77774, 90.34600)).title("Marker"));
            mMap.addMarker(new MarkerOptions().position(new LatLng(23.73225, 90.42445)).title("Marker"));

            mMap.addMarker(new MarkerOptions().position(new LatLng(24.89778, 91.87139)).title("Marker"));

            mMap.addMarker(new MarkerOptions().position(new LatLng(24.36667, 88.60000)).title("Marker"));

            mMap.addMarker(new MarkerOptions().position(new LatLng(22.36667, 91.80000)).title("Marker"));



            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(23.80625, 90.41269), 7));

            mMap.addPolyline(new PolylineOptions()
                    .add(new LatLng(25.74389, 89.27523), new LatLng(22.36667, 91.80000))
                    .width(5)
                    .color(Color.RED));

        }

    }
}
