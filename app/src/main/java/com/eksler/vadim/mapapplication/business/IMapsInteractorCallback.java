package com.eksler.vadim.mapapplication.business;

import com.eksler.vadim.mapapplication.model.CustomMarker;
import com.firebase.geofire.GeoLocation;

import java.util.Map;

/**
 * Created by helenaeksler on 24/03/2018.
 */

public interface IMapsInteractorCallback {
    void onSetLocationSuccess();
    void onSetLocationError(String error);
    void onGetDataSuccess(CustomMarker marker);
    void onGetDataError(String error);
}
