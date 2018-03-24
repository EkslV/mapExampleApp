package com.eksler.vadim.mapapplication.di.maps;

import com.eksler.vadim.mapapplication.presentation.presenter.MapsPresenter;

import dagger.Subcomponent;

/**
 * Created by vadimaeksler on 23/03/2018.
 */

@Subcomponent(modules = MapsModule.class)
@MapsScope
public interface MapsComponent {
    void inject(MapsPresenter presenter);
}
