package com.smarthub.baseapplication.ui.fragments.utilites.fireExtinguisher.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.UtilityFireExtEquipmentDetailsBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilitySMPSEquipment
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class UtilityFireEquipTableAdapter(var baseFragment: BaseFragment, FireEquipData: ArrayList<UtilitySMPSEquipment>?,var listener: UtilityFireEquipListListener) : RecyclerView.Adapter<UtilityFireEquipTableAdapter.ViewHold>() {

    var list = ArrayList<Any>()
    var currentOpened = -1
    fun setData(data: ArrayList<UtilitySMPSEquipment>?) {
        this.list.clear()
        this.list.addAll(data!!)
        notifyDataSetChanged()
    }
    init {
        if (FireEquipData!=null && FireEquipData.isNotEmpty()){
            list.clear()
            list.addAll(FireEquipData)
        }
        else
        {
            list.clear()
            list.add("empty")
        }
        currentOpened=AppPreferences.getInstance().getInteger("UtilityFireEquipTableAdapter")
    }


    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)
    
    class EquipViewHold(itemView: View) : ViewHold(itemView) {
        var binding : UtilityFireExtEquipmentDetailsBinding = UtilityFireExtEquipmentDetailsBinding.bind(itemView)

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
        return if (list[position] is UtilitySMPSEquipment)
            1
        else 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        return if (viewType == 1){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.utility_fire_ext_equipment_details, parent, false)
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
                val dataList =list[position] as UtilitySMPSEquipment
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

                  holder.binding.itemTitleStr.text = String.format(baseFragment.requireContext().resources.getString(R.string.rf_antenna_title_str_formate),
                      dataList.SerialNumber,
                      dataList.Model, Utils.getFormatedDate(dataList.WarrantyExpiryDate,"ddMMMyyyy"))

                // view mode
                holder.binding.SerialNumber.text=dataList.SerialNumber
                holder.binding.Make.text=dataList.Make
                holder.binding.Model.text=dataList.Model
                holder.binding.CapacityRating.text=dataList.CapacityRating
                holder.binding.SizeL.text=dataList.SizeL
                holder.binding.SizeB.text=dataList.SizeB
                holder.binding.SizeH.text=dataList.SizeH
                holder.binding.Weight.text=dataList.Weight
                holder.binding.LocationMark.text=dataList.LocationMark
                holder.binding.ManufacturingMonthYear.text= Utils.getFormatedDateMonthYear(dataList.ManufacturedOn,"MMM-yyyy")
                holder.binding.WarrantyPeriod.text=dataList.WarrantyPeriod
                holder.binding.WarrantyExpiryDate.text= Utils.getFormatedDate(dataList.WarrantyExpiryDate,"dd-MMM-yyyy")
                holder.binding.remarks.text=dataList.Remark
                if (dataList.OperationStatus?.isNotEmpty()==true)
                    AppPreferences.getInstance().setDropDown(holder.binding.OperationalStatus, DropDowns.OperationStatus.name,dataList.OperationStatus?.get(0).toString())
                if (dataList.Type!=null)
                    AppPreferences.getInstance().setDropDown(holder.binding.Type, DropDowns.Type.name,dataList.Type)
                if (dataList.Class!=null)
                    AppPreferences.getInstance().setDropDown(holder.binding.Class, DropDowns.Class.name,dataList.Class.toString())
                if (dataList.InstalledLocationType!=null)
                    AppPreferences.getInstance().setDropDown(holder.binding.InstalledLocationType, DropDowns.InstallationLocationType.name,dataList.InstalledLocationType.toString())
                // edit mode
                holder.binding.SerialNumberEdit.setText(dataList.SerialNumber)
                holder.binding.MakeEdit.setText(dataList.Make)
                holder.binding.ModelEdit.setText(dataList.Model)
                holder.binding.CapacityRatingEdit.setText(dataList.CapacityRating)
                holder.binding.SizeLEdit.setText(dataList.SizeL)
                holder.binding.SizeBEdit.setText(dataList.SizeB)
                holder.binding.SizeHEdit.setText(dataList.SizeH)
                holder.binding.WeightEdit.setText(dataList.Weight)
                holder.binding.LocationMarkEdit.setText(dataList.LocationMark)
                holder.binding.ManufacturingMonthYearEdit.text= Utils.getFormatedDateMonthYear(dataList.ManufacturedOn,"MMM-yyyy")
                holder.binding.WarrantyPeriodEdit.setText(dataList.WarrantyPeriod)
                holder.binding.WarrantyExpiryDateEdit.text= Utils.getFormatedDate(dataList.WarrantyExpiryDate,"dd-MMM-yyyy")
                holder.binding.remarksEdit.setText(dataList.Remark)
                
                if (dataList.OperationStatus?.isNotEmpty()==true)
                    AppPreferences.getInstance().setDropDown(holder.binding.OperationalStatusEdit, DropDowns.OperationStatus.name,dataList.OperationStatus?.get(0).toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.OperationalStatusEdit, DropDowns.OperationStatus.name)
                if (dataList.Class!=null)
                    AppPreferences.getInstance().setDropDown(holder.binding.ClassEdit, DropDowns.Class.name,dataList.Class.toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.ClassEdit, DropDowns.Class.name)
                if (dataList.InstalledLocationType!=null)
                    AppPreferences.getInstance().setDropDown(holder.binding.InstallationLocationTypeEdit, DropDowns.InstallationLocationType.name,dataList.InstalledLocationType.toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.InstallationLocationTypeEdit, DropDowns.InstallationLocationType.name)
                if (dataList.Type!=null)
                    AppPreferences.getInstance().setDropDown(holder.binding.TypeEdit, DropDowns.Type.name,dataList.Type)
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.TypeEdit, DropDowns.Type.name)

                holder.binding.update.setOnClickListener {
                    val tempCableData=UtilitySMPSEquipment()
                    tempCableData.let {
                        it.Type=holder.binding.TypeEdit.selectedValue.id
                        it.Class=holder.binding.ClassEdit.selectedValue.id.toIntOrNull()
                        it.SerialNumber=holder.binding.SerialNumberEdit.text.toString()
                        it.Make=holder.binding.MakeEdit.text.toString()
                        it.Model=Utils.getFullFormatedDate(holder.binding.ModelEdit.text.toString())
                        it.InstalledLocationType=holder.binding.InstallationLocationTypeEdit.selectedValue.id.toIntOrNull()
                        it.CapacityRating=holder.binding.CapacityRatingEdit.text.toString()
                        it.SizeL=holder.binding.SizeLEdit.text.toString()
                        it.SizeB=holder.binding.SizeBEdit.text.toString()
                        it.SizeH=holder.binding.SizeHEdit.text.toString()
                        it.Weight=holder.binding.WeightEdit.text.toString()
                        it.ManufacturedOn=Utils.getFullFormatedDate(holder.binding.ManufacturingMonthYearEdit.text.toString())
                        it.WarrantyExpiryDate=Utils.getFullFormatedDate(holder.binding.WarrantyExpiryDateEdit.text.toString())
                        it.WarrantyPeriod=holder.binding.WarrantyPeriodEdit.text.toString()
                        it.LocationMark=holder.binding.LocationMarkEdit.text.toString()
                        it.OperationStatus = arrayListOf(holder.binding.OperationalStatusEdit.selectedValue.id.toInt())
                        it.Remark=holder.binding.remarksEdit.text.toString()
                        if (dataList.id!=null)
                            it.id=dataList.id
                    }
                    listener.updateUtilityFireEquipData(tempCableData)
                    AppPreferences.getInstance().saveInteger("UtilityFireEquipTableAdapter",currentOpened)
                }
                baseFragment.setDatePickerView(holder.binding.WarrantyExpiryDateEdit)
                baseFragment.setDatePickerView(holder.binding.ManufacturingMonthYearEdit)

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



    interface UtilityFireEquipListListener {
        fun updateUtilityFireEquipData(updatedData: UtilitySMPSEquipment)
    }

}
