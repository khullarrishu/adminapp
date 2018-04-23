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

import java.util.ArrayList;
import java.util.List;

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






    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;



        getData();
    }


public void getData(){



    root.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot snapshot) {
            mMap.clear();

            for (DataSnapshot postSnapshot : snapshot.child("Team A").getChildren()) {


                Locations location = postSnapshot.getValue(Locations.class);

                Log.d("test longitude", String.valueOf(location.longitude));
                Log.d("test latitude", String.valueOf(location.latitude));
                Log.d("test playerName", String.valueOf(location.Name));


                LatLng sydney = new LatLng(location.latitude, location.longitude);
                mMap.addMarker(new MarkerOptions().position(sydney).title(location.Name));


            }


            for (DataSnapshot postSnapshot : snapshot.child("Team B").getChildren()) {


                Locations location = postSnapshot.getValue(Locations.class);

                Log.d("test longitude", String.valueOf(location.longitude));
                Log.d("test latitude", String.valueOf(location.latitude));
                Log.d("test playerName", String.valueOf(location.Name));


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


