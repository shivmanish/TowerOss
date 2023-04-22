package com.smarthub.baseapplication.model.dropdown.newData

import com.smarthub.baseapplication.model.dropdown.DropDownItem
import com.smarthub.baseapplication.utils.DropDowns

data class  DropDownNewItem(
    val data: List<DropDownItem>,
    val name: String?="name",
    val success: Boolean
)