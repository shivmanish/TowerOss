package com.smarthub.baseapplication.ui.adapter.customer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter

class BackhaulListAdapter(var listener: ImageAttachmentAdapter.ItemClickListener) : RecyclerView.Adapter<BackhaulListAdapter.ViewHold>() {

    var list : ArrayList<String> = ArrayList()

    var LINK_VIEW_TYPE =0
    var IDU_VIEW_TYPE =1
    var ODU_VIEW_TYPE =2
    var ANTEENA_VIEW_TYPE =3
    var INSTALLATION_TEAM_VIEW_TYPE =4
    var MATERIALS_VIEW_TYPE =5
    var LMC_VIEW_TYPE =6
    var ATPCHECK_LIST_VIEW_TYPE =7
    var PO_DETAILS_VIEW_TYPE =8
    init {
        list.add("Link")
        list.add("IDU")
        list.add("ODU")
        list.add("Antenna")
        list.add("Installation Team")
        list.add("Materials")
        list.add("LMC; Fiber")
        list.add("ATP Checklist")
        list.add("PO Details")
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
    class LinkViewHold(itemView: View,listener: ImageAttachmentAdapter.ItemClickListener) : ViewHold(itemView) {
        var binding : BackhaulListItemBinding = BackhaulListItemBinding.bind(itemView)
        var adapter =  ImageAttachmentAdapter(listener)
        init {
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_arrow_up,0)
            } else {
                binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.down_arrow,0)
            }

            var recyclerListener = itemView.findViewById<RecyclerView>(R.id.list_item)
            recyclerListener.adapter = adapter

            itemView.findViewById<View>(R.id.attach_card).setOnClickListener {
                adapter.addItem()
            }
        }
    }
    class IDUViewHold(itemView: View,listener: ImageAttachmentAdapter.ItemClickListener) : ViewHold(itemView) {
        var binding : BackhaulListItemBinding = BackhaulListItemBinding.bind(itemView)
        var adapter =  ImageAttachmentAdapter(listener)
        init {
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_arrow_up,0)
            } else {
                binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.down_arrow,0)
            }

            var recyclerListener = itemView.findViewById<RecyclerView>(R.id.list_item)
            recyclerListener.adapter = adapter

            itemView.findViewById<View>(R.id.attach_card).setOnClickListener {
                adapter.addItem()
            }
        }
    }
    class ODUViewHold(itemView: View,listener: ImageAttachmentAdapter.ItemClickListener) : ViewHold(itemView) {
        var binding : BackhaulListItemBinding = BackhaulListItemBinding.bind(itemView)
        var adapter =  ImageAttachmentAdapter(listener)
        init {
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_arrow_up,0)
            } else {
                binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.down_arrow,0)
            }

            var recyclerListener = itemView.findViewById<RecyclerView>(R.id.list_item)
            recyclerListener.adapter = adapter

            itemView.findViewById<View>(R.id.attach_card).setOnClickListener {
                adapter.addItem()
            }
        }
    }
    class AntennaViewHold(itemView: View,listener: ImageAttachmentAdapter.ItemClickListener) : ViewHold(itemView) {
        var binding : BackhaulListItemBinding = BackhaulListItemBinding.bind(itemView)
        var adapter =  ImageAttachmentAdapter(listener)
        init {
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_arrow_up,0)
            } else {
                binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.down_arrow,0)
            }

            var recyclerListener = itemView.findViewById<RecyclerView>(R.id.list_item)
            recyclerListener.adapter = adapter

            itemView.findViewById<View>(R.id.attach_card).setOnClickListener {
                adapter.addItem()
            }
        }
    }
    class InstallationTeamViewHold(itemView: View,listener: ImageAttachmentAdapter.ItemClickListener) : ViewHold(itemView) {
        var binding : BackhaulListItemBinding = BackhaulListItemBinding.bind(itemView)
        var adapter =  ImageAttachmentAdapter(listener)
        init {
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_arrow_up,0)
            } else {
                binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.down_arrow,0)
            }

            var recyclerListener = itemView.findViewById<RecyclerView>(R.id.list_item)
            recyclerListener.adapter = adapter

            itemView.findViewById<View>(R.id.attach_card).setOnClickListener {
                adapter.addItem()
            }
        }
    }
    class MaterialsViewHold(itemView: View,listener: ImageAttachmentAdapter.ItemClickListener) : ViewHold(itemView) {
        var binding : BackhaulListItemBinding = BackhaulListItemBinding.bind(itemView)
        var adapter =  ImageAttachmentAdapter(listener)
        init {
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_arrow_up,0)
            } else {
                binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.down_arrow,0)
            }

            var recyclerListener = itemView.findViewById<RecyclerView>(R.id.list_item)
            recyclerListener.adapter = adapter

            itemView.findViewById<View>(R.id.attach_card).setOnClickListener {
                adapter.addItem()
            }
        }
    }
    class LMCViewHold(itemView: View,listener: ImageAttachmentAdapter.ItemClickListener) : ViewHold(itemView) {
        var binding : BackhaulListItemBinding = BackhaulListItemBinding.bind(itemView)
        var adapter =  ImageAttachmentAdapter(listener)
        init {
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_arrow_up,0)
            } else {
                binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.down_arrow,0)
            }

            var recyclerListener = itemView.findViewById<RecyclerView>(R.id.list_item)
            recyclerListener.adapter = adapter

            itemView.findViewById<View>(R.id.attach_card).setOnClickListener {
                adapter.addItem()
            }
        }
    }
    class PODetailsViewHold(itemView: View,listener: ImageAttachmentAdapter.ItemClickListener) : ViewHold(itemView) {
        var binding : BackhaulListItemBinding = BackhaulListItemBinding.bind(itemView)
        var adapter =  ImageAttachmentAdapter(listener)
        init {
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_arrow_up,0)
            } else {
                binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.down_arrow,0)
            }

            var recyclerListener = itemView.findViewById<RecyclerView>(R.id.list_item)
            recyclerListener.adapter = adapter

            itemView.findViewById<View>(R.id.attach_card).setOnClickListener {
                adapter.addItem()
            }
        }
    }

    class ATPCHECKViewHold(itemView: View) : ViewHold(itemView) {
        var binding : AtpChecklistBinding = AtpChecklistBinding.bind(itemView)
      //  var adapter =  ImageAttachmentAdapter(listener)
        init {
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_arrow_up,0)
            } else {
                binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.down_arrow,0)
            }

         /*   var recyclerListener = itemView.findViewById<RecyclerView>(R.id.list_item)
            recyclerListener.adapter = adapter

            itemView.findViewById<View>(R.id.attach_card).setOnClickListener {
                adapter.addItem()
            }*/
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.backhaul_list_item,parent,false)
        return when(viewType){
            LINK_VIEW_TYPE->{
                view = LayoutInflater.from(parent.context).inflate(R.layout.backhaul_list_item,parent,false)
                LinkViewHold(view,listener)
            }
            IDU_VIEW_TYPE->{
                view = LayoutInflater.from(parent.context).inflate(R.layout.backhaul_list_item,parent,false)
                IDUViewHold(view,listener)
            }
            ODU_VIEW_TYPE->{
                view = LayoutInflater.from(parent.context).inflate(R.layout.backhaul_list_item,parent,false)
                ODUViewHold(view,listener)
            }
            ANTEENA_VIEW_TYPE->{
                view = LayoutInflater.from(parent.context).inflate(R.layout.backhaul_list_item,parent,false)
                AntennaViewHold(view,listener)
            }
            INSTALLATION_TEAM_VIEW_TYPE->{
                view = LayoutInflater.from(parent.context).inflate(R.layout.backhaul_list_item,parent,false)
                InstallationTeamViewHold(view,listener)
            }
            MATERIALS_VIEW_TYPE->{
                view = LayoutInflater.from(parent.context).inflate(R.layout.backhaul_list_item,parent,false)
                MaterialsViewHold(view,listener)
            }
            LMC_VIEW_TYPE->{
                view = LayoutInflater.from(parent.context).inflate(R.layout.backhaul_list_item,parent,false)
                LMCViewHold(view,listener)
            }
            ATPCHECK_LIST_VIEW_TYPE->{
                view = LayoutInflater.from(parent.context).inflate(R.layout.atp_checklist,parent,false)
                ATPCHECKViewHold(view)
            }
            PO_DETAILS_VIEW_TYPE->{
                view = LayoutInflater.from(parent.context).inflate(R.layout.backhaul_list_item,parent,false)
                PODetailsViewHold(view,listener)
            }
            else -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.backhaul_list_item,parent,false)
                ViewHold(view)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position] == "Link") LINK_VIEW_TYPE
        else if (list[position] == "IDU") IDU_VIEW_TYPE
        else if (list[position] == "ODU") ODU_VIEW_TYPE
        else if (list[position] == "Antenna") ANTEENA_VIEW_TYPE
        else if (list[position] == "Installation Team") INSTALLATION_TEAM_VIEW_TYPE
        else if (list[position] == "Materials") MATERIALS_VIEW_TYPE
        else if (list[position] == "LMC; Fiber") LMC_VIEW_TYPE
        else if (list[position] == "ATP Checklist") ATPCHECK_LIST_VIEW_TYPE
        else if (list[position] == "PO Details") PO_DETAILS_VIEW_TYPE
        else 0
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
       if (holder is LinkViewHold){

           holder.binding.collapsingLayout.setOnClickListener {
               holder.binding.itemTitle.tag = !(holder.binding.itemTitle.tag as Boolean)
               if ((holder.binding.itemTitle.tag as Boolean)) {
                   holder.binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_arrow_up,0)
               } else {
                   holder.binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.arrow_farword,0)
               }

               holder.binding.itemLine.visibility =
                   if (holder.binding.itemTitle.tag as Boolean) View.GONE else View.VISIBLE
               holder.binding.itemCollapse.visibility =
                   if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE
           }
           holder.binding.itemTitle.text = list[position]
       }
       else if (holder is IDUViewHold){
           holder.binding.collapsingLayout.setOnClickListener {
               holder.binding.itemTitle.tag = !(holder.binding.itemTitle.tag as Boolean)
               if ((holder.binding.itemTitle.tag as Boolean)) {
                   holder.binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_arrow_up,0)
               } else {
                   holder.binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.arrow_farword,0)
               }

               holder.binding.itemLine.visibility =
                   if (holder.binding.itemTitle.tag as Boolean) View.GONE else View.VISIBLE
               holder.binding.itemCollapse.visibility =
                   if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE
           }
           holder.binding.itemTitle.text = list[position]

       }
       else if (holder is ODUViewHold){
           holder.binding.collapsingLayout.setOnClickListener {
               holder.binding.itemTitle.tag = !(holder.binding.itemTitle.tag as Boolean)
               if ((holder.binding.itemTitle.tag as Boolean)) {
                   holder.binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_arrow_up,0)
               } else {
                   holder.binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.arrow_farword,0)
               }

               holder.binding.itemLine.visibility =
                   if (holder.binding.itemTitle.tag as Boolean) View.GONE else View.VISIBLE
               holder.binding.itemCollapse.visibility =
                   if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE
           }
           holder.binding.itemTitle.text = list[position]

       }
       else if (holder is AntennaViewHold){
           holder.binding.collapsingLayout.setOnClickListener {
               holder.binding.itemTitle.tag = !(holder.binding.itemTitle.tag as Boolean)
               if ((holder.binding.itemTitle.tag as Boolean)) {
                   holder.binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_arrow_up,0)
               } else {
                   holder.binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.arrow_farword,0)
               }

               holder.binding.itemLine.visibility =
                   if (holder.binding.itemTitle.tag as Boolean) View.GONE else View.VISIBLE
               holder.binding.itemCollapse.visibility =
                   if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE
           }
           holder.binding.itemTitle.text = list[position]

       }
       else if (holder is InstallationTeamViewHold){
           holder.binding.collapsingLayout.setOnClickListener {
               holder.binding.itemTitle.tag = !(holder.binding.itemTitle.tag as Boolean)
               if ((holder.binding.itemTitle.tag as Boolean)) {
                   holder.binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_arrow_up,0)
               } else {
                   holder.binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.arrow_farword,0)
               }

               holder.binding.itemLine.visibility =
                   if (holder.binding.itemTitle.tag as Boolean) View.GONE else View.VISIBLE
               holder.binding.itemCollapse.visibility =
                   if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE
           }
           holder.binding.itemTitle.text = list[position]

       }
       else if (holder is MaterialsViewHold){
           holder.binding.collapsingLayout.setOnClickListener {
               holder.binding.itemTitle.tag = !(holder.binding.itemTitle.tag as Boolean)
               if ((holder.binding.itemTitle.tag as Boolean)) {
                   holder.binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_arrow_up,0)
               } else {
                   holder.binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.arrow_farword,0)
               }

               holder.binding.itemLine.visibility =
                   if (holder.binding.itemTitle.tag as Boolean) View.GONE else View.VISIBLE
               holder.binding.itemCollapse.visibility =
                   if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE
           }
           holder.binding.itemTitle.text = list[position]

       }
       else if (holder is LMCViewHold){
           holder.binding.collapsingLayout.setOnClickListener {
               holder.binding.itemTitle.tag = !(holder.binding.itemTitle.tag as Boolean)
               if ((holder.binding.itemTitle.tag as Boolean)) {
                   holder.binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_arrow_up,0)
               } else {
                   holder.binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.arrow_farword,0)
               }

               holder.binding.itemLine.visibility =
                   if (holder.binding.itemTitle.tag as Boolean) View.GONE else View.VISIBLE
               holder.binding.itemCollapse.visibility =
                   if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE
           }
           holder.binding.itemTitle.text = list[position]

       }
       else if (holder is PODetailsViewHold){
           holder.binding.collapsingLayout.setOnClickListener {
               holder.binding.itemTitle.tag = !(holder.binding.itemTitle.tag as Boolean)
               if ((holder.binding.itemTitle.tag as Boolean)) {
                   holder.binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_arrow_up,0)
               } else {
                   holder.binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.arrow_farword,0)
               }

               holder.binding.itemLine.visibility =
                   if (holder.binding.itemTitle.tag as Boolean) View.GONE else View.VISIBLE
               holder.binding.itemCollapse.visibility =
                   if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE
           }
           holder.binding.itemTitle.text = list[position]

       }
       else if (holder is ATPCHECKViewHold){
           holder.binding.collapsingLayout.setOnClickListener {
               holder.binding.itemTitle.tag = !(holder.binding.itemTitle.tag as Boolean)
               if ((holder.binding.itemTitle.tag as Boolean)) {
                   holder.binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_arrow_up,0)
               } else {
                   holder.binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.arrow_farword,0)
               }

               holder.binding.itemLine.visibility =
                   if (holder.binding.itemTitle.tag as Boolean) View.GONE else View.VISIBLE
               holder.binding.itemCollapse.visibility =
                   if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE
           }
           holder.binding.itemTitle.text = list[position]

       }
    }
    override fun getItemCount(): Int {
        return list.size
    }

    interface ItemClickListener{
        fun itemClicked()
    }
}