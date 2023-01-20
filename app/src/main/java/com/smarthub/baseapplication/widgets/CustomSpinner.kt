package com.smarthub.baseapplication.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatSpinner
import com.smarthub.baseapplication.model.dropdown.DropDownItem
import com.smarthub.baseapplication.ui.adapter.spinner.CustomArrayAdapter
import com.smarthub.baseapplication.utils.AppLogger

class CustomSpinner : AppCompatSpinner {
    var data: List<DropDownItem> = ArrayList()
    var selectedValue: DropDownItem = DropDownItem("Name","1")
    var itemSelectedListener : ItemSelectedListener?=null
    constructor(context: Context) : super(context)
    constructor(context: Context, mode: Int) : super(context, mode)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun setOnItemSelectionListener(listener: ItemSelectedListener){
        itemSelectedListener = listener
    }

    fun init() {

    }

    private fun onItemSelected(item : DropDownItem){
        selectedValue = item
        itemSelectedListener?.itemSelected(item)
    }

    fun setSpinnerData(data: List<DropDownItem>) {
        this.data = data
        adapter = CustomArrayAdapter(context, data)
        var listener = object : OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                onItemSelected(data[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                
            }

        }
        onItemSelectedListener = listener
    }

    fun setSpinnerData(data: List<DropDownItem>, id: String?) {
        this.data = data
        adapter = CustomArrayAdapter(context, data)
        setSelection(getIndexById(id))
        var listener = object : OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                onItemSelected(data[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
        onItemSelectedListener = listener
    }

    fun setSpinnerDataByName(data: List<DropDownItem>, name: String?) {
        this.data = data
        adapter = CustomArrayAdapter(context, data)
        setSelection(getIndexByName(name))
        var listener = object : OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                onItemSelected(data[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
        onItemSelectedListener = listener
    }

    private fun getPositionOfItem(item: String?): Int {
        return getIndex(item)
    }

    private fun getIndex(myString: String?): Int {
        var index = 0
        for (i in 0 until count) {
            if (getItemAtPosition(i) == myString) {
                index = i
            }
        }
        return index
    }

    private fun getIndexByName(name: String?): Int {
        var index = 0
        for (i in data.indices) {
            if (data[i].name == name) {
                index = i
            }
        }
        return index
    }

    private fun getIndexById(id: String?): Int {
        var index = 0
        for (i in data.indices) {
            if (data[i].id == id) {
                index = i
            }
        }
        return index
    }

    interface ItemSelectedListener{
        fun itemSelected(item : DropDownItem)
    }
}