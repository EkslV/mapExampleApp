package com.eksler.vadim.mapapplication.repository;

import com.eksler.vadim.mapapplication.model.CustomMarker;

/**
 * Created by helenaeksler on 24/03/2018.
 */

public interface IMapsRepositoryCallback {
    void onSetLocationSuccess();
    void onSetLocationError(String error);
    void onGetDataSuccess(CustomMarker marker);
    void onGetDataError(String error);
}
