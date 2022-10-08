package com.smarthub.baseapplication.fragments.qat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.adapter.qat.OpenQatAdapter
import com.smarthub.baseapplication.adapter.qat.SubmittedQatAdapter
import com.smarthub.baseapplication.listeners.QatProfileListener

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class SubmitQatFragment : Fragment() {
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewlay = inflater.inflate(R.layout.fragment_submit_qat, container, false)
        setRecyclerView(viewlay)
        return viewlay
    }

    fun setRecyclerView(view: View) {
        val adapter = SubmittedQatAdapter(ArrayList())
        recyclerView = view.findViewById(R.id.recyclerView_submitted)
        recyclerView.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
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