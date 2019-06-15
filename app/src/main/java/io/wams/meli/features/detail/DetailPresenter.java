package io.wams.meli.features.detail;

import java.text.NumberFormat;

import javax.inject.Inject;

import io.wams.meli.data.DataManager;
import io.wams.meli.features.base.BasePresenter;
import io.wams.meli.injection.ConfigPersistent;
import io.wams.meli.util.rx.scheduler.SchedulerUtils;

@ConfigPersistent
public class DetailPresenter extends BasePresenter<DetailMvpView> {

    private final DataManager dataManager;

    @Inject
    public DetailPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(DetailMvpView mvpView) {
        super.attachView(mvpView);
    }

    public void getDetail(String id) {
        checkViewAttached();
        getView().showProgress(true);
        dataManager
                .getDetail(id)
                .compose(SchedulerUtils.ioToMain())
                .subscribe(
                        detailResult -> {
                            // It should be always checked if MvpView (Fragment or Activity) is attached.
                            // Calling showProgress() on a not-attached fragment will throw a NPE
                            // It is possible to ask isAdded() in the fragment, but it's better to ask in the presenter
                            getView().showProgress(false);
                            getView().setCarousel(detailResult.getPictures());
                            getView().setTitle(detailResult.getTitle());
                            getView().setCondition(detailResult.getCondition(), detailResult.getAvailableQuantity().toString());

                            NumberFormat format = NumberFormat.getCurrencyInstance();
                            if(detailResult.getPrice()!=null)
                            getView().setPrice(format.format(detailResult.getPrice()));

                            if(detailResult.getSellerAddress()!=null){
                                getView().setAddress(detailResult.getSellerAddress().getCity().getName() + " - " + detailResult.getSellerAddress().getState().getName());
                            }

                        },
                        throwable -> {
                            getView().showProgress(false);
                            getView().showError(throwable);
                        });
    }
}
