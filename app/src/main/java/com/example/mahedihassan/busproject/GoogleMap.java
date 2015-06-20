package com.example.mahedihassan.busproject;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class GoogleMap extends FragmentActivity implements GPSTracker.GetLocationFrequently{


    private com.google.android.gms.maps.GoogleMap mMap; // Might be null if Google Play services APK is not available.

    GPSTracker gpsTracker;
    SendData sendData;

    double startOne=0, startTwo=0;
    double lati=0;
    double longi=0;
    String admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map);

        gpsTracker = new GPSTracker(this, this);
        String stringLatitude = String.valueOf(gpsTracker.latitude);
        String stringLongitude = String.valueOf(gpsTracker.longitude);
        lati = Double.parseDouble(stringLatitude);
        longi = Double.parseDouble(stringLongitude);

        //longitude = Float.valueOf((float) gpsTracker.longitude);
        Toast.makeText(getApplicationContext(),stringLatitude,Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(),stringLongitude,Toast.LENGTH_LONG).show();

        try{

            Intent intent = this.getIntent();
            admin = intent.getStringExtra("admin");

        }catch (NullPointerException e)
        {

        }

        setUpMapIfNeeded();

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

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

    private void setUpMap() {
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(lati, longi))
                .title("Starting")).showInfoWindow();
        startOne=lati;
        startTwo=longi;

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lati, longi), 16));
        mMap.setMyLocationEnabled(true);
    }

    @Override
    public void getLoc(Location loc) {
        try{
            if (admin.equals("admin"))
            {
               sendData = new SendData(loc.getLatitude(),loc.getLongitude());
            }
            else
            {
                /*
                Toast.makeText(getApplicationContext(),loc.getLatitude()+"   "+loc.getLatitude(),Toast.LENGTH_LONG).show();
                mMap.addMarker(new MarkerOptions().position(new LatLng(loc.getLatitude(), loc.getLongitude())).title("Marker"));
                mMap.setMyLocationEnabled(true);
                */
            }

        }catch (NullPointerException e)
        {

        }
        Toast.makeText(getApplicationContext(),loc.getLatitude()+"   "+loc.getLatitude(),Toast.LENGTH_LONG).show();
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(loc.getLatitude(), loc.getLongitude()))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                .title("Marker"));
        mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(startOne, startTwo), new LatLng(loc.getLatitude(), loc.getLongitude()))
                .width(5)
                .color(Color.RED));

        startOne=loc.getLatitude();
        startTwo=loc.getLongitude();
        mMap.setMyLocationEnabled(true);
    }
}
