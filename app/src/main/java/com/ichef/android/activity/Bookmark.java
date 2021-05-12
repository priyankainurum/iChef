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
import com.ichef.android.adapter.BookmarkAdapter;
import com.ichef.android.adapter.HomeFoodAdapter;
import com.ichef.android.responsemodel.bookmarklist.GetBookmarkListResponse;
import com.ichef.android.responsemodel.bookmarklist.Result;
import com.ichef.android.responsemodel.fetchcart.FetchCartResponse;
import com.ichef.android.responsemodel.homefood.DriverListResponse;
import com.ichef.android.retrofit.APIInterface;
import com.ichef.android.retrofit.ApiClient;
import com.ichef.android.retrofit.ApiClientTest;
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
    String token;
    TextView spinner;
    RecyclerView rv_MyProjectList;
    BookmarkAdapter rv_MyProjectAdapter;
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

        token= Prefrence.get(Bookmark.this, Prefrence.KEY_TOKEN);
        APIInterface apiInterface = ApiClient.getClient().create(APIInterface.class);
        Call<GetBookmarkListResponse> call = apiInterface.Getbookmark("Bearer " + token);
        call.enqueue(new Callback<GetBookmarkListResponse>() {
            @Override
            public void onResponse(Call<GetBookmarkListResponse> call, Response<GetBookmarkListResponse> response)
            {
                dialog.dismiss();
                Toast.makeText(Bookmark.this, "Hello PSR - "+response, Toast.LENGTH_SHORT).show();
                if (response.body().getStatus()) {
                    //  dialog.dismiss();
                    mListData = response.body().getResult();
                    setProduct();
                } else {
                    // dialog.dismiss();
                    Toast.makeText(Bookmark.this, "No Data Get", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetBookmarkListResponse> call, Throwable t) {

                dialog.dismiss();
                call.cancel();
            }
        });
    }

    private void setProduct() {
        if (mListData != null && mListData.size() > 0) {
            rv_MyProjectAdapter = new BookmarkAdapter(Bookmark.this, (ArrayList<Result>) mListData);
            rv_MyProjectList.setAdapter(rv_MyProjectAdapter);
        }

    }

}