package com.smarthub.baseapplication.ui.fragments.siteAcquisition.tableAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.TowerPoTableItemBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.SAcqPODetail
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.SiteAcqAgreement
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.adapters.AgreementFragAdapter
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class SiteAgreePoTableAdapter (var context : Context, var listener : AgreementFragAdapter.AgreementListListener, var list:ArrayList<SAcqPODetail>?): RecyclerView.Adapter<SiteAgreePoTableAdapter.ViewHold>() {



    fun addItem(){
        val modelList:ArrayList<Int> = ArrayList()
        modelList.clear()
        modelList.add(0)
        val data=SAcqPODetail("","","",0,"","",
            "",modelList,)
//        listener.addPoData(data)
        list?.add(data)
        notifyItemInserted(list?.size!!.plus(1))
    }

    fun removeItem(position:Int){
        list?.removeAt(position)
        notifyItemRemoved(position)
    }

    class ViewHold(view: View) : RecyclerView.ViewHolder(view){
        var binding= TowerPoTableItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tower_po_table_item,parent,false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        val item:SAcqPODetail=list!![position]
        holder.binding.menu.setOnClickListener {
            performOptionsMenuClick(position,it,item)
        }
        try {
            if (item.VendorCompany.isNotEmpty())
                AppPreferences.getInstance().setDropDown(holder.binding.VendorName,DropDowns.VendorCompany.name,item.VendorCompany.get(0).toString())
            holder.binding.PoNo.text=item.PONumber
            holder.binding.SrNo.text=position.plus(1).toString()
            holder.binding.poDate.text=Utils.getFormatedDate(item.PODate,"dd-MMM-yyyy")
        }catch (e:java.lang.Exception){
            AppLogger.log("ToewerPoTableadapter error : ${e.localizedMessage}")
        }
    }

    override fun getItemCount(): Int {
        return list?.size!!
    }

    // this method will handle the onclick options click
    private fun performOptionsMenuClick(position: Int,view : View,data:SAcqPODetail) {
        val popupMenu = PopupMenu(context , view)
        popupMenu.inflate(R.menu.options_menu)
        popupMenu.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener{
            override fun onMenuItemClick(item: MenuItem?): Boolean {
                when(item?.itemId){
                    R.id.action_edit -> {
                        popupMenu.dismiss()
                        listener.editPoItemClicked(position,data)

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
                        listener.viewPoItemClicked(position,data)
                    }

                }
                return false
            }
        })
        popupMenu.show()
    }
}
