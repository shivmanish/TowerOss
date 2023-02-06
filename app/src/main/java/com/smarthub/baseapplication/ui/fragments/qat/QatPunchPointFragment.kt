package com.smarthub.baseapplication.ui.fragments.qat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.FragmentPunchPointQatBinding
import com.smarthub.baseapplication.ui.adapter.qat.QatPunchPointAdapter
import com.smarthub.baseapplication.listeners.PunchPointListener
import com.smarthub.baseapplication.ui.dialog.qat.PunchPointCreateDialog
import com.smarthub.baseapplication.ui.dialog.qat.PunchPointResolveDialog

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class QatPunchPointFragment : Fragment(), PunchPointListener {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentPunchPointQatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewlay = inflater.inflate(R.layout.fragment_punch_point_qat, container, false)
        binding = FragmentPunchPointQatBinding.bind(viewlay)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
    }

    private fun setRecyclerView() {
        val adapter = QatPunchPointAdapter(this)
        binding.recyclerViewOpen.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewOpen.adapter = adapter
        binding.back.setOnClickListener {
            requireActivity().onBackPressed()
        }
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
//        Toast.makeText(requireActivity(),"Click event not implemented", Toast.LENGTH_SHORT).show()
    }

    override fun addPunchPoint() {
        var dialog = PunchPointCreateDialog(requireContext())
        dialog.show()
    }

    override fun punchPointClicked() {
        var dialog = PunchPointResolveDialog(requireContext())
        dialog.show()
    }
}