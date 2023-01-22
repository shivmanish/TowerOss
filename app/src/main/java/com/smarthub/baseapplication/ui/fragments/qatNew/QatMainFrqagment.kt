package com.smarthub.baseapplication.ui.fragments.qatNew

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.FragmentServiceRequestBinding
import com.smarthub.baseapplication.databinding.PlanDesignFragmentBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllData
import com.smarthub.baseapplication.model.siteInfo.planAndDesign.PlanAndDesignDataItem
import com.smarthub.baseapplication.model.siteInfo.qat.QatCardItem
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.plandesign.PowerDesignDetailsActivity
import com.smarthub.baseapplication.ui.fragments.plandesign.adapter.PlanDesignAdapter
import com.smarthub.baseapplication.ui.fragments.plandesign.adapter.PlanDesignAdapterListener
import com.smarthub.baseapplication.ui.fragments.plandesign.dialouge.AddNewPlanDesignDialouge
import com.smarthub.baseapplication.ui.fragments.qat.QATCheckActivity
import com.smarthub.baseapplication.ui.fragments.qat.adapter.QatMainAdapter
import com.smarthub.baseapplication.ui.fragments.qat.adapter.QatMainAdapterListener
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.addCardBottomSheet.EarthingAddNew
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class QatMainFrqagment(var id:String) : BaseFragment(), QatMainAdapterListener {

    lateinit var viewmodel: HomeViewModel
    lateinit var adapter: QatMainAdapter
    lateinit var binding: PlanDesignFragmentBinding
    var isDataLoaded = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = PlanDesignFragmentBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.customerList.layoutManager = LinearLayoutManager(requireContext())
        adapter = QatMainAdapter(this@QatMainFrqagment, id)
        binding.customerList.adapter = adapter

        if (viewmodel.QatModelResponse?.hasActiveObservers() == true){
            viewmodel.QatModelResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.QatModelResponse?.observe(viewLifecycleOwner) {
            if (it!=null && it.status == Resource.Status.LOADING){
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS){
                AppLogger.log("Service request Fragment card Data fetched successfully")
                adapter.setData(it.data.item!![0].QATTemplateMain)
                AppLogger.log("size :${it.data.item?.size}")
                isDataLoaded = true
            }else if (it!=null) {
                Toast.makeText(requireContext(),"Service request Fragment error :${it.message}, data : ${it.data}", Toast.LENGTH_SHORT).show()
                AppLogger.log("Service request Fragment error :${it.message}, data : ${it.data}")
            }
            else {
                AppLogger.log("Service Request Fragment Something went wrong")
                Toast.makeText(requireContext(),"Service Request Fragment Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }

        binding.swipeLayout.setOnRefreshListener {
            binding.swipeLayout.isRefreshing=false
            adapter.addLoading()
            viewmodel?.qatRequestAll(id)
        }
        
        binding.addMore.setOnClickListener(){
            val dalouge = CommonBottomSheetDialog(R.layout.add_more_botom_sheet_dailog)
            dalouge.show(childFragmentManager,"")

        }
        binding.addNew.setOnClickListener {
            val bmSheet = AddNewPlanDesignDialouge(R.layout.plandesign_addnew_dialouge)
            bmSheet.show(childFragmentManager,"category")
        }
    }

    override fun onViewPageSelected() {
        super.onViewPageSelected()
        if (isDataLoaded){
            adapter.addLoading()
            viewmodel.qatRequestAll(id)
        }
        AppLogger.log("onViewPageSelected PlanAndDesign")
    }

    override fun onDestroy() {
        if (viewmodel.QatModelResponse?.hasActiveObservers() == true){
            viewmodel.QatModelResponse?.removeObservers(viewLifecycleOwner)
        }
        super.onDestroy()
    }

    

//    override fun clickedItem(data : PlanAndDesignDataItem?, Id :String,index:Int) {
//        PowerDesignDetailsActivity.Id=Id
//        PowerDesignDetailsActivity.planDesigndata=data
//        PowerDesignDetailsActivity.index=index
//        requireActivity().startActivity(Intent(requireContext(), PowerDesignDetailsActivity::class.java))
//    }

    override fun clickedItem(data: QatCardItem?, Id: String, index: Int) {
        val intent = Intent(requireActivity(), QATCheckActivity::class.java)
        startActivity(intent)
    }
}