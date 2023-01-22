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
import com.smarthub.baseapplication.databinding.PropertyOwnerTableItemRowBinding
import com.smarthub.baseapplication.model.siteInfo.siteAcqutiuons.PropertyViewDetailsModel
import com.smarthub.baseapplication.model.siteInfo.siteAgreements.PropertyOwnerPaymentDetail
import com.smarthub.baseapplication.utils.AppLogger

class PropertyOwenerTableAdapter(
    var context: Context,
    var listener: PropertyOwenerInfoListListener,
    var list: ArrayList<PropertyOwnerPaymentDetail>
) : RecyclerView.Adapter<PropertyOwenerTableAdapter.ViewHold>() {
    fun addItem() {
        list.add(
            PropertyOwnerPaymentDetail(
                ACNumber = "ass",
                share = "1st",
                BankBranch = "53",
                BankIFCCode = "8884",
                GSTNumber = "kk@gmail.com",
                PanNumber = "NA",
                PayeeBank = "NA",
                PayeeName = "NA",
                PayeeStatus = "NA",
                created_at = "NA",
                id = "NA",
                isActive = "NA",
                modified_at = "NA",

            )
        )
        notifyItemInserted(list.size.plus(1))
    }

    fun removeItem(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    class ViewHold(view: View) : RecyclerView.ViewHolder(view) {
        var binding = PropertyOwnerTableItemRowBinding.bind(view)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.property_owner_table_item_row, parent, false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.binding.menu.setOnClickListener {
            performOptionsMenuClick(position, it)
        }
        try {
            holder.binding.ItemName.text =list.get(position).share
            holder.binding.ItemCode.text = list.get(position).share
            holder.binding.Description.text = list.get(position).share
            holder.binding.Qty.text = list.get(position).created_at
        } catch (e: java.lang.Exception) {
            AppLogger.log("ToewerPoTableadapter error : ${e.localizedMessage}")
//            Toast.makeText(
//                context,
//                "ToewerPoTableadapter error :${e.localizedMessage}",
//                Toast.LENGTH_LONG
//            ).show()
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
//                        Toast.makeText(context, "Item 2 clicked", Toast.LENGTH_SHORT).show()
                        return true
                    }

                    R.id.action_view -> {
                        popupMenu.dismiss()
                        listener.viewClicked(position)
//                        Toast.makeText(context, "Item 2 clicked", Toast.LENGTH_SHORT).show()
                    }

                }
                return false
            }
        })
        popupMenu.show()
    }


    interface PropertyOwenerInfoListListener {
        fun editClicked(position: Int)
        fun viewClicked(position: Int)
        fun AgreementEditViewClick()
        fun attachmentItemClicked()

    }
}
