package com.smarthub.baseapplication.ui.fragments.task.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.Opcoinfo
import com.smarthub.baseapplication.network.pojo.site_info.BasicInfoModelDropDown
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.utils.AppLogger

class TaskAdapter(var context : Context, var listener: TaskLisListener) : RecyclerView.Adapter<TaskAdapter.ViewHold>() {
    var list : ArrayList<String> = ArrayList()
    var type1 = "OPCO/Site Info"
    var type2 = "Operations Team"
    var type3 = "Attachments"

    private var data : BasicInfoModelDropDown?=null
    private var opcoInfoData: Opcoinfo?=null

    fun setData(data : BasicInfoModelDropDown){
        this.data = data
        notifyDataSetChanged()
    }
    fun setValueData(data: Opcoinfo?){
        this.opcoInfoData = data
        notifyDataSetChanged()
    }
    init {
        list.add("OPCO/Site Info")
        list.add("Operations Team")
        list.add("Attachments")
    }
    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)
    override fun getItemViewType(position: Int): Int {
        if (list[position] is String && list[position] == type1)
            return 1
        else if (list[position] is String && list[position] == type2)
            return 2
        else if (list[position] is String && list[position] == type3)
            return 3
        return 0
    }
    class ViewHold1(itemView: View) : ViewHold(itemView) {
        var binding : OpcoSiteInfoItemBinding = OpcoSiteInfoItemBinding.bind(itemView)

        init {
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.down_arrow)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }


        }
    }
    class ViewHold2(itemView: View) : ViewHold(itemView) {
        var binding : OpcoOperationsTeamItemBinding = OpcoOperationsTeamItemBinding.bind(itemView)

        init {
            binding.itemTitleStr.tag = false
            if ((binding.itemTitleStr.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.down_arrow)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }


        }
    }


    class ViewHold3(itemView: View) : ViewHold(itemView,) {
        var binding : AttachmentListItemBinding = AttachmentListItemBinding.bind(itemView)
        var adapter =  ImageAttachmentAdapter(object : ImageAttachmentAdapter.ItemClickListener{
            override fun itemClicked() {
              //  listener.attachmentItemClicked()
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
                view = LayoutInflater.from(parent.context).inflate(R.layout.attachment_list_item, parent, false)
                return ViewHold3(view)
            }

        }
        return ViewHold(view)
    }
    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        when (holder) {

            is ViewHold1 -> {
                holder.binding.imgEdit.setOnClickListener {
                    listener.detailsItemClicked()
                }
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                }
                else {
                    holder.binding.itemTitle.tag = false
                    holder.binding.imgDropdown.setImageResource(R.drawable.down_arrow)
                    holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    holder.binding.itemLine.visibility = View.VISIBLE
                    holder.binding.itemCollapse.visibility = View.GONE
                }
                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
                holder.binding.itemTitle.text = list[position]
                    try {
                        holder.binding.OpcoName.text=opcoInfoData?.OpcoName
                        holder.binding.opcoSiteId.text=opcoInfoData?.OpcoSiteID
                        holder.binding.opcoSiteName.text=opcoInfoData?.OpcoSiteName
                        holder.binding.opcoSiteStatus.text=opcoInfoData?.Opcositestatus
                        holder.binding.opcoSiteType.text=opcoInfoData?.Opcositetype
                        holder.binding.networkType.text=opcoInfoData?.Operatornetworktype
                        holder.binding.rfiAcceptenceDate.text=opcoInfoData?.rfiAcceptanceDate
                        holder.binding.rfrDate.text=opcoInfoData?.rfrDate
                        holder.binding.OPCOSignOffDate.text=opcoInfoData?.Opcosignoffdate
                        holder.binding.CommittedNWA.text=opcoInfoData?.committedNWA
                        holder.binding.AlarmExtension.text=opcoInfoData?.Alarmsextension
                        holder.binding.RFTechnology.text=opcoInfoData?.Rftechnology
                        holder.binding.TelecomEquipmentType.text=opcoInfoData?.Telecomequipmenttype
                        holder.binding.RRUCount.text=opcoInfoData?.Rrucount
                        holder.binding.SectorCount.text=opcoInfoData?.Sectorcount
                        holder.binding.RackCount.text=opcoInfoData?.Rackcount
                        holder.binding.AntennaCount.text=opcoInfoData?.Antenacount
                        holder.binding.AntennaSlotUsed.text=opcoInfoData?.Antenaslotused
                    }catch (e:Exception){
                        Toast.makeText(context, "task tab adapter error", Toast.LENGTH_SHORT).show()
                        AppLogger.log("task tab adapter error ${e.localizedMessage}")
                    }


            }
            is ViewHold2 -> {
                holder.binding.imgEdit.setOnClickListener {
                    listener.requestinfoClicked()
                }
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                    holder.binding.imgEdit.visibility = View.VISIBLE
                }
                else {
                    holder.binding.titleLayout.tag = false
                    holder.binding.imgDropdown.setImageResource(R.drawable.down_arrow)
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
                    holder.binding.InstallationVendor.text=opcoInfoData?.InstallationVendor
                    holder.binding.MaintenanceVendor.text=opcoInfoData?.MaintenanceVendor
                    holder.binding.BackhaulTechnology.text=opcoInfoData?.Backhaultechnology
                    holder.binding.SiteInChargeName.text=opcoInfoData?.Siteinchargename
                    holder.binding.SiteInChargeEmailID.text=opcoInfoData?.Siteinchargeemail
                    holder.binding.SiteInChargeNumber.text=opcoInfoData?.Siteinchargenumber
                    holder.binding.OperatorMaintenanceLocation.text=opcoInfoData?.Operatormaintenancelocation
                }catch (e:Exception){
                    Toast.makeText(context, "task tab adapter error", Toast.LENGTH_SHORT).show()
                    AppLogger.log("task tab adapter error ${e.localizedMessage}")
                }

            }
            is ViewHold3 -> {
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                }
                else {
                    holder.binding.itemTitle.tag = false
                    holder.binding.imgDropdown.setImageResource(R.drawable.down_arrow)
                    holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    holder.binding.itemLine.visibility = View.VISIBLE
                    holder.binding.itemCollapse.visibility = View.GONE
                }
                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
                holder.binding.itemTitle.text = list[position]

                if (data!=null) {

                }
            }

        }
    }
    var currentOpened = -1
    fun updateList(position: Int){
        currentOpened = if(currentOpened == position) -1 else position
        notifyDataSetChanged()
        if (this.recyclerView!=null)
            this.recyclerView?.scrollToPosition(position)
    }
    var recyclerView: RecyclerView?=null

    override fun getItemCount(): Int {
        return list.size
    }
    interface TaskLisListener {
        fun attachmentItemClicked()
        fun detailsItemClicked()
        fun requestinfoClicked()
        fun operationInfoDetailsItemClicked()
        fun geoConditionsDetailsItemClicked()
        fun siteAccessDetailsItemClicked()
        fun EditInstallationAcceptence()
        fun EditTowerItem()
        fun editPoClicked(position:Int)
        fun viewPoClicked(position:Int)
        fun editConsumableClicked(position:Int)
        fun viewConsumableClicked(position:Int)
        fun editOffsetClicked(position:Int)
        fun viewOffsetClicked(position:Int)
    }
}