package com.smarthub.baseapplication.ui.fragments.siteAcquisition.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AcqAgreementTermItemsBinding
import com.smarthub.baseapplication.databinding.AcqPayeeAcountTableBinding
import com.smarthub.baseapplication.databinding.TowerAttachmentInfoBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.NewSiteAcquiAllData
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.SAcqPayeeAccountDetail
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.SoftAcqAgreementTerm
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.SoftAcquisitionData
import com.smarthub.baseapplication.ui.fragments.ImageAttachmentCommonAdapter
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.tableAdapters.PayeeAccountTableAdapter
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns

class SoftAcquisitionFragAdapter(var context: Context, var listener: SoftAcqListListener, data:NewSiteAcquiAllData?) : RecyclerView.Adapter<SoftAcquisitionFragAdapter.ViewHold>() {
    private var datalist: SoftAcquisitionData?=null
    private var agreeData: SoftAcqAgreementTerm?=null

    fun setData(data: SoftAcquisitionData?) {
        this.datalist=data!!
        notifyDataSetChanged()
    }
    init {
        try {
            if (data!=null && data.SAcqAssignACQTeam?.isNotEmpty()!!){
                datalist=data.SAcqSoftAcquisition?.get(0)
            }
        }catch (e:java.lang.Exception){
            AppLogger.log("TowerInfoFrag error :${e.localizedMessage}")
        }
    }



    var list : ArrayList<String> = ArrayList()
    var currentOpened = -1
    var type1 = "Agreement Terms"
    var type2 = "Payee Account Detail"
    var type3 = "Attachments"

    init {
        list.add("Agreement Terms")
        list.add("Payee Account Detail")
        list.add("Attachments")
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)

    class ViewHold1(itemView: View) : ViewHold(itemView) {
        var binding : AcqAgreementTermItemsBinding = AcqAgreementTermItemsBinding.bind(itemView)

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
        var binding : AcqPayeeAcountTableBinding = AcqPayeeAcountTableBinding.bind(itemView)
        var payeeTableList: RecyclerView=binding.payeeAccountTableItem

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
                addPayeeAccountTableItem()
            }
        }

        private fun addPayeeAccountTableItem(){
            if (payeeTableList.adapter!=null && payeeTableList.adapter is PayeeAccountTableAdapter){
                val adapter = payeeTableList.adapter as PayeeAccountTableAdapter
                adapter.addItem()
            }
        }

    }
    class ViewHold3(itemView: View,listener: SoftAcqListListener) : ViewHold(itemView) {
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
//
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
        else if (list[position]==type3)
            return 3
        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_empty,parent,false)
        when (viewType) {
            1 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.acq_agreement_term_items, parent, false)
                return ViewHold1(view)
            }
            2 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.acq_payee_acount_table, parent, false)
                return ViewHold2(view)
            }
            3 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.tower_attachment_info, parent, false)
                return ViewHold3(view,listener)
            }

        }
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        when (holder) {
            is ViewHold1 -> {
                holder.binding.imgEdit.setOnClickListener {
                    holder.binding.viewLayout.visibility = View.GONE
                    holder.binding.editLayout.visibility = View.VISIBLE
                }
                holder.binding.cancel.setOnClickListener {
                    holder.binding.viewLayout.visibility = View.VISIBLE
                    holder.binding.editLayout.visibility = View.GONE
                }
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                    holder.binding.imgEdit.visibility = View.VISIBLE
                    holder.binding.viewLayout.visibility = View.VISIBLE
                    holder.binding.editLayout.visibility = View.GONE

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
                if (datalist!=null && datalist?.SAcqSoftAcquisitionAgreementTerm?.isNotEmpty()==true){
                    agreeData=datalist?.SAcqSoftAcquisitionAgreementTerm?.get(0)
                }
                else
                    AppLogger.log("error in soft Acquisition data or Agreement terms data")
                if (agreeData!=null ){
                    //view mode
                    if(agreeData?.Acquisitiontype?.isNotEmpty()==true)
                        AppPreferences.getInstance().setDropDown(holder.binding.AgreementType,DropDowns.Acquisitiontype.name,agreeData?.Acquisitiontype?.get(0).toString())
                    if(agreeData?.PropertyOwnership?.isNotEmpty()==true)
                        AppPreferences.getInstance().setDropDown(holder.binding.PropertyOwnership,DropDowns.PropertyOwnership.name,agreeData?.PropertyOwnership?.get(0).toString())
                    holder.binding.RentEscalationPeriod.text=agreeData?.RentEscalationPeriod
                    holder.binding.AcquisitionArea.text=agreeData?.AcquisitionArea
                    holder.binding.AgreementPeriod.text=agreeData?.AgreementPeriod
                    holder.binding.LockInPeriod.text=agreeData?.LockInPeriod
                    holder.binding.AnnualRentAmount.text=agreeData?.AnnualRentAmount
                    holder.binding.PeriodicRentAmount.text=agreeData?.PeriodicRentAmount
                    holder.binding.RentEscalation.text=agreeData?.RentEscalation
                    holder.binding.EBBillLimitMin.text=agreeData?.EBBillLimitmin
                    holder.binding.EBBillLimitMax.text=agreeData?.EBBillLimitmax
                    holder.binding.EBPerUnitRate.text=agreeData?.EBPUnitRate
                    holder.binding.OnetimeAmount.text=agreeData?.OnetimeAmount
                    holder.binding.SecurityDepositAmount.text=agreeData?.SecurityDepositAmount
                    holder.binding.remarks.text=agreeData?.remark

                    //edit mode
                    holder.binding.RentEscalationPeriodEdit.setText(agreeData?.RentEscalationPeriod)
                    holder.binding.AcquisitionAreaEdit.setText(agreeData?.AcquisitionArea)
                    holder.binding.AgreementPeriodEdit.setText(agreeData?.AgreementPeriod)
                    holder.binding.LockInPeriodEdit.setText(agreeData?.LockInPeriod)
                    holder.binding.AnnualRentAmountEdit.setText(agreeData?.AnnualRentAmount)
                    holder.binding.PeriodicRentAmountEdit.setText(agreeData?.PeriodicRentAmount)
                    holder.binding.RentEscalationEdit.setText(agreeData?.RentEscalation)
                    holder.binding.MinEBBillLimitEdit.setText(agreeData?.EBBillLimitmin)
                    holder.binding.MaxEBBillLimitEdit.setText(agreeData?.EBBillLimitmax)
                    holder.binding.EBPerUnitRateEdit.setText(agreeData?.EBPUnitRate)
                    holder.binding.OnetimeAmountEdit.setText(agreeData?.OnetimeAmount)
                    holder.binding.SecurityDepositAmountEdit.setText(agreeData?.SecurityDepositAmount)
                    holder.binding.remarksEdit.setText(agreeData?.remark)

                }
                if(agreeData!=null && agreeData?.Acquisitiontype?.isNotEmpty()==true)
                    AppPreferences.getInstance().setDropDown(holder.binding.AgreementTypeEdit,DropDowns.Acquisitiontype.name,agreeData?.Acquisitiontype?.get(0).toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.AgreementTypeEdit,DropDowns.Acquisitiontype.name)
                if(agreeData!=null && agreeData?.PropertyOwnership?.isNotEmpty()==true)
                    AppPreferences.getInstance().setDropDown(holder.binding.PropertyOwnershipEdit,DropDowns.PropertyOwnership.name,agreeData?.PropertyOwnership?.get(0).toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.PropertyOwnershipEdit,DropDowns.PropertyOwnership.name)
                if(agreeData!=null && agreeData?.PropertyType!=null && agreeData?.PropertyType!!>0){
                    AppPreferences.getInstance().setDropDown(holder.binding.PropertyTypeEdit,DropDowns.PropertyType.name,agreeData?.PropertyType.toString())
                    AppPreferences.getInstance().setDropDown(holder.binding.PropertyType,DropDowns.PropertyType.name,agreeData?.PropertyType.toString())
                }
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.AgreementTypeEdit,DropDowns.PropertyType.name)
                if(agreeData!=null && agreeData?.Rentpaymentperiod!=null && agreeData?.Rentpaymentperiod!!>0){
                    AppPreferences.getInstance().setDropDown(holder.binding.RentAymentPeriodEdit,DropDowns.RentPaymentPeriod.name,agreeData?.Rentpaymentperiod.toString())
                    AppPreferences.getInstance().setDropDown(holder.binding.RentAymentPeriod,DropDowns.RentPaymentPeriod.name,agreeData?.Rentpaymentperiod.toString())
                }
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.RentAymentPeriodEdit,DropDowns.RentPaymentPeriod.name)
                if(agreeData!=null && agreeData?.EBBillingBasis!=null && agreeData?.EBBillingBasis!!>0){
                    AppPreferences.getInstance().setDropDown(holder.binding.EBBillingBasisEdit,DropDowns.EBInclusiveBasis.name,agreeData?.EBBillingBasis.toString())
                    AppPreferences.getInstance().setDropDown(holder.binding.EBBillingBasis,DropDowns.EBInclusiveBasis.name,agreeData?.EBBillingBasis.toString())
                }
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.EBBillingBasisEdit,DropDowns.EBInclusiveBasis.name)
                if(agreeData!=null && agreeData?.EBInclusiveInRental!=null && agreeData?.EBInclusiveInRental!!>0){
                    AppPreferences.getInstance().setDropDown(holder.binding.EBInclusiveInRentalEdit,DropDowns.EBInclusiveinRental.name,agreeData?.EBInclusiveInRental.toString())
                    AppPreferences.getInstance().setDropDown(holder.binding.EBInclusiveInRental,DropDowns.EBInclusiveinRental.name,agreeData?.EBInclusiveInRental.toString())
                }
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.EBInclusiveInRentalEdit,DropDowns.EBInclusiveinRental.name)

                holder.binding.update.setOnClickListener {
                    val tempAgreeData=SoftAcqAgreementTerm()
                    tempAgreeData.let {
                        it.PropertyType=holder.binding.PropertyTypeEdit.selectedValue.id.toIntOrNull()
                        it.AcquisitionArea=holder.binding.AcquisitionAreaEdit.text.toString()
                        it.AgreementPeriod=holder.binding.AgreementPeriodEdit.text.toString()
                        it.LockInPeriod=holder.binding.LockInPeriodEdit.text.toString()
                        it.AnnualRentAmount=holder.binding.AnnualRentAmountEdit.text.toString()
                        it.Rentpaymentperiod=holder.binding.RentAymentPeriodEdit.selectedValue.id.toIntOrNull()
                        it.PeriodicRentAmount=holder.binding.PeriodicRentAmountEdit.text.toString()
                        it.RentEscalationPeriod=holder.binding.RentEscalationPeriodEdit.text.toString()
                        it.RentEscalation=holder.binding.RentEscalationEdit.text.toString()
                        it.EBInclusiveInRental=holder.binding.EBInclusiveInRentalEdit.selectedValue.id.toIntOrNull()
                        it.EBBillLimitmin=holder.binding.MinEBBillLimitEdit.text.toString()
                        it.EBBillLimitmax=holder.binding.MaxEBBillLimitEdit.text.toString()
                        it.EBBillingBasis=holder.binding.EBBillingBasisEdit.selectedValue.id.toIntOrNull()
                        it.EBPUnitRate=holder.binding.EBPerUnitRateEdit.text.toString()
                        it.OnetimeAmount=holder.binding.OnetimeAmountEdit.text.toString()
                        it.SecurityDepositAmount=holder.binding.SecurityDepositAmountEdit.text.toString()
                        it.remark=holder.binding.remarksEdit.text.toString()
                        it.Acquisitiontype = arrayListOf(holder.binding.AgreementTypeEdit.selectedValue.id.toInt())
                        it.PropertyOwnership = arrayListOf(holder.binding.PropertyOwnershipEdit.selectedValue.id.toInt())
                        if (agreeData!=null)
                            it.id=agreeData?.id

                    }
                    val tempData= SoftAcquisitionData()
                    tempData.SAcqSoftAcquisitionAgreementTerm= arrayListOf(tempAgreeData)
                    if (datalist!=null)
                        tempData.id=datalist?.id
                    listener.updateAgreementTermClicked(tempData)

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
                    if (datalist!=null ){
                        holder.payeeTableList.adapter=PayeeAccountTableAdapter(context,listener,datalist?.SAcqPayeeAccountDetail)
                    }
                    else{
                        holder.payeeTableList.adapter=PayeeAccountTableAdapter(context,listener,
                            ArrayList()
                        )
                    }
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

                    holder.binding.root.findViewById<View>(R.id.attach_card).setOnClickListener {
                        if (datalist!=null){
                            listener.addAttachment()
                        }
                        else
                            Toast.makeText(context,"Firstly fill data then Add Attachment",Toast.LENGTH_SHORT).show()
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
                        holder.recyclerListener.adapter=
                            ImageAttachmentCommonAdapter(context,datalist?.attachment!!,object : ImageAttachmentCommonAdapter.ItemClickListener{
                            override fun itemClicked() {
                                listener.attachmentItemClicked()
                            }
                        })
                    }
                    else
                        AppLogger.log("Attachments Error")
                }catch (e:java.lang.Exception){
                    AppLogger.log("Acq Survey error : ${e.localizedMessage}")
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



    interface SoftAcqListListener {
       fun attachmentItemClicked()
       fun addAttachment()
       fun viewPayeeAccountClicked(position: Int,data:SAcqPayeeAccountDetail)
       fun editPayeeAccountClicked(position: Int,data:SAcqPayeeAccountDetail)
       fun updateAgreementTermClicked(data:SoftAcquisitionData)
    }

}
