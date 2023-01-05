package com.smarthub.baseapplication.ui.fragments.plandesign.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.PlandesignTowerInfoBinding
import com.smarthub.baseapplication.model.siteInfo.planAndDesign.PlanningAndDesignTowerAndCivil
import com.smarthub.baseapplication.model.siteInfo.planAndDesign.Pole
import com.smarthub.baseapplication.model.siteInfo.planAndDesign.Tower

class plandesignTowerCivilAdapter(var context: Context, var listener:TowrCivilListner,allData:List<PlanningAndDesignTowerAndCivil>?):RecyclerView.Adapter<plandesignTowerCivilAdapter.ViewHold>() {
    var list : ArrayList<String> = ArrayList()
    var currentOpened = -1
    private var data:PlanningAndDesignTowerAndCivil?=null
    private var towerData:Tower?=null
    private var poleData:Pole?=null
    var type1 = "Tower"
    var type2= "Pole"
    var type3 = "Attachments"
    init {
        list.add("Equipment Room")
        list.add("Pole")
        list.add("Attachments")

        try {
            data=allData?.get(0)
            towerData=data?.Tower?.get(0)
            poleData=data?.Pole?.get(0)
        }catch (e:java.lang.Exception){
            Toast.makeText(context,"PlanDesign TwrCivil Adapter error :${e.localizedMessage}", Toast.LENGTH_LONG).show()
        }
    }
    override fun getItemViewType(position: Int): Int {
        if (list[position] is String && list[position]==type1)
            return 1
        else if (list[position] is String && list[position]==type2)
            return 2
        else if (list[position] is String && list[position]==type3)
            return 3
        return 0
    }
    open class ViewHold(itemView: View): RecyclerView.ViewHolder(itemView)
    class TowerViewHold(itemView: View):ViewHold(itemView){
        val binding:PlandesignTowerInfoBinding=PlandesignTowerInfoBinding.bind(itemView)

        init {
            binding.collapsingLayout.tag = false

            if ((binding.collapsingLayout.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }


        }
    }
    class PoleViewHold(itemView: View):ViewHold(itemView){

    }
    class AttachmentsViewHold(itemView: View):ViewHold(itemView){

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_empty,parent,false)
        when(viewType){
            1->{
                view = LayoutInflater.from(parent.context).inflate(R.layout.layout_empty,parent,false)
                return TowerViewHold(view)
            }
            2->{
                view = LayoutInflater.from(parent.context).inflate(R.layout.layout_empty,parent,false)
                return PoleViewHold(view)
            }
            3->{
                view = LayoutInflater.from(parent.context).inflate(R.layout.layout_empty,parent,false)
                return PoleViewHold(view)
            }
        }
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        when(holder){
            is TowerViewHold->{
                holder.binding.imgEdit.setOnClickListener {
                    listener.editTower()
                }
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                    holder.binding.imgEdit.visibility = View.VISIBLE
                }
                else {
                    holder.binding.collapsingLayout.tag = false
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                    holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    holder.binding.itemLine.visibility = View.VISIBLE
                    holder.binding.itemCollapse.visibility = View.GONE
                    holder.binding.imgEdit.visibility = View.GONE
                }
                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
                holder.binding.itemTitleStr.text = list[position]
            }
            is PoleViewHold->{

            }
            is AttachmentsViewHold->{

            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    var recyclerView: RecyclerView?=null
    fun updateList(position: Int){
        currentOpened = if(currentOpened == position) -1 else position
        notifyDataSetChanged()
        if (this.recyclerView!=null)
            this.recyclerView?.scrollToPosition(position)
    }

    interface TowrCivilListner{
        fun attachmentItemClicked()
        fun editTower()
    }
}