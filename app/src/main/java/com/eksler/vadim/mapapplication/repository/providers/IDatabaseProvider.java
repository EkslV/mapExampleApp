package com.eksler.vadim.mapapplication.repository.providers;

import com.eksler.vadim.mapapplication.model.CustomMarker;
import com.eksler.vadim.mapapplication.repository.IMapsRepositoryCallback;

/**
 * Created by vadimeksler on 22/03/2018.
 */

public interface IDatabaseProvider {
    void saveMarkerData(CustomMarker marker, IMapsRepositoryCallback callback);
    void getGeoLocations(IMapsRepositoryCallback callback);
}
