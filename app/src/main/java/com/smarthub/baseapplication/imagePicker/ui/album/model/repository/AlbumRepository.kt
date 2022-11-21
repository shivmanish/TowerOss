package com.smarthub.baseapplication.imagePicker.ui.album.model.repository

import android.net.Uri
import com.smarthub.baseapplication.imagePicker.adapter.image.ImageAdapter
import com.smarthub.baseapplication.imagePicker.ui.album.model.Album
import com.smarthub.baseapplication.imagePicker.ui.album.model.AlbumMenuViewData
import com.smarthub.baseapplication.imagePicker.ui.album.model.AlbumMetaData
import com.smarthub.baseapplication.imagePicker.ui.album.model.AlbumViewData
import com.smarthub.baseapplication.imagePicker.util.future.CallableFutureTask

interface AlbumRepository {
    fun getAlbumList(): CallableFutureTask<List<Album>>

    fun getAlbumMetaData(albumId: Long): CallableFutureTask<AlbumMetaData>

    fun getAlbumViewData(): AlbumViewData

    fun getImageAdapter(): ImageAdapter

    fun getSelectedImageList(): List<Uri>

    fun getAlbumMenuViewData(): AlbumMenuViewData

    fun getMessageNotingSelected(): String

    fun getMinCount(): Int

    fun getDefaultSavePath(): String?
}