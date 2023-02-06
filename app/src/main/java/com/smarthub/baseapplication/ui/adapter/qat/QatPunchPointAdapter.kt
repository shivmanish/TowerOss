package com.smarthub.baseapplication.ui.adapter.qat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.QatCheckopenItemBinding
import com.smarthub.baseapplication.databinding.QatPunchPointItemBinding
import com.smarthub.baseapplication.listeners.PunchPointListener
import com.smarthub.baseapplication.listeners.QatProfileListener
import com.smarthub.baseapplication.model.qatcheck.OpenQatDataModel
import com.smarthub.baseapplication.model.siteInfo.qat.qat_main.Checkpoint
import com.smarthub.baseapplication.model.siteInfo.qat.qat_main.Item
import com.smarthub.baseapplication.model.siteInfo.qat.qat_main.Subitem
import com.smarthub.baseapplication.utils.Utils

class QatPunchPointAdapter(var listener: PunchPointListener,var list:List<Checkpoint>) : RecyclerView.Adapter<QatPunchPointAdapter.ViewHold>() {


    class ViewHold(itemView: View,var listener: PunchPointListener) : RecyclerView.ViewHolder(itemView) {
        var binding : QatPunchPointItemBinding = QatPunchPointItemBinding.bind(itemView)
        var attachmentAdapter = QatAttachmentAdapter(listener)
        var attachmentList = itemView.findViewById<RecyclerView>(R.id.list_item)
        var tag = false
        init {

            attachmentList.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            attachmentList.adapter = attachmentAdapter

            binding.editRemark.let { Utils.collapse(it) }

//            binding.attachment.setOnClickListener {
//                attachmentAdapter.addItem("")
//            }

            binding.punchPlush.setOnClickListener {
//                attachmentAdapter.addItem("")
                listener.addPunchPoint()
            }
//            binding.punchPoint?.setOnClickListener {
////                attachmentAdapter.addItem("")
//                listener.addPunchPoint()
//            }
        }
    }

    fun expand(v: View) {
        val matchParentMeasureSpec =
            View.MeasureSpec.makeMeasureSpec((v.parent as View).width, View.MeasureSpec.EXACTLY)
        val wrapContentMeasureSpec =
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        v.measure(matchParentMeasureSpec, wrapContentMeasureSpec)
        val targetHeight = 200

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.layoutParams.height = 1
        val a: Animation = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
                v.visibility = View.VISIBLE
                v.layoutParams.height =
                    if (interpolatedTime == 1f) 250 else (targetHeight * interpolatedTime).toInt()
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.qat_punch_point_item,parent,false)
        return ViewHold(view,listener)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.binding.titleText.text = list[position].QATSubItem
        holder.binding.punchCountList.adapter = QatPunchCountAdapter(listener, list = list[position].PunchPoint)

    }

    override fun getItemCount(): Int {
        return list.size
    }
}