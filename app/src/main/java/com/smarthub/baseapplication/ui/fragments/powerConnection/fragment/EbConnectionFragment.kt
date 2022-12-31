package com.smarthub.baseapplication.ui.fragments.powerConnection.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.databinding.EbConnectionFragmentBinding
import com.smarthub.baseapplication.ui.fragments.powerConnection.adapter.TarifftableAdapter
import com.smarthub.baseapplication.ui.fragments.powerConnection.dialouge.EbDetailsDialouge
import com.smarthub.baseapplication.ui.fragments.powerConnection.pojo.PowerAndFuel
import com.smarthub.baseapplication.ui.fragments.powerConnection.pojo.PowerAndFuelEBConnectionTariffsDetail
import com.smarthub.baseapplication.ui.utilites.editdialouge.InstalationAcceptanceDialouge
import com.smarthub.baseapplication.utils.Utils

class EbConnectionFragment : Fragment(), TarifftableAdapter.TrafilListiner {

    lateinit var binding: EbConnectionFragmentBinding
    var data: PowerAndFuel? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EbConnectionFragmentBinding.inflate(inflater, container, false)
        setView()
        return binding.root
    }

    fun setDatavalue(data: PowerAndFuel) {
        this.data = data
    }

    fun setView() {
        setData()
        binding.equipmentEdit.setOnClickListener {
            val dalouge = EbDetailsDialouge()
            dalouge.show(childFragmentManager, "")
        }
        binding.editInstanlation.setOnClickListener {
            val dalouge = InstalationAcceptanceDialouge()
            dalouge.show(childFragmentManager, "")
        }
        binding.equipmentRoot.setOnClickListener {
            if (binding.itemCollapseEquipment.visibility == View.VISIBLE) {
                Utils.collapse(binding.itemCollapseEquipment)
                binding.equipmentArrow.rotation = 0f
                binding.equipmentEdit.visibility = View.GONE
                binding.equipmentRoot.isSelected = false
            } else {
                Utils.expand(binding.itemCollapseEquipment)
                binding.equipmentRoot.isSelected = true
                binding.equipmentArrow.rotation = 180f
                binding.equipmentEdit.visibility = View.VISIBLE
            }
        }




        binding.instanlationRoot.setOnClickListener {
            if (binding.itemCollapseAcceptance.visibility == View.VISIBLE) {
                Utils.collapse(binding.itemCollapseAcceptance)
                binding.instanlationRoot.isSelected = false
                binding.instalationArrow.rotation = 0f
                binding.editInstanlation.visibility = View.GONE
            } else {
                Utils.expand(binding.itemCollapseAcceptance)
                binding.instanlationRoot.isSelected = true
                binding.instalationArrow.rotation = 180f
                binding.editInstanlation.visibility = View.VISIBLE
            }
        }


    }

    override fun trafil(position: Int) {
//        TODO("Not yet implemented")
    }

    fun setData(){
        if (data != null) {
            if (data!!.PowerAndFuelEBConnection != null && data!!.PowerAndFuelEBConnection!!.size > 0) {
                if (data!!.PowerAndFuelEBConnection!!.get(0).PowerAndFuelEBConnectionEBDetails != null && data!!.PowerAndFuelEBConnection!!.get(
                        0
                    ).PowerAndFuelEBConnectionEBDetails!!.size > 0
                ) {
                    data!!.PowerAndFuelEBConnection!!.get(0).PowerAndFuelEBConnectionEBDetails.get(0)
                        .let {
                            binding.powerConnectionType.text = it.PowerConnectionType
                            binding.electricitySupplier.text = it.ElectricitySupplier
                            binding.ebSanctionedLoad.text = it.EBSanctionedLoad
                            binding.connectedLoad.text = it.ConnectedLoad
                            binding.consumerNo.text = it.ConsumerNumber
                            binding.meterType.text = "...."
                            binding.meterSerialNumber.text = it.MeterSerialNumber
                            binding.supplyVoltage.text = it.SupplyVoltage
                            binding.cabinetSize.text = it.CabinetSize
                            binding.locationMark.text = it.LocationMark
                            binding.avgAvailibillityHrs.text = it.AverageAvailibility
                            binding.ownerCompany.text = it.OwnerCompany
                            binding.userCompany.text = it.UserCompany
                            binding.connectionType.text = it.ConnectionType
                        }
                }
                if (data!!.PowerAndFuelEBConnection!!.get(0).TowerAndCivilInfraTowerInstallationAndAcceptance != null && data!!.PowerAndFuelEBConnection!!.get(
                        0
                    ).TowerAndCivilInfraTowerInstallationAndAcceptance!!.size > 0
                ) {
                    data!!.PowerAndFuelEBConnection!!.get(0).TowerAndCivilInfraTowerInstallationAndAcceptance.get(
                        0
                    ).let {
                        binding.installationVendor.text = it.InstallationVendor
                        binding.installationDate.text = it.InstallationDate
                        binding.excutiveName.text = it.VendorExecutive
                        binding.excutiveNum.text = it.VendorPhoneNumber
                        binding.acceptanceStatus.text = it.AcceptanceStatus
                        binding.operationalStatus.text = it.OperationalStatus
                        binding.condtionalAcceptance.text = it.ConditionalAcceptanceDate
                        binding.finalDateAcceptanceData.text = it.FinalAcceptanceDate
                        binding.nextPmDate.text = it.NextPMDate

                    }
                }
                if (data!!.PowerAndFuelEBConnection!!.get(0).PowerAndFuelEBConnectionTariffsDetails != null && data!!.PowerAndFuelEBConnection!!.get(0).PowerAndFuelEBConnectionTariffsDetails.size > 0
                ) {
                    binding.tarifftable.adapter = TarifftableAdapter(requireContext(),
                        data!!.PowerAndFuelEBConnection!!.get(0).PowerAndFuelEBConnectionTariffsDetails as ArrayList<PowerAndFuelEBConnectionTariffsDetail>,object:TarifftableAdapter.TrafilListiner{
                        override fun trafil(position: Int) {
                        }
                    })
                }

            }
        }

    }



}