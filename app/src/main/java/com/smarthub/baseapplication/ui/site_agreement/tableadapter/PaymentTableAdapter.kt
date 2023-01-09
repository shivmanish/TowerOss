package com.smarthub.baseapplication.ui.site_agreement.tableadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.PaymentTableItemRowBinding
import com.smarthub.baseapplication.model.siteInfo.siteAgreements.SiteacquisitionPayment
import com.smarthub.baseapplication.utils.AppLogger

class PaymentTableAdapter(
    var context: Context,
    var listener: PaymentInfoListListener,
    paymentModel: SiteacquisitionPayment
) : RecyclerView.Adapter<PaymentTableAdapter.ViewHold>() {

    var list = ArrayList<SiteacquisitionPayment>()

    init {
        list = paymentModel as ArrayList<SiteacquisitionPayment>

    }

    fun addItem() {
        list.add(
            SiteacquisitionPayment(
                Amount = "",
                Date = "",
                DueDate = "",
                InvoiceNumber = "",
                LineItemNumber = "",
                NetPayable = "",
                PayeeName = "",
                PaymentAmount = "",
                PaymentDate = "",
                Status = "",
                created_at = "",
                id = "",
                isActive = "",
                modified_at = ""
            )
        )
        notifyItemInserted(list.size.plus(1))
    }

    fun removeItem(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    class ViewHold(view: View) : RecyclerView.ViewHolder(view) {
        var binding = PaymentTableItemRowBinding.bind(view)
    }

    interface PaymentInfoListListener {
        fun editClicked(position: Int)
        fun viewClicked(position: Int)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.payment_table_item_row, parent, false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.binding.menu.setOnClickListener {
            performOptionsMenuClick(position, it)
        }
        try {
            holder.binding.ItemName.text = "Data Not Found"
            holder.binding.ItemCode.text = list.get(position)?.PayeeName
            holder.binding.Description.text = list.get(position)?.PaymentAmount
            holder.binding.Qty.text = list.get(position)?.LineItemNumber
        } catch (e: java.lang.Exception) {
            AppLogger.log("ToewerPoTableadapter error : ${e.localizedMessage}")
            Toast.makeText(
                context,
                "ToewerPoTableadapter error :${e.localizedMessage}",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    // this method will handle the onclick options click
    private fun performOptionsMenuClick(position: Int, view: View) {
        // create object of PopupMenu and pass context and view where we want
        // to show the popup menu
        val popupMenu = PopupMenu(context, view)
        // add the menu
        popupMenu.inflate(R.menu.options_menu)
        // implement on menu item click Listener
        popupMenu.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
            override fun onMenuItemClick(item: MenuItem?): Boolean {
                when (item?.itemId) {
                    R.id.action_edit -> {
                        popupMenu.dismiss()
                        listener.editClicked(position)

                        return true
                    }
                    // in the same way you can implement others
                    R.id.action_delete -> {
                        popupMenu.dismiss()
                        // define
                        removeItem(position)
                        Toast.makeText(context, "Item 2 clicked", Toast.LENGTH_SHORT).show()
                        return true
                    }

                    R.id.action_view -> {
                        popupMenu.dismiss()
                        listener.viewClicked(position)
                        Toast.makeText(context, "Item 2 clicked", Toast.LENGTH_SHORT).show()
                    }

                }
                return false
            }
        })
        popupMenu.show()
    }
}
