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
import com.smarthub.baseapplication.model.siteInfo.towerAndCivilInfra.EarthingModelAuthorityPODetails
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.EarthingInfoFragmentAdapter
import com.smarthub.baseapplication.utils.AppLogger

class EarthingPoTableAdapter(var context : Context,var listener : EarthingInfoFragmentAdapter.TowerEarthingListListener,poData:List<EarthingModelAuthorityPODetails>?): Adapter<EarthingPoTableAdapter.ViewHold>() {

    var list :ArrayList<EarthingModelAuthorityPODetails>?

    init {
        list= poData as ArrayList<EarthingModelAuthorityPODetails>
    }

    fun addItem(item:String){
        list?.add(
            EarthingModelAuthorityPODetails(ItemDescription = "dhg", POAmount = "54", POCopy = "58",
                PODate = "22-12-2022", PONumber = "89", POQty = "56", PORate = "6", POlineNo = "43",
                VendorCode = "87", VendorName = "fdsh", created_at = "22-10-2022", id ="56", isActive = "true",
                modified_at = "22-12-2022")
        )
        notifyItemInserted(list?.size!!.plus(1))
    }

    fun removeItem(position:Int){
        list?.removeAt(position)
        notifyItemRemoved(position)
    }

    class ViewHold(view: View) : ViewHolder(view){
        var binding= EarthingPoTableItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.earthing_po_table_item,parent,false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.binding.menu.setOnClickListener {
            performOptionsMenuClick(position,it)
        }
        try {
            holder.binding.VendorName.text=list?.get(position)?.VendorName
            holder.binding.PoNumber.text=list?.get(position)?.PONumber
            holder.binding.VendorCode.text=list?.get(position)?.VendorCode
        }catch (e:java.lang.Exception){
            AppLogger.log("EarthPoTableAdapter error : ${e.localizedMessage}")
            Toast.makeText(context,"EarthPoTableAdapter error :${e.localizedMessage}",Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount(): Int {
        return list?.size!!
    }

    // this method will handle the onclick options click
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
                        listener.viewPoClicked(position)
                        Toast.makeText(context , "Item 2 clicked" , Toast.LENGTH_SHORT).show()
                    }

                }
                return false
            }
        })
        popupMenu.show()
    }
}

