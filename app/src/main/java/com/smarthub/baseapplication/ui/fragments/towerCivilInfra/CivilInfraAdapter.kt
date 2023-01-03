package com.smarthub.baseapplication.ui.fragments.towerCivilInfra

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.smarthub.baseapplication.R
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.smarthub.baseapplication.databinding.CivilInfraListItemBinding
import com.smarthub.baseapplication.model.siteInfo.towerAndCivilInfra.TowerAndCivilInfraEarthingModel
import com.smarthub.baseapplication.model.siteInfo.towerAndCivilInfra.TowerAndCivilInfraEquipmentModel
import com.smarthub.baseapplication.model.siteInfo.towerAndCivilInfra.TowerAndCivilInfraTowerModel
import com.smarthub.baseapplication.model.siteInfo.towerAndCivilInfra.TowerCivilInfraAllDataItem
import com.smarthub.baseapplication.utils.AppLogger

class CivilInfraAdapter (var context: Context, var listner: CivilInfraAdapterListner, var id:String): Adapter<CivilInfraAdapter.ViewHold>() {

    var list : ArrayList<String> = ArrayList()
    var datalist:TowerCivilInfraAllDataItem?=null

    var type1 = "Tower"
    var type2 = "Equipment Room"
    var type3= "Earthing"
    init {
        list.add("Tower")
        list.add("Equipment Room")
        list.add("Earthing")
    }
    fun setData(data: TowerCivilInfraAllDataItem?) {
        this.datalist=data
        notifyDataSetChanged()
    }

    class ViewHold(view: View) : ViewHolder(view){
        var binding= CivilInfraListItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.civil_infra_list_item,parent,false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        if (list[position]==type1) {
            try {
                holder.binding.textInstallationDateDate.text=
                    datalist?.TowerAndCivilInfraTowerModel?.get(0)?.
                    TowerAndCivilInfraTowerInstallationAndAcceptance?.get(0)?.InstallationDate
                holder.itemView.setOnClickListener {
                    if (datalist?.TowerAndCivilInfraTowerModel!=null)
                        listner.clickedTowerItem(id,datalist?.TowerAndCivilInfraTowerModel!!)
                    else Toast.makeText(context,"data null",Toast.LENGTH_LONG).show()
                }
            }catch (e:java.lang.Exception){
                AppLogger.log("Noc Fragment error : ${e.localizedMessage}")
                Toast.makeText(context,"Noc Fragment error :${e.localizedMessage}", Toast.LENGTH_LONG).show()
            }
            holder.binding.titalStr.text = list[position]
        }
        else if (list[position]==type2) {
            try {
                holder.binding.textInstallationDateDate.text=
                    datalist?.TowerAndCivilInfraEquipmentModel?.get(0)?.
                    TowerAndCivilInfraTowerInstallationAndAcceptance?.get(0)?.InstallationDate
                holder.itemView.setOnClickListener {
                    if (datalist?.TowerAndCivilInfraEquipmentModel!=null)
                        listner.clickedEquipmentRoomItem(id,datalist?.TowerAndCivilInfraEquipmentModel!!)
                    else Toast.makeText(context,"data null",Toast.LENGTH_LONG).show()
                }
            }catch (e:java.lang.Exception){
                AppLogger.log("Noc Fragment error : ${e.localizedMessage}")
                Toast.makeText(context,"Noc Fragment error :${e.localizedMessage}", Toast.LENGTH_LONG).show()
            }
            holder.binding.titalStr.text = list[position]
        }
        else if (list[position]==type3) {
            try {
                holder.binding.textInstallationDateDate.text=
                    datalist?.TowerAndCivilInfraEarthingModel?.get(0)?.
                    TowerAndCivilInfraTowerInstallationAndAcceptance?.get(0)?.InstallationDate
                holder.itemView.setOnClickListener {
                    listner.clickedEarthingItem(id,datalist?.TowerAndCivilInfraEarthingModel!!)
                }
            }catch (e:java.lang.Exception){
                AppLogger.log("Noc Fragment error : ${e.localizedMessage}")
                Toast.makeText(context,"Noc Fragment error :${e.localizedMessage}", Toast.LENGTH_LONG).show()
            }
            holder.binding.titalStr.text = list[position]
        }
        else{
            try {
                holder.binding.textInstallationDateDate.text=
                    datalist?.TowerAndCivilInfraTowerModel?.get(0)?.
                    TowerAndCivilInfraTowerInstallationAndAcceptance?.get(0)?.InstallationDate
                holder.itemView.setOnClickListener {
                    if (datalist?.TowerAndCivilInfraTowerModel!=null)
                        listner.clickedTowerItem(id,datalist?.TowerAndCivilInfraTowerModel!!)
                    else Toast.makeText(context,"data null",Toast.LENGTH_LONG).show()
                }
            }catch (e:java.lang.Exception){
                AppLogger.log("Noc Fragment error : ${e.localizedMessage}")
                Toast.makeText(context,"Noc Fragment error :${e.localizedMessage}", Toast.LENGTH_LONG).show()
            }
            holder.binding.titalStr.text = list[position]
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface CivilInfraAdapterListner{
        fun clickedTowerItem(id:String,data: List<TowerAndCivilInfraTowerModel>)
        fun clickedPoleItem()
        fun clickedEquipmentRoomItem(id:String,data: List<TowerAndCivilInfraEquipmentModel>)
        fun clickedEarthingItem(id:String,data: List<TowerAndCivilInfraEarthingModel>)
    }
}

