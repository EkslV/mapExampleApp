package com.eksler.vadim.mapapplication.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.eksler.vadim.mapapplication.App;
import com.eksler.vadim.mapapplication.business.IMapsInteractor;
import com.eksler.vadim.mapapplication.business.IMapsInteractorCallback;
import com.eksler.vadim.mapapplication.di.maps.MapsModule;
import com.eksler.vadim.mapapplication.model.CustomMarker;
import com.eksler.vadim.mapapplication.presentation.view.IMapsView;
import com.firebase.geofire.GeoLocation;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.Cluster;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by vadimeksler on 22/03/2018.
 */

@InjectViewState
public class MapsPresenter extends MvpPresenter<IMapsView> implements IMapsPresenter, IMapsInteractorCallback {

    @Inject
    IMapsInteractor interactor;

    public MapsPresenter() {
        super();
        App.get().addMapsComponent(new MapsModule()).inject(this);
    }

    @Override
    public void addButtonClicked() {
        getViewState().showRequestName();
    }

    @Override
    public void markerNameAdded(String markerName, LatLng target) {
        getViewState().closeDialog();
        CustomMarker customMarker = new CustomMarker(markerName, target.latitude, target.longitude);
        getViewState().showMarker(customMarker);
        saveDataIntoDatabase(customMarker);
    }

    @Override
    public void onClusterClicked(Cluster<CustomMarker> cluster) {
        getViewState().showClusterMarkers(cluster);
    }

    @Override
    public void closeDialog() {
        getViewState().closeDialog();
    }

    @Override
    public void setupMap() {
        interactor.getSavedMarkers(this);

    }

    private void saveDataIntoDatabase(CustomMarker customMarker) {
        interactor.saveMarkerData(customMarker, this);
    }

    @Override
    public void onDestroy() {
        App.get().clearMapsComponent();
        super.onDestroy();
    }

    // Callbacks
    @Override
    public void onSetLocationSuccess() {
        getViewState().showMessage("Data saved");
    }

    @Override
    public void onSetLocationError(String error) {
        getViewState().showMessage(error);
    }

    @Override
    public void onGetDataSuccess(CustomMarker marker) {
        getViewState().showMarker(marker);
    }

    @Override
    public void onGetDataError(String error) {
        getViewState().showMessage(error);
    }
}
