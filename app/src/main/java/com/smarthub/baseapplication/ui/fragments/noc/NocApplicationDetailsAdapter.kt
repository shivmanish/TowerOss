package com.smarthub.baseapplication.ui.fragments.noc

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.NocApplicationDetailsItemBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newNocAndComp.NocApplicationInitial
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.RfAnteenaData
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class NocApplicationDetailsAdapter(var listener: NocApplicationClickListener, cableDetails: ArrayList<NocApplicationInitial>?, var context: Context) : RecyclerView.Adapter<NocApplicationDetailsAdapter.ViewHold>() {

    var list : ArrayList<NocApplicationInitial> = cableDetails!!
    var currentOpened = -1

    fun setData(data : ArrayList<NocApplicationInitial>){
        this.list.clear()
        this.list.addAll(data)
        notifyDataSetChanged()
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)

    class ViewHold1(itemView: View,listener: NocApplicationClickListener) : ViewHold(itemView) {
        var binding : NocApplicationDetailsItemBinding = NocApplicationDetailsItemBinding.bind(itemView)
//        var adapter =  ImageAttachmentAdapter(object : ImageAttachmentAdapter.ItemClickListener{
//            override fun itemClicked() {
//                listener.attachmentItemClicked()
//            }
//        })
        init {
            binding.collapsingLayout.tag = false
            if ((binding.collapsingLayout.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }

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
                val view = LayoutInflater.from(parent.context).inflate(R.layout.noc_application_details_item,parent,false)
                ViewHold1(view,listener)
            }
            2 ->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.rf_equipment_no_data,parent,false)
                ViewHold(view)
            }

            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.noc_application_details_item,parent,false)
                ViewHold1(view,listener)
            }
        }

    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        if (holder is ViewHold1) {
            val data: NocApplicationInitial=list[position]
            holder.binding.imgEdit.setOnClickListener {
//                listener.editModeCliked(data,position)
            }
            holder.binding.collapsingLayout.setOnClickListener {
                updateList(position)
            }
            if (currentOpened == position) {
                holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                holder.binding.itemLine.visibility = View.GONE
                holder.binding.itemCollapse.visibility = View.VISIBLE
                holder.binding.imgEdit.visibility = View.VISIBLE
            } else {
                holder.binding.collapsingLayout.tag = false
                holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                holder.binding.itemLine.visibility = View.VISIBLE
                holder.binding.itemCollapse.visibility = View.GONE
                holder.binding.imgEdit.visibility = View.GONE
            }
            try {
                holder.binding.itemTitleStr.text = String.format(context.resources.getString(R.string.rf_antenna_title_str_formate),data.SrNumber,data.ApplicationNumber,Utils.getFormatedDate(data.IssueDate.substring(0,10),"ddMMMyyyy"))
                if(data.ApplicationStatus.isNotEmpty())
                    AppPreferences.getInstance().setDropDown(holder.binding.appliStatus, DropDowns.ApplicationInitialApplicationStatus.name,data.ApplicationStatus.get(0).toString())
                holder.binding.issueDate.text=Utils.getFormatedDate(data.IssueDate.substring(0,10),"dd-MMM-yyyy")
                holder.binding.applicationDate.text=Utils.getFormatedDate(data.ApplicationDate.substring(0,10),"dd-MMM-yyyy")
                holder.binding.applicationNumber.text=data.ApplicationNumber
                holder.binding.ExiparyDate.text=Utils.getFormatedDate(data.ExpiryDate.substring(0,10),"dd-MMM-yyyy")
                holder.binding.DocumentNo.text=data.DocumentNo
                holder.binding.statusDate.text=Utils.getFormatedDate(data.StatusDate.substring(0,10),"dd-MMM-yyyy")
            }catch (e:Exception){
                AppLogger.log("Somthig went wrong in rfAnteena adapter ${e.localizedMessage}")
                e.localizedMessage?.let { AppLogger.log(it) }
            }
        }
    }


    override fun getItemViewType(position: Int): Int {
        return if (list.isEmpty() || list.get(position)==null)
            2
        else
            1
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

    interface NocApplicationClickListener{
        fun attachmentItemClicked()
        fun editModeCliked(data :RfAnteenaData,pos:Int)
    }
}