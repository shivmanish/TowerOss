package com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition

data class SAcqPropertyDetail(
    var Direction: ArrayList<Int>,
    var FsDistance: String,
    var FsPhoneNo: String,
    var GateAndFence: Int,
    var NearbyFireStation: String,
    var NearbyPoliceStation: String,
    var OtherOperator: Int,
    var OtherOperatorName: String,
    var Potentialthreat: ArrayList<Int>,
    var PsDistance: String,
    var PsPhoneNo: String,
    var Remark: String,
    var SAcqBuildingDetail: ArrayList<SAcqBuildingDetail>,
    var SAcqLandDetail: ArrayList<SAcqLandDetail>,
    var SiteAccessWay: Int,
    var Siteaddress: ArrayList<Int>,
//    var created_at: String,
    var id: Int?=null,
//    var isActive: Boolean,
//    var modified_at: String
)