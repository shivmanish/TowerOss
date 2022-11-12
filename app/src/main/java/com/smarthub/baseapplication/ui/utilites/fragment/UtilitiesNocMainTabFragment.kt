package com.smarthub.baseapplication.ui.utilites.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.SearchActivity
import com.smarthub.baseapplication.databinding.FragmentSiteLeaseAcquitionBinding

import com.smarthub.baseapplication.databinding.FragmentUtilitesNocBinding
import com.smarthub.baseapplication.ui.fragments.sitedetail.adapter.SiteLeaseDataAdapter

import com.smarthub.baseapplication.ui.fragments.sitedetail.adapter.UtilitesNocDataAdapter
import com.smarthub.baseapplication.ui.fragments.sitedetail.adapter.UtilitesNocDataAdapterListener
import com.smarthub.baseapplication.ui.utilites.UtilitiesNocActivity
import com.smarthub.baseapplication.ui.utilites.UtilitiesNocViewModel

class UtilitiesNocMainTabFragment : Fragment(), UtilitesNocDataAdapterListener {
    private val ARG_PARAM1 = "param1"
    private val ARG_PARAM2 = "param2"
    lateinit var nocBinding: FragmentUtilitesNocBinding
    lateinit var viewmodel: UtilitiesNocViewModel
    lateinit var nocDataAdapter: UtilitesNocDataAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        nocBinding = FragmentUtilitesNocBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity())[UtilitiesNocViewModel::class.java]
        initializeFragment()


//        findNavController().navigate(UtilitiesNocMainTabFragmentDirections.actionUtilitiesNocMainTabFragmentToAC1UtilitesFrag())
        return nocBinding.root
    }

    private fun initializeFragment() {
        nocBinding.utilitesNocList.layoutManager = LinearLayoutManager(requireContext())
        nocDataAdapter = UtilitesNocDataAdapter(this@UtilitiesNocMainTabFragment, ArrayList())
        nocBinding.utilitesNocList.adapter = nocDataAdapter
        nocBinding.addmore.setOnClickListener{
            var arraydata = ArrayList<String>()
            arraydata.add("anything")
            nocDataAdapter.setData(arraydata)
        }
        viewmodel.fetchData()
        viewmodel.utilites_noc_data.observe(requireActivity(), Observer {
            // Data is get from server and ui work will be start from here
            println("this is called data is $it")
            var arraydata = ArrayList<String>()
            arraydata.add(it)
            nocDataAdapter.setData(arraydata)
        })
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            UtilitiesNocMainTabFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }

    override fun clickedItem() {
     //   requireActivity().startActivity(Intent(requireContext(), UtilitiesNocActivity::class.java))

    }

    override fun clickedItemAC() {

       // (requireActivity() as SearchActivity).navigationFragment(UtilitiesNocMainTabFragmentDirections.actionUtilitiesNocMainTabFragmentToAC1UtilitesFrag())

     //   requireActivity().findNavController(R.id.nav_host).navigate(UtilitiesNocMainTabFragmentDirections.actionUtilitiesNocMainTabFragmentToAC1UtilitesFrag())

   }  override fun clickedItemDG() {
    //    findNavController().navigate(UtilitiesNocMainTabFragmentDirections.actionUtilitiesNocMainTabFragmentToDG1TabUtilitesFrag())

   }
    override fun clickedItemSMP() {

    }
    override fun clickedItemFireExiting() {

   }
}