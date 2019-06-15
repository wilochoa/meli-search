package io.wams.meli.injection.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.wams.meli.data.remote.MeliService;
import retrofit2.Retrofit;


@Module(includes = {NetworkModule.class})
public class ApiModule {

    @Provides
    @Singleton
    MeliService provideMeliApi(Retrofit retrofit) {
        return retrofit.create(MeliService.class);
    }
}
