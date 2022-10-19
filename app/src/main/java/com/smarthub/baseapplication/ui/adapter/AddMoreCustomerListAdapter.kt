package com.smarthub.baseapplication.ui.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R

class AddMoreCustomerListAdapter( var list : ArrayList<Any> ) : RecyclerView.Adapter<AddMoreCustomerListAdapter.ViewHold>() {

    fun addItem(item : Any){
        list.add(item)
        notifyDataSetChanged()
    }
    class ViewHold(var view : View) : RecyclerView.ViewHolder(view) {
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view  : View = LayoutInflater.from(parent.context).inflate(R.layout.rv_add_more_customer,parent,false)

        return ViewHold(
            view
        )
    }
    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        //btn_next
    }
    override fun getItemCount(): Int {
        return list.size
    }
}