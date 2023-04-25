package com.smarthub.baseapplication.ui.fragments.powerAndFuel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.PowerFuelBillsItemBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.Attachments
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.PowerFuelBills
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.ImageAttachmentCommonAdapter
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class PowerFuelBillsAdapter(var listener: PowerBillsClickListener, var baseFragment: BaseFragment, powerBillsData:ArrayList<PowerFuelBills>?) : RecyclerView.Adapter<PowerFuelBillsAdapter.ViewHold>() {

    var list = ArrayList<Any>()

    fun setData(data: ArrayList<PowerFuelBills>?) {
        this.list.clear()
        this.list.addAll(data!!)
        notifyDataSetChanged()
    }
    fun addLoading(){
        this.list.clear()
        this.list.add("loading")
        notifyDataSetChanged()
    }
    init {
        this.list.clear()
        this.list.addAll(powerBillsData!!)
    }

    fun updateItem(pos : Int,data : PowerFuelBills){
        list[pos] = data
        notifyItemChanged(pos)
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)

    class ViewHold1(itemView: View,listener: PowerBillsClickListener) : ViewHold(itemView) {
        var binding : PowerFuelBillsItemBinding = PowerFuelBillsItemBinding.bind(itemView)
        val recyclerListener:RecyclerView = binding.root.findViewById(R.id.list_item)
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        return when (viewType) {
            1 ->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.power_fuel_bills_item,parent,false)
                ViewHold1(view,listener)
            }
            2 ->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.rf_equipment_no_data,parent,false)
                ViewHold(view)
            }

            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.list_loading_bar,parent,false)
                ViewHold(view)
            }
        }

    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        if (holder is ViewHold1) {
            val data: PowerFuelBills=list[position] as PowerFuelBills
            holder.binding.imgEdit.setOnClickListener {
                holder.binding.viewLayout.visibility = View.GONE
                holder.binding.editLayout.visibility = View.VISIBLE
            }
            holder.binding.cancel.setOnClickListener {
                holder.binding.viewLayout.visibility = View.VISIBLE
                holder.binding.editLayout.visibility = View.GONE
            }
            holder.binding.root.findViewById<View>(R.id.attach_card).setOnClickListener {
                if (data.id!=null){
                    listener.addAttachment(data.id.toString(),"PowerAndFuelEBBil")
                }
                else
                    Toast.makeText(baseFragment.requireContext(),"Firstly fill data then Add Attachment",
                        Toast.LENGTH_SHORT).show()
            }
            if (currentOpened == position) {
                holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                holder.binding.itemLine.visibility = View.GONE
                holder.binding.itemCollapse.visibility = View.VISIBLE
                holder.binding.imgEdit.visibility = View.VISIBLE
                holder.binding.viewLayout.visibility = View.VISIBLE
                holder.binding.editLayout.visibility = View.GONE
            } else {
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
            // titel text
            holder.binding.itemTitleStr.text = String.format(baseFragment.resources.getString(R.string.rf_antenna_title_str_formate),data.BillNumber,data.Amount,Utils.getFormatedDate(data.DueDate,"ddMMMyyyy"))

            //view mode
            if(data.PaymentStatus?.isNotEmpty()==true)
                AppPreferences.getInstance().setDropDown(holder.binding.PaymentStatus, DropDowns.PaymentStatus.name,data.PaymentStatus?.get(0).toString())
            holder.binding.BillDueDate.text=Utils.getFormatedDate(data.DueDate,"dd-MMM-yyyy")
            holder.binding.StatusDate.text=Utils.getFormatedDate(data.StatusDate,"dd-MMM-yyyy")
            holder.binding.BillNo.text=data.BillNumber
            holder.binding.BillMonth.text=Utils.getFormatedDateMonthYear(data.BillMonth,"MMM-yyyy")
            holder.binding.BillAmount.text=data.Amount
            holder.binding.SrNo.text=position.plus(1).toString()
            holder.binding.UnitConsumed.text=data.UnitConsumed.toString()

            // edit mode
            holder.binding.BillDueDateEdit.text=Utils.getFormatedDate(data.DueDate,"dd-MMM-yyyy")
            holder.binding.StatusDateEdit.text=Utils.getFormatedDate(data.StatusDate,"dd-MMM-yyyy")
            holder.binding.BillMonthEdit.text=Utils.getFormatedDateMonthYear(data.BillMonth,"MMM-yyyy")
            holder.binding.BillNumberEdit.setText(data.BillNumber)
            holder.binding.BillAmountEdit.setText(data.Amount)
            holder.binding.UnitConsumeEdit.setText(data.UnitConsumed.toString())
            if(data.PaymentStatus?.isNotEmpty()==true)
                AppPreferences.getInstance().setDropDown(holder.binding.PaymentStatusEdit, DropDowns.PaymentStatus.name,data.PaymentStatus?.get(0).toString())
            else
                AppPreferences.getInstance().setDropDown(holder.binding.PaymentStatusEdit, DropDowns.PaymentStatus.name)
            holder.binding.update.setOnClickListener {
                val tempBillData=PowerFuelBills()
                tempBillData.let {
                    it.BillNumber=holder.binding.BillNumberEdit.text.toString()
                    it.Amount=holder.binding.BillAmountEdit.text.toString()
                    it.BillMonth=Utils.getFullFormatedDate(holder.binding.BillMonthEdit.text.toString())
                    it.DueDate=Utils.getFullFormatedDate(holder.binding.BillDueDateEdit.text.toString())
                    it.StatusDate=Utils.getFullFormatedDate(holder.binding.StatusDateEdit.text.toString())
                    it.UnitConsumed=holder.binding.UnitConsumeEdit.text.toString().toIntOrNull()
                    it.PaymentStatus= arrayListOf(holder.binding.PaymentStatusEdit.selectedValue.id.toInt())
                    it.id=data.id
                }
                listener.updateBills(tempBillData)
            }
            baseFragment.setDatePickerView(holder.binding.BillDueDateEdit)
            baseFragment.setDatePickerView(holder.binding.StatusDateEdit)
            baseFragment.setDatePickerView(holder.binding.BillMonthEdit)

            if (data.attachment!=null){
                holder.recyclerListener.adapter= ImageAttachmentCommonAdapter(baseFragment.requireContext(),data.attachment!!,object : ImageAttachmentCommonAdapter.ItemClickListener{
                    override fun itemClicked(item : Attachments) {
                        listener.attachmentItemClicked()
                    }
                })
            }
            else
                AppLogger.log("Attachments Error")
        }
    }


    override fun getItemViewType(position: Int): Int {
        return if (list.isEmpty())
            2
        else if(list[position] is PowerFuelBills)
            1
        else
            3
    }

    override fun getItemCount(): Int {
        return list.size
    }

    var currentOpened = -1
    var recyclerView: RecyclerView?=null
    fun updateList(position: Int){
        currentOpened = if(currentOpened == position) -1 else position
        notifyDataSetChanged()
        if (this.recyclerView!=null)
            this.recyclerView?.scrollToPosition(position)
    }

    interface PowerBillsClickListener{
        fun updateBills(updatedData:PowerFuelBills)
        fun addAttachment(id:String,moduel:String)
        fun attachmentItemClicked()
    }
}