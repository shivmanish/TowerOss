package com.smarthub.baseapplication.ui.fragments.plandesign.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.databinding.NocCompFragmentBinding
import com.smarthub.baseapplication.ui.utilites.editdialouge.InstalationAcceptanceDialouge
import com.smarthub.baseapplication.utils.Utils

class NocCompFragment : Fragment() {
    lateinit var binding: NocCompFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NocCompFragmentBinding.inflate(inflater, container, false)
        setView()
        return binding.root
    }

    fun setView() {

        binding.nocEdit.setOnClickListener {
            val dalouge = InstalationAcceptanceDialouge()
            dalouge.show(childFragmentManager, "")
        }

        binding.nocRoot.setOnClickListener {
            if (binding.itemCollapseNoc.visibility == View.VISIBLE) {
                Utils.collapse(binding.itemCollapseNoc)
                binding.nocArrow.rotation = 0f
                binding.nocEdit.visibility = View.GONE
                binding.nocRoot.isSelected = false
            } else {
                Utils.expand(binding.itemCollapseNoc)
                binding.nocRoot.isSelected = true
                binding.nocArrow.rotation = 180f
                binding.nocEdit.visibility = View.VISIBLE
            }
        }

    }

}