package com.smarthub.baseapplication.ui.alert.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.UserListItemListBinding
import com.smarthub.baseapplication.ui.alert.model.response.UserDataResponseItem

class UserListAdapter(var list:ArrayList<UserDataResponseItem>,var selected_array:ArrayList<Int>,var listiner:SelectCallBack):Adapter<UserListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
       return UserListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.user_list_item_list, parent, false))
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.binding.checkbox.text = list[position].First_Name+" "+list[position].Last_Name
        holder.binding.checkbox.isChecked = selected_array.contains(position)

        holder.binding.checkbox.setOnCheckedChangeListener{ compoundButton: CompoundButton, b: Boolean ->
                listiner.onSelectUser(position, b)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}
class UserListViewHolder(itemView: View) : ViewHolder(itemView) {
    var binding : UserListItemListBinding = UserListItemListBinding.bind(itemView)
}
interface SelectCallBack{
    fun onSelectUser(position: Int,isadd:Boolean)
}

