package com.smarthub.baseapplication.widgets

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatSpinner
import com.smarthub.baseapplication.ui.adapter.spinner.CustomStringAdapter

class CustomStringSpinner : AppCompatSpinner {
    var data: List<String> = ArrayList()
    var selectedValue = "D1"
    var itemSelectedListener : ItemSelectedListener?=null
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun init() {

    }
    
    fun onItemSelected(item : String){
        selectedValue = item
        itemSelectedListener?.itemSelected(item)
    }

    fun setSpinnerData(data: List<String>) {
        this.data = data
        adapter = CustomStringAdapter(context, data)
    }

    fun setSpinnerData(data: List<String>, seletedString: String?) {
        this.data = data
        adapter = CustomStringAdapter(context, data)
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
        fun itemSelected(item : String)
    }
}