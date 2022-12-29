package com.smarthub.baseapplication.ui.fragments.services_request.tableAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AcquisitionPropertyOwnerTableItemBinding
import com.smarthub.baseapplication.model.serviceRequest.AcquisitionSurvey.ASPropertyOwnerDetail
import com.smarthub.baseapplication.ui.fragments.services_request.adapter.AcquisitionSurveyFragAdapter

class PropertyOwnerTableAdapter (var context : Context, var listener : AcquisitionSurveyFragAdapter.AcquisitionSurveyListItemListner,propertyOwnerData:List<ASPropertyOwnerDetail>): RecyclerView.Adapter<PropertyOwnerTableAdapter.ViewHold>() {
    var list : ArrayList<ASPropertyOwnerDetail>?
    init {
        list= propertyOwnerData as ArrayList<ASPropertyOwnerDetail>
    }
    fun addItem(item:String){
        list?.add(ASPropertyOwnerDetail(Action = "Goto", Address = "esrgsdfg", EmailId = "an@g.co",
            OwnerName = "Ankit", OwnershipSeq = "21", PhoneNumber = "7269024641", Remark = "dhdhffh",
            Share = "dwh", created_at = "22-10-2022", id = "448", isActive = "true", modified_at = "22-10-2022"))
        notifyItemInserted(list?.size!!.plus(1))
    }

    fun removeItem(position:Int){
        list?.removeAt(position)
        notifyItemRemoved(position)
    }

    class ViewHold(view: View) : RecyclerView.ViewHolder(view){
        var binding= AcquisitionPropertyOwnerTableItemBinding.bind(view)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.acquisition_property_owner_table_item,parent,false)
        return ViewHold(view)
    }
    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.binding.menu.setOnClickListener {
            performOptionsMenuClick(position,it)
        }
        holder.binding.SeqNo.text=list?.get(position)?.OwnershipSeq
        holder.binding.PayeeName.text="Data Not found"
        holder.binding.PropertyShare.text=list?.get(position)?.Share
        holder.binding.propertyPanNo.text="Data Not found"
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
                        listener.editPropertyOwnerDetailsClicked(position)
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
                        listener.viewPropertyOwnerDetailsClicked(position)
                        return true
                    }

                }
                return false
            }
        })
        popupMenu.show()
    }
}
