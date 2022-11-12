package com.smarthub.baseapplication.network.pojo.site_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ComercialModel implements Serializable {


    @SerializedName("Sodetails")
    @Expose
    private SiteSodetailsModel sodetails;

    @SerializedName("ColocationFee")
    @Expose
    private ColocationFeeModel colocationFee;

    public SiteSodetailsModel getSodetails() {
        return sodetails;
    }

    public void setSodetails(SiteSodetailsModel sodetails) {
        this.sodetails = sodetails;
    }

    public ColocationFeeModel getColocationFee() {
        return colocationFee;
    }

    public void setColocationFee(ColocationFeeModel colocationFee) {
        this.colocationFee = colocationFee;
    }

    public RentalEngeryChargesModel getRentalEngeryCharges() {
        return rentalEngeryCharges;
    }

    public void setRentalEngeryCharges(RentalEngeryChargesModel rentalEngeryCharges) {
        this.rentalEngeryCharges = rentalEngeryCharges;
    }

    @SerializedName("RentalEngeryCharges")
    @Expose
    private RentalEngeryChargesModel rentalEngeryCharges;


}
