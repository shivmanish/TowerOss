package com.smarthub.baseapplication.ui.fragments.siteAcquisition.tableAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AcqPropertyOwnerTableItemsBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.SAcqPropertyOwnerDetail
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.adapters.AcqSurveyFragAdapter
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns

class PropertyOwnerTableAdapter (var context : Context, var listener : AcqSurveyFragAdapter.AcqSurveyListListener, var list:ArrayList<SAcqPropertyOwnerDetail>?): RecyclerView.Adapter<PropertyOwnerTableAdapter.ViewHold>() {


    fun addItem(){
        val data=SAcqPropertyOwnerDetail("","","","",arrayListOf(0),"","")
        this.list?.add(data)
        notifyItemInserted(list?.size!!.plus(1))
    }

    fun removeItem(position:Int){
        list?.removeAt(position)
        notifyItemRemoved(position)
    }

    class ViewHold(view: View) : RecyclerView.ViewHolder(view){
        var binding= AcqPropertyOwnerTableItemsBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.acq_property_owner_table_items,parent,false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        val item:SAcqPropertyOwnerDetail=list!![position]
        holder.binding.menu.setOnClickListener {
            performOptionsMenuClick(position,it,item)
        }
        try {
            holder.binding.SrNo.text=position.plus(1).toString()
            holder.binding.OwnerName.text=item.OwnerName
            holder.binding.share.text=item.Share
            if (item.PropertyOwnership.isNotEmpty())
                AppPreferences.getInstance().setDropDown(holder.binding.PropertyOwnership,DropDowns.PropertyOwnership.name,item.PropertyOwnership.get(0).toString())

        }catch (e:java.lang.Exception){
            AppLogger.log("ToewerPoTableadapter error : ${e.localizedMessage}")
        }
    }

    override fun getItemCount(): Int {
        return list?.size!!
    }

    // this method will handle the onclick options click
    private fun performOptionsMenuClick(position: Int,view : View,data:SAcqPropertyOwnerDetail) {
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
                        listener.editPropertyOwnerClicked(position,data)

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
                        listener.viewPropertyOwnerClicked(position,data)
                    }

                }
                return false
            }
        })
        popupMenu.show()
    }
}
