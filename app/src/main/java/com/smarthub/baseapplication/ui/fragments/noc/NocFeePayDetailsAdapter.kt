package com.smarthub.baseapplication.ui.fragments.noc

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.NocApplicationDetailsItemBinding
import com.smarthub.baseapplication.databinding.NocAuthorityItemsBinding
import com.smarthub.baseapplication.databinding.NocFeePaymentItemsBinding
import com.smarthub.baseapplication.databinding.NocPoDetailsItemsBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newNocAndComp.NocApplicationInitial
import com.smarthub.baseapplication.model.siteIBoard.newNocAndComp.NocAuthorityDetail
import com.smarthub.baseapplication.model.siteIBoard.newNocAndComp.NocAuthorityFeePaymentDetail
import com.smarthub.baseapplication.model.siteIBoard.newNocAndComp.NocPODetail
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.RfAnteenaData
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class NocFeePayDetailsAdapter(var listener: NocFeePayClickListener, var list : ArrayList<NocAuthorityFeePaymentDetail>?, var baseFragment: BaseFragment) : RecyclerView.Adapter<NocFeePayDetailsAdapter.ViewHold>() {

    var currentOpened = -1

    fun setData(data : ArrayList<NocAuthorityFeePaymentDetail>?){
        if (data!=null){
            this.list?.clear()
            this.list?.addAll(data)
            notifyDataSetChanged()
        }
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)

    class ViewHold1(itemView: View,listener: NocFeePayClickListener) : ViewHold(itemView) {
        var binding : NocFeePaymentItemsBinding = NocFeePaymentItemsBinding.bind(itemView)
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
                val view = LayoutInflater.from(parent.context).inflate(R.layout.noc_fee_payment_items,parent,false)
                ViewHold1(view,listener)
            }
            2 ->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.rf_equipment_no_data,parent,false)
                ViewHold(view)
            }

            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.noc_fee_payment_items,parent,false)
                ViewHold1(view,listener)
            }
        }

    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        if (holder is ViewHold1) {
            val data: NocAuthorityFeePaymentDetail=list!![position]

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
                    if (AppController.getInstance().isTaskEditable) {
                        holder.binding.viewLayout.visibility = View.GONE
                        holder.binding.editLayout.visibility = View.VISIBLE
                    }
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
            holder.binding.itemTitleStr.text = String.format(baseFragment.resources.getString(R.string.rf_antenna_title_str_formate),position.plus(1).toString(),data.ApplicationNo,data.Type)
            // view mode
            if (data.PaymentStatus?.isNotEmpty()==true)
                AppPreferences.getInstance().setDropDown(holder.binding.paymentStatus,DropDowns.PaymentStatus.name,data.PaymentStatus?.get(0).toString())
            if (data.PaymentMode!=null && data.PaymentMode!=0)
                AppPreferences.getInstance().setDropDown(holder.binding.PaymentMode,DropDowns.PaymentMode.name,data.PaymentMode.toString())
            holder.binding.ApplicationNo.text=data.ApplicationNo
            holder.binding.statusDate.text=Utils.getFormatedDate(data.StatusDate,"dd-MMM-yyyy")
            holder.binding.PaymentType.text=data.Type
            holder.binding.Amount.text=data.Amount

            // edit mode
            holder.binding.ApplicationNumberEdit.setText(data.ApplicationNo)
            holder.binding.PaymentTypeEdit.setText(data.Type)
            holder.binding.AmountEdit.setText(data.Amount)
            holder.binding.StatusDateEdit.text=Utils.getFormatedDate(data.StatusDate,"dd-MMM-yyyy")

            if (data.PaymentStatus?.isNotEmpty()==true)
                AppPreferences.getInstance().setDropDown(holder.binding.PaymentStatusEdit,DropDowns.PaymentStatus.name,data.PaymentStatus?.get(0).toString())
            else
                AppPreferences.getInstance().setDropDown(holder.binding.PaymentStatusEdit,DropDowns.PaymentStatus.name)

            if (data.PaymentMode!=null && data.PaymentMode!=0)
                AppPreferences.getInstance().setDropDown(holder.binding.PaymentModeEdit,DropDowns.PaymentMode.name,data.PaymentMode.toString())
            else
                AppPreferences.getInstance().setDropDown(holder.binding.PaymentModeEdit,DropDowns.PaymentMode.name)

            baseFragment.setDatePickerView(holder.binding.StatusDateEdit)

            holder.binding.update.setOnClickListener {
                val tempFeeData=NocAuthorityFeePaymentDetail()
                tempFeeData.let {
                    it.ApplicationNo=holder.binding.ApplicationNumberEdit.text.toString()
                    it.Type=holder.binding.PaymentTypeEdit.text.toString()
                    it.Amount=holder.binding.AmountEdit.text.toString()
                    it.StatusDate=Utils.getFullFormatedDate(holder.binding.StatusDateEdit.text.toString())
                    it.PaymentMode= holder.binding.PaymentModeEdit.selectedValue.id.toIntOrNull()
                    it.PaymentStatus= arrayListOf(holder.binding.PaymentStatusEdit.selectedValue.id.toInt())
                    it.id=data.id
                    listener.updataDataClicked(it)
                }
            }

        }
    }


    override fun getItemViewType(position: Int): Int {
        return if (list?.isEmpty()==true)
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

    interface NocFeePayClickListener{
        fun attachmentItemClicked()
        fun updataDataClicked(data :NocAuthorityFeePaymentDetail)
    }
}