package com.smarthub.baseapplication.ui.fragments.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.FragmentNotificationsBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.notification.newData.SendData
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.addCardBottomSheet.EquipmentRoomAddNew
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class NotificationsFragment : Fragment() {

    lateinit var binding: FragmentNotificationsBinding
    private lateinit var homeViewModel: HomeViewModel
    lateinit var adapter:NotificationListSubtitleAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        binding = FragmentNotificationsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        adapter = NotificationListSubtitleAdapter(requireContext(),ArrayList())
        binding.list.adapter =adapter

        binding.backImage.setOnClickListener {
            requireActivity().onBackPressed()
        }
        homeViewModel.getNotifications()

        if (homeViewModel.notificationNew?.hasActiveObservers() == true)
            homeViewModel.notificationNew?.removeObservers(viewLifecycleOwner)
        homeViewModel.notificationNew?.observe(viewLifecycleOwner){
            if (it?.data!=null && it.status == Resource.Status.SUCCESS){
                binding.swipeLayout.isRefreshing=false
                adapter.setData(it.data)
                AppLogger.log("notification fetched successfully in NotificationFragment class")
            }else{
                AppLogger.log("something wend wrong in NotificationFragment class e:${it.message}")

            }
        }

        binding.swipeLayout.setOnRefreshListener {
            homeViewModel.getNotifications()
        }

        binding.addNotification.setOnClickListener {
            val bmSheet = AddNotificationDialouge(R.layout.add_notification_dialouge)
            bmSheet.show(childFragmentManager,"category")
        }

    }



}
