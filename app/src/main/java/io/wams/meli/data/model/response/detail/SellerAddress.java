
package io.wams.meli.data.model.response.detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SellerAddress {

    @SerializedName("city")
    @Expose
    private City city;
    @SerializedName("state")
    @Expose
    private State state;
    @SerializedName("country")
    @Expose
    private Country country;
    @SerializedName("search_location")
    @Expose
    private SearchLocation searchLocation;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("id")
    @Expose
    private Integer id;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public SearchLocation getSearchLocation() {
        return searchLocation;
    }

    public void setSearchLocation(SearchLocation searchLocation) {
        this.searchLocation = searchLocation;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
