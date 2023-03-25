package com.smarthub.baseapplication.ui.fragments.siteAcquisition.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.*
import com.smarthub.baseapplication.model.siteIBoard.newSiteInfoDataModel.SiteAddressData
import com.smarthub.baseapplication.ui.fragments.ImageAttachmentCommonAdapter
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.tableAdapters.InsidePremisesTableAdapter
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.tableAdapters.OutsidePremisesTableAdapter
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.tableAdapters.PropertyOwnerTableAdapter
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class AcqSurveyFragAdapter(var baseFragment: BaseFragment, var listener: AcqSurveyListListener, data:NewSiteAcquiAllData?) : RecyclerView.Adapter<AcqSurveyFragAdapter.ViewHold>() {
    private var datalist: AcquisitionSurveyData?=null
    private var feasibilityData: SAcqFeasibilityDetail?=null
    private var powerData: SAcqPowerConnectionFeasibility?=null
    private var propertyData: SAcqPropertyDetail?=null
    private var buildingData: SAcqBuildingDetail?=null
    private var landData: SAcqLandDetail ?=null
    private var siteAdd:SiteAddressData ?=null


    fun setData(data: AcquisitionSurveyData?) {
        this.datalist=data!!
        notifyDataSetChanged()
    }
    init {
        try {
            if (data!=null && data.SAcqAssignACQTeam?.isNotEmpty()!!){
                datalist=data.SAcqAcquitionSurvey?.get(0)
            }
        }catch (e:java.lang.Exception){
            AppLogger.log("TowerInfoFrag error :${e.localizedMessage}")
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
    class ViewHold6(itemView: View,listener: AcqSurveyListListener) : ViewHold(itemView) {
        var binding: TowerAttachmentInfoBinding = TowerAttachmentInfoBinding.bind(itemView)
        val recyclerListener:RecyclerView = binding.root.findViewById(R.id.list_item)

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
                view = LayoutInflater.from(parent.context).inflate(R.layout.tower_attachment_info, parent, false)
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
                        holder.binding.viewLayout.visibility = View.GONE
                        holder.binding.editLayout.visibility = View.VISIBLE
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
                try {
                    if (AppController.getInstance().newSiteInfoModel.Siteaddress?.isNotEmpty()==true){
                        siteAdd=AppController.getInstance().newSiteInfoModel.Siteaddress?.get(0)
                    }
                    if (datalist!=null && datalist?.SAcqPropertyDetail?.isNotEmpty()==true){
                        propertyData=datalist?.SAcqPropertyDetail?.get(0)
                        if (propertyData!=null && propertyData?.SAcqBuildingDetail?.isNotEmpty()==true){
                            buildingData=propertyData?.SAcqBuildingDetail?.get(0)
                        }
                        if (propertyData!=null && propertyData?.SAcqLandDetail?.isNotEmpty()==true){
                            landData=propertyData?.SAcqLandDetail?.get(0)
                        }
                    }
                    if (siteAdd!=null){
                        // view Mode
                        holder.binding.AddressLine1.text=siteAdd?.address1
                        holder.binding.AddressLine2.text=siteAdd?.address2
                        holder.binding.PostalCode.text=siteAdd?.pincode
                        holder.binding.SiteLAtitude.text=siteAdd?.locLatitude
                        holder.binding.SiteLongitude.text=siteAdd?.locLongitude
                        // edit mode
                        holder.binding.AddressLine1Edit.text=siteAdd?.address1
                        holder.binding.AddressLine2Edit.text=siteAdd?.address2
                        holder.binding.PostalCodeEdit.text=siteAdd?.pincode
                        holder.binding.SiteLAtitudeEdit.text=siteAdd?.locLatitude
                        holder.binding.SiteLongitudeEdit.text=siteAdd?.locLongitude
                    }

                    if (propertyData!=null){
                        // view Mode
                        if (propertyData?.Potentialthreat?.isNotEmpty() == true)
                            AppPreferences.getInstance().setDropDown(holder.binding.PotentialThreat,DropDowns.Potentialthreat.name,propertyData?.Potentialthreat?.get(0).toString())
                        if (propertyData?.Direction?.isNotEmpty() == true)
                            AppPreferences.getInstance().setDropDown(holder.binding.SiteAccessDirection,DropDowns.Direction.name,propertyData?.Direction?.get(0).toString())
                        holder.binding.SiteAccessWay.text=propertyData?.SiteAccessWay.toString()
                        holder.binding.GateAndFence.text=propertyData?.GateAndFence.toString()
                        holder.binding.OtherOperators.text=propertyData?.OtherOperator.toString()
                        holder.binding.OtherOperatorNames.text=propertyData?.OtherOperatorName
                        holder.binding.NearbyPoliceForceStation.text=propertyData?.NearbyPoliceStation
                        holder.binding.NearbyFireStation.text=propertyData?.NearbyFireStation
                        holder.binding.PoliceDistance.text=propertyData?.PsDistance
                        holder.binding.DistanceFireStation.text=propertyData?.FsDistance
                        holder.binding.PhnumberPolice.text=propertyData?.PsPhoneNo
                        holder.binding.PhNumberFireStation.text=propertyData?.FsPhoneNo
                        holder.binding.remarks.text=propertyData?.Remark

                        // edit Mode
                        holder.binding.SiteAccessWayEdit.text=propertyData?.SiteAccessWay.toString()
                        holder.binding.GateFenchEdit.text=propertyData?.GateAndFence.toString()
                        holder.binding.OtherOperatorsEdit.text=propertyData?.OtherOperator.toString()
                        holder.binding.OtherOperatorNameEdit.setText(propertyData?.OtherOperatorName)
                        holder.binding.NearbyPoliceForceStationEdit.setText(propertyData?.NearbyPoliceStation)
                        holder.binding.NearbyFireStationEdit.setText(propertyData?.NearbyFireStation)
                        holder.binding.PoliceDistanceEdit.setText(propertyData?.PsDistance)
                        holder.binding.DistanceFireStationEdit.setText(propertyData?.FsDistance)
                        holder.binding.PhNumberPoliceEdit.setText(propertyData?.PsPhoneNo)
                        holder.binding.phNoFireStationEdit.setText(propertyData?.FsPhoneNo)
                        holder.binding.remarksEdit.setText(propertyData?.Remark)

                        if (buildingData!=null){
                            // view mode
                            if (buildingData?.BuildingType?.isNotEmpty()==true)
                                AppPreferences.getInstance().setDropDown(holder.binding.BuildingType,DropDowns.Buildingtype.name,buildingData?.BuildingType?.get(0).toString())
                            holder.binding.buildingPropertyType.text=buildingData?.PropertyType.toString()
                            holder.binding.BuildingBuildType.text=buildingData?.BuildingBuildType.toString()
                            holder.binding.BuildingHeight.text=buildingData?.BuildingHeight
                            holder.binding.TypicalFloorArea.text=buildingData?.TypicalFloorArea
                            holder.binding.YearofConstruction.text=buildingData?.ConstructionYear.toString()
                            holder.binding.NoofFloors.text=buildingData?.NoOfFloors.toString()

                            // edit mode
                            holder.binding.PropertyTypeBuildingEdit.text=buildingData?.PropertyType.toString()
                            holder.binding.BuildingBuildTypeEdit.text=buildingData?.BuildingBuildType.toString()
                            holder.binding.BuildingHeightEdit.setText(buildingData?.BuildingHeight)
                            holder.binding.TypicalFloorAreaEdit.setText(buildingData?.TypicalFloorArea)
                            holder.binding.YearOfConstructionEdit.setText(buildingData?.ConstructionYear!!)
                            holder.binding.NoOfFloorEdit.setText(buildingData?.NoOfFloors.toString())
                        }
                        else
                            AppLogger.log("error in Property details data or Building Details Data")

                        if (landData!=null){
                            //View Mode
                            if (landData?.LandType?.isNotEmpty()==true)
                                AppPreferences.getInstance().setDropDown(holder.binding.LandType,DropDowns.LandType.name, landData?.LandType?.get(0).toString())
                            if (landData?.SoilType?.isNotEmpty()==true)
                                AppPreferences.getInstance().setDropDown(holder.binding.SoilType,DropDowns.SoilType.name, landData?.SoilType?.get(0).toString())
                            if (landData?.Terraintype?.isNotEmpty()==true)
                                AppPreferences.getInstance().setDropDown(holder.binding.TerrainType,DropDowns.Terraintype.name, landData?.Terraintype?.get(0).toString())

                            holder.binding.landPropertyType.text=landData?.PropertyType.toString()
                            holder.binding.SiteDemarcation.text=landData?.SiteDemarcation.toString()

                            //Edit Mode
                            holder.binding.PropertyTypeLandEdit.text=landData?.PropertyType.toString()
                            holder.binding.SiteDemarcationEdit.text=landData?.SiteDemarcation.toString()
                        }
                        else
                            AppLogger.log("error in Property details data or Building Details Data")


                    }
                    else
                        AppLogger.log("error in Acquisition Survey data or Property details data")

                }catch (e:java.lang.Exception){
                    AppLogger.log("AcqSurvey Property Details error : ${e.localizedMessage}")
                }
                if (propertyData!=null && propertyData?.Potentialthreat?.isNotEmpty() == true)
                    AppPreferences.getInstance().setDropDown(holder.binding.PotentialThreatEdit,DropDowns.Potentialthreat.name,propertyData?.Potentialthreat?.get(0).toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.PotentialThreatEdit,DropDowns.Potentialthreat.name)
                if (propertyData!=null && propertyData?.Direction?.isNotEmpty() == true)
                    AppPreferences.getInstance().setDropDown(holder.binding.SiteAccessDirectionEdit,DropDowns.Direction.name,propertyData?.Direction?.get(0).toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.SiteAccessDirectionEdit,DropDowns.Direction.name)
                if (buildingData!=null && buildingData?.BuildingType?.isNotEmpty()==true)
                    AppPreferences.getInstance().setDropDown(holder.binding.BuildingTypeEdit,DropDowns.Buildingtype.name,buildingData?.BuildingType?.get(0).toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.BuildingTypeEdit,DropDowns.Buildingtype.name)
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
                holder.binding.update.setOnClickListener {
                    try {
                        if (propertyData!=null){
                            val tempBuildingData=SAcqBuildingDetail()
                            val tempLandData=SAcqLandDetail()
                            tempBuildingData.let {
                                it.BuildingHeight = holder.binding.BuildingHeightEdit.text.toString()
                                it.NoOfFloors = holder.binding.NoOfFloorEdit.text.toString().toInt()
                                it.TypicalFloorArea = holder.binding.TypicalFloorAreaEdit.text.toString()
                                it.ConstructionYear = holder.binding.YearOfConstructionEdit.text.toString().toInt()
                                it.PropertyType = 0
                                it.BuildingBuildType = 0
                                it.BuildingType = arrayListOf(holder.binding.BuildingTypeEdit.selectedValue.id.toInt())
                                if (propertyData?.SAcqBuildingDetail?.isNotEmpty()==true)
                                    it.id=propertyData?.SAcqBuildingDetail?.get(0)?.id
                            }
                            tempLandData.let {
                                it.PropertyType = 0
                                it.SiteDemarcation = 0
                                it.LandType = arrayListOf(holder.binding.LandTypeEdit.selectedValue.id.toInt())
                                it.Terraintype = arrayListOf(holder.binding.TerrainTypeEdit.selectedValue.id.toInt())
                                it.SoilType = arrayListOf(holder.binding.SoilTypeEdit.selectedValue.id.toInt())
                                if (propertyData?.SAcqLandDetail?.isNotEmpty()==true)
                                    it.id=propertyData?.SAcqLandDetail?.get(0)?.id
                            }
                            propertyData?.let {
                                it.Potentialthreat= arrayListOf(holder.binding.PotentialThreatEdit.selectedValue.id.toInt())
                                it.Direction= arrayListOf(holder.binding.SiteAccessDirectionEdit.selectedValue.id.toInt())
                                it.OtherOperatorName=holder.binding.OtherOperatorNameEdit.text.toString()
                                it.NearbyPoliceStation=holder.binding.NearbyPoliceForceStationEdit.text.toString()
                                it.PsDistance=holder.binding.PoliceDistanceEdit.text.toString()
                                it.PsPhoneNo=holder.binding.PhNumberPoliceEdit.text.toString()
                                it.NearbyFireStation=holder.binding.NearbyFireStationEdit.text.toString()
                                it.FsDistance=holder.binding.DistanceFireStationEdit.text.toString()
                                it.FsPhoneNo=holder.binding.phNoFireStationEdit.text.toString()
                                it.Remark=holder.binding.remarksEdit.text.toString()
                                it.SAcqBuildingDetail= arrayListOf(tempBuildingData)
                                it.SAcqLandDetail= arrayListOf(tempLandData)
                                if (AppController.getInstance().newSiteInfoModel.Siteaddress?.isNotEmpty()==true)
                                    it.Siteaddress= arrayListOf(AppController.getInstance().newSiteInfoModel.Siteaddress?.get(0)?.id!!.toInt())
                                else
                                    it.Siteaddress= arrayListOf()
                                val tempList:ArrayList<SAcqPropertyDetail> = ArrayList()
                                tempList.clear()
                                tempList.add(it)
                                val tempData= AcquisitionSurveyData()
                                tempData.SAcqPropertyDetail=tempList
                                tempData.id= datalist?.id
                                listener.updateItemClicked(tempData)
                            }

                        }
                        else if(datalist!=null && datalist?.SAcqPropertyDetail?.isEmpty()==true){
                            val tempBuildingData=SAcqBuildingDetail()
                            val tempLandData=SAcqLandDetail()
                            val tempPropertyData=SAcqPropertyDetail()
                            tempBuildingData.let {
                                it.BuildingHeight = holder.binding.BuildingHeightEdit.text.toString()
                                it.NoOfFloors = holder.binding.NoOfFloorEdit.text.toString().toInt()
                                it.TypicalFloorArea = holder.binding.TypicalFloorAreaEdit.text.toString()
                                it.ConstructionYear = holder.binding.YearOfConstructionEdit.text.toString().toInt()
                                it.PropertyType = 0
                                it.BuildingBuildType = 0
                                it.BuildingType = arrayListOf(holder.binding.BuildingTypeEdit.selectedValue.id.toInt())
                            }
                            tempLandData.let {
                                it.PropertyType = 0
                                it.SiteDemarcation = 0
                                it.LandType = arrayListOf(holder.binding.LandTypeEdit.selectedValue.id.toInt())
                                it.Terraintype = arrayListOf(holder.binding.TerrainTypeEdit.selectedValue.id.toInt())
                                it.SoilType = arrayListOf(holder.binding.SoilTypeEdit.selectedValue.id.toInt())

                            }
                            tempPropertyData.let {

                                it.Potentialthreat= arrayListOf(holder.binding.PotentialThreatEdit.selectedValue.id.toInt())
                                it.Direction= arrayListOf(holder.binding.SiteAccessDirectionEdit.selectedValue.id.toInt())
                                it.OtherOperatorName=holder.binding.OtherOperatorNameEdit.text.toString()
                                it.NearbyPoliceStation=holder.binding.NearbyPoliceForceStationEdit.text.toString()
                                it.PsDistance=holder.binding.PoliceDistanceEdit.text.toString()
                                it.PsPhoneNo=holder.binding.PhNumberPoliceEdit.text.toString()
                                it.NearbyFireStation=holder.binding.NearbyFireStationEdit.text.toString()
                                it.FsDistance=holder.binding.DistanceFireStationEdit.text.toString()
                                it.FsPhoneNo=holder.binding.phNoFireStationEdit.text.toString()
                                it.Remark=holder.binding.remarksEdit.text.toString()
                                it.SAcqBuildingDetail= arrayListOf(tempBuildingData)
                                it.SAcqLandDetail= arrayListOf(tempLandData)
                                if (AppController.getInstance().newSiteInfoModel.Siteaddress?.isNotEmpty()==true)
                                    it.Siteaddress= arrayListOf(AppController.getInstance().newSiteInfoModel.Siteaddress?.get(0)?.id!!.toInt())
                                else
                                    it.Siteaddress= arrayListOf()
                                val tempList:ArrayList<SAcqPropertyDetail> = ArrayList()
                                tempList.clear()
                                tempList.add(it)
                                val tempData= AcquisitionSurveyData()
                                tempData.SAcqPropertyDetail=tempList
                                tempData.id= datalist?.id
                                listener.updateItemClicked(tempData)
                            }
                        }
                        else{
                            val tempBuildingData=SAcqBuildingDetail()
                            val tempLandData=SAcqLandDetail()
                            val tempPropertyData=SAcqPropertyDetail()
                            tempBuildingData.let {
                                it.BuildingHeight = holder.binding.BuildingHeightEdit.text.toString()
                                it.NoOfFloors = holder.binding.NoOfFloorEdit.text.toString().toInt()
                                it.TypicalFloorArea = holder.binding.TypicalFloorAreaEdit.text.toString()
                                it.ConstructionYear = holder.binding.YearOfConstructionEdit.text.toString().toInt()
                                it.PropertyType = 0
                                it.BuildingBuildType = 0
                                it.BuildingType = arrayListOf(holder.binding.BuildingTypeEdit.selectedValue.id.toInt())
                            }
                            tempLandData.let {
                                it.PropertyType = 0
                                it.SiteDemarcation = 0
                                it.LandType = arrayListOf(holder.binding.LandTypeEdit.selectedValue.id.toInt())
                                it.Terraintype = arrayListOf(holder.binding.TerrainTypeEdit.selectedValue.id.toInt())
                                it.SoilType = arrayListOf(holder.binding.SoilTypeEdit.selectedValue.id.toInt())

                            }
                            tempPropertyData.let {

                                it.Potentialthreat= arrayListOf(holder.binding.PotentialThreatEdit.selectedValue.id.toInt())
                                it.Direction= arrayListOf(holder.binding.SiteAccessDirectionEdit.selectedValue.id.toInt())
                                it.OtherOperatorName=holder.binding.OtherOperatorNameEdit.text.toString()
                                it.NearbyPoliceStation=holder.binding.NearbyPoliceForceStationEdit.text.toString()
                                it.PsDistance=holder.binding.PoliceDistanceEdit.text.toString()
                                it.PsPhoneNo=holder.binding.PhNumberPoliceEdit.text.toString()
                                it.NearbyFireStation=holder.binding.NearbyFireStationEdit.text.toString()
                                it.FsDistance=holder.binding.DistanceFireStationEdit.text.toString()
                                it.FsPhoneNo=holder.binding.phNoFireStationEdit.text.toString()
                                it.Remark=holder.binding.remarksEdit.text.toString()
                                it.SAcqBuildingDetail= arrayListOf(tempBuildingData)
                                it.SAcqLandDetail= arrayListOf(tempLandData)
                                if (AppController.getInstance().newSiteInfoModel.Siteaddress?.isNotEmpty()==true)
                                    it.Siteaddress= arrayListOf(AppController.getInstance().newSiteInfoModel.Siteaddress?.get(0)?.id!!.toInt())
                                else
                                    it.Siteaddress= arrayListOf()
                                val tempList:ArrayList<SAcqPropertyDetail> = ArrayList()
                                tempList.clear()
                                tempList.add(it)
                                val tempData= AcquisitionSurveyData()
                                tempData.SAcqPropertyDetail=tempList
                                listener.updateItemClicked(tempData)
                            }
                        }
                    }catch (e:Exception){
                        AppLogger.log("Somthing went wrong during update property details")
                    }

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
                        holder.binding.viewLayout.visibility = View.GONE
                        holder.binding.editLayout.visibility = View.VISIBLE
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
                try {
                    if (datalist!=null && datalist?.SAcqPowerConnectionFeasibility?.isNotEmpty()==true){
                        powerData=datalist?.SAcqPowerConnectionFeasibility?.get(0)
                    }
                    if (powerData!=null){
                        // view mode
                        holder.binding.EBPowerAvailability.text= powerData?.EBAvailability.toString()
                        holder.binding.PowerSupplierName.text= powerData?.PowerSupplier
                        holder.binding.EBApplicationStatus.text= powerData?.EBApplicationStatus.toString()
                        holder.binding.AvgAvailibillity.text= powerData?.AvgAvailability
                        holder.binding.MeterType.text= powerData?.MeterType.toString()
                        holder.binding.ConsumerNo.text= powerData?.ConsumerNo
                        holder.binding.MeterSerialNumber.text= powerData?.MeterSerialNo
                        holder.binding.NearestEBPole.text= powerData?.NearestEBPole
                        holder.binding.PowerConnRating.text= powerData?.PowerRating
                        holder.binding.SolarFeability.text= powerData?.SolarFeasibility.toString()
                        holder.binding.remarks.text= powerData?.Remark

                        //edit mode
                        holder.binding.EBPowerAvailabilityEdit.text= powerData?.EBAvailability.toString()
                        holder.binding.PowerSupplierNameEdit.setText(powerData?.PowerSupplier)
                        holder.binding.EBApplicationStatusEdit.text= powerData?.EBApplicationStatus.toString()
                        holder.binding.AvgAvailibillityEdit.setText(powerData?.AvgAvailability)
                        holder.binding.MeterTypeEdit.text= powerData?.MeterType.toString()
                        holder.binding.ConsumerNoEdit.setText(powerData?.ConsumerNo)
                        holder.binding.MeterSerialNumberEdit.setText(powerData?.MeterSerialNo)
                        holder.binding.NearestEBPoleEdit.setText(powerData?.NearestEBPole)
                        holder.binding.PowerConnRatingEdit.setText(powerData?.PowerRating)
                        holder.binding.SolarFeasibilityEdit.text= powerData?.SolarFeasibility.toString()
                        holder.binding.remarksEdit.setText(powerData?.Remark)

                    }
                    else
                        AppLogger.log("error in Acquisition Survey data or Property details data")
                }catch (e:java.lang.Exception){
                    AppLogger.log("ToewerInfoadapter error : ${e.localizedMessage}")
                }
                holder.binding.update.setOnClickListener {
                    if (powerData!=null){
                        powerData?.let {
                            it.PowerSupplier= holder.binding.PowerSupplierNameEdit.text.toString()
                            it.AvgAvailability= holder.binding.AvgAvailibillityEdit.text.toString()
                            it.ConsumerNo= holder.binding.ConsumerNoEdit.text.toString()
                            it.MeterSerialNo= holder.binding.MeterSerialNumberEdit.text.toString()
                            it.NearestEBPole= holder.binding.NearestEBPoleEdit.text.toString()
                            it.PowerRating= holder.binding.PowerConnRatingEdit.text.toString()
                            it.Remark= holder.binding.remarksEdit.text.toString()

                            val tempList:ArrayList<SAcqPowerConnectionFeasibility> = ArrayList()
                            tempList.clear()
                            tempList.add(it)
                            val tempData= AcquisitionSurveyData()
                            tempData.SAcqPowerConnectionFeasibility=tempList
                            tempData.id= datalist?.id
                            listener.updateItemClicked(tempData)
                        }
                    }
                    else if(datalist!=null && datalist?.SAcqPowerConnectionFeasibility?.isEmpty()==true){
                        val tempPowerData=SAcqPowerConnectionFeasibility()
                        tempPowerData.let {
                            it.PowerSupplier= holder.binding.PowerSupplierNameEdit.text.toString()
                            it.AvgAvailability= holder.binding.AvgAvailibillityEdit.text.toString()
                            it.ConsumerNo= holder.binding.ConsumerNoEdit.text.toString()
                            it.MeterSerialNo= holder.binding.MeterSerialNumberEdit.text.toString()
                            it.NearestEBPole= holder.binding.NearestEBPoleEdit.text.toString()
                            it.PowerRating= holder.binding.PowerConnRatingEdit.text.toString()
                            it.Remark= holder.binding.remarksEdit.text.toString()

                            val tempList:ArrayList<SAcqPowerConnectionFeasibility> = ArrayList()
                            tempList.clear()
                            tempList.add(it)
                            val tempData= AcquisitionSurveyData()
                            tempData.SAcqPowerConnectionFeasibility=tempList
                            tempData.id= datalist?.id
                            listener.updateItemClicked(tempData)
                        }
                    }
                    else{
                        val tempPowerData=SAcqPowerConnectionFeasibility()
                        tempPowerData.let {
                            it.PowerSupplier= holder.binding.PowerSupplierNameEdit.text.toString()
                            it.AvgAvailability= holder.binding.AvgAvailibillityEdit.text.toString()
                            it.ConsumerNo= holder.binding.ConsumerNoEdit.text.toString()
                            it.MeterSerialNo= holder.binding.MeterSerialNumberEdit.text.toString()
                            it.NearestEBPole= holder.binding.NearestEBPoleEdit.text.toString()
                            it.PowerRating= holder.binding.PowerConnRatingEdit.text.toString()
                            it.Remark= holder.binding.remarksEdit.text.toString()

                            val tempList:ArrayList<SAcqPowerConnectionFeasibility> = ArrayList()
                            tempList.clear()
                            tempList.add(it)
                            val tempData= AcquisitionSurveyData()
                            tempData.SAcqPowerConnectionFeasibility=tempList
                            listener.updateItemClicked(tempData)
                        }
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
                        holder.binding.viewLayout.visibility = View.GONE
                        holder.binding.editLayout.visibility = View.VISIBLE
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
                try {
                    if (datalist!=null && datalist?.SAcqFeasibilityDetail?.isNotEmpty()==true){
                        feasibilityData=datalist?.SAcqFeasibilityDetail?.get(0)
                    }
                    if (feasibilityData!=null){
                        // view mode
                        if (feasibilityData?.Acquisitiontype?.isNotEmpty() == true)
                            AppPreferences.getInstance().setDropDown(holder.binding.AcquisitionType,DropDowns.Acquisitiontype.name,feasibilityData?.Acquisitiontype?.get(0).toString())
                        if (feasibilityData?.TowerPoleType?.isNotEmpty() == true)
                            AppPreferences.getInstance().setDropDown(holder.binding.TowerPoleType,DropDowns.TowerPoleType.name,feasibilityData?.TowerPoleType?.get(0).toString())
                        holder.binding.AcquisitionArea.text=feasibilityData?.Area
                        holder.binding.ExpectedPrice.text=feasibilityData?.ExpectedPrice
                        holder.binding.AverageMarketRate.text=feasibilityData?.MarketPrice
                        holder.binding.FiberLMCLaying.text=feasibilityData?.FiberLMCLaying.toString()
                        holder.binding.EBSupplyThroOwnerMeter.text=feasibilityData?.OwnerMeter.toString()
                        holder.binding.EquipmentRoom.text=feasibilityData?.EquipmentRoom.toString()
                        holder.binding.RequiredAreaAvailable.text=feasibilityData?.RequiredAreaAvailable.toString()
                        holder.binding.StatutoryPermissions.text=feasibilityData?.StatutoryPermission.toString()
                        holder.binding.OverallFeasibility.text=feasibilityData?.OverallFeasibility.toString()
                        holder.binding.SurveyExecutiveName.text=feasibilityData?.ExecutiveName.toString()
                        holder.binding.SurveyDate.text=Utils.getFormatedDate(feasibilityData?.SurveyDate?.substring(0,10)!!,"dd-MMM-yyyy")
                        holder.binding.remarks.text=feasibilityData?.Remark

                        // edit mode
                        holder.binding.AcquisitionAreaEdit.setText(feasibilityData?.Area)
                        holder.binding.ExpectedPriceEdit.setText(feasibilityData?.ExpectedPrice)
                        holder.binding.AverageMarketRateEdit.setText(feasibilityData?.MarketPrice)
                        holder.binding.FiberLMCLayingEdit.text=feasibilityData?.FiberLMCLaying.toString()
                        holder.binding.EBSupplyThroOwnerMeterEdit.text=feasibilityData?.OwnerMeter.toString()
                        holder.binding.EquipmentRoomEdit.text=feasibilityData?.EquipmentRoom.toString()
                        holder.binding.RequiredAreaAvailableEdit.text=feasibilityData?.RequiredAreaAvailable.toString()
                        holder.binding.StatutoryPermissionsEdit.text=feasibilityData?.StatutoryPermission.toString()
                        holder.binding.OverallFeasibilityEdit.text=feasibilityData?.OverallFeasibility.toString()
                        holder.binding.SurveyExecutiveNameEdit.setText(feasibilityData?.ExecutiveName)
                        holder.binding.SurveyDateEdit.text=Utils.getFormatedDate(feasibilityData?.SurveyDate!!.substring(0,10),"dd-MMM-yyyy")
                        holder.binding.remarksEdit.setText(feasibilityData?.Remark)


                    }
                    else
                        AppLogger.log("error in Acquisition Survey data or Property details data")
                    if (feasibilityData!=null && feasibilityData?.Acquisitiontype?.isNotEmpty()==true)
                        AppPreferences.getInstance().setDropDown(holder.binding.AcquisitionTypeEdit,DropDowns.Acquisitiontype.name,feasibilityData?.Acquisitiontype?.get(0).toString())
                    else
                        AppPreferences.getInstance().setDropDown(holder.binding.AcquisitionTypeEdit,DropDowns.Acquisitiontype.name)

                    if (feasibilityData!=null &&feasibilityData?.TowerPoleType?.isNotEmpty()==true)
                        AppPreferences.getInstance().setDropDown(holder.binding.TowerPoleTypeEdit,DropDowns.TowerPoleType.name,feasibilityData?.TowerPoleType?.get(0).toString())
                    else
                        AppPreferences.getInstance().setDropDown(holder.binding.TowerPoleTypeEdit,DropDowns.TowerPoleType.name)

                }catch (e:java.lang.Exception){
                    AppLogger.log("ToewerInfoadapter error : ${e.localizedMessage}")
                }
                baseFragment.setDatePickerView(holder.binding.SurveyDateEdit)
                holder.binding.update.setOnClickListener {
                    if (feasibilityData!=null){
                        feasibilityData?.let {
                            it.Area=holder.binding.AcquisitionAreaEdit.text.toString()
                            it.ExpectedPrice=holder.binding.ExpectedPriceEdit.text.toString()
                            it.MarketPrice=holder.binding.AverageMarketRateEdit.text.toString()
                            it.ExecutiveName=holder.binding.SurveyExecutiveNameEdit.text.toString()
                            it.Remark=holder.binding.remarksEdit.text.toString()
                            it.SurveyDate=Utils.getFullFormatedDate(holder.binding.SurveyDateEdit.text.toString())
                            if (it.Acquisitiontype?.isNotEmpty()==true)
                                it.Acquisitiontype!![0] = holder.binding.AcquisitionTypeEdit.selectedValue.id.toInt()
                            else
                                it.Acquisitiontype?.add(holder.binding.AcquisitionTypeEdit.selectedValue.id.toInt())

                            if (it.TowerPoleType?.isNotEmpty()==true)
                                it.TowerPoleType!![0] = holder.binding.TowerPoleTypeEdit.selectedValue.id.toInt()
                            else
                                it.TowerPoleType?.add(holder.binding.TowerPoleTypeEdit.selectedValue.id.toInt())

                            val tempList:ArrayList<SAcqFeasibilityDetail> = ArrayList()
                            tempList.clear()
                            tempList.add(it)
                            val tempData= AcquisitionSurveyData()
                            tempData.SAcqFeasibilityDetail=tempList
                            tempData.id= datalist?.id
                            listener.updateItemClicked(tempData)
                        }
                    }
                    else if (datalist!=null && datalist?.SAcqFeasibilityDetail?.isEmpty()==true){
                        val tempfeasibilityData=SAcqFeasibilityDetail()
                        tempfeasibilityData.let {
                            it.Area=holder.binding.AcquisitionAreaEdit.text.toString()
                            it.ExpectedPrice=holder.binding.ExpectedPriceEdit.text.toString()
                            it.MarketPrice=holder.binding.AverageMarketRateEdit.text.toString()
                            it.ExecutiveName=holder.binding.SurveyExecutiveNameEdit.text.toString()
                            it.Remark=holder.binding.remarksEdit.text.toString()
                            it.SurveyDate=Utils.getFullFormatedDate(holder.binding.SurveyDateEdit.text.toString())
                            it.Acquisitiontype = arrayListOf(holder.binding.AcquisitionTypeEdit.selectedValue.id.toInt())
                            it.TowerPoleType =  arrayListOf(holder.binding.TowerPoleTypeEdit.selectedValue.id.toInt())

                            val tempList:ArrayList<SAcqFeasibilityDetail> = ArrayList()
                            tempList.clear()
                            tempList.add(it)
                            val tempData= AcquisitionSurveyData()
                            tempData.SAcqFeasibilityDetail=tempList
                            tempData.id= datalist?.id
                            listener.updateItemClicked(tempData)
                        }
                    }
                    else{
                        val tempfeasibilityData=SAcqFeasibilityDetail()
                        tempfeasibilityData.let {
                            it.Area=holder.binding.AcquisitionAreaEdit.text.toString()
                            it.ExpectedPrice=holder.binding.ExpectedPriceEdit.text.toString()
                            it.MarketPrice=holder.binding.AverageMarketRateEdit.text.toString()
                            it.ExecutiveName=holder.binding.SurveyExecutiveNameEdit.text.toString()
                            it.Remark=holder.binding.remarksEdit.text.toString()
                            it.SurveyDate=Utils.getFullFormatedDate(holder.binding.SurveyDateEdit.text.toString())
                            it.Acquisitiontype = arrayListOf(holder.binding.AcquisitionTypeEdit.selectedValue.id.toInt())
                            it.TowerPoleType =  arrayListOf(holder.binding.TowerPoleTypeEdit.selectedValue.id.toInt())

                            val tempList:ArrayList<SAcqFeasibilityDetail> = ArrayList()
                            tempList.clear()
                            tempList.add(it)
                            val tempData= AcquisitionSurveyData()
                            tempData.SAcqFeasibilityDetail=tempList
                            listener.updateItemClicked(tempData)
                        }
                    }
                }

            }
            is ViewHold6 -> {
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE

                    holder.binding.root.findViewById<View>(R.id.attach_card).setOnClickListener {
                        if (datalist!=null){
                            listener.addAttachment()
                        }
                        else
                            Toast.makeText(baseFragment.requireContext(),"Firstly fill data then Add Attachment",Toast.LENGTH_SHORT).show()
                    }
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
                    if (datalist!=null){
                        holder.recyclerListener.adapter= ImageAttachmentCommonAdapter(baseFragment.requireContext(),datalist?.attachment!!,object : ImageAttachmentCommonAdapter.ItemClickListener{
                            override fun itemClicked() {
                                listener.attachmentItemClicked()
                            }
                        })
                    }
                    else
                        AppLogger.log("Attachments Error")
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
       fun attachmentItemClicked()
       fun addAttachment()
       fun viewInsidePremisesClicked(position: Int,data:SAcqInsidePremise)
       fun editInsidePremisesClicked(position: Int,data:SAcqInsidePremise)
       fun viewOutsidePremisesClicked(position: Int,data:SAcqOutsidePremise)
       fun editOutsidePremisesClicked(position: Int,data:SAcqOutsidePremise)
       fun viewPropertyOwnerClicked(position: Int,data:SAcqPropertyOwnerDetail)
       fun editPropertyOwnerClicked(position: Int,data:SAcqPropertyOwnerDetail)
       fun updateItemClicked(data:AcquisitionSurveyData)

    }

}
