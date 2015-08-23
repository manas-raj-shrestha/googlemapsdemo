package com.example.manasshrestha.googlemapsdemo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.SphericalUtil;
import com.google.maps.android.clustering.ClusterManager;

import model.MyLocations;


public class MainActivity extends ActionBarActivity implements OnMapReadyCallback {
    Button btnMyLocation, btnPreviewCluster, btnDirec;
    public GoogleMap googleMap;
    LocationProvider locationProvider;
    // Declare a variable for the cluster manager.
    ClusterManager<MyLocations> mClusterManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        MapFragment mapFragment = (MapFragment) getFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
//
//        btnMyLocation = (Button) findViewById(R.id.btn_my_location);
//        btnPreviewCluster = (Button) findViewById(R.id.btn_clusters);
//        btnDirec = (Button) findViewById(R.id.btn_get_direc);
//
//        btnDirec.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//             JSONAsyncTask jsonAsyncTask = new JSONAsyncTask(MainActivity.this);
//                jsonAsyncTask.execute();
//
//            }
//        });
//
//        btnMyLocation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                locationProvider = new LocationProvider(MainActivity.this);
//
//                Log.e("lat long", locationProvider.getLatitude() + " " + locationProvider.getLongitude());
//
//                LatLng sydney = new LatLng(locationProvider.getLatitude(), locationProvider.getLongitude());
//
//                googleMap.setMyLocationEnabled(true);
//                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));
//
//                googleMap.addMarker(new MarkerOptions()
//                        .title("My Current Location.")
//                        .snippet("This is where the leapfroggers reside.")
//                        .position(sydney));
//
//                googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
//
//            }
//        });
//
//        btnPreviewCluster.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setUpClusterer();
//            }
//        });

    }

    @Override
    public void onMapReady(GoogleMap map) {

        LatLng newYork = new LatLng(40.749, -73.9);

        map.setMyLocationEnabled(true);

        CameraPosition cameraPosition = CameraPosition.builder().target(newYork).zoom(17).tilt(64).build();

        map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        map.addMarker(new MarkerOptions()
                .title("Home Town")
                .snippet("Enjoy the vibe!!!")
                .position(newYork));

        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        this.googleMap = map;
    }

    private void setUpClusterer() {


        // Position the map.
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(27.712066, 85.309331), 10));

        // Initialize the manager with the context and the map.
        // (Activity extends context, so we can pass 'this' in the constructor.)
        mClusterManager = new ClusterManager<MyLocations>(this, googleMap);

        // Point the map's listeners at the listeners implemented by the cluster
        // manager.
        googleMap.setOnCameraChangeListener(mClusterManager);
        googleMap.setOnMarkerClickListener(mClusterManager);

        // Add cluster items (markers) to the cluster manager.
        addItems();
    }

    private void addItems() {

        // Set some lat/lng coordinates to start with.
        double lat = 27.712066;
        double lng = 85.309331;

        // Add ten cluster items in close proximity, for purposes of this example.
        for (int i = 0; i < 10; i++) {
            double offset = i / 260d;
            lat = lat + offset;
            lng = lng + offset;
            MyLocations offsetItem = new MyLocations(lat, lng);
            mClusterManager.addItem(offsetItem);
        }
    }

    /**
     * uses spherical utils from google utils to calculate distance between two points
     * returns distance in meters
     */
    private Double calculateDistance() {
        return SphericalUtil.computeDistanceBetween(new LatLng(27.712066, 85.309331), new LatLng(29.712066, 89.309331));
    }

}
