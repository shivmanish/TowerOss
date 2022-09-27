package com.smarthub.baseapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.smarthub.baseapplication.R

private const val ARG_PARAM2 = "param2"


class DashboardFragment : Fragment() {
    // TODO: Rename and change types of parameters
//    private var param1: String? = null
    private var param2: String? = null

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
      /*  var textView:TextView=view.findViewById(R.id.text_dashboard)
        textView.text=param2*/
        return view
    }

    companion object {

        @JvmStatic
        fun newInstance( param2: String) =
            DashboardFragment().apply {
                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}