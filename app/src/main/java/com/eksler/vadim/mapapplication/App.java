package com.eksler.vadim.mapapplication;

import android.app.Application;

import com.eksler.vadim.mapapplication.di.application.AppComponent;
import com.eksler.vadim.mapapplication.di.application.AppModule;
import com.eksler.vadim.mapapplication.di.application.DaggerAppComponent;
import com.eksler.vadim.mapapplication.di.maps.MapsComponent;
import com.eksler.vadim.mapapplication.di.maps.MapsModule;

/**
 * Created by vadimeksler on 23/03/2018.
 */

public class App extends Application {

    private AppComponent appComponent;
    private MapsComponent mapsComponent;

    private static App app;

    public App() {
        app = this;
    }

    public static App get() {
        return app;
    }

    @Override
    public void onCreate() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        super.onCreate();
    }

    public MapsComponent addMapsComponent(MapsModule mapsModule) {
        if (mapsComponent == null) {
            mapsComponent = appComponent.addMapsComponent(mapsModule);
        }
        return mapsComponent;
    }

    public void clearMapsComponent() { mapsComponent = null; }
}
