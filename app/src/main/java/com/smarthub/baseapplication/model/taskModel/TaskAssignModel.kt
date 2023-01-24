package com.smarthub.baseapplication.model.taskModel

import com.smarthub.baseapplication.utils.AppController

data class TaskAssignModel(
    val updateprocesstask: Updateprocesstask,
    val ownername: String ?= AppController.getInstance().ownerName
)