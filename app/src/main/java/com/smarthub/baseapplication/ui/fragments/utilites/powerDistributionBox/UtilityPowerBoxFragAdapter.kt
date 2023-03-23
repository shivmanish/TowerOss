package com.smarthub.baseapplication.ui.fragments.utilites.powerDistributionBox

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.*
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class UtilityPowerBoxFragAdapter(var context: Context, var listener: UtilityPowerBoxListListener, powerDataData: ArrayList<UtilityEquipmentPowerDistributionBox>?) : RecyclerView.Adapter<UtilityPowerBoxFragAdapter.ViewHold>() {
//    private var equipmentData: UtilitySMPSEquipment?=null
//    private var InsAccepData: UtiltyInstallationAcceptence?=null

    var list = ArrayList<Any>()

    fun setData(data: ArrayList<UtilityEquipmentPowerDistributionBox>?) {
        this.list.clear()
        this.list.addAll(data!!)
        notifyDataSetChanged()
    }
    init {
        if (powerDataData!=null && powerDataData.isNotEmpty()){
            list.clear()
            list.addAll(powerDataData)
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
        var binding : UtilityPowerDistributionBoxDetailsBinding = UtilityPowerDistributionBoxDetailsBinding.bind(itemView)

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
        return if (list[position] is UtilityEquipmentPowerDistributionBox)
            1
        else 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        return if (viewType == 1){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.utility_power_distribution_box_details, parent, false)
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
                var equipmentData: UtilitySMPSEquipment?=null
                var InsAccepData: UtiltyInstallationAcceptence?=null
                val datalist=list[position] as UtilityEquipmentPowerDistributionBox
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
                if (datalist.Equipment?.isNotEmpty()==true)
                    equipmentData=datalist.Equipment?.get(0)
                if (datalist.InstallationAndAcceptence?.isNotEmpty()==true)
                    InsAccepData=datalist.InstallationAndAcceptence?.get(0)

                if (equipmentData!=null && InsAccepData!=null)
                    holder.binding.itemTitleStr.text = String.format(context.resources.getString(R.string.rf_antenna_title_str_formate),equipmentData.SerialNumber,equipmentData.Model, Utils.getFormatedDate(InsAccepData.InstallationDate,"ddMMMyyyy"))
                if (equipmentData!=null){
                    // view mode
                    holder.binding.Type.text=equipmentData.Type
                    holder.binding.SerialNumber.text=equipmentData.SerialNumber
                    holder.binding.Make.text=equipmentData.Make
                    holder.binding.Model.text=equipmentData.Model
                    holder.binding.Weight.text=equipmentData.Weight
                    holder.binding.SizeL.text=equipmentData.SizeL
                    holder.binding.SizeB.text=equipmentData.SizeB
                    holder.binding.SizeH.text=equipmentData.SizeH
                    holder.binding.CapacityRating.text=equipmentData.CapacityRating
                    holder.binding.Count.text= equipmentData.Count.toString()
                    holder.binding.remarks.text=equipmentData.Remark

                    // edit mode
                    holder.binding.TypeEdit.setText(equipmentData.Type)
                    holder.binding.SerialNumberEdit.setText(equipmentData.SerialNumber)
                    holder.binding.MakeEdit.setText(equipmentData.Make)
                    holder.binding.ModelEdit.setText(equipmentData.Model)
                    holder.binding.CapacityRatingEdit.setText(equipmentData.CapacityRating)
                    holder.binding.CountEdit.setText(equipmentData.Count.toString())
                    holder.binding.WeightEdit.setText(equipmentData.Weight)
                    holder.binding.SizeLEdit.setText(equipmentData.SizeL)
                    holder.binding.SizeBEdit.setText(equipmentData.SizeB)
                    holder.binding.SizeHEdit.setText(equipmentData.SizeH)
                    holder.binding.remarksEdit.setText(equipmentData.Remark)
                }
                if (InsAccepData!=null){
                    // view mode
                    holder.binding.VendorCode.text=InsAccepData.VendorCode
                    holder.binding.InstallationDate.text= Utils.getFormatedDate(InsAccepData.InstallationDate,"dd-MMM-yyyy")
                    if (InsAccepData.VendorCompany?.isNotEmpty()==true){
                        AppPreferences.getInstance().setDropDown(holder.binding.VendorName, DropDowns.VendorCompany.name,InsAccepData.VendorCompany?.get(0).toString())
                    }

                    // edit code
                    holder.binding.VendorCodeEdit.setText(InsAccepData.VendorCode)
                    holder.binding.InstallationDateEdit.text= Utils.getFormatedDate(InsAccepData.InstallationDate,"dd-MMM-yyyy")
                }

                if (InsAccepData!=null && InsAccepData.VendorCompany?.isNotEmpty()==true)
                    AppPreferences.getInstance().setDropDown(holder.binding.VendorNameEdit, DropDowns.VendorCompany.name,InsAccepData.VendorCompany?.get(0).toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.VendorNameEdit, DropDowns.VendorCompany.name)
                holder.binding.update.setOnClickListener {
                    val tempEquipData=UtilitySMPSEquipment()
                    val tempInsData=UtiltyInstallationAcceptence()
                    tempEquipData.let {
                        it.Type=holder.binding.TypeEdit.text.toString()
                        it.SerialNumber=holder.binding.SerialNumberEdit.text.toString()
                        it.Make=holder.binding.MakeEdit.text.toString()
                        it.Model=holder.binding.ModelEdit.text.toString()
                        it.CapacityRating=holder.binding.CapacityRatingEdit.text.toString()
                        it.Weight=holder.binding.WeightEdit.text.toString()
                        it.SizeL=holder.binding.SizeLEdit.text.toString()
                        it.SizeB=holder.binding.SizeBEdit.text.toString()
                        it.SizeH=holder.binding.SizeHEdit.text.toString()
                        it.Count=holder.binding.CountEdit.text.toString().toIntOrNull()
                        it.Remark=holder.binding.remarksEdit.text.toString()
                        if (equipmentData!=null)
                            it.id=equipmentData.id
                    }
                    tempInsData.let {
                        it.VendorCode=holder.binding.VendorCodeEdit.text.toString()
                        it.InstallationDate=Utils.getFullFormatedDate(holder.binding.InstallationDateEdit.text.toString())
                        it.VendorCompany= arrayListOf(holder.binding.VendorNameEdit.selectedValue.id.toInt())
                        if (InsAccepData!=null)
                            it.id=InsAccepData.id
                    }

                    val utilityPowerBoxData= UtilityEquipmentPowerDistributionBox()
                    utilityPowerBoxData.Equipment= arrayListOf(tempEquipData)
                    utilityPowerBoxData.InstallationAndAcceptence= arrayListOf(tempInsData)
                    utilityPowerBoxData.id=datalist.id
                    listener.updateUtilityPowerBoxData(utilityPowerBoxData)
                }
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



    interface UtilityPowerBoxListListener {
        fun updateUtilityPowerBoxData(updatedData: UtilityEquipmentPowerDistributionBox)
    }

}
