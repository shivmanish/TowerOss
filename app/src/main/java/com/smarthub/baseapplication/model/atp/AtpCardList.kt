package com.smarthub.baseapplication.model.atp

data class AtpCardList(
    val atpHeaderStatus: AtpHeaderStatus,
    val list: List<AtpListItem>
)