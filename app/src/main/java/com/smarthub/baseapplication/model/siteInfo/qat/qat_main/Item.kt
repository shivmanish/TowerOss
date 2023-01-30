package com.smarthub.baseapplication.model.siteInfo.qat.qat_main

data class Item(
    val QATItem: String,
    val id: String,
    val subitem: List<Subitem>
)