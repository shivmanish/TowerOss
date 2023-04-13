package com.smarthub.baseapplication.ui.fragments.towerCivilInfra.earthing.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.TowerEarthingDetailsTableItemsBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.TwrCivilInfraEarthingDetail
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns

class EarthingDetailsTableAdapter (var context : Context, var listener : EarthingInfoFragmentAdapter.TowerEarthingListListener, var list:ArrayList<TwrCivilInfraEarthingDetail>?): RecyclerView.Adapter<EarthingDetailsTableAdapter.ViewHold>() {


    fun addItem(){
        val data=TwrCivilInfraEarthingDetail()
        list?.add(data)
        notifyItemInserted(list?.size!!.plus(1))
    }

    fun removeItem(position:Int){
        list?.removeAt(position)
        notifyItemRemoved(position)
    }

    class ViewHold(view: View) : RecyclerView.ViewHolder(view){
        var binding= TowerEarthingDetailsTableItemsBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tower_earthing_details_table_items,parent,false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        val item:TwrCivilInfraEarthingDetail=list!![position]
        holder.binding.menu.setOnClickListener {
            performOptionsMenuClick(position,it,item)
        }
        try {
            if (item.EarthingCableType!=null && item.EarthingCableType!! >0)
                AppPreferences.getInstance().setDropDown(holder.binding.EarthingCableType,DropDowns.EarthingCableType.name,item.EarthingCableType.toString())
            holder.binding.EarthingCableLength.text=item.EarthingCableLength
            holder.binding.PitDepth.text= item.Height
            holder.binding.Id.text=position.plus(1).toString()
        }catch (e:java.lang.Exception){
            AppLogger.log("ToewerPoTableadapter error : ${e.localizedMessage}")
        }
    }

    override fun getItemCount(): Int {
        return list?.size!!
    }

    // this method will handle the onclick options click
    private fun performOptionsMenuClick(position: Int,view : View,data:TwrCivilInfraEarthingDetail) {
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
                        listener.editEarthingDetails(data)
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
                        listener.viewEarthingDetails(data)
                        return true
                    }

                }
                return false
            }
        })
        popupMenu.show()
    }
}
