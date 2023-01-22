package com.smarthub.baseapplication.model.taskModel

import com.smarthub.baseapplication.utils.AppController

data class CreateNewTaskModel(
    val Processtemplatecallmanual: List<Processtemplatecallmanual>,
    val ownerName : String ?= AppController.getInstance().ownerName
)