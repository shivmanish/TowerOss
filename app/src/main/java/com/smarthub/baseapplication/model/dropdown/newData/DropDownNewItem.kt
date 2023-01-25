package com.smarthub.baseapplication.model.dropdown.newData

import com.smarthub.baseapplication.model.dropdown.DropDownItem

data class  DropDownNewItem(
    val data: List<DropDownItem>,
    val name: String,
    val success: Boolean
)