package com.smarthub.baseapplication.ui.site_agreement.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter

class AgrementLeaseListAdapter(var context:Context,
    var listener: AgreementListItemlistner,
    var servicerequirement: ServiceRequestAllDataItem
) :
    RecyclerView.Adapter<AgrementLeaseListAdapter.ViewHold>() {
    var list: ArrayList<String> = ArrayList()
    var AGREMENT_VIEW_TYPE = 0
    var PROPERTY_VIEW_TYPE = 1
    var ATTACHMENT_VIEW_TYPE =2
    init {
        list.add("Agreement")
        list.add("Property Owner & SAPaymentFrag..")
        list.add("Attachments")

      }
    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)
    class AgreementViewHold(itemView: View) : ViewHold(itemView) {
        var binding: AgreementsListItemBinding= AgreementsListItemBinding.bind(itemView)


        init {
            binding.itemTitle.tag = false
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }


        }
    }
    class PropertyAgreementViewHold(itemView: View) :ViewHold(itemView) {
        var binding: SaPropertyInfoViewBinding =
            SaPropertyInfoViewBinding.bind(itemView)
        init {
            binding.itemTitle.tag = false
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }



        }

    }
    class AttachmentViewHold(itemView: View,listener: AgreementListItemlistner) : ViewHold(itemView) {
        var binding: AttachmentListItemBinding = AttachmentListItemBinding.bind(itemView)
        var adapter =  ImageAttachmentAdapter(object : ImageAttachmentAdapter.ItemClickListener{
            override fun itemClicked() {
                listener.attachmentItemClicked()
            }
        })
        init {
            binding.itemTitle.tag = false
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
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
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.agreements_list_item, parent, false)
        return when (viewType) {
          AGREMENT_VIEW_TYPE -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.agreements_list_item, parent, false)
                AgreementViewHold(view)
            }
                PROPERTY_VIEW_TYPE -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.sa_property_info_view, parent, false)
                PropertyAgreementViewHold(view)
            }
             ATTACHMENT_VIEW_TYPE -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.attachment_list_item, parent, false)
                AttachmentViewHold(view,listener)
            }

            else -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.agreements_list_item, parent, false)
                ViewHold(view)
            }
        }
    }
    override fun getItemViewType(position: Int): Int {
        return if (list[position] == "Agreement") AGREMENT_VIEW_TYPE

        else if (list[position] == "Property Owner & SAPaymentFrag..") PROPERTY_VIEW_TYPE

        else if (list[position] == "Attachments") ATTACHMENT_VIEW_TYPE
      else 0
    }
    override fun onBindViewHolder(holder: ViewHold, position: Int) {
       if (holder is AgreementViewHold) {
            holder.binding.collapsingLayout.setOnClickListener {
                holder.binding.itemTitle.tag = !(holder.binding.itemTitle.tag as Boolean)
                if ((holder.binding.itemTitle.tag as Boolean)) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                } else {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                    holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                }

                holder.binding.itemLine.visibility =
                    if (holder.binding.itemTitle.tag as Boolean) View.GONE else View.VISIBLE
                holder.binding.iconLayout.visibility =
                    if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE
                holder.binding.imgEdit.setOnClickListener()
                {
                    listener.detailsItemClicked()
                }
                holder.binding.itemCollapse.visibility =
                    if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE
            }
            holder.binding.itemTitle.text = list[position]

           if(servicerequirement.SoftAcquisition!=null && servicerequirement.SoftAcquisition!!.isNotEmpty()) {
               servicerequirement.SoftAcquisition?.get(0)?.AgreementTerms!![0].let{
                   holder.binding.agrementType.text = it.AgreementPeriod
                   holder.binding.registrationNumber.text = ""
                   holder.binding.registrationDate.text = ""
                   holder.binding.bookingCostCentre.text= ""
                   holder.binding.agreemenPeriod.text = it.AgreementPeriod
                   holder.binding.lockPeriod.text = it.LockInPeriod
                   holder.binding.agreemenEffectiveDate.text = ""
                   holder.binding.agreementExpiryDate.text = ""
                   holder.binding.rentStartDate.text = ""
                   holder.binding.initialAnnualRentAmount.text = ""

                   holder.binding.rentPaymentFrequency.text = ""
                   holder.binding.periodicRentPaybleAmount.text = it.PeriodicRentPayableAmount
                   holder.binding.rentEscalation.text = ""
                   holder.binding.rentEscalationPeriod.text = it.RentEscalationPeriod
                   holder.binding.lastEscalationDate.text = ""
                   holder.binding.lastRevisedRentAmount.text = ""
                   holder.binding.eBInclusiveRental.text = ""
                   holder.binding.eBBillLimit.text = ""
                   holder.binding.eBBillingBasis.text = it.EBBillingBasis
                   holder.binding.eBperunitRate.text = it.EBPerUnitRate
                   holder.binding.propertyOwnership.text = it.PropertyOwnership
                   holder.binding.propertyAcquired.text = ""

                   holder.binding.onetimeAmount.text = it.OnetimeAmount
                   holder.binding.securityDepositeAmount.text = it.SecurityDepositAmount
                   holder.binding.rooftopAcquiredArea.text = ""
                   holder.binding.groundAcquiredArea.text = ""



               }
           }
        }
       else if (holder is PropertyAgreementViewHold) {
       holder.binding.collapsingLayout.setOnClickListener {
                holder.binding.itemTitle.tag = !(holder.binding.itemTitle.tag as Boolean)
                if ((holder.binding.itemTitle.tag as Boolean)) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                } else {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                    holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                }

                holder.binding.itemLine.visibility =
                    if (holder.binding.itemTitle.tag as Boolean) View.GONE else View.VISIBLE
                holder.binding.iconLayout.visibility =
                    if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE

                holder.binding.itemCollapse.visibility =
                    if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE
            }
       holder.binding.itemTitle.text = list[position]
           holder.binding.rvSaPropertylist.layoutManager = LinearLayoutManager(context)
//           holder.binding.propertyDetailsTable.adapter = PropertyAgreementTableAdapter()
        }


         else if (holder is AttachmentViewHold) {
            holder.binding.collapsingLayout.setOnClickListener {
                holder.binding.itemTitle.tag = !(holder.binding.itemTitle.tag as Boolean)
                if ((holder.binding.itemTitle.tag as Boolean)) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                } else {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                    holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                }

                holder.binding.itemLine.visibility =
                    if (holder.binding.itemTitle.tag as Boolean) View.GONE else View.VISIBLE
                holder.binding.iconLayout.visibility =
                    if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE

                holder.binding.itemCollapse.visibility =
                    if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE
            }
            holder.binding.itemTitle.text = list[position]
         }

    }
    override fun getItemCount(): Int {
        return list.size
    }
    interface AgreementListItemlistner {
        fun attachmentItemClicked()
        fun detailsItemClicked()
    }
}