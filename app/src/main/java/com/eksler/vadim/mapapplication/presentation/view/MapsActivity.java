package com.eksler.vadim.mapapplication.presentation.view;

import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.eksler.vadim.mapapplication.R;
import com.eksler.vadim.mapapplication.adapter.CustomInfoWindow;
import com.eksler.vadim.mapapplication.model.CustomMarker;
import com.eksler.vadim.mapapplication.presentation.presenter.MapsPresenter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;

public class MapsActivity extends MvpAppCompatActivity implements OnMapReadyCallback, IMapsView,
        View.OnClickListener, ClusterManager.OnClusterClickListener<CustomMarker> {
    private GoogleMap mMap;
    private FloatingActionButton mAddButton;
    private ClusterManager<CustomMarker> mClusterManager;
    private AlertDialog dialog = null;

    @InjectPresenter
    MapsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mAddButton = findViewById(R.id.addButton);
        mAddButton.setOnClickListener(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mClusterManager = new ClusterManager<>(this, mMap);
        mClusterManager.setOnClusterClickListener(this);
        mMap.setOnMarkerClickListener(mClusterManager);
        mMap.setOnCameraIdleListener(mClusterManager);
        mClusterManager.getMarkerCollection().setOnInfoWindowAdapter(new CustomInfoWindow(this));
        mMap.setInfoWindowAdapter(mClusterManager.getMarkerManager());

        // Get from database saved markers and set to map.
        presenter.setupMap();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addButton:
                presenter.addButtonClicked();
                break;
        }
    }

    @Override
    public void showRequestName() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText markerNameEditor = dialogView.findViewById(R.id.markerName);
        dialogBuilder.setTitle(getString(R.string.alert_title));
        dialogBuilder.setPositiveButton(getString(R.string.save), (dialogInterface, i) -> {
            String markerName = String.valueOf(markerNameEditor.getText()).trim();
            if (!markerName.isEmpty()) {
                presenter.markerNameAdded(markerName, mMap.getCameraPosition().target);
            }
        });
        dialogBuilder.setOnCancelListener(dialogInterface -> presenter.closeDialog());

        dialog = dialogBuilder.create();
        dialog.show();
    }

    @Override
    public void showMarker(CustomMarker marker) {
        if (mMap != null && mClusterManager != null) {
            mClusterManager.addItem(marker);
            mClusterManager.cluster();
        }
    }

    @Override
    public void showClusterMarkers(Cluster<CustomMarker> cluster) {
        LatLngBounds.Builder builder = LatLngBounds.builder();
        for (ClusterItem item : cluster.getItems()) {
            builder.include(item.getPosition());
        }
        final LatLngBounds bounds = builder.build();
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));
    }

    @Override
    public void showMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onClusterClick(Cluster<CustomMarker> cluster) {
        presenter.onClusterClicked(cluster);
        return true;
    }

    @Override
    public void closeDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMap = null;
        mClusterManager = null;
    }
}
