package com.eksler.vadim.mapapplication.di.maps;

import com.eksler.vadim.mapapplication.business.IMapsInteractor;
import com.eksler.vadim.mapapplication.business.MapsInteractor;
import com.eksler.vadim.mapapplication.repository.IMapsRepository;
import com.eksler.vadim.mapapplication.repository.MapsRepository;
import com.eksler.vadim.mapapplication.repository.providers.IDatabaseProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vadimeksler on 23/03/2018.
 */

@Module
public class MapsModule {

    @Provides
    @MapsScope
    IMapsInteractor provideInteractor(IMapsRepository repository) {
        return new MapsInteractor(repository);
    }

    @Provides
    @MapsScope
    IMapsRepository provideRepository(IDatabaseProvider provider) {
        return new MapsRepository(provider);
    }
}
