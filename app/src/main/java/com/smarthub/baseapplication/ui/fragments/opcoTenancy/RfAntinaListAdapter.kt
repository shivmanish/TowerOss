package com.smarthub.baseapplication.ui.fragments.opcoTenancy

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.OpcoDataItem
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.RfAnteenaData
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.utils.DropDowns

class RfAntinaListAdapter(var listener: RfAnteenaItemClickListener,opcodata: OpcoDataItem?) : RecyclerView.Adapter<RfAntinaListAdapter.ViewHold>() {

    var list : List<RfAnteenaData> ? = opcodata?.RfAntena
    var currentOpened = -1
    lateinit var data : RfAnteenaData

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
                return ViewHold1(view,listener)
            }
            2 ->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.rf_equipment_no_data,parent,false)
                return ViewHold(view)
            }

            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.rf_antina_list_item,parent,false)
                return ViewHold1(view,listener)
            }
        }

    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        if (holder is ViewHold1) {
            holder.binding.imgEdit.setOnClickListener {
                listener.editModeCliked()
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
            holder.binding.itemTitleStr.text = "3G RRH - S3058940 - 10-Nov-22"
            if (list !=null && list?.isNotEmpty()!!) {
                data = list!![position]
                holder.binding.itemTitleStr.text = "${
                    AppPreferences.getInstance().setDropDown(holder.binding.itemTitleStr, DropDowns.Technology.name,data?.Technology)
                } - ${data.AntenaSerialNumber} - ${data.InstallationDate.substring(0,10)}"
                holder.binding.AntennaSerialNumber.text=data.AntenaSerialNumber
                holder.binding.AntennaMake.text=data.AntenaMake
                holder.binding.AntennaModel.text=data.AntenaModel
                holder.binding.InstalledHeight.text=data.InstalledHeight
                holder.binding.AntennaSize.text=data.AntenaSize
                holder.binding.AntennaWeight.text=data.AntenaWeight
                holder.binding.AntennaType.text=data.AntenaType
                holder.binding.AntennaOrientation.text=data.AntenaOrentiation
                holder.binding.AntennaTilt.text=data.AntenaTilt
                holder.binding.TotalPowerRating.text=data.AntenaTotalPowerRating
                holder.binding.FeederCableLength.text=data.FeederCableLength
                holder.binding.PowerCableLength.text=data.PowerCableLength
                holder.binding.CPRICableLength.text=data.CPRICableLength
                holder.binding.GPSCableLength.text=data.GPSCableLength
                holder.binding.InstallationDate.text=data.InstallationDate
                holder.binding.remark.text=data.Remarks

                AppPreferences.getInstance().setDropDown(holder.binding.Technology, DropDowns.Technology.name,data?.Technology)
                AppPreferences.getInstance().setDropDown(holder.binding.OwnerCompany, DropDowns.OwnerCompany.name,data?.OwnerCompany)
                AppPreferences.getInstance().setDropDown(holder.binding.UserCompany, DropDowns.OemCompany.name,data?.UserCompany)
                AppPreferences.getInstance().setDropDown(holder.binding.OperationalStatus, DropDowns.OperationalStatus.name,data?.LinkOperationalStatus)
                AppPreferences.getInstance().setDropDown(holder.binding.SpaceUsed, DropDowns.SpaceUsed.name,data?.SpaceUsed)
                AppPreferences.getInstance().setDropDown(holder.binding.PoleID, DropDowns.TowerPoleId.name,data?.TowerPoleId)
                AppPreferences.getInstance().setDropDown(holder.binding.SectorNumber, DropDowns.Sectornumber.name,data?.SectorNumber)
                AppPreferences.getInstance().setDropDown(holder.binding.AntennaShape, DropDowns.AntenaShape.name,data?.AntenaShape)
            }
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

    interface RfAnteenaItemClickListener{
        fun attachmentItemClicked()
        fun editModeCliked()
    }
}