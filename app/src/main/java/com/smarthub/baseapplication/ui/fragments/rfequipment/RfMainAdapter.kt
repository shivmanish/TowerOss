package com.smarthub.baseapplication.ui.fragments.rfequipment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.RfMainListItemBinding
import com.smarthub.baseapplication.ui.fragments.rfequipment.pojo.RfSurvey


class RfMainAdapter(var listener: RfAdapterListener, Id: String) : RecyclerView.Adapter<RfViewHolder>() {

    var list = ArrayList<RfSurvey>()
    var id=Id
    fun setData(data: ArrayList<RfSurvey>?) {
        if (data!=null){
            this.list.clear()
            this.list.addAll(data)
            notifyDataSetChanged()
        }
    }

    fun addLoading(){
        this.list.clear()
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RfViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.rf_main_list_item, parent, false)
            return RfViewHolder(view)

    }

    override fun onBindViewHolder(holder: RfViewHolder, position: Int) {

        if (holder is RfViewHolder) {
            val item = list[position] as RfSurvey
//            if (item.SstSbcTeam!=null && item.SstSbcTeam?.isNotEmpty()==true){
//                holder.binding.PoDate.text = Utils.getFormatedDate(item.SstSbcTeam?.get(0)?.PODate,"dd-MMM-yyyy")
//                holder.binding.PoNumberText.text = item.SstSbcTeam?.get(0)?.PONumber
//                if (item.SstSbcTeam?.get(0)?.Type!=null && item.SstSbcTeam?.get(0)?.Type!! > 0)
//                    AppPreferences.getInstance().setDropDown(holder.binding.titel,DropDowns.SstSbcType.name,item.SstSbcTeam?.get(0)?.Type.toString())
//            }

            holder.itemview.setOnClickListener {
                listener.clickedItem(item,position)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
 open class RfEmptyViewHolder(var itemview: View) : RecyclerView.ViewHolder(itemview) {}

class RfViewHolder(itemview: View) :RfEmptyViewHolder(itemview) {
    var binding = RfMainListItemBinding.bind(itemView)
}

interface RfAdapterListener{
    fun clickedItem(data : RfSurvey?,index:Int)
}