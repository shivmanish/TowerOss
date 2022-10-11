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
import com.smarthub.baseapplication.adapter.qat.QatPunchPointAdapter
import com.smarthub.baseapplication.listeners.QatProfileListener

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class QatPunchPointFragment : Fragment(), QatProfileListener {
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewlay = inflater.inflate(R.layout.fragment_punch_point_qat, container, false)
        setRecyclerView(viewlay)
        return viewlay
    }

    private fun setRecyclerView(view: View) {
        val adapter = QatPunchPointAdapter(this)
        recyclerView = view.findViewById(R.id.recyclerView_open)
        recyclerView.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OpenQatFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun itemClicked() {
        Toast.makeText(requireActivity(),"Click_Open", Toast.LENGTH_LONG).show()
    }
}