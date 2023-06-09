package com.smarthub.baseapplication.ui.taskUi
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.TaskDynamicTitleListBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.taskModel.dropdown.TaskDropDownModel
import com.smarthub.baseapplication.ui.adapter.DynamicItemListAdapter
import com.smarthub.baseapplication.ui.dynamic.DynamicTitleList
import com.smarthub.baseapplication.ui.dynamic.ItemData
import com.smarthub.baseapplication.ui.dynamic.TitleItem
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.sitedetail.SiteDetailViewModel
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class TaskDynamicSiteFragment(var listData:TitleItem,var childIndex:Int,var taskModel: TaskDropDownModel, var taskId:String): BaseFragment() {

    lateinit var binding: TaskDynamicTitleListBinding
    lateinit var homeViewModel: HomeViewModel
//    lateinit var listData:TitleItem


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        binding = TaskDynamicTitleListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.itemCollapse.visibility=View.VISIBLE
        binding.collapsingLayout.setOnClickListener {
            if (binding.itemCollapse.visibility==View.VISIBLE){
                binding.imgDropdown.setImageResource(R.drawable.down_arrow)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                binding.itemCollapse.visibility = View.GONE
                binding.itemLine.visibility=View.VISIBLE
                binding.imgEdit.visibility=View.GONE
            }
            else
            {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                binding.itemCollapse.visibility = View.VISIBLE
                binding.itemLine.visibility=View.GONE
                binding.imgEdit.visibility=View.VISIBLE
            }
        }
        AppLogger.log("all data for feilds on TaskDynamicSiteFragment====>: ")
        AppLogger.log("TaskListmodel tabs data====>:${Gson().toJson(listData)}")
        AppLogger.log("<======all data for feilds on TaskDynamicSiteFragment end ")
//        val json = Utils.getJsonDataFromAsset(requireContext(),"dynamic_list.json")
//        val model : DynamicTitleList = Gson().fromJson(json,DynamicTitleList::class.java)
//        listData =model.data[childIndex]
        binding.itemTitleStr.text=listData.title
        binding.imgEdit.setOnClickListener {
            setEditItemAdapter(listData.listData)
        }
        binding.cancelBtn.setOnClickListener {
            setViewItemAdapter(listData.listData)
        }
        binding.updateBtn.setOnClickListener {
            AppPreferences.getInstance().saveTaskUiModel(taskModel,taskId)
            setViewItemAdapter(listData.listData)
        }
        setViewItemAdapter(listData.listData)
    }

    fun setEditItemAdapter(data: List<ItemData>){
        binding.cancelBtn.visibility=View.VISIBLE
        binding.updateBtn.visibility=View.VISIBLE
        binding.itemCollapse.adapter = DynamicItemListAdapter(data,
            object : DynamicItemListAdapter.DynamicItemListAdapterListener{
                override fun onDateFieldFind(text: TextView) {
                    setDatePickerView(text)
                }

            })
    }
    fun setViewItemAdapter(data: List<ItemData>){
        AppLogger.log("all data for feilds on setViewItemAdapter====>: ")
        AppLogger.log("TaskListmodel tabs data====>:${Gson().toJson(data)}")
        AppLogger.log("<======all data for feilds on setViewItemAdapter end ")
        binding.cancelBtn.visibility=View.GONE
        binding.updateBtn.visibility=View.GONE
        binding.itemCollapse.adapter=DynamicViewItemListAdapter(data)
    }

    override fun onDestroy() {
        if (homeViewModel.siteInfoDataResponse?.hasActiveObservers() == true)
            homeViewModel.siteInfoDataResponse?.removeObservers(viewLifecycleOwner)
        super.onDestroy()
    }

}