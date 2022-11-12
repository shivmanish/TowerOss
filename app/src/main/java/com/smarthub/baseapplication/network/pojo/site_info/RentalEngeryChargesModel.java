package com.smarthub.baseapplication.network.pojo.site_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RentalEngeryChargesModel implements Serializable {
    @SerializedName("EbPaymentfrequency")
    @Expose
    private SiteInfoStatusData ebPaymentfrequency;
    @SerializedName("RentalPaymentfrequency")
    @Expose
    private SiteInfoStatusData rentalPaymentfrequency;

    public SiteInfoStatusData getEbPaymentfrequency() {
        return ebPaymentfrequency;
    }

    public void setEbPaymentfrequency(SiteInfoStatusData ebPaymentfrequency) {
        this.ebPaymentfrequency = ebPaymentfrequency;
    }

    public SiteInfoStatusData getRentalPaymentfrequency() {
        return rentalPaymentfrequency;
    }

    public void setRentalPaymentfrequency(SiteInfoStatusData rentalPaymentfrequency) {
        this.rentalPaymentfrequency = rentalPaymentfrequency;
    }

    public SiteInfoStatusData getInvoicePaymentStatus() {
        return invoicePaymentStatus;
    }

    public void setInvoicePaymentStatus(SiteInfoStatusData invoicePaymentStatus) {
        this.invoicePaymentStatus = invoicePaymentStatus;
    }

    @SerializedName("InvoicePaymentStatus")
    @Expose
    private SiteInfoStatusData invoicePaymentStatus;

}
