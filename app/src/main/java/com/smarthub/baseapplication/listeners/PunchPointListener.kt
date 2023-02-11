package com.smarthub.baseapplication.listeners

import com.smarthub.baseapplication.model.qatcheck.punch_point.PunchPointUpdate
import com.smarthub.baseapplication.model.siteInfo.qat.SaveCheckpointData
import com.smarthub.baseapplication.model.siteInfo.qat.qat_main.Checkpoint
import com.smarthub.baseapplication.model.siteInfo.qat.qat_main.PunchpointData

interface PunchPointListener {
    fun addPunchPoint(item : PunchPointUpdate)
    fun editPunchPoint(item : Checkpoint,pos : Int)
    fun punchPointClicked(data:ArrayList<PunchpointData>)
    fun closedPunchPointClicked(data:ArrayList<PunchpointData>)
    fun savePunchPointData(data:SaveCheckpointData)
}