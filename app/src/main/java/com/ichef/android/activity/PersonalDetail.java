package com.ichef.android.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ichef.android.MainActivity;
import com.ichef.android.R;
import com.ichef.android.requestmodel.LoginRequest;
import com.ichef.android.requestmodel.SignupRequest;
import com.ichef.android.responsemodel.signup.SignupResponse;
import com.ichef.android.retrofit.APIInterface;
import com.ichef.android.retrofit.ApiClient;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class PersonalDetail extends AppCompatActivity {

    LinearLayout llcontinue;
    EditText name,mail,phone,password,confirmpassword;
    private static String VALIDATION_MSG;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_detail);
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
        llcontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validation()) {
                    v.startAnimation(AnimationUtils.loadAnimation(PersonalDetail.this, R.anim.image_click));
                   /* Intent intent = new Intent(PersonalDetail.this, NiceToMeetYou.class);
                    startActivity(intent);*/
                    userSignUp();
                }

            }
        });

    }

    private void userSignUp() {

        SignupRequest request = new SignupRequest();
        /*request.setEmail("psrr@gmail.com");
        request.setMobileNumber("+918319079228");
        request.setPassword("psr24@psr24@");
        request.setName("Priyanka");*/
        request.setEmail(mail.getText().toString());
        request.setMobileNumber(phone.getText().toString());
        request.setPassword(password.getText().toString());
        request.setName(name.getText().toString());

        APIInterface apiInterface = ApiClient.getClient().create(APIInterface.class);
        Call<SignupResponse> resultCall = apiInterface.CallSignup(request);
        resultCall.enqueue(new Callback<SignupResponse>() {

            @Override
            public void onResponse(Call<SignupResponse> call, retrofit2.Response<SignupResponse> response) {
                Toast.makeText(PersonalDetail.this, ""+response.body().getMessage() , Toast.LENGTH_SHORT).show();

                if (response.body().getStatus().equals(true)) {

                    Toast.makeText(PersonalDetail.this, ""+response.body().getMessage()+" Your id is - "+response.body().getParam().getId() , Toast.LENGTH_SHORT).show();
                    Toast.makeText(PersonalDetail.this,  "Your OTP - "+response.body().getParam().getOtp(), Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(PersonalDetail.this, Login.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(PersonalDetail.this, "please try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                Toast.makeText(PersonalDetail.this, "Please check your Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private boolean validation() {

        boolean flag = true;

        if (name.getText().toString().trim().length() == 0) {

            flag = false;

            VALIDATION_MSG = "Name is required!";
            name.setError(VALIDATION_MSG);
        }
        if (phone.getText().toString().trim().length() == 0) {

            flag = false;

            VALIDATION_MSG = "Contact No. is required!";
            phone.setError(VALIDATION_MSG);
        }



        else if(!isValidEmail(mail.getText().toString().trim())){

            flag  = false;

            VALIDATION_MSG = "Please enter valid email address!";
            mail.setError(VALIDATION_MSG);

        }
        else if(password.getText().toString().trim().length()==0){

            flag  = false;

            VALIDATION_MSG = "Password is Required!";
            password.setError(VALIDATION_MSG);
        }
        else if(password.getText().toString().trim().contains(" ")){

            flag  = false;

            VALIDATION_MSG = "Space is not allowed in Password!";
            password.setError(VALIDATION_MSG);
        }
        else if(password.getText().toString().trim().length()<6){

            flag = false;

            VALIDATION_MSG = "Minimum 6 digits/characters are required!";
            password.setError(VALIDATION_MSG);

        }
        else if(confirmpassword.getText().toString().trim().length()==0){
            flag  = false;
            VALIDATION_MSG = "Confirm Password is Required!";
            confirmpassword.setError(VALIDATION_MSG);
        }
        else if(!confirmpassword.getText().toString().trim().equals(password.getText().toString().trim())){
            flag  = false;
            VALIDATION_MSG = " Password and Confirm Password should be same!";
            confirmpassword.setError(VALIDATION_MSG);
        }
        return flag;
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }


    private void init() {
        llcontinue= findViewById(R.id.llcontinue);
        name= findViewById(R.id.etname);
        mail= findViewById(R.id.etemail);
        phone= findViewById(R.id.etphone);
        password= findViewById(R.id.etpassword);
        confirmpassword= findViewById(R.id.etconfrmpass);
    }
}