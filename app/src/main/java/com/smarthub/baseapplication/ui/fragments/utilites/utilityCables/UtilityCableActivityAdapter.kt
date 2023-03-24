package com.smarthub.baseapplication.ui.fragments.utilites.utilityCables

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.BaseActivity
import com.smarthub.baseapplication.databinding.UtilityCableDetailsBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityCableDetail
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class UtilityCableActivityAdapter(var baseActivity: BaseActivity, var listener: UtilityCableListListener, cableDataData: ArrayList<UtilityCableDetail>?) : RecyclerView.Adapter<UtilityCableActivityAdapter.ViewHold>() {

    var list = ArrayList<Any>()

    fun setData(data: ArrayList<UtilityCableDetail>?) {
        this.list.clear()
        this.list.addAll(data!!)
        notifyDataSetChanged()
    }
    init {
        if (cableDataData!=null && cableDataData.isNotEmpty()){
            list.clear()
            list.addAll(cableDataData)
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
        var binding : UtilityCableDetailsBinding = UtilityCableDetailsBinding.bind(itemView)

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
        return if (list[position] is UtilityCableDetail)
            1
        else 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        return if (viewType == 1){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.utility_cable_details, parent, false)
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
                val dataList =list[position] as UtilityCableDetail
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

                if (dataList.CableName?.isNotEmpty()==true)
                  holder.binding.itemTitleStr.text = String.format(baseActivity.resources.getString(R.string.rf_antenna_title_str_formate),
                      AppPreferences.getInstance().getDropDownValue(DropDowns.CableName.name,dataList.CableName?.get(0).toString()),
                      dataList.CableType, Utils.getFormatedDate(dataList.InstallationDate,"ddMMMyyyy"))

                // view mode
                holder.binding.Type.text=dataList.CableType
                holder.binding.UsedFor.text=dataList.UsedFor
                holder.binding.Length.text=dataList.Length
                holder.binding.InstallationDate.text=Utils.getFormatedDate(dataList.InstallationDate,"dd-MMM-yyyy")
                holder.binding.VendorCode.text=dataList.VendorCode
                holder.binding.remarks.text=dataList.Remark
                if (dataList.CableName?.isNotEmpty()==true)
                    AppPreferences.getInstance().setDropDown(holder.binding.CableName,DropDowns.CableName.name,dataList.CableName?.get(0).toString())
                if (dataList.VendorCompany?.isNotEmpty()==true)
                    AppPreferences.getInstance().setDropDown(holder.binding.VendorName,DropDowns.VendorCompany.name,dataList.VendorCompany?.get(0).toString())

                // edit mode
                holder.binding.TypeEdit.setText(dataList.CableType)
                holder.binding.UsedForEdit.setText(dataList.UsedFor)
                holder.binding.LengthEdit.setText(dataList.Length)
                holder.binding.InstallationDateEdit.text=Utils.getFormatedDate(dataList.InstallationDate,"dd-MMM-yyyy")
                holder.binding.VendorCodeEdit.setText(dataList.VendorCode)
                holder.binding.remarksEdit.setText(dataList.Remark)

                if (dataList.VendorCompany?.isNotEmpty()==true)
                    AppPreferences.getInstance().setDropDown(holder.binding.VendorNameEdit, DropDowns.VendorCompany.name,dataList.VendorCompany?.get(0).toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.VendorNameEdit, DropDowns.VendorCompany.name)
                if (dataList.CableName?.isNotEmpty()==true)
                    AppPreferences.getInstance().setDropDown(holder.binding.CableNameEdit, DropDowns.CableName.name,dataList.CableName?.get(0).toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.CableNameEdit, DropDowns.CableName.name)

                holder.binding.update.setOnClickListener {
                    val tempCableData=UtilityCableDetail()
                    tempCableData.let {
                        it.CableType=holder.binding.TypeEdit.text.toString()
                        it.UsedFor=holder.binding.UsedForEdit.text.toString()
                        it.Length=holder.binding.LengthEdit.text.toString()
                        it.InstallationDate=Utils.getFullFormatedDate(holder.binding.InstallationDateEdit.text.toString())
                        it.VendorCode=holder.binding.VendorCodeEdit.text.toString()
                        it.Remark=holder.binding.remarksEdit.text.toString()
                        it.CableName= arrayListOf(holder.binding.CableNameEdit.selectedValue.id.toInt())
                        it.VendorCompany= arrayListOf(holder.binding.VendorNameEdit.selectedValue.id.toInt())
                        if (dataList.id!=null)
                            it.id=dataList.id
                    }

                    listener.updateUtilityCableData(tempCableData)
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



    interface UtilityCableListListener {
        fun updateUtilityCableData(updatedData: UtilityCableDetail)
    }

}
