package model;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

/**
 * Created by ManasShrestha on 8/2/15.
 */
public class MyLocations implements ClusterItem {
    private final LatLng mPosition;

    public MyLocations(double lat, double lng) {
        mPosition = new LatLng(lat, lng);
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }
}
