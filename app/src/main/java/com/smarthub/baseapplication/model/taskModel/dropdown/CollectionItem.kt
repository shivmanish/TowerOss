package com.smarthub.baseapplication.model.taskModel.dropdown

data class CollectionItem(
    val checked: Boolean,
    val disabled: Boolean,
    val id: Int,
    val name: String,
    var list : ArrayList<String>
)