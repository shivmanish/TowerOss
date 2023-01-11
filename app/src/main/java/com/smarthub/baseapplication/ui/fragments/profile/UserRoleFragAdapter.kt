package com.smarthub.baseapplication.ui.fragments.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ProfileHistoryBinding


class UserRoleFragAdapter() : RecyclerView.Adapter<UserRoleFragAdapter.ViewHolder>() {
    var list : ArrayList<String> = ArrayList()
    var type1="Roles"
    var type2="Permissions"


    init {
        list.add("Roles")
        list.add("Permissions")

    }

    open class ViewHolder(var itemView: View):RecyclerView.ViewHolder(itemView)


    class UserRoleViewHolder(itemView: View ) : ViewHolder(itemView) {
       var binding:ProfileHistoryBinding = ProfileHistoryBinding.bind(itemView)


    }
    class UserPermissionViewHolder(itemView: View ) : ViewHolder(itemView) {
        var binding:ProfileHistoryBinding = ProfileHistoryBinding.bind(itemView)


    }
    override fun getItemViewType(position: Int): Int {
        if (list[position] is String && list[position]==type1)
            return 1
        else if (list[position] is String && list[position]==type2)
            return 2

        return 0
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_empty,parent,false)
        when (viewType) {
             1->{
                view = LayoutInflater.from(parent.context).inflate(R.layout.profile_history,parent,false)
                return UserRoleViewHolder(view)
            }
            2->{
                view = LayoutInflater.from(parent.context).inflate(R.layout.profile_history,parent,false)
                return UserPermissionViewHolder(view)
            }


        }
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when(holder){
            is UserRoleViewHolder ->{
                holder.binding.titelText.text="Roles"
                holder.binding.list.adapter=UserRoleManagmentPageAdapter()
            }
            is UserPermissionViewHolder ->{
                holder.binding.titelText.text="Permission"
                holder.binding.list.adapter=UserRoleManagmentPageAdapter()
            }

        }

    }

    override fun getItemCount(): Int {
        return list.size
    }


}