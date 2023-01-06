package com.smarthub.baseapplication.ui.fragments.plandesign.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarthub.baseapplication.databinding.NocCompFragmentBinding
import com.smarthub.baseapplication.model.siteInfo.planAndDesign.NOCAndComp
import com.smarthub.baseapplication.ui.fragments.plandesign.adapter.NocCompAdapter
import com.smarthub.baseapplication.ui.utilites.editdialouge.InstalationAcceptanceDialouge

class NocCompFragment(var nocCompList:List<NOCAndComp>?) : Fragment(),NocCompAdapter.NocCompListListner {
    lateinit var binding: NocCompFragmentBinding
    lateinit var adapter: NocCompAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = NocCompFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listItem.layoutManager = LinearLayoutManager(requireContext())
        adapter= NocCompAdapter(requireContext(),this@NocCompFragment,nocCompList)
        binding.listItem.adapter=adapter
    }



    override fun attachmentItemClicked() {
        Toast.makeText(requireContext(),"attachment item clicked", Toast.LENGTH_SHORT).show()
    }

    override fun editNocComp() {
        val dalouge = InstalationAcceptanceDialouge()
        dalouge.show(childFragmentManager, "")
    }

    override fun editNocTableItem(position: Int) {
        Toast.makeText(requireContext(),"noc table edit item clicked", Toast.LENGTH_SHORT).show()
    }

    override fun viewNocTableItem(position: Int) {
        Toast.makeText(requireContext(),"noc table view item clicked", Toast.LENGTH_SHORT).show()
    }


}