package com.ichef.android.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ichef.android.R;
import com.ichef.android.utils.CommonUtility;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationManually extends AppCompatActivity implements LocationListener{

    LinearLayout locationtxt;
    TextView locationtx;
    String city;
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;
    TextView txtLat;
    String lat;
    String provider;
    protected String latitude, longitude;
    protected boolean gps_enabled, network_enabled;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_manually);
        LinearLayout back = findViewById(R.id.llback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocationManually.this, HomePageActivity.class);
                startActivity(intent);
            }
        });
        init();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
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
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

    }

    private void init() {
        locationtxt = findViewById(R.id.currentlocation);
        locationtx = findViewById(R.id.locationtx);
       /* LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(5, 5, 0, 0);
        locationtx.setLayoutParams(params);*/
    }

    @Override
    public void onLocationChanged(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        String lat = String.valueOf(latitude);
        String lon = String.valueOf(longitude);
        CommonUtility.setSetting(getApplicationContext(), "latitude", lat);
        CommonUtility.setSetting(getApplicationContext(), "longitude", lon);
        List<Address> addresses=null;
        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            // addresses = geocoder.getFromLocation(23.2601154, 77.4203411, 1);
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("Geo Location Priyanka", "\n"+addresses.get(0).getAddressLine(0)+", "+
                addresses.get(0).getAddressLine(1)+", "+addresses.get(0).getAddressLine(2));


        if (addresses!=null && addresses.size()>0){

            Address address = addresses.get(0);
            StringBuilder sb = new StringBuilder();
            for (int i=0; i<address.getMaxAddressLineIndex(); i++){
                sb.append(address.getAddressLine(i)).append("\n");
            }
            city = sb.append(address.getLocality()).append("\n").toString();
            Log.d("city name", city);
        }
        // txtLat.setText("LatLong:" + location.getLatitude() + "," + location.getLongitude());
        locationtx.setText("Current Location: "+city);
        String currentlocation =city.toString();

    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude","disable");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude","enable");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","status");
    }
}