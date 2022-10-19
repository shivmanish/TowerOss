package com.smarthub.baseapplication.listeners

import com.smarthub.baseapplication.ui.adapter.AtpCardListAdapter

interface PunchPointListener {
    fun itemClicked()
    fun addPunchPoint()
    fun punchPointClicked()
}