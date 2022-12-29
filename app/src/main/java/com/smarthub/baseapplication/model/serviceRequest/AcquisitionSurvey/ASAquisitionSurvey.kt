package com.smarthub.baseapplication.model.serviceRequest.AcquisitionSurvey

data class ASAquisitionSurvey(
    val ASAquisitionSurveyBuildingDetail: List<ASAquisitionSurveyBuildingDetail>,
    val ASBoundryStructureDetail: List<ASBoundryStructureDetail>,
    val ASDetail: List<ASDetail>,
    val ASPropertyOwnerDetail: List<ASPropertyOwnerDetail>,
    val created_at: String,
    val id: String,
    val isActive: String,
    val modified_at: String
)