package com.smarthub.baseapplication.ui.fragments.qat

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarthub.baseapplication.activities.BaseActivity
import com.smarthub.baseapplication.databinding.ActivityQatDetailsBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.listeners.PunchPointListener
import com.smarthub.baseapplication.model.qatcheck.punch_point.PunchPointUpdate
import com.smarthub.baseapplication.model.qatcheck.punch_point.QatPunchPointModel
import com.smarthub.baseapplication.model.siteInfo.qat.SaveCheckpointData
import com.smarthub.baseapplication.model.siteInfo.qat.SaveCheckpointModel
import com.smarthub.baseapplication.model.siteInfo.qat.qat_main.Checkpoint
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
                return@observe
            }
            hideLoader()
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.item!=null && it.data.item?.isNotEmpty()==true){
                AppLogger.log("Service request Fragment card Data fetched successfully")
                Toast.makeText(this@QatDetailsActivity,"Data updated successfully",Toast.LENGTH_SHORT).show()
//                adapter.addItem(punchPointUpdate)

            }else if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.itemNew!=null && it.data.itemNew?.isNotEmpty()==true){
                AppLogger.log("Service request Fragment card Data fetched successfully")
                it.data.item = it.data.itemNew
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

    override fun punchPointClicked() {
//        val dialog = PunchPointResolveDialog(this)
//        dialog.show()
        val bmSheet = OpenPunchPointDialouge()
        bmSheet.show(supportFragmentManager,"openPunchPoint")
    }

    override fun closedPunchPointClicked() {
        val bmSheet = ClosedPunchPointDialouge()
        bmSheet.show(supportFragmentManager,"openPunchPoint")
    }

    override fun savePunchPointData(data: SaveCheckpointData) {
        var item = SaveCheckpointModel()
        var dataList:ArrayList<SaveCheckpointData> = ArrayList()
        dataList.clear()
        dataList.add(data)
        item.QAT=dataList
        item.id=AppController.getInstance().siteid
        item.ownername=AppController.getInstance().ownerName
        viewmodel.saveQatPunchPoint(item)
    }

}