package com.smarthub.baseapplication.ui.site_lease_acquisition

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.circularreveal.cardview.CircularRevealCardView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.FragmentSiteLeaseAcquitionBinding
import com.smarthub.baseapplication.ui.fragments.sitedetail.adapter.SiteLeaseDataAdapter
import com.smarthub.baseapplication.ui.fragments.sitedetail.adapter.SiteLeaseDataAdapterListener


class SiteLeaseAcqusitionFragment : Fragment(), SiteLeaseDataAdapterListener {

    val ARG_PARAM1 = "param1"
    private val ARG_PARAM2 = "param2"
    lateinit var fragmentSiteLeaseBinding: FragmentSiteLeaseAcquitionBinding
    lateinit var viewmodel: SiteLeaseAcqusitionViewModel
    lateinit var siteLeaseDataAdapter: SiteLeaseDataAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentSiteLeaseBinding = FragmentSiteLeaseAcquitionBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity())[SiteLeaseAcqusitionViewModel::class.java]
        initializeFragment()
        return fragmentSiteLeaseBinding.root
    }

    private fun initializeFragment() {
        fragmentSiteLeaseBinding.siteLeaseListItem.layoutManager = LinearLayoutManager(requireContext())
        siteLeaseDataAdapter = SiteLeaseDataAdapter(this@SiteLeaseAcqusitionFragment, ArrayList())
        fragmentSiteLeaseBinding.siteLeaseListItem.adapter = siteLeaseDataAdapter
        fragmentSiteLeaseBinding.addmore.setOnClickListener{
            var arraydata = ArrayList<String>()
            arraydata.add("anything")
           siteLeaseDataAdapter.setData(arraydata)
        }
     //   viewmodel.fetchData()

        viewmodel.fetchDropDown()
        viewmodel.site_lease_data.observe(requireActivity(), Observer {
            // Data is get from server and ui work will be start from here
            println("this is called data is $it")
            var arraydata = ArrayList<String>()
            arraydata.add(it)
            siteLeaseDataAdapter.setData(arraydata)
        })

        fragmentSiteLeaseBinding.addMore.setOnClickListener(){
            val dialog = BottomSheetDialog(requireActivity(), R.style.NewDialog)
            // on below line we are inflating a layout file which we have created.
            val view = layoutInflater.inflate(R.layout.site_release_bottom_sheet_dialog_layout, null)
            val close = view.findViewById<CircularRevealCardView>(R.id.ic_menu_close)
            val ic_menu_call = view.findViewById<CircularRevealCardView>(R.id.ic_menu_call)
            val ic_map_view = view.findViewById<CircularRevealCardView>(R.id.ic_map_view)
            val ic_send_alert = view.findViewById<CircularRevealCardView>(R.id.ic_send_alert)
/*            val ic_menu_open_faults = view.findViewById<CircularRevealCardView>(R.id.ic_menu_open_faults)
            val ic_menu_escalations = view.findViewById<CircularRevealCardView>(R.id.ic_menu_escalations)
            val ic_menu_picture = view.findViewById<CircularRevealCardView>(R.id.ic_menu_picture)
            val ic_pm_task = view.findViewById<CircularRevealCardView>(R.id.ic_pm_task)
            val ic_menu_logs = view.findViewById<CircularRevealCardView>(R.id.ic_menu_logs)*/
            dialog.window?.setBackgroundDrawable( ColorDrawable(Color.TRANSPARENT));
            close.setOnClickListener {
                // on below line we are calling a dismiss
                // method to close our dialog.
                dialog.dismiss()
            }
            dialog.setCancelable(false)
            // on below line we are setting
            // content view to our view.
            dialog.setContentView(view)
            // on below line we are calling
            // a show method to display a dialog.
//            dialog.window?.setBackgroundDrawableResource(R.drawable.dialog_bg)
            dialog.show()
        }

    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            SiteLeaseAcqusitionFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }

    override fun clickedItem() {
        requireActivity().startActivity(Intent(requireContext(), NewSiteAcquisitionActivity::class.java))

    }
}