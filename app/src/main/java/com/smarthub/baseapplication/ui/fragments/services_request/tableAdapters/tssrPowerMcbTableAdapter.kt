package com.smarthub.baseapplication.ui.fragments.services_request.tableAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.OpcotssrPowermcbTableItemBinding
import com.smarthub.baseapplication.model.serviceRequest.opcoTssr.MCBRequirement
import com.smarthub.baseapplication.ui.fragments.services_request.adapter.OpcoTssrAdapter

class tssrPowerMcbTableAdapter(
    var context: Context,
    var listener: OpcoTssrAdapter.OpcoTssrLisListener,
    McbData: List<MCBRequirement>
) : RecyclerView.Adapter<tssrPowerMcbTableAdapter.ViewHold>() {
    var list: ArrayList<MCBRequirement>?

    init {
        list = McbData as ArrayList<MCBRequirement>
    }

    fun addItem(item: String) {
//        list?.add(
//            MCBRequirement(Equipment = "fds", MCBRating = "54", Quantity = "53", created_at = "12-10-2022",
//                id = "543", isActive = "true", modified_at = "22-12-2022")
//        )
//        notifyItemInserted(list?.size!!.plus(1))
    }

    fun removeItem(position: Int) {
        list?.removeAt(position)
        notifyItemRemoved(position)
    }

    class ViewHold(view: View) : RecyclerView.ViewHolder(view) {
        var binding = OpcotssrPowermcbTableItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.opcotssr_powermcb_table_item, parent, false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.binding.menu.setOnClickListener {
            performOptionsMenuClick(position, it)
        }
        holder.binding.McbRating.text = list?.get(position)?.MCBRating
        holder.binding.Quantity.text = list?.get(position)?.Quantity
        holder.binding.Equipment.text = list?.get(position)?.Equipment
    }

    override fun getItemCount(): Int {
        return list?.size!!
    }

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
                        listener.editPowerMcbClicked(position)
                        return true
                    }
                    // in the same way you can implement others
                    R.id.action_delete -> {
                        popupMenu.dismiss()
                        // define
                        removeItem(position)
                        return true
                    }

                    R.id.action_view -> {
                        popupMenu.dismiss()
                        listener.viewPowerMcbClicked(position)
                        return true
                    }

                }
                return false
            }
        })
        popupMenu.show()
    }
}
