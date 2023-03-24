package com.smarthub.baseapplication.ui.fragments.utilites.surgeProtectionDevice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.BaseActivity
import com.smarthub.baseapplication.databinding.UtilitySurgProtectionDeviceDetailsBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityEquipmentSurgeProtectionDevice
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilitySMPSEquipment
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtiltyInstallationAcceptence
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class UtilitySurgFragAdapter(var baseActivity: BaseActivity, var listener: UtilitySurgListListener, surgDataData: ArrayList<UtilityEquipmentSurgeProtectionDevice>?) : RecyclerView.Adapter<UtilitySurgFragAdapter.ViewHold>() {
//    private var equipmentData: UtilitySMPSEquipment?=null
//    private var InsAccepData: UtiltyInstallationAcceptence?=null

    var list = ArrayList<Any>()

    fun setData(data: ArrayList<UtilityEquipmentSurgeProtectionDevice>?) {
        this.list.clear()
        this.list.addAll(data!!)
        notifyDataSetChanged()
    }
    init {
        if (surgDataData!=null && surgDataData.isNotEmpty()){
            list.clear()
            list.addAll(surgDataData)
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
        var binding : UtilitySurgProtectionDeviceDetailsBinding = UtilitySurgProtectionDeviceDetailsBinding.bind(itemView)

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
            val view = LayoutInflater.from(parent.context).inflate(R.layout.utility_surg_protection_device_details, parent, false)
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
                val datalist=list[position] as UtilityEquipmentSurgeProtectionDevice
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
                    holder.binding.itemTitleStr.text = String.format(baseActivity.resources.getString(R.string.rf_antenna_title_str_formate),equipmentData.SerialNumber,equipmentData.Model, Utils.getFormatedDate(InsAccepData.InstallationDate,"ddMMMyyyy"))
                if (equipmentData!=null){
                    // view mode
                    holder.binding.Type.text=equipmentData.Type
                    holder.binding.SerialNumber.text=equipmentData.SerialNumber
                    holder.binding.Make.text=equipmentData.Make
                    holder.binding.Model.text=equipmentData.Model
                    holder.binding.ProtectionMode.text=equipmentData.ProtectionMode
                    holder.binding.InstallationPoint.text=equipmentData.LocationMark
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
                    holder.binding.ProtectionModeEdit.setText(equipmentData.ProtectionMode)
                    holder.binding.InstallationPointEdit.setText(equipmentData.LocationMark)
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
                        it.ProtectionMode=holder.binding.ProtectionModeEdit.text.toString()
                        it.LocationMark=holder.binding.InstallationPointEdit.text.toString()
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

                    val utilitySurgData= UtilityEquipmentSurgeProtectionDevice()
                    utilitySurgData.Equipment= arrayListOf(tempEquipData)
                    utilitySurgData.InstallationAndAcceptence= arrayListOf(tempInsData)
                    utilitySurgData.id=datalist.id
                    listener.updateUtilitySurgeData(utilitySurgData)
                }
                baseActivity.setDatePickerView(holder.binding.InstallationDateEdit)

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
        fun updateUtilitySurgeData(updatedData: UtilityEquipmentSurgeProtectionDevice)
    }

}
