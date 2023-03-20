package com.smarthub.baseapplication.ui.fragments.plandesign.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.CardLayoutBinding
import com.smarthub.baseapplication.databinding.PlanDesignListItemBinding
import com.smarthub.baseapplication.model.siteInfo.planAndDesign.PlanAndDesignDataItem


class TaskPlanDesignAdapter(var listener: PlanDesignAdapterListener, Id: String) : RecyclerView.Adapter<TaskPlanDesignEmptyViewHolder>() {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskPlanDesignEmptyViewHolder {
        return if (viewType == 0) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
            return TaskPlanDesignViewHolder(view)
        }else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.list_loading_bar, parent, false)
            TaskPlanDesignEmptyViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: TaskPlanDesignEmptyViewHolder, position: Int) {

        if (holder is TaskPlanDesignViewHolder) {
            var item = list[position] as PlanAndDesignDataItem
//            holder.binding.rfiDate.text = item.created_at
//            holder.binding.rfsDate.text = item.created_at
            holder.binding?.card?.setOnClickListener {
                listener.clickedItem(item, id,position)
            }
            holder.binding.cardTitle.text = "Planning and Design "+item.id
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
 open class TaskPlanDesignEmptyViewHolder(var itemview: View) : RecyclerView.ViewHolder(itemview) {}

class TaskPlanDesignViewHolder(itemview: View) :TaskPlanDesignEmptyViewHolder(itemview) {
    var binding = CardLayoutBinding.bind(itemView)
}

interface TaskPlanDesignAdapterListener{
    fun clickedItem(data : PlanAndDesignDataItem?, Id :String,index:Int)
}