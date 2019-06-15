package io.wams.meli.features.search;

import io.wams.meli.data.model.response.search.SearchResult;
import io.wams.meli.features.base.MvpView;

public interface MainMvpView extends MvpView {

    void showResult(SearchResult result);

    void addResult(SearchResult result);

    void showProgress(boolean show);

    void showError(Throwable error);

    void setQuery(String query);

    void setPaginationCount(int paginationCount);

    void showNotFoundLayout();

}