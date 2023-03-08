package com.smarthub.baseapplication.model.serviceRequest.acquisitionSurvey

data class ASAquisitionSurvey(
    var ASAquisitionSurveyBuildingDetail: ArrayList<ASAquisitionSurveyBuildingDetail>? = null,
    var ASBoundryStructureDetail: ArrayList<ASBoundryStructureDetail>? = null,
    var ASDetail: List<ASDetail>? = null,
    var ASPropertyOwnerDetail: List<ASPropertyOwnerDetail>? = null,
    var created_at: String? = null,
    var id: String? = null,
    var isActive: String? = null,
    var modified_at: String? = null
)