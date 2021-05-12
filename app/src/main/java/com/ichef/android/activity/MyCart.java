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
import com.ichef.android.responsemodel.fetchcart.FetchCartResponse;
import com.ichef.android.responsemodel.fetchcart.Result;
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
import retrofit2.http.Header;

public class MyCart extends AppCompatActivity {
    APIInterface apiInterface;
    String token;
    Spinner spinner;
    RecyclerView rv_MyProjectList;
    MyCartAdapter rv_MyProjectAdapter;
    TransparentProgressDialog dialog;
    RecyclerView.LayoutManager rv_MyProjectLayoutManager;
    List<com.ichef.android.responsemodel.fetchcart.Result> mListData = new ArrayList<>();
    TextView ten,twenty,thirty,tip;
    RelativeLayout checkout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);
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
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MyCart.this, Checkout.class);
                startActivity(intent);
            }
        });


    }

    private void init() {
        checkout=findViewById(R.id.checkout);

        rv_MyProjectList = findViewById(R.id.rvcart);
        rv_MyProjectList.setHasFixedSize(true);
        rv_MyProjectLayoutManager = new LinearLayoutManager(MyCart.this);
        rv_MyProjectList.setLayoutManager(rv_MyProjectLayoutManager);
        getlist();
        dialog=new TransparentProgressDialog(MyCart.this);
        dialog.show();
    }

    private void getlist() {

        token= Prefrence.get(MyCart.this, Prefrence.KEY_TOKEN);
        APIInterface apiInterface = ApiClient.getClient().create(APIInterface.class);
        Call<FetchCartResponse> call = apiInterface.Getcart("Bearer " + token);
        call.enqueue(new Callback<FetchCartResponse>() {
            @Override
            public void onResponse(Call<FetchCartResponse> call, Response<FetchCartResponse> response)
            {
                dialog.dismiss();
                Toast.makeText(MyCart.this, "Hello PSR - "+response, Toast.LENGTH_SHORT).show();
                if (response.body().getStatus()) {
                  //  dialog.dismiss();
                    mListData = response.body().getResult();
                    setProduct();
                } else {
                   // dialog.dismiss();
                    Toast.makeText(MyCart.this, "No Data Get", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FetchCartResponse> call, Throwable t) {

                dialog.dismiss();
                call.cancel();
            }
        });
    }

    private void setProduct() {
        if (mListData != null && mListData.size() > 0) {
            rv_MyProjectAdapter = new MyCartAdapter(MyCart.this, (ArrayList<Result>) mListData);
            rv_MyProjectList.setAdapter(rv_MyProjectAdapter);
        }

    }

}