package com.smarthub.baseapplication.ui.fragments.qat

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trackermodule.homepage.BaseActivity
import com.smarthub.baseapplication.databinding.ActivityQatDetailsBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.listeners.PunchPointListener
import com.smarthub.baseapplication.model.qatcheck.punch_point.PunchPointUpdate
import com.smarthub.baseapplication.model.qatcheck.punch_point.QatPunchPointModel
import com.smarthub.baseapplication.model.siteInfo.qat.SaveCheckpointData
import com.smarthub.baseapplication.model.siteInfo.qat.SaveCheckpointModel
import com.smarthub.baseapplication.model.siteInfo.qat.qat_main.Checkpoint
import com.smarthub.baseapplication.model.siteInfo.qat.qat_main.PunchpointData
import com.smarthub.baseapplication.model.siteInfo.qat.qat_main.Subitem
import com.smarthub.baseapplication.ui.adapter.qat.QatPunchPointAdapter
import com.smarthub.baseapplication.ui.dialog.qat.CreateQatPunchPointBottomSheet
import com.smarthub.baseapplication.ui.fragments.qat.bottomDialouge.ClosedPunchPointDialouge
import com.smarthub.baseapplication.ui.fragments.qat.bottomDialouge.OpenPunchPointDialouge
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class QatDetailsActivity : BaseActivity(), PunchPointListener {
    companion object{
        var data : Subitem?=null
        var mainIndex:Int?=0
        var categoryIndex:Int?=0
        var itemIndex:Int?=0
        var subItemIndex:Int?=0
    }
    lateinit var adapter : QatPunchPointAdapter
    lateinit var viewmodel: HomeViewModel
    lateinit var binding: ActivityQatDetailsBinding
    var punchPointUpdate : PunchPointUpdate?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding = ActivityQatDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.subTitle.text = data?.QATSubItem
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        adapter = data?.checkpoint?.let { QatPunchPointAdapter(this, ArrayList(it)) }!!
        binding.recyclerViewOpen.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewOpen.adapter = adapter
        binding.back.setOnClickListener {
            onBackPressed()
        }
        if (viewmodel.QatModelResponse?.hasActiveObservers() == true){
            viewmodel.QatModelResponse?.removeObservers(this)
        }
        viewmodel.QatModelResponse?.observe(this) {
            if (it!=null && it.status == Resource.Status.LOADING){
                showLoader()
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.item!=null && it.data.item?.isNotEmpty()==true){
                AppLogger.log("Punch Point Data In QatDetailsActivity Data fetched successfully")
                hideLoader()
                Toast.makeText(this@QatDetailsActivity,"Data updated successfully",Toast.LENGTH_SHORT).show()
                try {
                    adapter.updateData(
                        it.data.item!!.get(0).QATMainLaunch.get(mainIndex!!).Category.get(
                            categoryIndex!!).item.get(itemIndex!!).subitem.get(subItemIndex!!).checkpoint)
                }catch (e:Exception){
                    AppLogger.log("")
                }

            }else if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.itemNew!=null && it.data.itemNew?.isNotEmpty()==true){
                AppLogger.log("Service request Fragment card Data fetched successfully")
                hideLoader()
                it.data.item = it.data.itemNew
                try {
                    adapter.updateData(
                        it.data.item!!.get(0).QATMainLaunch.get(mainIndex!!).Category.get(
                            categoryIndex!!).item.get(itemIndex!!).subitem.get(subItemIndex!!).checkpoint)
                }catch (e:Exception){
                    AppLogger.log("")
                }
                Toast.makeText(this@QatDetailsActivity,"Data updated successfully",Toast.LENGTH_SHORT).show()
                AppLogger.log("size :${it.data.itemNew?.size}")
            }else if (it!=null) {
                Toast.makeText(this@QatDetailsActivity,"Service request Fragment error :${it.message}, data : ${it.data}", Toast.LENGTH_SHORT).show()
                AppLogger.log("Service request Fragment error :${it.message}, data : ${it.data}")
            }
            else {
                AppLogger.log("Service Request Fragment Something went wrong")
                Toast.makeText(this@QatDetailsActivity,"Service Request Fragment Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }



    override fun addPunchPoint(punchPointUpdate : PunchPointUpdate) {
        this.punchPointUpdate = punchPointUpdate
        val listPunch = ArrayList<PunchPointUpdate>()
        listPunch.add(punchPointUpdate)
        val updateData = QatPunchPointModel(listPunch, AppController.getInstance().siteid,AppController.getInstance().ownerName)
        viewmodel.qatLaunchMain(updateData)
    }

    override fun editPunchPoint(item:Checkpoint,pos: Int) {
        val bottomSheetDialogFragment = CreateQatPunchPointBottomSheet(object : CreateQatPunchPointBottomSheet.LaunchQatBottomSheetListener{
            override fun onPunchPointCreated(data: QatPunchPointModel) {
                showLoader()
                viewmodel.qatLaunchMain(data)
            }
        },item.QATItem_id)
        bottomSheetDialogFragment.show(supportFragmentManager, "category")
    }

    override fun punchPointClicked(data:ArrayList<PunchpointData>) {
//        val dialog = PunchPointResolveDialog(this)
//        dialog.show()
        val bmSheet = OpenPunchPointDialouge(data)
        bmSheet.show(supportFragmentManager,"openPunchPoint")
    }

    override fun closedPunchPointClicked(data:ArrayList<PunchpointData>) {
        val bmSheet = ClosedPunchPointDialouge(data)
        bmSheet.show(supportFragmentManager,"openPunchPoint")
    }

    override fun savePunchPointData(data: SaveCheckpointData) {
        showLoader()
        AppLogger.log("punch point data for update ====> : $data")
        var item = SaveCheckpointModel()
        var dataList:ArrayList<SaveCheckpointData> = ArrayList()
        dataList.clear()
        dataList.add(data)
        item.QAT=dataList
        item.id=AppController.getInstance().siteid
        item.ownername=AppController.getInstance().ownerName
        AppLogger.log("punch point data Model for update ====> : $data")
        viewmodel.saveQatPunchPoint(item)
    }

}