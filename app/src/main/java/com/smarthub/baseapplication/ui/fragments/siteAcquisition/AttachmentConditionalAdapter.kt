package com.smarthub.baseapplication.ui.fragments.siteAcquisition

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ImgCardViewBinding
import com.smarthub.baseapplication.databinding.LayoutImgAttachmentBinding
import com.smarthub.baseapplication.model.siteIBoard.Attachments
import com.smarthub.baseapplication.model.siteIBoard.AttachmentsConditions
import com.smarthub.baseapplication.ui.fragments.ImageAttachmentCommonAdapter
import com.smarthub.baseapplication.utils.AppLogger

class AttachmentConditionalAdapter(var context:Context, var attachments:ArrayList<Attachments>?, var list:ArrayList<AttachmentsConditions>,
                                   var subTabId:Int,var subTabDataId:Int?, var listener: AttachmentConditionsListener) : RecyclerView.Adapter<AttachmentConditionalAdapter.ViewHold>() {

    fun addItem(){
//        list.add("item1")
//        notifyItemChanged(list.size.plus(1))
    }
    init {
        for (item in list){
            if (subTabId!=item.SubSection)
                list.remove(item)
            if (item.id==0)
                list.remove(item)
        }
        val otherAttachment=AttachmentsConditions(Section = subTabId.div(10), SubSection = subTabId, name = "Others", id = 0)
        list.add(otherAttachment)
    }
    class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding : LayoutImgAttachmentBinding = LayoutImgAttachmentBinding.bind(itemView)
        val recyclerListener:RecyclerView = binding.listItem
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_img_attachment,parent,false)
        return ViewHold(view)
    }
    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        val attachmentsItem:AttachmentsConditions=list[position]
        if (attachmentsItem.isMandatory==true)
            holder.binding.AttachmentTitel.text="${position.plus(1)}"+attachmentsItem.name+" ${R.string.Astric_Mark}"
        else
            holder.binding.AttachmentTitel.text="${position.plus(1)}"+attachmentsItem.name
        AppLogger.log("Sunsection Id : ${attachmentsItem.SubSection}")
        try {
            if (attachments!=null){
                holder.recyclerListener.adapter= ImageAttachmentCommonAdapter(context,filterAttachment(attachmentsItem.id,attachments),object : ImageAttachmentCommonAdapter.ItemClickListener{
                    override fun itemClicked(item : Attachments) {
                        listener.attachmentItemClicked()
                    }
                })
            }
            else
                AppLogger.log("Attachments Error")
        }catch (e:java.lang.Exception){
            AppLogger.log("Acq Survey error : ${e.localizedMessage}")
        }
        holder.binding.attachCard.setOnClickListener {
            if (subTabDataId!=null){
                listener.addAttachmentItemClicked(attachmentsItem.id)
            }
            else
                Toast.makeText(context,"Firstly fill data then Add Attachment",
                    Toast.LENGTH_SHORT).show()
        }
    }
    override fun getItemCount(): Int {
        return list.size
    }
    fun filterAttachment(attachmentid:Int,attachmetns:ArrayList<Attachments>?):ArrayList<Attachments>{
        val filteredAttachments:ArrayList<Attachments> = ArrayList()
        if (attachmetns!=null) {
            for (item in attachmetns){
                if (item.category==attachmentid){
                    filteredAttachments.add(item)
                }
            }
        }
        return filteredAttachments
    }
    interface AttachmentConditionsListener{
        fun attachmentItemClicked()
        fun addAttachmentItemClicked(catagoeyId:Int)
    }
}