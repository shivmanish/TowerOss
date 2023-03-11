package com.smarthub.baseapplication.ui.fragments.opcoTenancy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.NewOpcoInfoData
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.OpcoInfoOperationsTeam
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.OpcoInfoSiteDetails
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.SiteAcqAgreement
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.Opcoinfo
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class OpcoSiteInfoFragAdapter(var listener: OpcoInfoLisListener, var opcodata: ArrayList<NewOpcoInfoData>?) : RecyclerView.Adapter<OpcoSiteInfoFragAdapter.ViewHold>() {
    var list : ArrayList<String> = ArrayList()
    var currentOpened = -1
    var type1 = "Site Details"
    var type2 = "Operations Team"
    var type3 = "Attachments"
    private var OpcoInfoData : NewOpcoInfoData? = null

    fun setData(data: NewOpcoInfoData?) {
        this.OpcoInfoData=data!!
        notifyDataSetChanged()
    }

    fun updateOpcoInfoData(updateddata:Opcoinfo){
//        opcodata?.Opcoinfo?.set(0, updateddata)
//        this.data=updateddata
//        notifyDataSetChanged()
    }
    init {
        list.add("Site Details")
        list.add("Operations Team")
        list.add("Attachments")
        try {
            OpcoInfoData=opcodata?.get(0)
        }catch (e:Exception){
            AppLogger.log("error in opci=o info adapter : ${e.localizedMessage}")
        }
    }
    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)
    override fun getItemViewType(position: Int): Int {
        if ( list[position]==type1)
            return 1
        else if ( list[position]==type2)
            return 2
        else if (list[position]==type3)
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

        init {

        }
        var adapter =  ImageAttachmentAdapter(object : ImageAttachmentAdapter.ItemClickListener{
            override fun itemClicked() {
                listener.attachmentItemClicked()
            }
        })

        init {
//            binding.collapsingLayout.tag = false
//            if ((binding.collapsingLayout.tag as Boolean)) {
//                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
//                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
//            } else {
//                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
//                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
//            }

            val recyclerListener = itemView.findViewById<RecyclerView>(R.id.list_item)
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
//                    listener.opcoSiteInfoItemClicked(data!!)
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

                if (OpcoInfoData!=null && OpcoInfoData?.SRInfo01?.isNotEmpty()==true) {
                    try {
                        val data : OpcoInfoSiteDetails ? = OpcoInfoData?.SRInfo01?.get(0)
                        holder.binding.opcoSiteId.text=data?.OpcoSiteId
                        holder.binding.opcoSiteName.text=data?.OpcoSiteName
                        holder.binding.rfiAcceptenceDate.text=data?.RFIAcceptanceDate?.substring(0,10)?.let { Utils.getFormatedDate(it,"dd-MMM-yyyy") }
                        holder.binding.rfrDate.text=data?.RFRDate?.substring(0,10)?.let { Utils.getFormatedDate(it,"dd-MMM-yyyy") }
                        holder.binding.OPCOSignOffDate.text= data?.OpcoSignOffDate?.substring(0,10)?.let { Utils.getFormatedDate(it,"dd-MMM-yyyy") }
                        holder.binding.CommittedNWA.text=data?.CommittedNWA.toString()
                        holder.binding.OpcoName.text=data?.OpcoName
                        holder.binding.srNumber.text=data?.ServiceRequestNumber
                        holder.binding.srType.text=data?.SRType.toString()
                        holder.binding.rackUSpaceUsed.text=data?.RackUSpaceUsed.toString()
                        holder.binding.AntennaSpaceUsed.text=data?.AntennaSpaceUsed.toString()
                        holder.binding.opcoSiteSapId.text=data?.OpcoSiteSAPId

                        AppPreferences.getInstance().setDropDown(holder.binding.opcoSiteStatus,DropDowns.Opcositestatus.name,data?.OpcoSiteStatus.toString())
                        AppPreferences.getInstance().setDropDown(holder.binding.opcoSiteType,DropDowns.Opcositetype.name,data?.Opcositetype?.get(0).toString())
                        AppPreferences.getInstance().setDropDown(holder.binding.AlarmExtension,DropDowns.Alarmsextension.name,data?.Alarmsextension.toString())
                        AppPreferences.getInstance().setDropDown(holder.binding.Technology,DropDowns.Rftechnology.name,data?.Technology?.get(0).toString())
                        AppPreferences.getInstance().setDropDown(holder.binding.opcoNetworkType,DropDowns.OpcoNetworkType.name,data?.OpcoNetworkType?.get(0).toString())

                    } catch (e:Exception){
                        AppLogger.log("erro in opco info opration team data: ${e.localizedMessage}")
                    }

                }
            }
            is ViewHold2 -> {
                holder.binding.imgEdit.setOnClickListener {
//                    listener.operationsItemClicked(data!!)
                }
                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
                if(currentOpened==position){
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up_faq)
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

                if (OpcoInfoData!=null && OpcoInfoData?.OperationsTeam?.isNotEmpty()==true) {
                    val data : OpcoInfoOperationsTeam ? = OpcoInfoData?.OperationsTeam?.get(0)
                    holder.binding.SiteEngineerName.text=data?.SiteEngineerName
                    holder.binding.SiteEngineerEmailID.text=data?.EmailId
                    holder.binding.SiteEngineerNumber.text=data?.ContactNumber
                    holder.binding.InstallationVendor.text=data?.InstallationVendor
                    holder.binding.MaintenanceVendor.text=data?.MaintenanceVendor

                }
            }
            is ViewHold3 -> {
                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
//                if(currentOpened==position){
//                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility=View.GONE
                    holder.binding.imgDropdown.visibility=View.GONE
                    holder.binding.itemCollapse.visibility=View.VISIBLE
//                }
//                else {
//                    holder.binding.collapsingLayout.tag=false
//                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
//                    holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
//                    holder.binding.itemLine.visibility=View.VISIBLE
//                    holder.binding.itemCollapse.visibility=View.GONE
//                }
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
        fun operationsItemClicked(data:Opcoinfo)
        fun opcoSiteInfoItemClicked(data:Opcoinfo)
    }
}