package com.smarthub.baseapplication.ui.fragments.opcoTenancy.radioAntenna

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.RfAntinaListItemBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.RadioAnteenaDetails
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.RfAnteenaData
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class RadioAntennaDetailsAdapter(var listener: RfAnteenaItemClickListener, antennaDetails: ArrayList<RadioAnteenaDetails>?, var context: Context) : RecyclerView.Adapter<RadioAntennaDetailsAdapter.ViewHold>() {

    var list : ArrayList<RadioAnteenaDetails> = antennaDetails!!
    var currentOpened = -1

    fun updateItem(pos : Int,data : RadioAnteenaDetails){
        list[pos] = data
        notifyItemChanged(pos)
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)

    class ViewHold1(itemView: View,listener: RfAnteenaItemClickListener) : ViewHold(itemView) {
        var binding : RfAntinaListItemBinding = RfAntinaListItemBinding.bind(itemView)
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
        return when (viewType) {
            1 ->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.rf_antina_list_item,parent,false)
                ViewHold1(view,listener)
            }
            2 ->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.rf_equipment_no_data,parent,false)
                ViewHold(view)
            }

            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.rf_antina_list_item,parent,false)
                ViewHold1(view,listener)
            }
        }

    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        if (holder is ViewHold1) {
            var data: RadioAnteenaDetails=list[position]
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
                data=list[position]
                AppLogger.log("data of $position is =======: $data")
                holder.binding.itemTitleStr.text = String.format(context.resources.getString(R.string.rf_antenna_title_str_formate),data.SerialNo,data.Model,Utils.getFormatedDate(data.InstallationDate.substring(0,10),"ddMMMyyy"))
                holder.binding.SerialNumber.text=data.SerialNo
                holder.binding.Make.text=data.Make
                holder.binding.Model.text=data.Model
                holder.binding.Height.text=data.Height
                holder.binding.weight.text=data.Weight
                holder.binding.Type.text=data.Type
                holder.binding.sectorNumber.text=data.No.toString()
                holder.binding.TRXCount.text=data.TrxCount.toString()
                holder.binding.AzimuthOrientation.text=data.AzimuthOrientation
                holder.binding.sizeB.text=data.SizeB
                holder.binding.sizeH.text=data.SizeH
                holder.binding.sizeL.text=data.SizeL
                holder.binding.InstallationDate.text=Utils.getFormatedDate(data.InstallationDate.substring(0,10),"dd-MMM-yyyy")
                holder.binding.remark.text=data.Remark
                if (data.OperationStatus.isNotEmpty())
                    AppPreferences.getInstance().setDropDown(holder.binding.OperationalStatus, DropDowns.OperationStatus.name,data.OperationStatus.get(0).toString())
            }catch (e:Exception){
                AppLogger.log("Somthig went wrong in rfAnteena adapter ${e.localizedMessage}")
                e.localizedMessage?.let { AppLogger.log(it) }
            }

        }
    }


    override fun getItemViewType(position: Int): Int {
        return if (list.isEmpty())
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

    interface RfAnteenaItemClickListener{
        fun attachmentItemClicked()
        fun editModeCliked(data :RfAnteenaData,pos:Int)
    }
}