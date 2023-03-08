package com.smarthub.baseapplication.ui.fragments.siteAcquisition.adapters

import android.content.Context
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
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.tableAdapters.InsidePremisesTableAdapter
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.tableAdapters.OutsidePremisesTableAdapter
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.tableAdapters.PropertyOwnerTableAdapter
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns

class AcqSurveyFragAdapter(var context: Context, var listener: AcqSurveyListListener, data:NewSiteAcquiAllData?) : RecyclerView.Adapter<AcqSurveyFragAdapter.ViewHold>() {
    private var datalist: AcquisitionSurveyData?=null

    fun setData(data: AcquisitionSurveyData?) {
        this.datalist=data!!
        notifyDataSetChanged()
    }
    init {
        try {
            if (data!=null && data.SAcqAssignACQTeam.isNotEmpty()){
                datalist=data.SAcqAcquitionSurvey.get(0)
            }
        }catch (e:java.lang.Exception){
            Toast.makeText(context,"TowerInfoFrag error :${e.localizedMessage}", Toast.LENGTH_LONG).show()
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
                addInsidePremisesTableItem("wqeeqs")
            }
            binding.addItemsOutsideLayout.setOnClickListener {
                addOutsidePremisesTableItem("wqeeqs")
            }
        }

        private fun addInsidePremisesTableItem(item:String){
            if (insidePremisesTableList.adapter!=null && insidePremisesTableList.adapter is InsidePremisesTableAdapter){
                val adapter = insidePremisesTableList.adapter as InsidePremisesTableAdapter
                adapter.addItem(item)
            }
        }
        private fun addOutsidePremisesTableItem(item:String){
            if (outsidePremisesTableList.adapter!=null && outsidePremisesTableList.adapter is OutsidePremisesTableAdapter){
                val adapter = outsidePremisesTableList.adapter as OutsidePremisesTableAdapter
                adapter.addItem(item)
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
                addOwnerTableItem("wqeeqs")
            }
        }

        private fun addOwnerTableItem(item:String){
            if (ownerTableList.adapter!=null && ownerTableList.adapter is PropertyOwnerTableAdapter){
                val adapter = ownerTableList.adapter as PropertyOwnerTableAdapter
                adapter.addItem(item)
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

            val recyclerListener = itemView.findViewById<RecyclerView>(R.id.list_item)
            recyclerListener.adapter = adapter

            itemView.findViewById<View>(R.id.attach_card).setOnClickListener {
                adapter.addItem()
            }

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

                    holder.binding.imgEdit.setOnClickListener {
//                        listener.EditTowerItem()
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
                    if (datalist!=null && datalist?.SAcqPropertyDetail?.isNotEmpty()==true){
                        val propertyData: SAcqPropertyDetail? = datalist?.SAcqPropertyDetail?.get(0)
                        if (propertyData?.Potentialthreat?.isNotEmpty() == true)
                            AppPreferences.getInstance().setDropDown(holder.binding.PotentialThreat,DropDowns.Potentialthreat.name,propertyData.Potentialthreat?.get(0).toString())
                        if (propertyData?.Direction?.isNotEmpty() == true)
                            AppPreferences.getInstance().setDropDown(holder.binding.SiteAccessDirection,DropDowns.Direction.name,propertyData.Direction.get(0).toString())
                        if (propertyData?.Direction?.isNotEmpty() == true)
                            AppPreferences.getInstance().setDropDown(holder.binding.SiteAccessDirection,DropDowns.Direction.name,propertyData.Direction.get(0).toString())

                        if (AppController.getInstance().newSiteInfoModel.Siteaddress?.isNotEmpty()==true){
                            val siteAdd:SiteAddressData?= AppController.getInstance().newSiteInfoModel.Siteaddress?.get(0)
                            holder.binding.AddressLine1.text=siteAdd?.address1
                            holder.binding.AddressLine2.text=siteAdd?.address2
                            holder.binding.PostalCode.text=siteAdd?.pincode
                            holder.binding.SiteLAtitude.text=siteAdd?.locLatitude
                            holder.binding.SiteLongitude.text=siteAdd?.locLongitude
                        }
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

                        if (propertyData!=null && propertyData.SAcqBuildingDetail.isNotEmpty()){
                            val buildingData: SAcqBuildingDetail =propertyData.SAcqBuildingDetail.get(0)
                            if (buildingData.BuildingType.isNotEmpty())
                                AppPreferences.getInstance().setDropDown(holder.binding.BuildingType,DropDowns.Buildingtype.name,buildingData.BuildingType.get(0).toString())
                            holder.binding.buildingPropertyType.text=buildingData.PropertyType.toString()
                            holder.binding.BuildingBuildType.text=buildingData.BuildingBuildType.toString()
                            holder.binding.BuildingHeight.text=buildingData.BuildingHeight
                            holder.binding.TypicalFloorArea.text=buildingData.TypicalFloorArea
                            holder.binding.YearofConstruction.text=buildingData.ConstructionYear.toString()
                            holder.binding.NoofFloors.text=buildingData.NoOfFloors.toString()
                        }
                        else
                            AppLogger.log("error in Property details data or Building Details Data")

                        if (propertyData!=null && propertyData.SAcqLandDetail.isNotEmpty()){
                            val landData: SAcqLandDetail =propertyData.SAcqLandDetail.get(0)
                            if (landData.LandType.isNotEmpty())
                                AppPreferences.getInstance().setDropDown(holder.binding.LandType,DropDowns.LandType.name,landData.LandType.get(0).toString())
                            if (landData.SoilType.isNotEmpty())
                                AppPreferences.getInstance().setDropDown(holder.binding.SoilType,DropDowns.SoilType.name,landData.SoilType.get(0).toString())
                            if (landData.Terraintype.isNotEmpty())
                                AppPreferences.getInstance().setDropDown(holder.binding.TerrainType,DropDowns.Terraintype.name,landData.Terraintype.get(0).toString())

                            holder.binding.landPropertyType.text=landData.PropertyType.toString()
                            holder.binding.SiteDemarcation.text=landData.SiteDemarcation.toString()
                        }
                        else
                            AppLogger.log("error in Property details data or Building Details Data")


                    }
                    else
                        AppLogger.log("error in Acquisition Survey data or Property details data")
                }catch (e:java.lang.Exception){
                    AppLogger.log("ToewerInfoadapter error : ${e.localizedMessage}")
                }

            }
            is ViewHold2 -> {
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                    holder.binding.imgEdit.visibility = View.VISIBLE

                    holder.binding.imgEdit.setOnClickListener {
//                        listener.EditTowerItem()
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
                    if (datalist!=null && datalist?.SAcqExternalStructure?.isNotEmpty()==true){
                        val ExtStrData: SAcqExternalStructure? =datalist?.SAcqExternalStructure?.get(0)
                        holder.insidePremisesTableList.adapter=InsidePremisesTableAdapter(context,listener,ExtStrData?.SAcqInsidePremise)
                        holder.outsidePremisesTableList.adapter=OutsidePremisesTableAdapter(context,listener,ExtStrData?.SAcqOutsidePremise)
                    }
                    else
                        AppLogger.log("error in Acquisition Survey data or External Structure data")
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

                    holder.binding.imgEdit.setOnClickListener {
//                        listener.EditTowerItem()
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
                        val powerData: SAcqPowerConnectionFeasibility? =datalist?.SAcqPowerConnectionFeasibility?.get(0)
                        holder.binding.EBPowerAvailability.text= powerData?.EBAvailability.toString()
                        holder.binding.PowerSupplierName.text= powerData?.PowerSupplier
                        holder.binding.EBApplicationStatus.text= powerData?.EBApplicationStatus.toString()
                        holder.binding.AvgAvailibillity.text= powerData?.AvgAvailability
                        holder.binding.AvgAvailibillity.text= powerData?.AvgAvailability
                        holder.binding.MeterType.text= powerData?.MeterType.toString()
                        holder.binding.ConsumerNo.text= powerData?.ConsumerNo
                        holder.binding.MeterSerialNumber.text= powerData?.MeterSerialNo
                        holder.binding.NearestEBPole.text= powerData?.NearestEBPole
                        holder.binding.PowerConnRating.text= powerData?.PowerRating
                        holder.binding.SolarFeability.text= powerData?.SolarFeasibility.toString()
                        holder.binding.remarks.text= powerData?.Remark
                    }
                    else
                        AppLogger.log("error in Acquisition Survey data or Property details data")
                }catch (e:java.lang.Exception){
                    AppLogger.log("ToewerInfoadapter error : ${e.localizedMessage}")
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
                        holder.ownerTableList.adapter=PropertyOwnerTableAdapter(context,listener,datalist?.SAcqPropertyOwnerDetail)
                    }
                    else
                        AppLogger.log("error in Acquisition Survey data or property Owner data")
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

                    holder.binding.imgEdit.setOnClickListener {
//                        listener.EditTowerItem()
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
                        val feasibilityData: SAcqFeasibilityDetail? = datalist?.SAcqFeasibilityDetail?.get(0)
                        if (feasibilityData?.Acquisitiontype?.isNotEmpty() == true)
                            AppPreferences.getInstance().setDropDown(holder.binding.AcquisitionType,DropDowns.Acquisitiontype.name,feasibilityData.Acquisitiontype.get(0).toString())
                        if (feasibilityData?.TowerPoleType?.isNotEmpty() == true)
                            AppPreferences.getInstance().setDropDown(holder.binding.TowerPoleType,DropDowns.TowerPoleType.name,feasibilityData.TowerPoleType.get(0).toString())

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
                        holder.binding.SurveyDate.text=feasibilityData?.SurveyDate.toString()
                        holder.binding.remarks.text=feasibilityData?.Remark

                    }
                    else
                        AppLogger.log("error in Acquisition Survey data or Property details data")
                }catch (e:java.lang.Exception){
                    AppLogger.log("ToewerInfoadapter error : ${e.localizedMessage}")
                }

            }
            is ViewHold6 -> {
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
       fun viewInsidePremisesClicked(position: Int,data:SAcqInsidePremise)
       fun viewOutsidePremisesClicked(position: Int,data:SAcqOutsidePremise)
       fun viewPropertyOwnerClicked(position: Int,data:SAcqPropertyOwnerDetail)

    }

}
