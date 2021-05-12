package com.ichef.android.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ichef.android.R;
import com.ichef.android.adapter.CompletedOrderAdapter;
import com.ichef.android.adapter.HomeFoodAdapter;
import com.ichef.android.adapter.MyRestoAdapter;
import com.ichef.android.adapter.NotificationAdapter;
import com.ichef.android.adapter.OrdersAdapter;
import com.ichef.android.responsemodel.homefood.DriverListResponse;
import com.ichef.android.responsemodel.homefood.Result;
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

public class MyRestaurant extends AppCompatActivity {
    APIInterface apiInterface;
    String username;
    Spinner spinner;
    RecyclerView rv_MyProjectList,rv_MyProjectList2,rv_MyProjectList3;
    MyRestoAdapter rv_MyProjectAdapter;
    NotificationAdapter rv_MyProjectAdapter2;
    OrdersAdapter rv_MyProjectAdapter3;
    TransparentProgressDialog dialog;
    RecyclerView.LayoutManager rv_MyProjectLayoutManager;
    RecyclerView.LayoutManager rv_MyProjectLayoutManager2;
    RecyclerView.LayoutManager rv_MyProjectLayoutManager3;
    List<Result> mListData = new ArrayList<>();
    List<Result> mListData2 = new ArrayList<>();
    List<Result> mListData3 = new ArrayList<>();
    LinearLayout lltab,llmyitem,llsetting,llorder,llpayment,llnotification;
    LinearLayout lltxview,lltxmanage,lltxadd;
    TextView txtadd ,txtmanage ,txtview;
    LinearLayout llactiveorder;
    LinearLayout managelayout,addlayout;
    ImageView immyitem,imorder,imnotification,imsetting,impayment;
    TextView txtmyitem,txtorder,txtnotification,txtsetting,txtpayment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_restaurant);
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
        llnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(MyRestaurant.this, R.anim.image_click));
                llnotification.setBackgroundResource(R.color.themered);
                llsetting.setBackgroundResource(R.color.white);
                llorder.setBackgroundResource(R.color.white);
                llmyitem.setBackgroundResource(R.color.white);
                llpayment.setBackgroundResource(R.color.white);

                rv_MyProjectList.setVisibility(View.GONE);
                rv_MyProjectList2.setVisibility(View.VISIBLE);
                lltab.setVisibility(View.GONE);
                llactiveorder.setVisibility(View.GONE);

                imnotification.setImageResource(R.drawable.notificationwhite);
                immyitem.setImageResource(R.drawable.myitemgrey);
                imorder.setImageResource(R.drawable.ordergrey);
                impayment.setImageResource(R.drawable.payementgrey);
                imsetting.setImageResource(R.drawable.settinggrey);
                txtnotification.setTextColor(Color.parseColor("#FFFFFFFF"));
                txtmyitem.setTextColor(Color.parseColor("#FF000000"));
                txtorder.setTextColor(Color.parseColor("#FF000000"));
                txtpayment.setTextColor(Color.parseColor("#FF000000"));
                txtsetting.setTextColor(Color.parseColor("#FF000000"));
            }
        });
        llsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(MyRestaurant.this, R.anim.image_click));
                llnotification.setBackgroundResource(R.color.white);
                llsetting.setBackgroundResource(R.color.themered);
                llorder.setBackgroundResource(R.color.white);
                llmyitem.setBackgroundResource(R.color.white);
                llpayment.setBackgroundResource(R.color.white);

                rv_MyProjectList.setVisibility(View.GONE);
                rv_MyProjectList2.setVisibility(View.VISIBLE);
                lltab.setVisibility(View.GONE);
                llactiveorder.setVisibility(View.GONE);
              //  Toast.makeText(MyRestaurant.this, "In Progress", Toast.LENGTH_SHORT).show();

                imnotification.setImageResource(R.drawable.notificationgrey);
                immyitem.setImageResource(R.drawable.myitemgrey);
                imorder.setImageResource(R.drawable.ordergrey);
                impayment.setImageResource(R.drawable.payementgrey);
                imsetting.setImageResource(R.drawable.settingwhite);
                txtnotification.setTextColor(Color.parseColor("#FF000000"));
                txtmyitem.setTextColor(Color.parseColor("#FF000000"));
                txtorder.setTextColor(Color.parseColor("#FF000000"));
                txtpayment.setTextColor(Color.parseColor("#FF000000"));
                txtsetting.setTextColor(Color.parseColor("#FFFFFFFF"));

            }
        });
        llorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(MyRestaurant.this, R.anim.image_click));
                llnotification.setBackgroundResource(R.color.white);
                llsetting.setBackgroundResource(R.color.white);
                llorder.setBackgroundResource(R.color.themered);
                llmyitem.setBackgroundResource(R.color.white);
                llpayment.setBackgroundResource(R.color.white);

                rv_MyProjectList.setVisibility(View.GONE);
                rv_MyProjectList2.setVisibility(View.GONE);
                llactiveorder.setVisibility(View.VISIBLE);
                lltab.setVisibility(View.GONE);
              //  Toast.makeText(MyRestaurant.this, "In Progress", Toast.LENGTH_SHORT).show();

                imnotification.setImageResource(R.drawable.notificationgrey);
                immyitem.setImageResource(R.drawable.myitemgrey);
                imorder.setImageResource(R.drawable.orderwhite);
                impayment.setImageResource(R.drawable.payementgrey);
                imsetting.setImageResource(R.drawable.settinggrey);
                txtnotification.setTextColor(Color.parseColor("#FF000000"));
                txtmyitem.setTextColor(Color.parseColor("#FF000000"));
                txtorder.setTextColor(Color.parseColor("#FFFFFFFF"));
                txtpayment.setTextColor(Color.parseColor("#FF000000"));
                txtsetting.setTextColor(Color.parseColor("#FF000000"));

            }
        });
        llpayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(MyRestaurant.this, R.anim.image_click));
                llnotification.setBackgroundResource(R.color.white);
                llsetting.setBackgroundResource(R.color.white);
                llorder.setBackgroundResource(R.color.white);
                llmyitem.setBackgroundResource(R.color.white);
                llpayment.setBackgroundResource(R.color.themered);

                rv_MyProjectList.setVisibility(View.GONE);
                rv_MyProjectList2.setVisibility(View.VISIBLE);
                lltab.setVisibility(View.GONE);
                llactiveorder.setVisibility(View.GONE);
              //  Toast.makeText(MyRestaurant.this, "In Progress", Toast.LENGTH_SHORT).show();

                imnotification.setImageResource(R.drawable.notificationgrey);
                immyitem.setImageResource(R.drawable.myitemgrey);
                imorder.setImageResource(R.drawable.ordergrey);
                impayment.setImageResource(R.drawable.paymentwhite);
                imsetting.setImageResource(R.drawable.settinggrey);
                txtnotification.setTextColor(Color.parseColor("#FF000000"));
                txtmyitem.setTextColor(Color.parseColor("#FF000000"));
                txtorder.setTextColor(Color.parseColor("#FF000000"));
                txtpayment.setTextColor(Color.parseColor("#FFFFFFFF"));
                txtsetting.setTextColor(Color.parseColor("#FF000000"));

            }
        });



        llmyitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(MyRestaurant.this, R.anim.image_click));
                llnotification.setBackgroundResource(R.color.white);
                llsetting.setBackgroundResource(R.color.white);
                llorder.setBackgroundResource(R.color.white);
                llmyitem.setBackgroundResource(R.color.themered);
                llpayment.setBackgroundResource(R.color.white);

                rv_MyProjectList.setVisibility(View.VISIBLE);
                rv_MyProjectList2.setVisibility(View.GONE);
                lltab.setVisibility(View.VISIBLE);
                llactiveorder.setVisibility(View.GONE);

                imnotification.setImageResource(R.drawable.notificationgrey);
                immyitem.setImageResource(R.drawable.myitemwhite);
                imorder.setImageResource(R.drawable.ordergrey);
                impayment.setImageResource(R.drawable.payementgrey);
                imsetting.setImageResource(R.drawable.settinggrey);
                txtnotification.setTextColor(Color.parseColor("#FF000000"));
                txtmyitem.setTextColor(Color.parseColor("#FFFFFFFF"));
                txtorder.setTextColor(Color.parseColor("#FF000000"));
                txtpayment.setTextColor(Color.parseColor("#FF000000"));
                txtsetting.setTextColor(Color.parseColor("#FF000000"));

            }
        });
        lltxview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(MyRestaurant.this, R.anim.image_click));
             /*   rv_MyProjectList.setVisibility(View.VISIBLE);
                rv_MyProjectList2.setVisibility(View.GONE);
                lltab.setVisibility(View.VISIBLE);*/
                txtadd.setTextColor(Color.parseColor("#FF000000"));
                txtmanage.setTextColor(Color.parseColor("#FF000000"));
                txtview.setTextColor(Color.parseColor("#c10b28"));

                rv_MyProjectList.setVisibility(View.VISIBLE);
                managelayout.setVisibility(View.GONE);
                addlayout.setVisibility(View.GONE);
                llactiveorder.setVisibility(View.GONE);



            }
        });
        lltxmanage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(MyRestaurant.this, R.anim.image_click));
             /*   rv_MyProjectList.setVisibility(View.VISIBLE);
                rv_MyProjectList2.setVisibility(View.GONE);
                lltab.setVisibility(View.VISIBLE);*/
                txtadd.setTextColor(Color.parseColor("#FF000000"));
                txtmanage.setTextColor(Color.parseColor("#c10b28"));
                txtview.setTextColor(Color.parseColor("#FF000000"));

                rv_MyProjectList.setVisibility(View.GONE);
                managelayout.setVisibility(View.VISIBLE);
                addlayout.setVisibility(View.GONE);
                llactiveorder.setVisibility(View.GONE);


            }
        });
        lltxadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(MyRestaurant.this, R.anim.image_click));
             /*   rv_MyProjectList.setVisibility(View.VISIBLE);
                rv_MyProjectList2.setVisibility(View.GONE);
                lltab.setVisibility(View.VISIBLE);*/
                txtadd.setTextColor(Color.parseColor("#c10b28"));
                txtmanage.setTextColor(Color.parseColor("#FF000000"));
                txtview.setTextColor(Color.parseColor("#FF000000"));

                rv_MyProjectList.setVisibility(View.GONE);
                managelayout.setVisibility(View.GONE);
                addlayout.setVisibility(View.VISIBLE);
                llactiveorder.setVisibility(View.GONE);



            }
        });
        addlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(MyRestaurant.this, R.anim.image_click));
                Intent intent = new Intent(MyRestaurant.this, AddNewItem.class);
                startActivity(intent);
            }
        });




    }

    private void init() {
      //  ImageView immyitem,imorder,imnotification,imsetting,impayment;
      //  TextView txtmyitem,txtorder,txtnotification,txtsetting,txtpayment;

        immyitem =findViewById(R.id.immyitem);
        imorder =findViewById(R.id.imorders);
        imnotification =findViewById(R.id.imnotification);
        imsetting =findViewById(R.id.imsetting);
        impayment =findViewById(R.id.impayment);


        txtmyitem =findViewById(R.id.txtmyitem);
        txtorder =findViewById(R.id.txtorder);
        txtnotification =findViewById(R.id.txtnotification);
        txtsetting =findViewById(R.id.txtsetting);
        txtpayment =findViewById(R.id.txtpayment);

        CalendarView simpleCalendarView = (CalendarView) findViewById(R.id.simpleCalendarView); // get the reference of CalendarView
        long selectedDate = simpleCalendarView.getDate();

        txtview =findViewById(R.id.txtview);
        txtmanage =findViewById(R.id.txtmanage);
        txtadd =findViewById(R.id.txtadd);


        /**/
        lltxview =findViewById(R.id.lltxviewitem);
        lltxmanage =findViewById(R.id.lltxmanageitem);
        lltxadd =findViewById(R.id.lltxadditem);
        llactiveorder =findViewById(R.id.llactiveorder);
        /**/

        managelayout =findViewById(R.id.managelayout);
        addlayout =findViewById(R.id.addlayout);

        lltab =findViewById(R.id.llmyitemtab);


        llmyitem =findViewById(R.id.llmyitem);
        llorder =findViewById(R.id.llorders);
        llnotification =findViewById(R.id.llnotification);
        llsetting =findViewById(R.id.llsettings);
        llpayment =findViewById(R.id.llpayments);

        /*myitem*/
        rv_MyProjectList = findViewById(R.id.rvfood);
        rv_MyProjectList.setHasFixedSize(true);
        rv_MyProjectLayoutManager = new LinearLayoutManager(MyRestaurant.this);
        rv_MyProjectList.setLayoutManager(rv_MyProjectLayoutManager);
        getlist();
        dialog=new TransparentProgressDialog(MyRestaurant.this);
        dialog.show();

        /*notification*/

        rv_MyProjectList2 = findViewById(R.id.rvnotification);
        rv_MyProjectList2.setHasFixedSize(true);
        rv_MyProjectLayoutManager2 = new LinearLayoutManager(MyRestaurant.this);
        rv_MyProjectList2.setLayoutManager(rv_MyProjectLayoutManager2);
        getnotificationlist();

        /*orders*/
        rv_MyProjectList3 = findViewById(R.id.rvorders);
        rv_MyProjectList3.setHasFixedSize(true);
        rv_MyProjectLayoutManager3 = new LinearLayoutManager(MyRestaurant.this);
        rv_MyProjectList3.setLayoutManager(rv_MyProjectLayoutManager3);
        getOrders();

    }
    private void getlist() {
        username= Prefrence.get(MyRestaurant.this, Prefrence.KEY_MANAGER_ID);
        Map<String, String> map = new HashMap<>();
        map.put("manager_id", "48");

        apiInterface = ApiClientTest.getClient().create(APIInterface.class);
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
                    Toast.makeText(MyRestaurant.this, "No Data Get", Toast.LENGTH_SHORT).show();
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
            rv_MyProjectAdapter = new MyRestoAdapter(MyRestaurant.this, (ArrayList<Result>) mListData);
            rv_MyProjectList.setAdapter(rv_MyProjectAdapter);
        }

    }

    /**/
    private void getnotificationlist() {
        username= Prefrence.get(MyRestaurant.this, Prefrence.KEY_MANAGER_ID);
        Map<String, String> map = new HashMap<>();
        map.put("manager_id", "48");

        apiInterface = ApiClientTest.getClient().create(APIInterface.class);
        Call<DriverListResponse> call = apiInterface.getdriverlist(map);
        call.enqueue(new Callback<DriverListResponse>() {
            @Override
            public void onResponse(Call<DriverListResponse> call, Response<DriverListResponse> response)
            {
                if (response.body().getStatus()) {
                    dialog.dismiss();
                    mListData2 = response.body().getResult();
                    setProduct2();
                } else {
                   // dialog.dismiss();
                    Toast.makeText(MyRestaurant.this, "No Data Get", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DriverListResponse> call, Throwable t) {

               // dialog.dismiss();
                call.cancel();
            }
        });
    }
    private void setProduct2() {
        if (mListData2 != null && mListData2.size() > 0) {
            rv_MyProjectAdapter2 = new NotificationAdapter(MyRestaurant.this, (ArrayList<Result>) mListData2);
            rv_MyProjectList2.setAdapter(rv_MyProjectAdapter2);
        }

    }

    /**/
    /**/
    private void getOrders() {
        username= Prefrence.get(MyRestaurant.this, Prefrence.KEY_MANAGER_ID);
        Map<String, String> map = new HashMap<>();
        map.put("manager_id", "48");

        apiInterface = ApiClientTest.getClient().create(APIInterface.class);
        Call<DriverListResponse> call = apiInterface.getdriverlist(map);
        call.enqueue(new Callback<DriverListResponse>() {
            @Override
            public void onResponse(Call<DriverListResponse> call, Response<DriverListResponse> response)
            {
                if (response.body().getStatus()) {
                    dialog.dismiss();
                    mListData3 = response.body().getResult();
                    setProduct3();
                } else {
                    // dialog.dismiss();
                    Toast.makeText(MyRestaurant.this, "No Data Get", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DriverListResponse> call, Throwable t) {

                // dialog.dismiss();
                call.cancel();
            }
        });
    }
    private void setProduct3() {
        if (mListData3 != null && mListData3.size() > 0) {
            rv_MyProjectAdapter3 = new OrdersAdapter(MyRestaurant.this, (ArrayList<Result>) mListData3);
            rv_MyProjectList3.setAdapter(rv_MyProjectAdapter3);
        }

    }
}