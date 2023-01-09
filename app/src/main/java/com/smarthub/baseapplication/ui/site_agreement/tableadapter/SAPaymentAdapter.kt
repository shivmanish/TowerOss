package com.smarthub.baseapplication.ui.site_agreement.tableadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.PaymentTableItemBinding
import com.smarthub.baseapplication.model.siteInfo.siteAgreements.SiteacquisitionAgreement
import com.smarthub.baseapplication.model.siteInfo.siteAgreements.SiteacquisitionPayment
import com.smarthub.baseapplication.utils.AppLogger

class SAPaymentAdapter(
    var context: Context,
    var listener: PaymentTableAdapter.PaymentInfoListListener? = null,
     allPayment: List<SiteacquisitionPayment>?,

    ) :
    RecyclerView.Adapter<SAPaymentAdapter.ViewHold>() {

    var list: ArrayList<String> = ArrayList()
     var paymentList: SiteacquisitionPayment?=null
    private var siteAgreementsData : SiteacquisitionAgreement?=null

    var type1 = "Payment"


    var currentOpened = -1

    init {
        list.add("Payment")
        paymentList=allPayment?.get(0);


    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)

    class PaymentViewHold(itemView: View) : ViewHold(itemView) {
        var binding: PaymentTableItemBinding = PaymentTableItemBinding.bind(itemView)

        var paymentList: RecyclerView=binding.paylist

        init {
                   binding.collapsingLayout.tag = false
                   if ((binding.collapsingLayout.tag as Boolean)) {
                       binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                       binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                   } else {
                       binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                       binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                   }

                   binding.imgAdd.setOnClickListener {
                       addTableItem("gsfbgksf")
                   }
               }
        private fun addTableItem(item: String) {
                if (paymentList.adapter!=null && paymentList.adapter is PaymentTableAdapter){
                    var adapter = paymentList.adapter as PaymentTableAdapter
                    adapter.addItem()
                }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        return when (viewType) {
            1 -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.payment_table_item, parent, false)
                PaymentViewHold(view)
            }


            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_empty, parent, false)
                ViewHold(view)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (list[position] is String && list[position] == type1)
            return 1
        else return 0
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        when (holder) {

            is PaymentViewHold -> {
                       if (currentOpened == position) {
                           holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                           holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                           holder.binding.itemLine.visibility = View.GONE
                           holder.binding.itemCollapse.visibility = View.VISIBLE
                           holder.binding.imgAdd.visibility = View.VISIBLE

                       }
                       else {
                           holder.binding.collapsingLayout.tag = false
                           holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                           holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                           holder.binding.itemLine.visibility = View.VISIBLE
                           holder.binding.itemCollapse.visibility = View.GONE
                           holder.binding.imgAdd.visibility = View.GONE
                       }
                       holder.binding.collapsingLayout.setOnClickListener {
                           updateList(position)
                       }
                       holder.binding.itemTitleStr.text = list[position]
                       try {
                           holder.paymentList.adapter=
                               PaymentTableAdapter(context,listener!!,paymentList!!)
                       }catch (e:java.lang.Exception){
                           AppLogger.log("ToewerInfoadapter error : ${e.localizedMessage}")
                       }
            }

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    var recyclerView: RecyclerView? = null
    fun updateList(position: Int) {
        currentOpened = if (currentOpened == position) -1 else position
        notifyDataSetChanged()
        if (this.recyclerView != null)
            this.recyclerView?.scrollToPosition(position)
    }

}