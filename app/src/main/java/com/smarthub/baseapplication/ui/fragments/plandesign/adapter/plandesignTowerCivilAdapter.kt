package com.smarthub.baseapplication.ui.fragments.plandesign.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.PlandesignPoleInfoBinding
import com.smarthub.baseapplication.databinding.PlandesignTowerAttachmentsBinding
import com.smarthub.baseapplication.databinding.PlandesignTowerInfoBinding
import com.smarthub.baseapplication.model.siteInfo.planAndDesign.PlanningAndDesignTowerAndCivil
import com.smarthub.baseapplication.model.siteInfo.planAndDesign.Pole
import com.smarthub.baseapplication.model.siteInfo.planAndDesign.Tower
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.utils.AppLogger

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
        list.add("Tower")
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
    open class ViewHold(itemView: View): RecyclerView.ViewHolder(itemView)
    class TowerViewHold(itemView: View):ViewHold(itemView){
        var binding:PlandesignTowerInfoBinding=PlandesignTowerInfoBinding.bind(itemView)

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
        var binding:PlandesignPoleInfoBinding=PlandesignPoleInfoBinding.bind(itemView)
        val poleTableList:RecyclerView=binding.poleTable
        init {
            binding.collapsingLayout.tag = false
            if ((binding.collapsingLayout.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }
            binding.AddNewRow.setOnClickListener {
                addTableItem("dfsdh")
            }
        }
        private fun addTableItem(item:String){
            if (poleTableList.adapter!=null && poleTableList.adapter is PoleTableAdapter){
                val adapter = poleTableList.adapter as PoleTableAdapter
                adapter.addItem(item)
            }
        }
    }
    class AttachmentsViewHold(itemView: View,listener: TowrCivilListner):ViewHold(itemView){
        var binding: PlandesignTowerAttachmentsBinding=PlandesignTowerAttachmentsBinding.bind(itemView)
        val adapter =  ImageAttachmentAdapter(object : ImageAttachmentAdapter.ItemClickListener{
            override fun itemClicked() {
                listener.attachmentItemClicked()
            }
        })
        init {
            binding.collapsingLayout.tag = false

            if ((binding.collapsingLayout.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }

            val recyclerListener = itemView.findViewById<RecyclerView>(R.id.list_item)
            recyclerListener.adapter = adapter

            itemView.findViewById<View>(R.id.attach_card).setOnClickListener {
                adapter.addItem()
            }
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_empty,parent,false)
        when(viewType){
            1->{
                view = LayoutInflater.from(parent.context).inflate(R.layout.plandesign_tower_info,parent,false)
                return TowerViewHold(view)
            }
            2->{
                view = LayoutInflater.from(parent.context).inflate(R.layout.plandesign_pole_info,parent,false)
                return PoleViewHold(view)
            }
            3->{
                view = LayoutInflater.from(parent.context).inflate(R.layout.plandesign_tower_attachments,parent,false)
                return AttachmentsViewHold(view,listener)
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

                try {
                    holder.binding.towerHeight.text=towerData?.TowerHeight
                    holder.binding.totalAnteenaWeight.text=towerData?.TotalAntennaWeight
                    holder.binding.AntenaSpace.text=towerData?.AntennaSpace
                    holder.binding.MaxAnteenaHeight.text=towerData?.MaxAntennaHeightAGL
                    holder.binding.OffsetPoleCount.text=towerData?.OffsetPoleCount
                    holder.binding.OffsetPoleLenth.text=towerData?.OffsetPoleLength
                }catch (e:java.lang.Exception){
                    AppLogger.log("PlanDesign twrCivil Adapter error : ${e.localizedMessage}")
                    Toast.makeText(context,"PlanDesign twrCivil Adapter error :${e.localizedMessage}",Toast.LENGTH_LONG).show()

                }
            }
            is PoleViewHold->{
                holder.binding.imgEdit.setOnClickListener {
                    listener.editPole()
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
                holder.poleTableList.adapter=PoleTableAdapter(context,listener)
                try {
                    holder.binding.towerOffsetPoleCount.text = poleData?.OffsetPoleCount
                    holder.binding.towerOffsetPoleLength.text = poleData?.OffsetPoleLength
                    holder.binding.remarks.text = poleData?.Remark
                }catch (e:java.lang.Exception){
                    AppLogger.log("PlanDesign twrCivil Adapter error : ${e.localizedMessage}")
                    Toast.makeText(context,"PlanDesign twrCivil Adapter error :${e.localizedMessage}",Toast.LENGTH_LONG).show()

                }
            }
            is AttachmentsViewHold->{
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                }
                else {
                    holder.binding.collapsingLayout.tag = false
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                    holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    holder.binding.itemLine.visibility = View.VISIBLE
                    holder.binding.itemCollapse.visibility = View.GONE
                }
                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
                holder.binding.itemTitleStr.text = list[position]

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
        fun editPole()
        fun editPoleTableItem(position: Int)
        fun viewPoleTableItem(position: Int)
    }
}
