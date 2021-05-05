package com.ichef.android.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ichef.android.R;
import com.ichef.android.adapter.HomeFoodAdapter;
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

public class Bookmark extends AppCompatActivity {
    APIInterface apiInterface;
    String username;
    TextView spinner;
    RecyclerView rv_MyProjectList;
    HomeFoodAdapter rv_MyProjectAdapter;
    TransparentProgressDialog dialog;
    RecyclerView.LayoutManager rv_MyProjectLayoutManager;
    List<Result> mListData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);
        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        init();
    }

    private void init() {
       /* spinner = findViewById(R.id.spinnerres);
        spinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bookmark.this, MyRestaurant.class);
                startActivity(intent);
            }
        });*/

        rv_MyProjectList = findViewById(R.id.rvlist);
        rv_MyProjectList.setHasFixedSize(true);
        rv_MyProjectLayoutManager = new LinearLayoutManager(Bookmark.this);
        rv_MyProjectList.setLayoutManager(rv_MyProjectLayoutManager);
        getlist();
        dialog=new TransparentProgressDialog(Bookmark.this);
        dialog.show();    }

    private void getlist() {
        username= Prefrence.get(Bookmark.this, Prefrence.KEY_MANAGER_ID);
        Map<String, String> map = new HashMap<>();
        map.put("manager_id", "48");

        apiInterface = ApiClient.getClient().create(APIInterface.class);
        Call<DriverListResponse> call = apiInterface.getdriverlist(map);
        call.enqueue(new Callback<DriverListResponse>() {
            @Override
            public void onResponse(Call<DriverListResponse> call, Response<DriverListResponse> response)
            {
                if (response.body().getStatus()) {
                    dialog.dismiss();
                    mListData = response.body().getResult();
                    setProduct();
                } else {
                    dialog.dismiss();
                    Toast.makeText(Bookmark.this, "No Data Get", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DriverListResponse> call, Throwable t) {

                dialog.dismiss();
                call.cancel();
            }
        });
    }

    private void setProduct() {
        if (mListData != null && mListData.size() > 0) {
            rv_MyProjectAdapter = new HomeFoodAdapter(Bookmark.this, (ArrayList<Result>) mListData);
            rv_MyProjectList.setAdapter(rv_MyProjectAdapter);
        }

    }

}