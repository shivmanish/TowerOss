package com.smarthub.baseapplication.fragments.sitedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.ui.site_detail.SiteDetailViewModel


private const val ARG_PARAM2 = "param2"


class Site_info : Fragment() {
    // TODO: Rename and change types of parameters
//    private var param1: String? = null
    private var param2: String? = null
    private lateinit var siteDetailViewModel: SiteDetailViewModel
    private var scrollview: ScrollView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.site_detail_frag, container, false)
        scrollview  = view.findViewById(R.id.scroll_view)
        siteDetailViewModel = ViewModelProvider(requireActivity())[SiteDetailViewModel::class.java]
        setScroolListner()
        /*  var textView:TextView=view.findViewById(R.id.text_dashboard)
          textView.text=param2*/
        return view
    }
    fun setScroolListner(){
        scrollview?.setOnScrollChangeListener(View.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            val x = scrollY - oldScrollY
            if (x > 0) {
                siteDetailViewModel.setScrollViewUp(true)
            } else if (x < 0) {
                siteDetailViewModel.setScrollViewUp(false)

            }
        })


    }

    companion object {

        @JvmStatic
        fun newInstance( param2: String) =
            Site_info().apply {
                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}