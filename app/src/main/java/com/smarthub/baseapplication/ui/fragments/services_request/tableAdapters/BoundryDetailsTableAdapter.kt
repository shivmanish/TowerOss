package com.smarthub.baseapplication.ui.fragments.services_request.tableAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AcquisitionSurveyBoundryTableItemBinding
import com.smarthub.baseapplication.model.serviceRequest.acquisitionSurvey.ASBoundryStructureDetail
import com.smarthub.baseapplication.ui.fragments.services_request.adapter.AcquisitionSurveyFragAdapter

class BoundryDetailsTableAdapter (var context : Context, var listener : AcquisitionSurveyFragAdapter.AcquisitionSurveyListItemListner,boundryData:List<ASBoundryStructureDetail>): RecyclerView.Adapter<BoundryDetailsTableAdapter.ViewHold>() {
    var list : ArrayList<ASBoundryStructureDetail>?
    init {
        list=boundryData as ArrayList<ASBoundryStructureDetail>
    }
    fun addItem(item:String){
//        list?.add(
//            ASBoundryStructureDetail(Action = "goto", BuidingName = "sdafg", BuildingHeight = "78",
//        BuildingType = "Comercial", Direction = "East", NoofFloor = "6", Remark = "fewref",
//            created_at = "22-10-2022", id = "448", isActive = "true", modified_at = "22-12-2022")
//        )
//        notifyItemInserted(list?.size!!.plus(1))
    }

    fun removeItem(position:Int){
        list?.removeAt(position)
        notifyItemRemoved(position)
    }

    class ViewHold(view: View) : RecyclerView.ViewHolder(view){
        var binding= AcquisitionSurveyBoundryTableItemBinding.bind(view)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.acquisition_survey_boundry_table_item,parent,false)
        return ViewHold(view)
    }
    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.binding.menu.setOnClickListener {
            performOptionsMenuClick(position,it)
        }
        holder.binding.BoundryDirection.text=list?.get(position)?.Direction
        holder.binding.BldgName.text=list?.get(position)?.BuidingName
        holder.binding.BldgType.text=list?.get(position)?.BuildingType
        holder.binding.BldgHeight.text=list?.get(position)?.BuildingHeight
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
                        listener.editBoundryDetailsClicked(position)
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
                        listener.viewBoundryDetailsClicked(position)
                        return true
                    }

                }
                return false
            }
        })
        popupMenu.show()
    }
}
