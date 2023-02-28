package com.smarthub.baseapplication.ui.fragments.opcoTenancy.radioAntenna

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.RfAntennaFragAdapterBinding
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.RadioAnteenaAndRRUData
import com.smarthub.baseapplication.utils.AppLogger

class RfAnteenaFragAdapter(var context:Context, var listener: RfAntennaItemListener, var list:ArrayList<RadioAnteenaAndRRUData>) : Adapter<RfAntennaViewHolder>() {
    var data = ArrayList<Any>()

    init {
        this.data.clear()
        this.data.addAll(list)
        notifyDataSetChanged()
    }

    fun addLoading(){
        this.data.clear()
        this.data.add("loading")
        notifyDataSetChanged()
    }

    fun setOpData(data: ArrayList<RadioAnteenaAndRRUData>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (data[position] is RadioAnteenaAndRRUData) 0 else 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RfAntennaViewHolder {
        return if (viewType == 0){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.rf_antenna_frag_adapter, parent, false)
            RfAntennaItemViewHolder(view)
        }else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.list_loading_bar, parent, false)
            RfAntennaViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RfAntennaViewHolder, position: Int) {
        if(holder is RfAntennaItemViewHolder) {
            try {
                var item = data[position] as RadioAnteenaAndRRUData

                holder.itemview.setOnClickListener {
                    listener.clickedItem(item)
                }
            }catch (e:java.lang.Exception){
                AppLogger.log("opcotenency card error : ${e.localizedMessage}")
                Toast.makeText(context,"opcotenency card error :${e.localizedMessage}", Toast.LENGTH_LONG).show()

            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

open class RfAntennaViewHolder(var itemview: View) : ViewHolder(itemview) {}

class RfAntennaItemViewHolder(itemview: View) : RfAntennaViewHolder(itemview) {
    var binding = RfAntennaFragAdapterBinding.bind(itemView)
}

interface RfAntennaItemListener{
    fun clickedItem(data : RadioAnteenaAndRRUData)
}