package com.example.trackermodule.server;

import com.google.gson.annotations.SerializedName;

public class OverviewPolyLine {

    @SerializedName("points")
    public String points;

    public String  getPoints() {
        return points;
    }
}
