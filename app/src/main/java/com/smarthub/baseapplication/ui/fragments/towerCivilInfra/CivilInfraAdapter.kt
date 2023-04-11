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
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.NewTowerCivilAllData
import com.smarthub.baseapplication.model.siteInfo.towerAndCivilInfra.*
import com.smarthub.baseapplication.utils.AppLogger

class CivilInfraAdapter (var context: Context, var listner: CivilInfraAdapterListner, var id:String): Adapter<CivilInfraAdapter.ViewHold>() {

    var list : ArrayList<String> = ArrayList()
    var datalist:ArrayList<NewTowerCivilAllData>?=null

    var type1 = "Tower"
    var type2 = "Pole"
    var type3 = "Equipment Room"
    var type4= "Earthing"
    init {
        list.add("Tower")
        list.add("Pole")
        list.add("Equipment Room")
        list.add("Earthing")
    }
    fun setData(data: ArrayList<NewTowerCivilAllData>?) {
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
                holder.itemView.setOnClickListener {
                    if (datalist!=null && datalist?.isNotEmpty()==true)
                        listner.clickedTowerItem(id,datalist?.get(0))
                    else AppLogger.log("data Null : $datalist")
                }
            }catch (e:java.lang.Exception){
                AppLogger.log("Noc Fragment error : ${e.localizedMessage}")
            }
            holder.binding.addItem.setOnClickListener {
                listner.addTower()
            }
            holder.binding.titalStr.text = list[position]
        }
        else if (list[position]==type2) {
            try {
                holder.itemView.setOnClickListener {
                    if (datalist!=null && datalist?.isNotEmpty()==true)
                        listner.clickedPoleItem(id,datalist?.get(0))
                    else Toast.makeText(context,"data null",Toast.LENGTH_LONG).show()
                }
            }catch (e:java.lang.Exception){
                AppLogger.log("Noc Fragment error : ${e.localizedMessage}")
            }
            holder.binding.titalStr.text = list[position]
            holder.binding.addItem.setOnClickListener {
                listner.addPole()
            }
        }
        else if (list[position]==type3) {
            try {
                holder.itemView.setOnClickListener {
                    if (datalist!=null)
                        listner.clickedEquipmentRoomItem(id,datalist)
                    else Toast.makeText(context,"data null",Toast.LENGTH_LONG).show()
                }
            }catch (e:java.lang.Exception){
                AppLogger.log("Noc Fragment error : ${e.localizedMessage}")
            }
            holder.binding.titalStr.text = list[position]
            holder.binding.addItem.setOnClickListener {
                listner.addEquipmentRoom()
            }
        }
        else if (list[position]==type4) {
            try {

                 holder.itemView.setOnClickListener {
                     if (datalist!=null)
                         listner.clickedEarthingItem(id,datalist)
                     else Toast.makeText(context,"data null",Toast.LENGTH_LONG).show()
                 }
            }catch (e:java.lang.Exception){
                AppLogger.log("Noc Fragment error : ${e.localizedMessage}")
            }
            holder.binding.titalStr.text = list[position]
            holder.binding.addItem.setOnClickListener {
                listner.addEarthing()
            }
        }
        else{
            try {
                holder.itemView.setOnClickListener {
                    if (datalist!=null && datalist?.isNotEmpty()==true)
                        listner.clickedTowerItem(id,datalist?.get(0))
                    else AppLogger.log("data null:===> $datalist")
                }
            }catch (e:java.lang.Exception){
                AppLogger.log("Noc Fragment error : ${e.localizedMessage}")
            }
            holder.binding.titalStr.text = list[position]
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface CivilInfraAdapterListner{
        fun clickedTowerItem(id:String,data: NewTowerCivilAllData?)
        fun clickedPoleItem(id:String,data: NewTowerCivilAllData?)
        fun clickedEquipmentRoomItem(id:String,data:ArrayList<NewTowerCivilAllData>?)
        fun clickedEarthingItem(id:String,data: ArrayList<NewTowerCivilAllData>?)
        fun addTower()
        fun addPole()
        fun addEquipmentRoom()
        fun addEarthing()
    }
}

