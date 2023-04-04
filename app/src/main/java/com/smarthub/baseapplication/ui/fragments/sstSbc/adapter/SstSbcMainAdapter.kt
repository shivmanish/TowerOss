package com.smarthub.baseapplication.ui.fragments.sstSbc.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.PlanDesignListItemBinding
import com.smarthub.baseapplication.databinding.SstSbcMainListItemBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newsstSbc.SstSbcAllData
import com.smarthub.baseapplication.model.siteInfo.planAndDesign.PlanAndDesignDataItem
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils


class SstSbcMainAdapter(var listener: SstSbcAdapterListener, Id: String) : RecyclerView.Adapter<SstSbcEmptyViewHolder>() {

    var list = ArrayList<Any>()
    var id=Id
    fun setData(data: ArrayList<SstSbcAllData>?) {
        if (data!=null){
            this.list.clear()
            this.list.addAll(data)
            notifyDataSetChanged()
        }
    }

    fun addLoading(){
        this.list.clear()
        this.list.add("loading")
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position] is SstSbcAllData) 0 else 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SstSbcEmptyViewHolder {
        return if (viewType == 0) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.sst_sbc_main_list_item, parent, false)
            return SstSbcViewHolder(view)
        }else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.list_loading_bar, parent, false)
            SstSbcEmptyViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: SstSbcEmptyViewHolder, position: Int) {

        if (holder is SstSbcViewHolder) {
            val item = list[position] as SstSbcAllData
            if (item.SstSbcTeam!=null && item.SstSbcTeam?.isNotEmpty()==true){
                holder.binding.PoDate.text = Utils.getFormatedDate(item.SstSbcTeam?.get(0)?.PODate,"dd-MMM-yyyy")
                holder.binding.PoNumberText.text = item.SstSbcTeam?.get(0)?.PONumber
                if (item.SstSbcTeam?.get(0)?.Type!=null && item.SstSbcTeam?.get(0)?.Type!! > 0)
                    AppPreferences.getInstance().setDropDown(holder.binding.titel,DropDowns.SstSbcType.name,item.SstSbcTeam?.get(0)?.Type.toString())
            }

            holder.itemview.setOnClickListener {
                listener.clickedItem(item,position)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
 open class SstSbcEmptyViewHolder(var itemview: View) : RecyclerView.ViewHolder(itemview) {}

class SstSbcViewHolder(itemview: View) :SstSbcEmptyViewHolder(itemview) {
    var binding = SstSbcMainListItemBinding.bind(itemView)
}

interface SstSbcAdapterListener{
    fun clickedItem(data : SstSbcAllData?,index:Int)
}