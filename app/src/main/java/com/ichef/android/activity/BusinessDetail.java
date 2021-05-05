package com.ichef.android.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ichef.android.R;

public class BusinessDetail extends AppCompatActivity {
    RelativeLayout llcontinue;
    RadioButton rd1,rd2,rd3,rd4;
    TextView sun,mon,tue,wed,thu,fri,sat;
    String ssun="0",smon="0",stue="0",swed="0",sthu="0",sfri="1",ssat="1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_detail);
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
        sun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ssun.equals("1")){
                    sun.setBackgroundResource(R.drawable.circle);
                    ssun="0";
                }
                else{
                    sun.setBackgroundResource(R.drawable.circle2);
                    ssun="1";}
            }
        });
        mon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (smon.equals("1")){
                    mon.setBackgroundResource(R.drawable.circle);
                    smon="0";
                }
                else{
                    mon.setBackgroundResource(R.drawable.circle2);
                    smon="1";}
            }
        });
        tue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stue.equals("1")){
                    tue.setBackgroundResource(R.drawable.circle);
                    stue="0";
                }
                else{
                    tue.setBackgroundResource(R.drawable.circle2);
                    stue="1";}
            }
        });
        wed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (swed.equals("1")){
                    wed.setBackgroundResource(R.drawable.circle);
                    swed="0";
                }
                else{
                    wed.setBackgroundResource(R.drawable.circle2);
                    swed="1";}
            }
        });
        thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sthu.equals("1")){
                    thu.setBackgroundResource(R.drawable.circle);
                    sthu="0";
                }
                else{
                    thu.setBackgroundResource(R.drawable.circle2);
                    sthu="1";}
            }
        });
        fri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sfri.equals("1")){
                    fri.setBackgroundResource(R.drawable.circle);
                    sfri="0";
                }
                else{
                    fri.setBackgroundResource(R.drawable.circle2);
                    sfri="1";}
            }
        });
        sat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ssat.equals("1")){
                    sat.setBackgroundResource(R.drawable.circle);
                    ssat="0";
                }
                else{
                    sat.setBackgroundResource(R.drawable.circle2);
                    ssat="1";}
            }
        });

        llcontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(BusinessDetail.this, R.anim.image_click));
                Intent intent = new Intent(BusinessDetail.this, CertificateOperation.class);
                startActivity(intent);

            }
        });
        rd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rd1.setChecked(true);
                rd2.setChecked(false);
                rd3.setChecked(false);
                rd4.setChecked(false);
            }
        });
        rd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rd1.setChecked(false);
                rd2.setChecked(true);
                rd3.setChecked(false);
                rd4.setChecked(false);
            }
        });
        rd3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rd1.setChecked(false);
                rd2.setChecked(false);
                rd3.setChecked(true);
                rd4.setChecked(false);
            }
        });
        rd4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rd1.setChecked(false);
                rd2.setChecked(false);
                rd3.setChecked(false);
                rd4.setChecked(true);
            }
        });



    }

    private void init() {
        rd1 = findViewById(R.id.rd1);
        rd2 = findViewById(R.id.rd2);
        rd3 = findViewById(R.id.rd3);
        rd4 = findViewById(R.id.rd4);

        sun = findViewById(R.id.sun);
        mon = findViewById(R.id.mon);
        tue = findViewById(R.id.tue);
        wed = findViewById(R.id.wed);
        thu = findViewById(R.id.thu);
        fri = findViewById(R.id.fri);
        sat = findViewById(R.id.sat);

        llcontinue= findViewById(R.id.llcontinuebd);
    }
}