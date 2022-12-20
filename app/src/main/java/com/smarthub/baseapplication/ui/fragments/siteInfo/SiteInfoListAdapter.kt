package com.smarthub.baseapplication.ui.fragments.siteInfo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.model.siteInfo.*
import com.smarthub.baseapplication.network.pojo.site_info.*

class SiteInfoListAdapter(var context: Context,var listener: SiteInfoLisListener,var basicinfodata:BasicInfoModelItem) : RecyclerView.Adapter<SiteInfoListAdapter.ViewHold>() {

    var list : ArrayList<String> = ArrayList()
    var currentOpened = -1
    var type1 = "Basic Details"
    var type2 = "Operational Info"
    var type3 = "Geo Condition"
    var type4 = "Safety / Access"
    var type5 = "OPCO Contact Details"
    private var data : BasicInfoModelDropDown?=null
    private var operationalInfoDropdown : OperationalInfoModel?=null
    private var geoConditionDropdown : GeoConditionModel?=null
    private var safetyAndAccessDropdown : SafetyAndAccessModel?=null
//    private var fieldData : SiteInfoModel?=null
//    private var basicinfodata:BasicInfoModelItem? = null
    private var dropdowndata: SiteInfoDropDownData? = null

    fun setData(datadrop : SiteInfoDropDownData){
        this.dropdowndata = datadrop
        data = datadrop.basicInfoModel
        operationalInfoDropdown = datadrop.operationalInfo
        geoConditionDropdown = datadrop.geoCondition
        safetyAndAccessDropdown = datadrop.safetyAndAccess
        notifyDataSetChanged()
    }

    fun setValueData(data : SiteInfoModel){
//        this.fieldData = data
//        notifyDataSetChanged()
    }

    init {
        list.add("Basic Details")
        list.add("Operational Info")
        list.add("Geo Condition")
        list.add("Safety / Access")

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
        return 0
    }

    class ViewHold1(itemView: View) : ViewHold(itemView) {
        var binding : BasicDetailItemViewBinding = BasicDetailItemViewBinding.bind(itemView)
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
        var binding : OperationInfoViewBinding = OperationInfoViewBinding.bind(itemView)

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
        var binding : GeoConditionListItemBinding = GeoConditionListItemBinding.bind(itemView)

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
        var binding : SafatyAccessListItemBinding = SafatyAccessListItemBinding.bind(itemView)
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

    class ViewHold5(itemView: View, listener: SiteInfoLisListener) : ViewHold(itemView) {
        var binding : CommercialListItem5Binding = CommercialListItem5Binding.bind(itemView)
        init {
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_arrow_up,0)
            } else {
                binding.itemTitle?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.down_arrow,0)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_empty,parent,false)
        when (viewType) {
            1 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.basic_detail_item_view, parent, false)
                return ViewHold1(view)
            }
            2 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.operation_info_view, parent, false)
                return ViewHold2(view)
            }
            3 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.geo_condition_list_item, parent, false)
                return ViewHold3(view)
            }
            4 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.safaty_access_list_item, parent, false)
                return ViewHold4(view)
            }
            5 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.commercial_list_item5, parent, false)
                return ViewHold5(view,listener)
            }

        }
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        when (holder) {
            is ViewHold1 -> {
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                    holder.binding.iconLayout.visibility = View.VISIBLE
                }
                else {
                    holder.binding.itemTitle.tag = false
                    holder.binding.imgDropdown.setImageResource(R.drawable.down_arrow)
                    holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    holder.binding.itemLine.visibility = View.VISIBLE
                    holder.binding.itemCollapse.visibility = View.GONE
                    holder.binding.iconLayout.visibility = View.GONE
                }
                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
                holder.binding.imgEdit.setOnClickListener {
                    listener.detailsItemClicked(basicinfodata.Basicinfo[0],basicinfodata.id.toString())
                }
                holder.binding.itemTitle.text = list[position]
                if(basicinfodata.Basicinfo.isNotEmpty()){
                    val siteBasicinfo:SiteBasicinfo = basicinfodata.Basicinfo[0]
                    holder.binding.txSiteName.text = siteBasicinfo.siteName
                    holder.binding.txSiteID.text = siteBasicinfo.siteID
                    holder.binding.siteStatus.text = siteBasicinfo.Sitestatus
                    holder.binding.siteCategory.text = siteBasicinfo.Sitecategory
                    holder.binding.siteType.text = siteBasicinfo.Sitetype
                    holder.binding.txBuildingType.text = siteBasicinfo.Buildingtype
                    holder.binding.txtLocationZone.text = siteBasicinfo.Locationzone
                    holder.binding.txtMaintenanceZone.text = siteBasicinfo.MaintenancePoint
                    holder.binding.txtProjectName.text = siteBasicinfo.Projectname
                    holder.binding.txtSiteInChargeName.text = siteBasicinfo.siteInChargeName
                    holder.binding.txtSiteInChargeNumber.text = siteBasicinfo.siteInChargeNumber
                    holder.binding.txtRentEscalation.text = ""
                    holder.binding.address.text = siteBasicinfo.siteaddress


                }
            }
            is ViewHold2 -> {
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                    holder.binding.iconLayout.visibility = View.VISIBLE
                }
                else {
                    holder.binding.itemTitle.tag = false
                    holder.binding.imgDropdown.setImageResource(R.drawable.down_arrow)
                    holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    holder.binding.itemLine.visibility = View.VISIBLE
                    holder.binding.itemCollapse.visibility = View.GONE
                    holder.binding.iconLayout.visibility = View.GONE
                }
                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
                holder.binding.imgEdit.setOnClickListener {
                    listener.detailsItemClicked(basicinfodata.Basicinfo[0],basicinfodata?.id.toString())
                }
                holder.binding.itemTitle.text = list[position]

                if(basicinfodata.OperationalInfo.isNotEmpty()){
                    val operationalInfo: OperationalInfo = basicinfodata.OperationalInfo.get(0)
                    holder.binding.txtRFCDate.text = operationalInfo.RFCDate
                    holder.binding.txtRFIDate.text = operationalInfo.RFIDate
                    holder.binding.txtRFSDate.text = operationalInfo.RFSDate
                    holder.binding.siteBillingStatus.text = operationalInfo.Sitebillingstatus
                    holder.binding.costCenter.text = operationalInfo.Costcentre
                    holder.binding.operatorSharing.text = operationalInfo.Sharingfeasibility
                    holder.binding.powerSource.text = operationalInfo.Powersource
                    holder.binding.designDcLoad.text = operationalInfo.DesignedDcLoad
                    holder.binding.installedDcLoad.text = operationalInfo.InstalledDcLoad
                    holder.binding.operationTemp.text = operationalInfo.OperatingTemp
                    holder.binding.townCategorySpinner.text = operationalInfo.Towncategory
                    holder.binding.hubCitySpinner.text = operationalInfo.Hubsite
                    holder.binding.ldcaSpinner.text = operationalInfo.Ldca
                    holder.binding.scdaSpinner.text = operationalInfo.Scda
                    holder.binding.dismanting.text = operationalInfo.DismantlinglDate
                }
            }
            is ViewHold3 -> {
                holder.binding.imgEdit.setOnClickListener {
                    listener.geoConditionsDetailsItemClicked(basicinfodata.GeoCondition)
                }
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                    holder.binding.iconLayout.visibility = View.VISIBLE
                }
                else {
                    holder.binding.itemTitle.tag = false
                    holder.binding.imgDropdown.setImageResource(R.drawable.down_arrow)
                    holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    holder.binding.itemLine.visibility = View.VISIBLE
                    holder.binding.itemCollapse.visibility = View.GONE
                    holder.binding.iconLayout.visibility = View.GONE
                }
                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
                holder.binding.itemTitle.text = list[position]
                if(basicinfodata.GeoCondition.isNotEmpty()){
                    val geoCondition: GeoCondition = basicinfodata.GeoCondition[0]
                    holder.binding.potentioalThreatSpinner.text = geoCondition.Potentialthreat
                    holder.binding.textAltitude.text = geoCondition.Altitude
                    holder.binding.windZoneSpinner.text = geoCondition.Windzone
                    holder.binding.seismecZoneSpinner.text = geoCondition.Seismiczone
                    holder.binding.floodZoneSpinner.text = geoCondition.Floodzone
                    holder.binding.textTempZone.text = geoCondition.TempratureZone
                    holder.binding.terrainTypeSpinner.text = geoCondition.Terraintype
                }

            }
            is ViewHold4 -> {
                holder.binding.imgEdit.setOnClickListener {
                    listener.siteAccessDetailsItemClicked(basicinfodata.SafetyAndAccess)
                }
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                    holder.binding.iconLayout.visibility = View.VISIBLE
                }
                else {
                    holder.binding.itemTitle.tag = false
                    holder.binding.imgDropdown.setImageResource(R.drawable.down_arrow)
                    holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    holder.binding.itemLine.visibility = View.VISIBLE
                    holder.binding.itemCollapse.visibility = View.GONE
                    holder.binding.iconLayout.visibility = View.GONE
                }
                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
                holder.binding.itemTitle.text = list[position]
                if(basicinfodata.SafetyAndAccess.isNotEmpty()){
                    val saftyAcess: SafetyAndAcces = basicinfodata.SafetyAndAccess.get(0)
                    holder.binding.physicalSecurity.text = saftyAcess.Physicalsecurity
                    holder.binding.textGate.text = saftyAcess.GateAndFence
                    holder.binding.videoMonitoringSpinner.text = saftyAcess.Videomonitoring
                    holder.binding.siteAccessAreaSpinner.text = saftyAcess.SiteAccessArea
                    holder.binding.dangerSignageSpinner.text = saftyAcess.DangerSignage
                    holder.binding.textCautionSignage.text = saftyAcess.CautionSignage
                    holder.binding.siteAccess.text = saftyAcess.Siteaccess

                    holder.binding.textSiteAccesseethodology.text = saftyAcess.Siteaccessmethodology
                    holder.binding.textPoliceNumber.text = saftyAcess.NearByPoliceStationNumber
                    holder.binding.textPoliceStation.text = saftyAcess.NearByPoliceStation
                    holder.binding.textFireStation.text = saftyAcess.NearByFireStation
                    holder.binding.textFireNumber.text = saftyAcess.NearByFireStationNumber
                }
            }
            is ViewHold5 -> {
                if (currentOpened == position) {
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                    holder.binding.itemTitle.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_arrow_up,0)
                }
                else {
                    holder.binding.itemTitle.tag = false
                    holder.binding.itemLine.visibility = View.VISIBLE
                    holder.binding.itemCollapse.visibility = View.GONE
                    holder.binding.itemTitle.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.down_arrow,0)
                }
                holder.binding.itemTitle.setOnClickListener {
                    updateList(position)
                }
                holder.binding.itemTitle.text = list[position]
            }
        }
    }

    fun updateList(position: Int){
        currentOpened = if(currentOpened == position) -1 else position
        notifyDataSetChanged()
        if (this.recyclerView!=null)
            this.recyclerView?.scrollToPosition(position)
    }

    var recyclerView: RecyclerView?=null
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView
        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface SiteInfoLisListener {
        fun attachmentItemClicked()
        fun detailsItemClicked(siteBasicinfo: SiteBasicinfo,id : String)
        fun operationInfoDetailsItemClicked(operationalInfo: List<OperationalInfo>)
        fun geoConditionsDetailsItemClicked(geoCondition: List<GeoCondition>)
        fun siteAccessDetailsItemClicked(safetyAndAccess: List<SafetyAndAcces>)
    }
}