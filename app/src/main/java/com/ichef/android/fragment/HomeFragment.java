package com.ichef.android.fragment;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ichef.android.R;
import com.ichef.android.activity.HomePageActivity;
import com.ichef.android.activity.Login;
import com.ichef.android.activity.MyRestaurant;
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


public class HomeFragment extends Fragment {
    APIInterface apiInterface;
    String username;
    TextView spinner;
    RecyclerView rv_MyProjectList;
    HomeFoodAdapter rv_MyProjectAdapter;
    TransparentProgressDialog dialog;
    RecyclerView.LayoutManager rv_MyProjectLayoutManager;
    List<Result> mListData = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_home, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        spinner = view.findViewById(R.id.spinnerres);
        spinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MyRestaurant.class);
                startActivity(intent);
            }
        });

        rv_MyProjectList = view.findViewById(R.id.rvlist);
        rv_MyProjectList.setHasFixedSize(true);
        rv_MyProjectLayoutManager = new LinearLayoutManager(getContext());
        rv_MyProjectList.setLayoutManager(rv_MyProjectLayoutManager);
        getlist();
        dialog=new TransparentProgressDialog(getContext());
        dialog.show();    }

    private void getlist() {
        username= Prefrence.get(getContext(), Prefrence.KEY_MANAGER_ID);
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
                    Toast.makeText(getContext(), "No Data Get", Toast.LENGTH_SHORT).show();
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
            rv_MyProjectAdapter = new HomeFoodAdapter(getContext(), (ArrayList<Result>) mListData);
            rv_MyProjectList.setAdapter(rv_MyProjectAdapter);
        }

    }

}