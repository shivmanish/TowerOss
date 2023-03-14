package com.smarthub.baseapplication.ui.dialog.utils

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.DgEqipmentDialogLayoutBinding
import com.smarthub.baseapplication.databinding.DialogAddImageBinding
import com.smarthub.baseapplication.databinding.MaintanaceBottomSheetBinding
import com.smarthub.baseapplication.databinding.PoDetailsBottomSheetBinding
import com.smarthub.baseapplication.databinding.ServicesDetailsBottomSheetBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.imagePicker.BaseActivity
import com.smarthub.baseapplication.imagePicker.FishBun
import com.smarthub.baseapplication.imagePicker.adapter.image.impl.GlideAdapter
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.AddAttachmentModel
import com.smarthub.baseapplication.ui.utilites.adapter.SMPSViewRecyclerAdapter
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.FileUtilities
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel
import java.io.File


class AttachmentDialogBottomSheet(var sourceSchemaName:String,var sourceSchemaId:String) : BaseBottomSheetDialogFragment() {

    lateinit var binding: DialogAddImageBinding
    lateinit var homeViewModel : HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        binding = DialogAddImageBinding.inflate(inflater)
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog!!.window!!.setLayout(width, height)
        dialog!!.window!!.setGravity(Gravity.BOTTOM)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.window!!.setWindowAnimations(R.style.DialogAnimation)
        return binding.root
    }

    override fun getTheme(): Int {
        return R.style.FullDialog
    }

    override fun isCancelable(): Boolean {
        return true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cancel.setOnClickListener{
            dialog?.dismiss()
        }

        binding.submit.setOnClickListener {
            val model = AddAttachmentModel()
            model.file = itemPath
            model.sourceSchemaName = sourceSchemaName
            model.sourceSchemaId = sourceSchemaId
            showLoader()
            homeViewModel.addAttachmentData(model)
        }

        binding.camera.setOnClickListener {
            if (Utils.verifyCameraPermissions(requireActivity()) == true) {
                openCamera()
            }
        }

        binding.openGallery.setOnClickListener {
            if (Utils.verifyMediaImagesPermissions(requireActivity()) == true) {
                FishBun.with(this)
                    .setImageAdapter(GlideAdapter())
                    .setMinCount(1)
                    .setMaxCount(1)
                    .setActionBarColor(Color.parseColor("#ffffff"), Color.parseColor("#ffffff"), true)
                    .setActionBarTitleColor(Color.parseColor("#000000"))
                    .setAlbumSpanCount(1, 2)
                    .setButtonInAlbumActivity(true)
                    .setReachLimitAutomaticClose(false)
                    .setHomeAsUpIndicatorDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_arrow_back_24))
                    .setAllViewTitle("All of your photos")
                    .setActionBarTitle("Select Images")
                    .textOnImagesSelectionLimitReached("You can't select any more.")
                    .textOnNothingSelected("Please Select some images")
                    .startAlbumWithActivityResultCallback(startForImageResult)
            }
        }
        if (homeViewModel.addAttachmentModel?.hasActiveObservers() == true)
            homeViewModel.addAttachmentModel?.removeObservers(this)
        homeViewModel.addAttachmentModel?.observe(viewLifecycleOwner){
            hideLoader()
            if (it.status == Resource.Status.SUCCESS && it.data!=null){
                dismiss()
                Toast.makeText(requireContext(),"file uploaded successfully",Toast.LENGTH_SHORT).show()
            }else{
                AppLogger.log("something wrong:"+it.message)
            }
        }
    }

    var ORIGINAL_IMAGE_PATH:String?=null
    private fun openCamera() {
        val uri: Uri
        if (Utils.hasCamera(requireContext())) {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            try {
                val createImageFile = File(requireActivity().cacheDir,"temp.jpg")
                uri = if (Build.VERSION.SDK_INT >= 24) {
                    FileProvider.getUriForFile(requireContext(), "${requireActivity().packageName}.provider", createImageFile)
                } else {
                    Uri.fromFile(createImageFile)
                }
                ORIGINAL_IMAGE_PATH = createImageFile.absolutePath
                intent.putExtra("output", uri)
                startForPIPCameraResult.launch(intent)
            } catch (e: Exception) {
                e.printStackTrace()
                AppLogger.log("Exception :" + e.localizedMessage)
                Toast.makeText(
                    requireContext(),
                    "Error occurred while trying to open camera, please try again",
                    Toast.LENGTH_LONG
                ).show()
            }
        } else {
            Toast.makeText(requireContext(), "Camera not found on this device", Toast.LENGTH_LONG).show()
        }
    }

    private val startForPIPCameraResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        when (result.resultCode) {
            Activity.RESULT_OK -> {
                try {
                    val intent2 = Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE")
                    val file = File(ORIGINAL_IMAGE_PATH)
                    if (file.exists()) {
                        Log.v("FILE_TEST", "file not null on " + file.absolutePath)
                    } else {
                        Log.v("FILE_TEST", "file is null")
                    }
                    val uriForFile = FileProvider.getUriForFile(requireContext(), "${requireActivity().packageName}.provider", file)
                    intent2.data = uriForFile
                    requireActivity().sendBroadcast(intent2)
                    itemPath = FileUtilities.getRealPath(context, uriForFile)
                    AppLogger.log("itemPath${itemPath}")
                    Glide
                        .with(requireActivity())
                        .load(itemPath)
                        .into(binding.imageIcon)
                } catch (e: Exception) {
                    e.printStackTrace()
                    AppLogger.log("Exception :" + e.localizedMessage)
                    Toast.makeText(
                        requireContext(),
                        "Error occurred while trying to take a picture, please try again",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            else -> {
                Toast.makeText(requireContext(), "Image not selected", Toast.LENGTH_SHORT).show()
            }
        }
    }


    var itemPath =""
    private val startForImageResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        when (result.resultCode) {
            Activity.RESULT_OK -> {
                val fileUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    result.data?.getParcelableExtra(FishBun.INTENT_PATH,Uri::class.java)
                } else {
                    result.data?.getParcelableExtra(FishBun.INTENT_PATH)
                }
                itemPath = FileUtilities.getRealPath(context, fileUri)
                AppLogger.log("itemPath${itemPath}")
                Glide
                    .with(requireActivity())
                    .load(itemPath)
                    .into(binding.imageIcon)
            }
            else -> {
                Toast.makeText(requireContext(), "Image picker cancelled", Toast.LENGTH_SHORT).show()
            }
        }
    }


}