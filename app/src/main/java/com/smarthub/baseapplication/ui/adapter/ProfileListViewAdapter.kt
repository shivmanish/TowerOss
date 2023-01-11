package com.smarthub.baseapplication.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ProfileUserRoleManagmentBinding

class ProfileListViewAdapter(var manager: FragmentManager) : RecyclerView.Adapter<ProfileListViewAdapter.ViewHolder>() {
    var list : ArrayList<String> = ArrayList()
    var type1="Header"
    var type2="Role Geograpghy"
    var type3="Address Office/Communication"
    var type4="User & Role Management"
    var type5="History"

    init {
        list.add("Header")
        list.add("Role Geograpghy")
        list.add("Address Office/Communication")
        list.add("User & Role Management")
        list.add("History")
    }

    open class ViewHolder(var itemView: View):RecyclerView.ViewHolder(itemView)

//    class HearderViewHolder(itemView: View):ViewHolder(itemView){
//        val binding :ProfileOfficialDetailsHeaderBinding=ProfileOfficialDetailsHeaderBinding.bind(itemView)
//    }
//    class RoleGeoViewHolder(itemView: View):ViewHolder(itemView){
//        val binding :ProfileRoleGeographiBinding=ProfileRoleGeographiBinding.bind(itemView)
//        init {
//            binding.collapsingLayout.tag = false
//            if ((binding.collapsingLayout.tag as Boolean)) {
//                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_faq)
//
//            } else {
//                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_faq)
//
//            }
//
//        }
//    }
//    class AddressGeoViewHolder(itemView: View):ViewHolder(itemView){
//        val binding :ProfileOfficeComunicationAddressBinding=ProfileOfficeComunicationAddressBinding.bind(itemView)
//        init {
//            binding.collapsingLayout.tag = false
//            if ((binding.collapsingLayout.tag as Boolean)) {
//                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_faq)
//
//            } else {
//                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_faq)
//
//            }
//
//        }
//    }
//    class HistoryViewHolder(itemView: View):ViewHolder(itemView){
//        val binding :ProfileHistoryBinding=ProfileHistoryBinding.bind(itemView)
//        init {
//            binding.collapsingLayout.tag = false
//            if ((binding.collapsingLayout.tag as Boolean)) {
//                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_faq)
//
//            } else {
//                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_faq)
//
//            }
//
//        }
//    }

    class UserRoleViewHolder(itemView: View ) : ViewHolder(itemView) {
       var binding:ProfileUserRoleManagmentBinding = ProfileUserRoleManagmentBinding.bind(itemView)
        init {
            binding.collapsingLayout.tag = false
            if ((binding.collapsingLayout.tag as Boolean)) {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_faq)

            } else {
                binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_faq)

            }

        }
//        var tag = false
//        init {
//            proSingleItemViewBinding.geograpghy?.setOnClickListener {
//                tag = !tag
//                if (tag) {
//                    proSingleItemViewBinding?.divider?.visibility = View.GONE
//                    proSingleItemViewBinding?.geograpghy?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_arrow_up,0)
//                    expand(proSingleItemViewBinding?.list!!)
//                } else {
//                    proSingleItemViewBinding?.divider?.visibility = View.VISIBLE
//                    proSingleItemViewBinding?.geograpghy?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.arrow_farword,0)
//                    collapse(proSingleItemViewBinding?.list!!)
//                }
//            }
//
//            proSingleItemViewBinding?.divider?.visibility = View.VISIBLE
//            proSingleItemViewBinding?.geograpghy?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.arrow_farword,0)
//            collapse(proSingleItemViewBinding?.list!!)
//
//
//
//            var list : ArrayList<Any> = ArrayList()
//            list.add("single_item")
//            list.add("double_item")
//            list.add("default")
//            list.add("double_half_item")
//            proSingleItemViewBinding.list.adapter = ProfileListViewItemAdapter(list)
//        }
//
//         private fun collapse(v: View) {
//             val initialHeight = v.measuredHeight
//
//             val matchParentMeasureSpec =
//                 View.MeasureSpec.makeMeasureSpec((v.parent as View).width, View.MeasureSpec.EXACTLY)
//             val wrapContentMeasureSpec =
//                 View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
//             v.measure(matchParentMeasureSpec, wrapContentMeasureSpec)
////        val targetHeight = v.measuredHeight
//             val targetHeight = 0
//
//             val a: Animation = object : Animation() {
//                 override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
////                v.layoutParams.height =
////                    if (interpolatedTime == 1f) ViewGroup.LayoutParams.WRAP_CONTENT else (targetHeight * interpolatedTime).toInt()
////                v.requestLayout()
//                     if (interpolatedTime == 1f) {
//                         v.visibility = View.GONE
//                     } else {
//                         v.layoutParams.height =
//                             initialHeight - (initialHeight * interpolatedTime).toInt()
//                         v.requestLayout()
//                     }
//                 }
//
//                 override fun willChangeBounds(): Boolean {
//                     return true
//                 }
//             }
//
//             // Collapse speed of 1dp/ms
//             a.duration = (initialHeight / v.context.resources.displayMetrics.density).toInt().toLong()
//             v.startAnimation(a)
//         }
//
//         private fun expand(v: View) {
//             val matchParentMeasureSpec =
//                 View.MeasureSpec.makeMeasureSpec((v.parent as View).width, View.MeasureSpec.EXACTLY)
//             val wrapContentMeasureSpec =
//                 View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
//             v.measure(matchParentMeasureSpec, wrapContentMeasureSpec)
//             val targetHeight = v.measuredHeight
//
//             // Older versions of android (pre API 21) cancel animations for views with a height of 0.
//             v.layoutParams.height = 1
//             val a: Animation = object : Animation() {
//                 override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
//                     v.visibility = View.VISIBLE
//                     v.layoutParams.height =
//                         if (interpolatedTime == 1f) ViewGroup.LayoutParams.WRAP_CONTENT else (targetHeight * interpolatedTime).toInt()
//                     v.requestLayout()
//                 }
//
//                 override fun willChangeBounds(): Boolean {
//                     return true
//                 }
//             }
//
//             // Expansion speed of 1dp/ms
//             a.duration = (targetHeight / v.context.resources.displayMetrics.density).toInt().toLong()
//             v.startAnimation(a)
//         }
    }
    override fun getItemViewType(position: Int): Int {
        if (list[position] is String && list[position]==type1)
            return 1
        else if (list[position] is String && list[position]==type2)
            return 2
        else if (list[position] is String && list[position]==type3)
            return 3
        else if (list[position] is String && list[position]==type4)
            return 4
        else if (list[position] is String && list[position]==type5)
            return 5
        return 0
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_empty,parent,false)
        when (viewType) {
             1->{
                view = LayoutInflater.from(parent.context).inflate(R.layout.profile_role_permission_frag,parent,false)
                return UserRoleViewHolder(view)
            }
            2->{
                view = LayoutInflater.from(parent.context).inflate(R.layout.profile_user_role_managment,parent,false)
                return UserRoleViewHolder(view)
            }
            3->{
                view = LayoutInflater.from(parent.context).inflate(R.layout.profile_user_role_managment,parent,false)
                return UserRoleViewHolder(view)
            }
            4->{
                view = LayoutInflater.from(parent.context).inflate(R.layout.profile_user_role_managment,parent,false)
                return UserRoleViewHolder(view)
            }
            5->{
                view = LayoutInflater.from(parent.context).inflate(R.layout.profile_history,parent,false)
                return UserRoleViewHolder(view)
            }

        }
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when(holder){
            is UserRoleViewHolder ->{
                if (currentOpened == position) {
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_faq)
                    holder.binding.itemLine.visibility = View.GONE
                    holder.binding.itemCollapse.visibility = View.VISIBLE

                }
                else {
                    holder.binding.collapsingLayout.tag = false
                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_faq)
                    holder.binding.itemLine.visibility = View.VISIBLE
                    holder.binding.itemCollapse.visibility = View.GONE

                }
                holder.binding.collapsingLayout.setOnClickListener {
                    updateList(position)
                }
                holder.binding.itemTitleStr.text = list[position]

            }
//            is RoleGeoViewHolder ->{
//                if (currentOpened == position) {
//                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_faq)
//                    holder.binding.itemLine.visibility = View.GONE
//                    holder.binding.itemCollapse.visibility = View.VISIBLE
//
//                }
//                else {
//                    holder.binding.collapsingLayout.tag = false
//                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_faq)
//                    holder.binding.itemLine.visibility = View.VISIBLE
//                    holder.binding.itemCollapse.visibility = View.GONE
//
//                }
//                holder.binding.collapsingLayout.setOnClickListener {
//                    updateList(position)
//                }
//                holder.binding.itemTitleStr.text = list[position]
//            }
//            is AddressGeoViewHolder ->{
//                if (currentOpened == position) {
//                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_faq)
//                    holder.binding.itemLine.visibility = View.GONE
//                    holder.binding.itemCollapse.visibility = View.VISIBLE
//
//                }
//                else {
//                    holder.binding.collapsingLayout.tag = false
//                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_faq)
//                    holder.binding.itemLine.visibility = View.VISIBLE
//                    holder.binding.itemCollapse.visibility = View.GONE
//
//                }
//                holder.binding.collapsingLayout.setOnClickListener {
//                    updateList(position)
//                }
//                holder.binding.itemTitleStr.text = list[position]
//            }
//            is HistoryViewHolder ->{
//                if (currentOpened == position) {
//                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_faq)
//                    holder.binding.itemLine.visibility = View.GONE
//                    holder.binding.itemCollapse.visibility = View.VISIBLE
//
//                }
//                else {
//                    holder.binding.collapsingLayout.tag = false
//                    holder.binding.imgDropdown.setImageResource(R.drawable.ic_arrow_down_faq)
//                    holder.binding.itemLine.visibility = View.VISIBLE
//                    holder.binding.itemCollapse.visibility = View.GONE
//
//                }
//                holder.binding.collapsingLayout.setOnClickListener {
//                    updateList(position)
//                }
//                holder.binding.itemTitleStr.text = list[position]
//            }
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    var recyclerView: RecyclerView?=null
    var currentOpened = -1
    fun updateList(position: Int){
        currentOpened = if(currentOpened == position) -1 else position
        notifyDataSetChanged()
        if (this.recyclerView!=null)
            this.recyclerView?.scrollToPosition(position)
    }
}