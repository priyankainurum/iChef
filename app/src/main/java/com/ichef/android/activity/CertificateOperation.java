package com.ichef.android.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ichef.android.R;

public class CertificateOperation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certificate_operation);
        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView skip = findViewById(R.id.skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(CertificateOperation.this, R.anim.image_click));
                Intent intent = new Intent(CertificateOperation.this, AlmostDone.class);
                startActivity(intent);
            }
        });
        RelativeLayout next = findViewById(R.id.llcontinueco);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(CertificateOperation.this, R.anim.image_click));
                Intent intent = new Intent(CertificateOperation.this, AlmostDone.class);
                startActivity(intent);
            }
        });
    }
}