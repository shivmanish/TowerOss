package com.smarthub.baseapplication.model.taskModel.dropdown

import com.smarthub.baseapplication.ui.dynamic.TitleItem

data class CollectionItem(
    val checked: Boolean,
    val disabled: Boolean,
    val id: Int,
    val name: String,
    var list : ArrayList<TitleItem>
)