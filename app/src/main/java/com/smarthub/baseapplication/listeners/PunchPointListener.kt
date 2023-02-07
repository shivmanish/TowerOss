package com.smarthub.baseapplication.listeners

import com.smarthub.baseapplication.model.qatcheck.punch_point.PunchPointUpdate
import com.smarthub.baseapplication.model.siteInfo.qat.qat_main.Checkpoint
interface PunchPointListener {
    fun addPunchPoint(item : PunchPointUpdate)
    fun editPunchPoint(item : Checkpoint,pos : Int)
    fun punchPointClicked()
}