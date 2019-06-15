
package io.wams.meli.data.model.response.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Installments {

    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("amount")
    @Expose
    private Double amount;
    @SerializedName("rate")
    @Expose
    private Double rate;
    @SerializedName("currency_id")
    @Expose
    private String currencyId;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

}
