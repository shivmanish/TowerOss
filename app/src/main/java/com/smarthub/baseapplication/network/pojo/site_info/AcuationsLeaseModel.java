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


    @SerializedName("City")
    @Expose
    private SiteInfoStatusData city;
    @SerializedName("AcquistionMode")
    @Expose
    private SiteInfoStatusData acquistionMode;
    @SerializedName("Acquisitiontype")
    @Expose
    private SiteInfoStatusData acquisitiontype;
    @SerializedName("VendorName")
    @Expose
    private SiteInfoStatusData vendorName;

    @SerializedName("BuildingBuildType")
    @Expose
    private SiteInfoStatusData buildingBuildType;
    @SerializedName("SiteAccessArea")
    @Expose
    private SiteInfoStatusData siteAccessArea;
    @SerializedName("GateAndFence")
    @Expose
    private SiteInfoStatusData gateAndFence;

    @SerializedName("Potentialthreat")
    @Expose
    private SiteInfoStatusData potentialthreat;

    @SerializedName("NoOfFloors")
    @Expose
    private SiteInfoStatusData noOfFloors;
    @SerializedName("Propertyownershiptype")
    @Expose
    private SiteInfoStatusData propertyownershiptype;
    @SerializedName("PropertyOfferforAcquisition")
    @Expose
    private SiteInfoStatusData propertyOfferforAcquisition;
    @SerializedName("OtherOperatorsonRoof")
    @Expose
    private SiteInfoStatusData OtherOperatorsonRoof;
    @SerializedName("FiberLMCLaying")
    @Expose
    private SiteInfoStatusData fiberLMCLaying;
    @SerializedName("TowerPoleInstallation")
    @Expose
    private SiteInfoStatusData towerPoleInstallation;
    @SerializedName("EBsupplythroBuildingMeter")
    @Expose
    private SiteInfoStatusData ebsupplythroBuildingMeter;
    @SerializedName("Equipementroom")
    @Expose
    private SiteInfoStatusData equipementroom;
    @SerializedName("RequiredAreaAvailable")
    @Expose
    private SiteInfoStatusData requiredAreaAvailable;
    @SerializedName("OverallFeasibility")
    @Expose
    private SiteInfoStatusData overallFeasibility;
    @SerializedName("RegistrationNumber")
    @Expose
    private SiteInfoStatusData registrationNumber;
    @SerializedName("BookingCostCentre")
    @Expose
    private SiteInfoStatusData bookingCostCentre;
    @SerializedName("RentPaymentFrequency")
    @Expose
    private SiteInfoStatusData rentPaymentFrequency;
    @SerializedName("EBInclusiveinRental")
    @Expose
    private SiteInfoStatusData eBInclusiveinRental;
    @SerializedName("EBBillLimit")
    @Expose
    private SiteInfoStatusData eBBillLimit;
    @SerializedName("EBBillingBasis")
    @Expose
    private SiteInfoStatusData eBBillingBasis;
    @SerializedName("PropertyOwnership")
    @Expose
    private SiteInfoStatusData propertyOwnership;
    @SerializedName("PropertyAcquired")
    @Expose
    private SiteInfoStatusData propertyAcquired;

    public SiteInfoStatusData getRequesterCompany() {
        return requesterCompany;
    }

    public void setRequesterCompany(SiteInfoStatusData requesterCompany) {
        this.requesterCompany = requesterCompany;
    }

    public SiteInfoStatusData getProposedSectorcounty() {
        return proposedSectorcounty;
    }

    public void setProposedSectorcounty(SiteInfoStatusData proposedSectorcounty) {
        this.proposedSectorcounty = proposedSectorcounty;
    }

    public SiteInfoStatusData getProposedAntenaHeight() {
        return proposedAntenaHeight;
    }

    public void setProposedAntenaHeight(SiteInfoStatusData proposedAntenaHeight) {
        this.proposedAntenaHeight = proposedAntenaHeight;
    }

    public SiteInfoStatusData getProposedSiteType() {
        return proposedSiteType;
    }

    public void setProposedSiteType(SiteInfoStatusData proposedSiteType) {
        this.proposedSiteType = proposedSiteType;
    }

    public SiteInfoStatusData getTelecomequipmenttype() {
        return telecomequipmenttype;
    }

    public void setTelecomequipmenttype(SiteInfoStatusData telecomequipmenttype) {
        this.telecomequipmenttype = telecomequipmenttype;
    }

    public SiteInfoStatusData getCity() {
        return city;
    }

    public void setCity(SiteInfoStatusData city) {
        this.city = city;
    }

    public SiteInfoStatusData getAcquistionMode() {
        return acquistionMode;
    }

    public void setAcquistionMode(SiteInfoStatusData acquistionMode) {
        this.acquistionMode = acquistionMode;
    }

    public SiteInfoStatusData getAcquisitiontype() {
        return acquisitiontype;
    }

    public void setAcquisitiontype(SiteInfoStatusData acquisitiontype) {
        this.acquisitiontype = acquisitiontype;
    }

    public SiteInfoStatusData getVendorName() {
        return vendorName;
    }

    public void setVendorName(SiteInfoStatusData vendorName) {
        this.vendorName = vendorName;
    }

    public SiteInfoStatusData getBuildingBuildType() {
        return buildingBuildType;
    }

    public void setBuildingBuildType(SiteInfoStatusData buildingBuildType) {
        this.buildingBuildType = buildingBuildType;
    }

    public SiteInfoStatusData getSiteAccessArea() {
        return siteAccessArea;
    }

    public void setSiteAccessArea(SiteInfoStatusData siteAccessArea) {
        this.siteAccessArea = siteAccessArea;
    }

    public SiteInfoStatusData getGateAndFence() {
        return gateAndFence;
    }

    public void setGateAndFence(SiteInfoStatusData gateAndFence) {
        this.gateAndFence = gateAndFence;
    }

    public SiteInfoStatusData getPotentialthreat() {
        return potentialthreat;
    }

    public void setPotentialthreat(SiteInfoStatusData potentialthreat) {
        this.potentialthreat = potentialthreat;
    }

    public SiteInfoStatusData getNoOfFloors() {
        return noOfFloors;
    }

    public void setNoOfFloors(SiteInfoStatusData noOfFloors) {
        this.noOfFloors = noOfFloors;
    }

    public SiteInfoStatusData getPropertyownershiptype() {
        return propertyownershiptype;
    }

    public void setPropertyownershiptype(SiteInfoStatusData propertyownershiptype) {
        this.propertyownershiptype = propertyownershiptype;
    }

    public SiteInfoStatusData getPropertyOfferforAcquisition() {
        return propertyOfferforAcquisition;
    }

    public void setPropertyOfferforAcquisition(SiteInfoStatusData propertyOfferforAcquisition) {
        this.propertyOfferforAcquisition = propertyOfferforAcquisition;
    }

    public SiteInfoStatusData getOtherOperatorsonRoof() {
        return OtherOperatorsonRoof;
    }

    public void setOtherOperatorsonRoof(SiteInfoStatusData otherOperatorsonRoof) {
        OtherOperatorsonRoof = otherOperatorsonRoof;
    }

    public SiteInfoStatusData getFiberLMCLaying() {
        return fiberLMCLaying;
    }

    public void setFiberLMCLaying(SiteInfoStatusData fiberLMCLaying) {
        this.fiberLMCLaying = fiberLMCLaying;
    }

    public SiteInfoStatusData getTowerPoleInstallation() {
        return towerPoleInstallation;
    }

    public void setTowerPoleInstallation(SiteInfoStatusData towerPoleInstallation) {
        this.towerPoleInstallation = towerPoleInstallation;
    }

    public SiteInfoStatusData getEbsupplythroBuildingMeter() {
        return ebsupplythroBuildingMeter;
    }

    public void setEbsupplythroBuildingMeter(SiteInfoStatusData ebsupplythroBuildingMeter) {
        this.ebsupplythroBuildingMeter = ebsupplythroBuildingMeter;
    }

    public SiteInfoStatusData getEquipementroom() {
        return equipementroom;
    }

    public void setEquipementroom(SiteInfoStatusData equipementroom) {
        this.equipementroom = equipementroom;
    }

    public SiteInfoStatusData getRequiredAreaAvailable() {
        return requiredAreaAvailable;
    }

    public void setRequiredAreaAvailable(SiteInfoStatusData requiredAreaAvailable) {
        this.requiredAreaAvailable = requiredAreaAvailable;
    }

    public SiteInfoStatusData getOverallFeasibility() {
        return overallFeasibility;
    }

    public void setOverallFeasibility(SiteInfoStatusData overallFeasibility) {
        this.overallFeasibility = overallFeasibility;
    }

    public SiteInfoStatusData getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(SiteInfoStatusData registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public SiteInfoStatusData getBookingCostCentre() {
        return bookingCostCentre;
    }

    public void setBookingCostCentre(SiteInfoStatusData bookingCostCentre) {
        this.bookingCostCentre = bookingCostCentre;
    }

    public SiteInfoStatusData getRentPaymentFrequency() {
        return rentPaymentFrequency;
    }

    public void setRentPaymentFrequency(SiteInfoStatusData rentPaymentFrequency) {
        this.rentPaymentFrequency = rentPaymentFrequency;
    }

    public SiteInfoStatusData geteBInclusiveinRental() {
        return eBInclusiveinRental;
    }

    public void seteBInclusiveinRental(SiteInfoStatusData eBInclusiveinRental) {
        this.eBInclusiveinRental = eBInclusiveinRental;
    }

    public SiteInfoStatusData geteBBillLimit() {
        return eBBillLimit;
    }

    public void seteBBillLimit(SiteInfoStatusData eBBillLimit) {
        this.eBBillLimit = eBBillLimit;
    }

    public SiteInfoStatusData geteBBillingBasis() {
        return eBBillingBasis;
    }

    public void seteBBillingBasis(SiteInfoStatusData eBBillingBasis) {
        this.eBBillingBasis = eBBillingBasis;
    }

    public SiteInfoStatusData getPropertyOwnership() {
        return propertyOwnership;
    }

    public void setPropertyOwnership(SiteInfoStatusData propertyOwnership) {
        this.propertyOwnership = propertyOwnership;
    }

    public SiteInfoStatusData getPropertyAcquired() {
        return propertyAcquired;
    }

    public void setPropertyAcquired(SiteInfoStatusData propertyAcquired) {
        this.propertyAcquired = propertyAcquired;
    }
}
