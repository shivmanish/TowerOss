package com.smarthub.baseapplication.network.pojo.site_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GeoConditionModel implements Serializable {

    @SerializedName("Potentialthreat")
    @Expose
    private SiteInfoStatusData potentialthreat;

    @SerializedName("Windzone")
    @Expose
    private SiteInfoStatusData windzone;

    @SerializedName("Seismiczone")
    @Expose
    private SiteInfoStatusData seismiczone;

    @SerializedName("Floodzone")
    @Expose
    private SiteInfoStatusData floodzone;

    @SerializedName("Terraintype")
    @Expose
    private SiteInfoStatusData terraintype;

    public SiteInfoStatusData getPotentialthreat() {
        return potentialthreat;
    }

    public void setPotentialthreat(SiteInfoStatusData potentialthreat) {
        this.potentialthreat = potentialthreat;
    }

    public SiteInfoStatusData getWindzone() {
        return windzone;
    }

    public void setWindzone(SiteInfoStatusData windzone) {
        this.windzone = windzone;
    }

    public SiteInfoStatusData getSeismiczone() {
        return seismiczone;
    }

    public void setSeismiczone(SiteInfoStatusData seismiczone) {
        this.seismiczone = seismiczone;
    }

    public SiteInfoStatusData getFloodzone() {
        return floodzone;
    }

    public void setFloodzone(SiteInfoStatusData floodzone) {
        this.floodzone = floodzone;
    }

    public SiteInfoStatusData getTerraintype() {
        return terraintype;
    }

    public void setTerraintype(SiteInfoStatusData terraintype) {
        this.terraintype = terraintype;
    }
}
