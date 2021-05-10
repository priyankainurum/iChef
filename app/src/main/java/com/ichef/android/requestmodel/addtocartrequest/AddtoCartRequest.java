
package com.ichef.android.requestmodel.addtocartrequest;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class AddtoCartRequest {

    @SerializedName("foodItem_ID")
    @Expose
    private String foodItemID;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("userID")
    @Expose
    private String userID;
    @SerializedName("restaurant_id")
    @Expose
    private String restaurantId;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("dining_time")
    @Expose
    private String diningTime;

    public String getFoodItemID() {
        return foodItemID;
    }

    public void setFoodItemID(String foodItemID) {
        this.foodItemID = foodItemID;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDiningTime() {
        return diningTime;
    }

    public void setDiningTime(String diningTime) {
        this.diningTime = diningTime;
    }

}
