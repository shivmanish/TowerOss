package com.smarthub.baseapplication.ui.fragments.siteAcquisition.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.SiteAcqAgreementDetailsItemsBinding
import com.smarthub.baseapplication.databinding.TowerAttachmentInfoBinding
import com.smarthub.baseapplication.databinding.TowerPoItemBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.NewSiteAcquiAllData
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.SAcqAgreementDetail
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.SAcqPODetail
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.SiteAcqAgreement
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.tableAdapters.PowerFuelPoTableAdapter
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.tableAdapters.SiteAgreePoTableAdapter
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class AgreementFragAdapter(var context: Context, var listener: AgreementListListener, data:NewSiteAcquiAllData?) : RecyclerView.Adapter<AgreementFragAdapter.ViewHold>() {
    private var datalist: SiteAcqAgreement?=null

    fun setData(data: SiteAcqAgreement?) {
        this.datalist=data!!
        notifyDataSetChanged()
    }
    init {
        try {
            if (data!=null && data.SAcqAssignACQTeam.isNotEmpty()){
                datalist=data.SAcqAgreement.get(0)
            }
        }catch (e:java.lang.Exception){
            Toast.makeText(context,"TowerInfoFrag error :${e.localizedMessage}", Toast.LENGTH_LONG).show()
        }
    }



    var list : ArrayList<String> = ArrayList()
    var currentOpened = -1
    var type1 = "Agreement Details"
    var type2 = "PO Details"
    var type3 = "Attachments"

    init {
        list.add("Agreement Details")
        list.add("PO Details")
        list.add("Attachments")
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)

    class ViewHold1(itemView: View) : ViewHold(itemView) {
        var binding : SiteAcqAgreementDetailsItemsBinding = SiteAcqAgreementDetailsItemsBinding.bind(itemView)

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
        var binding: TowerPoItemBinding = TowerPoItemBinding.bind(itemView)
        var poTableList: RecyclerView=binding.towerPoTableItem

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
                addPoTableItem("wqeeqs")
            }
        }

        private fun addPoTableItem(item:String){
            if (poTableList.adapter!=null && poTableList.adapter is PowerFuelPoTableAdapter){
                val adapter = poTableList.adapter as PowerFuelPoTableAdapter
                adapter.addItem(item)
            }
        }

    }
    class ViewHold3(itemView: View,listener: AgreementListListener) : ViewHold(itemView) {
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
                view = LayoutInflater.from(parent.context).inflate(R.layout.site_acq_agreement_details_items, parent, false)
                return ViewHold1(view)
            }
            2 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.tower_po_item, parent, false)
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
                    if (datalist!=null && datalist?.SAcqAgreementDetail?.isNotEmpty()==true){
                        val agreeDetailsData: SAcqAgreementDetail? = datalist?.SAcqAgreementDetail?.get(0)
                        if(agreeDetailsData?.Costcentre?.isNotEmpty()==true)
                            AppPreferences.getInstance().setDropDown(holder.binding.CostCentre,DropDowns.Costcentre.name,agreeDetailsData.Costcentre.get(0).toString())

                        holder.binding.RegistrationNumber.text=agreeDetailsData?.RegistrationNumber
                        holder.binding.LastRevisedRentAmount.text=agreeDetailsData?.LastRevisedRentAmount
                        holder.binding.AcquisitionArea.text=agreeDetailsData?.Acquisitionarea
                        holder.binding.RooftopAcquiredArea.text=agreeDetailsData?.RooftopacquiredArea
                        holder.binding.RooftopUsableArea.text=agreeDetailsData?.RooftopUsableArea
                        holder.binding.GroundAcquiredArea.text=agreeDetailsData?.GroundAcquiredArea
                        holder.binding.GroundUsableArea.text=agreeDetailsData?.GroundUsableArea
                        holder.binding.remarks.text=agreeDetailsData?.Remark
                        holder.binding.RegistrationDate.text=Utils.getFormatedDate(agreeDetailsData?.RegistrationDate?.substring(0,10)!!,"dd-MMM-yyyy")
                        holder.binding.AgreementEffectiveDate.text=Utils.getFormatedDate(agreeDetailsData.AgreementEffectiveDate.substring(0,10),"dd-MMM-yyyy")
                        holder.binding.AgreementExpiryDate.text=Utils.getFormatedDate(agreeDetailsData.AgreementExpiryDate.substring(0,10),"dd-MMM-yyyy")
                        holder.binding.RentStartDate.text=Utils.getFormatedDate(agreeDetailsData.RentStartDate.substring(0,10),"dd-MMM-yyyy")
                    }
                    else
                        AppLogger.log("error in agreement data or Agreement details data")
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
                        holder.poTableList.adapter=SiteAgreePoTableAdapter(context,listener,datalist?.SAcqPODetail)
                    }
                    else
                        AppLogger.log("error in Agreement data or Agree Po details data")
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



    interface AgreementListListener {
       fun attachmentItemClicked()
       fun viewPoItemClicked(position: Int,data:SAcqPODetail)
    }

}
