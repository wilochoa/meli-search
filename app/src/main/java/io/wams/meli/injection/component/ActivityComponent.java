package io.wams.meli.injection.component;

import dagger.Subcomponent;
import io.wams.meli.features.country.CountryActivity;
import io.wams.meli.features.detail.DetailActivity;
import io.wams.meli.features.search.SearchActivity;
import io.wams.meli.injection.PerActivity;
import io.wams.meli.injection.module.ActivityModule;

@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(DetailActivity detailActivity);

    void inject(CountryActivity countryActivity);

    void inject(SearchActivity searchActivity);

}
