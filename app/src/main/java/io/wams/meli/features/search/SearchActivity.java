package io.wams.meli.features.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import io.wams.meli.R;
import io.wams.meli.data.model.response.search.SearchResult;
import io.wams.meli.features.base.BaseActivity;
import io.wams.meli.features.common.ErrorView;
import io.wams.meli.features.detail.DetailActivity;
import io.wams.meli.features.search.widget.EndlessRecyclerViewScrollListener;
import io.wams.meli.injection.component.ActivityComponent;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class SearchActivity extends BaseActivity implements MainMvpView, ErrorView.ErrorListener {

    private static final int LIMIT_COUNT = 50;
    public static final String EXTRA_SITE_NAME = "EXTRA_SITE_NAME";

    @Inject
    SearchAdapter searchAdapter;
    @Inject
    SearchPresenter mainPresenter;

    @BindView(R.id.view_error)
    ErrorView errorView;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @BindView(R.id.recycler_search)
    RecyclerView searchRecycler;

    @BindView(R.id.layout_not_found)
    RelativeLayout layourNotFound;


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    String site;

    String query = "ipod";

    int paginationCount;

    private EndlessRecyclerViewScrollListener scrollListener;

    public static Intent getStartIntent(Context context, String siteName) {
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra(EXTRA_SITE_NAME, siteName);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        site = getIntent().getStringExtra(EXTRA_SITE_NAME);
        if (site == null) {
            throw new IllegalArgumentException("Detail Activity requires a pokemon name@");
        }

        setupToolbar();
        setupRecycler();
        productClicked();
        errorView.setErrorListener(this);
        mainPresenter.getItem(site, query, 0, LIMIT_COUNT);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
        setTitle("Busqueda");
    }

    private void setupRecycler() {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        searchRecycler.setLayoutManager(staggeredGridLayoutManager);
        searchRecycler.setAdapter(searchAdapter);

        scrollListener = new EndlessRecyclerViewScrollListener(staggeredGridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
               // int totalPagination =
               // Log.i("PAGE", page+ " / "+totalItemsCount );
                if(page<=paginationCount){
                    mainPresenter.getItem(site, query, totalItemsCount, LIMIT_COUNT);
                }
            }
        };
        searchRecycler.addOnScrollListener(scrollListener);
    }

    private void productClicked() {
        Disposable disposable =
                searchAdapter
                        .getProductClick()
                        .subscribe(
                                id ->
                                        startActivity(DetailActivity.getStartIntent(this, id)),
                                throwable -> {
                                    Timber.e(throwable, "item click failed");
                                    Toast.makeText(
                                            this,
                                            R.string.error_something_bad_happened,
                                            Toast.LENGTH_LONG)
                                            .show();
                                });
        mainPresenter.addDisposable(disposable);
    }

    @Override
    public int getLayout() {
        return R.layout.search_activity;
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void attachView() {
        mainPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        mainPresenter.detachView();
    }

    @Override
    public void showResult(SearchResult results) {
        layourNotFound.setVisibility(View.GONE);
        searchAdapter.setResult(results.getResults());
        searchRecycler.setVisibility(View.VISIBLE);

    }

    @Override
    public void showNotFoundLayout(){
        searchRecycler.setVisibility(View.GONE);
        layourNotFound.setVisibility(View.VISIBLE);
    }

    @Override
    public void addResult(SearchResult results) {
        searchAdapter.addResult(results.getResults());
    }

    @Override
    public void showProgress(boolean show) {
        if (show) {
            if (searchRecycler.getVisibility() == View.VISIBLE
                    && searchAdapter.getItemCount() > 0) {
            } else {
                progressBar.setVisibility(View.VISIBLE);
                searchRecycler.setVisibility(View.GONE);
            }

            errorView.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError(Throwable error) {
        searchRecycler.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
        Timber.e(error, "There was an error retrieving the pokemon");
    }

    @Override
    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public void setPaginationCount(int paginationCount) {
        this.paginationCount = paginationCount;
    }

    @Override
    public void onReloadData() {
        mainPresenter.getItem(site, query, 0, LIMIT_COUNT);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem mSearch = menu.findItem(R.id.action_search);
        SearchView mSearchView = (SearchView) mSearch.getActionView();
        mSearchView.setQueryHint("Search");
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mainPresenter.setQuery(query);
                mainPresenter.getItem(site, query, 0, LIMIT_COUNT);
                mSearchView.setQuery("",false);
                setTitle(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });

        return true;
    }
}
