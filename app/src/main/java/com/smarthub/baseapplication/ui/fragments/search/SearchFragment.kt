package com.smarthub.baseapplication.ui.fragments.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.SearchActivityBinding
import com.smarthub.baseapplication.databinding.SearchFragmentBinding
import com.smarthub.baseapplication.ui.fragments.sitedetail.SiteDetailFragment
import com.smarthub.baseapplication.viewmodels.SearchActivityViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var dataBinding: SearchFragmentBinding? = null
    private lateinit var mViewModel: SearchActivityViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = SearchFragmentBinding.inflate(layoutInflater)
        mViewModel = ViewModelProvider(this)[SearchActivityViewModel::class.java]

        dataBinding?.searchCardView?.setOnClickListener {

            findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToSiteDetailFragment())
        }
        setSearchFilter()
        return dataBinding?.root
    }

    private fun setSearchFilter() {
        val data: List<String> = mViewModel.getFlowData()
//        dataBinding!!.flowlayout.removeAllViews()
//        for (i in 0 until data.size) {
//            val child: View =
//                layoutInflater.inflate(R.layout.flowcontainer, dataBinding!!.flowlayout, false)
//            var titeltext: TextView = child.findViewById(R.id.titel)
//            titeltext.text = data[i]
//            dataBinding!!.flowlayout.addView(child)
//        }
    }

}