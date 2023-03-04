package com.smarthub.baseapplication.ui.fragments.siteAcquisition.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.*
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.AssignACQTeamDAta
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.NewSiteAcquiAllData
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.tableAdapters.PowerConsumableTableAdapter
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.tableAdapters.PowerFuelPaymentTableAdapter
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.tableAdapters.PowerFuelPoTableAdapter
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.tableAdapters.PowerFuelTariffTableAdapter
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class AssignACQTeamFragAdapter(var context: Context, var listener: AssignACQTeamListListener, data:NewSiteAcquiAllData?) : RecyclerView.Adapter<AssignACQTeamFragAdapter.ViewHold>() {
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
            Toast.makeText(context,"TowerInfoFrag error :${e.localizedMessage}", Toast.LENGTH_LONG).show()
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
    class ViewHold2(itemView: View,listener: AssignACQTeamListListener) : ViewHold(itemView) {
        var binding: TowerAttachmentInfoBinding = TowerAttachmentInfoBinding.bind(itemView)
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

            val recyclerListener = itemView.findViewById<RecyclerView>(R.id.list_item)
            recyclerListener.adapter = adapter

            itemView.findViewById<View>(R.id.attach_card).setOnClickListener {
                adapter.addItem()
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

                    holder.binding.imgEdit.setOnClickListener {
//                        listener.EditTowerItem()
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

                    }
                    else
                        AppLogger.log("error in Power Connection details data")
                }catch (e:java.lang.Exception){
                    AppLogger.log("ToewerInfoadapter error : ${e.localizedMessage}")
                }

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
       fun editTeamClicked(position: Int,data:PowerFuelPODetail)
    }

}
