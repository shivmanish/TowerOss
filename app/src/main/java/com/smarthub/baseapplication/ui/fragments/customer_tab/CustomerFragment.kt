package com.smarthub.baseapplication.ui.fragments.customer_tab

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarthub.baseapplication.databinding.FragmentCustomerBinding
import com.smarthub.baseapplication.ui.fragments.sitedetail.adapter.CustomerDataAdapter
import com.smarthub.baseapplication.ui.fragments.sitedetail.adapter.CustomerDataAdapterListener
import com.smarthub.baseapplication.ui.fragments.sitedetail.viewmodel.CustomerFragmentViewmodel


class CustomerFragment : Fragment(), CustomerDataAdapterListener {

    private val ARG_PARAM1 = "param1"
    private val ARG_PARAM2 = "param2"
    lateinit var customerBinding: FragmentCustomerBinding
    lateinit var viewmodel: CustomerFragmentViewmodel
    lateinit var customerDataAdapter: CustomerDataAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        customerBinding = FragmentCustomerBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity())[CustomerFragmentViewmodel::class.java]
        initializeFragment()
        return customerBinding.root
    }

    private fun initializeFragment() {
        customerBinding.customerList.layoutManager = LinearLayoutManager(requireContext())
        customerDataAdapter = CustomerDataAdapter(this@CustomerFragment, ArrayList())
        customerBinding.customerList.adapter = customerDataAdapter
        customerBinding.addmore.setOnClickListener{
            var arraydata = ArrayList<String>()
            arraydata.add("anything")
            customerDataAdapter.setData(arraydata)
        }


        viewmodel.fetchData()
        viewmodel.customer_data.observe(requireActivity(), Observer {
            // Data is get from server and ui work will be start from here
            println("this is called data is $it")
            var arraydata = ArrayList<String>()
            arraydata.add(it)
            customerDataAdapter.setData(arraydata)
        })
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            CustomerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }

    override fun clickedItem() {
        requireActivity().startActivity(Intent(requireContext(), NewCustomerDetailsActivity::class.java))

    }
}