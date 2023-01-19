package com.smarthub.baseapplication.ui.fragments.plandesign.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.PlandesignNocAttachmentsBinding
import com.smarthub.baseapplication.databinding.PlandesignNocRequirmentsBinding
import com.smarthub.baseapplication.model.siteInfo.planAndDesign.NOCAndComp
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.ui.fragments.plandesign.tableAdapters.NocTableAdapter
import com.smarthub.baseapplication.utils.AppLogger

class NocCompAdapter (var context: Context, var listener:NocCompListListner, allData:List<NOCAndComp>?):
    RecyclerView.Adapter<NocCompAdapter.ViewHold>() {
    var list : ArrayList<String> = ArrayList()
    var currentOpened = -1
    private var data: NOCAndComp?=null
    var type1 = "NOC & Compliance Requirements"
    var type2 = "Attachments"
    init {
        list.add("NOC & Compliance Requirements")
        list.add("Attachments")

        try {
            data=allData?.get(0)
        }catch (e:java.lang.Exception){
            Toast.makeText(context,"PlanDesign noc Adapter error :${e.localizedMessage}", Toast.LENGTH_LONG).show()
        }
    }
    open class ViewHold(itemView: View): RecyclerView.ViewHolder(itemView)
    class NocCompViewHold(itemView: View):ViewHold(itemView){
        var binding: PlandesignNocRequirmentsBinding = PlandesignNocRequirmentsBinding.bind(itemView)
        val nocTableList:RecyclerView=binding.nocTable
        init {
            binding.collapsingLayout.tag = false

            if ((binding.collapsingLayout.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }
            binding.imgAdd.setOnClickListener {
                addTableItem("dfsdh")
            }
        }
        private fun addTableItem(item:String){
            if (nocTableList.adapter!=null && nocTableList.adapter is NocTableAdapter){
                val adapter = nocTableList.adapter as NocTableAdapter
                adapter.addItem(item)
            }
        }
    }
    class AttachmentsViewHold(itemView: View, listener: NocCompListListner):ViewHold(itemView){
        var binding: PlandesignNocAttachmentsBinding = PlandesignNocAttachmentsBinding.bind(itemView)
        val adapter =  ImageAttachmentAdapter(object : ImageAttachmentAdapter.ItemClickListener{
            override fun itemClicked() {
                listener.attachmentItemClicked()
            }
        })
        init {
            binding.collapsingLayout.tag = true


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
        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_empty,parent,false)
        when(viewType){
            1->{
                view = LayoutInflater.from(parent.context).inflate(R.layout.plandesign_noc_requirments,parent,false)
                return NocCompViewHold(view)
            }
            2->{
                view = LayoutInflater.from(parent.context).inflate(R.layout.plandesign_noc_attachments,parent,false)
                return AttachmentsViewHold(view,listener)
            }
        }
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        when(holder){
            is NocCompViewHold->{
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                    holder.binding.imgAdd.visibility = View.VISIBLE
                }
                else {
                    holder.binding.collapsingLayout.tag = false
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                    holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    holder.binding.itemLine.visibility = View.VISIBLE
                    holder.binding.itemCollapse.visibility = View.GONE
                    holder.binding.imgAdd.visibility = View.GONE
                }
                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
                holder.binding.itemTitleStr.text = list[position]

                try {
                    holder.binding.remarks.text=data?.Remark
                }catch (e:java.lang.Exception){
                    AppLogger.log("PlanDesign noc Adapter error : ${e.localizedMessage}")
                    Toast.makeText(context,"PlanDesign noc Adapter error :${e.localizedMessage}",
                        Toast.LENGTH_LONG).show()

                }
                holder.nocTableList.adapter=NocTableAdapter(context,listener)
            }
            is AttachmentsViewHold->{
                if (currentOpened == position) {
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                }
                else {
                    holder.binding.collapsingLayout.tag = true
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
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

    interface NocCompListListner{
        fun attachmentItemClicked()
        fun editNocComp()
        fun editNocTableItem(position: Int)
        fun viewNocTableItem(position: Int)

    }
}
