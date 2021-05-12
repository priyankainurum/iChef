
package com.ichef.android.requestmodel.markbookmark;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class MarkBookmarkRequest {

    @SerializedName("foodItem_ID")
    @Expose
    private String foodItemID;
    @SerializedName("userID")
    @Expose
    private String userID;

    public String getFoodItemID() {
        return foodItemID;
    }

    public void setFoodItemID(String foodItemID) {
        this.foodItemID = foodItemID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

}
