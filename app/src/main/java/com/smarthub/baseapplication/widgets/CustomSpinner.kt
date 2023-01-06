package com.smarthub.baseapplication.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.AdapterView
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

//    fun setOnItemSelectionListener(listener: ItemSelectedListener){
//        itemSelectedListener = listener
//    }
    fun init() {

    }

//    fun selectItemByIndex(id: String){
//        for (i in 0 until count) {
//            if (data[i].id == id) {
//                onItemSelected(data[i])
//                return
//            }
//        }
//    }

    fun onItemSelected(item : DropDownItem){
        selectedValue = item
        itemSelectedListener?.itemSelected(item)
    }

    fun setSpinnerData(data: List<DropDownItem>) {
        this.data = data
        adapter = CustomArrayAdapter(context, data)
    }

    fun setSpinnerData(data: List<DropDownItem>, seletedString: String?) {
        this.data = data
        adapter = CustomArrayAdapter(context, data)
        setSelection(getPositionOfItem(seletedString))
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

    interface ItemSelectedListener{
        fun itemSelected(item : DropDownItem)
    }
}