package com.smarthub.baseapplication.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.smarthub.baseapplication.R

class GridItemAdapter(var list : ArrayList<String>) : RecyclerView.Adapter<GridItemAdapter.ViewHold>() {

    fun addItem(item:String){
        list.add(item)
        notifyItemInserted(list.size.minus(1))
    }

    class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.grid_image_view,parent,false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        list[position].apply {
            Glide.with(holder.imageView.context).asBitmap()
                .load(this)
                .into(holder.imageView)

        }
        Log.d("status", list[position])
    }

    override fun getItemCount(): Int {
        Log.d("list size", list.size.toString())
        return list.size
    }
}