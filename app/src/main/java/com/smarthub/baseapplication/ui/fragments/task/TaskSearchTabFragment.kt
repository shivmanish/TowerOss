package com.smarthub.baseapplication.ui.fragments.task
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.FragmentSearchTaskBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.taskModel.dropdown.CollectionItem
import com.smarthub.baseapplication.model.taskModel.dropdown.TaskDropDownModel
import com.smarthub.baseapplication.model.taskModel.dropdown.TaskDropDownModelItem
import com.smarthub.baseapplication.ui.alert.dialog.ChatFragment
import com.smarthub.baseapplication.ui.dynamic.TitleItem
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.sitedetail.SiteDetailViewModel
import com.smarthub.baseapplication.ui.fragments.task.adapter.HorizontalTabAdapter
import com.smarthub.baseapplication.ui.fragments.task.adapter.TaskSiteInfoAdapter
import com.smarthub.baseapplication.ui.fragments.task.editdialog.SiteInfoEditBottomSheet
import com.smarthub.baseapplication.ui.taskUi.serviceRequest.srDetails.SrDetauilsPageAdapter
import com.smarthub.baseapplication.utils.AppConstants
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils

class TaskSearchTabFragment(var siteID:String?,var taskId :String) : BaseFragment(),HorizontalTabAdapter.TaskCardClickListner,
    TaskSiteInfoAdapter.TaskSiteInfoListener {
    private lateinit var binding: FragmentSearchTaskBinding
    private lateinit var horizontalTabAdapter:HorizontalTabAdapter
    private lateinit var siteDetailViewModel: SiteDetailViewModel
//    var TaskAlltabsData: TaskDropDownModelItem ?=null
    lateinit var TaskListmodel :TaskDropDownModel
    var taskAndCardList:ArrayList<String> = ArrayList()
    var lat ="19.25382218490181"
    var long="72.98213045018673"
    var radius="2"
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        siteDetailViewModel = ViewModelProvider(requireActivity())[SiteDetailViewModel::class.java]
        val json = Utils.getJsonDataFromAsset(requireContext(),"task_drop_down.json")
        TaskListmodel = Gson().fromJson(json, TaskDropDownModel::class.java)
        var tempWhere="[41,42,43]"
        tempWhere=tempWhere.replace("[","")
        tempWhere=tempWhere.replace("]","")

        taskAndCardList.addAll(tempWhere.split(","))
//        if (siteDetailViewModel.taskUiModelResoonse?.hasActiveObservers() == true)
//            siteDetailViewModel.taskUiModelResoonse?.removeObservers(viewLifecycleOwner)
//        siteDetailViewModel.taskUiModelResoonse?.observe(viewLifecycleOwner){
//            if (it!=null && it.status == Resource.Status.SUCCESS && it.data!=null){
//                if(!it.data.isNullOrEmpty())
//                {
//                    AppLogger.log("all data from api start====>: ")
//                    AppLogger.log("data===> ${Gson().toJson(it.data.get(it.data.size.minus(1)).data.get(0).tabs.get(0))}")
//                    AppLogger.log("<======all data from api end ")
//                    TaskListmodel = it.data.reversed().get(0).data
//                    AppLogger.log("all data in TaskListmodel start====>: ")
//                    AppLogger.log("TaskListmodel data====>:${Gson().toJson(TaskListmodel.get(0).tabs.get(1))}")
//                    AppLogger.log("<======all data in TaskListmodel end ")
//                    var parentIndex=fetchParentIndexById(TaskListmodel,taskAndCardList[0].substring(0,1))
//                    val list = TaskListmodel[parentIndex].tabs[fetchChildIndexById(TaskListmodel[parentIndex].tabs,taskAndCardList[0])].list
//                    setViewPager(list)
//                    try {
//                        horizontalTabAdapter =  HorizontalTabAdapter(this@TaskSearchTabFragment,createHoriZentalList())
//                        binding.horizontalOnlyList.adapter = horizontalTabAdapter
//                    }catch (e:Exception){
//                        AppLogger.log("Somthing went wrong in TaskSearchTabFragment during set HorizontalTabAdapter ")
//                    }
//                }
//                else
//                    AppLogger.log("data is empty : ${it.data}")
//                Toast.makeText(requireContext(),"ui data fetched",Toast.LENGTH_SHORT).show()
//            }else Toast.makeText(requireContext(),"something went wrong",Toast.LENGTH_SHORT).show()
//        }

        siteDetailViewModel.siteTaskUiModel(taskId)

//        siteDetailViewModel.siteTaskUiUpdateModel(TaskListmodel)

        binding = FragmentSearchTaskBinding.inflate(inflater, container, false)
        return binding.root
    }
    var list : ArrayList<Any> = ArrayList()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.collapsingLayout.tag= false
        binding.horizontalOnlyList.layoutManager = LinearLayoutManager(requireContext(),RecyclerView.HORIZONTAL,false)
        try {
            horizontalTabAdapter =  HorizontalTabAdapter(this@TaskSearchTabFragment,createHoriZentalList())
            binding.horizontalOnlyList.adapter = horizontalTabAdapter
        }catch (e:Exception){
            AppLogger.log("Somthing went wrong in TaskSearchTabFragment during set HorizontalTabAdapter ")
        }
        setDataObserver()

        binding.dropdownImg.setOnClickListener {
            binding.collapsingLayout.tag= !(binding.collapsingLayout.tag as Boolean)
            if (binding.collapsingLayout.tag as Boolean){
                binding.collapsingLayout.visibility=View.VISIBLE
                binding.topLine.visibility=View.VISIBLE
                binding.dropdownImg.setImageResource(R.drawable.down_arrow)
            }
            else{
                binding.collapsingLayout.visibility=View.GONE
                binding.topLine.visibility=View.GONE
                binding.dropdownImg.setImageResource(R.drawable.ic_arrow_up_faq)
            }
        }

        binding.mapView.setOnClickListener {
            mapView()
        }
        binding.messages.setOnClickListener {
            val chatfragment=ChatFragment()
            val bundle=Bundle()
            bundle.putString("reportedBy","7269024641")
            bundle.putString("id","93")
            chatfragment.arguments=bundle
            addFragment(chatfragment)
        }
//        binding.back.setOnClickListener {
//            requireActivity().onBackPressedDispatcher.onBackPressed()
//        }
        TaskListmodel = AppPreferences.getInstance().getTaskUiModel(taskId,requireContext())
        AppLogger.log("all data in TaskListmodel start====>: ")
        AppLogger.log("TaskListmodel data====>:${Gson().toJson(TaskListmodel.get(0).tabs.get(1))}")
        AppLogger.log("<======all data in TaskListmodel end ")
        var parentIndex=fetchParentIndexById(TaskListmodel,taskAndCardList[0].substring(0,1))
        val list = TaskListmodel[parentIndex].tabs[fetchChildIndexById(TaskListmodel[parentIndex].tabs,taskAndCardList[0])].list
        setViewPager(list)
        try {
            horizontalTabAdapter =  HorizontalTabAdapter(this@TaskSearchTabFragment,createHoriZentalList())
            binding.horizontalOnlyList.adapter = horizontalTabAdapter
        }catch (e:Exception){
            AppLogger.log("Somthing went wrong in TaskSearchTabFragment during set HorizontalTabAdapter ")
        }
    }
    private fun setDataObserver() {
        var parentIndex=fetchParentIndexById(TaskListmodel,taskAndCardList[0].substring(0,1))
        val list = TaskListmodel[parentIndex].tabs[fetchChildIndexById(TaskListmodel[parentIndex].tabs,taskAndCardList[0])].list
        setViewPager(list)

        if (siteDetailViewModel.dropDownResponse?.hasActiveObservers() == true)
            siteDetailViewModel.dropDownResponse?.removeObservers(viewLifecycleOwner)
        siteDetailViewModel.dropDownResponse?.observe(viewLifecycleOwner) {
            hideLoader()

            if (it != null) {
                if (it.status == Resource.Status.SUCCESS && it.data != null) {
                   // saveDataToLocal(it.data)
//                    setData()
                    return@observe
                } else {
                    Log.d("status", "${it.message}")
                    Toast.makeText(context, "error:" + it.message, Toast.LENGTH_LONG).show()

                }
            } else {
                Log.d("status", AppConstants.GENERIC_ERROR)
                Toast.makeText(context, AppConstants.GENERIC_ERROR, Toast.LENGTH_LONG).show()
            }
        }
        siteDetailViewModel.fetchDropDown()
    }

    private fun setViewPager(list:List<TitleItem>){
        AppLogger.log("view pager List Size : ${binding.tabs.tabCount}")
        binding.viewpager.adapter = SrDetauilsPageAdapter(childFragmentManager,list,TaskListmodel,taskId)
        binding.tabs.setupWithViewPager( binding.viewpager)

        if(binding.tabs.tabCount==1) {
            binding.tabs.setBackgroundColor(Color.parseColor("#E9EEF7"))
            binding.tabs.setSelectedTabIndicatorColor(Color.parseColor("#E9EEF7"))
        }
        if(binding.tabs.tabCount<=4)
            binding.tabs.tabMode = TabLayout.MODE_FIXED
        else
            binding.tabs.tabMode = TabLayout.MODE_SCROLLABLE
    }

    fun addFragment(fragment: Fragment?) {
        val backStateName: String = childFragmentManager.javaClass.name
        val manager = childFragmentManager
        val fragmentPopped = manager.popBackStackImmediate(backStateName, 0)
        if (!fragmentPopped) {
            val transaction = manager.beginTransaction()
            transaction.setCustomAnimations(
                R.anim.enter,
                R.anim.exit,
                R.anim.pop_enter,
                R.anim.pop_exit
            )
            transaction.add(R.id.container, fragment!!)
            transaction.addToBackStack(backStateName)
            transaction.commit()
        }
    }

    private fun mapView(){
//        val intent = Intent(requireContext(), HomePage::class.java)
//        intent.putExtra("lat",lat)
//        intent.putExtra("long",long)
//        intent.putExtra("rad",radius)
//        startActivity(intent)
    }

    private fun createHoriZentalList():ArrayList<CollectionItem>{
        if (taskAndCardList.isEmpty()) {
            Toast.makeText(requireContext(),"Collection list is empty",Toast.LENGTH_SHORT).show()
            return ArrayList()
        }
        val cardList:ArrayList<CollectionItem> =ArrayList()

//        val selectedTask=TaskListmodel[taskAndCardList[0].toInt()]
        var fetchedId=taskAndCardList[0]
        if(fetchedId.length>1)
            fetchedId=fetchedId.substring(0,1)
        val selectedTask=TaskListmodel[fetchParentIndexById(TaskListmodel,fetchedId)]

        for(i in 0..taskAndCardList.size.minus(1)){
            val card = selectedTask.tabs[fetchChildIndexById(selectedTask.tabs,taskAndCardList[i])]
            cardList.add(card)
        }
        AppLogger.log("task name:${selectedTask.name},size:${selectedTask.tabs.size}")
        return cardList
    }

    fun fetchParentIndexById(list:ArrayList<TaskDropDownModelItem>,currentId:String): Int{
        try {
            for(i in 0..list.size.minus(1))
            {
                if(list[i].id.toString()==currentId)
                    return i
            }
        }catch (e:Exception){

        }
       return 0
    }

    fun fetchChildIndexById(list:ArrayList<CollectionItem>,currentId:String): Int{
        try {
            for(i in 0..list.size.minus(1))
            {
                if(list[i].id.toString()==currentId)
                    return i
            }
        }catch (e:Exception){

        }
        return 0
    }


    override fun taskCardItemClicked(selectedCollectionItemData:CollectionItem) {
        setViewPager(selectedCollectionItemData.list)
    }
    override fun taskSiteInfoItemClicked() {
        val bottomSheetDialogFragment = SiteInfoEditBottomSheet(R.layout.task_site_info_dialouge_layout)
        bottomSheetDialogFragment.show(childFragmentManager,"category")

    }
}