package com.smarthub.baseapplication.ui.fragments.powerConnection

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.PowerConnectionFragmentBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.powerConnection.adapter.PowerConnDataAdapter
import com.smarthub.baseapplication.ui.fragments.powerConnection.adapter.PowerConnDataDataDataAdapterListener
import com.smarthub.baseapplication.ui.fragments.powerConnection.pojo.PowerAndFuel
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class PowerConnection (var id:String): BaseFragment(), PowerConnDataDataDataAdapterListener {

    lateinit var binding: PowerConnectionFragmentBinding
    var viewmodel: HomeViewModel?=null
    lateinit var adapter: PowerConnDataAdapter
    var isDataLoaded = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = PowerConnectionFragmentBinding.inflate(inflater)
        viewmodel = ViewModelProvider(this).get(HomeViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.powerConnList.layoutManager = LinearLayoutManager(requireContext())
        adapter=PowerConnDataAdapter(requireContext(),this@PowerConnection,id)
        binding.powerConnList.adapter=adapter
        binding.addMore.setOnClickListener(){
            val dalouge = CommonBottomSheetDialog(R.layout.add_more_botom_sheet_dailog)
            dalouge.show(childFragmentManager,"")

        }

        if (viewmodel?.powerAndFuelResponse?.hasActiveObservers() == true){
            viewmodel?.powerAndFuelResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel?.powerAndFuelResponse?.observe(viewLifecycleOwner) {
            if (it!=null && it.status == Resource.Status.LOADING){
                AppLogger.log("PowerFuel Fragment data loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS){
                AppLogger.log("PowerFuel Fragment card Data fetched successfully")
                try {
                    adapter.setData(it.data.item!![0].PowerAndFuel)
                }catch (e:java.lang.Exception){
                    AppLogger.log("PowerFuel Fragment error : ${e.localizedMessage}")
                    Toast.makeText(context,"PowerFuel Fragment error :${e.localizedMessage}",Toast.LENGTH_LONG).show()
                }
                AppLogger.log("PowerFuel size :${it.data.item!![0].PowerAndFuel.size}")
                isDataLoaded = true
            }else if (it!=null) {
                Toast.makeText(requireContext(),"NocAndComp Fragment error :${it.message}, data : ${it.data}", Toast.LENGTH_SHORT).show()
                AppLogger.log("PowerFuel Fragment error :${it.message}, data : ${it.data}")
            }
            else {
                AppLogger.log("PowerFuel Fragment Something went wrong")
                Toast.makeText(requireContext(),"PowerFuel Fragment Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onViewPageSelected() {
        super.onViewPageSelected()
        if (viewmodel!=null || !isDataLoaded){
            adapter.addLoading()
            viewmodel?.fetchPowerAndFuel(id)
        }
        AppLogger.log("onViewPageSelected PowerAndFuel")
    }

    override fun onDestroy() {
        if (viewmodel?.powerAndFuelResponse?.hasActiveObservers() == true){
            viewmodel?.powerAndFuelResponse?.removeObservers(viewLifecycleOwner)
        }
        super.onDestroy()
    }

    override fun clickedItem(data: PowerAndFuel) {
        val intent = Intent(requireContext(), PowerConnectionDetailsActivity::class.java)
        intent.putExtra("data", data)
        requireActivity().startActivity(intent)
    }
}