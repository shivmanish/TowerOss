package com.smarthub.baseapplication.ui.alert.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.*
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.ui.alert.viewmodel.AlertViewModel
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.utils.SendAlertDropDowns
import com.smarthub.baseapplication.widgets.CustomStringSpinner

class AlertStatusListAdapter(var baseFragment: BaseFragment, var listener: AlertStatusListener,var type : String) : RecyclerView.Adapter<AlertStatusListAdapter.ViewHold>() {
    var count: Int = 0
    var list: ArrayList<Any> = ArrayList()
    var currentOpened = 0
    var type1 = "What?*"
    var type2 = "When?"
    var type3 = "Where?"
    var type4 = "Who?"
    var type5 = "How?"

    init {
        list.add("What?*")
        list.add("When?")
        list.add("Where?")
        list.add("Who?")
        list.add("How?")
    }

    open class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun setusercount(countt: Int) {
        count = countt
        notifyItemChanged((list.size)-1)
    }

    override fun getItemViewType(position: Int): Int {
        if (list[position] is String && list[position] == type1)
            return 1
        else if (list[position] is String && list[position] == type2)
            return 2
        else if (list[position] is String && list[position] == type3)
            return 3
        else if (list[position] is String && list[position] == type4)
            return 4
        else if (list[position] is String && list[position] == type5)
            return 5
        /*   else if (list[position] is String && list[position]==type6)
               return 6*/
        return 0
    }

    class ViewHold1(itemView: View) : ViewHold(itemView) {
        var binding: AlertWhatLayoutBinding = AlertWhatLayoutBinding.bind(itemView)


        init {
            binding.itemTitle.tag = false
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.down_arrow)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }
        }
    }

    class ViewHold2(itemView: View) : ViewHold(itemView) {
        var binding: AlertWhenLayoutBinding = AlertWhenLayoutBinding.bind(itemView)

        init {
            binding.itemTitle.tag = false
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.down_arrow)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }

        }
    }

    class ViewHold3(itemView: View) : ViewHold(itemView) {
        var binding: AlertWhereLayoutBinding = AlertWhereLayoutBinding.bind(itemView)

        init {
            binding.itemTitle.tag = false
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.down_arrow)
            }
        }
    }

    class ViewHold4(itemView: View) : ViewHold(itemView) {
        var binding: AlertWhoLayoutBinding = AlertWhoLayoutBinding.bind(itemView)

        init {
            binding.itemTitle.tag = false
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.down_arrow)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }
        }
    }

    class ViewHold5(itemView: View) : ViewHold(itemView) {
        var binding: AlertHowLayoutBinding = AlertHowLayoutBinding.bind(itemView)


        init {
            binding.itemTitle.tag = false
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.down_arrow)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }


        }
    }

    class ViewHold6(itemView: View) : ViewHold(itemView) {
        var binding: AlertOthersLayoutBinding = AlertOthersLayoutBinding.bind(itemView)

        init {
            binding.itemTitle.tag = false
            binding.itemTitle.tag = false
            if ((binding.itemTitle.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
            } else {
                binding.imgDropdown.setImageResource(R.drawable.down_arrow)
                binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_empty, parent, false)
        when (viewType) {
            1 -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.alert_what_layout, parent, false)
                return ViewHold1(view)
            }
            2 -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.alert_when_layout, parent, false)
                return ViewHold2(view)
            }
            3 -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.alert_where_layout, parent, false)
                return ViewHold3(view)
            }
            4 -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.alert_who_layout, parent, false)
                return ViewHold4(view)
            }
            5 -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.alert_how_layout, parent, false)
                return ViewHold5(view)
            }

            6 -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.alert_others_layout, parent, false)
                return ViewHold6(view)
            }

        }
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        when (holder) {
            is ViewHold1 -> {
                holder.binding.imgEdit.setOnClickListener {
//                    listener.detailsItemClicked()
                }
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                    holder.binding.iconLayout.visibility = View.VISIBLE
                } else {
                    holder.binding.itemTitle.tag = false
                    holder.binding.imgDropdown.setImageResource(R.drawable.down_arrow)
                    holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    holder.binding.itemLine.visibility = View.VISIBLE
                    holder.binding.itemCollapse.visibility = View.GONE
                    holder.binding.iconLayout.visibility = View.GONE
                }
                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
                holder.binding.itemTitle.text = list[position] as String

                AppPreferences.getInstance().setDropDownByName(holder.binding.spinIssueType, SendAlertDropDowns.SAIssueType.name,type)
                AppPreferences.getInstance().setDropDown(holder.binding.spinSeverity, SendAlertDropDowns.SASeverity.name)


            }
            is ViewHold2 -> {
                baseFragment.setDatePickerView(holder.binding.date)
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                    holder.binding.iconLayout.visibility = View.VISIBLE
                } else {
                    holder.binding.itemTitle.tag = false
                    holder.binding.imgDropdown.setImageResource(R.drawable.down_arrow)
                    holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    holder.binding.itemLine.visibility = View.VISIBLE
                    holder.binding.itemCollapse.visibility = View.GONE
                    holder.binding.iconLayout.visibility = View.GONE
                }
                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
                holder.binding.itemTitle.text = list[position] as String
                holder.binding.status.setOnCheckedChangeListener { group, checkedId ->
                    val radioButton = holder.itemView.findViewById(checkedId) as RadioButton
                    Toast.makeText(baseFragment.requireContext(), radioButton.text, Toast.LENGTH_SHORT).show();
                }


            }
            is ViewHold3 -> {
                listener.setSearchEditBoxAdapter(holder.binding.searchBox)
//                listener.setSearchEditProgress(holder.binding.loadingProgress)
//                if (currentOpened == position) {
//                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
//                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
//                    holder.binding.itemLine.visibility = View.GONE
//                    holder.binding.itemCollapse.visibility = View.VISIBLE
//                    holder.binding.iconLayout.visibility = View.VISIBLE
//                }
//                else {
//                    holder.binding.itemTitle.tag = false
//                    holder.binding.imgDropdown.setImageResource(R.drawable.down_arrow)
//                    holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
//                    holder.binding.itemLine.visibility = View.VISIBLE
//                    holder.binding.itemCollapse.visibility = View.GONE
//                    holder.binding.iconLayout.visibility = View.GONE
//                }
//                holder.binding.collapsingLayout.setOnClickListener {
//                    updateList(position)
//                }
                holder.binding.itemTitle.text = list[position] as String
                AppPreferences.getInstance().setDropDown(holder.binding.spinSelectCategory, SendAlertDropDowns.SACategory.name)
            }
            is ViewHold4 -> {
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                    holder.binding.iconLayout.visibility = View.VISIBLE
                } else {
                    holder.binding.itemTitle.tag = false
                    holder.binding.imgDropdown.setImageResource(R.drawable.down_arrow)
                    holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    holder.binding.itemLine.visibility = View.VISIBLE
                    holder.binding.itemCollapse.visibility = View.GONE
                    holder.binding.iconLayout.visibility = View.GONE
                }
                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
                holder.binding.itemTitle.text = list[position] as String
                AppPreferences.getInstance().setDropDown(
                    holder.binding.spinTroublemaker,
                    SendAlertDropDowns.SATroubleMaker.name
                )
            }
            is ViewHold5 -> {
                /* if (currentOpened == position) {
                     holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                     holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                     holder.binding.itemLine.visibility = View.GONE
                     holder.binding.itemCollapse.visibility = View.VISIBLE
                     holder.binding.iconLayout.visibility = View.VISIBLE
                 }
                 else {
                     holder.binding.itemTitle.tag = false
                     holder.binding.imgDropdown.setImageResource(R.drawable.down_arrow)
                     holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                     holder.binding.itemLine.visibility = View.VISIBLE
                     holder.binding.itemCollapse.visibility = View.GONE
                     holder.binding.iconLayout.visibility = View.GONE
                 }*/
                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
                if (count > 0) {
                    holder.binding.spinRecipients.text = "Recipients - " + count
                } else {
                    holder.binding.spinRecipients.text = "Recipients - " + 0
                }
                holder.binding.itemTitle.text = list[position] as String

                val adapter = AlertImageAdapter(object : ImageAttachmentAdapter.ItemClickListener {
                    override fun itemClicked() {
                        //  listener.attachmentItemClicked()
                    }
                })
                holder.binding.rvAlertImageList.adapter = adapter
                adapter.addItem()
                holder.binding.sendalert.setOnClickListener {
                    listener.sendAlertData()
                }

                holder.binding.spinRecipients.setOnClickListener {
                    listener.getuser()
                }
//                AppPreferences.getInstance()
//                    .setDropDown(holder.binding.spinDepartment, SendAlertDropDowns.SAMedium.name)
//                AppPreferences.getInstance()
//                    .setDropDown(holder.binding.spinDepartment, SendAlertDropDowns.SARecepient.name)

                listener.setCustomArrayAdapter(holder.binding.spinrecipientdepartmanet)
                listener.setCustomArrayAdapter1(holder.binding.spinDepartment)
            }
            is ViewHold6 -> {
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_up)
                    holder.binding.titleLayout.setBackgroundResource(R.drawable.bg_expansion_bar)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE
                    holder.binding.iconLayout.visibility = View.VISIBLE
                } else {
                    holder.binding.itemTitle.tag = false
                    holder.binding.imgDropdown.setImageResource(R.drawable.down_arrow)
                    holder.binding.titleLayout.setBackgroundResource(R.color.collapse_card_bg)
                    holder.binding.itemLine.visibility = View.VISIBLE
                    holder.binding.itemCollapse.visibility = View.GONE
                    holder.binding.iconLayout.visibility = View.GONE
                }
                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
                holder.binding.itemTitle.text = list[position] as String


            }
        }
    }

    fun updateList(position: Int) {
        currentOpened = if (currentOpened == position) -1 else position
        notifyDataSetChanged()
        if (this.recyclerView != null)
            this.recyclerView?.scrollToPosition(position)
    }

    var recyclerView: RecyclerView? = null
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView
        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

interface AlertStatusListener {
    fun sendAlertData()
    fun getuser()
    fun setSearchEditBoxAdapter(customStringSpinner: TextView)
    fun setSearchEditProgress(customStringSpinner: ProgressBar)
    fun setCustomArrayAdapter(customStringSpinner: CustomStringSpinner)
    fun setCustomArrayAdapter1(customStringSpinner: CustomStringSpinner)

}
