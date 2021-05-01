package com.ichef.android.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.ichef.android.MainActivity;
import com.ichef.android.R;
import com.ichef.android.adapter.MyExpandableListAdapter;
import com.ichef.android.fragment.HomeFragment;
import com.ichef.android.utils.CommonUtility;
import com.ichef.android.utils.Prefrence;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class HomePageActivity extends AppCompatActivity
        // implements LocationListener
        // implements  NavigationView.OnNavigationItemSelectedListener
{
    MyExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    TextView logouthere;
    private static long back_pressed;
    DrawerLayout drawer;
    View headerView;
    NavigationView navigationView;
    public static TextView headerName, headerLastName, headerMobno;
    public static ImageView profilepic;
    ImageView nav_btn, profile;
    int usertyp;
    String city;
    TextView locationtxt;
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;
    TextView txtLat;
    String lat;
    String provider;
    protected String latitude, longitude;
    protected boolean gps_enabled, network_enabled;
    String userid, firstname, lastname, email, password, mobile, title, msg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page2);
        String city = Prefrence.get(HomePageActivity.this, Prefrence.CITYNAME);
      //  Toast.makeText(HomePageActivity.this, ""+city, Toast.LENGTH_SHORT).show();

        init();
        onclick();

        locationtxt.setText(city);
       /* Intent intent = getIntent();
        String city= intent.getStringExtra("city");
        Toast.makeText(HomePageActivity.this, ""+city, Toast.LENGTH_SHORT).show();
        if(city.equals(null)||city.equals("")){
            locationtxt.setText(city);
            }
        else{
            locationtxt.setText("Location");
        }*/
/**/
        /*locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
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
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);*/
    }

    private void init() {

        profile=(ImageView) findViewById(R.id.profile);
        nav_btn = (ImageView) findViewById(R.id.nav_btn);
        locationtxt= findViewById(R.id.locationtxt);

        Fragment fragment=null;
        Intent in = getIntent();
        String str = "";
        fragment = new HomeFragment();

        if (fragment!=null){

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            //ft.addToBackStack(null);
            ft.replace(R.id.frame, fragment);
            ft.commit();

            //finish();
        }
        // navigationView.setNavigationItemSelectedListener(this);
    }

    private void onclick() {
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(HomePageActivity.this, R.anim.image_click));
                Intent in = new Intent(HomePageActivity.this, MyProfile.class);
                startActivity(in);
            }
        });
        nav_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(HomePageActivity.this, R.anim.image_click));
                Intent in = new Intent(HomePageActivity.this, LocationManually.class);
                startActivity(in);
            }
        });

    }

/*
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
        txtLat = (TextView) findViewById(R.id.locationtxt);
       // txtLat.setText("LatLong:" + location.getLatitude() + "," + location.getLongitude());

        txtLat.setText(city);
        String currentlocation =city.toString();

    }
*/

  /*  @Override
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
    }*/
}
