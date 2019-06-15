package io.wams.meli.features.country;

import java.util.List;

import io.wams.meli.data.model.response.country.Country;
import io.wams.meli.features.base.MvpView;

public interface CountryMvpView extends MvpView {

    void showCountries(List<Country> countryList);

    void showProgress(boolean show);

    void showError(Throwable error);
}
