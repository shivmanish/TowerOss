package com.smarthub.baseapplication.ui.fragments.towerCivilInfra.tableActionAdapters

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
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.EarthingInfoFragmentAdapter
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns

class EarthingDetailsTableAdapter (var context : Context, var listener : EarthingInfoFragmentAdapter.TowerEarthingListListener, var list:ArrayList<TwrCivilInfraEarthingDetail>?): RecyclerView.Adapter<EarthingDetailsTableAdapter.ViewHold>() {


    fun addItem(item:String){

//        notifyItemInserted(list?.size!!.plus(1))
    }

    fun removeItem(position:Int){
        list?.removeAt(position)
        notifyItemRemoved(position)
    }

    class ViewHold(view: View) : RecyclerView.ViewHolder(view){
        var binding= TowerEarthingDetailsTableItemsBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.tower_earthing_details_table_items,parent,false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        var item:TwrCivilInfraEarthingDetail=list!![position]
        holder.binding.menu.setOnClickListener {
            performOptionsMenuClick(position,it,item)
        }
        try {
//            if (item.VendorCompany.isNotEmpty())
//                AppPreferences.getInstance().setDropDown(holder.binding.VendorName,
//                    DropDowns.VendorCompany.name,item.VendorCompany.get(0).toString())
//            holder.binding.PitSize.text=item.P
//            holder.binding.poDate.text= Utils.getFormatedDate(item.PODate.substring(0,10),"dd-MMM-yyyy")
            AppPreferences.getInstance().setDropDown(holder.binding.pitRoadMaterial,
                DropDowns.PitRodMaterial.name,item.PitRodMaterial.toString())



            holder.binding.Id.text=position.plus(1).toString()
        }catch (e:java.lang.Exception){
            AppLogger.log("ToewerPoTableadapter error : ${e.localizedMessage}")
            Toast.makeText(context,"ToewerPoTableadapter error :${e.localizedMessage}", Toast.LENGTH_LONG).show()
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
//                        listener.editPoClicked(position)

                        return true
                    }
                    // in the same way you can implement others
                    R.id.action_delete -> {
                        popupMenu.dismiss()
                        // define
                        removeItem(position)
                        Toast.makeText(context , "Item 2 clicked" , Toast.LENGTH_SHORT).show()
                        return true
                    }

                    R.id.action_view -> {
                        popupMenu.dismiss()
                        listener.viewEarthingDetails(position,data)
                        Toast.makeText(context , "Item 2 clicked" , Toast.LENGTH_SHORT).show()
                    }

                }
                return false
            }
        })
        popupMenu.show()
    }
}
