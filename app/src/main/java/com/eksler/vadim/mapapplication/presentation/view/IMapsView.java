package com.eksler.vadim.mapapplication.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.eksler.vadim.mapapplication.model.CustomMarker;
import com.google.maps.android.clustering.Cluster;

/**
 * Created by vadimeksler on 22/03/2018.
 */

@StateStrategyType(value = OneExecutionStateStrategy.class)
public interface IMapsView extends MvpView {
    void showMarker(CustomMarker marker);
    void showRequestName();
    void showClusterMarkers(Cluster<CustomMarker> cluster);
    void showMessage(String s);
    void closeDialog();
}
