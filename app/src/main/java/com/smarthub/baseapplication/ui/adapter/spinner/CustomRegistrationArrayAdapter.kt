package com.smarthub.baseapplication.ui.adapter.spinner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.CustomRegistrationSpinnerViewBinding
import com.smarthub.baseapplication.model.dropdown.DropDownItem

class CustomRegistrationArrayAdapter(var list: List<DropDownItem>, var listener: CustomRegistrationArrayAdapterListener) : RecyclerView.Adapter<CustomRegistrationArrayAdapter.ViewHold>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val convertView = LayoutInflater.from(parent.context).inflate(R.layout.custom_registration_spinner_view, parent, false)
        return ViewHold(convertView)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        holder.binding.textView.text = list[position].name
        holder.binding.textView.setOnClickListener {
            listener.clickedItem(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: CustomRegistrationSpinnerViewBinding

        init {
            binding = CustomRegistrationSpinnerViewBinding.bind(itemView)
        }
    }

    interface CustomRegistrationArrayAdapterListener {
        fun clickedItem(item: DropDownItem?)
    }
}