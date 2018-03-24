package com.eksler.vadim.mapapplication.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

/**
 * Created by vadimeksler on 22/03/2018.
 */

public class CustomMarker implements ClusterItem {

    private final LatLng mPosition;
    private String markerName;

    public CustomMarker(String name, double lat, double lng) {
        this.markerName = name;
        mPosition = new LatLng(lat, lng);
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }

    @Override
    public String getTitle() {
        return markerName;
    }

    @Override
    public String getSnippet() {
        return "";
    }
}
