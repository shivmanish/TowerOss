package com.smarthub.baseapplication.ui.fragments.rfequipment.pojo

data class RfSurveyAssignTeam(
    var Department: List<Int>? = null,
    var ExecutiveMobile: String? = null,
    var ExecutiveName: String? = null,
    var GeographyLevel: String? = null,
    var created_at: String? = null,
    var created_by: Int? = null,
    var id: Int? = null,
    var isActive: Boolean? = null,
    var licenseeCompany: Int? = null,
    var modified_at: String? = null,
    var modified_by: Int? = null,
    var remark: String? = null
)