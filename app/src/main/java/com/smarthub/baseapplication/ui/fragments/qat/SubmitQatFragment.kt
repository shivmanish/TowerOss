package com.smarthub.baseapplication.ui.fragments.qat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.ui.adapter.qat.OpenQatAdapter
import com.smarthub.baseapplication.ui.adapter.qat.SubmittedQatAdapter
import com.smarthub.baseapplication.listeners.QatProfileListener
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.utils.Utils

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class SubmitQatFragment : BaseFragment(), QatProfileListener {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun itemClicked() {
        var fragment = QatCheckNestedList()
        Utils.addFragment(fragment,requireActivity() as AppCompatActivity,R.id.container)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewlay = inflater.inflate(R.layout.fragment_submit_qat, container, false)
        setRecyclerView(viewlay)
        return viewlay
    }

    private fun setRecyclerView(view: View) {
        val adapter = SubmittedQatAdapter(ArrayList(),this)
        recyclerView = view.findViewById(R.id.recyclerView_submitted)
        recyclerView.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SubmitQatFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}