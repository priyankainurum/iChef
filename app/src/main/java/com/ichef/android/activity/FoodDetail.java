package com.ichef.android.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ichef.android.R;
import com.ichef.android.adapter.MyCartAdapter;
import com.ichef.android.adapter.ReviewAdapter;
import com.ichef.android.requestmodel.SignupRequest;
import com.ichef.android.requestmodel.addtocartrequest.AddtoCartRequest;
import com.ichef.android.responsemodel.addtocartresponse.AddtoCartResponse;
import com.ichef.android.responsemodel.homefood.DriverListResponse;
import com.ichef.android.responsemodel.homefood.Result;
import com.ichef.android.responsemodel.signup.SignupResponse;
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

public class FoodDetail extends AppCompatActivity {
    APIInterface apiInterface;
    String username,token;
    Spinner spinner;
    RecyclerView rv_MyProjectList;
    ReviewAdapter rv_MyProjectAdapter;
    TransparentProgressDialog dialog;
    RecyclerView.LayoutManager rv_MyProjectLayoutManager;
    List<Result> mListData = new ArrayList<>();
    LinearLayout btndelivery,btndining,btnreview,lldelivery,lldining,llreview;
    ImageView bikeblack,diningblack,reviewblack;
    TextView txbiketx,diningtx,txtreview;
    RelativeLayout buynow,addtocart,dialogmode,dialogtime,dialogoffer,rltiming;

    Button btnClick, btnClose, btnRate;
    RatingBar rBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        init();
        onclick();
        btndelivery.setBackgroundResource(R.color.lightgrey);
        btndining.setBackgroundResource(R.color.themered);
        btnreview.setBackgroundResource(R.color.lightgrey);

        txbiketx.setTextColor(Color.parseColor("#FF000000"));
        diningtx.setTextColor(Color.parseColor("#FFFFFFFF"));
        txtreview.setTextColor(Color.parseColor("#FF000000"));

        bikeblack.setImageResource(R.drawable.deliverybikegrey);
        diningblack.setImageResource(R.drawable.diningwhite);
        reviewblack.setImageResource(R.drawable.review);
    }

    private void onclick() {
        dialogmode.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // add listener to button
                final Dialog dialog = new Dialog(FoodDetail.this);   // Create custom dialog object
                dialog.setContentView(R.layout.dialogmode);       // Include dialog.xml file
                dialog.show();                                         // Set dialog title dialog.setTitle("Custom Dialog");

                //  rBar = dialog.findViewById(R.id.rate_app);
                btnRate = (Button) dialog.findViewById(R.id.btn_submit_rating);
                //Performing action on Button Click
                btnRate.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        //Getting the rating and displaying it in the toast
                        //   String rating = String.valueOf(rBar.getRating());
                        //   Toast.makeText(getApplicationContext(), "You have rated :   " + rating, Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), "You have clicked on OK :   ", Toast.LENGTH_SHORT).show();

                    }

                });

                btnClose = (Button) dialog.findViewById(R.id.btn_remind_later);
                // if the button is clicked, close the custom dialog box
                btnClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Close dialog
                        dialog.dismiss();
                    }
                });
            }
        });
        dialogoffer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // add listener to button
                final Dialog dialog = new Dialog(FoodDetail.this);   // Create custom dialog object
                dialog.setContentView(R.layout.dialogmode);       // Include dialog.xml file
                dialog.show();                                         // Set dialog title dialog.setTitle("Custom Dialog");

                //  rBar = dialog.findViewById(R.id.rate_app);
                btnRate = (Button) dialog.findViewById(R.id.btn_submit_rating);
                //Performing action on Button Click
                btnRate.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        //Getting the rating and displaying it in the toast
                        //   String rating = String.valueOf(rBar.getRating());
                        //   Toast.makeText(getApplicationContext(), "You have rated :   " + rating, Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), "You have clicked on OK :   ", Toast.LENGTH_SHORT).show();

                    }

                });

                btnClose = (Button) dialog.findViewById(R.id.btn_remind_later);
                // if the button is clicked, close the custom dialog box
                btnClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Close dialog
                        dialog.dismiss();
                    }
                });
            }
        });
        rltiming.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // add listener to button
                final Dialog dialog = new Dialog(FoodDetail.this);   // Create custom dialog object
                dialog.setContentView(R.layout.dialogmode);       // Include dialog.xml file
                dialog.show();                                         // Set dialog title dialog.setTitle("Custom Dialog");

              //  rBar = dialog.findViewById(R.id.rate_app);
                btnRate = (Button) dialog.findViewById(R.id.btn_submit_rating);
                //Performing action on Button Click
                btnRate.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        //Getting the rating and displaying it in the toast
                     //   String rating = String.valueOf(rBar.getRating());
                     //   Toast.makeText(getApplicationContext(), "You have rated :   " + rating, Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), "You have clicked on OK :   ", Toast.LENGTH_SHORT).show();
                    }

                });

                btnClose = (Button) dialog.findViewById(R.id.btn_remind_later);
                // if the button is clicked, close the custom dialog box
                btnClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Close dialog
                        dialog.dismiss();
                    }
                });
            }
        });


        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(FoodDetail.this, R.anim.image_click));
             /*   Intent intent = new Intent(FoodDetail.this, MyCart.class);
                startActivity(intent);*/
                addtocartt();

            }
        });
        buynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(FoodDetail.this, R.anim.image_click));
                Intent intent = new Intent(FoodDetail.this, MyCart.class);
                startActivity(intent);

            }
        });

/*
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(MyProfile.this, R.anim.image_click));
                settings.setBackgroundResource(R.color.themered);
            }
        });
*/

        btndelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(FoodDetail.this, R.anim.image_click));
                btndelivery.setBackgroundResource(R.color.themered);
                btndining.setBackgroundResource(R.color.lightgrey);
                btnreview.setBackgroundResource(R.color.lightgrey);

                txbiketx.setTextColor(Color.parseColor("#FFFFFFFF"));
                diningtx.setTextColor(Color.parseColor("#FF000000"));
                txtreview.setTextColor(Color.parseColor("#FF000000"));

                bikeblack.setImageResource(R.drawable.deliverybikewhite);
                diningblack.setImageResource(R.drawable.dininggrey);
                reviewblack.setImageResource(R.drawable.review);
         /*       bikeblack.setVisibility(View.GONE);
                bikewhite.setVisibility(View.VISIBLE);
                diningwhite.setVisibility(View.GONE);
                diningblack.setVisibility(View.VISIBLE);
                reviewwhite.setVisibility(View.GONE);
                reviewblack.setVisibility(View.VISIBLE);*/

                lldelivery.setVisibility(View.VISIBLE);
                lldining.setVisibility(View.GONE);
                llreview.setVisibility(View.GONE);
            }
        });
        btndining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(FoodDetail.this, R.anim.image_click));
                btndelivery.setBackgroundResource(R.color.lightgrey);
                btndining.setBackgroundResource(R.color.themered);
                btnreview.setBackgroundResource(R.color.lightgrey);

                txbiketx.setTextColor(Color.parseColor("#FF000000"));
                diningtx.setTextColor(Color.parseColor("#FFFFFFFF"));
                txtreview.setTextColor(Color.parseColor("#FF000000"));

                bikeblack.setImageResource(R.drawable.deliverybikegrey);
                diningblack.setImageResource(R.drawable.diningwhite);
                reviewblack.setImageResource(R.drawable.review);
               /* diningblack.setVisibility(View.GONE);
                diningwhite.setVisibility(View.VISIBLE);
                bikewhite.setVisibility(View.GONE);
                bikeblack.setVisibility(View.VISIBLE);
                reviewwhite.setVisibility(View.GONE);
                reviewblack.setVisibility(View.VISIBLE);*/


                lldelivery.setVisibility(View.GONE);
                lldining.setVisibility(View.VISIBLE);
                llreview.setVisibility(View.GONE);
            }
        });

        btnreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(FoodDetail.this, R.anim.image_click));
                btndelivery.setBackgroundResource(R.color.lightgrey);
                btndining.setBackgroundResource(R.color.lightgrey);
                btnreview.setBackgroundResource(R.color.themered);

                txbiketx.setTextColor(Color.parseColor("#FF000000"));
                diningtx.setTextColor(Color.parseColor("#FF000000"));
                txtreview.setTextColor(Color.parseColor("#FFFFFFFF"));

                bikeblack.setImageResource(R.drawable.deliverybikegrey);
                diningblack.setImageResource(R.drawable.dininggrey);
                reviewblack.setImageResource(R.drawable.reviewwhite);

              /*  reviewblack.setVisibility(View.GONE);
                reviewwhite.setVisibility(View.VISIBLE);
                diningblack.setVisibility(View.VISIBLE);
                diningwhite.setVisibility(View.GONE);
                bikeblack.setVisibility(View.VISIBLE);
                bikewhite.setVisibility(View.GONE);*/

                lldelivery.setVisibility(View.GONE);
                lldining.setVisibility(View.GONE);
                llreview.setVisibility(View.VISIBLE);
            }
        });

    }

    private void addtocartt() {
        token= Prefrence.get(FoodDetail.this, Prefrence.KEY_TOKEN);
        AddtoCartRequest request = new AddtoCartRequest();
        /*request.setEmail("psrr@gmail.com");
        request.setMobileNumber("+918319079228");
        request.setPassword("psr24@psr24@");
        request.setName("Priyanka");*/
        request.setUserID("60882de62fb1f752c8069701");
        request.setFoodItemID("60857625c9107863c4ef683e");
        request.setDiningTime("2021-05-03 10:48:30");
        request.setPrice("5000");
        request.setRestaurantId("6089370372c9741abcd3ed3c");
        request.setType("dinning");


        APIInterface apiInterface = ApiClient.getClient().create(APIInterface.class);
        Call<AddtoCartResponse> resultCall = apiInterface.Calladdtocart("Bearer " + token,request);
        resultCall.enqueue(new Callback<AddtoCartResponse>() {

            @Override
            public void onResponse(Call<AddtoCartResponse> call, retrofit2.Response<AddtoCartResponse> response) {
              //  Toast.makeText(FoodDetail.this, "Hello"+response, Toast.LENGTH_SHORT).show();

                if (response.body().getStatus().equals(true)) {

                    Toast.makeText(FoodDetail.this,  ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
/*
                    Intent intent = new Intent(FoodDetail.this, MyCart.class);
                    startActivity(intent);*/
                } else {
                    Toast.makeText(FoodDetail.this, "please try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddtoCartResponse> call, Throwable t) {
                Toast.makeText(FoodDetail.this, "Please check your Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void init() {
        dialogmode=findViewById(R.id.dialogmode);
        dialogtime=findViewById(R.id.dialogtime);
        dialogoffer=findViewById(R.id.dialogoffer);
        rltiming=findViewById(R.id.rltiming);

        addtocart=findViewById(R.id.rlcart);
        buynow=findViewById(R.id.rlbuynow);
        bikeblack = findViewById(R.id.bikeblack);
        diningblack = findViewById(R.id.diningblack);
        reviewblack = findViewById(R.id.ratingblack);


        txbiketx = findViewById(R.id.txtdelivery);
        diningtx = findViewById(R.id.txtdining);
        txtreview = findViewById(R.id.txtreview);


        lldelivery = findViewById(R.id.lldelivery);
        lldining = findViewById(R.id.lldining);
        llreview = findViewById(R.id.llreview);
        btndelivery = findViewById(R.id.delivery);
        btndining = findViewById(R.id.dining);
        btnreview = findViewById(R.id.review);


        rv_MyProjectList = findViewById(R.id.rvreview);
        rv_MyProjectList.setHasFixedSize(true);
        rv_MyProjectLayoutManager = new LinearLayoutManager(FoodDetail.this);
        rv_MyProjectList.setLayoutManager(rv_MyProjectLayoutManager);
        getlist();
        dialog=new TransparentProgressDialog(FoodDetail.this);
        dialog.show();
    }

    private void getlist() {
        username= Prefrence.get(FoodDetail.this, Prefrence.KEY_MANAGER_ID);
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
                    Toast.makeText(FoodDetail.this, "No Data Get", Toast.LENGTH_SHORT).show();
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
            rv_MyProjectAdapter = new ReviewAdapter(FoodDetail.this, (ArrayList<Result>) mListData);
            rv_MyProjectList.setAdapter(rv_MyProjectAdapter);
        }

    }

}