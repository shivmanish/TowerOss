package com.example.trackermodule.server;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DirectionResults {
    @SerializedName("routes")
    private List<Route> routes;

    public List<Route> getRoutes() {
        return routes;
    }}

