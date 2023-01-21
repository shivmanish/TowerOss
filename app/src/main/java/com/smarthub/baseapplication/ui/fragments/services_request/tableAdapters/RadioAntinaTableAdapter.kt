package com.smarthub.baseapplication.ui.fragments.services_request.tableAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.SiteRequestRadioTableItemBinding
import com.smarthub.baseapplication.model.serviceRequest.Equipment
import com.smarthub.baseapplication.model.serviceRequest.RadioAntenna
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem
import com.smarthub.baseapplication.ui.fragments.services_request.adapter.ServicesRequestAdapter

class RadioAntinaTableAdapter (var context: Context,
                               var listener: ServicesRequestAdapter.ServicesRequestLisListener,
                               var radioAntenna: ArrayList<RadioAntenna>,
                               var serviceRequestAllData: ServiceRequestAllDataItem
): RecyclerView.Adapter<RadioAntinaTableAdapter.ViewHold>() {
    fun addItem(){
    }

    fun removeItem(position:Int){
        radioAntenna.removeAt(position)
        notifyItemRemoved(position)
    }

    class ViewHold(view: View) : RecyclerView.ViewHolder(view){
        var binding= SiteRequestRadioTableItemBinding.bind(view)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.site_request_radio_table_item,parent,false)
        return ViewHold(view)
    }
    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.binding.menu.setOnClickListener {
            performOptionsMenuClick(position,it)
        }
        holder.binding.AnteenaTechnology.text="Na"
        holder.binding.AnteenaCount.text=radioAntenna[position].AntennaCount
        holder.binding.AnteenaShape.text="Na"
        holder.binding.AnteenaSize.text=radioAntenna[position].AntennaSize

    }
    override fun getItemCount(): Int {
        return radioAntenna.size
    }
    private fun performOptionsMenuClick(position: Int,view : View) {
        val popupMenu = PopupMenu(context , view)
        popupMenu.inflate(R.menu.options_menu)
        popupMenu.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener{
            override fun onMenuItemClick(item: MenuItem?): Boolean {
                when(item?.itemId){
                    R.id.action_edit -> {
                        popupMenu.dismiss()
                        listener.editRadioAnteenaClicked(radioAntenna.get(position),serviceRequestAllData,"edit")
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
                        listener.viewRadioAnteenaClicked(position)
                        return true
                    }

                }
                return false
            }
        })
        popupMenu.show()
    }
}
