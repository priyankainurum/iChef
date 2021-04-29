package com.ichef.android.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.ichef.android.MainActivity;
import com.ichef.android.R;
import com.ichef.android.utils.Prefrence;

public class SplashScreen extends AppCompatActivity {


    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

      //  Mint.initAndStartSession(this, "a80e4c62");

        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String userID = Prefrence.get(SplashScreen.this, Prefrence.KEY_USER_ID);


                if (userID.equalsIgnoreCase(null) || userID.equalsIgnoreCase("") ||
                        userID.equalsIgnoreCase("0")){

                    Intent intent = new Intent(SplashScreen.this, Login.class);
                    startActivity(intent);
                    finish();

                }
                else {

                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);

                }

            }
        },2000);

    }
}