package com.smarthub.baseapplication.ui.fragments.utilites.surgeProtectionDevice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.model.siteIBoard.newNocAndComp.NocCompAllData
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.*
import com.smarthub.baseapplication.ui.fragments.BaseFragment

class UtilitySurgFragAdapter(var listener: UtilitySurgListListener, surgDataData: ArrayList<UtilityEquipmentSurgeProtectionDevice>?) : RecyclerView.Adapter<UtilitySurgFragAdapter.ViewHold>() {
    private var datalist: UtilityEquipmentAC?=null
    private var equipmentData: UtilitySMPSEquipment?=null
    private var InsAccepData: UtiltyInstallationAcceptence?=null

    var list = ArrayList<Any>()

    fun setData(data: ArrayList<UtilityEquipmentSurgeProtectionDevice>?) {
        this.list.clear()
        this.list.addAll(data!!)
        notifyDataSetChanged()
    }
    init {
        if (surgDataData!=null && surgDataData?.isNotEmpty()==true){
            list.clear()
            list.addAll(surgDataData!!)
        }
        else
        {
            list.clear()
            list.add("empty")
        }
    }

    var currentOpened = -1

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)
    
    class EquipViewHold(itemView: View) : ViewHold(itemView) {
        var binding : UtilityAcEquipmentBinding = UtilityAcEquipmentBinding.bind(itemView)

        init {
            binding.collapsingLayout.tag = false
            if ((binding.collapsingLayout.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }
        }
    }


    override fun getItemViewType(position: Int): Int {
        return if (list[position] is UtilityEquipmentSurgeProtectionDevice)
            1
        else 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        return if (viewType == 1){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.utility_ac_equipment, parent, false)
            return EquipViewHold(view)
        }
        else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.no_list_data, parent, false)
            ViewHold(view)
        }
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        when (holder) {
            is EquipViewHold -> {
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                    holder.binding.imgEdit.visibility = View.VISIBLE
                    holder.binding.viewLayout.visibility = View.VISIBLE
                    holder.binding.editLayout.visibility = View.GONE

                    holder.binding.imgEdit.setOnClickListener {
                        holder.binding.viewLayout.visibility = View.GONE
                        holder.binding.editLayout.visibility = View.VISIBLE
                    }
                    holder.binding.cancel.setOnClickListener {
                        holder.binding.viewLayout.visibility = View.VISIBLE
                        holder.binding.editLayout.visibility = View.GONE
                    }
                }
                else {
                    holder.binding.collapsingLayout.tag = false
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                    holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    holder.binding.itemLine.visibility = View.VISIBLE
                    holder.binding.itemCollapse.visibility = View.GONE
                    holder.binding.imgEdit.visibility = View.GONE
                }
                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
//                holder.binding.itemTitleStr.text = list[position]
//                if (datalist!=null && datalist?.Equipment?.isNotEmpty()==true){
//                    equipmentData=datalist?.Equipment?.get(0)
//                }
//                if (equipmentData!=null){
//                    // view mode
//                    holder.binding.Type.text=equipmentData?.Type
//                    holder.binding.SerialNumber.text=equipmentData?.SerialNumber
//                    holder.binding.Make.text=equipmentData?.Make
//                    holder.binding.Model.text=equipmentData?.Model
//                    holder.binding.MaxPowerRating.text=equipmentData?.MaxPowerRating
//                    holder.binding.OULocationMark.text=equipmentData?.LocationMark
//                    holder.binding.CapacityRating.text=equipmentData?.CapacityRating
//                    holder.binding.CopperTubing.text= equipmentData?.CopperTubing
//                    holder.binding.CoolingTempSetting.text=equipmentData?.CoolingTempSetting
//                    holder.binding.IUSizeL.text=equipmentData?.SizeL
//                    holder.binding.IUSizeB.text=equipmentData?.SizeB
//                    holder.binding.IUSizeH.text=equipmentData?.SizeH
//                    holder.binding.OUSizeL.text=equipmentData?.OUSizeL
//                    holder.binding.OUSizeB.text=equipmentData?.OUSizeB
//                    holder.binding.OUSizeH.text=equipmentData?.OUSizeH
//                    holder.binding.IUWeight.text=equipmentData?.Weight
//                    holder.binding.OUWeight.text=equipmentData?.OUWeight
//                    holder.binding.ManufacturingMonthYear.text= Utils.getFormatedDateMonthYear(equipmentData?.ManufacturedOn,"MMM-yyyy")
//                    holder.binding.WarrantyPeriod.text=equipmentData?.WarrantyPeriod
//                    holder.binding.WarrantyExpiryDate.text= Utils.getFormatedDate(equipmentData?.WarrantyExpiryDate,"dd-MMM-yyyy")
//                    holder.binding.remarks.text=equipmentData?.Remark
//                    if (equipmentData?.OperationStatus?.isNotEmpty()==true)
//                        AppPreferences.getInstance().setDropDown(holder.binding.OperationalStatus, DropDowns.OperationStatus.name,equipmentData?.OperationStatus?.get(0).toString())
//
//                    // edit mode
//                    holder.binding.TypeEdit.setText(equipmentData?.Type)
//                    holder.binding.SerialNumberEdit.setText(equipmentData?.SerialNumber)
//                    holder.binding.MakeEdit.setText(equipmentData?.Make)
//                    holder.binding.ModelEdit.setText(equipmentData?.Model)
//                    holder.binding.CapacityRatingEdit.setText(equipmentData?.CapacityRating)
//                    holder.binding.IUSizeLEdit.setText(equipmentData?.SizeL)
//                    holder.binding.IUSizeBEdit.setText(equipmentData?.SizeB)
//                    holder.binding.IUSizeHEdit.setText(equipmentData?.SizeH)
//                    holder.binding.IuWeightEdit.setText(equipmentData?.Weight)
//                    holder.binding.OUSizeLEdit.setText(equipmentData?.OUSizeL)
//                    holder.binding.OUSizeBEdit.setText(equipmentData?.OUSizeB)
//                    holder.binding.OUSizeHEdit.setText(equipmentData?.OUSizeH)
//                    holder.binding.OUWeightEdit.setText(equipmentData?.OUWeight)
//                    holder.binding.OULocationMarkEdit.setText(equipmentData?.LocationMark)
//                    holder.binding.CopperTubingEdit.setText(equipmentData?.CopperTubing)
//                    holder.binding.CoolingTempSettingEdit.setText(equipmentData?.CoolingTempSetting)
//                    holder.binding.ManufacturingMonthYearEdit.text= Utils.getFormatedDateMonthYear(equipmentData?.ManufacturedOn,"MMM-yyyy")
//                    holder.binding.WarrantyPeriodEdit.setText(equipmentData?.WarrantyPeriod)
//                    holder.binding.WarrantyExpiryDateEdit.text= Utils.getFormatedDate(equipmentData?.WarrantyExpiryDate,"dd-MMM-yyyy")
//                    holder.binding.remarksEdit.setText(equipmentData?.Remark)
//                }
//                if (equipmentData!=null && equipmentData?.OperationStatus?.isNotEmpty()==true)
//                    AppPreferences.getInstance().setDropDown(holder.binding.OperationalStatusEdit,
//                        DropDowns.OperationStatus.name,equipmentData?.OperationStatus?.get(0).toString())
//                else
//                    AppPreferences.getInstance().setDropDown(holder.binding.OperationalStatusEdit,
//                        DropDowns.OperationStatus.name)
//
//                holder.binding.update.setOnClickListener {
//                    val tempEquipData=UtilitySMPSEquipment()
//                    tempEquipData.let {
//                        it.Type=holder.binding.TypeEdit.text.toString()
//                        it.SerialNumber=holder.binding.SerialNumberEdit.text.toString()
//                        it.Make=holder.binding.MakeEdit.text.toString()
//                        it.Model=holder.binding.ModelEdit.text.toString()
//                        it.CapacityRating=holder.binding.CapacityRatingEdit.text.toString()
//                        it.SizeL=holder.binding.IUSizeLEdit.text.toString()
//                        it.SizeB=holder.binding.IUSizeBEdit.text.toString()
//                        it.SizeH=holder.binding.IUSizeHEdit.text.toString()
//                        it.Weight=holder.binding.IuWeightEdit.text.toString()
//                        it.OUSizeL=holder.binding.OUSizeLEdit.text.toString()
//                        it.OUSizeB=holder.binding.OUSizeBEdit.text.toString()
//                        it.OUSizeH=holder.binding.OUSizeHEdit.text.toString()
//                        it.OUWeight=holder.binding.OUWeightEdit.text.toString()
//                        it.LocationMark=holder.binding.OULocationMarkEdit.text.toString()
//                        it.CopperTubing=holder.binding.CopperTubingEdit.text.toString()
//                        it.MaxPowerRating=holder.binding.MaxPowerRatingEdit.text.toString()
//                        it.CoolingTempSetting=holder.binding.CoolingTempSettingEdit.text.toString()
//                        it.ManufacturedOn= Utils.getFullFormatedDate(holder.binding.ManufacturingMonthYearEdit.text.toString())
//                        it.WarrantyPeriod=holder.binding.WarrantyPeriodEdit.text.toString()
//                        it.WarrantyExpiryDate= Utils.getFullFormatedDate(holder.binding.WarrantyExpiryDateEdit.text.toString())
//                        it.Remark=holder.binding.remarksEdit.text.toString()
//                        it.OperationStatus= arrayListOf(holder.binding.OperationalStatusEdit.selectedValue.id.toInt())
//                        if (datalist!=null && datalist?.Equipment?.isNotEmpty()==true)
//                            it.id=datalist?.Equipment?.get(0)?.id
//                        val utilityACData= UtilityEquipmentAC()
//                        utilityACData.Equipment= arrayListOf(it)
//                        if (datalist!=null)
//                            utilityACData.id=datalist?.id
//                        listener.updateACData(utilityACData)
//                    }
//                }
//                baseFragment.setDatePickerView(holder.binding.WarrantyExpiryDateEdit)
//                baseFragment.setDatePickerView(holder.binding.ManufacturingMonthYearEdit)

            }

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
    var recyclerView: RecyclerView?=null
    fun updateList(position: Int){
        currentOpened = if(currentOpened == position) -1 else position
        notifyDataSetChanged()
        if (this.recyclerView!=null)
            this.recyclerView?.scrollToPosition(position)
    }



    interface UtilitySurgListListener {
        fun updateACData(updatedData: UtilityEquipmentAC)
    }

}
