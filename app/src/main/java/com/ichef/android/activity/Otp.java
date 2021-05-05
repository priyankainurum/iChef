package com.ichef.android.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ichef.android.R;

public class Otp extends AppCompatActivity {
    ImageView back;
    RelativeLayout go;
    TextView time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        init();
        onlclick();
        downTimer();


    }

    private void onlclick() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(Otp.this, R.anim.image_click));

                Intent intent = new Intent(Otp.this, PersonalDetail.class);
                startActivity(intent);
            }
        });

    }

    private void init() {
        back = findViewById(R.id.back);
        go = findViewById(R.id.checkotp);
        time=findViewById(R.id.timer30);

    }
    private void downTimer(){
        new CountDownTimer(30*1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long second = (millisUntilFinished / 1000) % 60;
                long minutes = (millisUntilFinished/(1000*60)) % 60;
                time.setText(minutes + ":" + second);
            }

            @Override
            public void onFinish() {
                time.setText("Time Out");
            }
        }.start();
    }

}