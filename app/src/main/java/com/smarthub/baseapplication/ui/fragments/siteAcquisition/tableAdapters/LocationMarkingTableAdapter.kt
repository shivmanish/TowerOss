package com.smarthub.baseapplication.ui.fragments.siteAcquisition.tableAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AcqFeasibilityDetailsTableItemBinding
import com.smarthub.baseapplication.databinding.OutsidePermisesTableItemsBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.SAcqLocationMarking
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.SAcqOutsidePremise
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.adapters.AcqSurveyFragAdapter
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns

class LocationMarkingTableAdapter (var context : Context, var listener : AcqSurveyFragAdapter.AcqSurveyListListener, var list:ArrayList<SAcqLocationMarking>?): RecyclerView.Adapter<LocationMarkingTableAdapter.ViewHold>() {


    var tempData=SAcqLocationMarking()
    fun addItem(){
        val data=SAcqLocationMarking()
        listener.editLocationMarkingClicked(-1,data)
    }

    fun removeItem(position:Int){
        list?.removeAt(position)
        notifyItemRemoved(position)
    }

    class ViewHold(view: View) : RecyclerView.ViewHolder(view){
        var binding= AcqFeasibilityDetailsTableItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.acq_feasibility_details_table_item,parent,false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        val item:SAcqLocationMarking=list!![position]
        holder.binding.menu.setOnClickListener {
            performOptionsMenuClick(position,it,item)
        }
        try {
            if (item.DirectionA?.isNotEmpty()==true)
                AppPreferences.getInstance().setDropDown(holder.binding.BoundaryInDirectionA,DropDowns.Direction.name,item.DirectionA?.get(0).toString())
            if (item.DirectionA?.isNotEmpty()==true)
                AppPreferences.getInstance().setDropDown(holder.binding.BoundaryInDirectionB,DropDowns.Direction.name,item.DirectionB?.get(0).toString())
            holder.binding.Object.text=item.Object
            holder.binding.SrNo.text=position.plus(1).toString()
        }catch (e:java.lang.Exception){
            AppLogger.log("ToewerPoTableadapter error : ${e.localizedMessage}")
        }
    }

    override fun getItemCount(): Int {
        return list?.size!!
    }

    // this method will handle the onclick options click
    private fun performOptionsMenuClick(position: Int,view : View,data:SAcqLocationMarking) {
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
                        listener.editLocationMarkingClicked(position,data)

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
                        listener.viewLocationMarkingClicked(position,data)
                    }

                }
                return false
            }
        })
        popupMenu.show()
    }
}
