package com.ichef.android.retrofit;



import com.ichef.android.requestmodel.LoginRequest;
import com.ichef.android.requestmodel.OTPRequest;
import com.ichef.android.requestmodel.SignupRequest;
import com.ichef.android.requestmodel.addtocartrequest.AddtoCartRequest;
import com.ichef.android.responsemodel.addtocartresponse.AddtoCartResponse;
import com.ichef.android.responsemodel.fetchcart.FetchCartResponse;
import com.ichef.android.responsemodel.getproductbyid.GetProductResponse;
import com.ichef.android.responsemodel.homefood.DriverListResponse;
import com.ichef.android.responsemodel.login.LoginResponse;
import com.ichef.android.responsemodel.otprequest.OTPResponse;
import com.ichef.android.responsemodel.signup.SignupResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIInterface {

    @POST("getdriverlist")
    @FormUrlEncoded
    Call<DriverListResponse> getdriverlist(@FieldMap Map<String,String> params);

    @Headers("Content-Type: application/json")
    @POST("/user/personal/login")
    Call<LoginResponse> CallLogin(@Body LoginRequest loginRequest);

    @Headers("Content-Type: application/json")
    @POST("/user/personal/signup")
    Call<SignupResponse> CallSignup(@Body SignupRequest signupRequest);

    @Headers("Content-Type: application/json")
    @POST("/user/verify")
    Call<OTPResponse> CallOtp(@Body OTPRequest otpRequest);

    @Headers("Content-Type: application/json")
    @POST("/product/addToCart")
    Call<AddtoCartResponse> Calladdtocart(@Body AddtoCartRequest addtoCartRequest);

    @GET("/product/fetchCart")
    @FormUrlEncoded
    Call<FetchCartResponse> Getcart();

    @GET("/product/") //60966c59c1efc7002291fad0
    @FormUrlEncoded
    Call<GetProductResponse> GetProduct();

  /* @POST("adduser")
   @FormUrlEncoded
   Call<SignupResponse> CallSignup(@FieldMap Map<String, String> params);

    @POST("login")
    @FormUrlEncoded
    Call<LoginResponse> CallLogin(@FieldMap Map<String, String> params);*/


}
