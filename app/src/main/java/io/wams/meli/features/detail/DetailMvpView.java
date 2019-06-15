package io.wams.meli.features.detail;

import java.util.List;

import io.wams.meli.data.model.response.detail.Picture;
import io.wams.meli.features.base.MvpView;

public interface DetailMvpView extends MvpView {

    void setCarousel(List<Picture> list);

    void setTitle(String title);

    void setCondition(String condition, String available);

    void setAddress(String address);

    void setPrice(String price);

    void showProgress(boolean show);

    void showError(Throwable error);
}
