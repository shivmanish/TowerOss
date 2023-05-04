package com.smarthub.baseapplication.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.smarthub.baseapplication.databinding.FragmentMyTaskHomeBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.home.MyTeamTask
import com.smarthub.baseapplication.network.APIInterceptor
import com.smarthub.baseapplication.network.EndPoints
import com.smarthub.baseapplication.ui.fragments.task.TaskListener
import com.smarthub.baseapplication.ui.fragments.task.routes
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class MyTaskFragment(var listener: TaskListener) : Fragment() {

    var homeViewModel : HomeViewModel?=null
    lateinit var binding : FragmentMyTaskHomeBinding
    private lateinit var adapterList : MyTaskItemAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMyTaskHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.taskList.setHasFixedSize(true)
        adapterList = MyTaskItemAdapter(listener,routes.MyTaskNavigator.name)
        binding.taskList.adapter = adapterList

        adapterList.addItem("loading")
        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]

        if (homeViewModel?.myTask?.hasActiveObservers() == true)
            homeViewModel?.myTask?.removeObservers(viewLifecycleOwner)
        homeViewModel?.myTask?.observe(viewLifecycleOwner){

            if (it!=null && it.isNotEmpty()){
                AppLogger.log("myTask data not null")
                val list :ArrayList<Any> = ArrayList()
                list.add("header")
                list.addAll(filterTaskList(it))
                adapterList.updateList(list)
                prepareOfflineList(it)
            }
            else{
                val list :ArrayList<Any> = ArrayList()
                list.add("header")
                adapterList.updateList(list)
                adapterList.addItem("no_data")
            }
        }

    }

    private fun prepareOfflineList(list:List<MyTeamTask>){
        for(item in list){
            val cacheData = AppPreferences.getInstance().getString("TaskDetailData"+item.id1)
            if (cacheData==null || cacheData.isEmpty()){
                val jsonObject = JsonObject()
                jsonObject.addProperty("gettaskdata", item.id1)
                jsonObject.addProperty("ownername", AppController.getInstance().ownerName)
                AppPreferences.getInstance().saveTaskOfflineApi(Gson().toJson(jsonObject), "${APIInterceptor.DYNAMIC_BASE_URL}${EndPoints.WORKFLOW_DATA_URL}","TaskDetailData"+item.id1)

                val splittedData = item.Where.replace("[","").replace("]","").split(",")
                var parentId =0
                if (splittedData.isNotEmpty()) {
                    val firstIdx = splittedData[0]
                    if (firstIdx.isNotEmpty()) {
                        try {
                            parentId = firstIdx.toInt().div(10)
                        }catch (e:Exception){
                            AppLogger.log("e:${e.localizedMessage}")
                        }
                    }
                    AppLogger.log("parentId=====>:${parentId}")
                    when (parentId){
                        2->{
                            val cacheSiteAgreementData = AppPreferences.getInstance().getString("siteAgreementRequestAll" + item.siteid)
                            if (cacheSiteAgreementData==null || cacheSiteAgreementData.isEmpty()) {
                                val childJsonObj = JsonObject()
                                childJsonObj.addProperty("id", item.siteid)
                                childJsonObj.addProperty("ownername", AppController.getInstance().ownerName)
                                childJsonObj.add("SAcqSiteAcquisition", JsonArray())
                                AppPreferences.getInstance().saveTaskOfflineApi(Gson().toJson(childJsonObj), "${APIInterceptor.DYNAMIC_BASE_URL}${EndPoints.SITE_IBOARD_DATA_URL}", "siteAgreementRequestAll" + item.siteid)
                                AppLogger.log("offline api saved for siteAgreementRequestAll${item.siteid}")
                            }else{
                                AppLogger.log("already saved for siteAgreementRequestAll${item.siteid}")
                            }
                        }
                        0 or 6->{
                            val cacheNocData = AppPreferences.getInstance().getString("NocAndCompRequestAll" + item.siteid)
                            if (cacheNocData==null || cacheNocData.isEmpty()) {
                                val childJsonObj = JsonObject()
                                childJsonObj.addProperty("id", item.siteid)
                                childJsonObj.addProperty("ownername", AppController.getInstance().ownerName)
                                childJsonObj.add("NOCCompliance", JsonArray())
                                AppPreferences.getInstance().saveTaskOfflineApi(Gson().toJson(childJsonObj), "${APIInterceptor.DYNAMIC_BASE_URL}${EndPoints.SITE_IBOARD_DATA_URL}", "NocAndCompRequestAll" + item.siteid)
                                AppLogger.log("offline api saved for NocAndCompRequestAll${item.siteid}")
                            }else{
                                AppLogger.log("already saved for NocAndCompRequestAll${item.siteid}")
                            }
                        }
                    }
                }
            }
        }
    }

    fun filterTaskList(allTaskList:List<MyTeamTask>):ArrayList<MyTeamTask>{
        val filteredTaskList:ArrayList<MyTeamTask> = ArrayList()
//        filteredTaskList.addAll(allTaskList)
        AppLogger.log("MyTaskListSize :===> ${filteredTaskList.size}")
        AppLogger.log("MyTaskList :===> ${Gson().toJson(filteredTaskList)}")
        for (item in allTaskList){
            if (item.Where.isNotEmpty()){
                if (item.Where.contains("q_")){
                    filteredTaskList.add(item)
                }else{
                    val subTaskTabList=item.Where.replace("[","").replace("]","").split(",")
                    if (subTaskTabList.isNotEmpty()){
                        try {
                            val tempTab=subTaskTabList[0].replace(" ","").toInt().div(10)
                            if (tempTab==2 || tempTab==6 || tempTab==10)
                                filteredTaskList.add(item)
                        }
                        catch (e:Exception){
                            AppLogger.log("${e.localizedMessage}")
                        }
                    }
                }

            }
        }
        return filteredTaskList
    }

}