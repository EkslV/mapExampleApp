package com.eksler.vadim.mapapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.eksler.vadim.mapapplication.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by vadimeksler on 22/03/2018.
 */

public class CustomInfoWindow implements GoogleMap.InfoWindowAdapter {

    private Context context;

    public CustomInfoWindow(Context context) {
        this.context = context;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View view = ((Activity)context).getLayoutInflater().inflate(R.layout.custom_infowindow, null);
        TextView markerName = view.findViewById(R.id.markerName);
        TextView location = view.findViewById(R.id.markerLocation);
        markerName.setText(marker.getTitle().trim());
        location.setText(marker.getPosition().toString());
        return view;
    }
}
