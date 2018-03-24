package com.eksler.vadim.mapapplication.business;

import com.eksler.vadim.mapapplication.model.CustomMarker;
import com.eksler.vadim.mapapplication.repository.IMapsRepository;
import com.eksler.vadim.mapapplication.repository.IMapsRepositoryCallback;
import com.firebase.geofire.GeoLocation;
import java.util.Map;


/**
 * Created by vadimeksler on 22/03/2018.
 */

public class MapsInteractor implements IMapsInteractor, IMapsRepositoryCallback {

    IMapsRepository repository;
    private IMapsInteractorCallback mCallback;

    public MapsInteractor(IMapsRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveMarkerData(CustomMarker marker, IMapsInteractorCallback callback) {
        mCallback = callback;
        repository.saveMarkerData(marker, this);
    }

    @Override
    public void getSavedMarkers(IMapsInteractorCallback callback) {
        mCallback = callback;
        repository.getSavedMarkers(this);
    }


    // Callback
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
