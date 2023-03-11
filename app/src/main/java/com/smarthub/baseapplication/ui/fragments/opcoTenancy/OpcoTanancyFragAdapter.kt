package com.smarthub.baseapplication.ui.fragments.opcoTenancy

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.gson.Gson
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.CustomerListItemBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.OpcoTenencyAllData
import com.smarthub.baseapplication.model.siteInfo.BasicInfoModelItem
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.OpcoDataItem
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class OpcoTanancyFragAdapter(var context:Context,var listener: CustomerDataAdapterListener) : Adapter<OpcoDataViewHolder>() {
    var data = ArrayList<Any>()



    fun addLoading(){
        this.data.clear()
        this.data.add("loading")
        notifyDataSetChanged()
    }

    fun setOpData(data: ArrayList<OpcoTenencyAllData>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (data[position] is OpcoTenencyAllData) 0 else 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OpcoDataViewHolder {
        return if (viewType == 0){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.customer_list_item, parent, false)
            OpcoDataItemViewHolder(view)
        }else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.list_loading_bar, parent, false)
            OpcoDataViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: OpcoDataViewHolder, position: Int) {
        if(holder is OpcoDataItemViewHolder) {
            val item = data[position] as OpcoTenencyAllData

            try {
                holder.binding.SiteId.text = "#${item.Opcoinfo[0].SRInfo01.get(0).OpcoSiteId}"
                holder.binding.titel.text=item.Opcoinfo[0].SRInfo01.get(0).OpcoName
                holder.binding.textRfiDate.text = Utils.getFormatedDate(item.Opcoinfo[0].SRInfo01.get(0).RFIAcceptanceDate.substring(0,10),"dd-MMM-yyyy")
                holder.binding.textRfsDate.text = Utils.getFormatedDate(item.Opcoinfo[0].SRInfo01.get(0).RFRDate.substring(0,10),"dd-MMM-yyyy")
                AppPreferences.getInstance().setDropDown(holder.binding.opcoSiteType,DropDowns.Opcositetype.name,item.Opcoinfo[0].SRInfo01.get(0).Opcositetype.get(0).toString())
            }catch (e:java.lang.Exception){
                AppLogger.log("opcotenency card error : ${e.localizedMessage}")
                Toast.makeText(context,"opcotenency card error :${e.localizedMessage}", Toast.LENGTH_LONG).show()

            }
            holder.itemview.setOnClickListener {
                listener.clickedItem(item,position)
                AppLogger.log("clicked card data : ===> ${Gson().toJson(item.Opcoinfo.get(0))}")
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

open class OpcoDataViewHolder(var itemview: View) : ViewHolder(itemview) {}

class OpcoDataItemViewHolder(itemview: View) : OpcoDataViewHolder(itemview) {
    var binding = CustomerListItemBinding.bind(itemView)
}

interface CustomerDataAdapterListener{
    fun clickedItem(data : OpcoTenencyAllData,parentIndex:Int)
}