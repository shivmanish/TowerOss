package com.smarthub.baseapplication.ui.fragments.noc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.NocPoDetailsItemsBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newNocAndComp.NocPODetail
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class NocPoDetailsAdapter(var listener: NocPoClickListener, poDetails: ArrayList<NocPODetail>?, var baseFragment: BaseFragment) : RecyclerView.Adapter<NocPoDetailsAdapter.ViewHold>() {

    var list : ArrayList<NocPODetail>? = poDetails
    var currentOpened = -1

    fun setData(data : ArrayList<NocPODetail>?){
        if (data!=null){
            this.list?.clear()
            this.list?.addAll(data)
            notifyDataSetChanged()
        }
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)

    class ViewHold1(itemView: View,listener: NocPoClickListener) : ViewHold(itemView) {
        var binding : NocPoDetailsItemsBinding = NocPoDetailsItemsBinding.bind(itemView)
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
                val view = LayoutInflater.from(parent.context).inflate(R.layout.noc_po_details_items,parent,false)
                ViewHold1(view,listener)
            }
            2 ->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.rf_equipment_no_data,parent,false)
                ViewHold(view)
            }

            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.noc_po_details_items,parent,false)
                ViewHold1(view,listener)
            }
        }

    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        if (holder is ViewHold1) {
            var data: NocPODetail?=null
            if (list?.get(position)!=null)
                data= list?.get(position)!!
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

                holder.binding.imgEdit.setOnClickListener {
                    holder.binding.viewLayout.visibility = View.GONE
                    holder.binding.editLayout.visibility = View.VISIBLE
                }
                holder.binding.cancel.setOnClickListener {
                    holder.binding.viewLayout.visibility = View.VISIBLE
                    holder.binding.editLayout.visibility = View.GONE
                }
            } else {
                holder.binding.collapsingLayout.tag = false
                holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                holder.binding.itemLine.visibility = View.VISIBLE
                holder.binding.itemCollapse.visibility = View.GONE
                holder.binding.imgEdit.visibility = View.GONE
            }
            if (data!=null){
                holder.binding.itemTitleStr.text = String.format(baseFragment.resources.getString(R.string.rf_equipment_title_str_formate),position.plus(1).toString(),data.PONumber,Utils.getFormatedDate(data.PODate,"dd-MMM-yyyy"))
                // view mode
                if (data.VendorCompany?.isNotEmpty()==true)
                    AppPreferences.getInstance().setDropDown(holder.binding.vendorName,DropDowns.VendorCompany.name, data.VendorCompany?.get(0).toString())
                holder.binding.poNumber.text=data.PONumber
                holder.binding.poDate.text=Utils.getFormatedDate(data.PODate,"dd-MMM-yyyy")
                holder.binding.vendorCode.text=data.VendorCode
                holder.binding.poAmount.text=data.POAmount
                holder.binding.poItems.text=data.POItem
                holder.binding.remark.text=data.remark
                holder.binding.poLineNumber.text=data.POLineNo.toString()

                //edit mode
                holder.binding.PoNumberEdit.setText(data.PONumber)
                holder.binding.PoAmountEdit.setText(data.POAmount)
                holder.binding.PoItemEdit.setText(data.POItem)
                holder.binding.PoLineNumberEdit.setText(data.POLineNo.toString())
                holder.binding.remarksEdit.setText(data.remark)
                holder.binding.PoDateEdit.text=Utils.getFormatedDate(data.PODate,"dd-MMM-yyyy")
            }

            if (data!=null && data.VendorCompany?.isNotEmpty()==true)
                AppPreferences.getInstance().setDropDown(holder.binding.VendorNameEdit, DropDowns.VendorCompany.name, data.VendorCompany?.get(0).toString(),holder.binding.VendorCodeEdit)
            else
                AppPreferences.getInstance().setDropDown(holder.binding.VendorNameEdit, DropDowns.VendorCompany.name,holder.binding.VendorCodeEdit)
            baseFragment.setDatePickerView(holder.binding.PoDateEdit)

            holder.binding.update.setOnClickListener {
                val temPoData=NocPODetail()
                temPoData.let {
                    it.POAmount=holder.binding.PoAmountEdit.text.toString()
                    it.POItem=holder.binding.PoItemEdit.text.toString()
                    it.VendorCode=holder.binding.VendorCodeEdit.text.toString()
                    it.PONumber=holder.binding.PoNumberEdit.text.toString()
                    it.remark=holder.binding.remarksEdit.text.toString()
                    it.POLineNo=holder.binding.PoLineNumberEdit.text.toString().toIntOrNull()
                    it.PODate=Utils.getFullFormatedDate(holder.binding.PoDateEdit.text.toString())
                    it.VendorCompany= arrayListOf(holder.binding.VendorNameEdit.selectedValue.id.toInt())
                    it.id=data?.id
                    listener.updataDataClicked(it)
                }
            }
        }
    }


    override fun getItemViewType(position: Int): Int {
        return if (list?.isEmpty() == true)
            2
        else
            1
    }

    override fun getItemCount(): Int {
        var size = 0
        list?.size?.let {
            size = it
        }
        return size
    }

    var recyclerView: RecyclerView?=null
    fun updateList(position: Int){
        currentOpened = if(currentOpened == position) -1 else position
        notifyDataSetChanged()
        if (this.recyclerView!=null)
            this.recyclerView?.scrollToPosition(position)
    }

    interface NocPoClickListener{
        fun attachmentItemClicked()
        fun updataDataClicked(data :NocPODetail)
    }
}