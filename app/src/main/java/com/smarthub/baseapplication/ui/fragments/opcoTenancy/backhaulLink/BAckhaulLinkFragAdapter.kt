package com.smarthub.baseapplication.ui.fragments.opcoTenancy.backhaulLink

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.BackhaulLinkData
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.*
import com.smarthub.baseapplication.model.siteIBoard.newSiteInfoDataModel.SiteAddressData
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.tableAdapters.InsidePremisesTableAdapter
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.tableAdapters.OutsidePremisesTableAdapter
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.tableAdapters.PropertyOwnerTableAdapter
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns

class BAckhaulLinkFragAdapter(var context: Context, var listener: BackhaulItemListListener, data:ArrayList<BackhaulLinkData>?) : RecyclerView.Adapter<BAckhaulLinkFragAdapter.ViewHold>() {
    private var datalist: BackhaulLinkData?=null
    var list : ArrayList<String> = ArrayList()
    fun setData(data: BackhaulLinkData?) {
        if (data!=null){
            this.list.clear()
            this.list.add("Backhaul Link Details")
            this.list.add("MW/ UBR")
            this.list.add("Fiber Optic")
            this.list.add("Attachments")
            this.datalist=data!!
            notifyDataSetChanged()
        }
        else {
            list.clear()
            list.add("Empty")
            notifyDataSetChanged()
        }

    }
    init {
        try {
            if (data!=null && data.isNotEmpty()){
                datalist=data.get(0)
                list.clear()
                list.add("Backhaul Link Details")
                list.add("MW/ UBR")
                list.add("Fiber Optic")
                list.add("Attachments")
            }
            else{
                list.clear()
                list.add("Empty")
            }
        }catch (e:java.lang.Exception){
            Toast.makeText(context,"TowerInfoFrag error :${e.localizedMessage}", Toast.LENGTH_LONG).show()
        }
    }
    fun addLoading(){
        this.list.clear()
        this.list.add("loading")
        notifyDataSetChanged()
    }


    var currentOpened = -1
    var type1 = "Backhaul Link Details"
    var type2 = "MW/ UBR"
    var type3 = "Fiber Optic"
    var type4 = "Attachments"
    var type5 = "loading"


    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)

    class ViewHold1(itemView: View) : ViewHold(itemView) {
        var binding : BackhaulLinkDetailsItemBinding = BackhaulLinkDetailsItemBinding.bind(itemView)

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
    class ViewHold2(itemView: View) : ViewHold(itemView) {
        var binding : BackhaulMbubrOpticsCommonDialougeBinding = BackhaulMbubrOpticsCommonDialougeBinding.bind(itemView)

    }
    class ViewHold3(itemView: View) : ViewHold(itemView) {
        var binding : BackhaulMbubrOpticsCommonDialougeBinding = BackhaulMbubrOpticsCommonDialougeBinding.bind(itemView)

    }
    class ViewHold4(itemView: View,listener: BackhaulItemListListener) : ViewHold(itemView) {
        var binding: BackhaulLinkAttachmentsBinding = BackhaulLinkAttachmentsBinding.bind(itemView)
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

    override fun getItemViewType(position: Int): Int {
        if (list[position]==type1)
            return 1
        else if (list[position]==type2)
            return 2
        else if (list[position]==type3)
            return 3
        else if (list[position]==type4)
            return 4
        else if (list[position]==type5)
            return 5
        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.no_list_data,parent,false)
        when (viewType) {
            1 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.backhaul_link_details_item, parent, false)
                return ViewHold1(view)
            }
            2 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.backhaul_mbubr_optics_common_dialouge, parent, false)
                return ViewHold2(view)
            }
            3 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.backhaul_mbubr_optics_common_dialouge, parent, false)
                return ViewHold3(view)
            }
            4 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.backhaul_link_attachments, parent, false)
                return ViewHold4(view,listener)
            }
            5 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.list_loading_bar, parent, false)
                return ViewHold(view)
            }

        }
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        when (holder) {
            is ViewHold1 -> {
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                    holder.binding.imgEdit.visibility = View.VISIBLE

                    holder.binding.imgEdit.setOnClickListener {
//                        listener.EditTowerItem()
                    }
                }
                else {
                    holder.binding.collapsingLayout.tag = false
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                    holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    holder.binding.itemLine.visibility = View.VISIBLE
                    holder.binding.itemCollapse.visibility = View.GONE
                    holder.binding.imgEdit.visibility = View.GONE
                }
                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
                holder.binding.itemTitleStr.text = list[position]
                try {
                    if (datalist!=null ){
                        holder.binding.BackhaulNodeCategory.text=datalist?.BackhaulNodeCategory.toString()
                        holder.binding.remarks.text=datalist?.Remark
                    }
                    else
                        AppLogger.log("error in Acquisition Survey data or Property details data")
                }catch (e:java.lang.Exception){
                    AppLogger.log("ToewerInfoadapter error : ${e.localizedMessage}")
                }

            }
            is ViewHold2 -> {

                holder.binding.itemTitleStr.text = list[position]
                try {
                    if (datalist!=null ){
                        holder.itemView.setOnClickListener {
                            listener.MwUbrCLicked(datalist)
                        }
                    }
                    else
                        AppLogger.log("error in Acquisition Survey data or External Structure data")
                }catch (e:java.lang.Exception){
                    AppLogger.log("ToewerInfoadapter error : ${e.localizedMessage}")
                }

            }
            is ViewHold3 -> {
                holder.binding.itemTitleStr.text = list[position]
                try {
                    if (datalist!=null ){
                        holder.itemView.setOnClickListener {
                            listener.FiberOpticsClicked(datalist)
                        }
                    }
                    else
                        AppLogger.log("error in Acquisition Survey data or Property details data")
                }catch (e:java.lang.Exception){
                    AppLogger.log("ToewerInfoadapter error : ${e.localizedMessage}")
                }

            }
            is ViewHold4 -> {
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                }
                else {
                    holder.binding.collapsingLayout.tag = false
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                    holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    holder.binding.itemLine.visibility = View.VISIBLE
                    holder.binding.itemCollapse.visibility = View.GONE
                }
                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
                holder.binding.itemTitleStr.text = list[position]
            }
        }
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



    interface BackhaulItemListListener {
       fun attachmentItemClicked()
       fun MwUbrCLicked(data:BackhaulLinkData?)
       fun FiberOpticsClicked(data:BackhaulLinkData?)

    }

}
