package com.smarthub.baseapplication.ui.fragments.services_request.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.serviceRequest.*
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.ui.fragments.services_request.tableAdapters.BackhaulLinkTableAdapter
import com.smarthub.baseapplication.ui.fragments.services_request.tableAdapters.SREquipmentTableAdapter
import com.smarthub.baseapplication.ui.fragments.services_request.tableAdapters.RadioAntinaTableAdapter
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns


class ServicesRequestAdapter(var context :Context,var listener: ServicesRequestLisListener,var serviceRequestAllData: ServiceRequestAllDataItem?) : RecyclerView.Adapter<ServicesRequestAdapter.ViewHold>() {
    var list : ArrayList<String> = ArrayList()
    var type1 = "SR Details"
    var type2 = "Equipments"
    var type3 = "Radio Antennas"
    var type4 = "Backhaul Links"
    var type5 = "Requester Info"
    var type6 = "Attachments"
//    private var data : BasicInfoModelDropDown?=null
    private var servicerequestData : ServiceRequest?=null
    private var SrDetailsData: SRDetails ?=null
    private var BackhaulLinksData: BackHaulLink?=null
    private var RequesterInfoData: RequesterInfo ?=null
    var currentOpened = -1

//    fun setData(data : BasicInfoModelDropDown){
//        this.data = data
//        notifyDataSetChanged()
//    }

    fun updateData(serviceRequestAllData: ServiceRequestAllDataItem?){
        this.serviceRequestAllData = serviceRequestAllData
        notifyDataSetChanged()
    }

    init {
        list.add("SR Details")
        list.add("Equipments")
        list.add("Radio Antennas")
        list.add("Backhaul Links")
        list.add("Requester Info")
        list.add("Attachments")
        try {
            servicerequestData=serviceRequestAllData?.ServiceRequest?.get(0)
            SrDetailsData=servicerequestData?.SRDetails?.get(0)
            BackhaulLinksData=servicerequestData?.BackHaulLinks?.get(0)
            RequesterInfoData=servicerequestData?.RequesterInfo?.get(0)
        }catch (e:java.lang.Exception){
            AppLogger.log("error in site request:${e.localizedMessage}")
        }
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
        else if (list[position] is String && list[position]==type6)
            return 6
        return 0
    }
    class ViewHold1(itemView: View) : ViewHold(itemView) {
        var binding : SrDetailItemViewBinding = SrDetailItemViewBinding.bind(itemView)

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
        var binding: EquipmentsInfoViewBinding = EquipmentsInfoViewBinding.bind(itemView)
        var equipmentTableList: RecyclerView=binding.SrEquipmentTables

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
                addTableItem("dfsdh")
            }
        }
        private fun addTableItem(item:String){
            if (equipmentTableList.adapter!=null && equipmentTableList.adapter is SREquipmentTableAdapter){
                var adapter = equipmentTableList.adapter as SREquipmentTableAdapter
                adapter.addItem()
            }
        }
    }

    class ViewHold3(itemView: View) : ViewHold(itemView) {
        var binding: RadioAntineListItemBinding = RadioAntineListItemBinding.bind(itemView)
        var RadioAnteenaTableList: RecyclerView=binding.SRRadioAnteenaTableItem

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
                addTableItem("dfsdh")
            }
        }
        private fun addTableItem(item:String){
            if (RadioAnteenaTableList.adapter!=null && RadioAnteenaTableList.adapter is RadioAntinaTableAdapter){
                var adapter = RadioAnteenaTableList.adapter as RadioAntinaTableAdapter
                adapter.addItem()
            }
        }
    }

    class ViewHold4(itemView: View) : ViewHold(itemView) {
        var binding : BackhaulLinksItemBinding = BackhaulLinksItemBinding.bind(itemView)
//        var BackhaulLinkTableList: RecyclerView=binding.SRRadioAnteenaTableItem
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

    class ViewHold5(itemView: View) : ViewHold(itemView) {
        var binding : RequestInfoViewBinding = RequestInfoViewBinding.bind(itemView)

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

    class ViewHold6(itemView: View, listener: ServicesRequestLisListener) : ViewHold(itemView) {
        var binding : ServiceRequestSrDetailsAttachmentsBinding = ServiceRequestSrDetailsAttachmentsBinding.bind(itemView)
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

            var recyclerListener = itemView.findViewById<RecyclerView>(R.id.list_item)
            recyclerListener.adapter = adapter

            itemView.findViewById<View>(R.id.attach_card).setOnClickListener {
                adapter.addItem()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_empty,parent,false)
        when (viewType) {
            1 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.sr_detail_item_view, parent, false)
                return ViewHold1(view)
            }
            2 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.equipments_info_view, parent, false)
                return ViewHold2(view)
            }
            3 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.radio_antine_list_item, parent, false)
                return ViewHold3(view)
            }
            4 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.backhaul_links_item, parent, false)
                return ViewHold4(view)
            }
            5 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.request_info_view, parent, false)
                return ViewHold5(view)
            }
            6-> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.service_request_sr_details_attachments, parent, false)
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

                if (SrDetailsData!=null) {
                    holder.binding.imgEdit.setOnClickListener {
                        if (SrDetailsData!=null && serviceRequestAllData!=null)
                            listener.editSrDetailsItemClicked(SrDetailsData!!,serviceRequestAllData!!)
                        else Toast.makeText(context,"data not fetched",Toast.LENGTH_SHORT).show()
                    }
                    AppPreferences.getInstance().setDropDown(holder.binding.SRType,DropDowns.SRType.name,SrDetailsData?.SRType)
                    AppPreferences.getInstance().setDropDown(holder.binding.SRStatus,DropDowns.SRType.name,SrDetailsData?.SRStatus)
                    AppPreferences.getInstance().setDropDown(holder.binding.RequesterCompany,DropDowns.SRDetailRequesterCompany.name,SrDetailsData?.RequesterCompany)
                    AppPreferences.getInstance().setDropDown(holder.binding.RFTechnology,DropDowns.SRDetailTechnology.name,SrDetailsData?.Technology)
                    AppPreferences.getInstance().setDropDown(holder.binding.Priority,DropDowns.Priority.name,SrDetailsData?.Priority)

                    holder.binding.RequestDate.text=SrDetailsData?.RequestDate
                    holder.binding.HubSite.text=SrDetailsData?.HubSite.toString()
                    holder.binding.OPCOSIteName.text=SrDetailsData?.OpcoSiteName
                    holder.binding.OPCOSIteID.text=""
                    holder.binding.OPCOSIteType.text=SrDetailsData?.OpcoSiteType
                    holder.binding.ExpectedDate.text=SrDetailsData?.ExpectedDate
                    holder.binding.nominalsLatLong.text="${SrDetailsData?.locLatitude},${SrDetailsData?.locLongitude}"
                    holder.binding.SearchRadius.text=SrDetailsData?.SearchRadius
                    holder.binding.Circle.text=SrDetailsData?.Circle
                    holder.binding.CityTown.text=SrDetailsData?.CityOrTown
                    holder.binding.Area.text=SrDetailsData?.Area
                    holder.binding.PinCode.text=SrDetailsData?.Pincode
                }
            }
            is ViewHold2 -> {
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                    holder.binding.imgAdd.visibility = View.VISIBLE
                }
                else {
                    holder.binding.itemTitleStr.tag = false
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
                    if(servicerequestData!=null && servicerequestData?.Equipments!=null) {
                        holder.equipmentTableList.adapter = serviceRequestAllData?.let {
                            SREquipmentTableAdapter(
                                context,
                                listener,
                                servicerequestData?.Equipments as ArrayList<Equipment>,
                                it
                            )
                        }
                    }
                }catch (e:java.lang.Exception){
                    AppLogger.log("e:${e.localizedMessage}")
                }
                holder.binding.imgAdd.setOnClickListener {
                    listener.editEquipmentClicked(null,null,null)
                }
            }
            is ViewHold3 -> {
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                    holder.binding.imgAdd.visibility = View.VISIBLE
                }
                else {
                    holder.binding.itemTitleStr.tag = false
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
                 holder.RadioAnteenaTableList.adapter= servicerequestData?.RadioAntennas?.let {
                     RadioAntinaTableAdapter(context,listener,
                         it as ArrayList<RadioAntenna>,serviceRequestAllData!!)
                 }
                holder.binding.imgAdd.setOnClickListener {
                    listener.editEquipmentClicked(null,null,null)
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

//                holder.BackhaulLinkTableList.setHasFixedSize(true)
//                holder.BackhaulLinkTableList.adapter=BackhaulLinkTableAdapter(context,listener)

                holder.binding.imgAdd.setOnClickListener {
                    listener.editEquipmentClicked(null,null,null)
                }
            }
            is ViewHold5 -> {
                holder.binding.imgEdit.setOnClickListener {
                    if (SrDetailsData!=null) {
                        listener.editRequestInfoClicked(RequesterInfoData!!,serviceRequestAllData!!)
                    }
                }
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                    holder.binding.imgEdit.visibility = View.VISIBLE
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
                if(RequesterInfoData!=null){
                    holder.binding.RequesterExcutiveName.text=RequesterInfoData?.RequesterExecutiveName
                    holder.binding.EmailId.text=RequesterInfoData?.EmailID
                    holder.binding.PhoneNumber.text=RequesterInfoData?.PhoneNumber
                }
            }
            is ViewHold6 -> {
//                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
//                }
//                else {
//                    holder.binding.collapsingLayout.tag = false
//                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
//                    holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
//                    holder.binding.itemLine.visibility = View.VISIBLE
//                    holder.binding.itemCollapse.visibility = View.GONE
//                }
                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
                holder.binding.itemTitleStr.text = list[position]
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
    interface ServicesRequestLisListener {
        fun attachmentItemClicked()
        fun editSrDetailsItemClicked(srDetailsData: SRDetails,serviceRequestAllData: ServiceRequestAllDataItem)
        fun editBackhaulLinkItemClicked()
        fun editRequestInfoClicked(
            backhaulLinksData: RequesterInfo,
            serviceRequestAllData: ServiceRequestAllDataItem?
        )
        fun editEquipmentClicked(
            equipment: Equipment?,
            serviceRequestAllData: ServiceRequestAllDataItem?,
            s: String?
        )
        fun viewEquipmentClicked(position:Int)
        fun editRadioAnteenaClicked( equipment: RadioAntenna,
                                     serviceRequestAllData: ServiceRequestAllDataItem,
                                     s: String)
        fun viewRadioAnteenaClicked(position:Int)

    }
}