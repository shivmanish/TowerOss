package com.smarthub.baseapplication.ui.fragments.opcoTenancy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.OpcoDataItem
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.Opcoinfo
import com.smarthub.baseapplication.network.pojo.site_info.BasicInfoModelDropDown
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.ui.fragments.noc.NocListAdapter

class OpcoSiteInfoFragAdapter(var listener: OpcoInfoLisListener,var opcodata: OpcoDataItem?) : RecyclerView.Adapter<OpcoSiteInfoFragAdapter.ViewHold>() {

    var list : ArrayList<String> = ArrayList()
    var currentOpened = -1

    var type1 = "OPCO/Site Info"
    var type2 = "Operations Team"
    var type3 = "Attachments"
    private var data : Opcoinfo? = opcodata?.Opcoinfo?.get(0) ?:null


    init {
        list.add("OPCO/Site Info")
        list.add("Operations Team")
        list.add("Attachments")
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun getItemViewType(position: Int): Int {
        if (list[position] is String && list[position]==type1)
            return 1
        else if (list[position] is String && list[position]==type2)
            return 2
        else if (list[position] is String && list[position]==type3)
            return 3
        return 0
    }

    class ViewHold1(itemView: View) : ViewHold(itemView) {
        var binding : OpcoSiteInfoItemBinding = OpcoSiteInfoItemBinding.bind(itemView)

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
    class ViewHold2(itemView: View) : ViewHold(itemView) {
        var binding : OpcoOperationsTeamItemBinding = OpcoOperationsTeamItemBinding.bind(itemView)

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
    class ViewHold3(itemView: View,listener: OpcoInfoLisListener) : ViewHold(itemView) {
        var binding : OpcoAttachmentBinding = OpcoAttachmentBinding.bind(itemView)

        var adapter =  ImageAttachmentAdapter(object : ImageAttachmentAdapter.ItemClickListener{
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

            var recyclerListener = itemView.findViewById<RecyclerView>(R.id.list_item)
            recyclerListener.adapter = adapter
            itemView.findViewById<View>(R.id.attach_card).setOnClickListener {
                adapter.addItem()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_empty,parent,false)
        when (viewType) {
            1 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.opco_site_info_item, parent, false)
                return ViewHold1(view)
            }
            2 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.opco_operations_team_item, parent, false)
                return ViewHold2(view)
            }
            3 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.opco_attachment, parent, false)
                return ViewHold3(view,listener)
            }

        }
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        when (holder) {
            is ViewHold1 -> {
                holder.binding.imgEdit.setOnClickListener {
                    listener.operationsItemClicked()
                }
                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
                if(currentOpened==position){
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility=View.GONE
                    holder.binding.itemCollapse.visibility=View.VISIBLE
                    holder.binding.imgEdit.visibility=View.VISIBLE
                }
                else {
                    holder.binding.collapsingLayout.tag=false
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                    holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    holder.binding.itemLine.visibility=View.VISIBLE
                    holder.binding.itemCollapse.visibility=View.GONE
                    holder.binding.imgEdit.visibility=View.GONE
                }
                holder.binding.itemTitle.text = list[position]

                if (data!=null && opcodata?.Opcoinfo?.isNotEmpty()!!) {
                    holder.binding.OpcoName.text=data?.OpcoName
                    holder.binding.opcoSiteId.text=data?.OpcoSiteID
                    holder.binding.opcoSiteName.text=data?.OpcoSiteName
                    holder.binding.opcoSiteStatus.text=data?.Opcositestatus
                    holder.binding.opcoSiteType.text=data?.Opcositetype
                    holder.binding.networkType.text=data?.Operatornetworktype
                    holder.binding.rfiAcceptenceDate.text=data?.rfiAcceptanceDate
                    holder.binding.rfrDate.text=data?.rfrDate
                    holder.binding.OPCOSignOffDate.text=data?.Opcosignoffdate
                    holder.binding.CommittedNWA.text=data?.committedNWA
                    holder.binding.AlarmExtension.text=data?.Alarmsextension
                    holder.binding.RFTechnology.text=data?.Rftechnology
                    holder.binding.TelecomEquipmentType.text=data?.Telecomequipmenttype
                    holder.binding.RRUCount.text=data?.Rrucount
                    holder.binding.SectorCount.text=data?.Sectorcount
                    holder.binding.RackCount.text=data?.Rackcount
                    holder.binding.AntennaCount.text=data?.Antenacount
                    holder.binding.AntennaSlotUsed.text=data?.Antenaslotused
                }
            }
            is ViewHold2 -> {
                holder.binding.imgEdit.setOnClickListener {
                    listener.operationsItemClicked()
                }
                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
                if(currentOpened==position){
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility=View.GONE
                    holder.binding.itemCollapse.visibility=View.VISIBLE
                    holder.binding.imgEdit.visibility=View.VISIBLE
                }
                else {
                    holder.binding.collapsingLayout.tag=false
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                    holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    holder.binding.itemLine.visibility=View.VISIBLE
                    holder.binding.itemCollapse.visibility=View.GONE
                    holder.binding.imgEdit.visibility=View.GONE
                }
                holder.binding.itemTitleStr.text = list[position]

                if (data!=null && opcodata?.Opcoinfo?.isNotEmpty()!!) {
                    holder.binding.InstallationVendor.text=data?.InstallationVendor
                    holder.binding.MaintenanceVendor.text=data?.MaintenanceVendor
                    holder.binding.BackhaulTechnology.text=data?.Backhaultechnology
                    holder.binding.SiteInChargeName.text=data?.Siteinchargename
                    holder.binding.SiteInChargeEmailID.text=data?.Siteinchargeemail
                    holder.binding.SiteInChargeNumber.text=data?.Siteinchargenumber
                    holder.binding.OperatorMaintenanceLocation.text=data?.Operatormaintenancelocation
                }
            }
            is ViewHold3 -> {
                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
                if(currentOpened==position){
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility=View.GONE
                    holder.binding.itemCollapse.visibility=View.VISIBLE
                }
                else {
                    holder.binding.collapsingLayout.tag=false
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                    holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    holder.binding.itemLine.visibility=View.VISIBLE
                    holder.binding.itemCollapse.visibility=View.GONE
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

    interface OpcoInfoLisListener {
        fun attachmentItemClicked()
        fun operationsItemClicked()
        fun opcoSiteInfoItemClicked()
    }
}