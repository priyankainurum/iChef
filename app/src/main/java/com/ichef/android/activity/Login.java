package com.ichef.android.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ichef.android.MainActivity;
import com.ichef.android.R;

import java.util.Locale;

public class Login extends AppCompatActivity {

    TextView blank,login,signup,otp,signupnext;
    LinearLayout lllogin,llsignup,fb,google,apple,llnext;
    EditText phoneno;
    private static String VALIDATION_MSG;
    LinearLayout sendotpbtn;
    String text;
    View view;
    ImageView user,server;
    String users="0",servers="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        onclick();
    }

    private void onclick() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(Login.this, R.anim.image_click));
                login.setTextColor(Color.parseColor("#FFFFFFFF"));
                signup.setTextColor(Color.parseColor("#D8455E"));
                blank.setText("");
                signup.setText("SIGNUP");
                login.setText("LOGIN");
                lllogin.setVisibility(View.VISIBLE);
                llsignup.setVisibility(View.GONE);

            }
        });
        blank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(Login.this, R.anim.image_click));
                login.setTextColor(Color.parseColor("#FFFFFFFF"));
                signup.setTextColor(Color.parseColor("#D8455E"));
                blank.setText("");
                signup.setText("SIGNUP");
                login.setText("LOGIN");
                lllogin.setVisibility(View.VISIBLE);
                llsignup.setVisibility(View.GONE);

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login.setText("SIGNUP");
                signup.setText("");
                blank.setText("LOGIN");
                v.startAnimation(AnimationUtils.loadAnimation(Login.this, R.anim.image_click));
                login.setTextColor(Color.parseColor("#FFFFFFFF"));
                signup.setTextColor(Color.parseColor("#D8455E"));
                blank.setTextColor(Color.parseColor("#D8455E"));
                lllogin.setVisibility(View.GONE);
                llsignup.setVisibility(View.VISIBLE);
            }
        });
        otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(Login.this, R.anim.image_click));
                Intent intent = new Intent(Login.this, Otp.class);
                startActivity(intent);
            }
        });
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(Login.this, R.anim.image_click));
                Intent intent = new Intent(Login.this, HomePageActivity.class);
                startActivity(intent);
            }
        });
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(Login.this, R.anim.image_click));
                Intent intent = new Intent(Login.this, HomePageActivity.class);
                startActivity(intent);
            }
        });
        apple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(Login.this, R.anim.image_click));
                Intent intent = new Intent(Login.this, HomePageActivity.class);
                startActivity(intent);
            }
        });
        signupnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (users.equals("1")){
                    v.startAnimation(AnimationUtils.loadAnimation(Login.this, R.anim.image_click));
                    Intent intent = new Intent(Login.this, PersonalDetail.class);
                    startActivity(intent);
                }
                else if (servers.equals("1")){
                v.startAnimation(AnimationUtils.loadAnimation(Login.this, R.anim.image_click));
                Intent intent = new Intent(Login.this, BusinessDetail.class);
                startActivity(intent);}
                else {
                    Toast.makeText(Login.this, "Please select type of user", Toast.LENGTH_SHORT).show();
                }
            }
        });
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(Login.this, R.anim.image_click));
                user.setImageResource(R.drawable.userwhite);
                server.setImageResource(R.drawable.chef);
                llnext.setBackgroundResource(R.drawable.bordergreen);
                users="1";
                servers="0";

            }
        });
        server.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(Login.this, R.anim.image_click));
                user.setImageResource(R.drawable.user);
                server.setImageResource(R.drawable.serverwhite);
                llnext.setBackgroundResource(R.drawable.bordergreen);
                users="0";
                servers="1";
            }
        });

        phoneno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validation()){
                    sendotpbtn.setBackgroundResource(R.drawable.bordergreen);
                }
            }
        }
        );

/*
        phoneno.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                text = phoneno.getText().toString().toLowerCase(Locale.getDefault());
                if(validation()){
                    phoneno.setBackgroundResource(R.color.green);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
                text = phoneno.getText().toString().toLowerCase(Locale.getDefault());
                if(validation()){
                    phoneno.setBackgroundResource(R.color.green);
                }
                text = phoneno.getText().toString().toLowerCase(Locale.getDefault());
                if(validation()){
                    phoneno.setBackgroundResource(R.color.green);
                }

            }
        });
*/
    }

    private boolean validation() {
        boolean flag = true;
        if(phoneno.getText().toString().trim().length()==0){
            flag  = false;
            VALIDATION_MSG = "Mobile No. is required!";
            phoneno.setError(VALIDATION_MSG);
        }
        else if(phoneno.getText().toString().trim().length()>10 || text.trim().length()<10){
            flag = false;
            VALIDATION_MSG = "Mobile No. must contain 10 digits only";
            phoneno.setError(VALIDATION_MSG);
        }
        else {
            phoneno.setBackgroundResource(R.color.green);
        }
        return flag;
    }

    private void init() {
        blank= findViewById(R.id.blank);
        login= findViewById(R.id.login);
        signupnext= findViewById(R.id.signupnext);
        fb= findViewById(R.id.fb);
        google= findViewById(R.id.google);
        apple= findViewById(R.id.apple);
        signup = findViewById(R.id.signup);
        otp= findViewById(R.id.otp);
        lllogin = findViewById(R.id.lllogin);
        llsignup = findViewById(R.id.llsignup);
        phoneno= findViewById(R.id.phoneno);
        sendotpbtn=findViewById(R.id.cardotp);
        view= findViewById(R.id.viewgreen);
        user= findViewById(R.id.imuser);
        server= findViewById(R.id.imserver);
        llnext= findViewById(R.id.llnext);
    }
}