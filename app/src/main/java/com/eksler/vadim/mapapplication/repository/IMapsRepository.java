package com.eksler.vadim.mapapplication.repository;

import com.eksler.vadim.mapapplication.model.CustomMarker;

/**
 * Created by vadimeksler on 22/03/2018.
 */

public interface IMapsRepository {
    void saveMarkerData(CustomMarker marker, IMapsRepositoryCallback callback);
    void getSavedMarkers(IMapsRepositoryCallback callback);
}
