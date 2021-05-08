package com.ichef.android.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ichef.android.R;
import com.ichef.android.requestmodel.OTPRequest;
import com.ichef.android.requestmodel.SignupRequest;
import com.ichef.android.responsemodel.otprequest.OTPResponse;
import com.ichef.android.responsemodel.signup.SignupResponse;
import com.ichef.android.retrofit.APIInterface;
import com.ichef.android.retrofit.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;

public class Otp extends AppCompatActivity {
    ImageView back;
    RelativeLayout go;
    TextView time,txmobile;
    EditText etbox;
    String otp,id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        Intent intent = getIntent();
        otp=intent.getStringExtra("otp");
        id=intent.getStringExtra("id");


        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        init();
        onclick();
        downTimer();
    }

    private void onclick() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(Otp.this, R.anim.image_click));
                getotp();
                /*Intent intent = new Intent(Otp.this, Login.class);
                startActivity(intent);*/
            }
        });
    }

    private void getotp() {
        etbox.setText(otp);
        OTPRequest request = new OTPRequest();
        request.setToken(etbox.getText().toString());
        request.setId(id);

        APIInterface apiInterface = ApiClient.getClient().create(APIInterface.class);
        Call<OTPResponse> resultCall = apiInterface.CallOtp(request);
        resultCall.enqueue(new Callback<OTPResponse>() {

            @Override
            public void onResponse(Call<OTPResponse> call, retrofit2.Response<OTPResponse> response) {
                if (response.body().getStatus().equals(true)) {

                    Toast.makeText(Otp.this,  ""+response.body().getMessage(), Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(Otp.this, Login.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(Otp.this, "please try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OTPResponse> call, Throwable t) {
                Toast.makeText(Otp.this, "Please check your Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void init() {
        back = findViewById(R.id.back);
        go = findViewById(R.id.checkotp);
        time=findViewById(R.id.timer30);
        txmobile=findViewById(R.id.tx2);
        etbox=findViewById(R.id.etbox);

    }
    private void downTimer(){
        new CountDownTimer(30*1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long second = (millisUntilFinished / 1000) % 60;
                long minutes = (millisUntilFinished/(1000*60)) % 60;
                time.setText(minutes + ":" + second);
            }

            @Override
            public void onFinish() {
                time.setText("Time Out");
            }
        }.start();
    }

}