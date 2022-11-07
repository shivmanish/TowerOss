package com.smarthub.baseapplication.ui.basic_info

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ActivityAddImageBinding
import com.smarthub.baseapplication.databinding.FragmentGalleryBinding
import kotlinx.android.synthetic.main.new_customer_detail_fragment.*
import kotlinx.android.synthetic.main.qat_punch_point_item.view.*
import kotlinx.android.synthetic.main.tab_name_item.view.*


class SiteImages : AppCompatActivity() {
    lateinit var binding : FragmentGalleryBinding
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
        val dialogBuilder: AlertDialog.Builder =
            AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogView: View = inflater.inflate(R.layout.activity_add_image, null)
        val exitDialogBinding: ActivityAddImageBinding = ActivityAddImageBinding.bind(dialogView)
        dialogBuilder.setView(dialogView)
        val exitDialog: AlertDialog = dialogBuilder.create()
        exitDialogBinding.crossDialog.setOnClickListener{
            exitDialog.dismiss();
        }
        exitDialog.setCancelable(true)

        exitDialog.show()
    }
}