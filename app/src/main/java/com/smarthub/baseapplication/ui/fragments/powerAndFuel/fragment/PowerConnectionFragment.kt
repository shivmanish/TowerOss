package com.smarthub.baseapplication.ui.fragments.powerAndFuel.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.PowerConnectionFragBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.*
import com.smarthub.baseapplication.ui.fragments.AttachmentCommonDialogBottomSheet
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.adapter.PowerConnecFragAdapter
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.dialouge.*
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class PowerConnectionFragment(var powerConnData:NewPowerFuelAllData?,var parentIndex:Int): BaseFragment(),PowerConnecFragAdapter.PowerConnectionListListener{
    var viewmodel: HomeViewModel?=null
    lateinit var binding : PowerConnectionFragBinding
    lateinit var adapter:PowerConnecFragAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewmodel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding = PowerConnectionFragBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= PowerConnecFragAdapter(this@PowerConnectionFragment,this@PowerConnectionFragment,powerConnData)
        binding.listItem.adapter = adapter

        if (viewmodel?.powerAndFuelResponse?.hasActiveObservers() == true){
            viewmodel?.powerAndFuelResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel?.powerAndFuelResponse?.observe(viewLifecycleOwner) {
            if (it!=null && it.status == Resource.Status.LOADING){
                showLoader()
                AppLogger.log("PowerFuel Fragment data loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS){
                AppLogger.log("PowerFuel Fragment card Data fetched successfully")
                try {
                    powerConnData=it.data.PowerAndFuel?.get(parentIndex)
                    adapter.setData(it.data.PowerAndFuel?.get(parentIndex)?.PowerAndFuelEBConnection?.get(0))
                    hideLoader()
                }catch (e:java.lang.Exception){
                    AppLogger.log("PowerFuel Fragment error : ${e.localizedMessage}")
                }
                AppLogger.log("PowerFuel size :${it.data.PowerAndFuel?.size}")
            }else if (it!=null) {
                AppLogger.log("PowerFuel Fragment error :${it.message}, data : ${it.data}")
            }
            else {
                AppLogger.log("PowerFuel Fragment Something went wrong")
            }
        }



        binding.swipeLayout.setOnRefreshListener {
            binding.swipeLayout.isRefreshing=false
            viewmodel?.fetchPowerAndFuel(AppController.getInstance().siteid)
        }
//        viewmodel?.fetchPowerAndFuel(AppController.getInstance().siteid)
    }

    override fun onDestroy() {
        if (viewmodel?.TowerCivilInfraModelResponse?.hasActiveObservers() == true){
            viewmodel?.TowerCivilInfraModelResponse?.removeObservers(viewLifecycleOwner)
        }
        super.onDestroy()
    }

    override fun attachmentItemClicked() {
       AppLogger.log("Attachment clicked")
    }

    override fun addAttachment() {
        val bm = AttachmentCommonDialogBottomSheet("PowerAndFuelEBConnection",powerConnData?.PowerAndFuelEBConnection?.get(0)?.id.toString(),
            object : AttachmentCommonDialogBottomSheet.AddAttachmentListner {
                override fun attachmentAdded(){
                    viewmodel?.fetchPowerAndFuel(AppController.getInstance().siteid)
                }
            })
        bm.show(childFragmentManager,"sdg")
    }

    override fun updateData(updatedData: PowerConnectionAllData) {
        showLoader()
        val dataModel = NewPowerFuelAllData()
        dataModel.PowerAndFuelEBConnection= arrayListOf(updatedData)
        dataModel.id=powerConnData?.id
        viewmodel?.updatePowerFuel(dataModel)
        if (viewmodel?.updatePowerFuelDataResponse?.hasActiveObservers() == true) {
            viewmodel?.updatePowerFuelDataResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel?.updatePowerFuelDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("PowerConnectionFragment data updating in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.status?.PowerAndFuelEBConnection==200) {
                AppLogger.log("PowerConnectionFragment card Data Updated successfully")
                viewmodel?.fetchPowerAndFuel(AppController.getInstance().siteid)
                Toast.makeText(context,"Data Updated successfully", Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideLoader()
                AppLogger.log("PowerConnectionFragment Something went wrong in updating data")
                Toast.makeText(context,"Something went wrong in update data . Try again", Toast.LENGTH_SHORT).show()
            }
            else if (it != null) {
                AppLogger.log("PowerConnectionFragment error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("PowerConnectionFragment Something went wrong in updating data")

            }
        }
    }

    override fun viewPoClicked(position: Int, data: PowerFuelPODetail) {
        val bm = PowerFuelPoViewDialouge(R.layout.tower_po_view_dialouge,data)
        bm.show(childFragmentManager, "category")
    }

    override fun editPoClicked(position: Int, data: PowerFuelPODetail) {
        val bm = PowerFuelPoEditDialouge(data,powerConnData,
            object : PowerFuelPoEditDialouge.PowerFuelPoUpdateListener {
                override fun updatedData() {
                    viewmodel?.fetchPowerAndFuel(AppController.getInstance().siteid)
                }

            })
        bm.show(childFragmentManager,"sdg")
    }

    override fun viewConsumableClicked(position: Int, data: PowerConsumableMaterial) {
        val bm = PowerConsumableViewDialouge(R.layout.tower_consumable_view_dialouge,data)
        bm.show(childFragmentManager, "category")
    }

    override fun editConsumableClicked(position: Int, data: PowerConsumableMaterial) {
        val bm = PowerFuelConsumMaterialEditDialouge(data,powerConnData,
            object : PowerFuelConsumMaterialEditDialouge.PowerFuelConsumMaterialUpdateListener {
                override fun updatedData() {
                    viewmodel?.fetchPowerAndFuel(AppController.getInstance().siteid)
                }

            })
        bm.show(childFragmentManager,"sdg")
    }

    override fun editTarrifClicked(position: Int, data: PowerFuelTariffDetails) {
        val bm = PowerFuelTarrifEditDialouge(data,powerConnData,
            object : PowerFuelTarrifEditDialouge.PowerFuelTarrifUpdateListener {
                override fun updatedData() {
                    viewmodel?.fetchPowerAndFuel(AppController.getInstance().siteid)
                }

            })
        bm.show(childFragmentManager,"sdg")
    }

    override fun viewAuthorityPaymentClicked(position: Int, data: PowerFuelAuthorityPayments) {
        val bm = PowerFuelAuthPaymentViewDialouge(R.layout.power_fuel_payment_dialouge,data)
        bm.show(childFragmentManager, "category")
    }

    override fun editAuthorityPaymentClicked(position: Int, data: PowerFuelAuthorityPayments) {
        val bm = PowerFuelPaymentEditDialouge(data,powerConnData,
            object : PowerFuelPaymentEditDialouge.PowerFuelPaymentUpdateListener {
                override fun updatedData() {
                    viewmodel?.fetchPowerAndFuel(AppController.getInstance().siteid)
                }

            })
        bm.show(childFragmentManager,"sdg")
    }


}
