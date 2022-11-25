package com.smarthub.baseapplication.ui.basic_info

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ActivityAddImageBinding
import com.smarthub.baseapplication.databinding.FragmentGalleryBinding
import com.smarthub.baseapplication.imagePicker.FishBun
import com.smarthub.baseapplication.imagePicker.adapter.image.impl.GlideAdapter
import com.smarthub.baseapplication.ui.adapter.GridItemAdapter
import com.smarthub.baseapplication.utils.FileUtilities


class SiteImages : AppCompatActivity() {
    lateinit var binding : FragmentGalleryBinding
    lateinit var dialogBinding : ActivityAddImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()

    }
    private fun initViews(){
        binding.back.setOnClickListener {
            finish()
        }
        binding.addImage1.setOnClickListener{
            showExitDialog()
        }

        binding.imageGrid.adapter = GridItemAdapter(ArrayList())
    }

    private fun showExitDialog() {
        val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(this,R.style.FullDialog)
        val inflater = layoutInflater
        val dialogView: View = inflater.inflate(R.layout.activity_add_image, null)
        dialogBinding = ActivityAddImageBinding.bind(dialogView)
        dialogBuilder.setView(dialogView)
        val exitDialog: AlertDialog = dialogBuilder.create()
        dialogBinding.crossDialog.setOnClickListener{
            exitDialog.dismiss();
        }
        dialogBinding.openGallery.setOnClickListener{
            Log.d("status", "Opening gallery")
            pickImages()
        }
        exitDialog.setCancelable(true)
        exitDialog.show()
    }

    private val startForProfileImageResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult -> when (result.resultCode) {
            Activity.RESULT_OK -> {
            var fileUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                result.data?.getParcelableExtra(FishBun.INTENT_PATH,Uri::class.java)
            } else {
                result.data?.getParcelableExtra(FishBun.INTENT_PATH)
            }
            if (fileUri!=null){
                try {
                    val path = FileUtilities().getRealPath(this@SiteImages,fileUri)
                    if (binding.imageGrid.adapter is GridItemAdapter){
                        (binding.imageGrid.adapter as GridItemAdapter).addItem(path)
                    }
                    Log.d("Gallery",path)
                } catch (e: java.lang.Exception) {
                    Log.d("Gallery","e ${e.localizedMessage}")
                    //showToast("error :"+e.localizedMessage)
                }
            }
        }
        else -> {
            Log.d("Gallery","Task Cancelled")
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }
    }
    private fun pickImages(){
//        if (verifyPermissions()!=true) {
//            requestPermission()
//            return
//        }

        FishBun.with(this)
            .setImageAdapter(GlideAdapter())
            .setMaxCount(1)
            .setActionBarColor(Color.parseColor("#ffffff"), Color.parseColor("#ffffff"), true)
            .setActionBarTitleColor(Color.parseColor("#000000"))
            .setAlbumSpanCount(1, 2)
            .setButtonInAlbumActivity(true)
            .setReachLimitAutomaticClose(false)
            .setHomeAsUpIndicatorDrawable(ContextCompat.getDrawable(this, R.drawable.ic_back))
            .setAllViewTitle("All of your photos")
            .setActionBarTitle("FishBun Light")
            .textOnImagesSelectionLimitReached("You can't select any more.")
            .textOnNothingSelected("I need a photo!")
            .startAlbumWithActivityResultCallback(startForProfileImageResult)

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            FishBun.FISHBUN_REQUEST_CODE -> if (resultCode === RESULT_OK) {
                //mSelected = data?.getParcelableArrayListExtra(INTENT_PATH)?: arrayListOf()
            }
        }
    }
    private fun processImages() {
        TODO("Not yet implemented")

    }
}