package com.smarthub.baseapplication.imagePicker.datasource

import android.net.Uri
import com.smarthub.baseapplication.imagePicker.MimeType
import com.smarthub.baseapplication.imagePicker.ui.album.model.Album
import com.smarthub.baseapplication.imagePicker.ui.album.model.AlbumMetaData
import com.smarthub.baseapplication.imagePicker.util.future.CallableFutureTask

interface ImageDataSource {
    fun getAlbumList(
        allViewTitle: String,
        exceptMimeTypeList: List<MimeType>,
        specifyFolderList: List<String>
    ): CallableFutureTask<List<Album>>

    fun getAllBucketImageUri(
        bucketId: Long,
        exceptMimeTypeList: List<MimeType>,
        specifyFolderList: List<String>
    ): CallableFutureTask<List<Uri>>

    fun getAlbumMetaData(
        bucketId: Long,
        exceptMimeTypeList: List<MimeType>,
        specifyFolderList: List<String>
    ): CallableFutureTask<AlbumMetaData>

    fun getDirectoryPath(bucketId: Long): CallableFutureTask<String>

    fun addAddedPath(addedImage: Uri)

    fun addAllAddedPath(addedImageList: List<Uri>)

    fun getAddedPathList(): List<Uri>
}