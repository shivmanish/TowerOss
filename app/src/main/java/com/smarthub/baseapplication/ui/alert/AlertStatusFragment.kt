package com.smarthub.baseapplication.ui.alert

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AlertStatusBinding
import com.smarthub.baseapplication.ui.alert.adapter.AlertImageAdapter
import com.smarthub.baseapplication.ui.alert.adapter.AlertStatusListAdapter
import com.smarthub.baseapplication.ui.alert.adapter.AlertStatusListener
import com.smarthub.baseapplication.ui.alert.adapter.SelectCallBack
import com.smarthub.baseapplication.ui.alert.dialog.AlertUserListBottomSheet
import com.smarthub.baseapplication.ui.alert.dialog.CreateAlertBottomSheet
import com.smarthub.baseapplication.ui.alert.dialog.SubmitAletrBottomSheet
import com.smarthub.baseapplication.ui.alert.model.request.GetUserList
import com.smarthub.baseapplication.ui.alert.viewmodel.AlertViewModel
import com.smarthub.baseapplication.ui.fragments.BaseFragment


class AlertStatusFragment : BaseFragment(), AlertStatusListener,
    AlertImageAdapter.ItemClickListener, SelectCallBack {

    lateinit var binding: AlertStatusBinding
    lateinit var viewmodel: AlertViewModel
    var adapter: AlertStatusListAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AlertStatusBinding.inflate(inflater)
        viewmodel = ViewModelProvider(this).get(AlertViewModel::class.java)
        observerData()
        return binding.root
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = AlertStatusListAdapter(requireContext(), viewmodel, this@AlertStatusFragment)
        binding.list.adapter = adapter

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    fun detailsItemClicked() {
        val bottomSheetDialogFragment = CreateAlertBottomSheet(R.layout.create_alert_dialog)
        bottomSheetDialogFragment.show(childFragmentManager, "category")
    }

    override fun itemClicked() {
//        TODO("Not yet implemented")
    }

    fun observerData() {
        viewmodel.sendAlertResponseLivedata.observe(viewLifecycleOwner, Observer {
            //response will get here
            if (it != null) {

                val bm = SubmitAletrBottomSheet(
                    R.layout.submit_allert_bottom_sheet,
                    it.data!!.data.get(0)
                )
                bm.show(childFragmentManager, "categoery")
            }
        })
        viewmodel.userDataResponseLiveData.observe(viewLifecycleOwner, Observer {
//response will get here
            viewmodel.userDataList.clear()
            viewmodel.userDataList.addAll(it.data!!)
            val bm = AlertUserListBottomSheet(R.layout.alert_list_bottom_sheet, viewmodel, this)
            bm.show(childFragmentManager, "categoery")

        })
    }

    override fun itemAdded() {

    }

    override fun sendAlertData() {
        viewmodel.sendAlert()
    }

    override fun getuser() {
        if (viewmodel.userDataList.size == 0) {
            viewmodel.getUser(GetUserList())
        } else {
            val bm = AlertUserListBottomSheet(R.layout.alert_list_bottom_sheet, viewmodel, this)
            bm.show(childFragmentManager, "categoery")
        }
    }

    override fun onSelectUser(position: Int, isadd: Boolean) {

        if (isadd) {
            viewmodel.selecteduserposition.add(position)
        } else {
            viewmodel.selecteduserposition.remove(position)
        }
        adapter!!.setusercount(viewmodel.selecteduserposition.size)

    }


}


