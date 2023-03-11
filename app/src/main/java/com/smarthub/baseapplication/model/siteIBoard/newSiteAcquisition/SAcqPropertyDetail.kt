package com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition

data class SAcqPropertyDetail(
    val Direction: List<Int>,
    val FsDistance: String,
    val FsPhoneNo: String,
    val GateAndFence: Int,
    val NearbyFireStation: String,
    val NearbyPoliceStation: String,
    val OtherOperator: Int,
    val OtherOperatorName: String,
    val Potentialthreat: List<Int>,
    val PsDistance: String,
    val PsPhoneNo: String,
    val Remark: String,
    val SAcqBuildingDetail: ArrayList<SAcqBuildingDetail>,
    val SAcqLandDetail: ArrayList<SAcqLandDetail>,
    val SiteAccessWay: Int,
    val Siteaddress: List<Int>,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)