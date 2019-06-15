
package io.wams.meli.data.model.response.country;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ParentCountry {

    @SerializedName("parentSites")
    @Expose
    private List<Country> countryList;

    public List<Country> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<Country> countryList) {
        this.countryList = countryList;
    }
}
