package com.smarthub.baseapplication.imagePicker.ui.album.model

import android.graphics.drawable.Drawable

data class AlbumMenuViewData(
    val hasButtonInAlbumActivity: Boolean,
    val drawableDoneButton: Drawable?,
    val strDoneMenu: String?,
    val colorTextMenu: Int)
