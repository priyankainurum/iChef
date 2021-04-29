package com.ichef.android.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ichef.android.MainActivity;
import com.ichef.android.R;

public class PersonalDetail extends AppCompatActivity {

    LinearLayout llcontinue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_detail);
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
        llcontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(PersonalDetail.this, R.anim.image_click));
                Intent intent = new Intent(PersonalDetail.this, NiceToMeetYou.class);
                startActivity(intent);

            }
        });

    }

    private void init() {
        llcontinue= findViewById(R.id.llcontinue);
    }
}