package com.smarthub.baseapplication.ui.fragments.services_request.tableAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.OpcotssrMicrowaveTableItemBinding
import com.smarthub.baseapplication.model.serviceRequest.opcoTssr.MicrowaveOrUBR
import com.smarthub.baseapplication.ui.fragments.services_request.adapter.OpcoTssrAdapter

class BackhaulMicrowaveTableAdapter (var context : Context, var listener : OpcoTssrAdapter.OpcoTssrLisListener,microwaveData:List<MicrowaveOrUBR>): RecyclerView.Adapter<BackhaulMicrowaveTableAdapter.ViewHold>() {
    var list : ArrayList<MicrowaveOrUBR>?
    init {
        list= microwaveData as ArrayList<MicrowaveOrUBR>
    }
    fun addItem(item:String){
        list?.add(
            MicrowaveOrUBR(AntennaHeight = "56", AzimuthOrOrientation = "erw", Feasibilty = "erdfs",
            LosDistance = "54", Obstraction = "fdr", OpcoFarEndSite = "ewr", SerialNo = "42", Shape = "54",
            Size = "58", Weight = "879", created_at = "22-10-2022", id = "4", isActive = "ture",
            modified_at = "22-12-2022")
        )
        notifyItemInserted(list?.size!!.plus(1))
    }

    fun removeItem(position:Int){
        list?.removeAt(position)
        notifyItemRemoved(position)
    }

    class ViewHold(view: View) : RecyclerView.ViewHolder(view){
        var binding= OpcotssrMicrowaveTableItemBinding.bind(view)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.opcotssr_microwave_table_item,parent,false)
        return ViewHold(view)
    }
    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.binding.menu.setOnClickListener {
            performOptionsMenuClick(position,it)
        }
        holder.binding.SerialNo.text=list?.get(position)?.SerialNo
        holder.binding.Technology.text="Data Not Found"
        holder.binding.TxrCount.text="Data not found"
        holder.binding.AnteenaHeight.text=list?.get(position)?.AntennaHeight
    }
    override fun getItemCount(): Int {
        return list?.size!!
    }
    private fun performOptionsMenuClick(position: Int,view : View) {
        // create object of PopupMenu and pass context and view where we want
        // to show the popup menu
        val popupMenu = PopupMenu(context , view)
        // add the menu
        popupMenu.inflate(R.menu.options_menu)
        // implement on menu item click Listener
        popupMenu.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener{
            override fun onMenuItemClick(item: MenuItem?): Boolean {
                when(item?.itemId){
                    R.id.action_edit -> {
                        popupMenu.dismiss()
                        listener.editBackhaulMicrowaveClicked(position)
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
                        listener.viewBackhaulMicrowaveClicked(position)
                        return true
                    }

                }
                return false
            }
        })
        popupMenu.show()
    }
}
