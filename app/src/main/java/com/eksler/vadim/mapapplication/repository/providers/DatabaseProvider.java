package com.eksler.vadim.mapapplication.repository.providers;

import com.eksler.vadim.mapapplication.model.CustomMarker;
import com.eksler.vadim.mapapplication.repository.IMapsRepositoryCallback;
import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.LocationCallback;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vadimeksler on 22/03/2018.
 */

public class DatabaseProvider implements IDatabaseProvider {

    private static final String PATH = "/geofire";

    DatabaseReference reference = FirebaseDatabase.getInstance().getReference(PATH);
    GeoFire geoFire = new GeoFire(reference);

    /**
     *  Saves into the firebase database marker's data.
     *
     * @param marker
     *  Custom created marker from ui with name, latitude and longitude
     * @param callback
     *  Callback for result
     */
    @Override
    public void saveMarkerData(CustomMarker marker, IMapsRepositoryCallback callback) {
        geoFire.setLocation(marker.getTitle(),
                new GeoLocation(marker.getPosition().latitude, marker.getPosition().longitude),
                (key, error) -> {
                    if (error != null) {
                        callback.onSetLocationError(error.getMessage());
                    } else {
                        callback.onSetLocationSuccess();
                    }
        });
    }

    /**
     * Gets all markers entities.
     *
     * @param callback
     */
    @Override
    public void getGeoLocations(IMapsRepositoryCallback callback) {
        Map<String, GeoLocation> res = new HashMap<>();
        Query query = FirebaseDatabase.getInstance().getReference().child(PATH);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String mKey = snapshot.getKey();
                        geoFire.getLocation(mKey, new LocationCallback() {
                            @Override
                            public void onLocationResult(String key, GeoLocation location) {
                                res.put(mKey, location);
                                callback.onGetDataSuccess(new CustomMarker(mKey, location.latitude, location.longitude));
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                callback.onGetDataError(databaseError.getMessage());
                            }
                        });

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.onGetDataError(databaseError.getMessage());
            }
        });
    }
}
