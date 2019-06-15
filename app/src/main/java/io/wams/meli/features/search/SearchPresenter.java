package io.wams.meli.features.search;

import javax.inject.Inject;

import io.wams.meli.data.DataManager;
import io.wams.meli.features.base.BasePresenter;
import io.wams.meli.injection.ConfigPersistent;
import io.wams.meli.util.rx.scheduler.SchedulerUtils;

@ConfigPersistent
public class SearchPresenter extends BasePresenter<MainMvpView> {

    private final DataManager dataManager;

    @Inject
    public SearchPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(MainMvpView mvpView) {
        super.attachView(mvpView);
    }

    public void getItem(String site, String q, int offset, int limit) {
        checkViewAttached();
        if (offset == 0)
            getView().showProgress(true);

        dataManager
                .getSearchList(site, q, offset, limit)
                .compose(SchedulerUtils.ioToMain())
                .subscribe(
                        result -> {

                            if(result.getPaging().getTotal()==0){
                                getView().showNotFoundLayout();
                            }else{
                                if (offset > 0)
                                    getView().addResult(result);
                                else
                                    getView().showResult(result);

                                getView().showProgress(false);
                                getView().setPaginationCount(getPaginationCount(result.getPaging().getTotal()));
                            }
                        },
                        throwable -> {
                            getView().showProgress(false);
                            getView().showError(throwable);
                        });
    }

    public void setQuery(String query) {
        getView().setQuery(query);
    }

    public int getPaginationCount(int totalItems) {

        int paginationCount;

        paginationCount = totalItems / 50;

        return paginationCount;
    }
}
