package com.smarthub.baseapplication.ui.fragments.noc
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.model.siteInfo.nocAndCompModel.ApplicationInitial
import com.smarthub.baseapplication.model.siteInfo.nocAndCompModel.NocAndCompAllDataItem
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.ui.fragments.noc.tableAdapters.NocFeePayTableAdapter
import com.smarthub.baseapplication.ui.fragments.noc.tableAdapters.NocPoTableAdapter
import com.smarthub.baseapplication.utils.AppLogger

class NocListAdapter(var context: Context, var listner: NocDetailsActivity,NocAndCompAllData: NocAndCompAllDataItem?) : RecyclerView.Adapter<NocListAdapter.ViewHold>() {

    var list : ArrayList<String> = ArrayList()
    var currentOpened = -1
    private var applicationDetailsData: ApplicationInitial?=null
    var type1 = "Application Details"
    var type2 = "Authority Details"
    var type3 = "PO Details"
    var type4 = "Fee & SAPaymentFrag Details"
    var type5 = "Documents Submitted to Authority"
    var type6 = "NOC / Compliance Copy"


    init {
        list.add("Application Details")
        list.add("Authority Details")
        list.add("PO Details")
        list.add("Fee & SAPaymentFrag Details")
        list.add("Documents Submitted to Authority")
        list.add("NOC / Compliance Copy")
        try {
            applicationDetailsData=NocAndCompAllData?.ApplicationInitial?.get(0)
        }catch (e:java.lang.Exception){
            Toast.makeText(context,"NocDataAdapter error :${e.localizedMessage}", Toast.LENGTH_LONG).show()
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
        var binding : NocApplicationDeatilsBinding = NocApplicationDeatilsBinding.bind(itemView)
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
        var binding : NocAuthorityDetailsBinding = NocAuthorityDetailsBinding.bind(itemView)

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

    class ViewHold3(itemView: View) : ViewHold(itemView) {
        var binding : NocPoTableBinding = NocPoTableBinding.bind(itemView)
        var NocPoTableList : RecyclerView = binding.root.findViewById(R.id.noc_po_tables)

        init {
            binding.collapsingLayout.tag = false
            if ((binding.collapsingLayout.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
            }

            binding.imgAdd.setOnClickListener {
                addTableItem("gsfbgksf")
            }
        }
        private fun addTableItem(item:String){
            if (NocPoTableList.adapter!=null && NocPoTableList.adapter is NocPoTableAdapter){
                var adapter = NocPoTableList.adapter as NocPoTableAdapter
                adapter.addItem(item)
            }
        }
    }
    class ViewHold4(itemView: View) : ViewHold(itemView) {
        var binding : NocFeePaymentTableBinding = NocFeePaymentTableBinding.bind(itemView)
        var NocFeePayTableList : RecyclerView = binding.root.findViewById(R.id.noc_feePay_tables)
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
        private fun addTableItem(item:String){
            if (NocFeePayTableList.adapter!=null && NocFeePayTableList.adapter is NocFeePayTableAdapter){
                var adapter = NocFeePayTableList.adapter as NocFeePayTableAdapter
                adapter.addItem(item)
            }
        }
    }

    class ViewHold5(itemView: View,listener: NOCListListener) : ViewHold(itemView) {
        var binding: NocDocSubmitedToAuthorityBinding = NocDocSubmitedToAuthorityBinding.bind(itemView)

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
    class ViewHold6(itemView: View,listener: NOCListListener) : ViewHold(itemView) {
        var binding: NocCompilenceCopyBinding = NocCompilenceCopyBinding.bind(itemView)

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
                view = LayoutInflater.from(parent.context).inflate(R.layout.noc_application_deatils, parent, false)
                return ViewHold1(view)
            }
            2 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.noc_authority_details, parent, false)
                return ViewHold2(view)
            }
            3 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.noc_po_table, parent, false)
                return ViewHold3(view)
            }
            4 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.noc_fee_payment_table, parent, false)
                return ViewHold4(view)
            }
            5 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.noc_doc_submited_to_authority, parent, false)
                return ViewHold5(view,listner)
            }
            6 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.noc_compilence_copy, parent, false)
                return ViewHold6(view,listner)
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
                        listner.EditAppDetailsItem()
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
                    holder.binding.CatagoryInitial.text="Data NotFound"
                    holder.binding.ApplicationType.text="Data NotFound"
                    holder.binding.ApplicationNameInitial.text=applicationDetailsData?.ApplicationName
                    holder.binding.ApplicationNumber.text=applicationDetailsData?.ApplicationNumber
                    holder.binding.ApplicationStatus.text="Data Not found"
                    holder.binding.ApplicationDate.text=applicationDetailsData?.ApplicationDate
                    holder.binding.IssueDate.text=applicationDetailsData?.IssueDate
                    holder.binding.DocumentNumber.text=applicationDetailsData?.DocumentNo
                    holder.binding.ExpiryDate.text=applicationDetailsData?.ExpiryDate
                    holder.binding.VendorName.text=applicationDetailsData?.VendorName
                    holder.binding.VendorExecutive.text=applicationDetailsData?.VendorExecutive
                    holder.binding.VendorPhoneNo.text=applicationDetailsData?.VendorPhoneNo
                    holder.binding.ApplicationTypeRenewal.text="Data Not Found"
                    holder.binding.ApplicationNameRenewal.text=applicationDetailsData?.ApplicationName2
                    holder.binding.ApplicationNumberRenewal.text=applicationDetailsData?.ApplicationNumber2
                    holder.binding.DocumentNoRenewal.text=applicationDetailsData?.DocumentNo2
                    holder.binding.VendorNameRenewal.text=applicationDetailsData?.VendorName2
                    holder.binding.VendorExecutiveRenewal.text=applicationDetailsData?.VendorExecutive2
                    holder.binding.VendorPhoneNoRenewal.text=applicationDetailsData?.VendorPhoneNo2
                    holder.binding.applicationStatusRenewal.text="Data Not Found"
                    holder.binding.renewalDate.text=applicationDetailsData?.RenewalDate2
                    holder.binding.ExpiryDate.text=applicationDetailsData?.ExpiryDate2
                }catch (e:java.lang.Exception){
                    AppLogger.log("NocDataAdapter error : ${e.localizedMessage}")
                    Toast.makeText(context,"NocDataAdapter error :${e.localizedMessage}",Toast.LENGTH_LONG).show()

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
                        listner.EditAuthorityDetails()
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
                holder.NocPoTableList.adapter = NocPoTableAdapter( context,listner)

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
                holder.NocFeePayTableList.adapter = NocFeePayTableAdapter( context,listner)
            }
            is ViewHold5 -> {
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

    interface NOCListListener {
        fun attachmentItemClicked()
        fun EditAuthorityDetails()
        fun EditAppDetailsItem()
        fun editPoClicked(position:Int)
        fun viewPoClicked(position:Int)
        fun editfeePaymentClicked(position:Int)
        fun viewfeePaymentClicked(position:Int)

    }
}