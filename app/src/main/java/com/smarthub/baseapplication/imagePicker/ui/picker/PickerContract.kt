package com.smarthub.baseapplication.imagePicker.ui.picker

import android.net.Uri
import com.smarthub.baseapplication.imagePicker.adapter.image.ImageAdapter
import com.smarthub.baseapplication.imagePicker.ui.picker.model.PickerListItem
import com.smarthub.baseapplication.imagePicker.ui.picker.model.PickerMenuViewData
import com.smarthub.baseapplication.imagePicker.ui.picker.model.PickerViewData

interface PickerContract {
    interface View {
        fun showImageList(
            imageList: List<PickerListItem>,
            adapter: ImageAdapter,
            hasCameraInPickerPage: Boolean
        )
        fun takePicture(saveDir: String)
        fun setToolbarTitle(
            pickerViewData: PickerViewData,
            selectedCount: Int,
            albumName: String
        )
        fun initToolBar(pickerViewData: PickerViewData)
        fun initRecyclerView(pickerViewData: PickerViewData)
        fun showLimitReachedMessage(messageLimitReached: String)
        fun showMinimumImageMessage(currentSelectedCount: Int)
        fun showNothingSelectedMessage(messageNotingSelected: String)
        fun onCheckStateChange(position: Int, image: PickerListItem.Image)
        fun showDetailView(position: Int)
        fun finishActivity()
        fun finishActivityWithResult(selectedImages: List<Uri>)
        fun takeANewPictureWithFinish(position: Int, addedImageList: List<Uri>)
        fun addImage(pickerListImage: PickerListItem.Image)
        fun onSuccessTakePicture()
    }

    interface Presenter {
        fun takePicture()
        fun getAddedImagePathList(): List<Uri>
        fun addAddedPath(addedImagePath: Uri)
        fun addAllAddedPath(addedImagePathList: List<Uri>)
        fun release()
        fun successTakePicture(addedImagePath: Uri)
        fun getPickerListItem()
        fun transImageFinish()
        fun getDesignViewData()
        fun onClickThumbCount(position: Int)
        fun onClickImage(position: Int)
        fun onDetailImageActivityResult()
        fun getPickerMenuViewData(callback: (PickerMenuViewData) -> Unit)
        fun onClickMenuDone()
        fun onClickMenuAllDone()
        fun onSuccessTakePicture()
    }
}