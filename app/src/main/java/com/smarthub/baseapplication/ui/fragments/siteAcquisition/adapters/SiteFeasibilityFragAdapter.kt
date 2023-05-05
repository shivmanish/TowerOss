package com.smarthub.baseapplication.ui.fragments.siteAcquisition.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AcqSiteFeasibilityDetailsBinding
import com.smarthub.baseapplication.databinding.TowerAttachmentInfoBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.Attachments
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.NewSiteAcquiAllData
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.SAcqBuildingDetail
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.SAcqFeasibilityDetail
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.SAcqLandDetail
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.ImageAttachmentCommonAdapter
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class SiteFeasibilityFragAdapter(var baseFragment:BaseFragment, var listener: SiteFeasibilityListListener, data:NewSiteAcquiAllData?) : RecyclerView.Adapter<SiteFeasibilityFragAdapter.ViewHold>() {
    private var datalist: SAcqFeasibilityDetail?=null
    private var buildingData: SAcqBuildingDetail?=null
    private var landData: SAcqLandDetail?=null

    fun setData(data: SAcqFeasibilityDetail?) {
        this.datalist=data!!
        notifyDataSetChanged()
    }
    init {
        try {
            if (data!=null && data.SAcqSiteFeasibility?.isNotEmpty()==true){
                datalist= data.SAcqSiteFeasibility?.get(0)
            }
            if (data!=null && data.SAcqAcquitionSurvey?.isNotEmpty()==true){
                if (data.SAcqAcquitionSurvey?.get(0)?.SAcqPropertyDetail!=null && data.SAcqAcquitionSurvey?.get(0)?.SAcqPropertyDetail?.isNotEmpty()==true){
                    if (data.SAcqAcquitionSurvey?.get(0)?.SAcqPropertyDetail?.get(0)?.SAcqLandDetail!=null &&
                        data.SAcqAcquitionSurvey?.get(0)?.SAcqPropertyDetail?.get(0)?.SAcqLandDetail?.isNotEmpty()==true  ){
                        landData=data.SAcqAcquitionSurvey?.get(0)?.SAcqPropertyDetail?.get(0)?.SAcqLandDetail?.get(0)
                    }
                    if (data.SAcqAcquitionSurvey?.get(0)?.SAcqPropertyDetail?.get(0)?.SAcqBuildingDetail!=null &&
                            data.SAcqAcquitionSurvey?.get(0)?.SAcqPropertyDetail?.get(0)?.SAcqBuildingDetail?.isNotEmpty()==true  ){
                        buildingData=data.SAcqAcquitionSurvey?.get(0)?.SAcqPropertyDetail?.get(0)?.SAcqBuildingDetail?.get(0)
                    }
                }
            }
        }catch (e:java.lang.Exception){
            AppLogger.log("AssignACQTeamFragAdapter error :${e.localizedMessage}")
        }
    }



    var list : ArrayList<String> = ArrayList()
    var currentOpened = -1
    var type1 = "Feasibility Detail"
    var type2 = "Attachments"

    init {
        list.add("Feasibility Detail")
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)
    
    class ViewHold1(itemView: View) : ViewHold(itemView) {
        var binding : AcqSiteFeasibilityDetailsBinding = AcqSiteFeasibilityDetailsBinding.bind(itemView)

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
    class ViewHold2(itemView: View, listener: SiteFeasibilityListListener) : ViewHold(itemView) {
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
                view = LayoutInflater.from(parent.context).inflate(R.layout.acq_site_feasibility_details, parent, false)
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
                holder.binding.imgDropdown.visibility=View.GONE
                holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                holder.binding.itemLine.visibility = View.GONE
                holder.binding.itemCollapse.visibility = View.VISIBLE
                holder.binding.imgEdit.visibility = View.VISIBLE
                holder.binding.viewLayout.visibility = View.VISIBLE
                holder.binding.editLayout.visibility = View.GONE

                holder.binding.imgEdit.setOnClickListener {
                    if (AppController.getInstance().isTaskEditable){
                        holder.binding.viewLayout.visibility = View.GONE
                        holder.binding.editLayout.visibility = View.VISIBLE
                    }

                }
                holder.binding.cancel.setOnClickListener {
                    holder.binding.viewLayout.visibility = View.VISIBLE
                    holder.binding.editLayout.visibility = View.GONE
                }
                
                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
                holder.binding.itemTitleStr.text = list[position]
                if (datalist!=null){
                    // view mode
                    if (datalist?.TowerPoleType?.isNotEmpty() == true)
                        AppPreferences.getInstance().setDropDown(holder.binding.TowerPoleType,DropDowns.TowerPoleType.name,datalist?.TowerPoleType?.get(0).toString())
                    if (datalist?.FiberLMCLaying!= null && datalist?.FiberLMCLaying!!>=1)
                        AppPreferences.getInstance().setDropDown(holder.binding.FiberLMCLaying,DropDowns.FiberLMCLaying.name,datalist?.FiberLMCLaying.toString())
                    if (datalist?.OwnerMeter!= null && datalist?.OwnerMeter!!>=1)
                        AppPreferences.getInstance().setDropDown(holder.binding.EBSupplyThroOwnerMeter,DropDowns.EBSupplyThroOwnerMeter.name,datalist?.OwnerMeter.toString())
                    if (datalist?.EquipmentRoom!= null && datalist?.EquipmentRoom!!>=1)
                        AppPreferences.getInstance().setDropDown(holder.binding.EquipmentRoom,DropDowns.EquipmentRoom.name,datalist?.EquipmentRoom.toString())
                    if (datalist?.RequiredAreaAvailable!= null && datalist?.RequiredAreaAvailable!!>=1)
                        AppPreferences.getInstance().setDropDown(holder.binding.RequiredAreaAvailable,DropDowns.RequiredAreaAvailable.name,datalist?.RequiredAreaAvailable.toString())
                    if (datalist?.StatutoryPermission!= null && datalist?.StatutoryPermission!!>=1)
                        AppPreferences.getInstance().setDropDown(holder.binding.StatutoryPermissions,DropDowns.StatutoryPermissions.name,datalist?.StatutoryPermission.toString())
                    if (datalist?.OverallFeasibility!= null && datalist?.OverallFeasibility!!>=1)
                        AppPreferences.getInstance().setDropDown(holder.binding.OverallFeasibility,DropDowns.OverallFeasibility.name,datalist?.OverallFeasibility.toString())

                    holder.binding.ExpectedPrice.text=datalist?.ExpectedPrice
                    holder.binding.AverageMarketRate.text=datalist?.MarketPrice
                    holder.binding.SurveyExecutiveName.text=datalist?.ExecutiveName.toString()
                    holder.binding.SurveyDate.text=Utils.getFormatedDate(datalist?.SurveyDate,"dd-MMM-yyyy")
                    holder.binding.remarks.text=datalist?.remark

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
                    holder.binding.ExpectedPriceEdit.setText(datalist?.ExpectedPrice)
                    holder.binding.AverageMarketRateEdit.setText(datalist?.MarketPrice)
                    holder.binding.SurveyExecutiveNameEdit.setText(datalist?.ExecutiveName)
                    holder.binding.SurveyDateEdit.text=Utils.getFormatedDate(datalist?.SurveyDate,"dd-MMM-yyyy")
                    holder.binding.remarksEdit.setText(datalist?.remark)


                }
                else
                    AppLogger.log("error in Power Connection details data")
                
                if (datalist!=null && datalist?.TowerPoleType?.isNotEmpty()==true)
                    AppPreferences.getInstance().setDropDown(holder.binding.TowerPoleTypeEdit,DropDowns.TowerPoleType.name,datalist?.TowerPoleType?.get(0).toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.TowerPoleTypeEdit,DropDowns.TowerPoleType.name)
                if (datalist!=null && datalist?.FiberLMCLaying!= null && datalist?.FiberLMCLaying!!>=1)
                    AppPreferences.getInstance().setDropDown(holder.binding.FiberLMCLayingEdit,DropDowns.FiberLMCLaying.name,datalist?.FiberLMCLaying.toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.FiberLMCLayingEdit,DropDowns.FiberLMCLaying.name)
                if (datalist!=null && datalist?.OwnerMeter!= null && datalist?.OwnerMeter!!>=1)
                    AppPreferences.getInstance().setDropDown(holder.binding.EBSupplyThroOwnerMeterEdit,DropDowns.EBSupplyThroOwnerMeter.name,datalist?.OwnerMeter.toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.EBSupplyThroOwnerMeterEdit,DropDowns.EBSupplyThroOwnerMeter.name)
                if (datalist!=null && datalist?.EquipmentRoom!= null && datalist?.EquipmentRoom!!>=1)
                    AppPreferences.getInstance().setDropDown(holder.binding.EquipmentRoomEdit,DropDowns.EquipmentRoom.name,datalist?.EquipmentRoom.toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.EquipmentRoomEdit,DropDowns.EquipmentRoom.name)
                if (datalist!=null && datalist?.RequiredAreaAvailable!= null && datalist?.RequiredAreaAvailable!!>=1)
                    AppPreferences.getInstance().setDropDown(holder.binding.RequiredAreaAvailableEdit,DropDowns.RequiredAreaAvailable.name,datalist?.RequiredAreaAvailable.toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.RequiredAreaAvailableEdit,DropDowns.RequiredAreaAvailable.name)
                if (datalist!=null && datalist?.StatutoryPermission!= null && datalist?.StatutoryPermission!!>=1)
                    AppPreferences.getInstance().setDropDown(holder.binding.StatutoryPermissionsEdit,DropDowns.StatutoryPermissions.name,datalist?.StatutoryPermission.toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.StatutoryPermissionsEdit,DropDowns.StatutoryPermissions.name)
                if (datalist!=null && datalist?.OverallFeasibility!= null && datalist?.OverallFeasibility!!>=1)
                    AppPreferences.getInstance().setDropDown(holder.binding.OverallFeasibilityEdit,DropDowns.OverallFeasibility.name,datalist?.OverallFeasibility.toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.OverallFeasibilityEdit,DropDowns.OverallFeasibility.name)

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
                        if (datalist != null)
                            it.id = datalist?.id
                    }
                    listener.updateFeasibilityClicked(tempfeasibilityData)

                }

                baseFragment.setDatePickerView(holder.binding.SurveyDateEdit)

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
                        holder.recyclerListener.adapter= ImageAttachmentCommonAdapter(baseFragment.requireContext(),
                            ArrayList(),object : ImageAttachmentCommonAdapter.ItemClickListener{
                            override fun itemClicked(item : Attachments) {
                                listener.attachmentItemClicked(item)
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



    interface SiteFeasibilityListListener {
       fun attachmentItemClicked(item : Attachments)
       fun addAttachment()
       fun updateFeasibilityClicked(data:SAcqFeasibilityDetail)
    }

}
