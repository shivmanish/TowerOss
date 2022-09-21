package com.smarthub.baseapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.databinding.LangItemBinding
import com.smarthub.baseapplication.model.LangModel

class LanguageListAdapter(var list : ArrayList<LangModel>) : RecyclerView.Adapter<LanguageListAdapter.ViewHold>() {


    class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding : LangItemBinding = LangItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.lang_item,parent,false)
        return ViewHold(view)
    }

    fun setUpForEnabled(holder: ViewHold, position: Int){
        holder.binding.button.isChecked = list[position].checked
        if (holder.binding.button.isChecked){
            holder.binding.button.text = list[position].name
            holder.binding.itemOverlay.visibility = View.VISIBLE
            holder.binding.imgIcon.setImageResource(list[position].img_url_enable)
        }else{
            holder.binding.button.text = list[position].name
            holder.binding.itemOverlay.visibility = View.GONE
            holder.binding.imgIcon.setImageResource(list[position].img_url)
        }
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        setUpForEnabled(holder,position)
        holder.binding.button.setOnClickListener {
            list[position].checked = !list[position].checked
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}