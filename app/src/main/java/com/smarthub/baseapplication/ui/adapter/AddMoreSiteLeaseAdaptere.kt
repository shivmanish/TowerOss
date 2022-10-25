package com.smarthub.baseapplication.ui.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.ui.fragments.sitedetail.SiteLeaseF

class AddMoreSiteLeaseAdaptere(var list: ArrayList<Any>) : RecyclerView.Adapter<AddMoreSiteLeaseAdaptere.ViewHold>() {

    fun addItem(item : Any){
        list.add(item)
        notifyDataSetChanged()
    }
    class ViewHold(var view : View) : RecyclerView.ViewHolder(view) {
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        var view  : View = LayoutInflater.from(parent.context).inflate(R.layout.site_lease_tab_layout,parent,false)

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