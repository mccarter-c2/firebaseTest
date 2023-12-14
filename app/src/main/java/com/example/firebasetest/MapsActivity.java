package com.example.firebasetest;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.firebasetest.databinding.ActivityMapsBinding;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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
        mMap = googleMap;

        // Add a marker to the map and move the camera
        LatLng marker = new LatLng(55.0071, -7.3239);
        mMap.addMarker(new MarkerOptions().position(marker).title("Ulster University"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 12.0f));

        Geocoder geoCoder = new Geocoder(getBaseContext(), Locale.getDefault());
        try{

            Intent i = getIntent();
            //String placeName = i.getStringExtra(MainActivity.locationMessage);//""; //address Foyle Women's Aid - put in address from add event database
           String placeName = "Foyle Women's Aid"; //This makes add events work
            List<Address> addresses = geoCoder.getFromLocationName(placeName,1);
            Address address = addresses.get(0);
            if (addresses.size() > 0){
                LatLng p = new LatLng(address.getLatitude(), address.getLongitude());
                mMap.addMarker(new MarkerOptions().position(p).title(placeName));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p,12.0f));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        try{

            Intent i = getIntent();
            //String placeName = i.getStringExtra(MainActivity.locationMessage);//""; //address Foyle Women's Aid - put in address from add event database
            String placeName = "Women's Centre Derry"; //This makes add events work
            List<Address> addresses = geoCoder.getFromLocationName(placeName,1);
            Address address = addresses.get(0);
            if (addresses.size() > 0){
                LatLng p = new LatLng(address.getLatitude(), address.getLongitude());
                mMap.addMarker(new MarkerOptions().position(p).title(placeName));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p,12.0f));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        try{

            Intent i = getIntent();
            //String placeName = i.getStringExtra(MainActivity.locationMessage);//""; //address Foyle Women's Aid - put in address from add event database
            String placeName = "Foyle Womens Information Network"; //This makes add events work
            List<Address> addresses = geoCoder.getFromLocationName(placeName,1);
            Address address = addresses.get(0);
            if (addresses.size() > 0){
                LatLng p = new LatLng(address.getLatitude(), address.getLongitude());
                mMap.addMarker(new MarkerOptions().position(p).title(placeName));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p,12.0f));
            }
        } catch (IOException e){
            e.printStackTrace();
        }




        // converts lat and lng into address
//        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//            @Override
//            public void onMapClick(LatLng point) {
//                Geocoder geoCoder = new Geocoder(getBaseContext(), Locale.getDefault());
//                try {
//                    List<Address> addresses = geoCoder.getFromLocation(point.latitude, point.longitude, 1);
//                    Address address = addresses.get(0);
//                    String add = "";
//
//                    if (addresses.size() > 0) {
//                        add += address.getAddressLine(0) + "\n";
//                    }
//                    Toast.makeText(getBaseContext(), add, Toast.LENGTH_SHORT).show();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });

    }
}