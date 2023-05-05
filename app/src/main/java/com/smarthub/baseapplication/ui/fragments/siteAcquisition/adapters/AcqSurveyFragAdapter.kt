package com.smarthub.baseapplication.ui.fragments.siteAcquisition.adapters

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.dropdown.DropDownItem
import com.smarthub.baseapplication.model.serviceRequest.SRDetails
import com.smarthub.baseapplication.model.siteIBoard.Attachments
import com.smarthub.baseapplication.model.siteIBoard.AttachmentsConditions
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.*
import com.smarthub.baseapplication.model.siteIBoard.newSiteInfoDataModel.AllsiteInfoDataModel
import com.smarthub.baseapplication.model.siteIBoard.newSiteInfoDataModel.SiteAddressData
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.AttachmentConditionalAdapter
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.tableAdapters.InsidePremisesTableAdapter
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.tableAdapters.LocationMarkingTableAdapter
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.tableAdapters.OutsidePremisesTableAdapter
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.tableAdapters.PropertyOwnerTableAdapter
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.widgets.CustomSpinner

class AcqSurveyFragAdapter(var baseFragment: BaseFragment, var listener: AcqSurveyListListener, data:NewSiteAcquiAllData?) : RecyclerView.Adapter<AcqSurveyFragAdapter.ViewHold>() {
    private var datalist: AcquisitionSurveyData?=null
    private var feasibilityData: SAcqFeasibilityDetail?=null
    private var powerData: SAcqPowerConnectionFeasibility?=null
    private var propertyData: SAcqPropertyDetail?=null
    private var buildingData: SAcqBuildingDetail?=null
    private var landData: SAcqLandDetail ?=null
    private var siteAdd:SiteAddressData ?=null
    private var nominalSiteAdd: SRDetails?=null


    fun setData(data: AcquisitionSurveyData?) {
        this.datalist=data!!
        if (AppController.getInstance().newSiteInfoModel!=null){
            if (AppController.getInstance().newSiteInfoModel.Siteaddress!=null && AppController.getInstance().newSiteInfoModel.Siteaddress?.isNotEmpty()==true){
                siteAdd=AppController.getInstance().newSiteInfoModel.Siteaddress?.get(0)
            }
        }
        notifyDataSetChanged()
    }
    init {
        try {
            if (data!=null && data.SAcqAssignACQTeam?.isNotEmpty()!!){
                datalist=data.SAcqAcquitionSurvey?.get(0)
            }
        }catch (e:java.lang.Exception){
            AppLogger.log("AcqSurveyFragAdapter TowerInfoFrag error :${e.localizedMessage}")
        }
    }



    var list : ArrayList<String> = ArrayList()
    var currentOpened = -1
    var type1 = "Property Details"
    var type2 = "External Structure"
    var type3 = "Power Connection Feasibility"
    var type4 = "Property Owner’s Detail"
    var type5 = "Feasibility Detail"
    var type6 = "Attachments"

    init {
        list.add("Property Details")
        list.add("External Structure")
        list.add("Power Connection Feasibility")
        list.add("Property Owner’s Detail")
        list.add("Feasibility Detail")
        list.add("Attachments")
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)

    class ViewHold1(itemView: View) : ViewHold(itemView) {
        var binding : AcqSurveyPropertyDetailsItemsBinding = AcqSurveyPropertyDetailsItemsBinding.bind(itemView)

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
        var binding : ExternalStructureItemsBinding = ExternalStructureItemsBinding.bind(itemView)
        var insidePremisesTableList: RecyclerView=binding.insidePremisesTableItem
        var outsidePremisesTableList: RecyclerView=binding.outsidePremisesTableItem

        init {
            binding.collapsingLayout.tag = false
            if ((binding.collapsingLayout.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }

            binding.addItemsInsideLayout.setOnClickListener {
                addInsidePremisesTableItem()
            }
            binding.addItemsOutsideLayout.setOnClickListener {
                addOutsidePremisesTableItem()
            }
        }

        private fun addInsidePremisesTableItem(){
            if (insidePremisesTableList.adapter!=null && insidePremisesTableList.adapter is InsidePremisesTableAdapter){
                val adapter = insidePremisesTableList.adapter as InsidePremisesTableAdapter
                adapter.addItem()
            }
        }
        private fun addOutsidePremisesTableItem(){
            if (outsidePremisesTableList.adapter!=null && outsidePremisesTableList.adapter is OutsidePremisesTableAdapter){
                val adapter = outsidePremisesTableList.adapter as OutsidePremisesTableAdapter
                adapter.addItem()
            }
        }

    }
    class ViewHold3(itemView: View) : ViewHold(itemView) {
        var binding : AcqPowerConnectionFeasibilityItemsBinding = AcqPowerConnectionFeasibilityItemsBinding.bind(itemView)

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
    class ViewHold4(itemView: View) : ViewHold(itemView) {
        var binding : AcqPropertyOwnersDetailsItemsBinding = AcqPropertyOwnersDetailsItemsBinding.bind(itemView)
        var ownerTableList: RecyclerView=binding.propertyOwnerTableItem

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
                addOwnerTableItem()
            }
        }

        private fun addOwnerTableItem(){
            if (ownerTableList.adapter!=null && ownerTableList.adapter is PropertyOwnerTableAdapter){
                val adapter = ownerTableList.adapter as PropertyOwnerTableAdapter
                adapter.addItem()
            }
        }


    }
    class ViewHold5(itemView: View) : ViewHold(itemView) {
        var binding : AcqFeasibilityDetailsItemsBinding = AcqFeasibilityDetailsItemsBinding.bind(itemView)
        var locationMarkingTableList: RecyclerView=binding.LocationMarkingTableItem
        init {
            binding.collapsingLayout.tag = false
            if ((binding.collapsingLayout.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }

            binding.addItemsLocationMarkingLayout.setOnClickListener {
                addInsidePremisesTableItem()
            }
        }

        private fun addInsidePremisesTableItem(){
            if (locationMarkingTableList.adapter!=null && locationMarkingTableList.adapter is LocationMarkingTableAdapter){
                val adapter = locationMarkingTableList.adapter as LocationMarkingTableAdapter
                adapter.addItem()
            }
        }

    }
    class ViewHold6(itemView: View,listener: AcqSurveyListListener) : ViewHold(itemView) {
        var binding: SiteAcqConditionalAttachmentsBinding = SiteAcqConditionalAttachmentsBinding.bind(itemView)
        val recyclerListener:RecyclerView = binding.attachmentTableItem

        init {
            binding.collapsingLayout.tag = false
            if ((binding.collapsingLayout.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }

//            recyclerListener.adapter = adapter

//            itemView.findViewById<View>(R.id.attach_card).setOnClickListener {
//                listener.addAttachment()
//            }

        }
    }

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
        else if (list[position]==type6)
            return 6
        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_empty,parent,false)
        when (viewType) {
            1 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.acq_survey_property_details_items, parent, false)
                return ViewHold1(view)
            }
            2 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.external_structure_items, parent, false)
                return ViewHold2(view)
            }
            3 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.acq_power_connection_feasibility_items, parent, false)
                return ViewHold3(view)
            }
            4 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.acq_property_owners_details_items, parent, false)
                return ViewHold4(view)
            }
            5 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.acq_feasibility_details_items, parent, false)
                return ViewHold5(view)
            }
            6 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.site_acq_conditional_attachments, parent, false)
                return ViewHold6(view,listener)
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
                    holder.binding.imgEdit.visibility = View.VISIBLE
                    holder.binding.viewLayout.visibility = View.VISIBLE
                    holder.binding.editLayout.visibility = View.GONE

                    holder.binding.imgEdit.setOnClickListener {
                        if (AppController.getInstance().isTaskEditable) {
                            holder.binding.viewLayout.visibility = View.GONE
                            holder.binding.editLayout.visibility = View.VISIBLE
                        }
                    }
                    holder.binding.cancel.setOnClickListener {
                        holder.binding.viewLayout.visibility = View.VISIBLE
                        holder.binding.editLayout.visibility = View.GONE
                    }
                }
                else {
                    holder.binding.collapsingLayout.tag = false
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                    holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    holder.binding.itemLine.visibility = View.VISIBLE
                    holder.binding.itemCollapse.visibility = View.GONE
                    holder.binding.imgEdit.visibility = View.GONE
                }
                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
                holder.binding.itemTitleStr.text = list[position]
                if (datalist!=null && datalist?.SAcqPropertyDetail?.isNotEmpty()==true){
                    propertyData=datalist?.SAcqPropertyDetail?.get(0)
                    if (propertyData!=null && propertyData?.SAcqBuildingDetail?.isNotEmpty()==true){
                        buildingData=propertyData?.SAcqBuildingDetail?.get(0)
                    }
                    if (propertyData!=null && propertyData?.SAcqLandDetail?.isNotEmpty()==true){
                        landData=propertyData?.SAcqLandDetail?.get(0)
                    }
                }
                if (AppController.getInstance().newSiteInfoModel!=null){
                    if (AppController.getInstance().newSiteInfoModel.Siteaddress!=null && AppController.getInstance().newSiteInfoModel.Siteaddress?.isNotEmpty()==true){
                        siteAdd=AppController.getInstance().newSiteInfoModel.Siteaddress?.get(0)
                    }
                }
                if (AppController.getInstance().NominalAddress!=null){
                    nominalSiteAdd=AppController.getInstance().NominalAddress
                }
                if (nominalSiteAdd!=null){
                    // view Mode
                    holder.binding.AddressLine1.text=nominalSiteAdd?.Address1
                    holder.binding.AddressLine2.text=nominalSiteAdd?.Address2
                    holder.binding.PostalCode.text=nominalSiteAdd?.Pincode
                    holder.binding.SiteLAtitude.text=nominalSiteAdd?.locLatitude
                    holder.binding.SiteLongitude.text=nominalSiteAdd?.locLongitude
                    // edit mode
                    holder.binding.AddressLine1Edit.text=nominalSiteAdd?.Address1
                    holder.binding.AddressLine2Edit.text=nominalSiteAdd?.Address2
                    holder.binding.PostalCodeEdit.text=nominalSiteAdd?.Pincode
                    holder.binding.SiteLAtitudeEdit.text=nominalSiteAdd?.locLatitude
                    holder.binding.SiteLongitudeEdit.text=nominalSiteAdd?.locLongitude
                }
                if (siteAdd!=null){
                    // view Mode
                    holder.binding.ActualCoordiAddressLine1.text=siteAdd?.address1
                    holder.binding.ActualCoordiAddressLine2.text=siteAdd?.address2
                    holder.binding.ActualCoordiPostalCode.text=siteAdd?.pincode
                    holder.binding.ActualCoordiSiteLAtitude.text=siteAdd?.locLatitude
                    holder.binding.ActualCoordiSiteLongitude.text=siteAdd?.locLongitude
                    // edit mode
                    holder.binding.ActualCoordiAddressLine1Edit.setText(siteAdd?.address1)
                    holder.binding.ActualCoordiAddressLine2Edit.text=siteAdd?.address2
                    holder.binding.ActualCoordiPostalCodeEdit.text=siteAdd?.pincode
                    holder.binding.ActualCoordiSiteLAtitudeEdit.text=siteAdd?.locLatitude
                    holder.binding.ActualCoordiSiteLongitudeEdit.text=siteAdd?.locLongitude
                }
                if (siteAdd!=null && nominalSiteAdd!=null){
                    if (siteAdd?.locLatitude!=null && siteAdd?.locLongitude!=null &&
                        nominalSiteAdd?.locLatitude!=null && nominalSiteAdd?.locLongitude!=null){
                        Utils.calculateDistanceLatLong(nominalSiteAdd?.locLatitude!!,nominalSiteAdd?.locLongitude!!,
                            siteAdd?.locLatitude!!,siteAdd?.locLongitude!!,holder.binding.DistanceFromProposedLoc)
                        Utils.calculateDistanceLatLong(nominalSiteAdd?.locLatitude!!,nominalSiteAdd?.locLongitude!!,
                            siteAdd?.locLatitude!!,siteAdd?.locLongitude!!,holder.binding.DistanceFromProposedLocEdit)
                    }
                }
                if (propertyData!=null){
                    if (propertyData?.PropertyType != null && propertyData?.PropertyType!! ==1){
                        holder.binding.BuildingLayoutView.visibility=View.VISIBLE
                        holder.binding.BuildingLayoutEdit.visibility=View.VISIBLE
                        holder.binding.LandLayoutView.visibility=View.GONE
                        holder.binding.LandLayoutEdit.visibility=View.GONE
                    }
                    else if(propertyData?.PropertyType != null && propertyData?.PropertyType!! ==2){
                        holder.binding.BuildingLayoutView.visibility=View.GONE
                        holder.binding.BuildingLayoutEdit.visibility=View.GONE
                        holder.binding.LandLayoutView.visibility=View.VISIBLE
                        holder.binding.LandLayoutEdit.visibility=View.VISIBLE
                    }
                    else{
                        holder.binding.BuildingLayoutView.visibility=View.VISIBLE
                        holder.binding.BuildingLayoutEdit.visibility=View.VISIBLE
                        holder.binding.LandLayoutView.visibility=View.VISIBLE
                        holder.binding.LandLayoutEdit.visibility=View.VISIBLE
                    }

                    //view mode
                    if (propertyData?.Potentialthreat?.isNotEmpty() == true)
                        AppPreferences.getInstance().setDropDown(holder.binding.PotentialThreat,DropDowns.Potentialthreat.name,propertyData?.Potentialthreat?.get(0).toString())
                    if (propertyData?.Direction?.isNotEmpty() == true)
                        AppPreferences.getInstance().setDropDown(holder.binding.SiteAccessDirection,DropDowns.Direction.name,propertyData?.Direction?.get(0).toString())
                    if (propertyData?.SiteAccessWay != null && propertyData?.SiteAccessWay!! >=1)
                        AppPreferences.getInstance().setDropDown(holder.binding.SiteAccessWay,DropDowns.SiteAccessWay.name,propertyData?.SiteAccessWay.toString())
                    if (propertyData?.GateAndFence != null && propertyData?.GateAndFence!! >=1)
                        AppPreferences.getInstance().setDropDown(holder.binding.GateAndFence,DropDowns.GateAndFence.name,propertyData?.GateAndFence.toString())
                    if (propertyData?.OtherOperator != null && propertyData?.OtherOperator!! >=1)
                        AppPreferences.getInstance().setDropDown(holder.binding.OtherOperators,DropDowns.OtherOperators.name,propertyData?.OtherOperator.toString())
                    if (propertyData?.PropertyType != null && propertyData?.PropertyType!! >=1)
                        AppPreferences.getInstance().setDropDown(holder.binding.PropertyType,DropDowns.PropertyType.name,propertyData?.PropertyType.toString())

                    holder.binding.OtherOperatorNames.text=propertyData?.OtherOperatorName
                    holder.binding.NearbyPoliceForceStation.text=propertyData?.NearbyPoliceStation
                    holder.binding.NearbyFireStation.text=propertyData?.NearbyFireStation
                    holder.binding.PoliceDistance.text=propertyData?.PsDistance
                    holder.binding.DistanceFireStation.text=propertyData?.FsDistance
                    holder.binding.PhnumberPolice.text=propertyData?.PsPhoneNo
                    holder.binding.PhNumberFireStation.text=propertyData?.FsPhoneNo
                    holder.binding.remarks.text=propertyData?.remark

                    // edit mode
                    holder.binding.OtherOperatorNameEdit.setText(propertyData?.OtherOperatorName)
                    holder.binding.NearbyPoliceForceStationEdit.setText(propertyData?.NearbyPoliceStation)
                    holder.binding.NearbyFireStationEdit.setText(propertyData?.NearbyFireStation)
                    holder.binding.PoliceDistanceEdit.setText(propertyData?.PsDistance)
                    holder.binding.DistanceFireStationEdit.setText(propertyData?.FsDistance)
                    holder.binding.PhNumberPoliceEdit.setText(propertyData?.PsPhoneNo)
                    holder.binding.phNoFireStationEdit.setText(propertyData?.FsPhoneNo)
                    holder.binding.remarksEdit.setText(propertyData?.remark)
                }
                if (buildingData!=null){
                    //view mode
                    if (buildingData?.BuildingType?.isNotEmpty()==true)
                        AppPreferences.getInstance().setDropDown(holder.binding.BuildingType,DropDowns.Buildingtype.name,buildingData?.BuildingType?.get(0).toString())
                    if (buildingData?.PropertyType != null && buildingData?.PropertyType!! >=1)
                        AppPreferences.getInstance().setDropDown(holder.binding.PropertyType,DropDowns.PropertyType.name,buildingData?.PropertyType.toString())
                    if (buildingData?.BuildingBuildType != null && buildingData?.BuildingBuildType!! >=1)
                        AppPreferences.getInstance().setDropDown(holder.binding.BuildingBuildType,DropDowns.BuildingBuildType.name,buildingData?.BuildingBuildType.toString())
                    holder.binding.BuildingHeight.text=buildingData?.BuildingHeight
                    holder.binding.BuildingAcquisitionAreaL.text=buildingData?.Length
                    holder.binding.BuildingAcquisitionAreaB.text=buildingData?.Breadth
                    holder.binding.BuildingAcquisitionAreaA.text=buildingData?.AcquisitionArea
                    holder.binding.TypicalFloorArea.text=buildingData?.TypicalFloorArea
                    holder.binding.YearofConstruction.text=buildingData?.ConstructionYear.toString()
                    holder.binding.NoofFloors.text=buildingData?.NoOfFloors.toString()
                    holder.binding.BuildingRemarks.text=buildingData?.remark
                    // edit mode
                    holder.binding.BuildingHeightEdit.setText(buildingData?.BuildingHeight)
                    holder.binding.BuildingAcquisitionAreaLEdit.setText(buildingData?.Length)
                    holder.binding.BuildingAcquisitionAreaBEdit.setText(buildingData?.Breadth)
                    holder.binding.BuildingAcquisitionAreaAEdit.text=buildingData?.AcquisitionArea
                    holder.binding.TypicalFloorAreaEdit.setText(buildingData?.TypicalFloorArea)
                    holder.binding.BuildingRemarksEdit.setText(buildingData?.remark)
                    holder.binding.YearOfConstructionEdit.setText(buildingData?.ConstructionYear.toString())
                    holder.binding.NoOfFloorEdit.setText(buildingData?.NoOfFloors.toString())
                }
                if (landData!=null){
                    //View Mode
                    holder.binding.LandAcquisitionAreaL.text=landData?.Length
                    holder.binding.LandAcquisitionAreaB.text=landData?.Breadth
                    holder.binding.LandAcquisitionAreaA.text=landData?.AcquisitionArea
                    holder.binding.LandRemarks.text=landData?.remark
                    if (landData?.LandType?.isNotEmpty()==true)
                        AppPreferences.getInstance().setDropDown(holder.binding.LandType,DropDowns.LandType.name, landData?.LandType?.get(0).toString())
                    if (landData?.SoilType?.isNotEmpty()==true)
                        AppPreferences.getInstance().setDropDown(holder.binding.SoilType,DropDowns.SoilType.name, landData?.SoilType?.get(0).toString())
                    if (landData?.Terraintype?.isNotEmpty()==true)
                        AppPreferences.getInstance().setDropDown(holder.binding.TerrainType,DropDowns.Terraintype.name, landData?.Terraintype?.get(0).toString())
                    if (landData?.SiteDemarcation != null && landData?.SiteDemarcation!! >=1)
                        AppPreferences.getInstance().setDropDown(holder.binding.SiteDemarcation,DropDowns.SiteDemarcation.name,landData?.SiteDemarcation.toString())
                    if (landData?.PlotLevelingCondition != null && landData?.PlotLevelingCondition!! >=1)
                        AppPreferences.getInstance().setDropDown(holder.binding.PlotLevelingCondition,DropDowns.PlotLevelingCondition.name,landData?.PlotLevelingCondition.toString())
                    if (landData?.LowLyingArea != null && landData?.LowLyingArea!! >=1)
                        AppPreferences.getInstance().setDropDown(holder.binding.LowLyingArea,DropDowns.YesNoDropdown.name,landData?.LowLyingArea.toString())
                    // edit mode
                    holder.binding.LandAcquisitionAreaLEdit.setText(landData?.Length)
                    holder.binding.LandAcquisitionAreaBEdit.setText(landData?.Breadth)
                    holder.binding.LandRemarksEdit.setText(landData?.remark)
                    holder.binding.LandAcquisitionAreaAEdit.text=landData?.AcquisitionArea
                }
                if (propertyData!=null && propertyData?.Potentialthreat?.isNotEmpty() == true)
                    AppPreferences.getInstance().setDropDown(holder.binding.PotentialThreatEdit,DropDowns.Potentialthreat.name,propertyData?.Potentialthreat?.get(0).toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.PotentialThreatEdit,DropDowns.Potentialthreat.name)
                if (propertyData!=null && propertyData?.Direction?.isNotEmpty() == true)
                    AppPreferences.getInstance().setDropDown(holder.binding.SiteAccessDirectionEdit,DropDowns.Direction.name,propertyData?.Direction?.get(0).toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.SiteAccessDirectionEdit,DropDowns.Direction.name)
                if (propertyData!=null && propertyData?.SiteAccessWay != null && propertyData?.SiteAccessWay!! >=1)
                    AppPreferences.getInstance().setDropDown(holder.binding.SiteAccessWayEdit,DropDowns.SiteAccessWay.name,propertyData?.SiteAccessWay.toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.SiteAccessWayEdit,DropDowns.SiteAccessWay.name)
                if (propertyData!=null && propertyData?.GateAndFence != null && propertyData?.GateAndFence!! >=1)
                    AppPreferences.getInstance().setDropDown(holder.binding.GateFenchEdit,DropDowns.GateAndFence.name,propertyData?.GateAndFence.toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.GateFenchEdit,DropDowns.GateAndFence.name)
                if (propertyData!=null && propertyData?.OtherOperator != null && propertyData?.OtherOperator!! >=1)
                    AppPreferences.getInstance().setDropDown(holder.binding.OtherOperatorsEdit,DropDowns.OtherOperators.name,propertyData?.OtherOperator.toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.OtherOperatorsEdit,DropDowns.OtherOperators.name)
                if (propertyData!=null && propertyData?.PropertyType != null && propertyData?.PropertyType!! >=1)
                    AppPreferences.getInstance().setDropDown(holder.binding.PropertyTypeEdit,DropDowns.PropertyType.name,propertyData?.PropertyType.toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.PropertyTypeEdit,DropDowns.PropertyType.name)

                if (buildingData!=null && buildingData?.BuildingType?.isNotEmpty()==true)
                    AppPreferences.getInstance().setDropDown(holder.binding.BuildingTypeEdit,DropDowns.Buildingtype.name,buildingData?.BuildingType?.get(0).toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.BuildingTypeEdit,DropDowns.Buildingtype.name)
                if (buildingData!=null && buildingData?.PropertyType != null && buildingData?.PropertyType!! >=1)
                    AppPreferences.getInstance().setDropDown(holder.binding.PropertyType,DropDowns.PropertyType.name,buildingData?.PropertyType.toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.PropertyTypeEdit,DropDowns.PropertyType.name)
                if (buildingData!=null && buildingData?.BuildingBuildType != null && buildingData?.BuildingBuildType!! >=1)
                    AppPreferences.getInstance().setDropDown(holder.binding.BuildingBuildTypeEdit,DropDowns.BuildingBuildType.name,buildingData?.BuildingBuildType.toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.BuildingBuildTypeEdit,DropDowns.BuildingBuildType.name)
                if (landData!=null && landData?.LandType?.isNotEmpty()==true)
                    AppPreferences.getInstance().setDropDown(holder.binding.LandTypeEdit,DropDowns.LandType.name,landData?.LandType?.get(0).toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.LandTypeEdit,DropDowns.LandType.name)
                if (landData!=null && landData?.SoilType?.isNotEmpty()==true)
                    AppPreferences.getInstance().setDropDown(holder.binding.SoilTypeEdit,DropDowns.SoilType.name,landData?.SoilType?.get(0).toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.SoilTypeEdit,DropDowns.SoilType.name)
                if (landData!=null && landData?.Terraintype?.isNotEmpty()==true)
                    AppPreferences.getInstance().setDropDown(holder.binding.TerrainTypeEdit,DropDowns.Terraintype.name,landData?.Terraintype?.get(0).toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.TerrainTypeEdit,DropDowns.Terraintype.name)

                if (landData!=null && landData?.SiteDemarcation != null && landData?.SiteDemarcation!! >=1)
                    AppPreferences.getInstance().setDropDown(holder.binding.SiteDemarcationEdit,DropDowns.SiteDemarcation.name,landData?.SiteDemarcation.toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.SiteDemarcationEdit,DropDowns.SiteDemarcation.name)
                if (landData!=null && landData?.PlotLevelingCondition != null && landData?.PlotLevelingCondition!! >=1)
                    AppPreferences.getInstance().setDropDown(holder.binding.PlotLevelingConditionEdit,DropDowns.PlotLevelingCondition.name,landData?.PlotLevelingCondition.toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.PlotLevelingConditionEdit,DropDowns.PlotLevelingCondition.name)
                if (landData!=null && landData?.LowLyingArea != null && landData?.LowLyingArea!! >=1)
                    AppPreferences.getInstance().setDropDown(holder.binding.LowLyingAreaEdit,DropDowns.YesNoDropdown.name,landData?.LowLyingArea.toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.LowLyingAreaEdit,DropDowns.YesNoDropdown.name)
                holder.binding.update.setOnClickListener {
                    val tempBuildingData=SAcqBuildingDetail()
                    val tempLandData=SAcqLandDetail()
                    val tempPropertyData=SAcqPropertyDetail()
                    val temSiteAddData=SiteAddressData()
                    val temSiteInfoAllData= AllsiteInfoDataModel()
                    temSiteAddData.let {
                        it.address1=holder.binding.ActualCoordiAddressLine1Edit.text.toString()
                        it.address2=holder.binding.ActualCoordiAddressLine2Edit.text.toString()
                        it.locLatitude=holder.binding.ActualCoordiSiteLAtitudeEdit.text.toString()
                        it.locLongitude=holder.binding.ActualCoordiSiteLongitudeEdit.text.toString()
                        it.pincode=holder.binding.ActualCoordiPostalCodeEdit.text.toString()
                        if (siteAdd!=null)
                            it.id=siteAdd?.id
                    }
                    tempBuildingData.let {
                        it.BuildingHeight = holder.binding.BuildingHeightEdit.text.toString()
                        it.NoOfFloors = holder.binding.NoOfFloorEdit.text.toString().toIntOrNull()
                        it.TypicalFloorArea = holder.binding.TypicalFloorAreaEdit.text.toString()
                        it.Length = holder.binding.BuildingAcquisitionAreaLEdit.text.toString()
                        it.Breadth = holder.binding.BuildingAcquisitionAreaBEdit.text.toString()
                        it.AcquisitionArea = holder.binding.BuildingAcquisitionAreaAEdit.text.toString()
                        it.remark = holder.binding.BuildingRemarksEdit.text.toString()
                        it.ConstructionYear = holder.binding.YearOfConstructionEdit.text.toString().toIntOrNull()
                        it.PropertyType = holder.binding.PropertyTypeEdit.selectedValue.id.toIntOrNull()
                        it.BuildingBuildType =holder.binding.BuildingBuildTypeEdit.selectedValue.id.toIntOrNull()
                        it.BuildingType = arrayListOf(holder.binding.BuildingTypeEdit.selectedValue.id.toInt())
                        if (propertyData!=null && buildingData!=null)
                            it.id=buildingData?.id
                    }
                    tempLandData.let {
                        it.Length = holder.binding.LandAcquisitionAreaLEdit.text.toString()
                        it.Breadth = holder.binding.LandAcquisitionAreaBEdit.text.toString()
                        it.AcquisitionArea = holder.binding.LandAcquisitionAreaAEdit.text.toString()
                        it.remark = holder.binding.LandRemarksEdit.text.toString()
                        it.PropertyType = holder.binding.PropertyTypeEdit.selectedValue.id.toIntOrNull()
                        it.SiteDemarcation = holder.binding.SiteDemarcationEdit.selectedValue.id.toIntOrNull()
                        it.PlotLevelingCondition = holder.binding.PlotLevelingConditionEdit.selectedValue.id.toIntOrNull()
                        it.LowLyingArea = holder.binding.LowLyingAreaEdit.selectedValue.id.toIntOrNull()
                        it.LandType = arrayListOf(holder.binding.LandTypeEdit.selectedValue.id.toInt())
                        it.Terraintype = arrayListOf(holder.binding.TerrainTypeEdit.selectedValue.id.toInt())
                        it.SoilType = arrayListOf(holder.binding.SoilTypeEdit.selectedValue.id.toInt())
                        if (propertyData!=null && landData!=null)
                            it.id=landData?.id
                    }
                    tempPropertyData.let {
                        it.Potentialthreat= arrayListOf(holder.binding.PotentialThreatEdit.selectedValue.id.toInt())
                        it.Direction= arrayListOf(holder.binding.SiteAccessDirectionEdit.selectedValue.id.toInt())
                        it.SiteAccessWay= holder.binding.SiteAccessWayEdit.selectedValue.id.toIntOrNull()
                        it.GateAndFence= holder.binding.GateFenchEdit.selectedValue.id.toIntOrNull()
                        it.OtherOperator= holder.binding.OtherOperatorsEdit.selectedValue.id.toIntOrNull()
                        it.PropertyType= holder.binding.PropertyTypeEdit.selectedValue.id.toIntOrNull()
                        it.OtherOperatorName=holder.binding.OtherOperatorNameEdit.text.toString()
                        it.NearbyPoliceStation=holder.binding.NearbyPoliceForceStationEdit.text.toString()
                        it.PsDistance=holder.binding.PoliceDistanceEdit.text.toString()
                        it.PsPhoneNo=holder.binding.PhNumberPoliceEdit.text.toString()
                        it.NearbyFireStation=holder.binding.NearbyFireStationEdit.text.toString()
                        it.FsDistance=holder.binding.DistanceFireStationEdit.text.toString()
                        it.FsPhoneNo=holder.binding.phNoFireStationEdit.text.toString()
                        it.remark=holder.binding.remarksEdit.text.toString()
                        it.SAcqBuildingDetail= arrayListOf(tempBuildingData)
                        it.SAcqLandDetail= arrayListOf(tempLandData)
                        if (propertyData!=null)
                            it.id=propertyData?.id
                    }
                    temSiteInfoAllData.Siteaddress= arrayListOf(temSiteAddData)
                    listener.updateActualAdderessAddress(temSiteInfoAllData)
                    val tempData= AcquisitionSurveyData()
                    if (datalist!=null)
                        tempData.id=datalist?.id
                    tempData.SAcqPropertyDetail= arrayListOf(tempPropertyData)
                    listener.updateItemClicked(tempData)
                }
                holder.binding.BuildingAcquisitionAreaLEdit.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                    override fun afterTextChanged(s: Editable?) {
                        listener.textChangeListner(holder.binding.BuildingAcquisitionAreaLEdit.text.toString().toFloatOrNull(),
                            holder.binding.BuildingAcquisitionAreaBEdit.text.toString().toFloatOrNull(),
                            holder.binding.BuildingAcquisitionAreaAEdit)
                    }
                })
                holder.binding.BuildingAcquisitionAreaBEdit.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                    override fun afterTextChanged(s: Editable?) {
                        listener.textChangeListner(holder.binding.BuildingAcquisitionAreaLEdit.text.toString().toFloatOrNull(),
                            holder.binding.BuildingAcquisitionAreaBEdit.text.toString().toFloatOrNull(),
                            holder.binding.BuildingAcquisitionAreaAEdit)
                    }
                })
                holder.binding.LandAcquisitionAreaLEdit.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                    override fun afterTextChanged(s: Editable?) {
                        listener.textChangeListner(holder.binding.LandAcquisitionAreaLEdit.text.toString().toFloatOrNull(),
                            holder.binding.LandAcquisitionAreaBEdit.text.toString().toFloatOrNull(),
                            holder.binding.LandAcquisitionAreaAEdit)
                    }
                })
                holder.binding.LandAcquisitionAreaBEdit.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                    override fun afterTextChanged(s: Editable?) {
                        listener.textChangeListner(holder.binding.LandAcquisitionAreaLEdit.text.toString().toFloatOrNull(),
                            holder.binding.LandAcquisitionAreaBEdit.text.toString().toFloatOrNull(),
                            holder.binding.LandAcquisitionAreaAEdit)
                    }
                })
                holder.binding.PropertyTypeEdit.itemSelectedListener=object : CustomSpinner.ItemSelectedListener{
                    override fun itemSelected(propertyType: DropDownItem) {
                        when (propertyType.id) {
                            "1" -> {
                                holder.binding.BuildingLayoutView.visibility=View.VISIBLE
                                holder.binding.BuildingLayoutEdit.visibility=View.VISIBLE
                                holder.binding.LandLayoutView.visibility=View.GONE
                                holder.binding.LandLayoutEdit.visibility=View.GONE
                            }
                            "2" -> {
                                holder.binding.BuildingLayoutView.visibility=View.GONE
                                holder.binding.BuildingLayoutEdit.visibility=View.GONE
                                holder.binding.LandLayoutView.visibility=View.VISIBLE
                                holder.binding.LandLayoutEdit.visibility=View.VISIBLE
                            }
                            else -> {
                                holder.binding.BuildingLayoutView.visibility=View.VISIBLE
                                holder.binding.BuildingLayoutEdit.visibility=View.VISIBLE
                                holder.binding.LandLayoutView.visibility=View.VISIBLE
                                holder.binding.LandLayoutEdit.visibility=View.VISIBLE
                            }
                        }
                    }
                }
                holder.binding.ActualCoordiAddressLine2Edit.setOnClickListener {
                    listener.initiateAddressActivity(holder.binding.DistanceFromProposedLocEdit,
                    holder.binding.ActualCoordiAddressLine2Edit,holder.binding.ActualCoordiSiteLAtitudeEdit,
                    holder.binding.ActualCoordiSiteLongitudeEdit,holder.binding.ActualCoordiPostalCodeEdit,
                    holder.binding.SiteLAtitudeEdit.text.toString(),holder.binding.SiteLongitudeEdit.text.toString())
                }
                holder.binding.ActualCoordiSiteLAtitudeEdit.setOnClickListener {
                    listener.initiateAddressActivity(holder.binding.DistanceFromProposedLocEdit,
                        holder.binding.ActualCoordiAddressLine2Edit,holder.binding.ActualCoordiSiteLAtitudeEdit,
                        holder.binding.ActualCoordiSiteLongitudeEdit,holder.binding.ActualCoordiPostalCodeEdit,
                        holder.binding.SiteLAtitudeEdit.text.toString(),holder.binding.SiteLongitudeEdit.text.toString())
                }
                holder.binding.ActualCoordiSiteLongitudeEdit.setOnClickListener {
                    listener.initiateAddressActivity(holder.binding.DistanceFromProposedLocEdit,
                        holder.binding.ActualCoordiAddressLine2Edit,holder.binding.ActualCoordiSiteLAtitudeEdit,
                        holder.binding.ActualCoordiSiteLongitudeEdit,holder.binding.ActualCoordiPostalCodeEdit,
                        holder.binding.SiteLAtitudeEdit.text.toString(),holder.binding.SiteLongitudeEdit.text.toString())
                }
                holder.binding.ActualCoordiPostalCodeEdit.setOnClickListener {
                    listener.initiateAddressActivity(holder.binding.DistanceFromProposedLocEdit,
                        holder.binding.ActualCoordiAddressLine2Edit,holder.binding.ActualCoordiSiteLAtitudeEdit,
                        holder.binding.ActualCoordiSiteLongitudeEdit,holder.binding.ActualCoordiPostalCodeEdit,
                        holder.binding.SiteLAtitudeEdit.text.toString(),holder.binding.SiteLongitudeEdit.text.toString())
                }
            }
            is ViewHold2 -> {
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE


                }
                else {
                    holder.binding.collapsingLayout.tag = false
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                    holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    holder.binding.itemLine.visibility = View.VISIBLE
                    holder.binding.itemCollapse.visibility = View.GONE
                }
                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
                holder.binding.itemTitleStr.text = list[position]
                try {
                    if (datalist!=null && datalist?.SAcqExternalStructure?.isNotEmpty()==true){
                        val ExtStrData: SAcqExternalStructure? =datalist?.SAcqExternalStructure?.get(0)
                        holder.insidePremisesTableList.adapter=InsidePremisesTableAdapter(baseFragment.requireContext(),listener,ExtStrData?.SAcqInsidePremise)
                        holder.outsidePremisesTableList.adapter=OutsidePremisesTableAdapter(baseFragment.requireContext(),listener,ExtStrData?.SAcqOutsidePremise)
                    }
                    else{
                        holder.insidePremisesTableList.adapter=InsidePremisesTableAdapter(baseFragment.requireContext(),listener,ArrayList())
                        holder.outsidePremisesTableList.adapter=OutsidePremisesTableAdapter(baseFragment.requireContext(),listener,ArrayList())
                    }
                }catch (e:java.lang.Exception){
                    AppLogger.log("ToewerInfoadapter error : ${e.localizedMessage}")
                }

            }
            is ViewHold3 -> {
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                    holder.binding.imgEdit.visibility = View.VISIBLE
                    holder.binding.viewLayout.visibility = View.VISIBLE
                    holder.binding.editLayout.visibility = View.GONE

                    holder.binding.imgEdit.setOnClickListener {
                        if (AppController.getInstance().isTaskEditable) {
                            holder.binding.viewLayout.visibility = View.GONE
                            holder.binding.editLayout.visibility = View.VISIBLE
                        }
                    }
                    holder.binding.cancel.setOnClickListener {
                        holder.binding.viewLayout.visibility = View.VISIBLE
                        holder.binding.editLayout.visibility = View.GONE
                    }
                }
                else {
                    holder.binding.collapsingLayout.tag = false
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                    holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    holder.binding.itemLine.visibility = View.VISIBLE
                    holder.binding.itemCollapse.visibility = View.GONE
                    holder.binding.imgEdit.visibility = View.GONE
                }
                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
                holder.binding.itemTitleStr.text = list[position]
                if (datalist!=null && datalist?.SAcqPowerConnectionFeasibility?.isNotEmpty()==true){
                    powerData=datalist?.SAcqPowerConnectionFeasibility?.get(0)
                }
                if (powerData!=null){
                    // view mode
                    if (powerData?.EBApplicationStatus!=null && powerData?.EBApplicationStatus!! >=1)
                        AppPreferences.getInstance().setDropDown(holder.binding.EBApplicationStatus,DropDowns.EBApplicationStatus.name,powerData?.EBApplicationStatus.toString())
                    if (powerData?.MeterType!=null && powerData?.MeterType!! >=1)
                        AppPreferences.getInstance().setDropDown(holder.binding.MeterType,DropDowns.MeterType.name,powerData?.MeterType.toString())
                    if (powerData?.EBAvailability!=null && powerData?.EBAvailability!! >=1)
                        AppPreferences.getInstance().setDropDown(holder.binding.EBPowerAvailability,DropDowns.EBPowerAvailability.name,powerData?.EBAvailability.toString())
                    if (powerData?.SolarFeasibility!=null && powerData?.SolarFeasibility!! >=1)
                        AppPreferences.getInstance().setDropDown(holder.binding.SolarFeability,DropDowns.SolarFeasibility.name,powerData?.SolarFeasibility.toString())
                    holder.binding.PowerSupplierName.text= powerData?.PowerSupplier
                    holder.binding.AvgAvailibillity.text= powerData?.AvgAvailability
                    holder.binding.ConsumerNo.text= powerData?.ConsumerNo
                    holder.binding.MeterSerialNumber.text= powerData?.MeterSerialNo
                    holder.binding.NearestEBPole.text= powerData?.NearestEBPole
                    holder.binding.PowerConnRating.text= powerData?.PowerRating
                    holder.binding.remarks.text= powerData?.remark

                    //edit mode
                    holder.binding.PowerSupplierNameEdit.setText(powerData?.PowerSupplier)
                    holder.binding.AvgAvailibillityEdit.setText(powerData?.AvgAvailability)
                    holder.binding.ConsumerNoEdit.setText(powerData?.ConsumerNo)
                    holder.binding.MeterSerialNumberEdit.setText(powerData?.MeterSerialNo)
                    holder.binding.NearestEBPoleEdit.setText(powerData?.NearestEBPole)
                    holder.binding.PowerConnRatingEdit.setText(powerData?.PowerRating)
                    holder.binding.remarksEdit.setText(powerData?.remark)

                }

                if (powerData!=null && powerData?.EBApplicationStatus!=null && powerData?.EBApplicationStatus!! >=1)
                    AppPreferences.getInstance().setDropDown(holder.binding.EBApplicationStatusEdit,DropDowns.EBApplicationStatus.name,powerData?.EBApplicationStatus.toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.EBApplicationStatusEdit,DropDowns.EBApplicationStatus.name)
                if (powerData!=null && powerData?.MeterType!=null && powerData?.MeterType!! >=1)
                    AppPreferences.getInstance().setDropDown(holder.binding.MeterTypeEdit,DropDowns.MeterType.name,powerData?.MeterType.toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.MeterTypeEdit,DropDowns.MeterType.name)
                if (powerData!=null && powerData?.EBAvailability!=null && powerData?.EBAvailability!! >=1)
                    AppPreferences.getInstance().setDropDown(holder.binding.EBPowerAvailabilityEdit,DropDowns.EBPowerAvailability.name,powerData?.EBAvailability.toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.EBPowerAvailabilityEdit,DropDowns.EBPowerAvailability.name)
                if (powerData!=null && powerData?.SolarFeasibility!=null && powerData?.SolarFeasibility!! >=1)
                    AppPreferences.getInstance().setDropDown(holder.binding.SolarFeasibilityEdit,DropDowns.SolarFeasibility.name,powerData?.SolarFeasibility.toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.SolarFeasibilityEdit,DropDowns.SolarFeasibility.name)

                holder.binding.update.setOnClickListener {
                    val tempPowerData=SAcqPowerConnectionFeasibility()
                    tempPowerData.let {
                        it.PowerSupplier= holder.binding.PowerSupplierNameEdit.text.toString()
                        it.AvgAvailability= holder.binding.AvgAvailibillityEdit.text.toString()
                        it.ConsumerNo= holder.binding.ConsumerNoEdit.text.toString()
                        it.MeterSerialNo= holder.binding.MeterSerialNumberEdit.text.toString()
                        it.NearestEBPole= holder.binding.NearestEBPoleEdit.text.toString()
                        it.PowerRating= holder.binding.PowerConnRatingEdit.text.toString()
                        it.remark= holder.binding.remarksEdit.text.toString()
                        it.EBAvailability=holder.binding.EBPowerAvailabilityEdit.selectedValue.id.toIntOrNull()
                        it.EBApplicationStatus=holder.binding.EBApplicationStatusEdit.selectedValue.id.toIntOrNull()
                        it.MeterType=holder.binding.MeterTypeEdit.selectedValue.id.toIntOrNull()
                        it.SolarFeasibility=holder.binding.SolarFeasibilityEdit.selectedValue.id.toIntOrNull()
                        if (powerData!=null)
                            it.id=powerData?.id

                        val tempData= AcquisitionSurveyData()
                        tempData.SAcqPowerConnectionFeasibility= arrayListOf(it)
                        if (datalist!=null)
                            tempData.id=datalist?.id
                        listener.updateItemClicked(tempData)
                    }
                }

            }
            is ViewHold4 -> {
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
                    if (datalist!=null){
                        holder.ownerTableList.adapter=PropertyOwnerTableAdapter(baseFragment.requireContext(),listener,datalist?.SAcqPropertyOwnerDetail)
                    }
                    else{
                        holder.ownerTableList.adapter=PropertyOwnerTableAdapter(baseFragment.requireContext(),listener,
                            ArrayList()
                        )
                    }
                }catch (e:java.lang.Exception){
                    AppLogger.log("ToewerInfoadapter error : ${e.localizedMessage}")
                }

            }
            is ViewHold5 -> {
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                    holder.binding.imgEdit.visibility = View.VISIBLE
                    holder.binding.viewLayout.visibility = View.VISIBLE
                    holder.binding.editLayout.visibility = View.GONE

                    holder.binding.imgEdit.setOnClickListener {
                        if (AppController.getInstance().isTaskEditable) {
                            holder.binding.viewLayout.visibility = View.GONE
                            holder.binding.editLayout.visibility = View.VISIBLE
                        }
                    }
                    holder.binding.cancel.setOnClickListener {
                        holder.binding.viewLayout.visibility = View.VISIBLE
                        holder.binding.editLayout.visibility = View.GONE
                    }
                }
                else {
                    holder.binding.collapsingLayout.tag = false
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                    holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    holder.binding.itemLine.visibility = View.VISIBLE
                    holder.binding.itemCollapse.visibility = View.GONE
                    holder.binding.imgEdit.visibility = View.GONE
                }
                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
                holder.binding.itemTitleStr.text = list[position]
                if (datalist!=null && datalist?.SAcqFeasibilityDetail?.isNotEmpty()==true){
                    feasibilityData=datalist?.SAcqFeasibilityDetail?.get(0)
                }
                if (feasibilityData!=null && feasibilityData?.SAcqLocationMarking!=null){
                    holder.locationMarkingTableList.adapter=LocationMarkingTableAdapter(baseFragment.requireContext(),listener,feasibilityData?.SAcqLocationMarking)
                }
                else{
                    holder.locationMarkingTableList.adapter=OutsidePremisesTableAdapter(baseFragment.requireContext(),listener,ArrayList())
                }
                if (feasibilityData!=null){
                    // view mode
                    if (feasibilityData?.TowerPoleType?.isNotEmpty() == true)
                        AppPreferences.getInstance().setDropDown(holder.binding.TowerPoleType,DropDowns.TowerPoleType.name,feasibilityData?.TowerPoleType?.get(0).toString())
                    if (feasibilityData?.FiberLMCLaying!= null && feasibilityData?.FiberLMCLaying!!>=1)
                        AppPreferences.getInstance().setDropDown(holder.binding.FiberLMCLaying,DropDowns.FiberLMCLaying.name,feasibilityData?.FiberLMCLaying.toString())
                    if (feasibilityData?.OwnerMeter!= null && feasibilityData?.OwnerMeter!!>=1)
                        AppPreferences.getInstance().setDropDown(holder.binding.EBSupplyThroOwnerMeter,DropDowns.EBSupplyThroOwnerMeter.name,feasibilityData?.OwnerMeter.toString())
                    if (feasibilityData?.EquipmentRoom!= null && feasibilityData?.EquipmentRoom!!>=1)
                        AppPreferences.getInstance().setDropDown(holder.binding.EquipmentRoom,DropDowns.EquipmentRoom.name,feasibilityData?.EquipmentRoom.toString())
                    if (feasibilityData?.RequiredAreaAvailable!= null && feasibilityData?.RequiredAreaAvailable!!>=1)
                        AppPreferences.getInstance().setDropDown(holder.binding.RequiredAreaAvailable,DropDowns.RequiredAreaAvailable.name,feasibilityData?.RequiredAreaAvailable.toString())
                    if (feasibilityData?.StatutoryPermission!= null && feasibilityData?.StatutoryPermission!!>=1)
                        AppPreferences.getInstance().setDropDown(holder.binding.StatutoryPermissions,DropDowns.StatutoryPermissions.name,feasibilityData?.StatutoryPermission.toString())
                    if (feasibilityData?.OverallFeasibility!= null && feasibilityData?.OverallFeasibility!!>=1)
                        AppPreferences.getInstance().setDropDown(holder.binding.OverallFeasibility,DropDowns.OverallFeasibility.name,feasibilityData?.OverallFeasibility.toString())

                    holder.binding.ExpectedPrice.text=feasibilityData?.ExpectedPrice
                    holder.binding.AverageMarketRate.text=feasibilityData?.MarketPrice
                    holder.binding.SurveyExecutiveName.text=feasibilityData?.ExecutiveName.toString()
                    holder.binding.SurveyDate.text=Utils.getFormatedDate(feasibilityData?.SurveyDate,"dd-MMM-yyyy")
                    holder.binding.remarks.text=feasibilityData?.remark

                    // edit mode
                    if (buildingData!=null && landData!=null){
                        if (buildingData?.AcquisitionArea?.toFloatOrNull()!=null && landData?.AcquisitionArea?.toFloatOrNull()!=null){
                            holder.binding.TotalAcquisitionAreaEdit.text=(buildingData?.AcquisitionArea?.toFloatOrNull()?.plus(landData?.AcquisitionArea!!.toFloat())).toString()
                            holder.binding.TotalAcquisitionArea.text=(buildingData?.AcquisitionArea?.toFloatOrNull()?.plus(landData?.AcquisitionArea!!.toFloat())).toString()
                        }
                        else if (buildingData?.AcquisitionArea?.toFloatOrNull()!=null){
                            holder.binding.TotalAcquisitionAreaEdit.text=buildingData?.AcquisitionArea
                            holder.binding.TotalAcquisitionArea.text=buildingData?.AcquisitionArea
                        }
                        else if (landData?.AcquisitionArea?.toFloatOrNull()!=null){
                            holder.binding.TotalAcquisitionAreaEdit.text=landData?.AcquisitionArea
                            holder.binding.TotalAcquisitionArea.text=landData?.AcquisitionArea
                        }
                    }
                    else if (buildingData!=null){
                        if (buildingData?.AcquisitionArea?.toFloatOrNull()!=null){
                            holder.binding.TotalAcquisitionAreaEdit.text=buildingData?.AcquisitionArea
                            holder.binding.TotalAcquisitionArea.text=buildingData?.AcquisitionArea
                        }
                    }
                    else if (landData!=null){
                        if (landData?.AcquisitionArea?.toFloatOrNull()!=null){
                            holder.binding.TotalAcquisitionAreaEdit.text=landData?.AcquisitionArea
                            holder.binding.TotalAcquisitionArea.text=landData?.AcquisitionArea
                        }
                    }
                    holder.binding.ExpectedPriceEdit.setText(feasibilityData?.ExpectedPrice)
                    holder.binding.AverageMarketRateEdit.setText(feasibilityData?.MarketPrice)
                    holder.binding.SurveyExecutiveNameEdit.setText(feasibilityData?.ExecutiveName)
                    holder.binding.SurveyDateEdit.text=Utils.getFormatedDate(feasibilityData?.SurveyDate,"dd-MMM-yyyy")
                    holder.binding.remarksEdit.setText(feasibilityData?.remark)


                }

                if (feasibilityData!=null && feasibilityData?.TowerPoleType?.isNotEmpty()==true)
                    AppPreferences.getInstance().setDropDown(holder.binding.TowerPoleTypeEdit,DropDowns.TowerPoleType.name,feasibilityData?.TowerPoleType?.get(0).toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.TowerPoleTypeEdit,DropDowns.TowerPoleType.name)
                if (feasibilityData!=null && feasibilityData?.FiberLMCLaying!= null && feasibilityData?.FiberLMCLaying!!>=1)
                    AppPreferences.getInstance().setDropDown(holder.binding.FiberLMCLayingEdit,DropDowns.FiberLMCLaying.name,feasibilityData?.FiberLMCLaying.toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.FiberLMCLayingEdit,DropDowns.FiberLMCLaying.name)
                if (feasibilityData!=null && feasibilityData?.OwnerMeter!= null && feasibilityData?.OwnerMeter!!>=1)
                    AppPreferences.getInstance().setDropDown(holder.binding.EBSupplyThroOwnerMeterEdit,DropDowns.EBSupplyThroOwnerMeter.name,feasibilityData?.OwnerMeter.toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.EBSupplyThroOwnerMeterEdit,DropDowns.EBSupplyThroOwnerMeter.name)
                if (feasibilityData!=null && feasibilityData?.EquipmentRoom!= null && feasibilityData?.EquipmentRoom!!>=1)
                    AppPreferences.getInstance().setDropDown(holder.binding.EquipmentRoomEdit,DropDowns.EquipmentRoom.name,feasibilityData?.EquipmentRoom.toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.EquipmentRoomEdit,DropDowns.EquipmentRoom.name)
                if (feasibilityData!=null && feasibilityData?.RequiredAreaAvailable!= null && feasibilityData?.RequiredAreaAvailable!!>=1)
                    AppPreferences.getInstance().setDropDown(holder.binding.RequiredAreaAvailableEdit,DropDowns.RequiredAreaAvailable.name,feasibilityData?.RequiredAreaAvailable.toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.RequiredAreaAvailableEdit,DropDowns.RequiredAreaAvailable.name)
                if (feasibilityData!=null && feasibilityData?.StatutoryPermission!= null && feasibilityData?.StatutoryPermission!!>=1)
                    AppPreferences.getInstance().setDropDown(holder.binding.StatutoryPermissionsEdit,DropDowns.StatutoryPermissions.name,feasibilityData?.StatutoryPermission.toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.StatutoryPermissionsEdit,DropDowns.StatutoryPermissions.name)
                if (feasibilityData!=null && feasibilityData?.OverallFeasibility!= null && feasibilityData?.OverallFeasibility!!>=1)
                    AppPreferences.getInstance().setDropDown(holder.binding.OverallFeasibilityEdit,DropDowns.OverallFeasibility.name,feasibilityData?.OverallFeasibility.toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.OverallFeasibilityEdit,DropDowns.OverallFeasibility.name)


                baseFragment.setDatePickerView(holder.binding.SurveyDateEdit)
                holder.binding.update.setOnClickListener {
                    val tempfeasibilityData=SAcqFeasibilityDetail()
                    tempfeasibilityData.let {
                        if (holder.binding.TotalAcquisitionAreaEdit.text.toString().toIntOrNull()!=null)
                            it.Area = holder.binding.TotalAcquisitionAreaEdit.text.toString()
                        it.ExpectedPrice = holder.binding.ExpectedPriceEdit.text.toString()
                        it.MarketPrice = holder.binding.AverageMarketRateEdit.text.toString()
                        it.ExecutiveName = holder.binding.SurveyExecutiveNameEdit.text.toString()
                        it.remark = holder.binding.remarksEdit.text.toString()
                        it.SurveyDate = Utils.getFullFormatedDate(holder.binding.SurveyDateEdit.text.toString())
                        it.TowerPoleType = arrayListOf(holder.binding.TowerPoleTypeEdit.selectedValue.id.toInt())
                        it.FiberLMCLaying = holder.binding.FiberLMCLayingEdit.selectedValue.id.toIntOrNull()
                        it.OwnerMeter = holder.binding.EBSupplyThroOwnerMeterEdit.selectedValue.id.toIntOrNull()
                        it.EquipmentRoom = holder.binding.EquipmentRoomEdit.selectedValue.id.toIntOrNull()
                        it.RequiredAreaAvailable = holder.binding.RequiredAreaAvailableEdit.selectedValue.id.toIntOrNull()
                        it.StatutoryPermission = holder.binding.StatutoryPermissionsEdit.selectedValue.id.toIntOrNull()
                        it.OverallFeasibility = holder.binding.OverallFeasibilityEdit.selectedValue.id.toIntOrNull()
                        if (feasibilityData != null)
                            it.id = feasibilityData?.id

                        val tempData = AcquisitionSurveyData()
                        tempData.SAcqFeasibilityDetail = arrayListOf(it)
                        if (datalist != null)
                            tempData.id = datalist?.id
                        listener.updateItemClicked(tempData)
                    }
                }

            }
            is ViewHold6 -> {
                var attachmentsList:ArrayList<AttachmentsConditions> ?=ArrayList()
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                }
                else {
                    holder.binding.collapsingLayout.tag = false
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                    holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    holder.binding.itemLine.visibility = View.VISIBLE
                    holder.binding.itemCollapse.visibility = View.GONE
                }
                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
                holder.binding.itemTitleStr.text = list[position]
                try {
                    if (AppController.getInstance().attachmentsConditionsList.Attachment!=null){
                        attachmentsList=AppController.getInstance().attachmentsConditionsList.Attachment
                    }

                    if (datalist!=null){
                        holder.recyclerListener.adapter= AttachmentConditionalAdapter(baseFragment.requireContext(),datalist?.attachment,
                            attachmentsList!!,22,datalist?.id,
                            object : AttachmentConditionalAdapter.AttachmentConditionsListener{
                                override fun attachmentItemClicked(item: Attachments) {
                                    listener.attachmentItemClicked(item)
                                }
                                override fun addAttachmentItemClicked(categoryId:Int) {
                                    listener.addAttachment(categoryId)
                                }
                        })
                    }
                    else {
                        holder.recyclerListener.adapter= AttachmentConditionalAdapter(baseFragment.requireContext(),
                            ArrayList(),
                            attachmentsList!!,22,null,
                            object : AttachmentConditionalAdapter.AttachmentConditionsListener{
                                override fun attachmentItemClicked(item: Attachments) {
                                    listener.attachmentItemClicked(item)
                                }
                                override fun addAttachmentItemClicked(categoryId:Int) {
                                    listener.addAttachment(categoryId)
                                }
                            })
                    }
                }catch (e:java.lang.Exception){
                    AppLogger.log("Acq Survey error : ${e.localizedMessage}")
                }
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



    interface AcqSurveyListListener {
       fun attachmentItemClicked(item: Attachments)
       fun addAttachment(categoryId:Int)
       fun viewInsidePremisesClicked(position: Int,data:SAcqInsidePremise)
       fun editInsidePremisesClicked(position: Int,data:SAcqInsidePremise)
       fun viewOutsidePremisesClicked(position: Int,data:SAcqOutsidePremise)
       fun editOutsidePremisesClicked(position: Int,data:SAcqOutsidePremise)
       fun viewPropertyOwnerClicked(position: Int,data:SAcqPropertyOwnerDetail)
       fun editPropertyOwnerClicked(position: Int,data:SAcqPropertyOwnerDetail)
       fun editLocationMarkingClicked(position: Int,data:SAcqLocationMarking)
       fun viewLocationMarkingClicked(position: Int,data:SAcqLocationMarking)
       fun textChangeListner(data1: Float?,data2: Float?,textview:TextView)
       fun updateItemClicked(data:AcquisitionSurveyData)
        fun updateActualAdderessAddress(data:AllsiteInfoDataModel?)
        fun initiateAddressActivity(distance:TextView?,address2:TextView?,siteLat:TextView,siteLong:TextView,postalCode:TextView,nominalSiteLat:String?,nominalSiteLong:String?)

    }

}
