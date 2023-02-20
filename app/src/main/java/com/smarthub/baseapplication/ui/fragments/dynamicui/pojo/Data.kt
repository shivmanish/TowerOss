package com.smarthub.baseapplication.ui.fragments.dynamicui.pojo

import com.smarthub.baseapplication.ui.dynamic.TitleItem

data class Data(
    val tab_id: String,
    val tab_titel: String,
    val tabData: ArrayList<TitleItem>
)