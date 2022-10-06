package com.smarthub.baseapplication.ui.gat

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Fragment
import android.os.Bundle
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.adapter.GADynamicAdapter
import com.smarthub.baseapplication.adapter.NotificationsListAdapter
import com.smarthub.baseapplication.databinding.FragmentGABinding
import com.smarthub.baseapplication.ui.notifications.NotificationsViewModel
import com.smarthub.baseapplication.ui.notifications.getMonthName
import java.util.*

class GAFragment : Fragment() {
    private var _binding: FragmentGABinding? = null
    private lateinit var gaViewModel: GAViewModel
    private val binding get() = _binding!!
    var list : ArrayList<Any> = ArrayList()
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
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        _binding?.rvDynamicList.apply {
            this?.layoutManager = layoutManager
            this?.adapter = GADynamicAdapter(list)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




}