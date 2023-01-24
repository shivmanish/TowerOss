package com.smarthub.baseapplication.widgets

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatSpinner
import com.smarthub.baseapplication.model.search.*
import com.smarthub.baseapplication.ui.adapter.spinner.SearchArrayAdapter
import com.smarthub.baseapplication.ui.fragments.search.SearchResultAdapter

class SearchResultSpinner : AppCompatSpinner, SearchResultAdapter.SearchResultListener {
    var data: List<Any> = ArrayList()
    var selectedValue: SearchListItem = SearchListItem("SiteId","1")
    var itemSelectedListener : SearchSelectionListener?=null
    constructor(context: Context) : super(context)
    constructor(context: Context, mode: Int) : super(context, mode)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun init() {

    }

    fun setSpinnerData(data: List<Any>) {
        this.data = data
        adapter = SearchArrayAdapter(context, ArrayList(data),this@SearchResultSpinner)
        performClick()
    }

    fun setSpinnerData(data: List<Any>, seletedString: String?) {
        this.data = data
        adapter = SearchArrayAdapter(context, ArrayList(data),this@SearchResultSpinner)
        setSelection(getPositionOfItem(seletedString))
        performClick()
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

    interface SearchSelectionListener{
        fun itemSelected(item : SearchListItem)
    }

    override fun onSearchItemSelected(item: Any?) {
        if (item!=null && item is SearchListItem){
            this.selectedValue = SearchListItem(item.name,item.id)
        }
        else if (item!=null && item is SearchSiteIdItem){
            this.selectedValue = SearchListItem(item.siteID,item.id)
        }
        else if (item!=null && item is SearchSiteNameItem){
            this.selectedValue = SearchListItem(item.siteName,item.id)
        }
        else if (item!=null && item is SearchAliasNameItem){
            this.selectedValue = SearchListItem(item.aliasName,item.id)
        }
        else if (item!=null && item is SearchSiteOpcoName){
            this.selectedValue = SearchListItem(item.OpcoName,item.id)
        }
        else if (item!=null && item is SearchSiteOpcoSiteId){
            this.selectedValue = SearchListItem(item.OpcoSiteID,item.id)
        }
        itemSelectedListener?.itemSelected(selectedValue)
    }
}