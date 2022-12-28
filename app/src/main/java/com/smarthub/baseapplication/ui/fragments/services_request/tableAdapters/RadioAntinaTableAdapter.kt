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
import com.smarthub.baseapplication.model.serviceRequest.RadioAntenna
import com.smarthub.baseapplication.ui.fragments.services_request.adapter.ServicesRequestAdapter

class RadioAntinaTableAdapter (var context : Context, var listener : ServicesRequestAdapter.ServicesRequestLisListener,radioAnteenaData:List<RadioAntenna>): RecyclerView.Adapter<RadioAntinaTableAdapter.ViewHold>() {
    var list  = ArrayList<RadioAntenna>()
    init {
        list=radioAnteenaData as ArrayList<RadioAntenna>
    }
    fun addItem(){
        list.add(
            RadioAntenna(AntennaCount = "3", AntennaHeight = "76", AntennaSize = "50",
        AntennaTotalWeight = "54", created_at = "22-10-2022", id = "448", isActive = "true",
        modified_at = "22-12-2022")
        )
        notifyItemInserted(list.size.plus(1))
    }

    fun removeItem(position:Int){
        list.removeAt(position)
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
        holder.binding.AnteenaTechnology.text="Data not found"
        holder.binding.AnteenaCount.text=list[position].AntennaCount
        holder.binding.AnteenaShape.text="Data not found"
        holder.binding.AnteenaSize.text=list[position].AntennaSize

    }
    override fun getItemCount(): Int {
        return list.size
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
                        listener.editRadioAnteenaClicked(position)
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
