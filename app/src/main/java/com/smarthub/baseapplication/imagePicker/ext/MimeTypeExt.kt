@file:JvmName("MimeTypeExt")

package com.smarthub.baseapplication.imagePicker.ext

import com.smarthub.baseapplication.imagePicker.MimeType

fun MimeType.equalsMimeType(mimeType: String) = this.type == mimeType