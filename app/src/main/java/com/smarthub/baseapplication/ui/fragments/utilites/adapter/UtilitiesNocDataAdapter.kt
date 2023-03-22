package com.smarthub.baseapplication.ui.fragments.utilites.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.UtilitiesSmpsListItemBinding
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityEquipmentAllData
import com.smarthub.baseapplication.utils.AppLogger

class UtilitesNocDataAdapter(var listener: UtilitesNocDataAdapterListener) : Adapter<UtilitesNocDataAdapter.EmptyViewHolder>() {

    var utilitydata: UtilityEquipmentAllData?=null
    var list: ArrayList<String> = ArrayList()

    val type1 = "SMPS"
    val type2 = "Battery Bank"
    val type3 = "DG"
    val type4 = "AC"
    val type5 = "Fire Extinguisher"
    val type6 = "Surge Protection Device"
    val type7 = "Power Distribution Box"
    val type8 = "Cables"
    val type9 = "loading"
    val type10 = "Empty"

    init {
        list.add("SMPS")
        list.add("Battery Bank")
        list.add("DG")
        list.add("AC")
        list.add("Fire Extinguisher")
        list.add("Surge Protection Device")
        list.add("Power Distribution Box")
        list.add("Cables")
    }
    fun setData(data: ArrayList<UtilityEquipmentAllData>?) {
        AppLogger.log("utility dataSize: ${data?.size}")
        try {
            list.clear()
            if (data?.isNotEmpty()==true ){
                utilitydata= data[0]
            }
            else
                AppLogger.log("smps all data: $data")
            list.add("SMPS")
            list.add("Battery Bank")
            list.add("DG")
            list.add("AC")
            list.add("Fire Extinguisher")
            list.add("Surge Protection Device")
            list.add("Power Distribution Box")
            list.add("Cables")
            notifyDataSetChanged()
        }
        catch (e:Exception){
            AppLogger.log("utility Data Is Empty: ${e.localizedMessage}")
        }


    }

    fun addLoading(){
        this.list.clear()
        this.list.add("loading")
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        if (list[position]==type9)
            return 1
        else if (list[position]==type10)
            return 2
        return 0
    }

    open class EmptyViewHolder(var itemview: View): ViewHolder(itemview)

    class DataViewHolder(itemview: View) : EmptyViewHolder(itemview) {
        var binding:UtilitiesSmpsListItemBinding = UtilitiesSmpsListItemBinding.bind(itemView)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmptyViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.no_list_data, parent, false)
        when (viewType){
            0-> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.utilities_smps_list_item, parent, false)
                return DataViewHolder(view)
            }
            1-> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.list_loading_bar, parent, false)
                return EmptyViewHolder(view)
            }
            2-> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.no_list_data, parent, false)
                return EmptyViewHolder(view)
            }
        }
        return EmptyViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmptyViewHolder, position: Int) {

        when(holder){
            is DataViewHolder->{
                if (list[position]==type1){
                    holder.binding.titel.text=list[position]
                    holder.binding.cardItem.setOnClickListener {
                        listener.SMPSItemClicked(utilitydata)
                    }
                    holder.binding.addNewItem.setOnClickListener {
                        listener.addNewSMPS()
                    }
                    if (utilitydata!=null && utilitydata?.UtilityEquipmentSmps!=null)
                        holder.binding.countItem.text= utilitydata?.UtilityEquipmentSmps?.size.toString()
                    else
                        holder.binding.countItem.text= "0"

                }
                else if (list[position]==type2){
                    holder.binding.titel.text=list[position]
                    holder.binding.cardItem.setOnClickListener {
                        listener.BateeryItemClicked(utilitydata)
                    }
                    if (utilitydata!=null && utilitydata?.UtilityEquipmentBatteryBank!=null)
                        holder.binding.countItem.text= utilitydata?.UtilityEquipmentBatteryBank?.size.toString()
                    else
                        holder.binding.countItem.text= "0"
                    holder.binding.addNewItem.setOnClickListener {
                        listener.addNewBatterryBank()
                    }
                }
                else if (list[position]==type3){
                    holder.binding.titel.text=list[position]
                    if (utilitydata!=null && utilitydata?.UtilityEquipmentDG!=null)
                        holder.binding.countItem.text= utilitydata?.UtilityEquipmentDG?.size.toString()
                    else
                        holder.binding.countItem.text= "0"
                    holder.binding.cardItem.setOnClickListener {
                        listener.DGItemClicked(utilitydata)
                    }
                    holder.binding.addNewItem.setOnClickListener {
                        listener.addNewDG()
                    }
                }
                else if (list[position]==type4){
                    holder.binding.titel.text=list[position]
                    if (utilitydata!=null && utilitydata?.UtilityEquipmentAC!=null)
                        holder.binding.countItem.text= utilitydata?.UtilityEquipmentAC?.size.toString()
                    else
                        holder.binding.countItem.text= "0"
                    holder.binding.cardItem.setOnClickListener {
                        listener.ACItemClicked(utilitydata)
                    }
                    holder.binding.addNewItem.setOnClickListener {
                        listener.addNewAC()
                    }
                }
                else if (list[position]==type5){
                    holder.binding.titel.text=list[position]
                    holder.binding.cardItem.setOnClickListener {
//                        listener.clickedItem(position)
                    }
                }
                else if (list[position]==type6){
                    holder.binding.titel.text=list[position]
                    if (utilitydata!=null && utilitydata?.UtilityEquipmentSurgeProtectionDevice!=null)
                        holder.binding.countItem.text= utilitydata?.UtilityEquipmentSurgeProtectionDevice?.size.toString()
                    else
                        holder.binding.countItem.text= "0"
                    holder.binding.cardItem.setOnClickListener {
                        listener.SuregeProtectionDeviceItemClicked(utilitydata)
                    }
                    holder.binding.addNewItem.setOnClickListener {
                        listener.addNewSurgeProtectionDevice(utilitydata)
                    }
                }
                else if (list[position]==type7){
                    holder.binding.titel.text=list[position]
                    holder.binding.cardItem.setOnClickListener {
//                        listener.clickedItem(position)
                    }
                }
                else if (list[position]==type8){
                    holder.binding.titel.text=list[position]
                    holder.binding.cardItem.setOnClickListener {
//                        listener.clickedItem(position)
                    }
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}

interface UtilitesNocDataAdapterListener {
    fun SMPSItemClicked(data:UtilityEquipmentAllData?)
    fun BateeryItemClicked(data:UtilityEquipmentAllData?)
    fun DGItemClicked(data:UtilityEquipmentAllData?)
    fun ACItemClicked(data:UtilityEquipmentAllData?)
    fun FireExtinguisherItemClicked(data:UtilityEquipmentAllData?)
    fun SuregeProtectionDeviceItemClicked(data:UtilityEquipmentAllData?)
    fun PowerDistributionBoxItemClicked(data:UtilityEquipmentAllData?)
    fun CableItemClicked(data:UtilityEquipmentAllData?)
    fun addNewSMPS()
    fun addNewBatterryBank()
    fun addNewDG()
    fun addNewAC()
    fun addNewSurgeProtectionDevice(data:UtilityEquipmentAllData?)
}