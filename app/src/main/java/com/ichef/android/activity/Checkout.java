package com.ichef.android.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ichef.android.R;
import com.ichef.android.adapter.MyCartAdapter;
import com.ichef.android.responsemodel.homefood.DriverListResponse;
import com.ichef.android.responsemodel.homefood.Result;
import com.ichef.android.retrofit.APIInterface;
import com.ichef.android.retrofit.ApiClient;
import com.ichef.android.utils.Prefrence;
import com.ichef.android.utils.TransparentProgressDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Checkout extends AppCompatActivity {
    APIInterface apiInterface;
    String username;
    Spinner spinner;
    RecyclerView rv_MyProjectList;
    MyCartAdapter rv_MyProjectAdapter;
    TransparentProgressDialog dialog;
    RecyclerView.LayoutManager rv_MyProjectLayoutManager;
    List<Result> mListData = new ArrayList<>();
    TextView ten,twenty,thirty,tip;
    RelativeLayout checkout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        init();
        onlclick();
    }

    private void onlclick() {
        ten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(Checkout.this, R.anim.image_click));
                tip.setText("10.00");
            }
        });
        twenty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(Checkout.this, R.anim.image_click));
                tip.setText("20.00");
            }
        });

        thirty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(Checkout.this, R.anim.image_click));
                tip.setText("30.00");
            }
        });
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Checkout.this, HomePageActivity.class);
                startActivity(intent);
            }
        });



    }

    private void init() {
        ten=findViewById(R.id.ten);
        twenty=findViewById(R.id.twenty);
        thirty=findViewById(R.id.thirty);
        tip=findViewById(R.id.tip);
       checkout=findViewById(R.id.rlcontinue);

    }

}