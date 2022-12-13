package com.smarthub.baseapplication.ui.fragments.project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.model.project.ProjectModelDataItem

class ProjectListAdapter() : RecyclerView.Adapter<ProjectListAdapter.ViewHold>() {

    var list : ArrayList<ProjectModelDataItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.project_item_list, parent, false)
        return ViewHold(view)
    }

    fun updateList(list : ArrayList<ProjectModelDataItem>){
        this.list = list
        notifyDataSetChanged()
    }

    class ViewHold(itemView: View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onBindViewHolder(holder: ViewHold, position: Int) {

    }

    override fun getItemCount(): Int {
      return list.size
    }


}