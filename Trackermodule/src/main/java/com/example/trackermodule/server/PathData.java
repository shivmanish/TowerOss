package com.example.trackermodule.server;

import com.google.android.gms.maps.model.LatLng;

public class PathData {

    public LatLng start_location;
    public LatLng end_location;

    public PathData(LatLng start_location, LatLng end_location) {
        this.start_location = start_location;
        this.end_location = end_location;
    }
}
