
package com.ichef.android.requestmodel.Updateproductrequest;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class UpdateProductRequest {

    @SerializedName("foodItem_name")
    @Expose
    private String foodItemName;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("raw_material")
    @Expose
    private String rawMaterial;
    @SerializedName("taste")
    @Expose
    private String taste;
    @SerializedName("cat_id")
    @Expose
    private String catId;
    @SerializedName("sub_cat_id")
    @Expose
    private String subCatId;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("eat_time")
    @Expose
    private String eatTime;
    @SerializedName("photos")
    @Expose
    private List<Photo> photos = null;
    @SerializedName("stock_quantity")
    @Expose
    private Integer stockQuantity;
    @SerializedName("unit_price")
    @Expose
    private List<UnitPrice> unitPrice = null;

    public String getFoodItemName() {
        return foodItemName;
    }

    public void setFoodItemName(String foodItemName) {
        this.foodItemName = foodItemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRawMaterial() {
        return rawMaterial;
    }

    public void setRawMaterial(String rawMaterial) {
        this.rawMaterial = rawMaterial;
    }

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getSubCatId() {
        return subCatId;
    }

    public void setSubCatId(String subCatId) {
        this.subCatId = subCatId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getEatTime() {
        return eatTime;
    }

    public void setEatTime(String eatTime) {
        this.eatTime = eatTime;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public List<UnitPrice> getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(List<UnitPrice> unitPrice) {
        this.unitPrice = unitPrice;
    }

}
