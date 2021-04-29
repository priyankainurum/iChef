package com.ichef.android.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ichef.android.R;
import com.ichef.android.utils.TransparentProgressDialog;

public class MyProfile extends AppCompatActivity {

    LinearLayout yourorder,llorders;
    LinearLayout bookmark,notification,settings,payment,mycart;
    TextView runningorder,completedorder,cancelledorder,myreward,serviceprovider;
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
            }
        });
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(MyProfile.this, R.anim.image_click));
                settings.setBackgroundResource(R.color.lightgrey);
                payment.setBackgroundResource(R.color.themered);
                bookmark.setBackgroundResource(R.color.lightgrey);
                notification.setBackgroundResource(R.color.lightgrey);            }
        });
        bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(MyProfile.this, R.anim.image_click));
                settings.setBackgroundResource(R.color.lightgrey);
                payment.setBackgroundResource(R.color.lightgrey);
                bookmark.setBackgroundResource(R.color.themered);
                notification.setBackgroundResource(R.color.lightgrey);
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
            }
        });


        yourorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(MyProfile.this, R.anim.image_click));

               llorders.setVisibility(View.VISIBLE);

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


    }

    private void init() {
        bookmark = findViewById(R.id.bookmark);
        payment = findViewById(R.id.payment);
        notification = findViewById(R.id.notification);
        settings = findViewById(R.id.setting);

        llorders = findViewById(R.id.llorders);
        yourorder = findViewById(R.id.yourorder);
        runningorder = findViewById(R.id.runningorder);
        completedorder = findViewById(R.id.completedorder);
        cancelledorder = findViewById(R.id.cancelledorder);
        myreward = findViewById(R.id.myreward);
        mycart = findViewById(R.id.mycart);
        serviceprovider = findViewById(R.id.serviceprovider);

    }

}