package com.smarthub.baseapplication.ui.fragments.powerConnection

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smarthub.baseapplication.R
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class PowerConnAdapter : Adapter<PowerConnAdapter.ViewHold>() {

    class ViewHold(view: View) : ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.power_connection_list_item,parent,false)
        return ViewHold(view)
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {

    }

    override fun getItemCount(): Int {
        return 5
    }
}