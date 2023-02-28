package com.smarthub.baseapplication.ui.fragments.opcoTenancy

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.PowerLoadListItemBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.LoadMeasermentData
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.OpcoDataItem
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.PowerLoadData
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class PowerLoadAdapter (var listener: PowerLoadItemClickListener, loadMesurmentData: ArrayList<LoadMeasermentData>, var context: Context) : RecyclerView.Adapter<PowerLoadAdapter.ViewHold>() {

    var list : List<LoadMeasermentData> ? = loadMesurmentData
    var currentOpened = -1
    lateinit var data : LoadMeasermentData



    class ViewHold(itemView: View, listener: PowerLoadItemClickListener) : RecyclerView.ViewHolder(itemView) {
        var binding : PowerLoadListItemBinding = PowerLoadListItemBinding.bind(itemView)
        var adapter =  ImageAttachmentAdapter(object : ImageAttachmentAdapter.ItemClickListener{
            override fun itemClicked() {
                listener.attachmentItemClicked()
            }
        })
        init {
            binding.collapsingLayout.tag = false
            if ((binding.collapsingLayout.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }

            val recyclerListener = itemView.findViewById<RecyclerView>(R.id.list_item)
            recyclerListener.adapter = adapter

            itemView.findViewById<View>(R.id.attach_card).setOnClickListener {
                adapter.addItem()
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
//        return when (viewType) {
//            1-> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.power_load_list_item,parent,false)
                return ViewHold(view,listener)
//            }
//            2->{
//                val view = LayoutInflater.from(parent.context).inflate(R.layout.rf_equipment_no_data,parent,false)
//                return ViewHold(view)
//            }
//            else -> {
//                val view = LayoutInflater.from(parent.context).inflate(R.layout.power_load_list_item,parent,false)
//                return ViewHold1(view,listener)
//            }
//        }

    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHold, position: Int) {
//        if (holder is ViewHold1) {
            holder.binding.imgEdit.setOnClickListener {
//                listener.EditItemDialouge(list!!.get(position),opcodata)
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

            if (list !=null && list?.isNotEmpty()!!) {
                data = list!![position]
                holder.binding.itemTitleStr.text = String.format(context.resources.getString(R.string.rf_antenna_title_str_formate),data.MeasurementPoint,data.LoadVoltage,Utils.getFormatedDate(data.MeasurementDate.substring(0,10),"ddMMMyyyy"))
                holder.binding.MeasuredPoint.text=data.MeasurementPoint
                holder.binding.MeasuredDate.text= Utils.getFormatedDate(data.MeasurementDate.substring(0,10),"dd-MMM-yyyy")
                holder.binding.PowerType.text=data.PowerType.toString()
                holder.binding.LoadCurrent.text=data.LoadCurrent
                holder.binding.LoadVoltage.text=data.LoadVoltage
                holder.binding.LoadWattage.text=data.LoadWattage
                holder.binding.OPCOExcutiveName.text=data.OpcoExecutiveName
                holder.binding.Remark.text = data.Remark

        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (list?.isEmpty()!! || list?.get(position)==null)
            2
        else
            1
    }

    override fun getItemCount(): Int {
        return list?.size!!
    }

    var recyclerView: RecyclerView?=null
    fun updateList(position: Int){
        currentOpened = if(currentOpened == position) -1 else position
        notifyDataSetChanged()
        if (this.recyclerView!=null)
            this.recyclerView?.scrollToPosition(position)
    }

    interface PowerLoadItemClickListener{
        fun attachmentItemClicked()
        fun EditItemDialouge(get: PowerLoadData, opcodata: OpcoDataItem?)
    }

}