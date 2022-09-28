package com.smarthub.baseapplication.fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.SiteDetailFragBinding

private const val ARG_PARAM2 = "param2"


class DashboardFragment : Fragment() {
    var binding : SiteDetailFragBinding ?=null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.site_detail_frag, container, false)
        binding = SiteDetailFragBinding.bind(view)
        return view
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.scrollView?.setOnScrollChangeListener(View.OnScrollChangeListener { view, x, y, oldX, oldY ->
            Log.d("status", "it works $y $x")
        })
    }

    companion object {
        @JvmStatic
        fun newInstance( param2: String) = DashboardFragment().apply {
                arguments = Bundle().apply { putString(ARG_PARAM2, param2) }
            }
    }
}