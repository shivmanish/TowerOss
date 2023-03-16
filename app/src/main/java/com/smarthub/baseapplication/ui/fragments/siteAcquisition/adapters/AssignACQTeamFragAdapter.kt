package com.smarthub.baseapplication.ui.fragments.siteAcquisition.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AssignAcqTeamItemBinding
import com.smarthub.baseapplication.databinding.TowerAttachmentInfoBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.AssignACQTeamDAta
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.NewSiteAcquiAllData
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class AssignACQTeamFragAdapter(var baseFragment:BaseFragment, var listener: AssignACQTeamListListener, data:NewSiteAcquiAllData?) : RecyclerView.Adapter<AssignACQTeamFragAdapter.ViewHold>() {
    private var datalist: AssignACQTeamDAta?=null

    fun setData(data: AssignACQTeamDAta?) {
        this.datalist=data!!
        notifyDataSetChanged()
    }
    init {
        try {
            if (data!=null && data.SAcqAssignACQTeam.isNotEmpty()){
                datalist=data.SAcqAssignACQTeam.get(0)
            }
        }catch (e:java.lang.Exception){
            AppLogger.log("TowerInfoFrag error :${e.localizedMessage}")
        }
    }



    var list : ArrayList<String> = ArrayList()
    var currentOpened = -1
    var type1 = "Team"
    var type2 = "Attachments"

    init {
        list.add("Team")
        list.add("Attachments")
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)
    
    class ViewHold1(itemView: View) : ViewHold(itemView) {
        var binding : AssignAcqTeamItemBinding = AssignAcqTeamItemBinding.bind(itemView)

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
    class ViewHold2(itemView: View, listener: AssignACQTeamListListener) : ViewHold(itemView) {
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

            itemView.findViewById<View>(R.id.attach_card).setOnClickListener {
                listener.addAttachment()
            }

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
                view = LayoutInflater.from(parent.context).inflate(R.layout.assign_acq_team_item, parent, false)
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
                        holder.binding.viewLayout.visibility = View.GONE
                        holder.binding.editLayout.visibility = View.VISIBLE
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
                try {
                    if (datalist!=null){
                        // view mode
                        if (datalist?.Acquisitiontype?.isNotEmpty() == true)
                            AppPreferences.getInstance().setDropDown(holder.binding.AcquisitionType,DropDowns.Acquisitiontype.name,datalist?.Acquisitiontype?.get(0).toString())
                        if (datalist?.AcquisitionMode?.isNotEmpty() == true)
                            AppPreferences.getInstance().setDropDown(holder.binding.AcquisitionMode,DropDowns.AcquisitionMode.name,datalist?.AcquisitionMode?.get(0).toString())
                        if (datalist?.VendorCompany?.isNotEmpty() == true)
                            AppPreferences.getInstance().setDropDown(holder.binding.VendorName,DropDowns.VendorCompany.name,datalist?.VendorCompany?.get(0).toString())
                        holder.binding.AcquisitionLeadName.text=datalist?.LeadName
                        holder.binding.AcquisitionExecutiveName.text=datalist?.ExecutiveName
                        holder.binding.AcquisitionBudget.text=datalist?.AcquisitionBudget
                        holder.binding.VendorCode.text=datalist?.VendorCode
                        holder.binding.PONumber.text=datalist?.PONumber
                        holder.binding.POLineNo.text=datalist?.POLineItemNo.toString()
                        holder.binding.POAmount.text=datalist?.POAmount
                        holder.binding.VendorExecutiveName.text=datalist?.VendorExecutiveName
                        holder.binding.VendorExecutiveEmailID.text=datalist?.VendorExecutiveEmailId
                        holder.binding.VendorExecutiveNumber.text=datalist?.VendorExecutiveMobile
                        holder.binding.AcquisitionTargetDate.text=Utils.getFormatedDate(datalist?.AcquisitionTargetDate?.substring(0,10)!!,"dd-MMM-yyyy")
                        holder.binding.PODate.text=Utils.getFormatedDate(datalist?.PODate?.substring(0,10)!!,"dd-MMM-yyyy")
                        holder.binding.remarks.text=datalist?.Remark

                        // edit mode
                        holder.binding.AcquisitionLeadNameEdit.setText(datalist?.LeadName)
                        holder.binding.AcquisitionExecutiveNameEdit.setText(datalist?.ExecutiveName)
                        holder.binding.AcquisitionBudgetEdit.setText(datalist?.AcquisitionBudget)
                        holder.binding.VendorCodeEdit.setText(datalist?.VendorCode)
                        holder.binding.PONumberEdit.setText(datalist?.PONumber)
                        holder.binding.POLineNoEdit.setText(datalist?.POLineItemNo.toString())
                        holder.binding.POAmountEdit.setText(datalist?.POAmount)
                        holder.binding.VendorExecutiveNameEdit.setText(datalist?.VendorExecutiveName)
                        holder.binding.VendorExecutiveEmailIDEdit.setText(datalist?.VendorExecutiveEmailId)
                        holder.binding.VendorExecutiveNumberEdit.setText(datalist?.VendorExecutiveMobile)
                        holder.binding.AcquisitionTargetDateEdit.text=Utils.getFormatedDate(datalist?.AcquisitionTargetDate?.substring(0,10)!!,"dd-MMM-yyyy")
                        holder.binding.PODateEdit.text=Utils.getFormatedDate(datalist?.PODate?.substring(0,10)!!,"dd-MMM-yyyy")
                        holder.binding.remarksEdit.setText(datalist?.Remark)


                    }
                    else
                        AppLogger.log("error in Power Connection details data")
                    if (datalist!=null && datalist?.Acquisitiontype?.isNotEmpty() == true)
                        AppPreferences.getInstance().setDropDown(holder.binding.AcquisitionTypeEdit,DropDowns.Acquisitiontype.name,datalist?.Acquisitiontype?.get(0).toString())
                    else
                        AppPreferences.getInstance().setDropDown(holder.binding.AcquisitionTypeEdit,DropDowns.Acquisitiontype.name)
                    if (datalist!=null && datalist?.AcquisitionMode?.isNotEmpty() == true)
                        AppPreferences.getInstance().setDropDown(holder.binding.AcquisitionModeEdit,DropDowns.AcquisitionMode.name,datalist?.AcquisitionMode?.get(0).toString())
                    else
                        AppPreferences.getInstance().setDropDown(holder.binding.AcquisitionModeEdit,DropDowns.AcquisitionMode.name)
                    if (datalist!=null && datalist?.VendorCompany?.isNotEmpty() == true)
                        AppPreferences.getInstance().setDropDown(holder.binding.VendorNameEdit,DropDowns.VendorCompany.name,datalist?.VendorCompany?.get(0).toString())
                    else
                        AppPreferences.getInstance().setDropDown(holder.binding.VendorNameEdit,DropDowns.VendorCompany.name)

                }catch (e:java.lang.Exception){
                    AppLogger.log("ToewerInfoadapter error : ${e.localizedMessage}")
                }

                holder.binding.update.setOnClickListener {
                    if (datalist!=null){
                        datalist.let {
                            it?.LeadName=holder.binding.AcquisitionLeadNameEdit.text.toString()
                            it?.ExecutiveName=holder.binding.AcquisitionExecutiveNameEdit.text.toString()
                            it?.AcquisitionBudget=holder.binding.AcquisitionBudgetEdit.text.toString()
                            it?.AcquisitionTargetDate=Utils.getFullFormatedDate(holder.binding.AcquisitionTargetDateEdit.text.toString())
                            it?.VendorCode=holder.binding.VendorCodeEdit.text.toString()
                            it?.PONumber=holder.binding.PONumberEdit.text.toString()
                            it?.PODate=Utils.getFullFormatedDate(holder.binding.PODateEdit.text.toString())
                            it?.POLineItemNo=holder.binding.POLineNoEdit.text.toString().toInt()
                            it?.POAmount=holder.binding.POAmountEdit.text.toString()
                            it?.VendorExecutiveName=holder.binding.VendorExecutiveNameEdit.text.toString()
                            it?.VendorExecutiveEmailId=holder.binding.VendorExecutiveEmailIDEdit.text.toString()
                            it?.VendorExecutiveMobile=holder.binding.VendorExecutiveNumberEdit.text.toString()
                            it?.Remark=holder.binding.remarksEdit.text.toString()
                            it?.isActive=null
                            it?.modified_at=null
                            it?.created_at=null

                            if (it?.AcquisitionMode?.isNotEmpty()==true)
                                it.AcquisitionMode!![0] = holder.binding.AcquisitionModeEdit.selectedValue.id.toInt()
                            else
                                it?.AcquisitionMode?.add(holder.binding.AcquisitionModeEdit.selectedValue.id.toInt())

                            if (it?.Acquisitiontype?.isNotEmpty()==true)
                                it.Acquisitiontype!![0] = holder.binding.AcquisitionTypeEdit.selectedValue.id.toInt()
                            else
                                it?.Acquisitiontype?.add(holder.binding.AcquisitionTypeEdit.selectedValue.id.toInt())

                            if (it?.VendorCompany?.isNotEmpty()==true)
                                it.VendorCompany!![0] = holder.binding.VendorNameEdit.selectedValue.id.toInt()
                            else
                                it?.VendorCompany?.add(holder.binding.VendorNameEdit.selectedValue.id.toInt())

                            listener.updateTeamClicked(it)
                        }
                    }
                    else{
                        val tempData=AssignACQTeamDAta()
                        tempData.let {
                            it.LeadName=holder.binding.AcquisitionLeadNameEdit.text.toString()
                            it.ExecutiveName=holder.binding.AcquisitionExecutiveNameEdit.text.toString()
                            it.AcquisitionBudget=holder.binding.AcquisitionBudgetEdit.text.toString()
                            it.AcquisitionTargetDate=Utils.getFullFormatedDate(holder.binding.AcquisitionTargetDateEdit.text.toString())
                            it.VendorCode=holder.binding.VendorCodeEdit.text.toString()
                            it.PONumber=holder.binding.PONumberEdit.text.toString()
                            it.PODate=Utils.getFullFormatedDate(holder.binding.PODateEdit.text.toString())
                            it.POLineItemNo=holder.binding.POLineNoEdit.text.toString().toInt()
                            it.POAmount=holder.binding.POAmountEdit.text.toString()
                            it.VendorExecutiveName=holder.binding.VendorExecutiveNameEdit.text.toString()
                            it.VendorExecutiveEmailId=holder.binding.VendorExecutiveEmailIDEdit.text.toString()
                            it.VendorExecutiveMobile=holder.binding.VendorExecutiveNumberEdit.text.toString()
                            it.AcquisitionMode = arrayListOf(holder.binding.AcquisitionModeEdit.selectedValue.id.toInt())
                            it.Acquisitiontype = arrayListOf(holder.binding.AcquisitionTypeEdit.selectedValue.id.toInt())
                            it.VendorCompany = arrayListOf(holder.binding.VendorNameEdit.selectedValue.id.toInt())
                            it.Remark=holder.binding.remarksEdit.text.toString()

                            listener.updateTeamClicked(it)
                        }
                    }

                }

                baseFragment.setDatePickerView(holder.binding.AcquisitionTargetDateEdit)
                baseFragment.setDatePickerView(holder.binding.PODateEdit)
            }
            is ViewHold2 -> {
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
                try {
                    if (datalist!=null){
                        holder.recyclerListener.adapter=AcqImageAttachmentAdapter(baseFragment.requireContext(),datalist?.attachment!!,object : AcqImageAttachmentAdapter.ItemClickListener{
                            override fun itemClicked() {
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



    interface AssignACQTeamListListener {
       fun attachmentItemClicked()
       fun addAttachment()
       fun updateTeamClicked(data:AssignACQTeamDAta?)
    }

}
