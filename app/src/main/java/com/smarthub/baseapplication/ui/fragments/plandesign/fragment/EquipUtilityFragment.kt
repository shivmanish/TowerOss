package com.smarthub.baseapplication.ui.fragments.plandesign.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.databinding.UtilityEquipFragmentBinding
import com.smarthub.baseapplication.ui.fragments.plandesign.dialouge.*
import com.smarthub.baseapplication.ui.utilites.editdialouge.BatteryEquipmentDialouge
import com.smarthub.baseapplication.ui.utilites.editdialouge.InstalationAcceptanceDialouge
import com.smarthub.baseapplication.utils.Utils

class EquipUtilityFragment : Fragment() {

    lateinit var binding: UtilityEquipFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = UtilityEquipFragmentBinding.inflate(inflater, container, false)
        setView()
        return binding.root
    }

    fun setView() {
        binding.smpsEdit.setOnClickListener {
            val dalouge = SMPSDialouge()
            dalouge.show(childFragmentManager, "")
        }

        binding.smpsRoot.setOnClickListener {
            if (binding.itemCollapseSmps.visibility == View.VISIBLE) {
                Utils.collapse(binding.itemCollapseSmps)
                binding.smpsArrow.rotation = 0f
                binding.smpsEdit.visibility = View.GONE
                binding.smpsRoot.isSelected = false
            } else {
                Utils.expand(binding.itemCollapseSmps)
                binding.smpsRoot.isSelected = true
                binding.smpsArrow.rotation = 180f
                binding.smpsEdit.visibility = View.VISIBLE
            }
        }

        binding.bBankEdit.setOnClickListener {
            val dalouge = BatteryBankDialouge()
            dalouge.show(childFragmentManager, "")
        }
        binding.bBankRoot.setOnClickListener {
            if (binding.itemCollapseBBank.visibility == View.VISIBLE) {
                Utils.collapse(binding.itemCollapseBBank)
                binding.bBankRoot.isSelected = false
                binding.bBankArrow.rotation = 0f
                binding.bBankEdit.visibility = View.GONE
            } else {
                Utils.expand(binding.itemCollapseBBank)
                binding.bBankRoot.isSelected = true
                binding.bBankArrow.rotation = 180f
                binding.bBankEdit.visibility = View.VISIBLE
            }
        }


        binding.dgEdit.setOnClickListener {
            val dalouge = DgDialouge()
            dalouge.show(childFragmentManager, "")
        }
        binding.dgRoot.setOnClickListener {
            if (binding.itemCollapseDg.visibility == View.VISIBLE) {
                Utils.collapse(binding.itemCollapseDg)
                binding.dgRoot.isSelected = false
                binding.dgArrow.rotation = 0f
                binding.dgEdit.visibility = View.GONE
            } else {
                Utils.expand(binding.itemCollapseDg)
                binding.dgRoot.isSelected = true
                binding.dgArrow.rotation = 180f
                binding.dgEdit.visibility = View.VISIBLE
            }
        }

        binding.acEdit.setOnClickListener {
            val dalouge = AcUtilityDialouge()
            dalouge.show(childFragmentManager, "")
        }
        binding.acRoot.setOnClickListener {
            if (binding.itemCollapseAc.visibility == View.VISIBLE) {
                Utils.collapse(binding.itemCollapseAc)
                binding.acRoot.isSelected = false
                binding.acArrow.rotation = 0f
                binding.acEdit.visibility = View.GONE
            } else {
                Utils.expand(binding.itemCollapseAc)
                binding.acRoot.isSelected = true
                binding.acArrow.rotation = 180f
                binding.acEdit.visibility = View.VISIBLE
            }
        }

        binding.fireEdit.setOnClickListener {
            val dalouge = FireDialouge()
            dalouge.show(childFragmentManager, "")
        }
        binding.fireRoot.setOnClickListener {
            if (binding.itemCollapseFire.visibility == View.VISIBLE) {
                Utils.collapse(binding.itemCollapseFire)
                binding.fireRoot.isSelected = false
                binding.fireArrow.rotation = 0f
                binding.fireEdit.visibility = View.GONE
            } else {
                Utils.expand(binding.itemCollapseFire)
                binding.fireRoot.isSelected = true
                binding.fireArrow.rotation = 180f
                binding.fireEdit.visibility = View.VISIBLE
            }
        }

        binding.surgeProctetorEdit.setOnClickListener {
            val dalouge = SurgeProtectroDeviceDialouge()
            dalouge.show(childFragmentManager, "")
        }
        binding.surgeProctetorRoot.setOnClickListener {
            if (binding.itemCollapseSurgeProctetor.visibility == View.VISIBLE) {
                Utils.collapse(binding.itemCollapseSurgeProctetor)
                binding.surgeProctetorRoot.isSelected = false
                binding.surgeProctetorArrow.rotation = 0f
                binding.surgeProctetorEdit.visibility = View.GONE
            } else {
                Utils.expand(binding.itemCollapseSurgeProctetor)
                binding.surgeProctetorRoot.isSelected = true
                binding.surgeProctetorArrow.rotation = 180f
                binding.surgeProctetorEdit.visibility = View.VISIBLE
            }
        }



        binding.dcdbEdit.setOnClickListener {
            val dalouge = DcdbDialouge()
            dalouge.show(childFragmentManager, "")
        }
        binding.dcdbRoot.setOnClickListener {
            if (binding.itemCollapseDcdb.visibility == View.VISIBLE) {
                Utils.collapse(binding.itemCollapseDcdb)
                binding.dcdbRoot.isSelected = false
                binding.dcdbArrow.rotation = 0f
                binding.dcdbEdit.visibility = View.GONE
            } else {
                Utils.expand(binding.itemCollapseDcdb)
                binding.dcdbRoot.isSelected = true
                binding.dcdbArrow.rotation = 180f
                binding.dcdbEdit.visibility = View.VISIBLE
            }
        }

        


    }

}