package com.smarthub.baseapplication.model.siteInfo

data class RfEquipment(
    val Band: List<Band>,
    val Equipementname: String,
    val InstallationDate: String,
    val Make: String,
    val Model: String,
    val OemCompany: List<OemCompany>,
    val OperationStatus: List<OperationStatu>,
    val OwnerCompany: List<Any>,
    val PowerRating: String,
    val RackNumber: String,
    val RackSpaceUsed: List<RackSpaceUsed>,
    val Remarks: String,
    val RfEquipmentAttachments: Any,
    val SerialNumber: String,
    val Technology: List<Technology>,
    val Usagetype: List<Usagetype>,
    val Weight: String,
    val created_at: String,
    val id: Int,
    val isActive: Boolean,
    val modified_at: String
)