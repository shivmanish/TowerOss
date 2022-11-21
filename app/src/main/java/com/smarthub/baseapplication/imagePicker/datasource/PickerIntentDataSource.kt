package com.smarthub.baseapplication.imagePicker.datasource

import com.smarthub.baseapplication.imagePicker.ui.picker.model.AlbumData

interface PickerIntentDataSource {
    fun getAlbumData(): AlbumData?
}