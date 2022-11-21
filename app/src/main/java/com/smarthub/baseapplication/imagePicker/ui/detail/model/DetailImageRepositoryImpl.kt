package com.smarthub.baseapplication.imagePicker.ui.detail.model

import android.net.Uri
import com.smarthub.baseapplication.imagePicker.datasource.FishBunDataSource

class DetailImageRepositoryImpl(private val fishBunDataSource: FishBunDataSource) :
    DetailImageRepository {
    override fun getPickerImage(index: Int) = fishBunDataSource.getPickerImages().getOrNull(index)

    override fun getPickerImages(): List<Uri> = fishBunDataSource.getPickerImages()

    override fun isSelected(imageUri: Uri) =
        fishBunDataSource.getSelectedImageList().contains(imageUri)

    override fun getImageIndex(imageUri: Uri) =
        fishBunDataSource.getSelectedImageList().indexOf(imageUri)


    override fun selectImage(imageUri: Uri) {
        fishBunDataSource.selectImage(imageUri)
    }

    override fun unselectImage(imageUri: Uri) {
        fishBunDataSource.unselectImage(imageUri)
    }

    override fun getImageAdapter() = fishBunDataSource.getImageAdapter()

    override fun isFullSelected(): Boolean =
        fishBunDataSource.getSelectedImageList().size == fishBunDataSource.getMaxCount()

    override fun checkForFinish(): Boolean =
        fishBunDataSource.getIsAutomaticClose() && isFullSelected()

    override fun getMessageLimitReached() = fishBunDataSource.getMessageLimitReached()
    override fun getMaxCount() = fishBunDataSource.getMaxCount()

    override fun getDetailPickerViewData() = fishBunDataSource.getDetailViewData()
}