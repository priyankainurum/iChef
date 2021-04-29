package com.ichef.android.retrofit;



import com.ichef.android.responsemodel.homefood.DriverListResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIInterface {


    @POST("getdriverlist")
    @FormUrlEncoded
    Call<DriverListResponse> getdriverlist(@FieldMap Map<String,String> params);

}
