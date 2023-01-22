package com.smarthub.baseapplication.ui.fragments.qat.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.QatMainListItemBinding
import com.smarthub.baseapplication.model.siteInfo.qat.QatCardItem


class QatMainAdapter(var listener: QatMainAdapterListener, Id: String) : RecyclerView.Adapter<QatMainEmptyViewHolder>() {

    var list = ArrayList<Any>()
    var id=Id
    fun setData(data: ArrayList<QatCardItem>) {
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
        return if (list[position] is QatCardItem) 0 else 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QatMainEmptyViewHolder {
        return if (viewType == 0) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.plan_design_list_item, parent, false)
            return QatMainViewHolder(view)
        }else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.list_loading_bar, parent, false)
            QatMainEmptyViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: QatMainEmptyViewHolder, position: Int) {

        if (holder is QatMainViewHolder) {
            var item = list[position] as QatCardItem
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
 open class QatMainEmptyViewHolder(var itemview: View) : RecyclerView.ViewHolder(itemview) {}

class QatMainViewHolder(itemview: View) :QatMainEmptyViewHolder(itemview) {
    var binding = QatMainListItemBinding.bind(itemView)
}

interface QatMainAdapterListener{
    fun clickedItem(data : QatCardItem?, Id :String,index:Int)
}