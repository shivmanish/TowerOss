package com.smarthub.baseapplication.ui.fragments.sitedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.ui.adapter.SiteDetailAdapter
import com.smarthub.baseapplication.model.SiteDetailDataModel
import com.smarthub.baseapplication.ui.fragments.site_detail.SiteDetailViewModel


private const val ARG_PARAM2 = "param2"


class Site_info : Fragment() {
    // TODO: Rename and change types of parameters
//    private var param1: String? = null
    private var param2: String? = null
    private lateinit var siteDetailViewModel: SiteDetailViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewmain:View
//    private var scrollview: ScrollView?=null

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
        viewmain = inflater.inflate(R.layout.site_info_redetail, container, false)
//        scrollview  = view.findViewById(R.id.scroll_view)
        siteDetailViewModel = ViewModelProvider(requireActivity())[SiteDetailViewModel::class.java]
//        setScroolListner()
        /*  var textView:TextView=view.findViewById(R.id.text_dashboard)
          textView.text=param2*/

        return viewmain
    }

    override fun onResume() {
        super.onResume()
        setRecyclerView(viewmain)
    }

    fun setRecyclerView(view: View) {
        val dataList = ArrayList<SiteDetailDataModel>()
        dataList.add(SiteDetailDataModel(SiteDetailAdapter.VIEW_TYPE_ONE, true))
        val adapter = SiteDetailAdapter(requireActivity(), dataList)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
    }

    /* fun setScroolListner(){
         scrollview?.setOnScrollChangeListener(View.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
             val x = scrollY - oldScrollY
             if (x > 0) {

             }
             if (x < 0) {


             }

             if (scrollY > oldScrollY) {
                 siteDetailViewModel.setScrollViewUp(true)
                 Log.i(TAG, "Scroll DOWN  ${scrollY}   ${oldScrollY}   $x");
             }
             if (scrollY < oldScrollY) {
                 siteDetailViewModel.setScrollViewUp(false)

                 Log.i(TAG, "Scroll UP  ${scrollY}   ${oldScrollY}   $x");
             }

             if (scrollY == 0) {
                 Log.i(TAG, "TOP SCROLL ${scrollY}   ${oldScrollY}   $x" +
                         "");
             }

             Log.i(TAG, "SCROLL------ ${oldScrollY}   ${scrollY}" +
                     "")
         })


     }*/

    companion object {

        @JvmStatic
        fun newInstance(param2: String) =
            Site_info().apply {
                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}