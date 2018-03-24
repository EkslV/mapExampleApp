package com.eksler.vadim.mapapplication.presentation.presenter;

import com.eksler.vadim.mapapplication.model.CustomMarker;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.Cluster;

/**
 * Created by vadimeksler on 22/03/2018.
 */

public interface IMapsPresenter {
    void addButtonClicked();
    void markerNameAdded(String markerName, LatLng target);
    void onClusterClicked(Cluster<CustomMarker> cluster);
    void closeDialog();
    void setupMap();
}
