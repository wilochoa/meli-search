
package io.wams.meli.data.model.response.detail;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Variation {

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("attribute_combinations")
    @Expose
    private List<AttributeCombination> attributeCombinations = null;
    @SerializedName("available_quantity")
    @Expose
    private Integer availableQuantity;
    @SerializedName("sold_quantity")
    @Expose
    private Integer soldQuantity;
    @SerializedName("sale_terms")
    @Expose
    private List<Object> saleTerms = null;
    @SerializedName("picture_ids")
    @Expose
    private List<String> pictureIds = null;
    @SerializedName("catalog_product_id")
    @Expose
    private Object catalogProductId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<AttributeCombination> getAttributeCombinations() {
        return attributeCombinations;
    }

    public void setAttributeCombinations(List<AttributeCombination> attributeCombinations) {
        this.attributeCombinations = attributeCombinations;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public Integer getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(Integer soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

    public List<Object> getSaleTerms() {
        return saleTerms;
    }

    public void setSaleTerms(List<Object> saleTerms) {
        this.saleTerms = saleTerms;
    }

    public List<String> getPictureIds() {
        return pictureIds;
    }

    public void setPictureIds(List<String> pictureIds) {
        this.pictureIds = pictureIds;
    }

    public Object getCatalogProductId() {
        return catalogProductId;
    }

    public void setCatalogProductId(Object catalogProductId) {
        this.catalogProductId = catalogProductId;
    }

}
