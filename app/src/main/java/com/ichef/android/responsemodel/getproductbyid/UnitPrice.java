
package com.ichef.android.responsemodel.getproductbyid;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class UnitPrice {

    @SerializedName("unit_name")
    @Expose
    private String unitName;
    @SerializedName("price")
    @Expose
    private Integer price;

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

}
