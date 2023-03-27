package com.smarthub.baseapplication.model.workflow

import com.smarthub.baseapplication.utils.AppController

class TaskDataUpdateModel{
    var ModuleId: Int?=null
    var ModuleName: String?=null
    var ownername: String=AppController.getInstance().ownerName
    var updatemodule: String?=null
}