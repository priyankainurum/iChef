package com.ichef.android.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ichef.android.R;

public class NiceToMeetYou extends AppCompatActivity {
    TextView llcontinue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nice_to_meet_you);
        init();
        onclick();

    }

    private void onclick() {
        llcontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(NiceToMeetYou.this, R.anim.image_click));
                Intent intent = new Intent(NiceToMeetYou.this, LocationManually.class);
                startActivity(intent);

            }
        });

    }

    private void init() {
        llcontinue= findViewById(R.id.llmanuallocation);
    }
}
