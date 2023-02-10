package com.smarthub.baseapplication.ui.adapter.qat

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.QatPunchPointItemBinding
import com.smarthub.baseapplication.listeners.PunchPointListener
import com.smarthub.baseapplication.model.siteInfo.qat.SaveCheckpointData
import com.smarthub.baseapplication.model.siteInfo.qat.qat_main.Checkpoint
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils

class QatPunchPointAdapter(var listener: PunchPointListener,var list:ArrayList<Checkpoint>) : RecyclerView.Adapter<QatPunchPointAdapter.ViewHold>() {

    var savePunchPointData=SaveCheckpointData()
    class ViewHold(itemView: View,var listener: PunchPointListener) : RecyclerView.ViewHolder(itemView) {
        var binding : QatPunchPointItemBinding = QatPunchPointItemBinding.bind(itemView)
        var attachmentAdapter = QatAttachmentAdapter(listener)
        var attachmentList = itemView.findViewById<RecyclerView>(R.id.list_item)
        var tag = false
        init {
            attachmentList.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            attachmentList.adapter = attachmentAdapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.qat_punch_point_item,parent,false)
        return ViewHold(view,listener)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
//        holder.binding.punchCountList.adapter = QatPunchCountAdapter(listener, list = list[position].PunchPoint)
        try {
            var item:Checkpoint=list[position]
            holder.binding.titleText.text = list[position].CheckPoint
            holder.binding.closedPunchPoint.text=list[position].PunchPoint.size.toString()
            holder.binding.openPunchPoint.text=list[position].PunchPoint.size.toString()
            holder.binding.editRemark.text = list[position].Remark.toEditable()
            holder.binding.idTxtCircle.text = list[position].PunchPoint.size.toString()
            holder.binding.date.text = "${Utils.getFormatedDate(list[position].modified_at.substring(0,10),"dd MMM yyyy")} ; " +
                    Utils.get12hrformate(list[position].modified_at.substring(11,19)).uppercase()
            val observations = list[position].QATObservation.split(",")
            if (observations.isNotEmpty())
                holder.binding.observations.setSpinnerData(observations)

            val readings = list[position].FieldValue.split(",")
            if (readings.isNotEmpty())
                holder.binding.reading.setSpinnerData(readings)

            holder.binding.Save.setOnClickListener {
                savePunchPointData.let {
                    it.id=item.id
                    it.QATFieldType1=item.QATFieldType1
                    it.CheckPoint=item.CheckPoint
                    it.QATFieldType1Value=item.QATFieldType1Value
                }

            }
        }catch (e:java.lang.Exception){
            AppLogger.log("e : ${e.localizedMessage}")
        }
        holder.binding.addRemark.setOnClickListener {
            holder.tag = !holder.tag
            AppLogger.log("visibility:${holder.binding.editRemark.visibility}")
            if (holder.binding.editRemark.visibility == View.VISIBLE) {
                holder.binding.editRemark.visibility = View.INVISIBLE
            } else {
                holder.binding.editRemark.visibility = View.VISIBLE
            }
        }
        holder.binding.editRemark.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
//                holder.binding.editRemarks.text = holder.binding.editRemark.text.toString()
            }

        })


        holder.binding.punchPlush.setOnClickListener {
            listener.editPunchPoint(list[position],position)
        }
        holder.binding.openPunchPoint.setOnClickListener {
           listener.punchPointClicked()
        }
        holder.binding.closedPunchPoint.setOnClickListener {
            listener.closedPunchPointClicked()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

}