package com.smarthub.baseapplication.model.siteInfo

import com.smarthub.baseapplication.model.siteInfo.opcoInfo.OpcoDataItem
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllData

data class BasicInfoModelItem(
    val Basicinfo: List<SiteBasicinfo>,
    val Filesstorage: List<Any>,
    val GeoCondition: List<GeoCondition>,
    val OperationalInfo: List<OperationalInfo>,
    val Operator: List<OpcoDataItem>,
    val SafetyAndAccess: List<SafetyAndAcces>,
    val created_at: String,
    val ServiceRequestMain: ServiceRequestAllData,
    val id: Int,
    val National: String,
    val Region: String,
    val State: String,
    val isActive: Boolean,
    val modified_at: String,
    val ownername: List<Any>,
    val siteacquisition: List<Any>,
    val utilities: List<Any>
)