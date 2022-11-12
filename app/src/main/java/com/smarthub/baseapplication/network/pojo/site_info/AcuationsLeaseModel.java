package com.smarthub.baseapplication.network.pojo.site_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AcuationsLeaseModel implements Serializable {



    @SerializedName("RequesterCompany")
    @Expose
    private SiteInfoStatusData requesterCompany;

     @SerializedName("ProposedSectorcount")
    @Expose
    private SiteInfoStatusData proposedSectorcounty;

   @SerializedName("ProposedAntenaHeight")
    @Expose
    private SiteInfoStatusData proposedAntenaHeight;
    @SerializedName("ProposedSiteType")
    @Expose
    private SiteInfoStatusData proposedSiteType;

    @SerializedName("Telecomequipmenttype")
    @Expose
    private SiteInfoStatusData telecomequipmenttype;



}
