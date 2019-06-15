package io.wams.meli.features.country;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.wams.meli.R;
import io.wams.meli.data.model.response.country.Country;
import io.wams.meli.features.base.BaseActivity;
import io.wams.meli.features.common.ErrorView;
import io.wams.meli.features.detail.DetailActivity;
import io.wams.meli.features.search.SearchActivity;
import io.wams.meli.injection.component.ActivityComponent;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

import static io.wams.meli.features.detail.DetailActivity.EXTRA_ITEM_ID;

public class CountryActivity extends BaseActivity implements CountryMvpView, ErrorView.ErrorListener {


    @Inject
    CountryAdapter countryAdapter;
    @Inject
    CountryPresenter countryPresenter;

   /* @Inject
    PreferencesHelper preferencesHelper;*/

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @BindView(R.id.recycler_country)
    RecyclerView countryRecycler;


    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, CountryActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        countryRecycler.setLayoutManager(new GridLayoutManager(this,2));
        countryRecycler.setAdapter(countryAdapter);
        countryClicked();

        countryPresenter.getData(this);
    }

    private void countryClicked() {
        Disposable disposable =
                countryAdapter
                        .getCountryClick()
                        .subscribe(
                                idSite -> {
                                    startActivity(SearchActivity.getStartIntent(this, idSite));
                                    //preferencesHelper.putString("Country",idSite);
                                },
                                throwable -> {
                                    Timber.e(throwable, "Country click failed");
                                    Toast.makeText(
                                            this,
                                            R.string.error_something_bad_happened,
                                            Toast.LENGTH_LONG)
                                            .show();
                                });
        countryPresenter.addDisposable(disposable);
    }

    @Override
    public int getLayout() {
        return R.layout.country_activity;
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void attachView() {
        countryPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        countryPresenter.detachView();
    }

    @Override
    public void showCountries(List<Country> countries) {
        countryAdapter.setCountry(countries);
        countryRecycler.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress(boolean show) {
        if (show) {
            if (countryRecycler.getVisibility() == View.VISIBLE
                    && countryAdapter.getItemCount() > 0) {
            } else {
                progressBar.setVisibility(View.VISIBLE);
                countryRecycler.setVisibility(View.GONE);
            }
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError(Throwable error) {
        countryRecycler.setVisibility(View.GONE);
        Timber.e(error, "There was an error retrieving the date");
    }

    @Override
    public void onReloadData() {
        countryPresenter.getData(this);
    }
}
