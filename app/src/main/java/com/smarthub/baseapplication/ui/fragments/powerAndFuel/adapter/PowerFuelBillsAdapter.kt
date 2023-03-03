package com.smarthub.baseapplication.ui.fragments.powerAndFuel.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.NocApplicationDetailsItemBinding
import com.smarthub.baseapplication.databinding.PowerFuelBillsItemBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newNocAndComp.NocApplicationInitial
import com.smarthub.baseapplication.model.siteIBoard.newNocAndComp.NocCompAllData
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.PowerFuelBills
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.RfAnteenaData
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class PowerFuelBillsAdapter(var listener: PowerBillsClickListener, var context: Context) : RecyclerView.Adapter<PowerFuelBillsAdapter.ViewHold>() {

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
        list.add("loading")
    }

    fun updateItem(pos : Int,data : PowerFuelBills){
        list[pos] = data
        notifyItemChanged(pos)
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)

    class ViewHold1(itemView: View,listener: PowerBillsClickListener) : ViewHold(itemView) {
        var binding : PowerFuelBillsItemBinding = PowerFuelBillsItemBinding.bind(itemView)
//        var adapter =  ImageAttachmentAdapter(object : ImageAttachmentAdapter.ItemClickListener{
//            override fun itemClicked() {
//                listener.attachmentItemClicked()
//            }
//        })
        init {
            binding.collapsingLayout.tag = false
            if ((binding.collapsingLayout.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }

//            val recyclerListener = itemView.findViewById<RecyclerView>(R.id.list_item)
//            recyclerListener.adapter = adapter

//            itemView.findViewById<View>(R.id.attach_card).setOnClickListener {
//                adapter.addItem()
//            }
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
//                listener.editModeCliked(data,position)
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
            } else {
                holder.binding.collapsingLayout.tag = false
                holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                holder.binding.itemLine.visibility = View.VISIBLE
                holder.binding.itemCollapse.visibility = View.GONE
                holder.binding.imgEdit.visibility = View.GONE
            }
            try {
                holder.binding.itemTitleStr.text = String.format(context.resources.getString(R.string.rf_antenna_title_str_formate),data.BillNumber,data.Amount,Utils.getFormatedDate(data.DueDate.substring(0,10),"ddMMMyyyy"))
                if(data.PaymentStatus.isNotEmpty())
                    AppPreferences.getInstance().setDropDown(holder.binding.PaymentStatus, DropDowns.PaymentStatus.name,data.PaymentStatus.get(0).toString())
                holder.binding.BillDueDate.text=Utils.getFormatedDate(data.DueDate.substring(0,10),"dd-MMM-yyyy")
                holder.binding.StatusDate.text=Utils.getFormatedDate(data.StatusDate.substring(0,10),"dd-MMM-yyyy")
                holder.binding.BillNo.text=data.BillNumber
                holder.binding.BillMonth.text=data.BillNumber
                holder.binding.BillAmount.text=data.Amount
                holder.binding.SrNo.text=position.plus(1).toString()
                holder.binding.UnitConsumed.text=data.UnitConsumed.toString()

            }catch (e:Exception){
                AppLogger.log("Somthig went wrong in rfAnteena adapter ${e.localizedMessage}")
                e.localizedMessage?.let { AppLogger.log(it) }
            }
        }
    }


    override fun getItemViewType(position: Int): Int {
        return if (list.isEmpty() || list.get(position)==null)
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
        fun editModeCliked(data :PowerFuelBills,pos:Int)
    }
}