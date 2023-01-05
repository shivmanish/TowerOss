package com.smarthub.baseapplication.ui.fragments.services_request.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.BackhualPlanningItemViewBinding
import com.smarthub.baseapplication.databinding.PowerMsbPlanningItemViewBinding
import com.smarthub.baseapplication.databinding.RpRadioAntineListItemBinding
import com.smarthub.baseapplication.databinding.SiteDetailItemViewBinding
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem
import com.smarthub.baseapplication.model.siteInfo.SafetyAndAcces
import com.smarthub.baseapplication.model.siteInfo.SiteBasicinfo
import com.smarthub.baseapplication.model.siteInfo.SiteInfoModel
import com.smarthub.baseapplication.network.pojo.site_info.BasicInfoModelDropDown

class FeasibilityoplanningAdapter(
    var context: Context,
    var listener: FeasibilityoplanningLisListener,
    var serviceRequestAllData: ServiceRequestAllDataItem?
) : RecyclerView.Adapter<FeasibilityoplanningAdapter.ViewHold>() {
    var list: ArrayList<String> = ArrayList()
    var type1 = "Site Details"
    var type2 = "Radio Antenna"
    var type3 = "Backhaul"
    var type4 = "Power & MCB"

    private var data: BasicInfoModelDropDown? = null
    private var fieldData: SiteInfoModel? = null

    fun setData(data: BasicInfoModelDropDown) {
        this.data = data
        notifyDataSetChanged()
    }

    fun setValueData(data: SiteInfoModel) {
        this.fieldData = data
        notifyDataSetChanged()
    }

    init {
        list.add("Site Details")
        list.add("Radio Antenna")
        list.add("Backhaul")
        list.add("Power & MCB")

    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun getItemViewType(position: Int): Int {
        if (list[position] is String && list[position] == type1)
            return 1
        else if (list[position] is String && list[position] == type2)
            return 2
        else if (list[position] is String && list[position] == type3)
            return 3
        else if (list[position] is String && list[position] == type4)
            return 4

        return 0
    }

    class ViewHold1(itemView: View) : ViewHold(itemView) {
        var binding: SiteDetailItemViewBinding = SiteDetailItemViewBinding.bind(itemView)

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
        var binding: RpRadioAntineListItemBinding = RpRadioAntineListItemBinding.bind(itemView)

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
        var binding: BackhualPlanningItemViewBinding =
            BackhualPlanningItemViewBinding.bind(itemView)

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
        var binding: PowerMsbPlanningItemViewBinding =
            PowerMsbPlanningItemViewBinding.bind(itemView)

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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_empty, parent, false)
        when (viewType) {
            1 -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.site_detail_item_view, parent, false)
                return ViewHold1(view)
            }
            2 -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.rp_radio_antine_list_item, parent, false)
                return ViewHold2(view)
            }
            3 -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.backhual_planning_item_view, parent, false)
                return ViewHold3(view)
            }
            4 -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.power_msb_planning_item_view, parent, false)
                return ViewHold4(view)
            }

        }
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        when (holder) {
            is ViewHold1 -> {
                holder.binding.imgEdit.setOnClickListener {
                    listener.detailsItemClicked()
                }
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                    holder.binding.iconLayout.visibility = View.VISIBLE
                } else {
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

                if (data != null) {

                }
                if (fieldData != null && fieldData?.item!!.size > 0 && fieldData?.item!![0].Basicinfo.isNotEmpty()) {
                    val siteBasicinfo: SiteBasicinfo = fieldData?.item!![0].Basicinfo[0]

                }
                if (serviceRequestAllData != null && serviceRequestAllData!!.FeasibilityPlanning != null && serviceRequestAllData!!.FeasibilityPlanning?.get(
                        0
                    ) != null
                ) {
                    serviceRequestAllData!!.FeasibilityPlanning?.get(0)?.SiteDetails?.get(0).let {
                        holder.binding.editSiteId.text = it?.OpcoSiteID
                        holder.binding.editOPCOSiteName.text = it?.OpcoSiteName
                        holder.binding.editOPCOSiteType.text = ""
                        holder.binding.editSiteCategory.text = ""
                        holder.binding.editTOCOUID.text = it?.TocoUID
                        holder.binding.tocoSiteType.text = ""
                        holder.binding.tocoSiteTypeSecond.text = ""
                        holder.binding.tocoSiteStatus.text = ""
                        holder.binding.projectName.text = it?.ProjectName
                        holder.binding.buildType.text = ""
                        holder.binding.porpertyOwonership.text = ""
                        holder.binding.acquistionType.text = ""
                        holder.binding.nominalsLatLong.text = it?.Nominallatitude
                        holder.binding.siteLatLong.text = it?.Sitelatitude
                        holder.binding.existingTentant.text = it?.ExistingTenants
                        holder.binding.address.text = it?.Address
                    }
                }
            }
            is ViewHold2 -> {
                holder.binding.imgEdit.setOnClickListener {
                    listener.requestinfoClicked()
                }
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                    holder.binding.iconLayout.visibility = View.VISIBLE
                } else {
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

                if (data != null) {

                }
                if (fieldData != null && fieldData?.item!!.size > 0 && fieldData?.item!![0].Basicinfo.isNotEmpty()) {
                    val siteBasicinfo: SiteBasicinfo = fieldData?.item!![0].Basicinfo[0]

                }
                if (serviceRequestAllData != null && serviceRequestAllData!!.FeasibilityPlanning != null && serviceRequestAllData!!.FeasibilityPlanning?.get(0)?.RadioAntenna!!.isNotEmpty()
                ) {
                    serviceRequestAllData!!.FeasibilityPlanning?.get(0)?.RadioAntenna?.get(0).let {
                        holder.binding.editTechnology.text = it?.RFTechnology
                        holder.binding.editRRUCount.text = it?.RRUCount
                        holder.binding.editSectocCount.text = it?.SectorCount
                        holder.binding.editMaxAntenna.text = it?.MaxAntennaHeight
                        holder.binding.editAntennaType.text = it?.AntennaType
                        holder.binding.editTower.text = it?.TowerOrPoleHeight
                        holder.binding.editAdditional.text = it?.AdditionalPoleHeight
                        holder.binding.editTotalWaight.text = it?.TotalWeight
                        holder.binding.editOffsetPoleCount.text = it?.OffsetPoleCount
                        holder.binding.editAntennaSpace.text = it?.AntennaSpace
                        holder.binding.editTimeline.text = it?.Timeline
                    }
                }
            }
            is ViewHold3 -> {
                holder.binding.imgEdit.setOnClickListener() {
                    listener.geoConditionsDetailsItemClicked()
                }
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                    holder.binding.iconLayout.visibility = View.VISIBLE
                } else {
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
                if (fieldData != null && fieldData?.item!!.size > 0 && fieldData?.item!![0].GeoCondition.isNotEmpty()) {

                }

                if (serviceRequestAllData != null && serviceRequestAllData!!.FeasibilityPlanning != null && serviceRequestAllData!!.FeasibilityPlanning?.get(0)?.BackHaul?.isNotEmpty() == true
                ) {
                    serviceRequestAllData!!.FeasibilityPlanning!![0].BackHaul[0].Microwave[0].let {
                        holder.binding.editAntennaCount.text = it.AntennaCount
                        holder.binding.editMaxheight.text = it.MaxHeight
                        holder.binding.editTotalWaight.text = it.TotalWeight
                        holder.binding.editOffsetPoleCount.text = it.OffsetPoleCount
                        holder.binding.editAntennaSpace.text = it.AntennaSpace
                        holder.binding.editTotalWaight.text = it.TotalWeight
                        holder.binding.editOffsetPoleCount.text = it.OffsetPoleCount
                        holder.binding.anteenaSpace.text = it.AntennaSpace
                        holder.binding.offsetPoleCount.text = it.OffsetPoleCount
                        holder.binding.editAntennaSpace.text = it.AntennaSpace
                        holder.binding.editRemark.text = it.Remark

                    }

                    serviceRequestAllData!!.FeasibilityPlanning!!.get(0).BackHaul.get(0).Fiber.get(0).let {
                        holder.binding.editLMLength.text = it.LMLength
                        holder.binding.editCableLength.text = it.CableLength
                        holder.binding.editLayingType.text = ""
                        holder.binding.editFibreCore.text = it.FiberCore
                        holder.binding.editFiberLaying.text = it.FiberLaying
                        holder.binding.editRemark1.text = it.Remark
                    }

                }


            }
            is ViewHold4 -> {
                holder.binding.imgEdit.setOnClickListener() {
                    listener.operationInfoDetailsItemClicked()
                }
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                    holder.binding.iconLayout.visibility = View.VISIBLE
                } else {
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
                if (fieldData != null && fieldData?.item!!.size > 0 && fieldData?.item!![0].SafetyAndAccess.isNotEmpty()) {
//                    val geoCondition: SafetyAndAcces = fieldData?.item!![0].SafetyAndAccess[0]
                }
                if (serviceRequestAllData != null && serviceRequestAllData!!.FeasibilityPlanning != null && serviceRequestAllData!!.FeasibilityPlanning?.get(
                        0
                    )?.PowerAndMCB!!.isNotEmpty()
                ) {
                    serviceRequestAllData!!.FeasibilityPlanning!![0].PowerAndMCB[0].Power.get(0).let {
                        holder.binding.editPowerType.text = it.PowerType
                        holder.binding.editVoltage.text = it.Voltage
                        holder.binding.editMaxTotalPower.text = it.MaxTotalPower
                        holder.binding.editBasicSitePowerrating.text = it.BasicSitePowerRating
                        holder.binding.editAdditionalPower.text = it.AdditionalPower
                        holder.binding.editTotalSitePower.text = it.TotalSitePower
                        holder.binding.editBasicBatteryBackup.text = it.BasicBatteryBackup
                        holder.binding.editAdditionalBatteryBackup.text = it.AdditionalBatteryBackup
                        holder.binding.editTotalBatterybackup.text = it.TotalBatteryBackup
                        holder.binding.editPowerRequirement.text = it.PowerRequirement
                        holder.binding.editTimeline.text = it.Timeline
                        holder.binding.editRemark.text = it.Remark

                    }

                    serviceRequestAllData!!.FeasibilityPlanning!![0].PowerAndMCB[0].MCB[0].let {
                        holder.binding.editMCBRequirement.text = it.MCBRequirement
                        holder.binding.editMCBCount.text = it.TotalMCBCount
                        holder.binding.editRemark.text = it.Remark
                    }

                }

            }

        }
    }

    var currentOpened = -1
    fun updateList(position: Int) {
        currentOpened = if (currentOpened == position) -1 else position
        notifyDataSetChanged()
        if (this.recyclerView != null)
            this.recyclerView?.scrollToPosition(position)
    }

    var recyclerView: RecyclerView? = null


    override fun getItemCount(): Int {
        return list.size
    }

    interface FeasibilityoplanningLisListener {
        fun attachmentItemClicked()
        fun detailsItemClicked()
        fun requestinfoClicked()
        fun operationInfoDetailsItemClicked()
        fun geoConditionsDetailsItemClicked()
        fun siteAccessDetailsItemClicked()
    }
}