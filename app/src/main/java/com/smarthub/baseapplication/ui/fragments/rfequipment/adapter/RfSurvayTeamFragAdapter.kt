package com.smarthub.baseapplication.ui.fragments.rfequipment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.RfSurveyTeamItemBinding
import com.smarthub.baseapplication.databinding.SstSbcTeamItemBinding
import com.smarthub.baseapplication.databinding.TowerAttachmentInfoBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.dropdown.DropDownItem
import com.smarthub.baseapplication.model.siteIBoard.Attachments
import com.smarthub.baseapplication.model.siteIBoard.newsstSbc.SstSbcAllData
import com.smarthub.baseapplication.model.siteIBoard.newsstSbc.SstSbcTeam
import com.smarthub.baseapplication.ui.fragments.ImageAttachmentCommonAdapter
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.rfequipment.pojo.RfSurvey
import com.smarthub.baseapplication.ui.fragments.rfequipment.pojo.RfSurveyAssignTeam
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.adapters.AssignACQTeamFragAdapter
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.widgets.CustomSpinner
import com.smarthub.baseapplication.widgets.CustomUserSpinner

class RfSurvayTeamFragAdapter(var baseFragment:BaseFragment, var listener: RfListListener, data:RfSurvey?) : RecyclerView.Adapter<RfSurvayTeamFragAdapter.ViewHold>() {
    private var datalist: RfSurveyAssignTeam?=null

    fun setData(data: RfSurveyAssignTeam?) {
        this.datalist=data!!
        notifyDataSetChanged()
    }
    init {
        try {
            if (data!=null && data.RfSurveyAssignTeam?.isNotEmpty()!!){
                datalist=data.RfSurveyAssignTeam!!.get(0)
            }
        }catch (e:java.lang.Exception){
            AppLogger.log("RfSurvayTeamFragAdapter error :${e.localizedMessage}")
        }
    }



    var list : ArrayList<String> = ArrayList()
    var currentOpened = -1
    var type1 = "Team"
//    var type2 = "Attachments"

    init {
        list.add("Team")
//        list.add("Attachments")
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)
    
    class ViewHold1(itemView: View) : ViewHold(itemView) {
        var binding : RfSurveyTeamItemBinding = RfSurveyTeamItemBinding.bind(itemView)

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

    override fun getItemViewType(position: Int): Int {
        if (list[position]==type1)
            return 1
        return 0
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_empty, parent, false)
        when (viewType) {
            1 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.rf_survey_team_item, parent, false)
                return ViewHold1(view)
            }

        }
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        when (holder) {
            is ViewHold1 -> {
                holder.binding.imgDropdown.visibility=View.GONE
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

                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
                if(datalist!=null){
                    // view mode
                    if (datalist?.Department?.isNotEmpty() == true)
                        AppPreferences.getInstance().setDropDown(holder.binding.Department,DropDowns.Deaprtments.name,datalist?.Department?.get(0).toString())
                    holder.binding.AcquisitionExecutiveName.text=datalist?.ExecutiveName
                    holder.binding.AcquisitionExecutiveNumber.text=datalist?.ExecutiveMobile
                    holder.binding.GeographyLevel.text=datalist?.GeographyLevel
                    holder.binding.remarks.text=datalist?.remark

                    // edit mode
                    holder.binding.AcquisitionExecutiveNumberEdit.text=datalist?.ExecutiveMobile
                    holder.binding.remarksEdit.setText(datalist?.remark)
                }
                if (datalist!=null && datalist?.GeographyLevel?.isNotEmpty() == true)
                    AppPreferences.getInstance().setDropDownByName(holder.binding.GeographyLevelEdit,DropDowns.GeographyLevel.name,datalist?.GeographyLevel)
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.GeographyLevelEdit,DropDowns.GeographyLevel.name)
                if (datalist!=null && datalist?.Department?.isNotEmpty() == true)
                    AppPreferences.getInstance().setDropDown(holder.binding.DepartmentEdit,DropDowns.Deaprtments.name,datalist?.Department?.get(0).toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.DepartmentEdit,DropDowns.Deaprtments.name)
                holder.binding.update.setOnClickListener {
                    val tempSurveyTeamData=RfSurveyAssignTeam()
                    tempSurveyTeamData.let {
                        it.GeographyLevel=holder.binding.GeographyLevelEdit.selectedValue.name
                        it.Department=holder.binding.DepartmentEdit.getSelectedArray()
                        it.ExecutiveMobile=holder.binding.AcquisitionExecutiveNumberEdit.text.toString()
                        it.remark=holder.binding.remarksEdit.text.toString()
                        it.ExecutiveName=holder.binding.AcquisitionExecutiveNameEdit.selectedValue.First_Name +" "+ holder.binding.AcquisitionExecutiveNameEdit.selectedValue.Last_Name
                        if (datalist!=null)
                            it.id=datalist?.id
                    }
                    listener.updateTeamClicked(tempSurveyTeamData)
                }
                holder.binding.DepartmentEdit.itemSelectedListener=object : CustomSpinner.ItemSelectedListener{
                    override fun itemSelected(departmentName: DropDownItem) {
                        listener.departmentTextSelected(departmentName.name,holder.binding.AcquisitionExecutiveNameEdit,
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



    interface RfListListener {
       fun updateTeamClicked(data:RfSurveyAssignTeam)
        fun departmentTextSelected(department:String, userListText: CustomUserSpinner, executiveNumber: TextView, selectedName:String?)
        fun geographyTextSelected(geograpgy:String, userListText: CustomSpinner, selectedItem:String?)
    }

}
