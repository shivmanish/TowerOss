package com.smarthub.baseapplication.ui.fragments.siteInfo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteInfo.*
import com.smarthub.baseapplication.model.siteInfo.siteInfoData.*
import com.smarthub.baseapplication.network.pojo.site_info.*
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns

class SiteInfoListAdapter(var context: Context,var listener: SiteInfoLisListener,var basicinfodata:SiteInfoAllData) : RecyclerView.Adapter<SiteInfoListAdapter.ViewHold>() {

    var list : ArrayList<String> = ArrayList()
    var currentOpened = -1
    var type1 = "Basic Details"
    var type2 = "Operational Info"
    var type3 = "Geo Condition"
    var type4 = "Safety / Access"
    var type5 = "OPCO Contact Details"
    private var basicInfoModelDropDown : BasicInfoModelDropDown?=null
    private var operationalInfoDropdown : OperationalInfoModel?=null
    private var geoConditionDropdown : GeoConditionModel?=null
    private var safetyAndAccessDropdown : SafetyAndAccessModel?=null
    private var dropdowndata: SiteInfoDropDownData? = null

    fun setData(datadrop : SiteInfoDropDownData){
        this.dropdowndata = datadrop
        basicInfoModelDropDown = datadrop.basicInfoModel
        operationalInfoDropdown = datadrop.operationalInfo
        geoConditionDropdown = datadrop.geoCondition
        safetyAndAccessDropdown = datadrop.safetyAndAccess
        notifyDataSetChanged()
    }

    init {
        list.add("Basic Details")
        list.add("Operational Info")
        list.add("Geo Condition")
        list.add("Safety / Access")

    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun getItemViewType(position: Int): Int {
        if (list[position]==type1)
            return 1
        else if (list[position]==type2)
            return 2
        else if (list[position]==type3)
            return 3
        else if (list[position]==type4)
            return 4
        else if (list[position]==type5)
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

    class ViewHold5(itemView: View) : ViewHold(itemView) {
        var binding : CommercialListItem5Binding = CommercialListItem5Binding.bind(itemView)
        init {
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.itemTitle.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_arrow_up,0)
            } else {
                binding.itemTitle.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.down_arrow,0)
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
                return ViewHold5(view)
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
                    val siteBasicinfo: SiteBasicinfo = basicinfodata.Basicinfo[0]
                    holder.binding.txSiteName.text = siteBasicinfo.siteName
                    holder.binding.txSiteID.text = siteBasicinfo.siteID

                    AppPreferences.getInstance().setDropDown(holder.binding.siteStatus,DropDowns.Sitestatus.name,siteBasicinfo.Sitestatus)
                    AppPreferences.getInstance().setDropDown(holder.binding.siteCategory,DropDowns.Sitecategory.name,siteBasicinfo.Sitecategory)
                    AppPreferences.getInstance().setDropDown(holder.binding.siteType,DropDowns.Sitetype.name,siteBasicinfo.Sitetype)
                    AppPreferences.getInstance().setDropDown(holder.binding.txBuildingType,DropDowns.Buildingtype.name,siteBasicinfo.Buildingtype)
                    AppPreferences.getInstance().setDropDown(holder.binding.txtLocationZone,DropDowns.Locationzone.name,siteBasicinfo.Locationzone)
                    AppPreferences.getInstance().setDropDown(holder.binding.txtProjectName,DropDowns.Projectname.name,siteBasicinfo.Projectname)
                    holder.binding.txtMaintenanceZone.text = siteBasicinfo.MaintenancePoint
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
                    if (basicinfodata.OperationalInfo.isNotEmpty())
                        listener.operationInfoDetailsItemClicked(basicinfodata.OperationalInfo[0], basicinfodata.id.toString())
                    else Toast.makeText(context,"OperationalInfo not found",Toast.LENGTH_SHORT).show()
                }
                holder.binding.itemTitle.text = list[position]

                if(basicinfodata.OperationalInfo.isNotEmpty()){
                    val operationalInfo: OperationalInfo = basicinfodata.OperationalInfo[0]
                    holder.binding.txtRFCDate.text = operationalInfo.RFCDate
                    holder.binding.txtRFIDate.text = operationalInfo.RFIDate
                    holder.binding.txtRFSDate.text = operationalInfo.RFSDate
                    holder.binding.powerSource.text = operationalInfo.Powersource
                    holder.binding.designDcLoad.text = operationalInfo.DesignedDcLoad
                    holder.binding.installedDcLoad.text = operationalInfo.InstalledDcLoad
                    holder.binding.operationTemp.text = operationalInfo.OperatingTemp
                    holder.binding.dismanting.text = operationalInfo.DismantlinglDate

                    AppPreferences.getInstance().setDropDown(holder.binding.costCenter,DropDowns.Costcentre.name,operationalInfo.Costcentre)
                    AppPreferences.getInstance().setDropDown(holder.binding.siteBillingStatus,DropDowns.Sitebillingstatus.name,operationalInfo.Sitebillingstatus)
                    AppPreferences.getInstance().setDropDown(holder.binding.operatorSharing,DropDowns.Sharingfeasibility.name,operationalInfo.Sharingfeasibility)
                    AppPreferences.getInstance().setDropDown(holder.binding.townCategorySpinner,DropDowns.Towncategory.name,operationalInfo.Towncategory)
                    AppPreferences.getInstance().setDropDown(holder.binding.hubCitySpinner,DropDowns.Hubsite.name,operationalInfo.Hubsite)
                    AppPreferences.getInstance().setDropDown(holder.binding.ldcaSpinner,DropDowns.Ldca.name,operationalInfo.Ldca)
                    AppPreferences.getInstance().setDropDown(holder.binding.scdaSpinner,DropDowns.Scda.name,operationalInfo.Scda)
                }
            }
            is ViewHold3 -> {
                holder.binding.imgEdit.setOnClickListener {
                    if (basicinfodata.GeoCondition.isNotEmpty())
                        listener.geoConditionsDetailsItemClicked(basicinfodata.GeoCondition[0],basicinfodata.id.toString())
                    else
                        Toast.makeText(context,"Geo Condition ",Toast.LENGTH_SHORT).show()
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
                    holder.binding.textAltitude.text = geoCondition.Altitude
                    holder.binding.textTempZone.text = geoCondition.TempratureZone
                    AppPreferences.getInstance().setDropDown(holder.binding.potentioalThreatSpinner,DropDowns.Potentialthreat.name,geoCondition.Potentialthreat)
                    AppPreferences.getInstance().setDropDown(holder.binding.windZoneSpinner,DropDowns.Windzone.name,geoCondition.Windzone)
                    AppPreferences.getInstance().setDropDown(holder.binding.seismecZoneSpinner,DropDowns.Seismiczone.name,geoCondition.Seismiczone)
                    AppPreferences.getInstance().setDropDown(holder.binding.floodZoneSpinner,DropDowns.Floodzone.name,geoCondition.Floodzone)
                    AppPreferences.getInstance().setDropDown(holder.binding.terrainTypeSpinner,DropDowns.Terraintype.name,geoCondition.Terraintype)
                }

            }
            is ViewHold4 -> {
                holder.binding.imgEdit.setOnClickListener {
                    if (basicinfodata.GeoCondition.isNotEmpty())
                        listener.siteAccessDetailsItemClicked(basicinfodata.SafetyAndAccess[0],basicinfodata.id.toString())
                    else
                        Toast.makeText(context,"Geo Condition ",Toast.LENGTH_SHORT).show()
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
                    AppLogger.log("basic info site data safty not empty: ${basicinfodata.SafetyAndAccess}")
                    try {
                        val saftyAcess: SafetyAndAcces = basicinfodata.SafetyAndAccess[0]
                            holder.binding.textSiteAccesseethodology.text = saftyAcess.Siteaccessmethodology
                            holder.binding.textPoliceNumber.text = saftyAcess.NearByPoliceStationNumber
                            holder.binding.textPoliceStation.text = saftyAcess.NearByPoliceStation
                            holder.binding.textFireStation.text = saftyAcess.NearByFireStation
                            holder.binding.textFireNumber.text = saftyAcess.NearByFireStationNumber
                            AppPreferences.getInstance().setDropDown(holder.binding.physicalSecurity,DropDowns.Physicalsecurity.name,saftyAcess.Physicalsecurity)
                            AppPreferences.getInstance().setDropDown(holder.binding.textGate,DropDowns.GateAndFence.name,saftyAcess.GateAndFence)
                            AppPreferences.getInstance().setDropDown(holder.binding.videoMonitoringSpinner,DropDowns.Videomonitoring.name,saftyAcess.Videomonitoring)
                            AppPreferences.getInstance().setDropDown(holder.binding.siteAccessAreaSpinner,DropDowns.SiteAccessArea.name,saftyAcess.SiteAccessArea)
                            AppPreferences.getInstance().setDropDown(holder.binding.dangerSignageSpinner,DropDowns.DangerSignage.name,saftyAcess.DangerSignage)
                            AppPreferences.getInstance().setDropDown(holder.binding.textCautionSignage,DropDowns.CautionSignage.name,saftyAcess.CautionSignage)
                            AppPreferences.getInstance().setDropDown(holder.binding.siteAccess,DropDowns.Siteaccess.name,saftyAcess.Siteaccess)


                    }catch (e:java.lang.Exception){
                        AppLogger.log("e : ${e.localizedMessage}")
                    }
                }
                else
                    AppLogger.log("basic info site data safty empty: ${basicinfodata.SafetyAndAccess}")

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
        fun detailsItemClicked(siteBasicinfo: SiteBasicinfo, id : String)
        fun operationInfoDetailsItemClicked(operationalInfo: OperationalInfo, id : String)
        fun geoConditionsDetailsItemClicked(geoCondition: GeoCondition, id : String)
        fun siteAccessDetailsItemClicked(safetyAndAccess: SafetyAndAcces, id : String)
    }
}