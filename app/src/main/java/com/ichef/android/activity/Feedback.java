package com.ichef.android.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ichef.android.R;

public class Feedback extends AppCompatActivity {

    ImageView redheart,whiteheart;
    RatingBar ratingBar,ratingBar2,ratingBar3;
    ImageView smile,qualitysmile,servicesmile;
    TextView  txsmile,txsmile2,txsmile3;
    LinearLayout llsubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        init();
        onlclick();
    }

    private void init() {
        redheart= findViewById(R.id.redheartt);
        whiteheart= findViewById(R.id.whiteherat);
        ratingBar= findViewById(R.id.rate);
        ratingBar2= findViewById(R.id.qualityrate);
        ratingBar3= findViewById(R.id.servicerate);
        smile= findViewById(R.id.smile);
        qualitysmile= findViewById(R.id.qualitysmile);
        servicesmile= findViewById(R.id.servicesmile);
        txsmile= findViewById(R.id.txt);
        txsmile2= findViewById(R.id.qualitytxt);
        txsmile3= findViewById(R.id.servicetxt);
        llsubmit =findViewById(R.id.llsubmit);
    }

    private void onlclick() {

        redheart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redheart.setVisibility(View.GONE);
                whiteheart.setVisibility(View.VISIBLE);
            }
        });
        whiteheart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redheart.setVisibility(View.VISIBLE);
                whiteheart.setVisibility(View.GONE);
            }
        });
        llsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                //Getting the rating and displaying it in the toast
                  Float rating = ratingBar.getRating();
               //   Toast.makeText(getApplicationContext(), "You have rated :   " + rating, Toast.LENGTH_SHORT).show();
                  if (rating==5.0){
                  smile.setImageResource(R.drawable.smileexelent);
                  txsmile.setText("Excellent");
                  }
                  else if (rating==4.5){
                    smile.setImageResource(R.drawable.smileexelent);
                    txsmile.setText("Excellent");
                  }
                  else if (rating==4.0){
                      smile.setImageResource(R.drawable.smileexelent);
                      txsmile.setText("Excellent");
                  }
                  else if (rating==3.5){
                      smile.setImageResource(R.drawable.smilegood);
                      txsmile.setText("Good");}
                  else if (rating==3.0){
                    smile.setImageResource(R.drawable.smilegood);
                      txsmile.setText("Good");}
                  else if (rating==2.5){
                      smile.setImageResource(R.drawable.smilesad);
                      txsmile.setText("Bad");}
                  else if (rating==2.0){
                      smile.setImageResource(R.drawable.smilesad);
                      txsmile.setText("Bad");}
                  else if (rating==1.5){
                      smile.setImageResource(R.drawable.smilesad);
                      txsmile.setText("Bad");}
                  else if (rating==1.0){
                      smile.setImageResource(R.drawable.smilesad);
                      txsmile.setText("Bad");}
                  else if (rating==0.5){
                      smile.setImageResource(R.drawable.smilesad);
                      txsmile.setText("Bad");}
                  else {
                      /*smile.setImageResource(R.drawable.smilesad);
                      txsmile.setText("Bad");*/
                  }

                  /**/
                Float rating2 = ratingBar2.getRating();
             //   Toast.makeText(getApplicationContext(), "You have rated :   " + rating2, Toast.LENGTH_SHORT).show();
                if (rating2==5.0){
                    qualitysmile.setImageResource(R.drawable.smileexelent);
                    txsmile2.setText("Excellent");
                }
                else if (rating2==4.5){
                    qualitysmile.setImageResource(R.drawable.smileexelent);
                    txsmile2.setText("Excellent");
                }
                else if (rating2==4.0){
                    qualitysmile.setImageResource(R.drawable.smileexelent);
                    txsmile2.setText("Excellent");
                }
                else if (rating2==3.5){
                    qualitysmile.setImageResource(R.drawable.smilegood);
                    txsmile2.setText("Good");}
                else if (rating2==3.0){
                    qualitysmile.setImageResource(R.drawable.smilegood);
                    txsmile2.setText("Good");}
                else if (rating2==2.5){
                    qualitysmile.setImageResource(R.drawable.smilesad);
                    txsmile2.setText("Bad");}
                else if (rating2==2.0){
                    qualitysmile.setImageResource(R.drawable.smilesad);
                    txsmile2.setText("Bad");}
                else if (rating2==1.5){
                    qualitysmile.setImageResource(R.drawable.smilesad);
                    txsmile2.setText("Bad");}
                else if (rating2==1.0){
                    qualitysmile.setImageResource(R.drawable.smilesad);
                    txsmile2.setText("Bad");}
                else if (rating2==0.5){
                    qualitysmile.setImageResource(R.drawable.smilesad);
                    txsmile2.setText("Bad");}
                else {
                      /*smile.setImageResource(R.drawable.smilesad);
                      txsmile.setText("Bad");*/
                }

                /**/
                Float rating3 = ratingBar3.getRating();
              //  Toast.makeText(getApplicationContext(), "You have rated :   " + rating3, Toast.LENGTH_SHORT).show();
                if (rating3==5.0){
                    servicesmile.setImageResource(R.drawable.smileexelent);
                    txsmile3.setText("Excellent");
                }
                else if (rating3==4.5){
                    servicesmile.setImageResource(R.drawable.smileexelent);
                    txsmile3.setText("Excellent");
                }
                else if (rating3==4.0){
                    servicesmile.setImageResource(R.drawable.smileexelent);
                    txsmile3.setText("Excellent");
                }
                else if (rating3==3.5){
                    servicesmile.setImageResource(R.drawable.smilegood);
                    txsmile3.setText("Good");}
                else if (rating3==3.0){
                    servicesmile.setImageResource(R.drawable.smilegood);
                    txsmile3.setText("Good");}
                else if (rating3==2.5){
                    servicesmile.setImageResource(R.drawable.smilesad);
                    txsmile3.setText("Bad");}
                else if (rating3==2.0){
                    servicesmile.setImageResource(R.drawable.smilesad);
                    txsmile3.setText("Bad");}
                else if (rating3==1.5){
                    servicesmile.setImageResource(R.drawable.smilesad);
                    txsmile3.setText("Bad");}
                else if (rating3==1.0){
                    servicesmile.setImageResource(R.drawable.smilesad);
                    txsmile3.setText("Bad");}
                else if (rating3==0.5){
                    servicesmile.setImageResource(R.drawable.smilesad);
                    txsmile3.setText("Bad");}
                else {
                      /*smile.setImageResource(R.drawable.smilesad);
                      txsmile.setText("Bad");*/
                }



            }

        });


    }
}