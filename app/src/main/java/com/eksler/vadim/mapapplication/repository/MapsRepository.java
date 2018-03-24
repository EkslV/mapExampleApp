package com.eksler.vadim.mapapplication.repository;

import com.eksler.vadim.mapapplication.model.CustomMarker;
import com.eksler.vadim.mapapplication.repository.providers.IDatabaseProvider;

/**
 * Created by vadimeksler on 22/03/2018.
 *
 */

public class MapsRepository implements IMapsRepository, IMapsRepositoryCallback {

    private IDatabaseProvider databaseProvider;
    private IMapsRepositoryCallback mCallback;

    public MapsRepository(IDatabaseProvider provider) {
        this.databaseProvider = provider;
    }

    @Override
    public void saveMarkerData(CustomMarker marker, IMapsRepositoryCallback callback) {
        mCallback = callback;
        databaseProvider.saveMarkerData(marker, this);
    }

    @Override
    public void getSavedMarkers(IMapsRepositoryCallback callback) {
        mCallback = callback;
        databaseProvider.getGeoLocations(this);
    }

    // Callbacks
    @Override
    public void onSetLocationSuccess() {
        mCallback.onSetLocationSuccess();
    }

    @Override
    public void onSetLocationError(String error) {
        mCallback.onSetLocationError(error);
    }

    @Override
    public void onGetDataSuccess(CustomMarker marker) {
        mCallback.onGetDataSuccess(marker);
    }

    @Override
    public void onGetDataError(String error) {
        mCallback.onGetDataError(error);
    }
}
