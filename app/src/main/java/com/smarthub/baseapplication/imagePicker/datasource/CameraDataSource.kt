package com.smarthub.baseapplication.imagePicker.datasource

interface CameraDataSource {
    fun getCameraPath(): String
    fun getPicturePath(): String?
}