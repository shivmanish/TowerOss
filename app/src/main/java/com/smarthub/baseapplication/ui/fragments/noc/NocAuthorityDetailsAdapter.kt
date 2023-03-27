package com.smarthub.baseapplication.ui.fragments.noc

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.NocApplicationDetailsItemBinding
import com.smarthub.baseapplication.databinding.NocAuthorityItemsBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newNocAndComp.NocApplicationInitial
import com.smarthub.baseapplication.model.siteIBoard.newNocAndComp.NocAuthorityDetail
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityEquipmentAC
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.RfAnteenaData
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class NocAuthorityDetailsAdapter(var listener: NocauthorityClickListener, var authorityDetails: ArrayList<NocAuthorityDetail>?, var context: Context) : RecyclerView.Adapter<NocAuthorityDetailsAdapter.ViewHold>() {
    private var datalist: NocAuthorityDetail?=null
    var list : ArrayList<String> = ArrayList()
    var currentOpened = -1
    fun setData(data : ArrayList<NocAuthorityDetail>?){
        if (data!=null && data.isNotEmpty()){
            datalist= data[0]
            notifyDataSetChanged()
        }
    }
    var type1 = "Authority Details"
    init {
        list.add("Authority Details")
        if (authorityDetails!=null && authorityDetails?.isNotEmpty()==true ){
            datalist=authorityDetails?.get(0)
        }
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)

    class ViewHold1(itemView: View,listener: NocauthorityClickListener) : ViewHold(itemView) {
        var binding : NocAuthorityItemsBinding = NocAuthorityItemsBinding.bind(itemView)
//        var adapter =  ImageAttachmentAdapter(object : ImageAttachmentAdapter.ItemClickListener{
//            override fun itemClicked() {
//                listener.attachmentItemClicked()
//            }
//        })
        init {
//            binding.collapsingLayout.tag = false
//            if ((binding.collapsingLayout.tag as Boolean)) {
//                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
//                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
//            } else {
//                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
//                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
//            }

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
                val view = LayoutInflater.from(parent.context).inflate(R.layout.noc_authority_items,parent,false)
                ViewHold1(view,listener)
            }
            2 ->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.rf_equipment_no_data,parent,false)
                ViewHold(view)
            }

            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.noc_authority_items,parent,false)
                ViewHold1(view,listener)
            }
        }

    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        if (holder is ViewHold1) {
            holder.binding.collapsingLayout.setOnClickListener {
                updateList(position)
            }
            holder.binding.imgEdit.setOnClickListener {

            }
            try {
                holder.binding.Name.text=datalist?.Name
                holder.binding.EmailID.text=datalist?.EmailId
                holder.binding.ContactNumber.text=datalist?.ContactNumber
                holder.binding.PreferredLaungauge.text=datalist?.PreferredLangauage
                holder.binding.Address.text=datalist?.Address
            }catch (e:Exception){
                AppLogger.log("Somthig went wrong in rfAnteena adapter ${e.localizedMessage}")
                e.localizedMessage?.let { AppLogger.log(it) }
            }
        }
    }


    override fun getItemViewType(position: Int): Int {
        return if (list[position]=="Authority Details")
            1
        else
            2
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

    interface NocauthorityClickListener{
        fun attachmentItemClicked()
        fun updataDataClicked(data :NocAuthorityDetail)
    }
}