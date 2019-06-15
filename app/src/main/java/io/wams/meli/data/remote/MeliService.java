package io.wams.meli.data.remote;

import io.wams.meli.data.model.response.detail.DetailResult;
import io.wams.meli.data.model.response.search.SearchResult;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MeliService {

    @GET("sites/{site}/search")
    Single<SearchResult> getSearchList(@Path("site") String site,
                                       @Query("q") String q,
                                       @Query("offset") int  offset,
                                       @Query("limit") int limit);

    @GET("items/{id}")
    Single<DetailResult> getDetailProduct(@Path("id") String id);
}
