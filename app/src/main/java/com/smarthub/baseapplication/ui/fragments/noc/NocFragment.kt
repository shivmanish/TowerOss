package com.smarthub.baseapplication.ui.fragments.noc

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.NocAndCompFragmentBinding
import com.smarthub.baseapplication.ui.fragments.services_request.adapter.NocDataAdapter
import com.smarthub.baseapplication.ui.fragments.services_request.adapter.NocDataAdapterListener
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.ui.fragments.services_request.ServiceFragmentViewModel

class NocFragment : Fragment(), NocDataAdapterListener {

    lateinit var customerBinding: NocAndCompFragmentBinding
    lateinit var viewmodel: ServiceFragmentViewModel
    lateinit var nocDataViewHolder: NocDataAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        customerBinding = NocAndCompFragmentBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity())[ServiceFragmentViewModel::class.java]
        initializeFragment()
        return customerBinding.root
    }

    private fun initializeFragment() {
        customerBinding.customerList.layoutManager = LinearLayoutManager(requireContext())
        nocDataViewHolder = NocDataAdapter(this@NocFragment, ArrayList())
        customerBinding.customerList.adapter = nocDataViewHolder

        customerBinding.addMore.setOnClickListener(){
            val dalouge = CommonBottomSheetDialog(R.layout.add_more_botom_sheet_dailog)
            dalouge.show(childFragmentManager,"")

        }

        nocDataViewHolder.updateData("anything")
        customerBinding.addMore.setOnClickListener{
            nocDataViewHolder.updateData("anything")
        }

//        viewmodel.fetchData()
//        viewmodel.customer_data.observe(requireActivity(), Observer {
//            // Data is get from server and ui work will be start from here
//            println("this is called data is $it")
//            var arraydata = ArrayList<String>()
//            arraydata.add(it)
//            nocDataViewHolder.setData(arraydata)
//        })
    }

    override fun clickedItem() {
        requireActivity().startActivity(Intent(requireContext(), NocDetailsActivity::class.java))

    }
}