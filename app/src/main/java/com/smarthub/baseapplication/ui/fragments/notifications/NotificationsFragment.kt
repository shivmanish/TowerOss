package com.smarthub.baseapplication.ui.fragments.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarthub.baseapplication.databinding.FragmentNotificationsBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.notification.newData.SendData
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class NotificationsFragment : Fragment() {

    lateinit var binding: FragmentNotificationsBinding
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        binding = FragmentNotificationsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        binding.list.adapter = NotificationListSubtitleAdapter(requireContext(),ArrayList())

        binding.backImage.setOnClickListener {
            requireActivity().onBackPressed()
        }
        homeViewModel.getNotifications()

        if (homeViewModel.notificationNew?.hasActiveObservers() == true)
            homeViewModel.notificationNew?.removeObservers(viewLifecycleOwner)
        homeViewModel.notificationNew?.observe(viewLifecycleOwner){
            if (it?.data!=null && it.status == Resource.Status.SUCCESS){
                binding.swipeLayout.isRefreshing=false
                (binding.list.adapter as NotificationListSubtitleAdapter).setData(it.data)
                Toast.makeText(requireContext(),"notification fetched successfully",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(requireContext(),"something wend wrong e:${it.message}",Toast.LENGTH_LONG).show()

            }
        }

        binding.swipeLayout.setOnRefreshListener {
            homeViewModel.getNotifications()
        }
    }



}
