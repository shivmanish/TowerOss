package com.smarthub.baseapplication.ui.adapter.qat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.CardItemBinding
import com.smarthub.baseapplication.databinding.LangItemBinding
import com.smarthub.baseapplication.databinding.SpinnerTextBinding
import com.smarthub.baseapplication.databinding.UploadPicBottomSheetBinding
import com.smarthub.baseapplication.listeners.AddImageListener
import com.smarthub.baseapplication.listeners.QatProfileListener
import com.smarthub.baseapplication.model.LangModel
import com.smarthub.baseapplication.model.atp.AtpHeaderStatus
import com.smarthub.baseapplication.model.atp.AtpHeaderTitle
import com.smarthub.baseapplication.model.atp.AtpListItem

class AtpAddImageListAdapter( var listener: AddImageListener) : RecyclerView.Adapter<AtpAddImageListAdapter.ViewHold>() {

    var list =ArrayList<Any>()
     fun UpdateList(list:ArrayList<Any> )
     {this.list.addAll(list)
         notifyDataSetChanged()

     }

    init {
 //       list.add(AtpListItem(AtpHeaderStatus("",""),ArrayList()))
//        list.add(AtpListItem(AtpHeaderStatus("",""),ArrayList()))
//        list.add(AtpListItem(AtpHeaderStatus("",""),ArrayList()))
//        list.add(AtpListItem(AtpHeaderStatus("",""),ArrayList()))
    }

    fun addListItem(){
        list.add(AtpListItem(AtpHeaderStatus("",""),ArrayList()))
        notifyItemChanged(list.size - 1)
    }

    class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var uploadPicBottomSheetBinding = UploadPicBottomSheetBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.upload_pic_bottom_sheet,parent,false)
        var layoutCamera=view.findViewById<RecyclerView>(R.id.layoutCamera)
        var layoutGallery=view.findViewById<RecyclerView>(R.id.layoutGallery)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
           holder.uploadPicBottomSheetBinding.layoutCamera.setOnClickListener(){
             listener.itemCameraClicked()
         }
         holder.uploadPicBottomSheetBinding.layoutGallery.setOnClickListener(){
             listener.itemClicked()
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}