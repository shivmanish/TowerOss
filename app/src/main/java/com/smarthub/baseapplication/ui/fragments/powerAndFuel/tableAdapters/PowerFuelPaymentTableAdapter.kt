package com.smarthub.baseapplication.ui.fragments.powerAndFuel.tableAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.PowerAuthorityPaymentTableItemBinding
import com.smarthub.baseapplication.databinding.TowerPoTableItemBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.PowerFuelAuthorityPayments
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.PowerFuelPODetail
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.TwrCivilPODetail
import com.smarthub.baseapplication.model.siteInfo.towerAndCivilInfra.TowerModelAuthorityPODetail
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.adapter.PowerConnecFragAdapter
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.TowerInfoListAdapter
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class PowerFuelPaymentTableAdapter (var context : Context, var listener : PowerConnecFragAdapter.PowerConnectionListListener, var list:ArrayList<PowerFuelAuthorityPayments>?): RecyclerView.Adapter<PowerFuelPaymentTableAdapter.ViewHold>() {


    fun addItem(item:String){

        notifyItemInserted(list?.size!!.plus(1))
    }

    fun removeItem(position:Int){
        list?.removeAt(position)
        notifyItemRemoved(position)
    }

    class ViewHold(view: View) : RecyclerView.ViewHolder(view){
        var binding= PowerAuthorityPaymentTableItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.power_authority_payment_table_item,parent,false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        val item:PowerFuelAuthorityPayments=list!![position]
        holder.binding.menu.setOnClickListener {
            performOptionsMenuClick(position,it,item)
        }
        try {
            holder.binding.paymentType.text=item.PaymentType
            holder.binding.Amount.text=item.Amount
            holder.binding.dueDate.text=Utils.getFormatedDate(item.DueDate.substring(0,10),"dd-MMM-yyyy")
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
    private fun performOptionsMenuClick(position: Int,view : View,data:PowerFuelAuthorityPayments) {
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
                        listener.viewAuthorityPaymentClicked(position,data)
                        Toast.makeText(context , "Item 2 clicked" , Toast.LENGTH_SHORT).show()
                    }

                }
                return false
            }
        })
        popupMenu.show()
    }
}
