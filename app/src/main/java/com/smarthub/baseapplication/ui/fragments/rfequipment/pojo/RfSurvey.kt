package com.smarthub.baseapplication.ui.fragments.rfequipment.pojo

data class RfSurvey(
    var RfSurvey1: ArrayList<RfSurvey1>? = null,
    var RfSurvey2: ArrayList<RfSurvey2>? = null,
    var RfSurveyAssignTeam: ArrayList<RfSurveyAssignTeam>? = null,
    var created_at: String? = null,
    var created_by: Int? = null,
    var id: Int? = null,
    var isActive: Boolean? = null,
    var licenseeCompany: Int? = null,
    var modified_at: String? = null,
    var modified_by: Int? = null,
    var remark: Any? = null
)