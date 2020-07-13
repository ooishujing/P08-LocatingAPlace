package sg.edu.rp.c346.p08_locatingaplace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {
    Button btnN,btnC,btnE;
    private GoogleMap map;
    LatLng poiE, poiC, poiN, poiSG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback(){
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                }

                UiSettings ui = map.getUiSettings();
                ui.setCompassEnabled(true);
                ui.setZoomControlsEnabled(true);

                //Position
                poiE = new LatLng(1.352730, 103.944739);
                Marker east = map.addMarker(new
                        MarkerOptions()
                        .position(poiE)
                        .title("East")
                        .snippet("Block 555, Tampines Ave 3, 287788")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

                poiC = new LatLng(1.300908, 103.839842);
                Marker central = map.addMarker(new
                        MarkerOptions()
                        .position(poiC)
                        .title("Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                poiN = new LatLng(1.44224, 103.785733);
                Marker north = map.addMarker(new
                        MarkerOptions()
                        .position(poiN)
                        .title("North - HQ")
                        .snippet("Block 333, Admiralty Ave 3, 765654")
                        .icon(BitmapDescriptorFactory.fromResource(android.R.drawable.btn_star_big_on)));

                poiSG = new LatLng(1.3521, 103.8189);
                Marker sg = map.addMarker(new
                        MarkerOptions()
                        .position(poiSG));
                CameraUpdate location = CameraUpdateFactory.newLatLngZoom(poiSG, 0);
                map.animateCamera(location);

            }
        });



        btnN = findViewById(R.id.btnN);
        btnC = findViewById(R.id.btnC);
        btnE = findViewById(R.id.btnE);

        btnN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null) {
                    CameraUpdate location = CameraUpdateFactory.newLatLngZoom(poiN, 15);
                    map.animateCamera(location);
                }
            }
        });

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null) {
                    CameraUpdate location = CameraUpdateFactory.newLatLngZoom(poiC, 15);
                    map.animateCamera(location);
                }
            }
        });

        btnE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null) {
                    CameraUpdate location = CameraUpdateFactory.newLatLngZoom(poiE, 15);
                    map.animateCamera(location);
                }
            }
        });
    }//end of onCreate
}//end of MainActivity
