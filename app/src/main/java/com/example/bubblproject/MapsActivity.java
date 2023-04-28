package com.example.bubblproject;

import static com.example.bubblproject.MainActivity.CanavanArena;

import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private SearchView mapSearch;

    private FloatingActionButton addLocation;

    private FloatingActionButton cancelButton;
    private LatLng locPos;
    private Address address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);

        mapSearch = findViewById(R.id.mapSearch);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String locationName = mapSearch.getQuery().toString();
                List<Address> addressList = null;

                if(locationName != null){
                    Geocoder geocoder = new Geocoder(MapsActivity.this);


                    try{
                        addressList = geocoder.getFromLocationName(locationName, 1, CanavanArena.latitude - 5, CanavanArena.longitude - 5,CanavanArena.latitude + 5, CanavanArena.longitude + 5);
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }

                    address = addressList.get(0);
                    locPos = new LatLng(address.getLatitude(), address.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(locPos).title(locationName));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(locPos, 10));
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        addLocation = findViewById(R.id.addLocation);

        addLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoCreateActivity();
            }
        });

        mapFragment.getMapAsync(MapsActivity.this);
    }

    private void gotoCreateActivity() {

        System.out.println(locPos.longitude);

        Intent intent = new Intent(this, CreateTaskActivity.class);
        intent.putExtra("Address", address.getAddressLine(0));
        intent.putExtra("Latitude", locPos.latitude);
        intent.putExtra("Longitude", locPos.longitude);
        startActivity(intent);
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

        // Add a marker in Sydney and move the camera


        mMap.addMarker(new MarkerOptions().position(CanavanArena).title("Current Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(CanavanArena));
    }
}