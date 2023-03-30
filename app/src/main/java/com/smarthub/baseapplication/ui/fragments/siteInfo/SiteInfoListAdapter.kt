package com.smarthub.baseapplication.ui.fragments.siteInfo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newSiteInfoDataModel.*
import com.smarthub.baseapplication.model.siteInfo.siteInfoData.*
import com.smarthub.baseapplication.network.pojo.site_info.*
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class SiteInfoListAdapter(var context: Context,var listener: SiteInfoLisListener) : RecyclerView.Adapter<SiteInfoListAdapter.ViewHold>() {

    var list : ArrayList<String> = ArrayList()
    var currentOpened = -1
    var type1 = "Basic Details"
    var type2 = "Operational Info"
    var type3 = "Geo Condition"
    var type4 = "Safety / Access"
    var type5 = "OPCO Contact Details"
    private var basicinfodata : AllsiteInfoDataModel?=null

    fun setData(data : AllsiteInfoDataModel?){
        if (data!=null){
            basicinfodata=data
            list.clear()
            list.add("Basic Details")
            list.add("Operational Info")
            list.add("Geo Condition")
            list.add("Safety / Access")
            notifyDataSetChanged()
        }
        else{
            list.clear()
            list.add("Loading")
            notifyDataSetChanged()
        }
    }

    fun addLoading(){
        list.clear()
        list.add("Loading")
        notifyDataSetChanged()
    }

    init {
        list.add("Loading")
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
        when (viewType) {
            1 -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.basic_detail_item_view, parent, false)
                return ViewHold1(view)
            }
            2 -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.operation_info_view, parent, false)
                return ViewHold2(view)
            }
            3 -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.geo_condition_list_item, parent, false)
                return ViewHold3(view)
            }
            4 -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.safaty_access_list_item, parent, false)
                return ViewHold4(view)
            }
            5 -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.commercial_list_item5, parent, false)
                return ViewHold5(view)
            }
            else->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.list_loading_bar,parent,false)
                return ViewHold(view)
            }
        }

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
//                    listener.detailsItemClicked(basicinfodata.Basicinfo[0],basicinfodata.id.toString())
                }
                holder.binding.itemTitle.text = list[position]
                try {
                    if(basicinfodata?.Basicinfo?.isNotEmpty()==true && basicinfodata?.Siteaddress?.isNotEmpty()==true){
                        val siteBasicinfo: BasicInfoData = basicinfodata?.Basicinfo!!.get(0)
                        val siteAddress: SiteAddressData = basicinfodata?.Siteaddress!!.get(0)
                        holder.binding.txSiteName.text = siteBasicinfo.siteName
                        holder.binding.txSiteID.text = siteBasicinfo.siteID
                        holder.binding.SiteAlternateName.text=siteBasicinfo.aliasName
                        if (siteBasicinfo.Sitestatus.isNotEmpty())
                            AppPreferences.getInstance().setDropDown(holder.binding.siteStatus,DropDowns.Sitestatus.name,siteBasicinfo.Sitestatus.get(0).toString())
                        if (siteBasicinfo.Sitecategory.isNotEmpty())
                            AppPreferences.getInstance().setDropDown(holder.binding.siteCategory,DropDowns.Sitecategory.name,siteBasicinfo.Sitecategory.get(0).toString())
                        if (siteBasicinfo.Opcositetype?.isNotEmpty() == true)
                            AppPreferences.getInstance().setDropDown(holder.binding.siteType,DropDowns.Sitetype.name,siteBasicinfo.Opcositetype.get(0).toString())
                        if (siteBasicinfo.Buildingtype.isNotEmpty())
                            AppPreferences.getInstance().setDropDown(holder.binding.txBuildingType,DropDowns.Buildingtype.name,siteBasicinfo.Buildingtype.get(0).toString())
                        if (siteBasicinfo.Projectname.isNotEmpty())
                            AppPreferences.getInstance().setDropDown(holder.binding.txtProjectName,DropDowns.Projectname.name,siteBasicinfo.Projectname.get(0).toString())
                        if (siteBasicinfo.Acquisitiontype.isNotEmpty())
                            AppPreferences.getInstance().setDropDown(holder.binding.AcquisitionType,DropDowns.Acquisitiontype.name,siteBasicinfo.Acquisitiontype.get(0).toString())
                        if (siteBasicinfo.MaintenancePoint!=null)
                        holder.binding.MaintenanceGeography.text = siteBasicinfo.MaintenancePoint.maintenancePoint
                        holder.binding.address.text = "${siteAddress.address1}  ${siteAddress.address2}"
                        holder.binding.postalCode.text=siteAddress.pincode
                        holder.binding.siteLatitude.text=siteAddress.locLatitude
                        holder.binding.siteLongitude.text=siteAddress.locLongitude
                    }
                    else
                        AppLogger.log("basic details data or site address data is empty")

                }
                catch (e:Exception){
                    AppLogger.log("error in basic Details Data on SiteInfo")
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
//                    if (basicinfodata.OperationalInfo.isNotEmpty())
//                        listener.operationInfoDetailsItemClicked(basicinfodata.OperationalInfo[0], basicinfodata.id.toString())
//                    else Toast.makeText(context,"OperationalInfo not found",Toast.LENGTH_SHORT).show()
                }
                holder.binding.itemTitle.text = list[position]

                try {
                    if(basicinfodata?.OperationalInfo?.isNotEmpty()==true){
                        val operationalInfo: OprationalInfoData = basicinfodata?.OperationalInfo!![0]
                        holder.binding.txtRFCDate.text = Utils.getFormatedDate( operationalInfo.RFCDate.substring(0,10),"dd-MMM-yyyy")
                        holder.binding.txtRFIDate.text = Utils.getFormatedDate( operationalInfo.RFIDate.substring(0,10),"dd-MMM-yyyy")
                        holder.binding.txtRFSDate.text = Utils.getFormatedDate( operationalInfo.RFSDate.substring(0,10),"dd-MMM-yyyy")
                        holder.binding.maxOperationTemp.text = operationalInfo.OperatingTempMax
                        holder.binding.minOperationTemp.text = operationalInfo.OperatingTempMin
                        holder.binding.FeasibleOPCOSharing.text = operationalInfo.FeasibleOpcoSharing
                        holder.binding.AvailableOPCO.text = operationalInfo.AvailableOpco
                        holder.binding.designDcLoad.text = operationalInfo.DesignedDcLoad
                        holder.binding.installedDcLoad.text = operationalInfo.InstalledDcLoad
                        holder.binding.TowerPoleHeight.text = operationalInfo.TowerorPoleHeight
                        holder.binding.BackhaulNodeCategory.text = operationalInfo.BackhaulNodeCategory.toString()
                        holder.binding.SiteAgreementDate.text = Utils.getFormatedDate(operationalInfo.SiteAgreementDate.substring(0,10),"dd-MMM-yyyy")
                        holder.binding.dismanting.text = Utils.getFormatedDate(operationalInfo.DismantlinglDate.substring(0,10),"dd-MMM-yyyy")
                        if (operationalInfo.PowerConnectionType.isNotEmpty())
                            AppPreferences.getInstance().setDropDown(holder.binding.AvailablePowerSource,DropDowns.PowerConnectionType.name,operationalInfo.PowerConnectionType.get(0).toString())
                        if (operationalInfo.Sitebillingstatus.isNotEmpty())
                            AppPreferences.getInstance().setDropDown(holder.binding.siteBillingStatus,DropDowns.Sitebillingstatus.name,operationalInfo.Sitebillingstatus.get(0).toString())
                    }
                    else
                        AppLogger.log("Operational Info data is empty")
                }catch (e:Exception){
                    AppLogger.log("error in Oprational Info Data on SiteInfo")
                }
            }
            is ViewHold3 -> {
                holder.binding.imgEdit.setOnClickListener {
//                    if (basicinfodata.GeoCondition.isNotEmpty())
//                        listener.geoConditionsDetailsItemClicked(basicinfodata.GeoCondition[0],basicinfodata.id.toString())
//                    else
//                        Toast.makeText(context,"Geo Condition ",Toast.LENGTH_SHORT).show()
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
                if(basicinfodata?.GeoCondition?.isNotEmpty()==true){
                    val geoCondition: GeoConditionData = basicinfodata?.GeoCondition!![0]
                    holder.binding.textAltitude.text = geoCondition.Altitude.toString()
                    holder.binding.minTempRange.text = geoCondition.TempRangeMin
                    holder.binding.maxTempRange.text = geoCondition.TempRangeMax
                    if (geoCondition.Potentialthreat.isNotEmpty())
                        AppPreferences.getInstance().setDropDown(holder.binding.potentioalThreatSpinner,DropDowns.Potentialthreat.name,
                            geoCondition.Potentialthreat[0].toString())
                    if (geoCondition.Windzone.isNotEmpty())
                        AppPreferences.getInstance().setDropDown(holder.binding.windZoneSpinner,DropDowns.Windzone.name,
                            geoCondition.Windzone[0].toString())
                    if (geoCondition.Seismiczone.isNotEmpty())
                        AppPreferences.getInstance().setDropDown(holder.binding.seismecZoneSpinner,DropDowns.Seismiczone.name,
                            geoCondition.Seismiczone[0].toString())
                    if (geoCondition.Floodzone.isNotEmpty())
                        AppPreferences.getInstance().setDropDown(holder.binding.floodZoneSpinner,DropDowns.Floodzone.name,
                        geoCondition.Floodzone[0].toString())
                    if (geoCondition.Terraintype.isNotEmpty())
                        AppPreferences.getInstance().setDropDown(holder.binding.terrainTypeSpinner,DropDowns.Terraintype.name,
                            geoCondition.Terraintype[0].toString())
                    if (geoCondition.Climatezone.isNotEmpty())
                        AppPreferences.getInstance().setDropDown(holder.binding.ClimateZone,DropDowns.Climatezone.name,
                            geoCondition.Climatezone[0].toString())
                }

            }
            is ViewHold4 -> {
                holder.binding.imgEdit.setOnClickListener {
//                    if (basicinfodata.GeoCondition.isNotEmpty())
//                        listener.siteAccessDetailsItemClicked(basicinfodata.SafetyAndAccess[0],basicinfodata.id.toString())
//                    else
//                        Toast.makeText(context,"Geo Condition ",Toast.LENGTH_SHORT).show()
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
                if(basicinfodata?.SafetyAndAccess?.isNotEmpty()==true){
                    AppLogger.log("basic info site data safty not empty: ${basicinfodata?.SafetyAndAccess}")
                    try {
                        val saftyAcess: SaftyAccessData = basicinfodata?.SafetyAndAccess!![0]
                        holder.binding.SiteAccesseethodology.text = saftyAcess.Siteaccessmethodology
                        holder.binding.PoliceStationNumber.text = saftyAcess.NearByPoliceStationNumber
                        holder.binding.nearByPoliceStation.text = saftyAcess.NearByPoliceStation
                        holder.binding.nearByFireStation.text = saftyAcess.NearByFireStation
                        holder.binding.fireStationNumber.text = saftyAcess.NearByFireStationNumber
                        holder.binding.GateFence.text = saftyAcess.NearByFireStationNumber
                        holder.binding.videoMonitoring.text = saftyAcess.Videomonitoring.toString()
                        holder.binding.siteAccessWay.text = saftyAcess.SiteAccessWay.toString()
                        holder.binding.dangerSignage.text = saftyAcess.DangerSignage.toString()
                        holder.binding.CautionSignage.text = saftyAcess.CautionSignage.toString()
                        if(saftyAcess.Physicalsecurity.isNotEmpty())
                            AppPreferences.getInstance().setDropDown(holder.binding.physicalSecurity,DropDowns.Physicalsecurity.name,saftyAcess.Physicalsecurity.get(0).toString())
                    }catch (e:java.lang.Exception){
                        AppLogger.log("e : ${e.localizedMessage}")
                    }
                }
                else
                    AppLogger.log("basic info site data safty empty: ${basicinfodata?.SafetyAndAccess}")

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