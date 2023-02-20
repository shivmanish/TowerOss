package com.example.trackermodule.server;

public class Steps {
    private Location start_location;
    private Location end_location;
    private OverviewPolyLine polyline;
    private Distance distance;

    public Location getStart_location() {
        return start_location;
    }

    public Distance getDistance() {
        return distance;
    }

    public Location getEnd_location() {
        return end_location;
    }

    public OverviewPolyLine getPolyline() {
        return polyline;
    }
}
