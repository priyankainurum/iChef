package com.ichef.android.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.ichef.android.R;
import com.ichef.android.utils.CommonUtility;
import com.ichef.android.utils.LocationTrack;
import com.ichef.android.utils.Prefrence;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
public class LocationManually extends AppCompatActivity
      //  implements LocationListener
{

    LinearLayout locationtxt;
    TextView locationtx;
    String city;
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;
    TextView txtLat;
    String lat;
    String provider;
    protected boolean gps_enabled, network_enabled;
    EditText etPlace;
    String s_source = "";
    SupportMapFragment mapFragment;
    private GoogleMap mMap;
    double srcLatitude, srcLlongitude;
    /**/
    /*private FusedLocationProviderClient fusedLocationProviderClient;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private static final String KEY_LOCATION = "location";
    private Location lastKnownLocation;
    private PlacesClient placesClient;
    private boolean locationPermissionGranted;
    private final LatLng defaultLocation = new LatLng(-33.8523341, 151.2106085);*/
    private ArrayList permissionsToRequest;
    private ArrayList permissionsRejected = new ArrayList();
    private ArrayList permissions = new ArrayList();

    private final static int ALL_PERMISSIONS_RESULT = 101;
    LocationTrack locationTrack;
    double slatitude,slongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_manually);
        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /*   if(city.equals(null)||city.equals("")){
                Intent intent = new Intent(LocationManually.this, HomePageActivity.class);
                startActivity(intent);}
                else{
                    Intent intent = new Intent(LocationManually.this, HomePageActivity.class);
                    intent.putExtra("city",city);
                    startActivity(intent);
                }
                finish();*/
                Intent intent = new Intent(LocationManually.this, HomePageActivity.class);
                startActivity(intent);
            }
        });
        permissions.add(ACCESS_FINE_LOCATION);
        permissions.add(ACCESS_COARSE_LOCATION);

        permissionsToRequest = findUnAskedPermissions(permissions);
        //get the permissions we have asked for before but are not granted..
        //we will store this in a global list to access later.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {


            if (permissionsToRequest.size() > 0)
                requestPermissions((String[]) permissionsToRequest.toArray(new String[permissionsToRequest.size()]), ALL_PERMISSIONS_RESULT);
        }
        init();
        onclick();


    }

    private void onclick() {
        locationtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LocationManually.this, "Getting Current Location...", Toast.LENGTH_LONG).show();

                locationTrack = new LocationTrack(LocationManually.this);


                if (locationTrack.canGetLocation()) {

                    slongitude = locationTrack.getLongitude();
                    slatitude = locationTrack.getLatitude();

                    Toast.makeText(LocationManually.this, "Longitude:" + Double.toString(slongitude) + "\nLatitude:" + Double.toString(slatitude), Toast.LENGTH_SHORT).show();
                } else {
                    locationTrack.showSettingsAlert();
                }
                String lat = String.valueOf(slatitude);
                String lon = String.valueOf(slongitude);
                CommonUtility.setSetting(getApplicationContext(), "latitude", lat);
                CommonUtility.setSetting(getApplicationContext(), "longitude", lon);
                List<Address> addresses=null;
                try {
                    Geocoder geocoder = new Geocoder(LocationManually.this, Locale.getDefault());
                    // addresses = geocoder.getFromLocation(23.2601154, 77.4203411, 1);
                    addresses = geocoder.getFromLocation(slatitude, slongitude, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
               /* Log.d("Geo Location Priyanka", "\n"+addresses.get(0).getAddressLine(0)+", "+
                        addresses.get(0).getAddressLine(1)+", "+addresses.get(0).getAddressLine(2));*/

                if (addresses!=null && addresses.size()>0){

                    Address address = addresses.get(0);
                    StringBuilder sb = new StringBuilder();
                    for (int i=0; i<address.getMaxAddressLineIndex(); i++){
                        sb.append(address.getAddressLine(i)).append("\n");
                    }
                    city = sb.append(address.getLocality()).append("\n").toString();
                    etPlace.setText(city);
                    Toast.makeText(LocationManually.this, ""+city, Toast.LENGTH_SHORT).show();
                    Prefrence.save(LocationManually.this, Prefrence.CITYNAME, city);
                  //  Log.d("city name", city);
                }
            }
        });
    }

    private ArrayList findUnAskedPermissions(ArrayList wanted) {
        ArrayList result = new ArrayList();

        for (Object perm : wanted) {
            if (!hasPermission((String) perm)) {
                result.add(perm);
            }
        }

        return result;
    }

    private boolean hasPermission(String permission) {
        if (canMakeSmores()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
            }
        }
        return true;
    }

    private boolean canMakeSmores() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode) {

            case ALL_PERMISSIONS_RESULT:
                for (Object perms : permissionsToRequest) {
                    if (!hasPermission((String) perms)) {
                        permissionsRejected.add(perms);
                    }
                }

                if (permissionsRejected.size() > 0) {


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale((String) permissionsRejected.get(0))) {
                            showMessageOKCancel("These permissions are mandatory for the application. Please allow access.",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermissions((String[]) permissionsRejected.toArray(new String[permissionsRejected.size()]), ALL_PERMISSIONS_RESULT);
                                            }
                                        }
                                    });
                            return;
                        }
                    }

                }

                break;
        }

    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(LocationManually.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationTrack.stopListener();
    }


    private void init() {
        locationtxt = findViewById(R.id.currentlocation);
        locationtx = findViewById(R.id.locationtx);
        Places.initialize(getApplicationContext(), getString(R.string.google_maps_key), Locale.US);
        // Initialize the AutocompleteSupportFragment.
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        etPlace = (EditText) autocompleteFragment.getView().findViewById(R.id.places_autocomplete_search_input);
        etPlace.setText("Search Location");
        etPlace.setTextSize(16);
        etPlace.setTextColor(Color.parseColor("#FF000000"));

        //autocompleteFragment.setCountry("UK");
        ImageView searchIcon = (ImageView)((LinearLayout)autocompleteFragment.getView()).getChildAt(0);
        searchIcon.setImageDrawable(getResources().getDrawable(R.drawable.search));
        // Specify the types of place data to return.
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG));

        // Set up a PlaceSelectionListener to handle the response.
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NotNull Place place) {
                // TODO: Get info about the selected place.
                s_source = place.getName();
                Log.i("SHUBHAM", "Place: " + place.getName() + ", " + place.getId());
              //  mMap.addMarker(new MarkerOptions().position(place.getLatLng()).title(place.getName()));
               // mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(place.getLatLng(), 10));
                srcLatitude = place.getLatLng().latitude;
                srcLlongitude = place.getLatLng().longitude;
                Prefrence.save(LocationManually.this, Prefrence.CITYNAME, s_source);

            }
            @Override
            public void onError(@NotNull Status status) {
                // TODO: Handle the error.
                Log.i("SHUBHAM", "An error occurred: " + status);
                s_source = "";
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
        // txtLat.setText("LatLong:" + location.getLatitude() + "," + location.getLongitude());
        locationtx.setText("Current Location: "+city);
        String currentlocation =city.toString();

    }
*/

 /*   @Override
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