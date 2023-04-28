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
import java.util.Date;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private SearchView mapSearch;

    private FloatingActionButton addLocation;

    private FloatingActionButton cancelButton;
    private LatLng locPos;
    private Address address;

    private Date taskDate = null;

    private String taskName = "";

    private int prioClicked = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);

        mapSearch = findViewById(R.id.mapSearch);

        Intent intent = getIntent();

        taskDate = (Date) intent.getSerializableExtra("TaskDate");
        taskName = (String) intent.getSerializableExtra("TaskName");
        System.out.println((int) intent.getSerializableExtra("TaskPrio"));
        prioClicked = (int) intent.getSerializableExtra("TaskPrio");


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

                    try {
                        address = addressList.get(0);
                        locPos = new LatLng(address.getLatitude(), address.getLongitude());
                        mMap.addMarker(new MarkerOptions().position(locPos).title(locationName));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(locPos, 10));
                    }
                    catch(IndexOutOfBoundsException e){
                        Context context = getApplicationContext();
                        CharSequence text = "Location not found.";
                        int duration = Toast.LENGTH_SHORT;
                        e.printStackTrace();
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
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

        cancelButton = findViewById(R.id.cancelLocation);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoCreateActivityWithoutLocation();
            }
        });

        mapFragment.getMapAsync(MapsActivity.this);
    }

    private void gotoCreateActivity() {

        Intent intent = new Intent(this, CreateTaskActivity.class);
        intent.putExtra("Address", address.getAddressLine(0));
        intent.putExtra("Latitude", locPos.latitude);
        intent.putExtra("Longitude", locPos.longitude);
        if(taskName != null) {
            intent.putExtra("TaskName", taskName);
        }
        intent.putExtra("TaskDate", taskDate);
        intent.putExtra("TaskPrio", prioClicked);
        startActivity(intent);
    }

    private void gotoCreateActivityWithoutLocation() {

        Intent intent = new Intent(this, CreateTaskActivity.class);
        if(taskName != null) {
            intent.putExtra("TaskName", taskName);
        }
        intent.putExtra("TaskDate", taskDate);
        System.out.println(prioClicked);
        intent.putExtra("TaskPrio", prioClicked);
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