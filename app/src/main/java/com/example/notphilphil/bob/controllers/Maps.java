package com.example.notphilphil.bob.controllers;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.notphilphil.bob.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class Maps extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LocationManager locationManager;
    private LocationListener locationListener;
    //private Button myLoc ;
    private Location myLocation ;
    //private FusedLocationProviderClient mFusedLocationClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                locationListener.onLocationChanged(location);
                //mMap.addMarker(new MarkerOptions().position(location).title("My Location")) ;
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

    }



    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
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
//        LatLng marks = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(marks).title("Markers of location"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(marks));

        DatabaseReference ref = LoggedUser.getRef().child("locations");
        ref.addValueEventListener(new ValueEventListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot loc : dataSnapshot.getChildren()) {
                    double lt = Double.parseDouble((String) loc.child("Latitude").getValue());
                    double lg = Double.parseDouble((String) loc.child("Longitude").getValue());
                    LatLng newMarker = new LatLng(lt, lg);
                    String name = (String)loc.child("Name").getValue();
                    String phone = (String)loc.child("Phone").getValue();
                    mMap.addMarker(new MarkerOptions()
                            .position(newMarker)
                            .title(name)
                            .snippet("Phone: "+phone));
                }

                //ADD ANOTHER MARKER TO DISPLAY CURRENT LOCATION

                //myLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                //myLocation = new Location(myLocation) ;
                //locationListener.onLocationChanged(myLocation);
                LatLng myLoc = new LatLng(33.777211, -84.397474);
                mMap.addMarker(new MarkerOptions().position(myLoc).title("My Location")) ;

                mMap.moveCamera(CameraUpdateFactory
                        .newLatLng(new LatLng(33.775331,-84.395824)));
                mMap.setMaxZoomPreference(20);
                mMap.setMinZoomPreference(10);
            }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Probably do nothing.
                    Log.w("LocationsActivity", databaseError.getDetails());
                }
            });
    }
}
