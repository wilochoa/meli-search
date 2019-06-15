package io.wams.meli.features.country;

import android.content.Context;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;

import io.wams.meli.data.DataManager;
import io.wams.meli.data.model.response.country.ParentCountry;
import io.wams.meli.features.base.BasePresenter;
import io.wams.meli.injection.ConfigPersistent;

@ConfigPersistent
public class CountryPresenter extends BasePresenter<CountryMvpView> {

    private final DataManager dataManager;


    @Inject
    public CountryPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(CountryMvpView mvpView) {
        super.attachView(mvpView);
    }

    public void getData(Context context) {
        checkViewAttached();
        getView().showProgress(true);

        String json = null;
        try {
            InputStream is = context.getAssets().open("sites.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            getView().showError(ex);
        }

        try {
            ParentCountry data = new Gson().fromJson(json, ParentCountry.class);
            getView().showCountries(data.getCountryList());
            getView().showProgress(false);
        } catch (NullPointerException ex) {
            getView().showError(ex);
        }


    }
}
