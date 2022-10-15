package com.smarthub.baseapplication.fragments.sitedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarthub.baseapplication.databinding.FragmentCustomerBinding
import com.smarthub.baseapplication.fragments.sitedetail.adapter.CustomerDataAdapter
import com.smarthub.baseapplication.fragments.sitedetail.viewmodel.CustomerFragmentViewmodel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
lateinit var customerBinding: FragmentCustomerBinding
lateinit var viewmodel: CustomerFragmentViewmodel
lateinit var customerDataAdapter: CustomerDataAdapter

class CustomerFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      /*  arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }*/
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        customerBinding = FragmentCustomerBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity()).get(CustomerFragmentViewmodel::class.java)
        initializeFragment()
        return customerBinding.root
    }

    fun initializeFragment() {
        customerBinding.customerList.layoutManager = LinearLayoutManager(requireContext())
        customerDataAdapter = CustomerDataAdapter(requireContext(), ArrayList<String>())
        customerBinding.customerList.adapter = customerDataAdapter
        customerBinding.addmore.setOnClickListener{
            var arraydata = ArrayList<String>()
            arraydata.add(
                "anything"
            )
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
}