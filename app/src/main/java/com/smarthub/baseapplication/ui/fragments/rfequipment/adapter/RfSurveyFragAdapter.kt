package com.smarthub.baseapplication.ui.fragments.rfequipment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.RfSurveyItemsBinding
import com.smarthub.baseapplication.model.siteIBoard.Attachments
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.ImageAttachmentCommonAdapter
import com.smarthub.baseapplication.ui.fragments.rfequipment.pojo.RfSurvey1
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils

class RfSurveyFragAdapter(var listener: RfSurveyClickListener, var baseFragment: BaseFragment, rfSurveyData:ArrayList<RfSurvey1>?) : RecyclerView.Adapter<RfSurveyFragAdapter.ViewHold>() {

    var list = ArrayList<Any>()

    fun setData(data: ArrayList<RfSurvey1>?) {
        this.list.clear()
        this.list.addAll(data!!)
        notifyDataSetChanged()
    }
    fun addLoading(){
        this.list.clear()
        this.list.add("loading")
        notifyDataSetChanged()
    }
    init {
        this.list.clear()
        this.list.addAll(rfSurveyData!!)
    }

    fun updateItem(pos : Int,data : RfSurvey1){
        list[pos] = data
        notifyItemChanged(pos)
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)

    class ViewHold1(itemView: View) : ViewHold(itemView) {
        var binding : RfSurveyItemsBinding = RfSurveyItemsBinding.bind(itemView)
        val BsnlAttachment:RecyclerView = binding.BsnlAttachmentsListItem
        val JioAttachment:RecyclerView = binding.JioAttachmentsListItem
        val AirtelAttachment:RecyclerView = binding.AirtelAttachmentsListItem
        val VodafoneAttachment:RecyclerView = binding.VodafoneAttachmentsListItem
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        return when (viewType) {
            1 ->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.rf_survey_items,parent,false)
                ViewHold1(view)
            }
            2 ->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.rf_equipment_no_data,parent,false)
                ViewHold(view)
            }

            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.list_loading_bar,parent,false)
                ViewHold(view)
            }
        }

    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        if (holder is ViewHold1) {
            val data: RfSurvey1=list[position] as RfSurvey1
            holder.binding.imgEdit.setOnClickListener {
                if (AppController.getInstance().isTaskEditable) {
                    holder.binding.viewLayout.visibility = View.GONE
                    holder.binding.editLayout.visibility = View.VISIBLE
                    holder.binding.BSNlRsrpValueEditLayout.visibility=View.VISIBLE
                    holder.binding.JioRsrpValueEditLayout.visibility=View.VISIBLE
                    holder.binding.AirtelRsrpValueEditLayout.visibility=View.VISIBLE
                    holder.binding.VodafoneRsrpValueEditLayout.visibility=View.VISIBLE
                    holder.binding.bottomLayout.visibility=View.VISIBLE

                    holder.binding.BSNlRsrpValueViewLayout.visibility=View.GONE
                    holder.binding.JioRsrpValueViewLayout.visibility=View.GONE
                    holder.binding.AirtelRsrpValueViewLayout.visibility=View.GONE
                    holder.binding.VodafoneRsrpValueViewLayout.visibility=View.GONE
                }
            }
            holder.binding.cancel.setOnClickListener {
                holder.binding.viewLayout.visibility = View.VISIBLE
                holder.binding.editLayout.visibility = View.GONE
                
                holder.binding.BSNlRsrpValueEditLayout.visibility=View.GONE
                holder.binding.JioRsrpValueEditLayout.visibility=View.GONE
                holder.binding.AirtelRsrpValueEditLayout.visibility=View.GONE
                holder.binding.VodafoneRsrpValueEditLayout.visibility=View.GONE
                holder.binding.bottomLayout.visibility=View.GONE

                holder.binding.BSNlRsrpValueViewLayout.visibility=View.VISIBLE
                holder.binding.JioRsrpValueViewLayout.visibility=View.VISIBLE
                holder.binding.AirtelRsrpValueViewLayout.visibility=View.VISIBLE
                holder.binding.VodafoneRsrpValueViewLayout.visibility=View.VISIBLE
            }
            holder.binding.BsnlAttachmentsAttachCard.setOnClickListener {
                if (data.id!=null){
                    listener.addAttachment(data.id.toString(),"RfSurvey1Bsnl")
                }
                else
                    Toast.makeText(baseFragment.requireContext(),"Firstly fill data then Add Attachment",
                        Toast.LENGTH_SHORT).show()
            }
            holder.binding.jioAttachmentsAttachCard.setOnClickListener {
                if (data.id!=null){
                    listener.addAttachment(data.id.toString(),"RfSurvey1Jio")
                }
                else
                    Toast.makeText(baseFragment.requireContext(),"Firstly fill data then Add Attachment",
                        Toast.LENGTH_SHORT).show()
            }
            holder.binding.AirtelAttachmentsAttachCard.setOnClickListener {
                if (data.id!=null){
                    listener.addAttachment(data.id.toString(),"RfSurvey1Airtel")
                }
                else
                    Toast.makeText(baseFragment.requireContext(),"Firstly fill data then Add Attachment",
                        Toast.LENGTH_SHORT).show()
            }
            holder.binding.VodafoneAttachmentsAttachCard.setOnClickListener {
                if (data.id!=null){
                    listener.addAttachment(data.id.toString(),"RfSurvey1Vi")
                }
                else
                    Toast.makeText(baseFragment.requireContext(),"Firstly fill data then Add Attachment",
                        Toast.LENGTH_SHORT).show()
            }
            if (currentOpened == position) {
                holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                holder.binding.itemLine.visibility = View.GONE
                holder.binding.itemCollapse.visibility = View.VISIBLE
                holder.binding.imgEdit.visibility = View.VISIBLE
                holder.binding.viewLayout.visibility = View.VISIBLE
                holder.binding.editLayout.visibility = View.GONE
                
                holder.binding.BSNlRsrpValueEditLayout.visibility=View.GONE
                holder.binding.JioRsrpValueEditLayout.visibility=View.GONE
                holder.binding.AirtelRsrpValueEditLayout.visibility=View.GONE
                holder.binding.VodafoneRsrpValueEditLayout.visibility=View.GONE
                holder.binding.bottomLayout.visibility=View.GONE

                holder.binding.BSNlRsrpValueViewLayout.visibility=View.VISIBLE
                holder.binding.JioRsrpValueViewLayout.visibility=View.VISIBLE
                holder.binding.AirtelRsrpValueViewLayout.visibility=View.VISIBLE
                holder.binding.VodafoneRsrpValueViewLayout.visibility=View.VISIBLE
            } else {
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
            // titel text
            holder.binding.itemTitleStr.text = String.format(baseFragment.resources.getString(R.string.two_string_format),position.plus(1).toString(),Utils.getFormatedDate(data.SurveyDate,"ddMMMyyyy"))

            //view mode
            holder.binding.SrNo.text=position.plus(1).toString()
            holder.binding.SurveyDate.text=Utils.getFormatedDate(data.SurveyDate,"dd-MMM-yyyy")
            holder.binding.Latitude.text=data.locLatitude
            holder.binding.Date.text=Utils.getFormatedDateMonthYear(data.ExtraDate,"MMM-yyyy")
            holder.binding.Longitude.text=data.locLongitude
            holder.binding.Description.text=data.Description
            holder.binding.remarks.text=data.remark
            holder.binding.BSNlRsrpValueView.text=data.Bsnl
            holder.binding.AirtelRsrpValueView.text=data.Airtel
            holder.binding.jioRsrpValueView.text=data.Jio
            holder.binding.VodafoneRsrpValueView.text=data.Vi

            // edit mode
            holder.binding.SurveyDateEdit.text=Utils.getFormatedDate(data.SurveyDate,"dd-MMM-yyyy")
            holder.binding.DateEdit.text=Utils.getFormatedDateMonthYear(data.ExtraDate,"MMM-yyyy")
            holder.binding.LatitudeEdit.setText(data.locLatitude)
            holder.binding.LongitudeEdit.setText(data.locLongitude)
            holder.binding.DescriptionEdit.setText(data.Description)
            holder.binding.remarksEdit.setText(data.remark)
            holder.binding.BSNlRsrpValueEdit.setText(data.Bsnl)
            holder.binding.jioRsrpValueEdit.setText(data.Jio)
            holder.binding.AirtelRsrpValueEdit.setText(data.Airtel)
            holder.binding.VodafoneRsrpValueEdit.setText(data.Vi)
            holder.binding.update.setOnClickListener {
                val tempRfSurveyData=RfSurvey1()
                tempRfSurveyData.let {
                    it.locLatitude=holder.binding.LatitudeEdit.text.toString()
                    it.locLongitude=holder.binding.LongitudeEdit.text.toString()
                    it.SurveyDate=Utils.getFullFormatedDate(holder.binding.SurveyDateEdit.text.toString())
                    it.ExtraDate=Utils.getFullFormatedDate(holder.binding.DateEdit.text.toString())
                    it.Description=holder.binding.DescriptionEdit.text.toString()
                    it.remark=holder.binding.remarksEdit.text.toString()
                    it.Bsnl=holder.binding.BSNlRsrpValueEdit.text.toString()
                    it.Jio=holder.binding.jioRsrpValueEdit.text.toString()
                    it.Airtel=holder.binding.AirtelRsrpValueEdit.text.toString()
                    it.Vi=holder.binding.VodafoneRsrpValueEdit.text.toString()
                    it.id=data.id
                }
                listener.updateRfSurvey(tempRfSurveyData)
            }
            baseFragment.setDatePickerView(holder.binding.SurveyDateEdit)
            baseFragment.setDatePickerView(holder.binding.DateEdit)

            if (data.attachmentBsnl!=null){
                holder.BsnlAttachment.adapter= ImageAttachmentCommonAdapter(baseFragment.requireContext(),data.attachmentBsnl!!,object : ImageAttachmentCommonAdapter.ItemClickListener{
                    override fun itemClicked(item : Attachments) {
                        listener.attachmentItemClicked(item)
                    }
                })
            }
            else
                AppLogger.log("Basnl Attachments Error")

            if (data.attachmentJio!=null){
                holder.JioAttachment.adapter= ImageAttachmentCommonAdapter(baseFragment.requireContext(),data.attachmentJio!!,object : ImageAttachmentCommonAdapter.ItemClickListener{
                    override fun itemClicked(item : Attachments) {
                        listener.attachmentItemClicked(item)
                    }
                })
            }
            else
                AppLogger.log("JIO Attachments Error")

            if (data.attachmentAirtel!=null){
                holder.AirtelAttachment.adapter= ImageAttachmentCommonAdapter(baseFragment.requireContext(),data.attachmentAirtel!!,object : ImageAttachmentCommonAdapter.ItemClickListener{
                    override fun itemClicked(item : Attachments) {
                        listener.attachmentItemClicked(item)
                    }
                })
            }
            else
                AppLogger.log("Airtel Attachments Error")

            if (data.attachmentVi!=null){
                holder.VodafoneAttachment.adapter= ImageAttachmentCommonAdapter(baseFragment.requireContext(),data.attachmentVi!!,object : ImageAttachmentCommonAdapter.ItemClickListener{
                    override fun itemClicked(item : Attachments) {
                        listener.attachmentItemClicked(item)
                    }
                })
            }
            else
                AppLogger.log("Vodafone Attachments Error")
        }
    }


    override fun getItemViewType(position: Int): Int {
        return if (list.isEmpty())
            2
        else if(list[position] is RfSurvey1)
            1
        else
            3
    }

    override fun getItemCount(): Int {
        return list.size
    }

    var currentOpened = -1
    var recyclerView: RecyclerView?=null
    fun updateList(position: Int){
        currentOpened = if(currentOpened == position) -1 else position
        notifyDataSetChanged()
        if (this.recyclerView!=null)
            this.recyclerView?.scrollToPosition(position)
    }

    interface RfSurveyClickListener{
        fun updateRfSurvey(updatedData:RfSurvey1)
        fun addAttachment(id:String,moduel:String)
        fun attachmentItemClicked(data:Attachments)
    }
}