package com.example.notphilphil.bob.controllers

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v4.app.FragmentActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

import com.example.notphilphil.bob.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

class Maps : FragmentActivity(), OnMapReadyCallback {

    private var mMap: GoogleMap? = null
    private var locationManager: LocationManager? = null
    private var locationListener: LocationListener? = null
    //private Button myLoc ;
    private val myLocation: Location? = null

    //private FusedLocationProviderClient mFusedLocationClient;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {

                locationListener!!.onLocationChanged(location)
                //mMap.addMarker(new MarkerOptions().position(location).title("My Location")) ;
            }

            override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {

            }

            override fun onProviderEnabled(provider: String) {

            }

            override fun onProviderDisabled(provider: String) {

            }
        }

    }


    override fun onBackPressed() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
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
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        //        LatLng marks = new LatLng(-34, 151);
        //        mMap.addMarker(new MarkerOptions().position(marks).title("Markers of location"));
        //        mMap.moveCamera(CameraUpdateFactory.newLatLng(marks));

        val ref = LoggedUser.ref!!.child("locations")
        ref.addValueEventListener(object : ValueEventListener {
            @SuppressLint("MissingPermission")
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (loc in dataSnapshot.children) {
                    val lt = java.lang.Double.parseDouble(loc.child("Latitude").value as String?)
                    val lg = java.lang.Double.parseDouble(loc.child("Longitude").value as String?)
                    val newMarker = LatLng(lt, lg)
                    val name = loc.child("Name").value as String?
                    val phone = loc.child("Phone").value as String?
                    mMap!!.addMarker(MarkerOptions()
                            .position(newMarker)
                            .title(name)
                            .snippet("Phone: " + phone!!))
                }

                //ADD ANOTHER MARKER TO DISPLAY CURRENT LOCATION

                //myLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                //myLocation = new Location(myLocation) ;
                //locationListener.onLocationChanged(myLocation);
                val myLoc = LatLng(33.777211, -84.397474)
                mMap!!.addMarker(MarkerOptions().position(myLoc).title("My Location"))

                mMap!!.moveCamera(CameraUpdateFactory
                        .newLatLng(LatLng(33.775331, -84.395824)))
                mMap!!.setMaxZoomPreference(20f)
                mMap!!.setMinZoomPreference(10f)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Probably do nothing.
                Log.w("LocationsActivity", databaseError.details)
            }
        })
    }
}
