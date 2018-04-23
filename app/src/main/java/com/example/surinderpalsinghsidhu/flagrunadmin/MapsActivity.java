package com.example.surinderpalsinghsidhu.flagrunadmin;

import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference root = database.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                // mMap.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {



                }
            }
            @Override
            public void onCancelled(DatabaseError firebaseError) {
                /*
                 * You may print the error message.
                 **/
            }
        });

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



        root.child("Team A").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Locations location = postSnapshot.getValue(Locations.class);
                    Log.d("longitude", String.valueOf(location.longitude));
                    Log.d("latitude", String.valueOf(location.latitude));
                    Log.d("playerName", String.valueOf(location.Name));



              LatLng sydney = new LatLng(location.latitude, location.longitude);
//                    mMap.addMarker(new MarkerOptions().position(sydney).title(location.Name));


                    MarkerOptions a = new MarkerOptions().position(new LatLng(location.latitude,location.longitude));
                     mMap.addMarker(a).setPosition(sydney);

                }
            }
            @Override
            public void onCancelled(DatabaseError firebaseError) {
                /*
                 * You may print the error message.
                 **/
            }
        });





        root.child("Team B").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {


                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Locations location = postSnapshot.getValue(Locations.class);
                    Log.d("longitude", String.valueOf(location.longitude));
                    Log.d("latitude", String.valueOf(location.latitude));
                    Log.d("playerName", String.valueOf(location.Name));


                    LatLng sydney = new LatLng(location.latitude, location.longitude);
                    mMap.addMarker(new MarkerOptions().position(sydney).title(location.Name));


                }
            }
            @Override
            public void onCancelled(DatabaseError firebaseError) {
                /*
                 * You may print the error message.
                 **/
            }
        });
    }









}


