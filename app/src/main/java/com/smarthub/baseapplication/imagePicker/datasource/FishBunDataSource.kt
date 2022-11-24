package com.smarthub.baseapplication.imagePicker.datasource

import android.net.Uri
import com.smarthub.baseapplication.imagePicker.MimeType
import com.smarthub.baseapplication.imagePicker.adapter.image.ImageAdapter
import com.smarthub.baseapplication.imagePicker.ui.album.model.AlbumMenuViewData
import com.smarthub.baseapplication.imagePicker.ui.album.model.AlbumViewData
import com.smarthub.baseapplication.imagePicker.ui.detail.model.DetailImageViewData
import com.smarthub.baseapplication.imagePicker.ui.picker.model.PickerMenuViewData
import com.smarthub.baseapplication.imagePicker.ui.picker.model.PickerViewData

interface FishBunDataSource {
    fun getSelectedImageList(): List<Uri>
    fun selectImage(imageUri: Uri)
    fun unselectImage(imageUri: Uri)
    fun getPickerImages(): List<Uri>
    fun getMaxCount(): Int
    fun getMinCount(): Int
    fun getIsAutomaticClose(): Boolean
    fun getMessageLimitReached(): String
    fun getMessageNothingSelected(): String
    fun getExceptMimeTypeList(): List<MimeType>
    fun getSpecifyFolderList(): List<String>
    fun getAllViewTitle(): String
    fun getDetailViewData(): DetailImageViewData
    fun getAlbumViewData(): AlbumViewData
    fun getImageAdapter(): ImageAdapter
    fun gatAlbumMenuViewData(): AlbumMenuViewData
    fun getPickerViewData(): PickerViewData
    fun setCurrentPickerImageList(pickerImageList: List<Uri>)
    fun hasCameraInPickerPage(): Boolean
    fun useDetailView(): Boolean
    fun getPickerMenuViewData(): PickerMenuViewData
    fun isStartInAllView(): Boolean
}