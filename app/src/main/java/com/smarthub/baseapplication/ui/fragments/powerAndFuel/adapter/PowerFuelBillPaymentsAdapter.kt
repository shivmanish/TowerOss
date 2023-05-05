package com.smarthub.baseapplication.ui.fragments.powerAndFuel.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.PowerFuelBillPaymentsItemsBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.Attachments
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.NewPowerFuelAllData
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.PowerFuelBills
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.PowerFuelEBPayments
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.ImageAttachmentCommonAdapter
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class PowerFuelBillPaymentsAdapter(var listener: PowerBillPaymentsClickListener, var baseFragment: BaseFragment, paymentData:ArrayList<PowerFuelEBPayments>?) : RecyclerView.Adapter<PowerFuelBillPaymentsAdapter.ViewHold>() {

    var list = ArrayList<Any>()

    fun setData(data: ArrayList<PowerFuelEBPayments>?) {
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
        this.list.addAll(paymentData!!)
    }

    fun updateItem(pos : Int,data : PowerFuelEBPayments){
        list[pos] = data
        notifyItemChanged(pos)
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)

    class ViewHold1(itemView: View,listener: PowerBillPaymentsClickListener) : ViewHold(itemView) {
        var binding : PowerFuelBillPaymentsItemsBinding = PowerFuelBillPaymentsItemsBinding.bind(itemView)
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
                val view = LayoutInflater.from(parent.context).inflate(R.layout.power_fuel_bill_payments_items,parent,false)
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
            val data: PowerFuelEBPayments=list[position] as PowerFuelEBPayments
            holder.binding.imgEdit.setOnClickListener {
                if (AppController.getInstance().isTaskEditable) {
                    holder.binding.viewLayout.visibility = View.GONE
                    holder.binding.editLayout.visibility = View.VISIBLE
                }
            }
            holder.binding.cancel.setOnClickListener {
                holder.binding.viewLayout.visibility = View.VISIBLE
                holder.binding.editLayout.visibility = View.GONE
            }
            holder.binding.root.findViewById<View>(R.id.attach_card).setOnClickListener {
                if (data.id!=null){
                    listener.addAttachment(data.id.toString(),"PowerAndFuelEBPayment")
                }
                else
                    Toast.makeText(baseFragment.requireContext(),"Firstly fill data then Add Attachment",
                        Toast.LENGTH_SHORT).show()
            }
            holder.binding.collapsingLayout.setOnClickListener {
                updateList(position)
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
            //titel text
            holder.binding.itemTitleStr.text = String.format(baseFragment.resources.getString(R.string.rf_antenna_title_str_formate),data.BillNumber,data.Amount,Utils.getFormatedDate(data.PaymentDate,"ddMMMyyyy"))

            if (data.PaymentMode!=null && data.PaymentMode!!>0){
                AppPreferences.getInstance().setDropDown(holder.binding.PaymentMode,DropDowns.PaymentMode.name,data.PaymentMode.toString())
                AppPreferences.getInstance().setDropDown(holder.binding.PaymentModeEdit,DropDowns.PaymentMode.name,data.PaymentMode.toString())
            }
            else
                AppPreferences.getInstance().setDropDown(holder.binding.PaymentModeEdit,DropDowns.PaymentMode.name)

            // view mode
            holder.binding.paymentDate.text=Utils.getFormatedDate(data.PaymentDate,"dd-MMM-yyyy")
            holder.binding.BillNo.text=data.BillNumber
            holder.binding.Amount.text=data.Amount
            holder.binding.SrNo.text=position.plus(1).toString()
            holder.binding.PaymentRefNo.text=data.PaymentRefNo

            //edit mode
            holder.binding.PaymentDateEdit.text=Utils.getFormatedDate(data.PaymentDate,"dd-MMM-yyyy")
            holder.binding.BillNumberEdit.setText(data.BillNumber)
            holder.binding.AmountEdit.setText(data.Amount)
            holder.binding.PaymentRefNoEdit.setText(data.PaymentRefNo)

            holder.binding.update.setOnClickListener {
                val tempBillPaymentsData= PowerFuelEBPayments()
                tempBillPaymentsData.let {
                    it.BillNumber=holder.binding.BillNumberEdit.text.toString()
                    it.Amount=holder.binding.AmountEdit.text.toString()
                    it.PaymentDate=Utils.getFullFormatedDate(holder.binding.PaymentDateEdit.text.toString())
                    it.PaymentRefNo=holder.binding.PaymentRefNoEdit.text.toString()
                    it.PaymentMode= holder.binding.PaymentModeEdit.selectedValue.id.toIntOrNull()
                    it.id=data.id
                }
                listener.updateBillPayments(tempBillPaymentsData)
            }
            baseFragment.setDatePickerView(holder.binding.PaymentDateEdit)

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
        else if(list[position] is PowerFuelEBPayments)
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

    interface PowerBillPaymentsClickListener{
        fun updateBillPayments(data :PowerFuelEBPayments)
        fun addAttachment(id :String,moduel:String)
        fun attachmentItemClicked()
    }
}