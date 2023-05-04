package com.smarthub.baseapplication.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.trackermodule.homepage.BaseActivity
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ActivityTaskDetailBinding
import com.smarthub.baseapplication.ui.fragments.task.TaskSearchTabNewFragment
import com.smarthub.baseapplication.ui.fragments.task.adapter.TaskAdapter
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.TaskViewModel

class TaskDetailActivity : BaseActivity(), TaskAdapter.TaskLisListener {
    lateinit var binding: ActivityTaskDetailBinding
    lateinit var viewModel : TaskViewModel
    var siteId:String = "1526"
    var lattitude:String = "20.735566"
    var longitude:String = "85.853400"
    var trackingId:String = "474"
    var taskDetailId:String ? = null
    var tempWhere = "[22,24]"
    var NotificationSettingGeoTracking = false
    var NotificationSettingGeoFencing = false
    var Distance :Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[TaskViewModel::class.java]
        binding = ActivityTaskDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.btnBack.setOnClickListener {
//            onBackPressed()
//        }
        checkLocationPermission()
        if (intent.hasExtra("url")){
             taskDetailId = intent.getStringExtra("url")
//            binding.titleText.text = id
//            showLoader()
//            viewModel.fetchTaskDetails(taskDetailId)
        }
        if (intent.hasExtra("siteId") && intent.getStringExtra("siteId")!=null && intent.getStringExtra("siteId")?.isNotEmpty() == true){
            siteId = intent.getStringExtra("siteId")!!
            AppController.getInstance().taskSiteId=siteId
            AppController.getInstance().siteid=siteId
        }
        if (intent.hasExtra("lattitude")){
            lattitude = intent.getStringExtra("lattitude")!!
        }

        if (intent.hasExtra("NotificationSettingGeoFencing")){
            try {
                intent.getStringExtra("NotificationSettingGeoFencing")?.toBoolean()?.let {
                    NotificationSettingGeoFencing = it
                }
            }catch (e:Exception){
                AppLogger.log("errorr in fetching Distance:${e.localizedMessage}")
            }

        }

        if (intent.hasExtra("NotificationSettingGeoTracking"))
            NotificationSettingGeoTracking=intent.getBooleanExtra("NotificationSettingGeoTracking",false)

        if (intent.hasExtra("Distance")){
            try {
                 intent.getStringExtra("Distance")?.toDouble()?.let {
                     Distance = it
                }
            }catch (e:Exception){
                AppLogger.log("errorr in fetching Distance:${e.localizedMessage}")
            }
        }
        if (intent.hasExtra("longitude")){
            longitude = intent.getStringExtra("longitude")!!
        }

        if (intent.hasExtra("trackingId")){
            trackingId = intent.getStringExtra("trackingId")!!
        }
        if (intent.hasExtra("where") && intent.getStringExtra("where")?.isNotEmpty() == true &&
            intent.getStringExtra("where")!="[]"){
//            tempWhere = intent.getStringExtra("where")!!
        }

        if (viewModel.taskDataList?.hasActiveObservers() == true)
            viewModel.taskDataList?.removeObservers(this)
/*
        viewModel.taskDataList?.observe(this){
            hideLoader()
            if (it?.data != null){
                if (it.data.isNotEmpty()){
                    mapUIData(it.data[0])
                }
                else
                    setFragment(TaskSearchTabFragment(siteId,trackingId,lattitude,longitude))
                Toast.makeText(this@TaskDetailActivity,"task data fetched",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this@TaskDetailActivity,"Something went wrong",Toast.LENGTH_SHORT).show()
            }
        }
*/
        /*binding.refreshLayout.setOnRefreshListener {
            binding.refreshLayout.isRefreshing = false
            if (intent.hasExtra("url")){
                val id = intent.getStringExtra("url")
//                binding.titleText.text = id
                showLoader()
                viewModel.fetchTaskDetails(id!!)
            }else{
                Toast.makeText(this,"Task id not found",Toast.LENGTH_SHORT).show()
            }
        }*/
        mapUIData()
    }

    private fun checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this, Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                AlertDialog.Builder(this).setTitle("Location Permission Needed")
                    .setMessage("This app needs the Location permission, please accept to use location functionality")
                    .setPositiveButton(
                        "OK"
                    ) { _, _ ->
                        requestLocationPermission()
                    }.create().show()
            } else {
                requestLocationPermission()
            }
        } else {
            checkBackgroundLocation()
        }
    }
    private fun checkBackgroundLocation() {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_BACKGROUND_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestBackgroundLocationPermission()
        }
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            this, arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
            ), MY_PERMISSIONS_REQUEST_LOCATION
        )
    }

    private fun requestBackgroundLocationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION
                ), MY_PERMISSIONS_REQUEST_BACKGROUND_LOCATION
            )
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                MY_PERMISSIONS_REQUEST_LOCATION
            )
        }
    }



    private fun mapUIData(){
        setFragment(TaskSearchTabNewFragment(siteId,trackingId,taskDetailId,lattitude,longitude,tempWhere,NotificationSettingGeoFencing,Distance,NotificationSettingGeoTracking))
//        binding.titleText.text ="Task\n${item.Processname}"
//
//        binding.listItem.adapter = TaskAdapter(applicationContext,this)
    }

    override fun onStop() {
        super.onStop()
        if (viewModel.taskDataList?.hasActiveObservers() == true)
            viewModel.taskDataList?.removeObservers(this)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent (this@TaskDetailActivity, DashboardActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
    private fun setFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.placeholder, fragment)
        fragmentTransaction.commit()
    }

    override fun attachmentItemClicked() {

    }

    override fun detailsItemClicked() {

    }

    override fun requestinfoClicked() {

    }

    override fun operationInfoDetailsItemClicked() {
//       
    }

    override fun geoConditionsDetailsItemClicked() {
//       
    }

    override fun siteAccessDetailsItemClicked() {
//       
    }

    override fun EditInstallationAcceptence() {
       
    }

    override fun EditTowerItem() {
       
    }

    override fun editPoClicked(position: Int) {
       
    }

    override fun viewPoClicked(position: Int) {
       
    }

    override fun editConsumableClicked(position: Int) {
       
    }

    override fun viewConsumableClicked(position: Int) {
       
    }

    override fun editOffsetClicked(position: Int) {
       
    }

    override fun viewOffsetClicked(position: Int) {
       
    }

    companion object {
        private const val MY_PERMISSIONS_REQUEST_LOCATION = 99
        private const val PHONE_STATE_READ = 23
        private const val MY_PERMISSIONS_REQUEST_BACKGROUND_LOCATION = 66
        var perms = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION
            )
        } else {
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

}