package com.bmsit.marzil.driverapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    LocationManager locationManager;
    Button login;
    Switch loccheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.login);

        Toast.makeText(this,"Hello",Toast.LENGTH_SHORT).show();
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
        {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {

                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    Log.d("latitude",Double.toString(latitude));
                    String message = Double.toString(latitude) + "   " + Double.toString(longitude);
                    Toast.makeText(getBaseContext(), "Coordinates: " + message, Toast.LENGTH_LONG).show();


                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            });
        }
        else if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
        {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {

                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    String message = Double.toString(latitude) + "   " + Double.toString(longitude);
                    Toast.makeText(getBaseContext(), "Coordinates: " + message, Toast.LENGTH_LONG).show();



                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            });
        }
    }
}
