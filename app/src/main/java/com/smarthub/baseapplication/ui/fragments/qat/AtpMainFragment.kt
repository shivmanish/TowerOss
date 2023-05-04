package com.smarthub.baseapplication.ui.fragments.qat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AtpMainFragmentBinding
import com.smarthub.baseapplication.databinding.QatAddNewDialogBinding
import com.smarthub.baseapplication.listeners.QatListListener
import com.smarthub.baseapplication.ui.adapter.AtpCardListAdapter
import com.smarthub.baseapplication.ui.adapter.AtpListAdapter

class AtpMainFragment : Fragment(), QatListListener {

    var binding: AtpMainFragmentBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.atp_main_fragment, container, false)
        binding = AtpMainFragmentBinding.bind(view)
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding?.atpList?.adapter = AtpListAdapter(this@AtpMainFragment)
        binding?.imgBack?.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    override fun itemClicked() {
        var fragment = QatCheckNestedList()
        (requireActivity() as QATCheckActivity).addFragment(fragment)
    }

    override fun addNewClicked(adapter : AtpCardListAdapter) {
        val dialogBuilder = AlertDialog.Builder(requireContext())
        var binding = QatAddNewDialogBinding.inflate(layoutInflater)
        dialogBuilder.setView(binding.root)
        var b: AlertDialog = dialogBuilder.create()

        binding.btnCreate.setOnClickListener {
            adapter.addListItem()
            b.dismiss()
//            Toast.makeText(requireContext(),"Item Added",Toast.LENGTH_SHORT).show()
        }
        b.setCanceledOnTouchOutside(false)
        b.show()
    }

    override fun cardClicked() {
        var fragment = QatCheckNestedList()
        (requireActivity() as QATCheckActivity).addFragment(fragment)
    }


}