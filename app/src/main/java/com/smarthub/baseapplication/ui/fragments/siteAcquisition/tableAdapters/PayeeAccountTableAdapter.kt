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
import com.smarthub.baseapplication.databinding.AcqPayeeAcountsTableItemsBinding
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.SAcqPayeeAccountDetail
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.adapters.SoftAcquisitionFragAdapter
import com.smarthub.baseapplication.utils.AppLogger

class PayeeAccountTableAdapter (var context : Context, var listener : SoftAcquisitionFragAdapter.SoftAcqListListener, var list:ArrayList<SAcqPayeeAccountDetail>?): RecyclerView.Adapter<PayeeAccountTableAdapter.ViewHold>() {


    fun addItem(item:String){

        notifyItemInserted(list?.size!!.plus(1))
    }

    fun removeItem(position:Int){
        list?.removeAt(position)
        notifyItemRemoved(position)
    }

    class ViewHold(view: View) : RecyclerView.ViewHolder(view){
        var binding= AcqPayeeAcountsTableItemsBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.acq_payee_acounts_table_items,parent,false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        val item:SAcqPayeeAccountDetail=list!![position]
        holder.binding.menu.setOnClickListener {
            performOptionsMenuClick(position,it,item)
        }
        try {
            holder.binding.PayeeName.text=item.PayeeName
            holder.binding.accountNo.text=item.AccountNumber
            holder.binding.share.text=item.Share
            holder.binding.SrNo.text=position.plus(1).toString()
        }catch (e:java.lang.Exception){
            AppLogger.log("ToewerPoTableadapter error : ${e.localizedMessage}")
            Toast.makeText(context,"ToewerPoTableadapter error :${e.localizedMessage}",Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount(): Int {
        return list?.size!!
    }

    // this method will handle the onclick options click
    private fun performOptionsMenuClick(position: Int,view : View,data:SAcqPayeeAccountDetail) {
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
                        return true
                    }

                    R.id.action_view -> {
                        popupMenu.dismiss()
                        listener.viewPayeeAccountClicked(position,data)
                    }

                }
                return false
            }
        })
        popupMenu.show()
    }
}
