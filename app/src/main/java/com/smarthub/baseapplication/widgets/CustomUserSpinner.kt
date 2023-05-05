package com.smarthub.baseapplication.widgets
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatSpinner
import com.smarthub.baseapplication.ui.adapter.spinner.CustomUserArrayAdapter
import com.smarthub.baseapplication.ui.alert.model.response.UserDataResponseItem
import com.smarthub.baseapplication.utils.AppLogger

class CustomUserSpinner : AppCompatSpinner {
    var data: List<UserDataResponseItem> = ArrayList()
    var selectedValue: UserDataResponseItem = UserDataResponseItem("","","","","","","","")
    var tempData: UserDataResponseItem = UserDataResponseItem("","","- Select","here-","","","","")
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
    fun onItemSelected(item : UserDataResponseItem){
        selectedValue = item
        itemSelectedListener?.itemSelected(item)
    }

    fun setSpinnerData(data: ArrayList<UserDataResponseItem>) {
        if (data.isEmpty())
            data.add(tempData)
        this.data = data
        adapter = CustomUserArrayAdapter(context, data)
        val listener = object : OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                onItemSelected(data[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
        onItemSelectedListener = listener
    }
    fun setSpinnerData(data: ArrayList<UserDataResponseItem>,LeadName: TextView?,executiveNumber: TextView) {
        if (data.isEmpty())
            data.add(tempData)
        this.data = data
        adapter = CustomUserArrayAdapter(context, data)
        val listener = object : OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                onItemSelected(data[position])
                if (LeadName!=null)
                    LeadName.text=data[position].managername
                executiveNumber.text=data[position].username
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
        onItemSelectedListener = listener
    }

    fun setSpinnerDataByPhonNumber(data: ArrayList<UserDataResponseItem>, seletedNumber: String?) {
        this.data = data
        adapter = CustomUserArrayAdapter(context, data)
        setSelection(getIndexByNumber(seletedNumber))
        val listener = object : OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                onItemSelected(data[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
        onItemSelectedListener = listener
    }
    fun setSpinnerDataByName(data: ArrayList<UserDataResponseItem>, selectedName: String?,LeadName: TextView?,executiveNumber: TextView) {
        if (data.isEmpty())
            data.add(tempData)
        this.data = data
        adapter = CustomUserArrayAdapter(context, data)
        setSelection(getIndexByName(selectedName))
        val listener = object : OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                onItemSelected(data[position])
                if (LeadName!=null)
                    LeadName.text=data[position].managername
                executiveNumber.text=data[position].username
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
    private fun getIndexByNumber(PhoneNumber: String?): Int {
        var index = 0
        for (i in data.indices) {
            if (data[i].username == PhoneNumber) {
                index = i
            }
        }
        return index
    }
    private fun getIndexByName(Name: String?): Int {
        var index = 0
        AppLogger.log("getIndexByName condition name :====> $Name")
        for (i in data.indices) {
            if ((data[i].First_Name +" "+data[i].Last_Name )== Name) {
                AppLogger.log("getIndexByName condition :====> ${data[i].First_Name} ${data[i].Last_Name} and name : $Name")
                index = i
            }
        }
        return index
    }

    interface ItemSelectedListener{
        fun itemSelected(item : UserDataResponseItem)
    }
}