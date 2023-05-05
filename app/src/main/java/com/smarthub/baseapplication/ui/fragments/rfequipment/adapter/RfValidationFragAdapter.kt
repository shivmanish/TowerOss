package com.smarthub.baseapplication.ui.fragments.rfequipment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AcqSurveyValidationBinding
import com.smarthub.baseapplication.databinding.TowerAttachmentInfoBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.Attachments
import com.smarthub.baseapplication.model.siteIBoard.newsstSbc.SstSbcObservation
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.ImageAttachmentCommonAdapter
import com.smarthub.baseapplication.ui.fragments.rfequipment.pojo.RfSurvey
import com.smarthub.baseapplication.ui.fragments.rfequipment.pojo.RfSurvey2
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns

class RfValidationFragAdapter(var baseFragment:BaseFragment, var listener: RfSurveyValidationListListener, data: RfSurvey?) : RecyclerView.Adapter<RfValidationFragAdapter.ViewHold>() {
    private var datalist: RfSurvey2?=null


    fun setData(data: RfSurvey2?) {
        this.datalist=data!!
        notifyDataSetChanged()
    }
    init {
        try {
            if (data!=null && data.RfSurvey2?.isNotEmpty()==true){
                datalist= data.RfSurvey2?.get(0)
            }
        }catch (e:java.lang.Exception){
            AppLogger.log("RfValidationFragAdapter error :${e.localizedMessage}")
        }
    }



    var list : ArrayList<String> = ArrayList()
    var currentOpened = -1
    var type1 = "Details"
    var type2 = "Attachments"

    init {
        list.add("Details")
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)
    
    class ViewHold1(itemView: View) : ViewHold(itemView) {
        var binding : AcqSurveyValidationBinding = AcqSurveyValidationBinding.bind(itemView)

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
    class ViewHold2(itemView: View, listener: RfSurveyValidationListListener) : ViewHold(itemView) {
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
                view = LayoutInflater.from(parent.context).inflate(R.layout.acq_survey_validation, parent, false)
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
                holder.binding.imgDropdown.visibility=View.GONE
                holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                holder.binding.itemLine.visibility = View.GONE
                holder.binding.itemCollapse.visibility = View.VISIBLE
                holder.binding.imgEdit.visibility = View.VISIBLE
                holder.binding.viewLayout.visibility = View.VISIBLE
                holder.binding.editLayout.visibility = View.GONE

                holder.binding.imgEdit.setOnClickListener {
                    if (AppController.getInstance().isTaskEditable){
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
                holder.binding.itemTitleStr.text = list[position]
                if (datalist!=null){
                    // view mode
                    if (datalist?.Observation!= null && datalist?.Observation!!>=1)
                        AppPreferences.getInstance().setDropDown(holder.binding.Approval,DropDowns.OkNotOk.name,datalist?.Observation.toString())
                    holder.binding.remarks.text=datalist?.remark

                    // edit mode
                    holder.binding.remarksEdit.setText(datalist?.remark)


                }
                else
                    AppLogger.log("error in Power Connection details data")
                
                if (datalist!=null && datalist?.Observation!= null && datalist?.Observation!!>=1)
                    AppPreferences.getInstance().setDropDown(holder.binding.ApprovalEdit,DropDowns.OkNotOk.name,datalist?.Observation.toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.ApprovalEdit,DropDowns.OkNotOk.name)
                
                holder.binding.update.setOnClickListener {
                    val tempSurveyApprovalData=RfSurvey2()
                    tempSurveyApprovalData.let {
                        it.remark = holder.binding.remarksEdit.text.toString()
                        it.Observation = holder.binding.ApprovalEdit.selectedValue.id.toIntOrNull()
                        
                        if (datalist != null)
                            it.id = datalist?.id
                    }
                    listener.RfSurveyValidationClicked(tempSurveyApprovalData)

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
                        holder.recyclerListener.adapter= ImageAttachmentCommonAdapter(baseFragment.requireContext(),
                            ArrayList(),object : ImageAttachmentCommonAdapter.ItemClickListener{
                            override fun itemClicked(item : Attachments) {
                                listener.attachmentItemClicked(item)
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



    interface RfSurveyValidationListListener {
       fun attachmentItemClicked(item : Attachments)
       fun addAttachment()
       fun RfSurveyValidationClicked(data:RfSurvey2)
    }

}
