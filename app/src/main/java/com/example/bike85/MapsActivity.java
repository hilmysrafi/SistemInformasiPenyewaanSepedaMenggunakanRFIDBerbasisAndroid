package com.example.bike85;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private static final String TAG = MapsActivity.class.getSimpleName();
    private HashMap<String, Marker> mMarkers = new HashMap<>();
    private GoogleMap mMap, nMap;

    LatLng currentLocation;
    TextView deviceId, lastUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        deviceId = (TextView) findViewById(R.id.device_name);
        lastUpdate = (TextView)findViewById(R.id.last_update);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
//        nMap = googleMap;
//
//        LatLng parking = new LatLng(-7.291907, 112.797889);
//        nMap.addMarker(new MarkerOptions().position(parking).icon(BitmapDescriptorFactory.fromResource(R.drawable.bikeparking)).title("Parking Area"));
//        nMap.moveCamera(CameraUpdateFactory.newLatLng(parking));
        mMap.setMaxZoomPreference(20);
        lokasi();

    }

    private void lokasi() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Data GPS");
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                mMap.clear();
                MARKER(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                MARKER(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "Database Error", databaseError.toException());
            }
        });
    }


    private void MARKER(DataSnapshot dataSnapshot) {
        String key = dataSnapshot.getKey();
        HashMap<String, Object> value = (HashMap<String, Object>) dataSnapshot.getValue();
        double lat = Double.parseDouble(value.get("latitude").toString());
        double lon = Double.parseDouble(value.get("longitude").toString());
        String device = value.get("deviceId").toString();
        String update = value.get("lastUpdate").toString();

        currentLocation = new LatLng(lat,lon);
        if (!mMarkers.containsKey(key)) {
            mMarkers.put(key, mMap.addMarker(new MarkerOptions().title(device).icon(BitmapDescriptorFactory.fromResource(R.drawable.bike_icon)).position(currentLocation)));
        } else {
            mMarkers.get(key).setPosition(currentLocation);
        }
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (Marker m : mMarkers.values()) {
            builder.include(m.getPosition());
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(builder.build(),300));
        deviceId.setText("Id Sepeda: " + device);
        lastUpdate.setText("Waktu: " + update);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MapsActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}