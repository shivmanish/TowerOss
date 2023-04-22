package com.smarthub.baseapplication.ui.fragments.plandesign.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.PlanDesignFragmentBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteInfo.planAndDesign.PlanAndDesignDataItem
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.plandesign.PowerDesignDetailsActivity
import com.smarthub.baseapplication.ui.fragments.plandesign.adapter.PlanDesignAdapter
import com.smarthub.baseapplication.ui.fragments.plandesign.adapter.PlanDesignAdapterListener
import com.smarthub.baseapplication.ui.fragments.plandesign.dialouge.AddNewPlanDesignDialouge
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class PlanDesignMainFrqagment(var id:String) : BaseFragment(), PlanDesignAdapterListener {

    lateinit var viewmodel: HomeViewModel
    lateinit var adapter: PlanDesignAdapter
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
        adapter = PlanDesignAdapter(this@PlanDesignMainFrqagment, id)
        binding.customerList.adapter = adapter

        if (viewmodel?.PlanDesignModelResponse?.hasActiveObservers() == true){
            viewmodel?.PlanDesignModelResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel?.PlanDesignModelResponse?.observe(viewLifecycleOwner) {
            if (it!=null && it.status == Resource.Status.LOADING){
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS){
               try {
                   AppLogger.log("Service request Fragment card Data fetched successfully")
                   adapter.setData(it.data.PlanningAndDesign!!)
                   isDataLoaded = true
               }catch (e:Exception){
                   e.printStackTrace()
               }
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
            viewmodel?.planAndDesignRequestAll(id)
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
        if (viewmodel!=null && !isDataLoaded){
            adapter.addLoading()
            viewmodel?.planAndDesignRequestAll(id)
        }
        AppLogger.log("onViewPageSelected PlanAndDesign")
    }

    override fun onDestroy() {
        if (viewmodel?.PlanDesignModelResponse?.hasActiveObservers() == true){
            viewmodel?.PlanDesignModelResponse?.removeObservers(viewLifecycleOwner)
        }
        super.onDestroy()
    }

    

    override fun clickedItem(data : PlanAndDesignDataItem?, Id :String,index:Int) {
        PowerDesignDetailsActivity.Id=Id
        PowerDesignDetailsActivity.planDesigndata=data
        PowerDesignDetailsActivity.index=index
        requireActivity().startActivity(Intent(requireContext(), PowerDesignDetailsActivity::class.java))

    }
}