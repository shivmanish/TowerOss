package com.smarthub.baseapplication.ui.site_agreement.tableadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.SaAgreementsItemViewBinding
import com.smarthub.baseapplication.databinding.SaAttachmentsBinding
import com.smarthub.baseapplication.databinding.SaPoInfoViewBinding
import com.smarthub.baseapplication.databinding.SaPropertyInfoViewBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteInfo.siteAgreements.SiteacquisitionAgreement
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns

class SANominalsFragmentAdapter(
    var context: Context,
    var listener: PoTableAdapter.PoInfoListListener? = null,
    var allData: List<SiteacquisitionAgreement>?,
    var listener2: PropertyOwenerTableAdapter.PropertyOwenerInfoListListener? = null,
) :
    RecyclerView.Adapter<SANominalsFragmentAdapter.ViewHold>() {

    var list: ArrayList<String> = ArrayList()
    var type1 = "Agreements"
    var type2 = "Property Owner & Payments Details"
    var type3 = "PO Details"
    var type4 = "Attachments"
    var currentOpened = -1
    private var siteAgreementsData: SiteacquisitionAgreement? = null


    init {
        list.add("Agreements")
        list.add("Property Owner & Payments Details")
        list.add("PO Details")
        list.add("Attachments")
        siteAgreementsData = allData?.get(0)


    }


    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)
    class AgreemetViewHold(itemView: View) : ViewHold(itemView) {
        var binding: SaAgreementsItemViewBinding = SaAgreementsItemViewBinding.bind(itemView)

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

    class POViewHold(itemView: View) : ViewHold(itemView) {
        var binding: SaPoInfoViewBinding = SaPoInfoViewBinding.bind(itemView)

        var paymentList: RecyclerView = binding.root.findViewById(R.id.paylist)

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
                addTableItem("gsfbgksf")
            }
        }

        private fun addTableItem(item: String) {
            if (paymentList.adapter != null && paymentList.adapter is PoTableAdapter) {
                var adapter = paymentList.adapter as PoTableAdapter
                adapter.addItem()
            }
        }
    }

    class PropertyViewHold(itemView: View) : ViewHold(itemView) {
        var binding: SaPropertyInfoViewBinding = SaPropertyInfoViewBinding.bind(itemView)

        var ownerList: RecyclerView = binding.root.findViewById(R.id.ownerList)

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
                addTableItem()

            }
        }

        fun addTableItem() {
            if (ownerList.adapter != null && ownerList.adapter is PropertyOwenerTableAdapter) {
                var adapter = ownerList.adapter as PropertyOwenerTableAdapter
                adapter.addItem()

            }
        }
    }

    class AttachmentViewHold(itemView: View, listener: PoTableAdapter.PoInfoListListener) :
        ViewHold(itemView) {
        var binding: SaAttachmentsBinding = SaAttachmentsBinding.bind(itemView)
        var adapter = ImageAttachmentAdapter(object : ImageAttachmentAdapter.ItemClickListener {
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
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.sa_agreements_item_view, parent, false)
                AgreemetViewHold(view)
            }
            2 -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.sa_property_info_view, parent, false)
                PropertyViewHold(view)
            }
            3 -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.sa_po_info_view, parent, false)
                POViewHold(view)
            }
            4 -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.sa_attachments, parent, false)
                AttachmentViewHold(view, listener!!)
            }

            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.nominals_list_item, parent, false)
                ViewHold(view)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (list[position] is String && list[position] == type1)
            return 1
        else if (list[position] is String && list[position] == type2)
            return 2
        else if (list[position] is String && list[position] == type3)
            return 3
        else if (list[position] is String && list[position] == type4)
            return 4
        else return 0
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        when (holder) {
            is AgreemetViewHold -> {
                holder.binding.imgEdit.setOnClickListener {
                    listener!!.AgreementEditViewClick()
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

                AppPreferences.getInstance().setDropDown(
                    holder.binding.textRegistrationNumber,
                    DropDowns.RegistrationNumber.name, siteAgreementsData?.RegistrationNumber
                )
                AppPreferences.getInstance().setDropDown(
                    holder.binding.textRegistrationNumber,
                    DropDowns.BookingCostCentre.name, siteAgreementsData?.BookingCostCentre
                )

                    AppPreferences.getInstance().setDropDown(
                        holder.binding.textBookingCostCentre,
                        DropDowns.BookingCostCentre.name, siteAgreementsData?.BookingCostCentre
                    )
                    AppPreferences.getInstance().setDropDown(
                        holder.binding.textRentPaymentFrequency,
                        DropDowns.RentPaymentFrequency.name, siteAgreementsData?.RentPaymentFrequency
                    )
                    AppPreferences.getInstance().setDropDown(
                        holder.binding.textEBInclusiveRental,
                        DropDowns.EBInclusiveinRental.name, siteAgreementsData?.EBInclusiveinRental
                    )
                       AppPreferences.getInstance().setDropDown(
                        holder.binding.textEBBillLimit,
                        DropDowns.EBBillLimit.name, siteAgreementsData?.EBBillLimit
                    )

                       AppPreferences.getInstance().setDropDown(
                        holder.binding.textPropertyOwnership,
                        DropDowns.PropertyOwnership.name, siteAgreementsData?.PropertyOwnership
                    )
                    AppPreferences.getInstance().setDropDown(
                        holder.binding.textPropertyAcquired,
                        DropDowns.PropertyAcquired.name, siteAgreementsData?.PropertyAcquired
                    )
                holder.binding.txtTermLease.text = siteAgreementsData?.AgreementType
/*
               holder.binding.textRegistrationNumber.setText(siteAgreementsData?.RegistrationNumber)
*/
                holder.binding.textRegistrationDate.text = siteAgreementsData?.RegistrationDate
                /*  holder.binding.textBookingCostCentre.text = siteAgreementsData?.BookingCostCentre
                  holder.binding.textEBBillLimit.text = siteAgreementsData?.EBBillLimit
                  holder.binding.textEBBillingBasis.text = siteAgreementsData?.EBBillingBasis
                  holder.binding.textEBInclusiveRental.text = siteAgreementsData?.EBInclusiveinRental
               */   holder.binding.txtEBperunitRate.text = siteAgreementsData?.EBPerUnitRate
                holder.binding.txtUsableArea.text = siteAgreementsData?.GroundUsableArea
                holder.binding.txtInitialAnnualRentAmount.text =
                    siteAgreementsData?.InitialAnnualRentAmount
                holder.binding.txtLastRevisedRentAmount.text =
                    siteAgreementsData?.LastRevisedRentAmount
                holder.binding.txtLastEscalationDate.text = siteAgreementsData?.LastescalationDate
                holder.binding.txtLockPeriod.text = siteAgreementsData?.LockInPeriod
                holder.binding.txtOnetimeAmount.text = siteAgreementsData?.OnetimeAmount
/*
               holder.binding.textRentPaymentFrequency.setText(siteAgreementsData?.PeriodicRentPayableAmount)
*/
                holder.binding.textPropertyOwnership.text = siteAgreementsData?.RentStartDate
                holder.binding.textPropertyAcquired.text = siteAgreementsData?.PropertyAcquired
                holder.binding.textPropertyOwnership.text = siteAgreementsData?.PropertyOwnership
                holder.binding.textRegistrationDate.text = siteAgreementsData?.RegistrationDate
                holder.binding.textRegistrationNumber.text = siteAgreementsData?.RegistrationNumber
                holder.binding.txtRentEscalation.text = siteAgreementsData?.RentEscalation
                holder.binding.txtRentEscalationPeriod.text =
                    siteAgreementsData?.RentEscalationPeriod
                holder.binding.textRentPaymentFrequency.text =
                    siteAgreementsData?.RentPaymentFrequency
                holder.binding.txtRentStartDate.text = siteAgreementsData?.RentStartDate
                holder.binding.txtSecurityAmount.text = siteAgreementsData?.SecurityDepositAmount
                holder.binding.txtRooftopArea.text = siteAgreementsData?.RooftopUsableArea
                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
                holder.binding.itemTitleStr.text = list[position]
            }
            is POViewHold -> {
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                    holder.binding.imgAdd.visibility = View.VISIBLE

                } else {
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

                    holder.paymentList.adapter =
                        PoTableAdapter(
                            context,
                            listener!!,
                            ArrayList(siteAgreementsData?.PODetails)
                        )
                } catch (e: java.lang.Exception) {
                    AppLogger.log("ToewerInfoadapter error : ${e.localizedMessage}")
                }
            }
            is PropertyViewHold -> {
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                    holder.binding.imgAdd.visibility = View.VISIBLE

                } else {
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
                    holder.ownerList.adapter =
                        PropertyOwenerTableAdapter(
                            context,
                            listener2!!,
                            ArrayList(siteAgreementsData?.PropertyOwnerPaymentDetails)
                        )
                } catch (e: java.lang.Exception) {
                    AppLogger.log("ToewerInfoadapter error : ${e.localizedMessage}")
                }
            }
            is AttachmentViewHold -> {
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                } else {
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

    var recyclerView: RecyclerView? = null
    fun updateList(position: Int) {
        currentOpened = if (currentOpened == position) -1 else position
        notifyDataSetChanged()
        if (this.recyclerView != null)
            this.recyclerView?.scrollToPosition(position)
    }

}