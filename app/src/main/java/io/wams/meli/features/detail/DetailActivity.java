package io.wams.meli.features.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.wams.meli.R;
import io.wams.meli.data.model.response.detail.Picture;
import io.wams.meli.features.base.BaseActivity;
import io.wams.meli.features.common.ErrorView;
import io.wams.meli.injection.component.ActivityComponent;
import timber.log.Timber;

public class DetailActivity extends BaseActivity implements DetailMvpView, ErrorView.ErrorListener {

    public static final String EXTRA_ITEM_ID = "EXTRA_ITEM_ID";

    @Inject
    DetailPresenter detailPresenter;

    @BindView(R.id.view_error)
    ErrorView errorView;

    @BindView(R.id.progress)
    ProgressBar progress;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.carouselView)
    ImageView carouselView;

    @BindView(R.id.layout_detail)
    View detailLayout;

    @BindView(R.id.txt_title)
    TextView txtTitle;

    @BindView(R.id.txt_price)
    TextView txtPrice;

    @BindView(R.id.txt_condition)
    TextView txtCondition;

    @BindView(R.id.txt_address)
    TextView txtAddress;

    private String itemId;

    List<Picture> listPictures;

    public static Intent getStartIntent(Context context, String itemId) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_ITEM_ID, itemId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        itemId = getIntent().getStringExtra(EXTRA_ITEM_ID);
        if (itemId == null) {
            throw new IllegalArgumentException("Detail Activity requires an item id");
        }

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("");
        errorView.setErrorListener(this);
        detailPresenter.getDetail(itemId);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_detail;
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void attachView() {
        detailPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        detailPresenter.detachView();
    }

    @Override
    public void setCarousel(List<Picture> list) {
        Glide.with(getApplicationContext())
                .load(list.get(0).getSecureUrl())
                .apply(RequestOptions.centerCropTransform())
                .into(carouselView);
    }

    @Override
    public void setTitle(String title) {
        txtTitle.setText(title);
    }

    @Override
    public void setCondition(String condition, String available) {
        txtCondition.setText(condition.toUpperCase() + " - "+ available + " Disponibles" );
    }

    @Override
    public void setAddress(String address) {
        txtAddress.setText(address);
    }

    @Override
    public void setPrice(String price) {
        txtPrice.setText(price);
    }

    @Override
    public void showProgress(boolean show) {
        errorView.setVisibility(View.GONE);
        progress.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showError(Throwable error) {
        detailLayout.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
        Timber.e(error, "There was a problem retrieving the pokemon...");
    }

    @Override
    public void onReloadData() {
    }

}
