package com.ichef.android.retrofit;



import com.ichef.android.requestmodel.AddNewPd.AddNewItemRequest;
import com.ichef.android.requestmodel.LoginRequest;
import com.ichef.android.requestmodel.OTPRequest;
import com.ichef.android.requestmodel.SignupRequest;
import com.ichef.android.requestmodel.addtocartrequest.AddtoCartRequest;
import com.ichef.android.requestmodel.markbookmark.MarkBookmarkRequest;
import com.ichef.android.responsemodel.addnewproduct.AddNewProductResponse;
import com.ichef.android.responsemodel.addnewproduct.Photo;
import com.ichef.android.responsemodel.addnewproduct.UnitPrice;
import com.ichef.android.responsemodel.addtocartresponse.AddtoCartResponse;
import com.ichef.android.responsemodel.bookmarklist.GetBookmarkListResponse;
import com.ichef.android.responsemodel.fetchcart.FetchCartResponse;
import com.ichef.android.responsemodel.homefood.DriverListResponse;
import com.ichef.android.responsemodel.login.LoginResponse;
import com.ichef.android.responsemodel.markbookmark.MarkBookmarkResponse;
import com.ichef.android.responsemodel.otprequest.OTPResponse;
import com.ichef.android.responsemodel.signup.SignupResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

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
    Call<AddtoCartResponse> Calladdtocart(@Header("Authorization")String token,@Body AddtoCartRequest addtoCartRequest);

    @GET("/product/fetchCart")
    Call<FetchCartResponse> Getcart(@Header("Authorization")String token);

    @Headers("Content-Type: application/json")
    @POST("/product/bookmark")
    Call<MarkBookmarkResponse> Callmarkbookmark(@Header("Authorization")String token, @Body MarkBookmarkRequest markBookmarkRequest);

    @GET("/product/bookmarks")
    Call<GetBookmarkListResponse> Getbookmark(@Header("Authorization")String token);

    @Multipart
    @POST("/product")
    Call<AddNewProductResponse> CallAddProduct(@Header("Authorization") String token,
                                         @Part("file\"; filename=\"pp.png\" ") AddNewItemRequest addNewProductRequest,
                                         @Part("file\"; filename=\"pp.png\" ") Photo file,
                                         @Part("unit_price") UnitPrice unitPrice);


   /* @Headers("Content-Type: application/json")
    @POST("/product")
    Call<AddNewProductResponse> CallAddProduct(@Header("Authorization")String token, @Body AddNewProductRequest addNewProductRequest);*/

   /* @GET("/product/") //60966c59c1efc7002291fad0
    @FormUrlEncoded
    Call<GetProductResponse> GetProduct();

    @POST("/product?page=0&limit=4&sort=datecreated&direction=desc")
    @FormUrlEncoded
    Call<ProductListResponse> CallProductList(@FieldMap Map<String,String> params);*/


  /* @POST("adduser")
     @FormUrlEncoded
     Call<SignupResponse> CallSignup(@FieldMap Map<String, String> params);

     @POST("editproduct")
     @Multipart
     Call<EditProductResponse> EditProduct(@PartMap Map<String, RequestBody> params, @Part MultipartBody.Part filePart1,
                                          @Part MultipartBody.Part filePart2);


    */


}
