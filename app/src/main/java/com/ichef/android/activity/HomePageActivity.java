package com.ichef.android.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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
import com.ichef.android.utils.Prefrence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomePageActivity extends AppCompatActivity
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
    public static TextView headerName,headerLastName,headerMobno;
    public static ImageView profilepic;
    ImageView nav_btn,profile;
    int usertyp;
    String userid,firstname,lastname,email,password,mobile,title,msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page2);

      //  userid= Prefrence.get(HomePageActivity.this,Prefrence.APP_USER_ID);

        // get the listview
      /*  expListView = (ExpandableListView) findViewById(R.id.lvExp);
        // preparing list data
        prepareListData();

        listAdapter = new MyExpandableListAdapter(this, listDataHeader, listDataChild);
        // setting list adapter
        expListView.setAdapter(listAdapter);*/
        init();
        onclick();

       /* final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                cartCount();
               // Toast.makeText(HomePage.this, "handler ", Toast.LENGTH_SHORT).show();
                handler.postDelayed(this,30);//60 second delay
            }
        };
       handler.postDelayed(runnable,30);*/

    }
    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        // Adding child data
        //listDataHeader.add("Home");
        listDataHeader.add("Content");
        listDataHeader.add("Manage Product");
        listDataHeader.add("Manage Order");
        //  listDataHeader.add("Help & Support");
        listDataHeader.add("Setting");
        //   listDataHeader.add("Share");
        // listDataHeader.add("Logout");


        // Adding child data

      /*  List<String> Home = new ArrayList<String>();
        Home.add("HomePage");*/

        List<String> Content = new ArrayList<String>();
        Content.add("HomePage");
        Content.add("My Profile");
        Content.add("Help & Support");
        Content.add("Share");
        Content.add("Logout");

        //listDataChild.put(listDataHeader.get(0), Home); // Header, Child data
        listDataChild.put(listDataHeader.get(0), Content);

    }

    private void init() {
      //  drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        profile=(ImageView) findViewById(R.id.profile);
        nav_btn = (ImageView) findViewById(R.id.nav_btn);


       /* navigationView = (NavigationView) findViewById(R.id.nav_view);
        headerView = navigationView.getHeaderView(0);
        headerName = (TextView) headerView.findViewById(R.id.firstname);
        headerLastName = (TextView) headerView.findViewById(R.id.lastname);
        headerMobno = (TextView) headerView.findViewById(R.id.profilecontact);

        headerName.setText(firstname);
        headerLastName.setText(lastname);
        headerMobno.setText(mobile);*/
        //  Toast.makeText(this, ""+userid, Toast.LENGTH_SHORT).show();

        Fragment fragment=null;
        Intent in = getIntent();
        String str = "";
        fragment = new HomeFragment();

       /* Intent intent = getIntent();
        String notification= intent.getStringExtra("messagenotification");
        String messageBody= intent.getStringExtra("messageBody");
        String is_from_notification= intent.getStringExtra("is_from_notification");
        Toast.makeText(this, "no"+notification, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "is"+is_from_notification, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "messageBody"+messageBody, Toast.LENGTH_SHORT).show();*/

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

/*
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
               */
/* Toast.makeText(getApplicationContext(),listDataHeader.get(groupPosition) + " : "
                    + listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition), Toast.LENGTH_SHORT)
                        .show();*//*


                String i = listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);


                Fragment fragment = null;
                Intent intent = null;

                if (i == "HomePage") {
                    fragment = new HomeFragment(); //Homefragment
                } else if (i == "My Profile") {
                    fragment = new HomeFragment();
                } else if (i == "Help & Support") {
                    intent = new Intent(HomePageActivity.this, MainActivity.class);
                    // startActivity(intent);
                } else if (i == "Share") {
                    intent = new Intent(HomePageActivity.this, MainActivity.class);
                } else if (i == "Manage Product") {
                    fragment = new HomeFragment();
                } else if (i == "Add New product") {
                    intent = new Intent(HomePageActivity.this, MainActivity.class);
                } else if (i == "Add Brand") {
                    intent = new Intent(HomePageActivity.this, MainActivity.class);
                } else if (i == "Add Sub-Category") {
                    intent = new Intent(HomePageActivity.this, MainActivity.class);
                } else if (i == "Received Order") {
                    intent = new Intent(HomePageActivity.this, MainActivity.class);}
                 else if (i == "Logout") {
                    {

                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(HomePageActivity.this);
                        alertDialog.setMessage("Are you sure you want to Logout?");
                        alertDialog.setIcon(R.drawable.logout);
                        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {


                                Prefrence.save(getApplication(), Prefrence.APP_PREF, "");

                                Intent in = new Intent(HomePageActivity.this, MainActivity.class);
                                startActivity(in);
                                finish();
                                Toast.makeText(getApplicationContext(), "Logout", Toast.LENGTH_SHORT).show();
                            }
                        });

                        // Setting Negative "NO" Button
                        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Write your code here to invoke NO event
                                dialog.dismiss();
                                // Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                            }
                        });
                        // Showing Alert Message
                        alertDialog.show();
                    }
                }
                if (intent != null) {
                    startActivity(intent);
                }
                if (fragment != null) {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    // ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
                    ft.addToBackStack(getClass().getName());
                    ft.replace(R.id.frame, fragment);
                    ft.detach(fragment);
                    ft.attach(fragment);
                    ft.commit();
                }
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
*/

/*
        nav_btn.setOnClickListener(v -> {
            drawer.openDrawer(GravityCompat.START);
        });
*/
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
        return true;
    }
*/

/*
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.frame);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
*/

}
