package com.smarthub.baseapplication.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.widget.AppCompatSpinner
import com.smarthub.baseapplication.model.dropdown.DropDownItem
import com.smarthub.baseapplication.ui.adapter.spinner.CustomArrayAdapter

class CustomSpinner : AppCompatSpinner {
    var data: List<DropDownItem> = ArrayList()
    var selectedValue: DropDownItem = DropDownItem("Name","1")

    constructor(context: Context) : super(context) {}
    constructor(context: Context, mode: Int) : super(context, mode) {}
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
    }

    fun init() {
        this.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                selectedValue = data[position]
                println("CustomSpinner.onItemSelected this is called " + selectedValue!!.name)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
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

    fun getPositionOfItem(item: String?): Int {
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
}