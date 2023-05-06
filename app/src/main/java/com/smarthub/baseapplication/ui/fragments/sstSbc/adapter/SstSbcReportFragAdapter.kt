package com.smarthub.baseapplication.ui.fragments.sstSbc.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.SstSbcTestReportItemsBinding
import com.smarthub.baseapplication.databinding.TowerAttachmentInfoBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.Attachments
import com.smarthub.baseapplication.model.siteIBoard.newSiteInfoDataModel.AllsiteInfoDataModel
import com.smarthub.baseapplication.model.siteIBoard.newSiteInfoDataModel.SiteAddressData
import com.smarthub.baseapplication.model.siteIBoard.newsstSbc.*
import com.smarthub.baseapplication.ui.fragments.ImageAttachmentCommonAdapter
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class SstSbcReportFragAdapter(var baseFragment:BaseFragment, var listener: SstSbcReportListListener, data:SstSbcAllData?) : RecyclerView.Adapter<SstSbcReportFragAdapter.ViewHold>() {
    private var datalist: SstSbcTestReport?=null
    private var buildingData: SstSbcBuildingDetail?=null
    private var landData: SstSbcLandDetail?=null
    private var siteAdd: SiteAddressData?=null

    fun setData(data: SstSbcTestReport?) {
        this.datalist=data!!
        if (AppController.getInstance().newSiteInfoModel!=null){
            if (AppController.getInstance().newSiteInfoModel.Siteaddress!=null && AppController.getInstance().newSiteInfoModel.Siteaddress?.isNotEmpty()==true){
                siteAdd= AppController.getInstance().newSiteInfoModel.Siteaddress?.get(0)
            }
        }
        notifyDataSetChanged()
    }
    init {
        try {
            if (data!=null && data.SstSbcTeam?.isNotEmpty()!!){
                datalist=data.SstSbcTestReport!!.get(0)
            }
        }catch (e:java.lang.Exception){
            AppLogger.log("TowerInfoFrag error :${e.localizedMessage}")
        }
    }



    var list : ArrayList<String> = ArrayList()
    var currentOpened = -1
    var type1 = "Report"
    var type2 = "Attachments"

    init {
        list.add("Report")
        list.add("Attachments")
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)
    
    class ViewHold1(itemView: View) : ViewHold(itemView) {
        var binding : SstSbcTestReportItemsBinding = SstSbcTestReportItemsBinding.bind(itemView)

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
    class ViewHold2(itemView: View, listener: SstSbcReportListListener) : ViewHold(itemView) {
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
        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_empty,parent,false)
        when (viewType) {
            1 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.sst_sbc_test_report_items, parent, false)
                return ViewHold1(view)
            }
            2 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.tower_attachment_info, parent, false)
                return ViewHold2(view,listener)
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
                if (datalist!=null){
                    if (datalist?.SstSbcBuildingDetail!=null && datalist?.SstSbcBuildingDetail?.isNotEmpty()==true)
                        buildingData=datalist?.SstSbcBuildingDetail?.get(0)
                    if (datalist?.SstSbcLandDetail!=null && datalist?.SstSbcLandDetail?.isNotEmpty()==true)
                        landData=datalist?.SstSbcLandDetail?.get(0)
                }
                if (AppController.getInstance().newSiteInfoModel!=null){
                    if (AppController.getInstance().newSiteInfoModel.Siteaddress!=null && AppController.getInstance().newSiteInfoModel.Siteaddress?.isNotEmpty()==true){
                        siteAdd= AppController.getInstance().newSiteInfoModel.Siteaddress?.get(0)
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
                    holder.binding.AddressLine1Edit.setText(siteAdd?.address1)
                    holder.binding.AddressLine2Edit.text=siteAdd?.address2
                    holder.binding.PostalCodeEdit.text=siteAdd?.pincode
                    holder.binding.SiteLatitudeEdit.text=siteAdd?.locLatitude
                    holder.binding.SiteLongitudeEdit.text=siteAdd?.locLongitude
                }
                if (buildingData!=null){
                    //view mode
                    if (buildingData?.BuildingType?.isNotEmpty()==true)
                        AppPreferences.getInstance().setDropDown(holder.binding.BuildingType,DropDowns.Buildingtype.name,buildingData?.BuildingType?.get(0).toString())
                   if (buildingData?.BuildingBuildType != null && buildingData?.BuildingBuildType!! >=1)
                        AppPreferences.getInstance().setDropDown(holder.binding.BuildingBuildType,DropDowns.BuildingBuildType.name,buildingData?.BuildingBuildType.toString())
                    holder.binding.BuildingHeight.text=buildingData?.BuildingHeight
                    holder.binding.TypicalFloorArea.text=buildingData?.TypicalFloorArea
                    holder.binding.YearofConstruction.text=buildingData?.ConstructionYear.toString()
                    holder.binding.NoofFloors.text=buildingData?.NoOfFloors.toString()
                    holder.binding.BuildingReportNumber.text=buildingData?.ReportNo
                    holder.binding.BuildingRemarks.text=buildingData?.remark
                    holder.binding.SiteTestingDate.text=Utils.getFormatedDate(buildingData?.TestingDate,"dd-MMM-yyyy")
                    holder.binding.BuildingTestReportDate.text=Utils.getFormatedDate(buildingData?.TestReportDate,"dd-MMM-yyyy")
                    // edit mode
                    holder.binding.BuildingHeightEdit.setText(buildingData?.BuildingHeight)
                    holder.binding.TypicalFloorAreaEdit.setText(buildingData?.TypicalFloorArea)
                    holder.binding.YearOfConstructionEdit.setText(buildingData?.ConstructionYear.toString())
                    holder.binding.NoOfFloorEdit.setText(buildingData?.NoOfFloors.toString())
                    holder.binding.BuildingReportNumberEdit.setText(buildingData?.ReportNo)
                    holder.binding.BuildingRemarksEdit.setText(buildingData?.remark)
                    holder.binding.SiteTestingDateEdit.text=Utils.getFormatedDate(buildingData?.TestingDate,"dd-MMM-yyyy")
                    holder.binding.BuildingTestReportDateEdit.text=Utils.getFormatedDate(buildingData?.TestReportDate,"dd-MMM-yyyy")
                }
                if (landData!=null){
                    //View Mode
                    if (landData?.LandType?.isNotEmpty()==true)
                        AppPreferences.getInstance().setDropDown(holder.binding.LandType,DropDowns.LandType.name, landData?.LandType?.get(0).toString())
                    if (landData?.SoilType?.isNotEmpty()==true)
                        AppPreferences.getInstance().setDropDown(holder.binding.SoilType,DropDowns.SoilType.name, landData?.SoilType?.get(0).toString())
                    holder.binding.LandReportNumber.text=landData?.ReportNo
                    holder.binding.LandRemarks.text=landData?.remark
                    holder.binding.SoilSampleCollectionDate.text=Utils.getFormatedDate(landData?.TestingDate,"dd-MMM-yyyy")
                    holder.binding.LandTestReportDate.text=Utils.getFormatedDate(landData?.TestReportDate,"dd-MMM-yyyy")

                    // edit mode
                    holder.binding.LandReportNumberEdit.setText(landData?.ReportNo)
                    holder.binding.LandRemarksEdit.setText(landData?.remark)
                    holder.binding.SoilSampleCollectionDateEdit.text=Utils.getFormatedDate(buildingData?.TestingDate,"dd-MMM-yyyy")
                    holder.binding.LandTestReportDateEdit.text=Utils.getFormatedDate(buildingData?.TestReportDate,"dd-MMM-yyyy")
                }
                if (buildingData!=null && buildingData?.BuildingType?.isNotEmpty()==true)
                    AppPreferences.getInstance().setDropDown(holder.binding.BuildingTypeEdit,DropDowns.Buildingtype.name,buildingData?.BuildingType?.get(0).toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.BuildingTypeEdit,DropDowns.Buildingtype.name)

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


                holder.binding.update.setOnClickListener {
                    val tempBuildingData=SstSbcBuildingDetail()
                    val tempLandData=SstSbcLandDetail()
                    val tempTestReportData= SstSbcTestReport()
                    val temSiteAddData=SiteAddressData()
                    val temSiteInfoAllData= AllsiteInfoDataModel()
                    temSiteAddData.let {
                        it.address1=holder.binding.AddressLine1Edit.text.toString()
                        it.address2=holder.binding.AddressLine2Edit.text.toString()
                        it.locLongitude=holder.binding.SiteLongitudeEdit.text.toString()
                        it.locLatitude=holder.binding.SiteLatitudeEdit.text.toString()
                        it.pincode=holder.binding.PostalCodeEdit.text.toString()
                        if (siteAdd!=null)
                            it.id=siteAdd?.id
                    }
                    tempLandData.let {
                        it.ReportNo=holder.binding.LandReportNumberEdit.text.toString()
                        it.remark=holder.binding.LandRemarksEdit.text.toString()
                        it.TestingDate=Utils.getFullFormatedDate(holder.binding.SoilSampleCollectionDateEdit.text.toString())
                        it.TestReportDate=Utils.getFullFormatedDate(holder.binding.LandTestReportDateEdit.text.toString())
                        it.LandType= holder.binding.LandTypeEdit.getSelectedArray()
                        it.SoilType= holder.binding.SoilTypeEdit.getSelectedArray()
                        if (landData!=null)
                            it.id=landData?.id
                    }
                    tempBuildingData.let {
                        it.BuildingHeight=holder.binding.BuildingHeightEdit.text.toString()
                        it.NoOfFloors=holder.binding.NoOfFloorEdit.text.toString().toIntOrNull()
                        it.TypicalFloorArea=holder.binding.TypicalFloorAreaEdit.text.toString()
                        it.ConstructionYear=holder.binding.YearOfConstructionEdit.text.toString().toIntOrNull()
                        it.ReportNo=holder.binding.BuildingReportNumberEdit.text.toString()
                        it.remark=holder.binding.BuildingRemarksEdit.text.toString()
                        it.TestingDate=Utils.getFullFormatedDate(holder.binding.SoilSampleCollectionDateEdit.text.toString())
                        it.TestReportDate=Utils.getFullFormatedDate(holder.binding.LandTestReportDateEdit.text.toString())
                        it.BuildingType= holder.binding.BuildingTypeEdit.getSelectedArray()
                        it.BuildingBuildType= holder.binding.BuildingBuildTypeEdit.selectedValue.id.toInt()
                        if (buildingData!=null)
                            it.id=buildingData?.id
                    }
                    tempTestReportData.let {
                        it.SstSbcBuildingDetail= arrayListOf(tempBuildingData)
                        it.SstSbcLandDetail= arrayListOf(tempLandData)
                        if (datalist!=null){
                            it.attachment=datalist?.attachment
                            it.id=datalist?.id
                        }
                    }
                    temSiteInfoAllData.Siteaddress= arrayListOf(temSiteAddData)
                    listener.updateAddress(temSiteInfoAllData)
                    listener.updateTeamClicked(tempTestReportData)
                }

                baseFragment.setDatePickerView(holder.binding.SoilSampleCollectionDateEdit)
                baseFragment.setDatePickerView(holder.binding.BuildingTestReportDateEdit)
                baseFragment.setDatePickerView(holder.binding.LandTestReportDateEdit)
                baseFragment.setDatePickerView(holder.binding.SiteTestingDateEdit)
                holder.binding.AddressLine2Edit.setOnClickListener {
                    listener.initiateAddressActivity(
                        holder.binding.AddressLine2Edit,holder.binding.SiteLatitudeEdit,
                        holder.binding.SiteLongitudeEdit,holder.binding.PostalCodeEdit)
                }
                holder.binding.SiteLatitudeEdit.setOnClickListener {
                    listener.initiateAddressActivity(
                        holder.binding.AddressLine2Edit,holder.binding.SiteLatitudeEdit,
                        holder.binding.SiteLongitudeEdit,holder.binding.PostalCodeEdit)
                }
                holder.binding.SiteLongitudeEdit.setOnClickListener {
                    listener.initiateAddressActivity(
                        holder.binding.AddressLine2Edit,holder.binding.SiteLatitudeEdit,
                        holder.binding.SiteLongitudeEdit,holder.binding.PostalCodeEdit)

                }
                holder.binding.PostalCodeEdit.setOnClickListener {
                    listener.initiateAddressActivity(
                        holder.binding.AddressLine2Edit,holder.binding.SiteLatitudeEdit,
                        holder.binding.SiteLongitudeEdit,holder.binding.PostalCodeEdit)
                }
            }
            is ViewHold2 -> {
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
                            Toast.makeText(baseFragment.requireContext(),"Firstly fill data then Add Attachment", Toast.LENGTH_SHORT).show()
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
                            override fun itemClicked(item : Attachments) {
                                listener.attachmentItemClicked()
                            }
                        })
                    }
                    else
                        AppLogger.log("Attachments Error")
                }catch (e:java.lang.Exception){
                    AppLogger.log("Assign Acq Team error : ${e.localizedMessage}")
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



    interface SstSbcReportListListener {
       fun attachmentItemClicked()
       fun addAttachment()
       fun updateTeamClicked(data:SstSbcTestReport)
       fun updateAddress(data: AllsiteInfoDataModel?)
        fun initiateAddressActivity(address2: TextView?, siteLat: TextView, siteLong: TextView, postalCode: TextView)
    }

}
