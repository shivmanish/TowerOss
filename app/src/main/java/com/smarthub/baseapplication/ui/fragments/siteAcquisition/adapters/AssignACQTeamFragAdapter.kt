package com.smarthub.baseapplication.ui.fragments.siteAcquisition.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AssignAcqTeamItemBinding
import com.smarthub.baseapplication.databinding.TowerAttachmentInfoBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.dropdown.DropDownItem
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.AssignACQTeamDAta
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.NewSiteAcquiAllData
import com.smarthub.baseapplication.ui.alert.model.request.GetUserList
import com.smarthub.baseapplication.ui.fragments.ImageAttachmentCommonAdapter
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.widgets.CustomSpinner
import com.smarthub.baseapplication.widgets.CustomUserSpinner

class AssignACQTeamFragAdapter(var baseFragment:BaseFragment, var listener: AssignACQTeamListListener, data:NewSiteAcquiAllData?) : RecyclerView.Adapter<AssignACQTeamFragAdapter.ViewHold>() {
    private var datalist: AssignACQTeamDAta?=null

    fun setData(data: AssignACQTeamDAta?) {
        this.datalist=data!!
        notifyDataSetChanged()
    }
    init {
        try {
            if (data!=null && data.SAcqAssignACQTeam?.isNotEmpty()!!){
                datalist=data.SAcqAssignACQTeam!!.get(0)
            }
        }catch (e:java.lang.Exception){
            AppLogger.log("AssignACQTeamFragAdapter error :${e.localizedMessage}")
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
                if (datalist!=null){
                    // view mode
                    if (datalist?.Acquisitiontype?.isNotEmpty() == true)
                        AppPreferences.getInstance().setDropDown(holder.binding.AcquisitionType,DropDowns.Acquisitiontype.name,datalist?.Acquisitiontype?.get(0).toString())
                    if (datalist?.AcquisitionMode?.isNotEmpty() == true)
                        AppPreferences.getInstance().setDropDown(holder.binding.AcquisitionMode,DropDowns.AcquisitionMode.name,datalist?.AcquisitionMode?.get(0).toString())
                    if (datalist?.VendorCompany?.isNotEmpty() == true)
                        AppPreferences.getInstance().setDropDown(holder.binding.VendorName,DropDowns.VendorCompany.name,datalist?.VendorCompany?.get(0).toString())
                    if (datalist?.Department?.isNotEmpty() == true)
                        AppPreferences.getInstance().setDropDown(holder.binding.Department,DropDowns.Deaprtments.name,datalist?.Department?.get(0).toString())
                    holder.binding.AcquisitionLeadName.text=datalist?.LeadName
                    holder.binding.AcquisitionExecutiveName.text=datalist?.ExecutiveName
                    holder.binding.AcquisitionExecutiveNumber.text=datalist?.ExecutiveMobile
                    holder.binding.AcquisitionBudget.text= datalist?.AcquisitionBudget?.ifEmpty { "0" }
                    holder.binding.VendorCode.text=datalist?.VendorCode
                    holder.binding.PONumber.text=datalist?.PONumber
                    holder.binding.POLineNo.text=datalist?.POLineItemNo.toString()
                    holder.binding.POAmount.text=datalist?.POAmount
                    holder.binding.VendorExecutiveName.text=datalist?.VendorExecutiveName
                    holder.binding.VendorExecutiveEmailID.text=datalist?.VendorExecutiveEmailId
                    holder.binding.VendorExecutiveNumber.text=datalist?.VendorExecutiveMobile
                    holder.binding.GeographyLevel.text=datalist?.GeographyLevel
                    holder.binding.AcquisitionTargetDate.text=Utils.getFormatedDate(datalist?.AcquisitionTargetDate,"dd-MMM-yyyy")
                    holder.binding.PODate.text=Utils.getFormatedDate(datalist?.PODate,"dd-MMM-yyyy")
                    holder.binding.remarks.text=datalist?.remark

                    // edit mode
                    holder.binding.AcquisitionLeadNameEdit.text=datalist?.LeadName
                    holder.binding.AcquisitionExecutiveNumberEdit.text=datalist?.ExecutiveMobile
                    holder.binding.AcquisitionBudgetEdit.setText(datalist?.AcquisitionBudget)
                    holder.binding.PONumberEdit.setText(datalist?.PONumber)
                    holder.binding.POLineNoEdit.setText(datalist?.POLineItemNo.toString())
                    holder.binding.POAmountEdit.setText(datalist?.POAmount)
                    holder.binding.VendorExecutiveNameEdit.setText(datalist?.VendorExecutiveName)
                    holder.binding.VendorExecutiveEmailIDEdit.setText(datalist?.VendorExecutiveEmailId)
                    holder.binding.VendorExecutiveNumberEdit.setText(datalist?.VendorExecutiveMobile)
                    holder.binding.AcquisitionTargetDateEdit.text=Utils.getFormatedDate(datalist?.AcquisitionTargetDate,"dd-MMM-yyyy")
                    holder.binding.PODateEdit.text=Utils.getFormatedDate(datalist?.PODate,"dd-MMM-yyyy")
                    holder.binding.remarksEdit.setText(datalist?.remark)


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
                    AppPreferences.getInstance().setDropDown(holder.binding.VendorNameEdit,DropDowns.VendorCompany.name,datalist?.VendorCompany?.get(0).toString(),holder.binding.VendorCodeEdit)
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.VendorNameEdit,DropDowns.VendorCompany.name,holder.binding.VendorCodeEdit)
                if (datalist!=null && datalist?.GeographyLevel?.isNotEmpty() == true)
                    AppPreferences.getInstance().setDropDownByName(holder.binding.GeographyLevelEdit,DropDowns.GeographyLevel.name,datalist?.GeographyLevel)
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.AcquisitionModeEdit,DropDowns.AcquisitionMode.name)
                if (datalist!=null && datalist?.Department?.isNotEmpty() == true)
                    AppPreferences.getInstance().setDropDown(holder.binding.DepartmentEdit,DropDowns.Deaprtments.name,datalist?.Department?.get(0).toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.AcquisitionModeEdit,DropDowns.Deaprtments.name)

                holder.binding.update.setOnClickListener {
                    val tempData=AssignACQTeamDAta()
                    tempData.let {
                        it.LeadName=holder.binding.AcquisitionExecutiveNameEdit.selectedValue.managername
                        it.ExecutiveName=holder.binding.AcquisitionExecutiveNameEdit.selectedValue.First_Name +" "+ holder.binding.AcquisitionExecutiveNameEdit.selectedValue.Last_Name
                        it.ExecutiveMobile=holder.binding.AcquisitionExecutiveNumberEdit.text.toString()
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
                        it.GeographyLevel=holder.binding.GeographyLevelEdit.selectedValue.name
                        it.AcquisitionMode = arrayListOf(holder.binding.AcquisitionModeEdit.selectedValue.id.toInt())
                        it.Acquisitiontype = arrayListOf(holder.binding.AcquisitionTypeEdit.selectedValue.id.toInt())
                        it.VendorCompany = arrayListOf(holder.binding.VendorNameEdit.selectedValue.id.toInt())
                        it.Department = arrayListOf(holder.binding.DepartmentEdit.selectedValue.id.toInt())
                        it.remark=holder.binding.remarksEdit.text.toString()
                        if (datalist!=null)
                            it.id=datalist?.id
                        listener.updateTeamClicked(it)
                    }

                }

                baseFragment.setDatePickerView(holder.binding.AcquisitionTargetDateEdit)
                baseFragment.setDatePickerView(holder.binding.PODateEdit)
                holder.binding.DepartmentEdit.itemSelectedListener=object : CustomSpinner.ItemSelectedListener{
                    override fun itemSelected(departmentName: DropDownItem) {
                        listener.departmentTextSelected(departmentName.name,holder.binding.AcquisitionExecutiveNameEdit,holder.binding.AcquisitionLeadNameEdit,
                            holder.binding.AcquisitionExecutiveNumberEdit, holder.binding.AcquisitionExecutiveName.text.toString())
                    }
                }
                holder.binding.GeographyLevelEdit.itemSelectedListener=object : CustomSpinner.ItemSelectedListener{
                    override fun itemSelected(geographySelected: DropDownItem) {
                        if (datalist!=null && datalist?.Department?.isNotEmpty() == true )
                            listener.geographyTextSelected(geographySelected.name,holder.binding.DepartmentEdit,datalist?.Department?.get(0).toString())
                        else
                            listener.geographyTextSelected(geographySelected.name,holder.binding.DepartmentEdit,null)
                    }
                }
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
                        holder.recyclerListener.adapter= ImageAttachmentCommonAdapter(baseFragment.requireContext(),datalist?.attachment!!,object : ImageAttachmentCommonAdapter.ItemClickListener{
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
       fun departmentTextSelected(department:String,userListText:CustomUserSpinner,executiveName:TextView,executiveNumber:TextView,selectedName:String?)
       fun geographyTextSelected(geograpgy:String,userListText:CustomSpinner,selectedItem:String?)
       fun updateTeamClicked(data:AssignACQTeamDAta?)
    }

}
