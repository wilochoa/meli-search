
package io.wams.meli.data.model.response.detail;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Shipping {

    @SerializedName("mode")
    @Expose
    private String mode;
    @SerializedName("methods")
    @Expose
    private List<Object> methods = null;
    @SerializedName("tags")
    @Expose
    private List<Object> tags = null;
    @SerializedName("dimensions")
    @Expose
    private Object dimensions;
    @SerializedName("local_pick_up")
    @Expose
    private Boolean localPickUp;
    @SerializedName("free_shipping")
    @Expose
    private Boolean freeShipping;
    @SerializedName("logistic_type")
    @Expose
    private String logisticType;
    @SerializedName("store_pick_up")
    @Expose
    private Boolean storePickUp;

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public List<Object> getMethods() {
        return methods;
    }

    public void setMethods(List<Object> methods) {
        this.methods = methods;
    }

    public List<Object> getTags() {
        return tags;
    }

    public void setTags(List<Object> tags) {
        this.tags = tags;
    }

    public Object getDimensions() {
        return dimensions;
    }

    public void setDimensions(Object dimensions) {
        this.dimensions = dimensions;
    }

    public Boolean getLocalPickUp() {
        return localPickUp;
    }

    public void setLocalPickUp(Boolean localPickUp) {
        this.localPickUp = localPickUp;
    }

    public Boolean getFreeShipping() {
        return freeShipping;
    }

    public void setFreeShipping(Boolean freeShipping) {
        this.freeShipping = freeShipping;
    }

    public String getLogisticType() {
        return logisticType;
    }

    public void setLogisticType(String logisticType) {
        this.logisticType = logisticType;
    }

    public Boolean getStorePickUp() {
        return storePickUp;
    }

    public void setStorePickUp(Boolean storePickUp) {
        this.storePickUp = storePickUp;
    }

}
