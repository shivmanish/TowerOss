package com.smarthub.baseapplication.ui.fragments.siteAcquisition.tableAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.OutsidePermisesTableItemsBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.SAcqOutsidePremise
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.adapters.AcqSurveyFragAdapter
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns

class OutsidePremisesTableAdapter (var context : Context, var listener : AcqSurveyFragAdapter.AcqSurveyListListener, var list:ArrayList<SAcqOutsidePremise>?): RecyclerView.Adapter<OutsidePremisesTableAdapter.ViewHold>() {


    fun addItem(){
        val data=SAcqOutsidePremise()
        listener.editOutsidePremisesClicked(-1,data)
    }

    fun removeItem(position:Int){
        list?.removeAt(position)
        notifyItemRemoved(position)
    }

    class ViewHold(view: View) : RecyclerView.ViewHolder(view){
        var binding= OutsidePermisesTableItemsBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.outside_permises_table_items,parent,false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        val item:SAcqOutsidePremise=list!![position]
        holder.binding.menu.setOnClickListener {
            performOptionsMenuClick(position,it,item)
        }
        try {
            if (item.ExternalStructureType?.isNotEmpty()==true)
                AppPreferences.getInstance().setDropDown(holder.binding.ExternalStructureType,DropDowns.ExternalStructureType.name,item.ExternalStructureType?.get(0).toString())
            holder.binding.DistanceFromBoundry.text=item.DistanceFromBoundry
            holder.binding.HeightAGL.text=item.Height
            holder.binding.SrNo.text=position.plus(1).toString()
        }catch (e:java.lang.Exception){
            AppLogger.log("ToewerPoTableadapter error : ${e.localizedMessage}")
        }
    }

    override fun getItemCount(): Int {
        return list?.size!!
    }

    // this method will handle the onclick options click
    private fun performOptionsMenuClick(position: Int,view : View,data:SAcqOutsidePremise) {
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
                        listener.editOutsidePremisesClicked(position,data)

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
                        listener.viewOutsidePremisesClicked(position,data)
                    }

                }
                return false
            }
        })
        popupMenu.show()
    }
}
