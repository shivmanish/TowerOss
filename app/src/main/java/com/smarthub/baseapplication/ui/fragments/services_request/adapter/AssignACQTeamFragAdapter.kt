package com.smarthub.baseapplication.ui.fragments.services_request.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.model.serviceRequest.AssignACQTeam
import com.smarthub.baseapplication.model.serviceRequest.AssignACQTeamTeam
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.utils.AppLogger

class AssignACQTeamFragAdapter(var listener: AssignAcqTeamListItemListner, var serviceRequestAllData: ServiceRequestAllDataItem?) : RecyclerView.Adapter<AssignACQTeamFragAdapter.ViewHold>() {

    var currentOpened = -1
    var list: ArrayList<String> = ArrayList()
    private var  AssignAcqTeamData: AssignACQTeam?=null
    private var AssignAcqTeamDetailsData: AssignACQTeamTeam?=null
    var type1="Details"
    var type2="Attachments"

    init {
        list.add("Details")
        list.add("Attachments")
        try {
            AssignAcqTeamData=serviceRequestAllData?.AssignACQTeam?.get(0)
            AssignAcqTeamDetailsData=AssignAcqTeamData?.AssignACQTeamTeam?.get(0)
        } catch (e: Exception){
           AppLogger.log("Error in Assign ACQ fragment ${e.localizedMessage}")
        }
      }
    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)
    class DetailsViewHold(itemView: View) : ViewHold(itemView) {
        var binding: AssignAcqTeamDetailsItemBinding = AssignAcqTeamDetailsItemBinding.bind(itemView)

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
    class AttachmentViewHold(itemView: View,listener: AssignAcqTeamListItemListner) : ViewHold(itemView) {
        var binding: ServiceRequestTeamvendorAttachmentBinding = ServiceRequestTeamvendorAttachmentBinding.bind(itemView)
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

        return when (viewType) {
            1 -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.team_vendor_list_item, parent, false)
                DetailsViewHold(view)
            }
            2 -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.service_request_teamvendor_attachment, parent, false)
                AttachmentViewHold(view,listener)
            }

            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.team_vendor_list_item, parent, false)
                ViewHold(view)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position]==type1)
            1
        else if (list[position]==type2)
            2
        else 0
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        if (holder is DetailsViewHold) {
            holder.binding.imgEdit.setOnClickListener {
                listener.EditdetailsItemClicked(AssignAcqTeamData,serviceRequestAllData)
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
            if(AssignAcqTeamDetailsData!=null){
                holder.binding.AcquisitionExecutiveName.text=AssignAcqTeamDetailsData?.ExecutiveName
                holder.binding.ExecutiveEmailID.text=AssignAcqTeamDetailsData?.ExecutiveEmailId
                holder.binding.ExecutiveNumber.text=AssignAcqTeamDetailsData?.ExecutiveMobile
                holder.binding.AcquisitionLeadName.text=AssignAcqTeamDetailsData?.LeadName
                holder.binding.LeadEmailId.text=AssignAcqTeamDetailsData?.LeadEmailId
                holder.binding.Number.text=AssignAcqTeamDetailsData?.LeadMobile
                holder.binding.AcquisitionMode.text=AssignAcqTeamDetailsData?.AcquistionMode
                holder.binding.AcquisitionType.text=AssignAcqTeamDetailsData?.AcquisitionType
                holder.binding.AcquisitionBudget.text=AssignAcqTeamDetailsData?.AcquisitionBudget
                holder.binding.AcquisitionTargetDate.text=AssignAcqTeamDetailsData?.AcquisitionTargetDate
//                holder.binding.VendorNameAssignACQ.text="AssignAcqTeamDetailsData?.VendorName!!"
                holder.binding.PONumber.text=AssignAcqTeamDetailsData?.PONumber
                holder.binding.PoAmount.text=AssignAcqTeamDetailsData?.POAmount
                holder.binding.VendorExcutiveName.text=AssignAcqTeamDetailsData?.VendorExecutiveName
                holder.binding.VendorExecutiveEmail.text=AssignAcqTeamDetailsData?.VendorExecutiveEmailId
                holder.binding.VendorExecutiveNumber.text=AssignAcqTeamDetailsData?.VendorExecutiveMobile
                holder.binding.OfficeAddress.text=AssignAcqTeamDetailsData?.OfficeAddress
                holder.binding.Remmarks.text=AssignAcqTeamDetailsData?.Remark
            }
        }

       else if (holder is AttachmentViewHold) {
            if (currentOpened == position) {
                holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                holder.binding.itemLine.visibility = View.GONE
                holder.binding.itemCollapse.visibility = View.VISIBLE
//            }
//            else {
//                holder.binding.collapsingLayout.tag = false
//                holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
//                holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
//                holder.binding.itemLine.visibility = View.VISIBLE
//                holder.binding.itemCollapse.visibility = View.GONE
//            }
//            holder.binding.collapsingLayout.setOnClickListener {
//                updateList(position)
//            }
            holder.binding.itemTitleStr.text = list[position]
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


    interface AssignAcqTeamListItemListner {
        fun attachmentItemClicked()
        fun EditdetailsItemClicked(
            AssignAcqTeamData: AssignACQTeam?,
            serviceRequestAllData: ServiceRequestAllDataItem?
        )
    }
}