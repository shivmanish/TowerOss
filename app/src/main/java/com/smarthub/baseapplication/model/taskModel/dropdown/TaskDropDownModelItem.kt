package com.smarthub.baseapplication.model.taskModel.dropdown

data class TaskDropDownModelItem(
    val checked: Boolean,
    val id: Int,
    val name: String,
    val tabs: ArrayList<CollectionItem>
)