package com.smarthub.baseapplication.imagePicker.ui.album.listener

import com.smarthub.baseapplication.imagePicker.ui.album.model.Album

interface AlbumClickListener {
    fun onAlbumClick(position: Int, album: Album)
}