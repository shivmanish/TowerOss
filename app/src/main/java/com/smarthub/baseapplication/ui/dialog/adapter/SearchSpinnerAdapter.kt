package com.smarthub.baseapplication.ui.dialog.adapter

import android.content.Context
import android.widget.BaseAdapter
import android.view.LayoutInflater
import android.view.View
import com.smarthub.baseapplication.ui.dialog.model.SearchQatModel
import android.view.ViewGroup
import com.smarthub.baseapplication.R
import android.widget.TextView
import java.util.ArrayList

class SearchSpinnerAdapter(var context: Context?,var  searchQatModels: ArrayList<SearchQatModel>?) : BaseAdapter() {
    private val inflator: LayoutInflater? = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


    override fun getCount(): Int {
        return searchQatModels!!.size
    }

    override fun getItem(position: Int): Any? {
        return searchQatModels?.get(position)
    }


    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view :View
        if(convertView==null){
            view = inflator!!.inflate(R.layout.search_qat_spinner_item, null)
        }else
        {
            view = convertView
        }
        val titel = view.findViewById<View>(R.id.titel) as TextView
        val obj_count = view.findViewById<View>(R.id.obj_count) as TextView
        val chk_point = view.findViewById<View>(R.id.chk_point) as TextView
        titel.text = searchQatModels!![position].titel
        obj_count.text = searchQatModels!![position].obj_count
        chk_point.text = searchQatModels!![position].check_point
        return view
    }

}