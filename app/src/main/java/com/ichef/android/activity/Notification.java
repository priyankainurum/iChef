package com.ichef.android.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ichef.android.R;
import com.ichef.android.adapter.NotificationAdapter;
import com.ichef.android.adapter.NotificationProfileAdapter;
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

public class Notification extends AppCompatActivity {
    APIInterface apiInterface;
    String username;
    RecyclerView rv_MyProjectList;
    NotificationProfileAdapter rv_MyProjectAdapter;
    RecyclerView.LayoutManager rv_MyProjectLayoutManager;
    List<Result> mListData = new ArrayList<>();
    TransparentProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
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
        rv_MyProjectList = findViewById(R.id.rvnotification);
        rv_MyProjectList.setHasFixedSize(true);
        rv_MyProjectLayoutManager = new LinearLayoutManager(Notification.this);
        rv_MyProjectList.setLayoutManager(rv_MyProjectLayoutManager);
        getnotificationlist();
        dialog=new TransparentProgressDialog(Notification.this);
        dialog.show();
    }

    private void getnotificationlist() {
        username= Prefrence.get(Notification.this, Prefrence.KEY_MANAGER_ID);
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
                    // dialog.dismiss();
                    Toast.makeText(Notification.this, "No Data Get", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DriverListResponse> call, Throwable t) {
                // dialog.dismiss();
                call.cancel();
            }
        });
    }
    private void setProduct() {
        if (mListData != null && mListData.size() > 0) {
            rv_MyProjectAdapter = new NotificationProfileAdapter(Notification.this, (ArrayList<Result>) mListData);
            rv_MyProjectList.setAdapter(rv_MyProjectAdapter);
        }
    }
}