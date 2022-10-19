package com.smarthub.baseapplication.ui.fragments.customer_tab

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.ui.adapter.AddMoreCustomerListAdapter


import com.smarthub.baseapplication.databinding.FragmentCustomerBinding


class CustomerTab : Fragment() {
    private var _binding: FragmentCustomerBinding? = null
    private lateinit var customerViewModel: CustomerViewModel
    var list : ArrayList<Any> = ArrayList()
    private val binding get() = _binding!!
    private var layoutManager: RecyclerView.LayoutManager? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        customerViewModel = ViewModelProvider(requireActivity())[CustomerViewModel::class.java]
        _binding = FragmentCustomerBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }
    @SuppressLint("MissingInflatedId")
    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        var layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        list.add("Reliance jio pvt ltd")
        list.add("Smart mile")
        list.add("Airtel")

        var myAdapter =
            AddMoreCustomerListAdapter(
                list
            )
//        _binding?.rvAddMoreItems.apply {
//            this?.layoutManager = layoutManager
//            this?.adapter = myAdapter
//        }
//        _binding?.btnNext?.setOnClickListener{
//            myAdapter.addItem("Item Added")
//        }


    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}



