package com.smarthub.baseapplication.ui.project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AtpViewType1Binding
import com.smarthub.baseapplication.databinding.AtpViewType2Binding
import com.smarthub.baseapplication.listeners.QatListListener
import com.smarthub.baseapplication.listeners.QatProfileListener
import com.smarthub.baseapplication.model.atp.AtpCardList
import com.smarthub.baseapplication.model.atp.AtpHeaderStatus
import com.smarthub.baseapplication.model.atp.AtpHeaderTitle
import com.smarthub.baseapplication.model.atp.HeaderList

class ProjectListAdapter() : RecyclerView.Adapter<ProjectListAdapter.ProjectListViewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectListViewholder {
        val view: View
        view = LayoutInflater.from(parent.context).inflate(R.layout.project_item_list, parent, false)
        return ProjectListViewholder(view)
    }




    open class ProjectListViewholder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onBindViewHolder(holder: ProjectListViewholder, position: Int) {

    }

    override fun getItemCount(): Int {
      return 10
    }


}