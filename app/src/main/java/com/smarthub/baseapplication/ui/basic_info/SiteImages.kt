package com.smarthub.baseapplication.ui.basic_info

import android.Manifest
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ActivityAddImageBinding
import com.smarthub.baseapplication.databinding.FragmentGalleryBinding
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.engine.impl.GlideEngine
import kotlinx.android.synthetic.main.new_customer_detail_fragment.*
import kotlinx.android.synthetic.main.qat_punch_point_item.view.*
import kotlinx.android.synthetic.main.tab_name_item.view.*


class SiteImages : AppCompatActivity() {
    lateinit var binding : FragmentGalleryBinding
    lateinit var dialogBinding : ActivityAddImageBinding
    private val MULTIPLE_IMAGE_PICKER = 100
    lateinit var mSelected: List<Uri>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentGalleryBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        initViews()

    }
    private fun initViews(){
        binding.back.setOnClickListener {
            onBackPressed()
        }
        binding.addImage1.setOnClickListener{
            showExitDialog()
        }
    }
    fun showExitDialog() {
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
    private fun pickImages(){
        if (verifyPermissions()!=true) {
            requestPermission()
            //return
        }
        Log.d("status", "Pick Images")
        Matisse.from(this)
            .choose(MimeType.ofAll())
            .countable(true)
            .maxSelectable(1)
            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
            .thumbnailScale(0.85f)
            .imageEngine(GlideEngine())
            .showPreview(false) // Default is `true`
            .forResult(MULTIPLE_IMAGE_PICKER)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == MULTIPLE_IMAGE_PICKER && resultCode == RESULT_OK) {
            mSelected = ArrayList(Matisse.obtainResult(data))
            processImages()
        }
    }
    private fun processImages() {
        TODO("Not yet implemented")

    }
    private fun requestPermission() {
        val STORAGE_PERMISSIONS = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
        // If permission not granted then ask for permission real time.
        Log.d("status", "Request")
        ActivityCompat.requestPermissions(this, STORAGE_PERMISSIONS, 1)
    }

    fun verifyPermissions(): Boolean? {
        // This will return the current Status
        Log.d("status", "verify")
        var permissionExternalMemory = ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        permissionExternalMemory = ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)
        return permissionExternalMemory == PackageManager.PERMISSION_GRANTED
    }
}