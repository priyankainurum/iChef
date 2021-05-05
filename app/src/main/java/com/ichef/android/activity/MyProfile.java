package com.ichef.android.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ichef.android.R;
import com.ichef.android.utils.Prefrence;
import com.ichef.android.utils.TransparentProgressDialog;

public class MyProfile extends AppCompatActivity {

    TextView logout;
    LinearLayout yourorder,favorder,myaddress,llorders;
    LinearLayout bookmark,notification,settings,payment,mycart;
    TextView runningorder,completedorder,cancelledorder,myreward,serviceprovider;
    ImageView imbookmark,imnotification,imsetting,impayment;
    TextView txtbookmark,txtnotification,txtsetting,txtpayment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        init();
        onclick();
    }

    private void onclick() {
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(MyProfile.this, R.anim.image_click));
                settings.setBackgroundResource(R.color.themered);
                payment.setBackgroundResource(R.color.lightgrey);
                bookmark.setBackgroundResource(R.color.lightgrey);
                notification.setBackgroundResource(R.color.lightgrey);

                /*imsetting.setImageResource(R.drawable.settingwhite);
                imnotification.setImageResource(R.drawable.notificationgrey);
                imbookmark.setImageResource(R.drawable.bookmarks);
                impayment.setImageResource(R.drawable.payementgrey);
                */
               /* txtsetting.setTextColor(Color.parseColor("#FFFFFFFF"));
                txtpayment.setTextColor(Color.parseColor("#FF000000"));
                txtnotification.setTextColor(Color.parseColor("#FF000000"));
                txtbookmark.setTextColor(Color.parseColor("#FF000000"));*/

                Intent intent = new Intent(MyProfile.this, Settings.class);
                startActivity(intent);
            }
        });
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(MyProfile.this, R.anim.image_click));
                settings.setBackgroundResource(R.color.lightgrey);
                payment.setBackgroundResource(R.color.themered);
                bookmark.setBackgroundResource(R.color.lightgrey);
                notification.setBackgroundResource(R.color.lightgrey);
                Intent intent = new Intent(MyProfile.this, Payment.class);
                startActivity(intent);}
        });
        bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(MyProfile.this, R.anim.image_click));
                settings.setBackgroundResource(R.color.lightgrey);
                payment.setBackgroundResource(R.color.lightgrey);
                bookmark.setBackgroundResource(R.color.themered);
                notification.setBackgroundResource(R.color.lightgrey);
                Intent intent = new Intent(MyProfile.this, Bookmark.class);
                startActivity(intent);
            }
        });
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(MyProfile.this, R.anim.image_click));
                settings.setBackgroundResource(R.color.lightgrey);
                payment.setBackgroundResource(R.color.lightgrey);
                bookmark.setBackgroundResource(R.color.lightgrey);
                notification.setBackgroundResource(R.color.themered);
                Intent intent = new Intent(MyProfile.this, Notification.class);
                startActivity(intent);
            }
        });


        yourorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(MyProfile.this, R.anim.image_click));
                llorders.setVisibility(View.VISIBLE);

            }
        });
        favorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(MyProfile.this, R.anim.image_click));
                llorders.setVisibility(View.GONE);
                Intent intent = new Intent(MyProfile.this, FavoritesOrder.class);
                startActivity(intent);
            }
        });

        myaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(MyProfile.this, R.anim.image_click));
                llorders.setVisibility(View.GONE);
                Intent intent = new Intent(MyProfile.this, MyAddress.class);
                startActivity(intent);
            }
        });

        runningorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(MyProfile.this, R.anim.image_click));
                runningorder.setBackgroundResource(R.color.themered);
                completedorder.setBackgroundResource(R.color.white);
                cancelledorder.setBackgroundResource(R.color.white);
                runningorder.setTextColor(Color.parseColor("#FFFFFFFF"));
                completedorder.setTextColor(Color.parseColor("#FF000000"));
                cancelledorder.setTextColor(Color.parseColor("#FF000000"));
                Intent intent = new Intent(MyProfile.this, RunningOrder.class);
                startActivity(intent);
            }
        });
        completedorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(MyProfile.this, R.anim.image_click));
                runningorder.setBackgroundResource(R.color.white);
                completedorder.setBackgroundResource(R.color.themered);
                cancelledorder.setBackgroundResource(R.color.white);
                runningorder.setTextColor(Color.parseColor("#FF000000"));
                completedorder.setTextColor(Color.parseColor("#FFFFFFFF"));
                cancelledorder.setTextColor(Color.parseColor("#FF000000"));
                Intent intent = new Intent(MyProfile.this, CompletedOrder.class);
                startActivity(intent);
            }
        });
        cancelledorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(MyProfile.this, R.anim.image_click));
                runningorder.setBackgroundResource(R.color.white);
                completedorder.setBackgroundResource(R.color.white);
                cancelledorder.setBackgroundResource(R.color.themered);
                runningorder.setTextColor(Color.parseColor("#FF000000"));
                completedorder.setTextColor(Color.parseColor("#FF000000"));
                cancelledorder.setTextColor(Color.parseColor("#FFFFFFFF"));
                Intent intent = new Intent(MyProfile.this, CompletedOrder.class);
                startActivity(intent);
            }
        });

        myreward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(MyProfile.this, R.anim.image_click));
                llorders.setVisibility(View.GONE);
                Intent intent = new Intent(MyProfile.this, MyReward.class);
                startActivity(intent);
            }
        });
        mycart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(MyProfile.this, R.anim.image_click));
                llorders.setVisibility(View.GONE);
                Intent intent = new Intent(MyProfile.this, MyCart.class);
                startActivity(intent);
            }
        });

        serviceprovider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(MyProfile.this, R.anim.image_click));
                llorders.setVisibility(View.GONE);
                Intent intent = new Intent(MyProfile.this, PersonalDetail.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {

                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(MyProfile.this);
                    alertDialog.setMessage("Are you sure you want to Logout?");
                    alertDialog.setIcon(R.drawable.logout);
                    alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Prefrence.save(getApplication(), Prefrence.KEY_USER_ID, "");
                            Prefrence.save(getApplication(), Prefrence.KEY_FIRST_NAME, "");
                            Prefrence.save(getApplication(), Prefrence.KEY_EMAIL_ID, "");
                            Intent in = new Intent(MyProfile.this, Login.class);
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
                            dialog.cancel();
                        }
                    });
                    // Showing Alert Message
                    alertDialog.show();
                }
            }
        });



    }

    private void init() {
        bookmark = findViewById(R.id.bookmark);
        payment = findViewById(R.id.payment);
        notification = findViewById(R.id.notification);
        settings = findViewById(R.id.setting);

        llorders = findViewById(R.id.llorders);
        yourorder = findViewById(R.id.yourorder);
        favorder = findViewById(R.id.favorder);
        myaddress = findViewById(R.id.myaddress);
        runningorder = findViewById(R.id.runningorder);
        completedorder = findViewById(R.id.completedorder);
        cancelledorder = findViewById(R.id.cancelledorder);
        myreward = findViewById(R.id.myreward);
        mycart = findViewById(R.id.mycart);
        serviceprovider = findViewById(R.id.serviceprovider);
        logout=findViewById(R.id.logout);



    }

}