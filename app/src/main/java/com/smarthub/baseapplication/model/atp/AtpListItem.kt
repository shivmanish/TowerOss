package com.smarthub.baseapplication.model.atp

data class AtpListItem(
    val sub_title: AtpHeaderStatus,
    val title: List<AtpHeaderTitle>
)