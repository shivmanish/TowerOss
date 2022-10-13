package com.smarthub.baseapplication.listeners

import com.smarthub.baseapplication.adapter.AtpCardListAdapter

interface QatListListener {
    fun addNewClicked(adapter : AtpCardListAdapter)
    fun itemClicked()
    fun cardClicked()
}