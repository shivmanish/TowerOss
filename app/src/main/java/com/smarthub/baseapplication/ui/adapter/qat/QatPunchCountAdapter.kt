package com.smarthub.baseapplication.ui.adapter.qat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.PunchPointCountBinding
import com.smarthub.baseapplication.databinding.QatCheckopenItemBinding
import com.smarthub.baseapplication.databinding.QatPunchPointItemBinding
import com.smarthub.baseapplication.databinding.QatSpinnerItemViewBinding
import com.smarthub.baseapplication.listeners.PunchPointListener
import com.smarthub.baseapplication.listeners.QatProfileListener
import com.smarthub.baseapplication.model.qatcheck.OpenQatDataModel
import com.smarthub.baseapplication.model.siteInfo.qat.qat_main.Checkpoint
import com.smarthub.baseapplication.model.siteInfo.qat.qat_main.Subitem

class QatPunchCountAdapter(var listener: PunchPointListener,var list:List<Any>) : RecyclerView.Adapter<QatPunchCountAdapter.ViewHold>() {


    class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding : PunchPointCountBinding = PunchPointCountBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.punch_point_count,parent,false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.binding.root.setOnClickListener {
            listener.punchPointClicked()
        }
//        holder.binding.p1.text= list[position].
    }

    override fun getItemCount(): Int {
        return list.size
    }
}