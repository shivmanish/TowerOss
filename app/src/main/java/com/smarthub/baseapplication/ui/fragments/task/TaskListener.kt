package com.smarthub.baseapplication.ui.fragments.task

import com.smarthub.baseapplication.model.home.MyTeamTask

interface TaskListener {
    fun closeTask(task : MyTeamTask)
}