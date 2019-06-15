package io.wams.meli.data;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.wams.meli.data.model.response.detail.DetailResult;
import io.wams.meli.data.model.response.search.SearchResult;
import io.wams.meli.data.remote.MeliService;
import io.reactivex.Single;


@Singleton
public class DataManager {

    private MeliService meliService;

    @Inject
    public DataManager(MeliService meliService) {
        this.meliService = meliService;
    }

    public Single<SearchResult> getSearchList(String site, String q, int offset , int limit) {
        return meliService
                .getSearchList(site, q, offset, limit);
    }

    public Single<DetailResult> getDetail(String idProduct) {
        return meliService.getDetailProduct(idProduct);
    }
}
