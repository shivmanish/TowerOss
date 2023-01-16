package com.smarthub.baseapplication.ui.fragments.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ProfileRolePermissionFragBinding

class UserRoleManagmentPageAdapter (var list : ArrayList<String>?) : RecyclerView.Adapter<UserRoleManagmentPageAdapter.ViewHolder>() {



    open class ViewHolder(var itemView: View): RecyclerView.ViewHolder(itemView){
        var binding: ProfileRolePermissionFragBinding = ProfileRolePermissionFragBinding.bind(itemView)
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.profile_role_permission_frag,parent,false)


        return ViewHolder(view)

    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.fileName.text=list?.get(position)
    }

    override fun getItemCount(): Int {
        return list?.size!!
    }


}