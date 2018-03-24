package com.eksler.vadim.mapapplication.business;

import com.eksler.vadim.mapapplication.model.CustomMarker;

/**
 * Created by vadimeksler on 22/03/2018.
 */

public interface IMapsInteractor {
    void saveMarkerData(CustomMarker marker, IMapsInteractorCallback callback);
    void getSavedMarkers(IMapsInteractorCallback callback);
}
