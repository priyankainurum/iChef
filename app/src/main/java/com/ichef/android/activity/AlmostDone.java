package com.ichef.android.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ichef.android.R;

public class AlmostDone extends AppCompatActivity {
    LinearLayout llcontinue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_almost_done);
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
                v.startAnimation(AnimationUtils.loadAnimation(AlmostDone.this, R.anim.image_click));
                Intent intent = new Intent(AlmostDone.this, MyRestaurant.class);
                startActivity(intent);

            }
        });


    }

    private void init() {
        llcontinue= findViewById(R.id.llcontinue);
    }
}
