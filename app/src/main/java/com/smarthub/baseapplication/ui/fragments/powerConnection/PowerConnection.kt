package com.smarthub.baseapplication.ui.fragments.powerConnection

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.databinding.PowerConnectionFragmentBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.powerConnection.adapter.PowerConnDataAdapter
import com.smarthub.baseapplication.ui.fragments.powerConnection.adapter.PowerConnDataDataDataAdapterListener
import com.smarthub.baseapplication.ui.fragments.powerConnection.pojo.PowerAndFuel
import com.smarthub.baseapplication.ui.fragments.powerConnection.viewmodel.PowerConnectionRootViewModel
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class PowerConnection : BaseFragment(), PowerConnDataDataDataAdapterListener {

    lateinit var binding: PowerConnectionFragmentBinding
    lateinit var viewmodel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PowerConnectionFragmentBinding.inflate(inflater)
        viewmodel = ViewModelProvider(this).get(HomeViewModel::class.java)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        initViews()
    }

    fun initViews() {
        viewmodel.fetchPowerAndFuel("448")
        viewmodel.powerandfuel!!.observe(viewLifecycleOwner, Observer {

            if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideLoader()
                AppLogger.log("Service request Fragment card Data fetched successfully")
                PowerConnDataAdapter(this@PowerConnection,
                    it.data.item!![0].ServiceRequestMain.get(0).PowerAndFuel as ArrayList<PowerAndFuel>
                )
                AppLogger.log("size :${it.data.item?.size}")
            }else if (it!=null) {
                Toast.makeText(requireContext(),"PowerConnection Fragment error :${it.message}, data : ${it.data}", Toast.LENGTH_SHORT).show()
                AppLogger.log("PowerConnection Fragment error :${it.message}, data : ${it.data}")
            }
            else {
                AppLogger.log("PowerConnection Fragment Something went wrong")
                Toast.makeText(requireContext(),"PowerConnection Fragment Something went wrong", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun clickedItem(data: PowerAndFuel) {
        val intent = Intent(
            requireContext(),
            PowerConnectionDetailsActivity::class.java
        )
        intent.putExtra("data", data)
        requireActivity().startActivity(intent)
    }

    override fun onViewPageSelected() {
        super.onViewPageSelected()

        initViews()

    }

}