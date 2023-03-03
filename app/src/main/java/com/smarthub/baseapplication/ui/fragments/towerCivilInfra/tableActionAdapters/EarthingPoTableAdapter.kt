package com.smarthub.baseapplication.ui.fragments.towerCivilInfra.tableActionAdapters
import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import com.smarthub.baseapplication.R
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.smarthub.baseapplication.databinding.EarthingPoTableItemBinding
import com.smarthub.baseapplication.databinding.TowerPoTableItemBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.TwrCivilPODetail
import com.smarthub.baseapplication.model.siteInfo.towerAndCivilInfra.EarthingModelAuthorityPODetails
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.EarthingInfoFragmentAdapter
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class EarthingPoTableAdapter(var context : Context,var listener : EarthingInfoFragmentAdapter.TowerEarthingListListener,var list:ArrayList<TwrCivilPODetail>?): Adapter<EarthingPoTableAdapter.ViewHold>() {


    fun addItem(item:String){
//        notifyItemInserted(list?.size!!.plus(1))
    }

    fun removeItem(position:Int){
        list?.removeAt(position)
        notifyItemRemoved(position)
    }

    class ViewHold(view: View) : ViewHolder(view){
        var binding= TowerPoTableItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.tower_po_table_item,parent,false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        val item:TwrCivilPODetail=list!![position]
        holder.binding.menu.setOnClickListener {
            performOptionsMenuClick(position,it,item)
        }
        try {
            if (item.VendorCompany.isNotEmpty())
                AppPreferences.getInstance().setDropDown(holder.binding.VendorName,
                    DropDowns.VendorCompany.name,item.VendorCompany.get(0).toString())
            holder.binding.PoNo.text=item.PONumber
            holder.binding.poDate.text= Utils.getFormatedDate(item.PODate.substring(0,10),"dd-MMM-yyyy")
            holder.binding.SrNo.text=position.plus(1).toString()
        }catch (e:java.lang.Exception){
            AppLogger.log("EarthPoTableAdapter error : ${e.localizedMessage}")
            Toast.makeText(context,"EarthPoTableAdapter error :${e.localizedMessage}",Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount(): Int {
        return list?.size!!
    }

    // this method will handle the onclick options click
    private fun performOptionsMenuClick(position: Int,view : View,data:TwrCivilPODetail) {
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
                        listener.editPoClicked(position)

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
                        listener.viewPoClicked(position,data)
                        Toast.makeText(context , "Item 2 clicked" , Toast.LENGTH_SHORT).show()
                    }

                }
                return false
            }
        })
        popupMenu.show()
    }
}

