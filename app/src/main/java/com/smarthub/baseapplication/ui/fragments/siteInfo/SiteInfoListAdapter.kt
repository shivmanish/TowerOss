package com.smarthub.baseapplication.ui.fragments.siteInfo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newSiteInfoDataModel.*
import com.smarthub.baseapplication.model.siteInfo.siteInfoData.GeoCondition
import com.smarthub.baseapplication.model.siteInfo.siteInfoData.OperationalInfo
import com.smarthub.baseapplication.model.siteInfo.siteInfoData.SafetyAndAcces
import com.smarthub.baseapplication.model.siteInfo.siteInfoData.SiteBasicinfo
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class SiteInfoListAdapter(var baseFragment: BaseFragment,var listener: SiteInfoLisListener) : RecyclerView.Adapter<SiteInfoListAdapter.ViewHold>() {

    var list : ArrayList<String> = ArrayList()
    var currentOpened = -1
    var type1 = "Basic Details"
    var type2 = "Operational Info"
    var type3 = "Geo Condition"
    var type4 = "Safety / Access"
    var type5 = "OPCO Contact Details"
    private var basicinfodata : AllsiteInfoDataModel?=null
    private var siteBasicinfo: BasicInfoData ?=null
    private var siteAddress: SiteAddressData ?=null
    private var operationalInfo: OprationalInfoData?=null
    private var geoCondition: GeoConditionData?=null
    private var saftyAcess: SaftyAccessData?=null

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
                    holder.binding.viewLayout.visibility = View.VISIBLE
                    holder.binding.editLayout.visibility = View.GONE

                    holder.binding.imgEdit.setOnClickListener {
                        holder.binding.viewLayout.visibility = View.GONE
                        holder.binding.editLayout.visibility = View.VISIBLE
                    }
                    holder.binding.cancel.setOnClickListener {
                        holder.binding.viewLayout.visibility = View.VISIBLE
                        holder.binding.editLayout.visibility = View.GONE
                    }
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
                if (basicinfodata!=null){
                    if (basicinfodata?.Basicinfo!=null && basicinfodata?.Basicinfo?.isNotEmpty()==true){
                        siteBasicinfo=basicinfodata?.Basicinfo?.get(0)
                    }
                    if (basicinfodata?.Siteaddress!=null && basicinfodata?.Siteaddress?.isNotEmpty()==true){
                        siteAddress=basicinfodata?.Siteaddress?.get(0)
                    }

                }
                if (siteBasicinfo!=null){
                    holder.binding.txSiteName.text = siteBasicinfo?.siteName
                    holder.binding.txSiteID.text = siteBasicinfo?.siteID
                    holder.binding.SiteAlternateName.text=siteBasicinfo?.aliasName
                    if (siteBasicinfo?.Sitestatus?.isNotEmpty()==true)
                        AppPreferences.getInstance().setDropDown(holder.binding.siteStatus,DropDowns.Sitestatus.name,siteBasicinfo?.Sitestatus?.get(0).toString())
                    if (siteBasicinfo?.Sitecategory?.isNotEmpty()==true)
                        AppPreferences.getInstance().setDropDown(holder.binding.siteCategory,DropDowns.Sitecategory.name,siteBasicinfo?.Sitecategory?.get(0).toString())
                    if (siteBasicinfo?.Sitetype?.isNotEmpty() == true)
                        AppPreferences.getInstance().setDropDown(holder.binding.siteType,DropDowns.Sitetype.name,siteBasicinfo?.Sitetype?.get(0).toString())
                    if (siteBasicinfo?.Projectname?.isNotEmpty()==true)
                        AppPreferences.getInstance().setDropDown(holder.binding.txtProjectName,DropDowns.Projectname.name,siteBasicinfo?.Projectname?.get(0).toString())
                    if (siteBasicinfo?.Costcentre?.isNotEmpty()==true)
                        AppPreferences.getInstance().setDropDown(holder.binding.costCenter,DropDowns.Costcentre.name,siteBasicinfo?.Costcentre?.get(0).toString())
                    if (siteBasicinfo?.Opcositetype?.isNotEmpty()==true)
                        AppPreferences.getInstance().setDropDown(holder.binding.SiteTypeEdit,DropDowns.Sitetype.name,siteBasicinfo?.Opcositetype?.get(0).toString())
                    if (siteBasicinfo?.Acquisitiontype?.isNotEmpty()==true)
                        AppPreferences.getInstance().setDropDown(holder.binding.AcquisitionType,DropDowns.Acquisitiontype.name,siteBasicinfo?.Acquisitiontype?.get(0).toString())
                    if (siteBasicinfo?.PropertyType!=null && siteBasicinfo?.PropertyType!!>0)
                        AppPreferences.getInstance().setDropDown(holder.binding.PropertyType,DropDowns.PropertyType.name,siteBasicinfo?.PropertyType.toString())
                    if (siteBasicinfo?.Siteownership!=null && siteBasicinfo?.Siteownership!!>0)
                        AppPreferences.getInstance().setDropDown(holder.binding.SiteOwnership,DropDowns.SiteOwnership.name,siteBasicinfo?.Siteownership.toString())
                    if (siteBasicinfo?.MaintenancePoint!=null){
                        holder.binding.MaintenanceGeography.text = siteBasicinfo?.MaintenancePoint?.name
                        holder.binding.MaintenanceGeographyEdit.text = siteBasicinfo?.MaintenancePoint?.name
                    }

                    // edit mode
                    holder.binding.SiteNameEdit.setText(siteBasicinfo?.siteName)
                    holder.binding.SiteIDEdit.text = siteBasicinfo?.siteID
                    holder.binding.SiteAlternateNameEdit.setText(siteBasicinfo?.aliasName)
                }
                if (siteAddress!=null){
                    // view mode
                    holder.binding.AddressLine1.text = siteAddress?.address1
                    holder.binding.AddressLine2.text = siteAddress?.address2
                    holder.binding.postalCode.text=siteAddress?.pincode
                    holder.binding.siteLatitude.text=siteAddress?.locLatitude
                    holder.binding.siteLongitude.text=siteAddress?.locLongitude

                    // edit mode
                    holder.binding.AddressLine1Edit.setText(siteAddress?.address1)
                    holder.binding.AddressLine2Edit.setText(siteAddress?.address2)
                    holder.binding.PostalCodeEdit.setText(siteAddress?.pincode)
                    holder.binding.SiteLatitudeEdit.setText(siteAddress?.locLatitude)
                    holder.binding.SiteLongitudeEdit.setText(siteAddress?.locLongitude)
                }
                if (siteBasicinfo!=null && siteBasicinfo?.Sitestatus?.isNotEmpty()==true)
                    AppPreferences.getInstance().setDropDown(holder.binding.SiteStatusEdit,DropDowns.Sitestatus.name,siteBasicinfo?.Sitestatus?.get(0).toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.SiteStatusEdit,DropDowns.Sitestatus.name)
                if (siteBasicinfo!=null && siteBasicinfo?.Sitecategory?.isNotEmpty()==true)
                    AppPreferences.getInstance().setDropDown(holder.binding.SiteCategoryEdit,DropDowns.Sitecategory.name,siteBasicinfo?.Sitecategory?.get(0).toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.SiteCategoryEdit,DropDowns.Sitecategory.name)
                if (siteBasicinfo!=null && siteBasicinfo?.Sitetype?.isNotEmpty() == true)
                    AppPreferences.getInstance().setDropDown(holder.binding.SiteTypeEdit,DropDowns.Sitetype.name,siteBasicinfo?.Sitetype?.get(0).toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.SiteTypeEdit,DropDowns.Sitetype.name)
                if (siteBasicinfo!=null && siteBasicinfo?.Projectname?.isNotEmpty()==true)
                    AppPreferences.getInstance().setDropDown(holder.binding.ProjectNameEdit,DropDowns.Projectname.name,siteBasicinfo?.Projectname?.get(0).toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.ProjectNameEdit,DropDowns.Projectname.name)
                if (siteBasicinfo!=null && siteBasicinfo?.Costcentre?.isNotEmpty()==true)
                    AppPreferences.getInstance().setDropDown(holder.binding.CostCenterEdit,DropDowns.Costcentre.name,siteBasicinfo?.Costcentre?.get(0).toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.CostCenterEdit,DropDowns.Costcentre.name)
                if (siteBasicinfo!=null && siteBasicinfo?.Acquisitiontype?.isNotEmpty()==true)
                    AppPreferences.getInstance().setDropDown(holder.binding.AcquisitionTypeEdit,DropDowns.Acquisitiontype.name,siteBasicinfo?.Acquisitiontype?.get(0).toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.AcquisitionTypeEdit,DropDowns.Acquisitiontype.name)
                if (siteBasicinfo!=null && siteBasicinfo?.PropertyType!=null && siteBasicinfo?.PropertyType!!>0) {
                    AppPreferences.getInstance().setDropDown(holder.binding.PropertyTypeEdit,
                        DropDowns.PropertyType.name,
                        siteBasicinfo?.PropertyType.toString())
                }
                    else
                    AppPreferences.getInstance().setDropDown(holder.binding.PropertyTypeEdit,DropDowns.PropertyType.name)
                if (siteBasicinfo!=null && siteBasicinfo?.Siteownership!=null && siteBasicinfo?.Siteownership!!>0)
                    AppPreferences.getInstance().setDropDown(holder.binding.SiteOwnershipEdit,DropDowns.SiteOwnership.name,siteBasicinfo?.Siteownership.toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.SiteOwnershipEdit,DropDowns.SiteOwnership.name)

                holder.binding.update.setOnClickListener {
                    val temBAsicInfoData=BasicInfoData()
                    val temSiteAddData=SiteAddressData()
                    val temSiteInfoAllData=AllsiteInfoDataModel()
                    temBAsicInfoData.let {
                        it.siteName=holder.binding.SiteNameEdit.text.toString()
                        it.siteID=AppController.getInstance().siteName
                        it.aliasName=holder.binding.SiteAlternateNameEdit.text.toString()
                        it.Sitestatus= arrayListOf(holder.binding.SiteStatusEdit.selectedValue.id.toInt())
                        it.Sitecategory= arrayListOf(holder.binding.SiteCategoryEdit.selectedValue.id.toInt())
                        it.Opcositetype= arrayListOf(holder.binding.SiteTypeEdit.selectedValue.id.toInt())
                        it.Costcentre= arrayListOf(holder.binding.CostCenterEdit.selectedValue.id.toInt())
                        it.Projectname= arrayListOf(holder.binding.ProjectNameEdit.selectedValue.id.toInt())
                        it.Acquisitiontype= arrayListOf(holder.binding.AcquisitionTypeEdit.selectedValue.id.toInt())
                        it.PropertyType= holder.binding.PropertyTypeEdit.selectedValue.id.toIntOrNull()
                        it.Siteownership= holder.binding.SiteOwnershipEdit.selectedValue.id.toIntOrNull()
                        if (siteBasicinfo!=null)
                            it.id=siteBasicinfo?.id
                    }
                    temSiteAddData.let {
                        it.address1=holder.binding.AddressLine1Edit.text.toString()
                        it.address2=holder.binding.AddressLine2Edit.text.toString()
                        it.locLatitude=holder.binding.SiteLatitudeEdit.text.toString()
                        it.locLongitude=holder.binding.SiteLongitudeEdit.text.toString()
                        it.pincode=holder.binding.PostalCodeEdit.text.toString()
                        if (siteAddress!=null)
                            it.id=siteAddress?.id
                    }
                    temSiteInfoAllData.Basicinfo= arrayListOf(temBAsicInfoData)
                    temSiteInfoAllData.Siteaddress= arrayListOf(temSiteAddData)
                    listener.updateSiteInfo(temSiteInfoAllData)
                }

            }
            is ViewHold2 -> {
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                    holder.binding.iconLayout.visibility = View.VISIBLE
                    holder.binding.viewLayout.visibility = View.VISIBLE
                    holder.binding.editLayout.visibility = View.GONE
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
                    holder.binding.viewLayout.visibility = View.GONE
                    holder.binding.editLayout.visibility = View.VISIBLE
                }
                holder.binding.cancel.setOnClickListener {
                    holder.binding.viewLayout.visibility = View.VISIBLE
                    holder.binding.editLayout.visibility = View.GONE
                }
                holder.binding.itemTitle.text = list[position]
                if (basicinfodata!=null){
                    if(basicinfodata?.OperationalInfo!=null && basicinfodata?.OperationalInfo?.isNotEmpty()==true)
                        operationalInfo=basicinfodata?.OperationalInfo?.get(0)
                }
                if (operationalInfo!=null){
                    holder.binding.txtRFCDate.text = Utils.getFormatedDate( operationalInfo?.RFCDate,"dd-MMM-yyyy")
                    holder.binding.txtRFIDate.text = Utils.getFormatedDate( operationalInfo?.RFIDate,"dd-MMM-yyyy")
                    holder.binding.txtRFSDate.text = Utils.getFormatedDate( operationalInfo?.RFSDate,"dd-MMM-yyyy")
                    holder.binding.maxOperationTemp.text = operationalInfo?.OperatingTempMax
                    holder.binding.minOperationTemp.text = operationalInfo?.OperatingTempMin
                    holder.binding.FeasibleOPCOSharing.text = operationalInfo?.FeasibleOpcoSharing
                    holder.binding.AvailableOPCO.text = operationalInfo?.AvailableOpco
                    holder.binding.designDcLoad.text = operationalInfo?.DesignedDcLoad
                    holder.binding.installedDcLoad.text = operationalInfo?.InstalledDcLoad
                    holder.binding.TowerPoleHeight.text = operationalInfo?.TowerorPoleHeight
                    holder.binding.SiteAgreementDate.text = Utils.getFormatedDate(operationalInfo?.SiteAgreementDate,"dd-MMM-yyyy")
                    holder.binding.dismanting.text = Utils.getFormatedDate(operationalInfo?.DismantlinglDate,"dd-MMM-yyyy")
                    if (operationalInfo?.PowerConnectionType?.isNotEmpty()==true)
                        AppPreferences.getInstance().setDropDown(holder.binding.AvailablePowerSource,DropDowns.PowerConnectionType.name,operationalInfo?.PowerConnectionType?.get(0).toString())
                    if (operationalInfo?.Sitebillingstatus!=null && operationalInfo?.Sitebillingstatus!!>0)
                        AppPreferences.getInstance().setDropDown(holder.binding.siteBillingStatus,DropDowns.Sitebillingstatus.name,operationalInfo?.Sitebillingstatus.toString())
                    if (operationalInfo?.BackhaulNodeCategory!=null && operationalInfo?.BackhaulNodeCategory!!>0)
                        AppPreferences.getInstance().setDropDown(holder.binding.BackhaulNodeCategory,DropDowns.BackhaulNodeCategory.name,operationalInfo?.BackhaulNodeCategory.toString())

                    // edit mode
                    holder.binding.maxOperatingTempEdit.setText(operationalInfo?.OperatingTempMax)
                    holder.binding.minOperatingTempEdit.setText(operationalInfo?.OperatingTempMin)
                    holder.binding.FeasibleOpcoSharingEdit.setText(operationalInfo?.FeasibleOpcoSharing)
                    holder.binding.AvailableOpcoEdit.setText(operationalInfo?.AvailableOpco)
                    holder.binding.DesignDcLoadEdit.setText(operationalInfo?.DesignedDcLoad)
                    holder.binding.InstalledDcLoadEdit.setText(operationalInfo?.InstalledDcLoad)
                    holder.binding.TowerPoleHeightEdit.setText(operationalInfo?.TowerorPoleHeight)
                    holder.binding.minOperationTemp.text = operationalInfo?.OperatingTempMin
                    holder.binding.RfcDateEdit.text = Utils.getFormatedDate( operationalInfo?.RFCDate,"dd-MMM-yyyy")
                    holder.binding.RfiDateEdit.text = Utils.getFormatedDate( operationalInfo?.RFIDate,"dd-MMM-yyyy")
                    holder.binding.RfsDateEdit.text = Utils.getFormatedDate( operationalInfo?.RFSDate,"dd-MMM-yyyy")
                    holder.binding.DismantingDateEdit.text = Utils.getFormatedDate( operationalInfo?.DismantlinglDate,"dd-MMM-yyyy")
                    holder.binding.SiteAgreementDateEdit.text = Utils.getFormatedDate( operationalInfo?.SiteAgreementDate,"dd-MMM-yyyy")
                }

                if (operationalInfo!=null && operationalInfo?.PowerConnectionType?.isNotEmpty()==true)
                    AppPreferences.getInstance().setDropDown(holder.binding.AvailablePowerSourceEdit,DropDowns.PowerConnectionType.name,operationalInfo?.PowerConnectionType?.get(0).toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.AvailablePowerSourceEdit,DropDowns.PowerConnectionType.name)
                if (operationalInfo!=null && operationalInfo?.Sitebillingstatus!=null && operationalInfo?.Sitebillingstatus!!>0)
                    AppPreferences.getInstance().setDropDown(holder.binding.SiteBillingStatusEdit,DropDowns.Sitebillingstatus.name,operationalInfo?.Sitebillingstatus.toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.SiteBillingStatusEdit,DropDowns.Sitebillingstatus.name)
                if (operationalInfo!=null && operationalInfo?.BackhaulNodeCategory!=null && operationalInfo?.BackhaulNodeCategory!!>0)
                    AppPreferences.getInstance().setDropDown(holder.binding.BackhaulNodeCategoryEdit,DropDowns.BackhaulNodeCategory.name,operationalInfo?.BackhaulNodeCategory.toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.BackhaulNodeCategoryEdit,DropDowns.BackhaulNodeCategory.name)
                baseFragment.setDatePickerView(holder.binding.RfcDateEdit)
               baseFragment.setDatePickerView(holder.binding.RfiDateEdit)
               baseFragment.setDatePickerView(holder.binding.RfsDateEdit)
               baseFragment.setDatePickerView(holder.binding.DismantingDateEdit)

                holder.binding.update.setOnClickListener {
                    val temOperationalInfoData=OprationalInfoData()
                    val temSiteInfoAllData=AllsiteInfoDataModel()
                    temOperationalInfoData.let {
                        it.FeasibleOpcoSharing=holder.binding.FeasibleOpcoSharingEdit.text.toString()
                        it.AvailableOpco=holder.binding.AvailableOpcoEdit.text.toString()
                        it.DesignedDcLoad=holder.binding.DesignDcLoadEdit.text.toString()
                        it.InstalledDcLoad= holder.binding.InstalledDcLoadEdit.text.toString()
                        it.OperatingTempMin= holder.binding.minOperatingTempEdit.text.toString()
                        it.OperatingTempMax= holder.binding.maxOperatingTempEdit.text.toString()
                        it.TowerorPoleHeight= holder.binding.TowerPoleHeightEdit.text.toString()
                        it.PowerConnectionType= arrayListOf(holder.binding.AvailablePowerSourceEdit.selectedValue.id.toInt())
                        it.Sitebillingstatus= holder.binding.SiteBillingStatusEdit.selectedValue.id.toIntOrNull()
                        it.BackhaulNodeCategory= holder.binding.BackhaulNodeCategoryEdit.selectedValue.id.toIntOrNull()
                        it.RFCDate= Utils.getFullFormatedDate(holder.binding.RfcDateEdit.text.toString())
                        it.RFIDate= Utils.getFullFormatedDate(holder.binding.RfiDateEdit.text.toString())
                        it.RFSDate= Utils.getFullFormatedDate(holder.binding.RfsDateEdit.text.toString())
                        it.DismantlinglDate= Utils.getFullFormatedDate(holder.binding.DismantingDateEdit.text.toString())
                        if (operationalInfo!=null)
                            it.id=operationalInfo?.id
                    }

                    temSiteInfoAllData.OperationalInfo= arrayListOf(temOperationalInfoData)
                    listener.updateSiteInfo(temSiteInfoAllData)
                }
            }
            is ViewHold3 -> {
                holder.binding.imgEdit.setOnClickListener {
                    holder.binding.viewLayout.visibility = View.GONE
                    holder.binding.editLayout.visibility = View.VISIBLE
                }
                holder.binding.cancel.setOnClickListener {
                    holder.binding.viewLayout.visibility = View.VISIBLE
                    holder.binding.editLayout.visibility = View.GONE
                }
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                    holder.binding.iconLayout.visibility = View.VISIBLE
                    holder.binding.viewLayout.visibility = View.VISIBLE
                    holder.binding.editLayout.visibility = View.GONE
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
                if (basicinfodata!=null){
                    if(basicinfodata?.GeoCondition!=null && basicinfodata?.GeoCondition?.isNotEmpty()==true)
                        geoCondition=basicinfodata?.GeoCondition?.get(0)
                }
                if (geoCondition!=null){
                    //view mode
                    holder.binding.textAltitude.text = geoCondition?.Altitude.toString()
                    holder.binding.minTempRange.text = geoCondition?.TempRangeMin
                    holder.binding.maxTempRange.text = geoCondition?.TempRangeMax
                    if (geoCondition?.Potentialthreat?.isNotEmpty()==true)
                        AppPreferences.getInstance().setDropDown(holder.binding.potentioalThreatSpinner,DropDowns.Potentialthreat.name, geoCondition?.Potentialthreat?.get(0).toString())
                    if (geoCondition?.Windzone?.isNotEmpty()==true)
                        AppPreferences.getInstance().setDropDown(holder.binding.windZoneSpinner,DropDowns.Windzone.name, geoCondition?.Windzone?.get(0).toString())
                    if (geoCondition?.Seismiczone?.isNotEmpty()==true)
                        AppPreferences.getInstance().setDropDown(holder.binding.seismecZoneSpinner,DropDowns.Seismiczone.name, geoCondition?.Seismiczone?.get(0).toString())
                    if (geoCondition?.Floodzone?.isNotEmpty()==true)
                        AppPreferences.getInstance().setDropDown(holder.binding.floodZoneSpinner,DropDowns.Floodzone.name, geoCondition?.Floodzone?.get(0).toString())
                    if (geoCondition?.Terraintype?.isNotEmpty()==true)
                        AppPreferences.getInstance().setDropDown(holder.binding.terrainTypeSpinner,DropDowns.Terraintype.name, geoCondition?.Terraintype?.get(0).toString())
                    if (geoCondition?.Climatezone?.isNotEmpty()==true)
                        AppPreferences.getInstance().setDropDown(holder.binding.ClimateZone,DropDowns.Climatezone.name, geoCondition?.Climatezone?.get(0).toString())
                    // edit mode
                    holder.binding.AltitudeEdit.setText(geoCondition?.Altitude.toString())
                    holder.binding.minTempEdit.setText(geoCondition?.TempRangeMin)
                    holder.binding.maxTempEdit.setText(geoCondition?.TempRangeMax)
                }
                if (geoCondition!=null && geoCondition?.Potentialthreat?.isNotEmpty()==true)
                    AppPreferences.getInstance().setDropDown(holder.binding.PotentialThreatEdit,DropDowns.Potentialthreat.name, geoCondition?.Potentialthreat?.get(0).toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.PotentialThreatEdit,DropDowns.Potentialthreat.name)
                if (geoCondition!=null && geoCondition?.Windzone?.isNotEmpty()==true)
                    AppPreferences.getInstance().setDropDown(holder.binding.WindZoneEdit,DropDowns.Windzone.name, geoCondition?.Windzone?.get(0).toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.WindZoneEdit,DropDowns.Windzone.name)
                if (geoCondition!=null && geoCondition?.Seismiczone?.isNotEmpty()==true)
                    AppPreferences.getInstance().setDropDown(holder.binding.SeismecZoneEdit,DropDowns.Seismiczone.name, geoCondition?.Seismiczone?.get(0).toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.SeismecZoneEdit,DropDowns.Seismiczone.name)
                if (geoCondition!=null && geoCondition?.Floodzone?.isNotEmpty()==true)
                    AppPreferences.getInstance().setDropDown(holder.binding.FloodZoneEdit,DropDowns.Floodzone.name, geoCondition?.Floodzone?.get(0).toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.FloodZoneEdit,DropDowns.Floodzone.name)
                if (geoCondition!=null && geoCondition?.Terraintype?.isNotEmpty()==true)
                    AppPreferences.getInstance().setDropDown(holder.binding.TerrainTypeEdit,DropDowns.Terraintype.name, geoCondition?.Terraintype?.get(0).toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.TerrainTypeEdit,DropDowns.Terraintype.name)
                if (geoCondition!=null && geoCondition?.Climatezone?.isNotEmpty()==true)
                    AppPreferences.getInstance().setDropDown(holder.binding.ClimateZoneEdit,DropDowns.Climatezone.name, geoCondition?.Climatezone?.get(0).toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.ClimateZoneEdit,DropDowns.Climatezone.name)
                holder.binding.update.setOnClickListener {
                    val temGeoConditionData=GeoConditionData()
                    val temSiteInfoAllData=AllsiteInfoDataModel()
                    temGeoConditionData.let {
                        it.Altitude=holder.binding.AltitudeEdit.text.toString().toIntOrNull()
                        it.TempRangeMin=holder.binding.minTempEdit.text.toString()
                        it.TempRangeMax=holder.binding.maxTempEdit.text.toString()
                        it.Windzone= arrayListOf(holder.binding.WindZoneEdit.selectedValue.id.toInt())
                        it.Seismiczone= arrayListOf(holder.binding.SeismecZoneEdit.selectedValue.id.toInt())
                        it.Climatezone= arrayListOf(holder.binding.ClimateZoneEdit.selectedValue.id.toInt())
                        it.Floodzone= arrayListOf(holder.binding.FloodZoneEdit.selectedValue.id.toInt())
                        it.Terraintype= arrayListOf(holder.binding.TerrainTypeEdit.selectedValue.id.toInt())
                        it.Potentialthreat= arrayListOf(holder.binding.PotentialThreatEdit.selectedValue.id.toInt())
                        if (geoCondition!=null)
                            it.id=geoCondition?.id
                    }
                    temSiteInfoAllData.GeoCondition= arrayListOf(temGeoConditionData)
                    listener.updateSiteInfo(temSiteInfoAllData)
                }
            }
            is ViewHold4 -> {
                holder.binding.imgEdit.setOnClickListener {
                    holder.binding.viewLayout.visibility = View.GONE
                    holder.binding.editLayout.visibility = View.VISIBLE
                }
                holder.binding.cancel.setOnClickListener {
                    holder.binding.viewLayout.visibility = View.VISIBLE
                    holder.binding.editLayout.visibility = View.GONE
                }
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                    holder.binding.iconLayout.visibility = View.VISIBLE
                    holder.binding.viewLayout.visibility = View.VISIBLE
                    holder.binding.editLayout.visibility = View.GONE
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
                if (basicinfodata!=null){
                    if (basicinfodata?.SafetyAndAccess!=null && basicinfodata?.SafetyAndAccess?.isNotEmpty()==true)
                        saftyAcess=basicinfodata?.SafetyAndAccess?.get(0)
                }
                if (saftyAcess!=null){
                    holder.binding.SiteAccesseethodology.text = saftyAcess?.Siteaccessmethodology
                    holder.binding.PoliceStationNumber.text = saftyAcess?.NearByPoliceStationNumber
                    holder.binding.nearByPoliceStation.text = saftyAcess?.NearByPoliceStation
                    holder.binding.DistancePolice.text = saftyAcess?.NearByPoliceStationDistance
                    holder.binding.PoliceStationNumber.text = saftyAcess?.NearByPoliceStationDistance
                    holder.binding.nearByFireStation.text = saftyAcess?.NearByFireStation
                    holder.binding.DistanceFire.text = saftyAcess?.NearByFireStationDistance
                    holder.binding.fireStationNumber.text = saftyAcess?.NearByFireStationNumber
                    if (saftyAcess?.GateAndFence!=null && saftyAcess?.GateAndFence!! >0)
                        AppPreferences.getInstance().setDropDown(holder.binding.GateFence,DropDowns.GateAndFence.name,saftyAcess?.GateAndFence.toString())
                    if (saftyAcess?.Videomonitoring!=null && saftyAcess?.Videomonitoring!! >0)
                        AppPreferences.getInstance().setDropDown(holder.binding.videoMonitoring,DropDowns.VideoMonitoring.name,saftyAcess?.Videomonitoring.toString())
                    if (saftyAcess?.SiteAccessWay!=null && saftyAcess?.SiteAccessWay!! >0)
                        AppPreferences.getInstance().setDropDown(holder.binding.siteAccessWay,DropDowns.SiteAccessWay.name,saftyAcess?.SiteAccessWay.toString())
                    if (saftyAcess?.DangerSignage!=null && saftyAcess?.DangerSignage!! >0)
                        AppPreferences.getInstance().setDropDown(holder.binding.dangerSignage,DropDowns.DangerSignage.name,saftyAcess?.DangerSignage.toString())
                    if (saftyAcess?.CautionSignage!=null && saftyAcess?.CautionSignage!! >0)
                        AppPreferences.getInstance().setDropDown(holder.binding.CautionSignage,DropDowns.CautionSignage.name,saftyAcess?.CautionSignage.toString())
                    if(saftyAcess?.Physicalsecurity!=null && saftyAcess?.Physicalsecurity!! >0)
                        AppPreferences.getInstance().setDropDown(holder.binding.physicalSecurity,DropDowns.PhysicalSecurity.name,saftyAcess?.Physicalsecurity.toString())
                    //edit mode
                    holder.binding.SiteAccessMethodologyEdit.setText(saftyAcess?.Siteaccessmethodology)
                    holder.binding.NearbyPoliceStationEdit.setText(saftyAcess?.NearByPoliceStation)
                    holder.binding.DistancePoliceEdit.setText(saftyAcess?.NearByPoliceStationDistance)
                    holder.binding.PhoneNumberPoliceEdit.setText(saftyAcess?.NearByPoliceStationNumber)
                    holder.binding.NearbyFireEdit.setText(saftyAcess?.NearByFireStation)
                    holder.binding.DistanceFireEdit.setText(saftyAcess?.NearByFireStationDistance)
                    holder.binding.PhoneNumberFireEdit.setText(saftyAcess?.NearByFireStationNumber)
                }
                if (saftyAcess!=null && saftyAcess?.GateAndFence!=null && saftyAcess?.GateAndFence!! >0)
                    AppPreferences.getInstance().setDropDown(holder.binding.GateFenceEdit,DropDowns.GateAndFence.name,saftyAcess?.GateAndFence.toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.GateFenceEdit,DropDowns.GateAndFence.name)
                if (saftyAcess!=null && saftyAcess?.Videomonitoring!=null && saftyAcess?.Videomonitoring!! >0)
                    AppPreferences.getInstance().setDropDown(holder.binding.videoMonitoringEdit,DropDowns.VideoMonitoring.name,saftyAcess?.Videomonitoring.toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.videoMonitoringEdit,DropDowns.VideoMonitoring.name)
                if (saftyAcess!=null && saftyAcess?.SiteAccessWay!=null && saftyAcess?.SiteAccessWay!! >0)
                    AppPreferences.getInstance().setDropDown(holder.binding.SiteAccessWayEdit,DropDowns.SiteAccessWay.name,saftyAcess?.SiteAccessWay.toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.SiteAccessWayEdit,DropDowns.SiteAccessWay.name)
                if (saftyAcess!=null && saftyAcess?.DangerSignage!=null && saftyAcess?.DangerSignage!! >0)
                    AppPreferences.getInstance().setDropDown(holder.binding.DangerSignageEdit,DropDowns.DangerSignage.name,saftyAcess?.DangerSignage.toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.DangerSignageEdit,DropDowns.DangerSignage.name)
                if (saftyAcess!=null && saftyAcess?.CautionSignage!=null && saftyAcess?.CautionSignage!! >0)
                    AppPreferences.getInstance().setDropDown(holder.binding.CautionSignageEdit,DropDowns.CautionSignage.name,saftyAcess?.CautionSignage.toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.CautionSignageEdit,DropDowns.CautionSignage.name)
                if(saftyAcess!=null && saftyAcess?.Physicalsecurity!=null && saftyAcess?.Physicalsecurity!! >0){
                    AppPreferences.getInstance().setDropDown(holder.binding.PhysicalSecurityEdit,DropDowns.PhysicalSecurity.name,saftyAcess?.Physicalsecurity.toString())
                }
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.PhysicalSecurityEdit,DropDowns.PhysicalSecurity.name)
                holder.binding.update.setOnClickListener {
                    val temSaftyAccessData=SaftyAccessData()
                    val temSiteInfoAllData=AllsiteInfoDataModel()
                    temSaftyAccessData.let {
                        it.Siteaccessmethodology=holder.binding.SiteAccesseethodology.text.toString()
                        it.NearByPoliceStation=holder.binding.NearbyPoliceStationEdit.text.toString()
                        it.NearByPoliceStationDistance=holder.binding.DistancePoliceEdit.text.toString()
                        it.NearByPoliceStationNumber=holder.binding.PhoneNumberPoliceEdit.text.toString()
                        it.NearByFireStation=holder.binding.NearbyFireEdit.text.toString()
                        it.NearByFireStationDistance=holder.binding.DistanceFireEdit.text.toString()
                        it.NearByFireStationNumber=holder.binding.PhoneNumberFireEdit.text.toString()
                        it.Physicalsecurity= holder.binding.PhysicalSecurityEdit.selectedValue.id.toIntOrNull()
                        it.GateAndFence= holder.binding.GateFenceEdit.selectedValue.id.toIntOrNull()
                        it.Videomonitoring= holder.binding.videoMonitoringEdit.selectedValue.id.toIntOrNull()
                        it.SiteAccessWay= holder.binding.SiteAccessWayEdit.selectedValue.id.toIntOrNull()
                        it.DangerSignage= holder.binding.DangerSignageEdit.selectedValue.id.toIntOrNull()
                        it.CautionSignage= holder.binding.CautionSignageEdit.selectedValue.id.toIntOrNull()
                        if (geoCondition!=null)
                            it.id=geoCondition?.id
                    }
                    temSiteInfoAllData.SafetyAndAccess= arrayListOf(temSaftyAccessData)
                    listener.updateSiteInfo(temSiteInfoAllData)
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
        fun detailsItemClicked(siteBasicinfo: SiteBasicinfo, id : String)
        fun operationInfoDetailsItemClicked(operationalInfo: OperationalInfo, id : String)
        fun geoConditionsDetailsItemClicked(geoCondition: GeoCondition, id : String)
        fun siteAccessDetailsItemClicked(safetyAndAccess: SafetyAndAcces, id : String)
        fun updateSiteInfo(data:AllsiteInfoDataModel?)
    }
}