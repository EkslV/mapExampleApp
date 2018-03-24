package com.eksler.vadim.mapapplication.di.application;

import com.eksler.vadim.mapapplication.di.maps.MapsComponent;
import com.eksler.vadim.mapapplication.di.maps.MapsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by vadimeksler on 23/03/2018.
 */

@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {
    MapsComponent addMapsComponent(MapsModule module);
}
