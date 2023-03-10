package com.smarthub.baseapplication.ui.fragments.opcoTenancy.backhaulLink.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.MwUbrLinkInfoDetailsBinding
import com.smarthub.baseapplication.databinding.PowerFuelBillsItemBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.BackhaulLinkMWLinkInfo
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.PowerFuelBills
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class MwUbrLinkFragAdapter(var listener: MwUbrLinkInfoClickListener, var context: Context, LinkInfoData:ArrayList<BackhaulLinkMWLinkInfo>?) : RecyclerView.Adapter<MwUbrLinkFragAdapter.ViewHold>() {

    var list = ArrayList<Any>()

    fun setData(data: ArrayList<BackhaulLinkMWLinkInfo>?) {
        this.list.clear()
        this.list.addAll(data!!)
        notifyDataSetChanged()
    }
//    fun addLoading(){
//        this.list.clear()
//        this.list.add("loading")
//        notifyDataSetChanged()
//    }
    init {
        this.list.clear()
        this.list.addAll(LinkInfoData!!)
    }

    fun updateItem(pos : Int,data : BackhaulLinkMWLinkInfo){
        list[pos] = data
        notifyItemChanged(pos)
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)

    class ViewHold1(itemView: View) : ViewHold(itemView) {
        var binding : MwUbrLinkInfoDetailsBinding = MwUbrLinkInfoDetailsBinding.bind(itemView)
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
                val view = LayoutInflater.from(parent.context).inflate(R.layout.mw_ubr_link_info_details,parent,false)
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
            val data: BackhaulLinkMWLinkInfo=list[position] as BackhaulLinkMWLinkInfo
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
                holder.binding.itemTitleStr.text = String.format(context.resources.getString(R.string.rf_antenna_title_str_formate),data.LinkId,data.LinkName,Utils.getFormatedDate(data.OperationalDate.substring(0,10),"ddMMMyyyy"))
                if(data.BackhaulType.isNotEmpty())
                    AppPreferences.getInstance().setDropDown(holder.binding.BackhaulType, DropDowns.BackhaulType.name,data.BackhaulType.get(0).toString())
                if(data.OperationStatus.isNotEmpty())
                    AppPreferences.getInstance().setDropDown(holder.binding.OperationalStatus, DropDowns.OperationStatus.name,data.OperationStatus.get(0).toString())
                holder.binding.LinkID.text=data.LinkId
                holder.binding.LinkName.text=data.LinkName
                holder.binding.LinkBandwidth.text=data.LinkBandwidth
                holder.binding.Frequency.text=data.Frequency
                holder.binding.FarEndSiteName.text=data.FarEndSiteName
                holder.binding.OperationalDate.text=Utils.getFormatedDate(data.OperationalDate.substring(0,10),"dd-MMM-yyyy")
            }catch (e:Exception){
                AppLogger.log("Somthig went wrong in mw ubr linkInfo adapter ${e.localizedMessage}")
                e.localizedMessage?.let { AppLogger.log(it) }
            }
        }
    }


    override fun getItemViewType(position: Int): Int {
        return if (list.isEmpty())
            2
        else if(list[position] is BackhaulLinkMWLinkInfo)
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

    interface MwUbrLinkInfoClickListener{
        fun editModeCliked(data :PowerFuelBills,pos:Int)
    }
}