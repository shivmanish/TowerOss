package com.smarthub.baseapplication.ui.fragments.noc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.NocApplicationDetailsItemBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newNocAndComp.NocApplicationInitial
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class NocApplicationDetailsAdapter(var listener: NocApplicationClickListener, cableDetails: ArrayList<NocApplicationInitial>, var baseFragment: BaseFragment) : RecyclerView.Adapter<NocApplicationDetailsAdapter.ViewHold>() {

    var list : ArrayList<NocApplicationInitial> = cableDetails
    var currentOpened = -1

    fun setData(data : ArrayList<NocApplicationInitial>?){
        if (data!=null){
            this.list.clear()
            this.list.addAll(data)
            notifyDataSetChanged()
        }
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)

    class ViewHold1(itemView: View,listener: NocApplicationClickListener) : ViewHold(itemView) {
        var binding : NocApplicationDetailsItemBinding = NocApplicationDetailsItemBinding.bind(itemView)
//        var adapter =  ImageAttachmentAdapter(object : ImageAttachmentAdapter.ItemClickListener{
//            override fun itemClicked() {
//                listener.attachmentItemClicked()
//            }
//        })
        init {
            binding.collapsingLayout.tag = false
            if ((binding.collapsingLayout.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }

//            val recyclerListener = itemView.findViewById<RecyclerView>(R.id.list_item)
//            recyclerListener.adapter = adapter

//            itemView.findViewById<View>(R.id.attach_card).setOnClickListener {
//                adapter.addItem()
//            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        return when (viewType) {
            1 ->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.noc_application_details_item,parent,false)
                ViewHold1(view,listener)
            }
            2 ->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.rf_equipment_no_data,parent,false)
                ViewHold(view)
            }

            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.noc_application_details_item,parent,false)
                ViewHold1(view,listener)
            }
        }

    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        if (holder is ViewHold1) {
            val data: NocApplicationInitial=list[position]
            holder.binding.collapsingLayout.setOnClickListener {
                updateList(position)
            }
            if (currentOpened == position) {
                holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                holder.binding.itemLine.visibility = View.GONE
                holder.binding.itemCollapse.visibility = View.VISIBLE
                holder.binding.imgEdit.visibility = View.VISIBLE
                holder.binding.viewLayout.visibility = View.VISIBLE
                holder.binding.editLayout.visibility = View.GONE

                holder.binding.imgEdit.setOnClickListener {
                    if (AppController.getInstance().isTaskEditable) {
                        holder.binding.viewLayout.visibility = View.GONE
                        holder.binding.editLayout.visibility = View.VISIBLE
                    }
                }
                holder.binding.cancel.setOnClickListener {
                    holder.binding.viewLayout.visibility = View.VISIBLE
                    holder.binding.editLayout.visibility = View.GONE
                }
            } else {
                holder.binding.collapsingLayout.tag = false
                holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                holder.binding.itemLine.visibility = View.VISIBLE
                holder.binding.itemCollapse.visibility = View.GONE
                holder.binding.imgEdit.visibility = View.GONE
            }
            holder.binding.itemTitleStr.text = String.format(baseFragment.resources.getString(R.string.rf_antenna_title_str_formate),position.plus(1).toString(),data.ApplicationNumber,Utils.getFormatedDate(data.IssueDate,"ddMMMyyyy"))

            // view mode

            if(data.AuthorityApplicationType?.isNotEmpty()==true)
                AppPreferences.getInstance().setDropDown(holder.binding.applicationType, DropDowns.AuthorityApplicationType.name,data.AuthorityApplicationType?.get(0).toString())
            if(data.Category!=null && data.Category!! > 0)
                AppPreferences.getInstance().setDropDown(holder.binding.Category, DropDowns.ApplicationCategory.name,data.Category.toString())
            if(data.ApplicationStatus?.isNotEmpty()==true)
                AppPreferences.getInstance().setDropDown(holder.binding.Status, DropDowns.ApplicationStatus.name,data.ApplicationStatus?.get(0).toString())
            holder.binding.issueDate.text=Utils.getFormatedDate(data.IssueDate,"dd-MMM-yyyy")
            holder.binding.applicationDate.text=Utils.getFormatedDate(data.ApplicationDate,"dd-MMM-yyyy")
            holder.binding.applicationNumber.text=data.ApplicationNumber
            holder.binding.ExiparyDate.text=Utils.getFormatedDate(data.ExpiryDate,"dd-MMM-yyyy")
            holder.binding.DocumentNo.text=data.DocumentNo
            holder.binding.SrNumber.text=position.plus(1).toString()
            holder.binding.StatusDate.text=Utils.getFormatedDate(data.StatusDate,"dd-MMM-yyyy")

            // edit mode
            holder.binding.ApplicationNumberEdit.setText(data.ApplicationNumber)
            holder.binding.DocumentNumberEdit.setText(data.DocumentNo)
            holder.binding.IssueDateEdit.text=Utils.getFormatedDate(data.IssueDate,"dd-MMM-yyyy")
            holder.binding.StatusDateEdit.text=Utils.getFormatedDate(data.StatusDate,"dd-MMM-yyyy")
            holder.binding.ExpiryDateEdit.text=Utils.getFormatedDate(data.ExpiryDate,"dd-MMM-yyyy")
            holder.binding.ApplicationDateEdit.text=Utils.getFormatedDate(data.ApplicationDate,"dd-MMM-yyyy")

            if(data.AuthorityApplicationType?.isNotEmpty()==true)
                AppPreferences.getInstance().setDropDown(holder.binding.ApplicationTypeEdit, DropDowns.AuthorityApplicationType.name,data.AuthorityApplicationType?.get(0).toString())
            else
                AppPreferences.getInstance().setDropDown(holder.binding.ApplicationTypeEdit, DropDowns.AuthorityApplicationType.name)
            if(data.Category!=null && data.Category!! > 0)
                AppPreferences.getInstance().setDropDown(holder.binding.CategoryEdit, DropDowns.ApplicationCategory.name,data.Category.toString())
            else
                AppPreferences.getInstance().setDropDown(holder.binding.CategoryEdit, DropDowns.ApplicationCategory.name)
            if(data.ApplicationStatus?.isNotEmpty()==true)
                AppPreferences.getInstance().setDropDown(holder.binding.StatusEdit, DropDowns.ApplicationStatus.name,data.ApplicationStatus?.get(0).toString())
            else
                AppPreferences.getInstance().setDropDown(holder.binding.StatusEdit, DropDowns.ApplicationStatus.name)

            baseFragment.setDatePickerView(holder.binding.ApplicationDateEdit)
            baseFragment.setDatePickerView(holder.binding.StatusDateEdit)
            baseFragment.setDatePickerView(holder.binding.IssueDateEdit)
            baseFragment.setDatePickerView(holder.binding.ExpiryDateEdit)

            holder.binding.update.setOnClickListener {
                val tempApplicatioData=NocApplicationInitial()
                tempApplicatioData.let {
                    it.ApplicationNumber=holder.binding.ApplicationNumberEdit.text.toString()
                    it.DocumentNo=holder.binding.DocumentNumberEdit.text.toString()
                    it.ApplicationDate=Utils.getFullFormatedDate(holder.binding.ApplicationDateEdit.text.toString())
                    it.StatusDate=Utils.getFullFormatedDate(holder.binding.StatusDateEdit.text.toString())
                    it.IssueDate=Utils.getFullFormatedDate(holder.binding.IssueDateEdit.text.toString())
                    it.ExpiryDate=Utils.getFullFormatedDate(holder.binding.ExpiryDateEdit.text.toString())
                    it.AuthorityApplicationType= arrayListOf(holder.binding.ApplicationTypeEdit.selectedValue.id.toInt())
                    it.ApplicationStatus= arrayListOf(holder.binding.StatusEdit.selectedValue.id.toInt())
                    it.Category= holder.binding.CategoryEdit.selectedValue.id.toInt()
                    it.id=data.id
                    listener.updataDataClicked(it)
                }
            }
        }
    }


    override fun getItemViewType(position: Int): Int {
        return if (list.isEmpty())
            2
        else
            1
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

    interface NocApplicationClickListener{
        fun attachmentItemClicked()
        fun updataDataClicked(data :NocApplicationInitial)
    }
}