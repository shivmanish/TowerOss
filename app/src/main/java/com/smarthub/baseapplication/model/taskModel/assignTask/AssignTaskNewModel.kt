package com.smarthub.baseapplication.model.taskModel.assignTask

import com.smarthub.baseapplication.utils.AppController

data class AssignTaskNewModel(
    var assigntask: Assigntask?=null,
    var ownername: String?=AppController.getInstance().ownerName
)