package com.smarthub.baseapplication.ui.fragments.plandesign.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.PlanDesignListItemBinding
import com.smarthub.baseapplication.model.siteInfo.planAndDesign.PlanAndDesignDataItem


class PlanDesignAdapter(var listener: PlanDesignAdapterListener, Id: String) : RecyclerView.Adapter<PlanDesignEmptyViewHolder>() {

    var list = ArrayList<Any>()
    var id=Id
    fun setData(data: ArrayList<PlanAndDesignDataItem>) {
        this.list.clear()
        this.list.addAll(data)
        notifyDataSetChanged()
    }

    fun addLoading(){
        this.list.clear()
        this.list.add("loading")
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position] is PlanAndDesignDataItem) 0 else 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanDesignEmptyViewHolder {
        return if (viewType == 0) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.plan_design_list_item, parent, false)
            return PlanDesignViewHolder(view)
        }else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.list_loading_bar, parent, false)
            PlanDesignEmptyViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: PlanDesignEmptyViewHolder, position: Int) {

        if (holder is PlanDesignViewHolder) {
            var item = list[position] as PlanAndDesignDataItem
            holder.binding.rfiDate.text = "DataNotFoundFromApi"
            holder.binding.rfsDate.text = "DataNotFoundFromApi"
            holder.binding?.cardItem?.setOnClickListener {
                listener.clickedItem(item, id,position)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
 open class PlanDesignEmptyViewHolder(var itemview: View) : RecyclerView.ViewHolder(itemview) {}

class PlanDesignViewHolder(itemview: View) :PlanDesignEmptyViewHolder(itemview) {
    var binding = PlanDesignListItemBinding.bind(itemView)
}

interface PlanDesignAdapterListener{
    fun clickedItem(data : PlanAndDesignDataItem?, Id :String,index:Int)
}