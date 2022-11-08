package com.smarthub.baseapplication.ui.basic_info

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import com.smarthub.baseapplication.databinding.ActivityAddImageBinding


class AddImage : AppCompatActivity() {
    lateinit var binding : ActivityAddImageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddImageBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        initViews()

    }
    private fun initViews(){
        binding.openGallery.setOnClickListener{
            val i = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI
            )
            val ACTIVITY_SELECT_IMAGE = 1234
            startActivityForResult(i, ACTIVITY_SELECT_IMAGE)
        }
    }
}