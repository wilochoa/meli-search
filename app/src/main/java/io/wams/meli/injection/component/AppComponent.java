package io.wams.meli.injection.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import io.wams.meli.data.DataManager;
import io.wams.meli.injection.ApplicationContext;
import io.wams.meli.injection.module.AppModule;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    @ApplicationContext
    Context context();

    Application application();

    DataManager apiManager();
}
