package com.ichef.android.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.hbb20.CountryCodePicker;
import com.ichef.android.R;
import com.ichef.android.requestmodel.LoginRequest;
import com.ichef.android.responsemodel.login.LoginResponse;
import com.ichef.android.retrofit.APIInterface;
import com.ichef.android.retrofit.ApiClient;
import com.ichef.android.utils.Prefrence;
import com.ichef.android.utils.TransparentProgressDialog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.internal.http2.Header;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    TransparentProgressDialog dialog;
    private static final int RC_SIGN_IN = 007;
    private static final String TAG = "Hello";
    TextView blank,login,signup,otp,signupnext;
    LinearLayout lllogin,llsignup,fb,google,apple,llnext;
    EditText phoneno;
    private static String VALIDATION_MSG;
    LinearLayout sendotpbtn;
    String text;
    View view;
    ImageView user,server;
    String users="0",servers="0";
    CountryCodePicker ccp;
    String selected_country_code;
    GoogleSignInClient mGoogleSignInClient;

    LinearLayout g_pluse;
    private String gid;
    private Uri gprofile;
    private String gmail;
    private String glastname;
    private String gname;
    private String gfullname;

    String mobile,email,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        onclick();

        // Configure sign-in to request the user's ID, email address, and basic
       // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        /*onstart check if user already signed in or not*/
        // Check for existing Google Sign In account, if the user is already signed in
       // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.sign_in_button:
                        signIn();
                        break;
                    // ...
                }
            }
        });


    }
    private void updateUI(@Nullable GoogleSignInAccount account) {
        if (account != null) {


            gid = account.getId();
            gmail = account.getEmail();
            gname = account.getGivenName();
            glastname = account.getFamilyName();
            if (account.getPhotoUrl() != null) {
                gprofile = account.getPhotoUrl();
            }
            gfullname = gname + " " + glastname;

            Toast.makeText(Login.this, ""+gfullname, Toast.LENGTH_SHORT).show();
/*
            CommonUtility.setSetting(this, "email", gmail);
            CommonUtility.setSetting(this, "first_name", gname);
            CommonUtility.setSetting(this, "last_name", glastname);
            CommonUtility.setSetting(this, "g_profile", gprofile.toString());*/
/*
            if (gfullname!=null) {
                Toast.makeText(this, gfullname, Toast.LENGTH_LONG).show();
            }
*/
            Intent intent = new Intent(this, HomePageActivity.class);
            intent.putExtra("gid", gid);
            intent.putExtra("gmail", gmail);
            //  intent.putExtra("gname", gname);
            //  intent.putExtra("glastname", glastname);
            if (gprofile != null) {
                intent.putExtra("gprofile", gprofile.toString());
            }
            if (gfullname != null && gfullname != "null null") {
                intent.putExtra("gfullname", gfullname);
            }

            startActivity(intent);
            /*mStatusTextView.setText(getString(R.string.signed_in_fmt, account.getDisplayName()));

            findViewById(R.id.sign_in_button).setVisibility(View.GONE);
            findViewById(R.id.sign_out_and_disconnect).setVisibility(View.VISIBLE);*/

            //   checkGUser();
        } else {
            /*mStatusTextView.setText(R.string.signed_out);

            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
            findViewById(R.id.sign_out_and_disconnect).setVisibility(View.GONE);*/
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    public void onCountryPickerClick(View view) {
        ccp.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
               // Alert.showMessage(Login.this, ccp.getSelectedCountryCodeWithPlus());
                selected_country_code = ccp.getSelectedCountryCodeWithPlus();
              //  Toast.makeText(Login.this, ""+selected_country_code, Toast.LENGTH_SHORT).show();
                Toast.makeText(Login.this, "Updated " + ccp.getSelectedCountryName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void onclick() {
        sendotpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signin();
                dialog=new TransparentProgressDialog(Login.this);
                dialog.show();
            }
        });

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
/*
        otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(Login.this, R.anim.image_click));
                Intent intent = new Intent(Login.this, Otp.class);
                startActivity(intent);
            }
        });
*/
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

    }

   /* private void signin() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("psr@gmail.com");
        loginRequest.setMobileNumber("+918319079223");
        loginRequest.setPassword("psr24@psr24@");

    }*/
    


    private void signin() {

        /*Map<String, String> map = new HashMap<>();
        map.put("mobile_number", "+918319079223");
        map.put("password", "psr24@psr24@");
        map.put("email", "psr@gmail.com");*/
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("psr@gmail.com");
        loginRequest.setMobileNumber("+918319079223");
        loginRequest.setPassword("psr24@psr24@");

        APIInterface apiInterface = ApiClient.getClient().create(APIInterface.class);
        Call<LoginResponse> resultCall = apiInterface.CallLogin(loginRequest);

        resultCall.enqueue(new Callback<LoginResponse>() {


            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.body().getStatus().equals(true)) {
                    dialog.dismiss();
                    // get headers
                    Headers headers = response.headers();
                    // get header value
                    String cookie = response.headers().get("Set-Cookie");
                   // Toast.makeText(Login.this, ""+headers.get("Auth-Token"), Toast.LENGTH_SHORT).show();
                    // TODO


                    Toast.makeText(Login.this, ""+response.body().getMessage() , Toast.LENGTH_SHORT).show();

                    Prefrence.save(Login.this, Prefrence.KEY_TOKEN, headers.get("Auth-Token"));
                    Prefrence.save(Login.this, Prefrence.KEY_USER_ID, response.body().getParam().getId());
                    Prefrence.save(Login.this, Prefrence.KEY_MOBILE_NO, response.body().getParam().getMobileNumber());
                    Prefrence.save(Login.this, Prefrence.KEY_EMAIL_ID, response.body().getParam().getEmail());
                    Prefrence.save(Login.this, Prefrence.KEY_FIRST_NAME, response.body().getParam().getFirstname());
                    Prefrence.save(Login.this, Prefrence.KEY_USERTYPE, response.body().getParam().getUserType());

                    String usertype=response.body().getParam().getUserType();
                    Intent in = new Intent(Login.this, HomePageActivity.class);
                    startActivity(in);
                    finish();
                } else {
                    dialog.dismiss();
                    Toast.makeText(Login.this, "please try again"+response, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(Login.this, "Please check your Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });

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
        ccp = findViewById(R.id.ccp);
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