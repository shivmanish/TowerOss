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
import com.smarthub.baseapplication.databinding.PoTableItemRowBinding
import com.smarthub.baseapplication.ui.site_agreement.model.PODetailsModel

import com.smarthub.baseapplication.ui.site_agreement.model.PaymentModel
import com.smarthub.baseapplication.utils.AppLogger

class PoTableAdapter(
    var context: Context,
    var listener: PoTableAdapter.PoInfoListListener,
    poDetailsModel: List<Any>?
) : RecyclerView.Adapter<PoTableAdapter.ViewHold>() {

    var list: ArrayList<PODetailsModel>?

    init {
        list = poDetailsModel as ArrayList<PODetailsModel>
    }

    fun addItem(item: String) {
        list?.add(
            PODetailsModel(Item = "ass", PONo = "1st", Vendor = "53", Date = "10-Nov-18")
        )
        notifyItemInserted(list?.size!!.plus(1))
    }

    fun removeItem(position: Int) {
        list?.removeAt(position)
        notifyItemRemoved(position)
    }

    class ViewHold(view: View) : RecyclerView.ViewHolder(view) {
        var binding = PoTableItemRowBinding.bind(view)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.po_table_item_row, parent, false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.binding.menu.setOnClickListener {
            performOptionsMenuClick(position, it)
        }
        try {
            holder.binding.ItemName.text = "Data Not Found"
            holder.binding.ItemCode.text = list?.get(position)?.PONo
            holder.binding.Description.text = list?.get(position)?.Vendor
            holder.binding.Qty.text = list?.get(position)?.Date
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
        return list?.size!!
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


    interface PoInfoListListener {
        fun editClicked(position: Int)
        fun viewClicked(position: Int)
        fun AgreementEditViewClick()
        fun attachmentItemClicked()

    }
}
