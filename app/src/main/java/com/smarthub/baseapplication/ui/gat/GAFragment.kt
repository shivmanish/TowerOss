package com.smarthub.baseapplication.ui.gat

import android.R.attr
import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.adapter.GADynamicAdapter
import com.smarthub.baseapplication.databinding.FragmentGABinding
import com.smarthub.baseapplication.listeners.AddImageListener


class GAFragment : Fragment() , AddImageListener {
    private var _binding: FragmentGABinding? = null
    private lateinit var gaViewModel: GAViewModel
    private val binding get() = _binding!!
    var list : ArrayList<Any> = ArrayList()
    private val PICK_IMAGE = 12345
    private val TAKE_PICTURE = 6352
    private val REQUEST_CAMERA_ACCESS_PERMISSION = 5674
    private val bitmap: Bitmap? = null
    private val pickImage = 100
    private val CAMERA_PIC_REQUEST = 100
    private var imageUri: Uri? = null
    private var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        gaViewModel = ViewModelProvider(requireActivity())[GAViewModel::class.java]

        _binding = FragmentGABinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }
    @SuppressLint("MissingInflatedId")
    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        var layoutManager = LinearLayoutManager(activity)
        list.add("spiner_item")
        list.add("text_filed_items")
        list.add("main_layout")
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        _binding?.rvDynamicList.apply {
            this?.layoutManager = layoutManager
            this?.adapter = GADynamicAdapter(list,this@GAFragment)
        }
    }

     override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun itemClicked(): String {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(gallery, pickImage)
        return gallery.toString()
        }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
        }
        else {

            val photo: Bitmap = data?.extras?.get("data") as Bitmap
        }
    }
    override fun itemCameraClicked(): String {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST)
        return cameraIntent.toString()
    }


}