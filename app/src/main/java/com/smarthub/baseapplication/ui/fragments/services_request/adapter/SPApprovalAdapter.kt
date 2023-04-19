package com.smarthub.baseapplication.ui.fragments.services_request.adapter

import com.smarthub.baseapplication.model.serviceRequest.SODetail
import com.smarthub.baseapplication.model.serviceRequest.SPApproval
import com.smarthub.baseapplication.model.serviceRequest.SPApprovalAndSO
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.SpApprovalItemViewBinding
import com.smarthub.baseapplication.databinding.SrApprovalItemViewBinding
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem
import com.smarthub.baseapplication.model.siteInfo.SiteInfoModel
import com.smarthub.baseapplication.network.pojo.site_info.BasicInfoModelDropDown

class SPApprovalAdapter(
    var context: Context,
    var listener: SPSoftLisListener,
    var serviceRequestAllData: ServiceRequestAllDataItem
) : RecyclerView.Adapter<SPApprovalAdapter.ViewHold>() {
    var list: ArrayList<String> = ArrayList()
    var type1 = "SP Approval"
    var type2 = "SO Details"
    var spApprovalAndSO: SPApprovalAndSO? = null
    var soDetail: SODetail? = null
    var spApproval: SPApproval? = null
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
        list.add("SP Approval")
        list.add("SO Details")
        list.add("Equipments")
        list.add("Power & MCB")
        list.add("Attachments")
        list.add("TSSR Executive Info")
        spApprovalAndSO = serviceRequestAllData.SPApprovarAndSO?.get(0)
        soDetail = spApprovalAndSO?.SODetails?.get(0)
        spApproval = spApprovalAndSO?.SPApproval?.get(0)
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun getItemViewType(position: Int): Int {
        if (list[position] is String && list[position] == type1)
            return 1
        else if (list[position] is String && list[position] == type2)
            return 2
        return 0
    }

    class ViewHold1(itemView: View) : ViewHold(itemView) {
        var binding: SpApprovalItemViewBinding = SpApprovalItemViewBinding.bind(itemView)

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
        var binding: SrApprovalItemViewBinding = SrApprovalItemViewBinding.bind(itemView)

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_empty, parent, false)
        when (viewType) {
            1 -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.sp_approval_item_view, parent, false)
                return ViewHold1(view)
            }
            2 -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.sr_approval_item_view, parent, false)
                return ViewHold2(view)
            }


        }
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        when (holder) {
            is ViewHold1 -> {
                holder.binding.imgEdit.setOnClickListener {
//                    listener.detailsItemClicked(spApproval!!,serviceRequestAllData)
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
               try{
                if (spApproval != null) {
                    spApproval.let {
                        holder.binding.txtSPSubmissionDate.text = it!!.SPSubmissionDate
                        holder.binding.txtSPApprovalDate.text = it!!.SPApprovalDate
                        holder.binding.txtApprovedBy.text = it!!.ApprovedBy
                        holder.binding.txtApproverEmailID.text = it!!.ApproverEmailID
                    }
                }
               }catch (e:Exception){
                   e.printStackTrace()
               }

            }
            is ViewHold2 -> {
                holder.binding.imgEdit.setOnClickListener {
//                    listener.requestinfoClicked(soDetail!!,serviceRequestAllData)
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

                try{
                    if (soDetail != null) {
                        soDetail.let {
                            holder.binding.sonumber.text = it!!.SONumber
                            holder.binding.txtSoDate.text = it!!.SODate
                            holder.binding.txtSOValue.text =it!!.SOValue
                            holder.binding.txtSOLine.text = it!!.SOLineItemNo
                            holder.binding.txtRemarks.text =it!!.Remark
                        }
                    }
                }catch (e:Exception){
                    e.printStackTrace()
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

    interface SPSoftLisListener {
        fun attachmentItemClicked()
        fun detailsItemClicked(
            spApproval: SPApproval,
            serviceRequestAllData: ServiceRequestAllDataItem
        )
        fun requestinfoClicked(soDetail: SODetail, serviceRequestAllData: ServiceRequestAllDataItem)
        fun operationInfoDetailsItemClicked()
        fun geoConditionsDetailsItemClicked()
        fun siteAccessDetailsItemClicked()
    }
}