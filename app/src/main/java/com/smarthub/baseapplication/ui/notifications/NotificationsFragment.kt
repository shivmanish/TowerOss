package com.smarthub.baseapplication.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.adapter.NotificationsListAdapter
import com.smarthub.baseapplication.databinding.FragmentNotificationsBinding


import androidx.recyclerview.widget.LinearLayoutManager
import com.smarthub.baseapplication.adapter.ProfileListAdapter

class NotificationsFragment : Fragment() {
     private var _binding: FragmentNotificationsBinding? = null
     private lateinit var notificationViewModel:NotificationsViewModel
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val notificationsViewModel = ViewModelProvider(this)[NotificationsViewModel::class.java]
        notificationViewModel = ViewModelProvider(requireActivity())[NotificationsViewModel::class.java]
       // notificationViewModel.isActionBarHide(false)
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        var layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        _binding?.rvNotificationList.apply {
            this?.layoutManager = layoutManager
            this?.adapter = NotificationsListAdapter()
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}