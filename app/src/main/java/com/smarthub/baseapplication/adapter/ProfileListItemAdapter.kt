package com.smarthub.baseapplication.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ProSingleItemViewBinding
import com.smarthub.baseapplication.databinding.ProfileListItemBinding

class ProfileListItemAdapter : RecyclerView.Adapter<ProfileListItemAdapter.ViewHold>() {

    var list : ArrayList<String> = ArrayList()

    init {
        list.add("Role Geograpghy")
        list.add("Address Office/Communication")
        list.add("User & Role Management")
        list.add("History")
    }

    class ViewHold(var view : View) : RecyclerView.ViewHolder(view) {
        var proSingleItemViewBinding = ProfileListItemBinding.bind(view)
        var tag = false
        init {
            proSingleItemViewBinding.geograpghy?.setOnClickListener {
                tag = !tag
                if (tag) {
                    proSingleItemViewBinding?.divider?.visibility = View.GONE
                    proSingleItemViewBinding?.geograpghy?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_arrow_up,0)
                    expand(proSingleItemViewBinding?.list!!)
                } else {
                    proSingleItemViewBinding?.divider?.visibility = View.VISIBLE
                    proSingleItemViewBinding?.geograpghy?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.arrow_farword,0)
                    collapse(proSingleItemViewBinding?.list!!)
                }
            }

            proSingleItemViewBinding?.divider?.visibility = View.VISIBLE
            proSingleItemViewBinding?.geograpghy?.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.arrow_farword,0)
            collapse(proSingleItemViewBinding?.list!!)



            var list : ArrayList<Any> = ArrayList()
            list.add("double_item")
            list.add("double_item")
            list.add("double_item")
            list.add("double_half_item")
            proSingleItemViewBinding.list.adapter = ProfileListViewItemAdapter(list)
        }

        private fun collapse(v: View) {
            val initialHeight = v.measuredHeight

            val matchParentMeasureSpec = View.MeasureSpec.makeMeasureSpec((v.parent as View).width, View.MeasureSpec.EXACTLY)
            val wrapContentMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            v.measure(matchParentMeasureSpec, wrapContentMeasureSpec)

            val a: Animation = object : Animation() {
                override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
                    if (interpolatedTime == 1f) {
                        v.visibility = View.GONE
                    } else {
                        v.layoutParams.height = initialHeight - (initialHeight * interpolatedTime).toInt()
                        v.requestLayout()
                    }
                }

                override fun willChangeBounds(): Boolean {
                    return true
                }
            }

            // Collapse speed of 1dp/ms
            a.duration = (initialHeight / v.context.resources.displayMetrics.density).toInt().toLong()
            v.startAnimation(a)
        }

        private fun expand(v: View) {
            val matchParentMeasureSpec = View.MeasureSpec.makeMeasureSpec((v.parent as View).width, View.MeasureSpec.EXACTLY)
            val wrapContentMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            v.measure(matchParentMeasureSpec, wrapContentMeasureSpec)
            val targetHeight = v.measuredHeight

            v.layoutParams.height = 1
            val a: Animation = object : Animation() {
                override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
                    v.visibility = View.VISIBLE
                    v.layoutParams.height = if (interpolatedTime == 1f) ViewGroup.LayoutParams.WRAP_CONTENT else (targetHeight * interpolatedTime).toInt()
                    v.requestLayout()
                }

                override fun willChangeBounds(): Boolean {
                    return true
                }
            }

            // Expansion speed of 1dp/ms
            a.duration = (targetHeight / v.context.resources.displayMetrics.density).toInt().toLong()
            v.startAnimation(a)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {

        var view  : View = LayoutInflater.from(parent.context).inflate(R.layout.profile_list_item,parent,false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        Log.d("status","title : ${list[position]}")
        holder.proSingleItemViewBinding.geograpghy.text = list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }
}