package com.eksler.vadim.mapapplication.di.application;

import android.content.Context;

import com.eksler.vadim.mapapplication.repository.providers.DatabaseProvider;
import com.eksler.vadim.mapapplication.repository.providers.IDatabaseProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Global provider.
 * Can provide Gson, ApiClient etc.
 */

@Module
public class AppModule {

    private final Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    IDatabaseProvider provideDatabase() { return new DatabaseProvider(); }

}
