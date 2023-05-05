package com.smarthub.baseapplication.ui.fragments.plandesign.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.PlandesignEquipRoomBinding
import com.smarthub.baseapplication.databinding.PlandesignEquiproomAttachmentsBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteInfo.planAndDesign.PlanningAndDesignEquipRoomEquipmentRoom
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns

class equipmentRoomAdapter(var context:Context, var listener:equipmentRoomListner,allData:List<PlanningAndDesignEquipRoomEquipmentRoom>?):RecyclerView.Adapter<equipmentRoomAdapter.ViewHold>() {
    var currentOpened = -1
    private var data:PlanningAndDesignEquipRoomEquipmentRoom?=null
    var list : ArrayList<String> = ArrayList()
     var type1 = "Equipment Room"
     var type2 = "Attachments"
     init {
         list.add("Equipment Room")
         list.add("Attachments")

         try {
             data=allData?.get(0)
         }catch (e:java.lang.Exception){
             Toast.makeText(context,"PlanDesign Equip Room Adapter error :${e.localizedMessage}", Toast.LENGTH_LONG).show()
         }
     }
     override fun getItemViewType(position: Int): Int {
         if (list[position]==type1)
             return 1
         else if (list[position]==type2)
             return 2
         return 0
     }
     open class ViewHold(itemView: View): RecyclerView.ViewHolder(itemView)

     class equipRoomViewHold(itemView: View):ViewHold(itemView){
         var binding : PlandesignEquipRoomBinding = PlandesignEquipRoomBinding.bind(itemView)

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
     class attachmentsViewHold(itemView: View,listener: equipmentRoomListner):ViewHold(itemView){
         val binding:PlandesignEquiproomAttachmentsBinding=PlandesignEquiproomAttachmentsBinding.bind(itemView)
         val adapter =  ImageAttachmentAdapter(object : ImageAttachmentAdapter.ItemClickListener{
             override fun itemClicked() {
                 listener.attachmentItemClicked()
             }
         })
         init {
             binding.collapsingLayout.tag = true

/*
             if ((binding.collapsingLayout.tag as Boolean)) {
                 binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                 binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
             } else {
                 binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                 binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
             }
*/

             val recyclerListener = itemView.findViewById<RecyclerView>(R.id.list_item)
             recyclerListener.adapter = adapter

             itemView.findViewById<View>(R.id.attach_card).setOnClickListener {
                 adapter.addItem()
             }
         }
     }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
         var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_empty,parent,false)
         when(viewType){
             1->{
                 view = LayoutInflater.from(parent.context).inflate(R.layout.plandesign_equip_room,parent,false)
                 return equipRoomViewHold(view)
             }
             2->{
                 view = LayoutInflater.from(parent.context).inflate(R.layout.plandesign_equiproom_attachments,parent,false)
                 return attachmentsViewHold(view,listener)
             }
         }
         return ViewHold(view)
     }

     override fun onBindViewHolder(holder: ViewHold, position: Int) {
         when(holder){
             is equipRoomViewHold->{
                 holder.binding.imgEdit.setOnClickListener {
                     if (AppController.getInstance().isTaskEditable)
                        listener.editequipmentRoom(data)
                 }
                 if (currentOpened == position) {
                     holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                     holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                     holder.binding.itemLine.visibility = View.GONE
                     holder.binding.itemCollapse.visibility = View.VISIBLE
                     holder.binding.imgEdit.visibility = View.VISIBLE
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

                 try
                 {
                     holder.binding.materialUsed.text=data?.MaterialUsed
                     holder.binding.sizeLenth.text=data?.SizeL
                     holder.binding.sizeWidth.text=data?.SizeB
                     holder.binding.sizeHeight.text=data?.SizeH
                     holder.binding.FoundationLenth.text=data?.FoundationSizeL
                     holder.binding.FoundationWidth.text=data?.FoundationSizeB
                     holder.binding.FoundationHeight.text=data?.FoundationSizeH
                     holder.binding.Remarks.text=data?.Remark


                     AppPreferences.getInstance().setDropDown(holder.binding.FoundationType,DropDowns.FoundationType.name,data?.FoundationType.toString())
                     AppPreferences.getInstance().setDropDown(holder.binding.type,DropDowns.EquipmentType.name,data?.Type.toString())

                 }catch (e:java.lang.Exception){
                     AppLogger.log("PlanDesign Equip Room Adapter error : ${e.localizedMessage}")
                     Toast.makeText(context,"PlanDesign Equip Room Adapter error :${e.localizedMessage}",Toast.LENGTH_LONG).show()

                 }

             }
             is attachmentsViewHold->{
                 if (currentOpened == position) {
                     holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                     holder.binding.itemLine.visibility = View.GONE
                     holder.binding.itemCollapse.visibility = View.VISIBLE
                 }
                 else {
                     holder.binding.collapsingLayout.tag = true
                     holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                     holder.binding.itemLine.visibility = View.GONE
                     holder.binding.itemCollapse.visibility = View.VISIBLE
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

    interface equipmentRoomListner{
        fun attachmentItemClicked()
        fun editequipmentRoom(data: PlanningAndDesignEquipRoomEquipmentRoom?)
    }
 }