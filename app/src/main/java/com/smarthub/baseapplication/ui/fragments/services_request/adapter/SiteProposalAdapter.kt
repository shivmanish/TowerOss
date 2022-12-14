package com.smarthub.baseapplication.ui.fragments.services_request.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.model.siteInfo.*
import com.smarthub.baseapplication.network.pojo.site_info.BasicInfoModelDropDown

class SiteProposalAdapter(var listener: SiteProposalListener) : RecyclerView.Adapter<SiteProposalAdapter.ViewHold>() {
    var list : ArrayList<String> = ArrayList()
    var type1 = "Commercial Details"
    var type2 = "Generate Site Proposal"
    var type3 = "SP Submission to OPCO"
    var type4 = "Power & MCB"
    var type5 = "Attachments"
    var type6 = "TSSR Executive Info"
    private var data : BasicInfoModelDropDown?=null
    private var fieldData : SiteInfoModel?=null

    fun setData(data : BasicInfoModelDropDown){
        this.data = data
        notifyDataSetChanged()
    }
    fun setValueData(data : SiteInfoModel){
        this.fieldData = data
        notifyDataSetChanged()
    }
    init {
        list.add("Commercial Details")
        list.add("Generate Site Proposal")
        list.add("SP Submission to OPCO")
     /*   list.add("Equipments")
        list.add("Power & MCB")
        list.add("Attachments")
        list.add("TSSR Executive Info")*/
    }
    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)
    override fun getItemViewType(position: Int): Int {
        if (list[position] is String && list[position]==type1)
            return 1
        else if (list[position] is String && list[position]==type2)
            return 2
        else if (list[position] is String && list[position]==type3)
            return 3
        else if (list[position] is String && list[position]==type4)
            return 4
        else if (list[position] is String && list[position]==type5)
            return 5
        else if (list[position] is String && list[position]==type6)
            return 6
        return 0
    }
    class ViewHold1(itemView: View) : ViewHold(itemView) {
        var binding : CommersalItemViewBinding = CommersalItemViewBinding.bind(itemView)

        init {
            binding.itemTitle.tag = false
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
        var binding : GenerateItemViewBinding = GenerateItemViewBinding.bind(itemView)

        init {
            binding.itemTitle.tag = false
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
    class ViewHold3(itemView: View) : ViewHold(itemView) {
        var binding : SpSubmissionInfoViewBinding = SpSubmissionInfoViewBinding.bind(itemView)

        init {
            binding.itemTitle.tag = false
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.down_arrow)
            }


        }
    }
    class ViewHold4(itemView: View) : ViewHold(itemView) {
        var binding : PowerMsbInfoViewBinding = PowerMsbInfoViewBinding.bind(itemView)
        init {
            binding.itemTitle.tag = false
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.down_arrow)
            }


        }
    }

    class ViewHold5(itemView: View, listener: SiteProposalListener) : ViewHold(itemView) {
        var binding : AttachmentListItemBinding = AttachmentListItemBinding.bind(itemView)
        init {
            binding.itemTitle.tag = true
            if ((binding.itemTitle.tag as Boolean)) {
                binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_arrow_up,0)
            } /*else {
                binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,
                    R.drawable.down_arrow,0)
            }*/
        }
    }

   class ViewHold6(itemView: View, listener: SiteProposalListener) : ViewHold(itemView) {
        var binding : TssrInfoViewBinding = TssrInfoViewBinding.bind(itemView)

       init {
           binding.itemTitle.tag = false
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_empty,parent,false)
        when (viewType) {
            1 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.commersal_item_view, parent, false)
                return ViewHold1(view)
            }
            2 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.generate_item_view, parent, false)
                return ViewHold2(view)
            }
            3 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.sp_submission_info_view, parent, false)
                return ViewHold3(view)
            }
            4 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.power_msb_info_view, parent, false)
                return ViewHold4(view)
            }
            5 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.attachment_list_item, parent, false)
                return ViewHold5(view,listener)
            }   6-> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.tssr_info_view, parent, false)
                return ViewHold6(view,listener)
            }

        }
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        when (holder) {
            is ViewHold1 -> {
                holder.binding.collapsingLayout.setOnClickListener {
                    holder.binding.itemTitle.tag = !(holder.binding.itemTitle.tag as Boolean)
                    if ((holder.binding.itemTitle.tag as Boolean)) {
                        holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                        holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)

                    } else {
                        holder.binding.imgDropdown.setImageResource(R.drawable.down_arrow)
                        holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    }
                    holder.binding.itemLine.visibility = if (holder.binding.itemTitle.tag as Boolean) View.GONE else View.VISIBLE
                    holder.binding.iconLayout.visibility = if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE
                    holder.binding.imgEdit.setOnClickListener {
                        listener.detailsItemClicked()
                    }

                    holder.binding.itemCollapse.visibility = if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE
                    holder.binding.iconLayout.visibility = if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE

                }
                holder.binding.itemTitle.text = list[position]

                if (data!=null) {
                    /*  holder.binding.siteStatusSpinner.setSpinnerData(data?.sitestatus?.data)
                      holder.binding.siteCategorySpinner.setSpinnerData(data?.sitecategory?.data)
                      holder.binding.siteOwnershipSpinner.setSpinnerData(data?.siteownership?.data)
                      holder.binding.siteTypeSpinner.setSpinnerData(data?.sitetype?.data)*/
                }
                if(fieldData!=null && fieldData!!.size>0 && fieldData!!.get(0).Basicinfo!=null && fieldData!!.get(0).Basicinfo.size >0){
                    val basicinfo: Basicinfo = fieldData!!.get(0).Basicinfo.get(0)
      /*              holder.binding.txSiteName.text = basicinfo.siteName
                    holder.binding.txSiteID.text = basicinfo.siteID
                    holder.binding.siteStatus.text = basicinfo.Sitestatus.get(0).name
                    holder.binding.siteCategory.text = basicinfo.Sitecategory.get(0).name
                    holder.binding.siteType.text = basicinfo.Sitetype.get(0).name
                    holder.binding.txBuildingType.text = basicinfo.Buildingtype.get(0).name
                    holder.binding.txtLocationZone.text = basicinfo.Locationzone.get(0).name
                    holder.binding.txtMaintenanceZone.text = basicinfo.MaintenancePoint.get(0).maintenancepoint
                    holder.binding.txtProjectName.text = basicinfo.Projectname.get(0).name
                    holder.binding.txtSiteInChargeName.text = basicinfo.siteInChargeName
                    holder.binding.txtSiteInChargeNumber.text = basicinfo.siteInChargeNumber
                    holder.binding.txtRentEscalation.text = ""
                    holder.binding.address.text = basicinfo.siteaddress*/


                }
            }
            is ViewHold2 -> {
                holder.binding.collapsingLayout.setOnClickListener {
                    holder.binding.itemTitle.tag = !(holder.binding.itemTitle.tag as Boolean)
                    if ((holder.binding.itemTitle.tag as Boolean)) {
                        holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                        holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    } else {
                        holder.binding.imgDropdown.setImageResource(R.drawable.down_arrow)
                        holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    }
                    holder.binding.itemLine.visibility =
                        if (holder.binding.itemTitle.tag as Boolean) View.GONE else View.VISIBLE
                    holder.binding.iconLayout.visibility =
                        if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE
                    holder.binding.imgEdit.setOnClickListener()
                    {
                        listener.operationInfoDetailsItemClicked()
                    }
                    holder.binding.itemCollapse.visibility = if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE
                    holder.binding.iconLayout.visibility = if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE
                }
                holder.binding.itemTitle.text = list[position]

                if(fieldData!=null && fieldData!!.size>0 && fieldData!!.get(0).OperationalInfo!=null && fieldData!!.get(0).OperationalInfo.size >0){
                    val operationalInfo: OperationalInfo = fieldData!!.get(0).OperationalInfo.get(0)
//                    holder.binding.txtRFCDate.text = operationalInfo.RFCDate
//                    holder.binding.txtRFIDate.text = operationalInfo.RFIDate
//                    holder.binding.txtRFSDate.text = operationalInfo.RFSDate
//                    holder.binding.siteBillingStatus.text = operationalInfo.Sitebillingstatus.get(0).name
//                    holder.binding.costCenter.text = operationalInfo.Costcentre.get(0).name
//                    holder.binding.operatorSharing.text = operationalInfo.Sharingfeasibility.get(0).name
//                    holder.binding.powerSource.text = operationalInfo.Powersource
//                    holder.binding.designDcLoad.text = operationalInfo.DesignedDcLoad
//                    holder.binding.installedDcLoad.text = operationalInfo.InstalledDcLoad
//                    holder.binding.operationTemp.text = operationalInfo.OperatingTemp
//                    holder.binding.townCategorySpinner.text = operationalInfo.Towncategory.get(0).name
//                    holder.binding.hubCitySpinner.text = operationalInfo.Hubsite.get(0).name
//                    holder.binding.ldcaSpinner.text = operationalInfo.Ldca.get(0).name
//                    holder.binding.scdaSpinner.text = operationalInfo.Scda.get(0).name
//                    holder.binding.dismanting.text = operationalInfo.DismantlinglDate
                }
            }
            is ViewHold3 -> {
                holder.binding.collapsingLayout.setOnClickListener {
                    holder.binding.itemTitle.tag = !(holder.binding.itemTitle.tag as Boolean)
                    if ((holder.binding.itemTitle.tag as Boolean)) {
                        holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                        holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    } else {
                        holder.binding.imgDropdown.setImageResource(R.drawable.down_arrow)
                        holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    }

                    holder.binding.itemLine.visibility =
                        if (holder.binding.itemTitle.tag as Boolean) View.GONE else View.VISIBLE
                    holder.binding.iconLayout.visibility =
                        if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE

                    holder.binding.imgEdit.setOnClickListener()
                    {
                        listener.geoConditionsDetailsItemClicked()
                    }

                    holder.binding.itemCollapse.visibility = if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE
                    holder.binding.iconLayout.visibility = if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE

                }
                holder.binding.itemTitle.text = list[position]
                if(fieldData!=null && fieldData!!.size>0 && fieldData!!.get(0).GeoCondition!=null && fieldData!!.get(0).GeoCondition.size >0){
                   /* val geoCondition: GeoCondition = fieldData!!.get(0).GeoCondition.get(0)
                    holder.binding.potentioalThreatSpinner.text = geoCondition.Potentialthreat.get(0).name
                    holder.binding.textAltitude.text = geoCondition.Altitude
                    holder.binding.windZoneSpinner.text = geoCondition.Windzone.get(0).name
                    holder.binding.seismecZoneSpinner.text = geoCondition.Seismiczone.get(0).name
                    holder.binding.floodZoneSpinner.text = geoCondition.Floodzone.get(0).name
                    holder.binding.textTempZone.text = geoCondition.TempratureZone
                    holder.binding.terrainTypeSpinner.text = geoCondition.Terraintype.get(0).name*/
                }

            }
            is ViewHold4 -> {
                holder.binding.collapsingLayout.setOnClickListener {
                    holder.binding.itemTitle.tag = !(holder.binding.itemTitle.tag as Boolean)
                    if ((holder.binding.itemTitle.tag as Boolean)) {
                        holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                        holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    } else {
                        holder.binding.imgDropdown.setImageResource(R.drawable.down_arrow)
                        holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    }

                    holder.binding.itemLine.visibility =
                        if (holder.binding.itemTitle.tag as Boolean) View.GONE else View.VISIBLE
                    holder.binding.iconLayout.visibility =
                        if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE

                    holder.binding.imgEdit.setOnClickListener()
                    {
                        listener.siteAccessDetailsItemClicked()
                    }

                    holder.binding.itemCollapse.visibility = if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE
                    holder.binding.iconLayout.visibility = if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE

                }
                holder.binding.itemTitle.text = list[position]
                if(fieldData!=null && fieldData!!.size>0 && fieldData!!.get(0).SafetyAndAccess!=null && fieldData!!.get(0).SafetyAndAccess.size >0){
                    val geoCondition: SafetyAndAcces = fieldData!!.get(0).SafetyAndAccess.get(0)
                  /*  holder.binding.physicalSecurity.text = geoCondition.Physicalsecurity.get(0).name
                    holder.binding.textGate.text = geoCondition.GateAndFence.get(0).name
                    holder.binding.videoMonitoringSpinner.text = geoCondition.Videomonitoring.get(0).name
                    holder.binding.siteAccessAreaSpinner.text = geoCondition.SiteAccessArea.get(0).name
                    holder.binding.dangerSignageSpinner.text = geoCondition.DangerSignage.get(0).name
                    holder.binding.textCautionSignage.text = geoCondition.CautionSignage.get(0).name
                    holder.binding.siteAccess.text = geoCondition.Siteaccess.get(0).name

                    holder.binding.textSiteAccesseethodology.text = geoCondition.Siteaccessmethodology
                    holder.binding.textPoliceNumber.text = geoCondition.NearByPoliceStationNumber
                    holder.binding.textPoliceStation.text = geoCondition.NearByPoliceStation
                    holder.binding.textFireStation.text = geoCondition.NearByFireStation
                    holder.binding.textFireNumber.text = geoCondition.NearByFireStationNumber*/
                }
            }
            is ViewHold5 -> {
                holder.binding.itemTitle.setOnClickListener {
                    holder.binding.itemTitle.tag = !(holder.binding.itemTitle.tag as Boolean)
                    if ((holder.binding.itemTitle.tag as Boolean)) {
                        holder.binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_arrow_up,0)
                    } else {
                        holder.binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,
                            R.drawable.down_arrow,0)
                    }

                    holder.binding.itemLine.visibility =
                        if (holder.binding.itemTitle.tag as Boolean) View.GONE else View.VISIBLE
                    holder.binding.itemCollapse.visibility =
                        if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE
                }
                holder.binding.itemTitle.text = list[position]
            }
            is ViewHold6 -> {
                holder.binding.collapsingLayout.setOnClickListener {
                    holder.binding.itemTitle.tag = !(holder.binding.itemTitle.tag as Boolean)
                    if ((holder.binding.itemTitle.tag as Boolean)) {
                        holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                        holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)

                    } else {
                        holder.binding.imgDropdown.setImageResource(R.drawable.down_arrow)
                        holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    }
                    holder.binding.itemLine.visibility = if (holder.binding.itemTitle.tag as Boolean) View.GONE else View.VISIBLE
                    holder.binding.iconLayout.visibility = if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE
                    holder.binding.imgEdit.setOnClickListener {
                        listener.requestinfoClicked()
                    }

                    holder.binding.itemCollapse.visibility = if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE
                    holder.binding.iconLayout.visibility = if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE

                }
                holder.binding.itemTitle.text = list[position]
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface SiteProposalListener {
        fun attachmentItemClicked()
        fun detailsItemClicked()
        fun requestinfoClicked()
        fun operationInfoDetailsItemClicked()
        fun geoConditionsDetailsItemClicked()
        fun siteAccessDetailsItemClicked()
    }
}