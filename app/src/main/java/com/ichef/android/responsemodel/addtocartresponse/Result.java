
package com.ichef.android.responsemodel.addtocartresponse;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Result {

    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("foodItem_ID")
    @Expose
    private String foodItemID;
    @SerializedName("price")
    @Expose
    private Integer price;
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
    @SerializedName("datecreated")
    @Expose
    private String datecreated;
    @SerializedName("lastmodified")
    @Expose
    private String lastmodified;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFoodItemID() {
        return foodItemID;
    }

    public void setFoodItemID(String foodItemID) {
        this.foodItemID = foodItemID;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
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

    public String getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(String datecreated) {
        this.datecreated = datecreated;
    }

    public String getLastmodified() {
        return lastmodified;
    }

    public void setLastmodified(String lastmodified) {
        this.lastmodified = lastmodified;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}
