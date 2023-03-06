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
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.*
import com.smarthub.baseapplication.model.siteIBoard.newSiteInfoDataModel.SiteAddressData
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.tableAdapters.PowerConsumableTableAdapter
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.tableAdapters.PowerFuelPaymentTableAdapter
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.tableAdapters.PowerFuelPoTableAdapter
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.tableAdapters.PowerFuelTariffTableAdapter
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.tableAdapters.InsidePremisesTableAdapter
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.tableAdapters.OutsidePremisesTableAdapter
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.tableAdapters.PropertyOwnerTableAdapter
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class SoftAcquisitionFragAdapter(var context: Context, var listener: SoftAcqListListener, data:NewSiteAcquiAllData?) : RecyclerView.Adapter<SoftAcquisitionFragAdapter.ViewHold>() {
    private var datalist: SoftAcquisitionData?=null

    fun setData(data: SoftAcquisitionData?) {
        this.datalist=data!!
        notifyDataSetChanged()
    }
    init {
        try {
            if (data!=null && data.SAcqAssignACQTeam.isNotEmpty()){
                datalist=data.SAcqSoftAcquisition.get(0)
            }
        }catch (e:java.lang.Exception){
            Toast.makeText(context,"TowerInfoFrag error :${e.localizedMessage}", Toast.LENGTH_LONG).show()
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
    class ViewHold4(itemView: View) : ViewHold(itemView) {
        var binding : AcqPropertyOwnersDetailsItemsBinding = AcqPropertyOwnersDetailsItemsBinding.bind(itemView)
        var ownerTableList: RecyclerView=binding.propertyOwnerTableItem

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
                addOwnerTableItem("wqeeqs")
            }
        }

        private fun addOwnerTableItem(item:String){
            if (ownerTableList.adapter!=null && ownerTableList.adapter is PropertyOwnerTableAdapter){
                val adapter = ownerTableList.adapter as PropertyOwnerTableAdapter
                adapter.addItem(item)
            }
        }


    }
    class ViewHold2(itemView: View) : ViewHold(itemView) {
        var binding : AcqFeasibilityDetailsItemsBinding = AcqFeasibilityDetailsItemsBinding.bind(itemView)

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
    class ViewHold3(itemView: View,listener: SoftAcqListListener) : ViewHold(itemView) {
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
                view = LayoutInflater.from(parent.context).inflate(R.layout.acq_feasibility_details_items, parent, false)
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
                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
                holder.binding.itemTitleStr.text = list[position]
                try {
                    if (datalist!=null && datalist?.SAcqSoftAcquisitionAgreementTerm?.isNotEmpty()==true){
                        val agreeData: SoftAcqAgreementTerm? = datalist?.SAcqSoftAcquisitionAgreementTerm?.get(0)
                        
                    }
                    else
                        AppLogger.log("error in soft Acquisition data or Agreement terms data")
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
                    if (datalist!=null ){
//                        val feasibilityData: SAcqFeasibilityDetail? = datalist?.SAcqFeasibilityDetail?.get(0)
//                        if (feasibilityData?.Acquisitiontype?.isNotEmpty() == true)
//                            AppPreferences.getInstance().setDropDown(holder.binding.AcquisitionType,DropDowns.Acquisitiontype.name,feasibilityData.Acquisitiontype.get(0).toString())
//                        if (feasibilityData?.TowerPoleType?.isNotEmpty() == true)
//                            AppPreferences.getInstance().setDropDown(holder.binding.TowerPoleType,DropDowns.TowerPoleType.name,feasibilityData.TowerPoleType.get(0).toString())
//
//                        holder.binding.AcquisitionArea.text=feasibilityData?.Area
//                        holder.binding.ExpectedPrice.text=feasibilityData?.ExpectedPrice
//                        holder.binding.AverageMarketRate.text=feasibilityData?.MarketPrice
//                        holder.binding.FiberLMCLaying.text=feasibilityData?.FiberLMCLaying.toString()
//                        holder.binding.EBSupplyThroOwnerMeter.text=feasibilityData?.OwnerMeter.toString()
//                        holder.binding.EquipmentRoom.text=feasibilityData?.EquipmentRoom.toString()
//                        holder.binding.RequiredAreaAvailable.text=feasibilityData?.RequiredAreaAvailable.toString()
//                        holder.binding.StatutoryPermissions.text=feasibilityData?.StatutoryPermission.toString()
//                        holder.binding.OverallFeasibility.text=feasibilityData?.OverallFeasibility.toString()
//                        holder.binding.SurveyExecutiveName.text=feasibilityData?.ExecutiveName.toString()
//                        holder.binding.SurveyDate.text=feasibilityData?.SurveyDate.toString()
//                        holder.binding.remarks.text=feasibilityData?.Remark

                    }
                    else
                        AppLogger.log("error in Acquisition Survey data or Property details data")
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



    interface SoftAcqListListener {
       fun attachmentItemClicked()


    }

}
