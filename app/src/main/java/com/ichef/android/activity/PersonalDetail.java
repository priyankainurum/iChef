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

import com.ichef.android.MainActivity;
import com.ichef.android.R;

public class PersonalDetail extends AppCompatActivity {

    LinearLayout llcontinue;
    EditText name,mail,password,confirmpassword;
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
                    Intent intent = new Intent(PersonalDetail.this, NiceToMeetYou.class);
                    startActivity(intent);
                }

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
        password= findViewById(R.id.etpassword);
        confirmpassword= findViewById(R.id.etconfrmpass);
    }
}