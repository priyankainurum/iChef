
package com.ichef.android.responsemodel.signup;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Param {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("otp")
    @Expose
    private String otp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

}
