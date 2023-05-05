package com.smarthub.baseapplication.ui.fragments.sstSbc.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.SstSbcTeamItemBinding
import com.smarthub.baseapplication.databinding.TowerAttachmentInfoBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.Attachments
import com.smarthub.baseapplication.model.siteIBoard.newsstSbc.SstSbcAllData
import com.smarthub.baseapplication.model.siteIBoard.newsstSbc.SstSbcTeam
import com.smarthub.baseapplication.ui.fragments.ImageAttachmentCommonAdapter
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class SstSbcTeamFragAdapter(var baseFragment:BaseFragment, var listener: SstSbcTeamListListener, data:SstSbcAllData?) : RecyclerView.Adapter<SstSbcTeamFragAdapter.ViewHold>() {
    private var datalist: SstSbcTeam?=null

    fun setData(data: SstSbcTeam?) {
        this.datalist=data!!
        notifyDataSetChanged()
    }
    init {
        try {
            if (data!=null && data.SstSbcTeam?.isNotEmpty()!!){
                datalist=data.SstSbcTeam!!.get(0)
            }
        }catch (e:java.lang.Exception){
            AppLogger.log("TowerInfoFrag error :${e.localizedMessage}")
        }
    }



    var list : ArrayList<String> = ArrayList()
    var currentOpened = -1
    var type1 = "Team"
    var type2 = "Attachments"

    init {
        list.add("Team")
        list.add("Attachments")
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)
    
    class ViewHold1(itemView: View) : ViewHold(itemView) {
        var binding : SstSbcTeamItemBinding = SstSbcTeamItemBinding.bind(itemView)

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
    class ViewHold2(itemView: View, listener: SstSbcTeamListListener) : ViewHold(itemView) {
        var binding: TowerAttachmentInfoBinding = TowerAttachmentInfoBinding.bind(itemView)
        val recyclerListener:RecyclerView = binding.root.findViewById(R.id.list_item)

        init {
            binding.collapsingLayout.tag = false
            if ((binding.collapsingLayout.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_black)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }

//            recyclerListener.adapter = adapter

//            itemView.findViewById<View>(R.id.attach_card).setOnClickListener {
//                listener.addAttachment()
//            }

        }
    }

    override fun getItemViewType(position: Int): Int {
        if (list[position]==type1)
            return 1
        else if (list[position]==type2)
            return 2
        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_empty,parent,false)
        when (viewType) {
            1 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.sst_sbc_team_item, parent, false)
                return ViewHold1(view)
            }
            2 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.tower_attachment_info, parent, false)
                return ViewHold2(view,listener)
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
                    holder.binding.viewLayout.visibility = View.VISIBLE
                    holder.binding.editLayout.visibility = View.GONE

                    holder.binding.imgEdit.setOnClickListener {
                        if (AppController.getInstance().isTaskEditable) {
                            holder.binding.viewLayout.visibility = View.GONE
                            holder.binding.editLayout.visibility = View.VISIBLE
                        }
                    }
                    holder.binding.cancel.setOnClickListener {
                        holder.binding.viewLayout.visibility = View.VISIBLE
                        holder.binding.editLayout.visibility = View.GONE
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
                if (datalist!=null){
                    // view mode
                    if (datalist?.Type!= null && datalist?.Type!! > 0)
                        AppPreferences.getInstance().setDropDown(holder.binding.TestType,DropDowns.SstSbcType.name,datalist?.Type.toString())
                    if (datalist?.VendorCompany?.isNotEmpty() == true)
                        AppPreferences.getInstance().setDropDown(holder.binding.VendorName,DropDowns.VendorCompany.name,datalist?.VendorCompany?.get(0).toString(),holder.binding.VendorCode)

                    holder.binding.Budget.text=datalist?.Budget
                    holder.binding.PONumber.text=datalist?.PONumber
                    holder.binding.POLineNo.text=datalist?.POLineItemNo.toString()
                    holder.binding.POAmount.text=datalist?.POAmount
                    holder.binding.VendorExecutiveName.text=datalist?.VendorExecutiveName
                    holder.binding.VendorExecutiveEmailID.text=datalist?.VendorExecutiveEmailId
                    holder.binding.VendorExecutiveNumber.text=datalist?.VendorExecutiveMobile
                    holder.binding.PODate.text=Utils.getFormatedDate(datalist?.PODate?.substring(0,10)!!,"dd-MMM-yyyy")
                    holder.binding.remarks.text=datalist?.remark

                    // edit mode
                    holder.binding.BudgetEdit.setText(datalist?.Budget)
                    holder.binding.PONumberEdit.setText(datalist?.PONumber)
                    holder.binding.POLineNoEdit.setText(datalist?.POLineItemNo.toString())
                    holder.binding.POAmountEdit.setText(datalist?.POAmount)
                    holder.binding.VendorExecutiveNameEdit.setText(datalist?.VendorExecutiveName)
                    holder.binding.VendorExecutiveEmailIDEdit.setText(datalist?.VendorExecutiveEmailId)
                    holder.binding.VendorExecutiveNumberEdit.setText(datalist?.VendorExecutiveMobile)
                    holder.binding.PODateEdit.text=Utils.getFormatedDate(datalist?.PODate,"dd-MMM-yyyy")
                    holder.binding.remarksEdit.setText(datalist?.remark)


                }
                if (datalist!=null && datalist?.Type!= null && datalist?.Type!! > 0)
                    AppPreferences.getInstance().setDropDown(holder.binding.TestTypeEdit,DropDowns.SstSbcType.name,datalist?.Type.toString())
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.TestTypeEdit,DropDowns.SstSbcType.name)

                if (datalist!=null && datalist?.VendorCompany?.isNotEmpty() == true)
                    AppPreferences.getInstance().setDropDown(holder.binding.VendorNameEdit,DropDowns.VendorCompany.name,datalist?.VendorCompany?.get(0).toString(),holder.binding.VendorCodeEdit)
                else
                    AppPreferences.getInstance().setDropDown(holder.binding.VendorNameEdit,DropDowns.VendorCompany.name,holder.binding.VendorCodeEdit)

                holder.binding.update.setOnClickListener {
                    val tempData=SstSbcTeam()
                    tempData.let {

                        it.Budget=holder.binding.BudgetEdit.text.toString()
                        it.PONumber=holder.binding.PONumberEdit.text.toString()
                        it.PODate=Utils.getFullFormatedDate(holder.binding.PODateEdit.text.toString())
                        it.POLineItemNo=holder.binding.POLineNoEdit.text.toString().toInt()
                        it.POAmount=holder.binding.POAmountEdit.text.toString()
                        it.VendorExecutiveName=holder.binding.VendorExecutiveNameEdit.text.toString()
                        it.VendorExecutiveEmailId=holder.binding.VendorExecutiveEmailIDEdit.text.toString()
                        it.VendorExecutiveMobile=holder.binding.VendorExecutiveNumberEdit.text.toString()
                        it.Type = holder.binding.TestTypeEdit.selectedValue.id.toIntOrNull()
                        it.VendorCompany = arrayListOf(holder.binding.VendorNameEdit.selectedValue.id.toInt())
                        it.remark=holder.binding.remarksEdit.text.toString()
                        if (datalist!=null){
                            it.attachment=datalist?.attachment
                            it.id=datalist?.id
                        }
                        listener.updateTeamClicked(it)
                    }

                }

                baseFragment.setDatePickerView(holder.binding.PODateEdit)
            }
            is ViewHold2 -> {
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE

                    holder.binding.root.findViewById<View>(R.id.attach_card).setOnClickListener {
                        if (datalist!=null){
                            listener.addAttachment()
                        }
                        else
                            Toast.makeText(baseFragment.requireContext(),"Firstly fill data then Add Attachment", Toast.LENGTH_SHORT).show()
                    }
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
                try {
                    if (datalist!=null){
                        holder.recyclerListener.adapter= ImageAttachmentCommonAdapter(baseFragment.requireContext(),datalist?.attachment!!,object : ImageAttachmentCommonAdapter.ItemClickListener{
                            override fun itemClicked(item : Attachments) {
                                listener.attachmentItemClicked()
                            }
                        })
                    }
                    else
                        AppLogger.log("Attachments Error")
                }catch (e:java.lang.Exception){
                    AppLogger.log("Assign Acq Team error : ${e.localizedMessage}")
                }
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



    interface SstSbcTeamListListener {
       fun attachmentItemClicked()
       fun addAttachment()
       fun updateTeamClicked(data:SstSbcTeam)
    }

}
