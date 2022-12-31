package com.smarthub.baseapplication.ui.fragments.services_request.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.model.siteInfo.*
import com.smarthub.baseapplication.network.pojo.site_info.BasicInfoModelDropDown

class SoftAcquisitionAdapter(var listener: SoftAcquisitionLisListener) : RecyclerView.Adapter<SoftAcquisitionAdapter.ViewHold>() {
    var list : ArrayList<String> = ArrayList()
    var type1 = "RF Feasibility"
    private var data : BasicInfoModelDropDown?=null
    private var fieldData : SiteInfoModel?=null

    fun setData(data : BasicInfoModelDropDown){
        this.data = data
        notifyDataSetChanged()
    }
    fun setValueData(data : SiteInfoModel){
        this.fieldData = data
        notifyDataSetChanged()
    }
    init {
        list.add("RF Feasibility")

    }
    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)
    override fun getItemViewType(position: Int): Int {
        if (list[position] is String && list[position]==type1)
            return 1
        return 0
    }
    class ViewHold1(itemView: View) : ViewHold(itemView) {
        var binding : SoftAqutionFeasibilityBinding = SoftAqutionFeasibilityBinding.bind(itemView)

        init {
            binding.itemTitle.tag = false
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.down_arrow)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }


        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_empty,parent,false)
        when (viewType) {
            1 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.soft_aqution_feasibility, parent, false)
                return ViewHold1(view)
            }

        }
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        when (holder) {
            is ViewHold1 -> {
                holder.binding.collapsingLayout.setOnClickListener {
                    holder.binding.itemTitle.tag = !(holder.binding.itemTitle.tag as Boolean)
                    if ((holder.binding.itemTitle.tag as Boolean)) {
                        holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                        holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)

                    } else {
                        holder.binding.imgDropdown.setImageResource(R.drawable.down_arrow)
                        holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    }
                    holder.binding.itemLine.visibility = if (holder.binding.itemTitle.tag as Boolean) View.GONE else View.VISIBLE
                    holder.binding.iconLayout.visibility = if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE
                    holder.binding.imgEdit.setOnClickListener {
                        listener.detailsItemClicked()
                    }

                    holder.binding.itemCollapse.visibility = if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE
                    holder.binding.iconLayout.visibility = if (holder.binding.itemTitle.tag as Boolean) View.VISIBLE else View.GONE

                }
                holder.binding.itemTitle.text = list[position]

                if (data!=null) {
                    /*  holder.binding.siteStatusSpinner.setSpinnerData(data?.sitestatus?.data)
                      holder.binding.siteCategorySpinner.setSpinnerData(data?.sitecategory?.data)
                      holder.binding.siteOwnershipSpinner.setSpinnerData(data?.siteownership?.data)
                      holder.binding.siteTypeSpinner.setSpinnerData(data?.sitetype?.data)*/
                }
                if(fieldData!=null && fieldData?.item!!.size>0 && fieldData?.item!!.get(0).Basicinfo!=null && fieldData?.item!!.get(0).Basicinfo.size >0){
                    val siteBasicinfo: SiteBasicinfo = fieldData?.item!!.get(0).Basicinfo.get(0)
      /*              holder.binding.txSiteName.text = basicinfo.siteName
                    holder.binding.txSiteID.text = basicinfo.siteID
                    holder.binding.siteStatus.text = basicinfo.Sitestatus.get(0).name
                    holder.binding.siteCategory.text = basicinfo.Sitecategory.get(0).name
                    holder.binding.siteType.text = basicinfo.Sitetype.get(0).name
                    holder.binding.txBuildingType.text = basicinfo.Buildingtype.get(0).name
                    holder.binding.txtLocationZone.text = basicinfo.Locationzone.get(0).name
                    holder.binding.txtMaintenanceZone.text = basicinfo.MaintenancePoint.get(0).maintenancepoint
                    holder.binding.txtProjectName.text = basicinfo.Projectname.get(0).name
                    holder.binding.txtSiteInChargeName.text = basicinfo.siteInChargeName
                    holder.binding.txtSiteInChargeNumber.text = basicinfo.siteInChargeNumber
                    holder.binding.txtRentEscalation.text = ""
                    holder.binding.address.text = basicinfo.siteaddress*/


                }
            }

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface SoftAcquisitionLisListener {
        fun attachmentItemClicked()
        fun detailsItemClicked()

    }
}