package com.example.trackermodule.homepage

import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

open class BaseActivity : AppCompatActivity() {
    lateinit var progressDialog : ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Please Wait...")
        progressDialog.setCanceledOnTouchOutside(true)
    }

    open fun setDatePickerView(view : TextView) {
        view.setOnClickListener {
            openDatePicker(view)
        }
    }

    private fun openDatePicker(view : TextView) {
        var year = 0
        var month = 0
        var day = 0
        val calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        val datePickerDialog = DatePickerDialog(this, { arg0, arg1, arg2, arg3 ->
                // arg1 = year
                // arg2 = month
                // arg3 = day
                showDate(arg1, arg2 + 1, arg3,view)
            }, year, month, day
        )
        datePickerDialog.setButton(DatePickerDialog.BUTTON_NEGATIVE,"clear") { dialog, which ->
            view.text = ""
        }
        datePickerDialog.setButton(DatePickerDialog.BUTTON_NEUTRAL,"cancel") { dialog, which ->
            datePickerDialog.dismiss()
        }
        datePickerDialog.show()
    }
    fun getFormatedDate(d : String?,format:String) : String{
        var date: String? ="2023-03-08"
        if (d!=null && d.length>=10){
            date=d.substring(0,10)
        }else{
            return ""
        }

//        AppLogger.log("getformatedDate:$date")
        try {
            val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
            val outputFormat: DateFormat = SimpleDateFormat(format)
            val inputDateStr = date
            val inputDate: Date = inputFormat.parse(inputDateStr)
            date = outputFormat.format(inputDate)
        }catch (e:java.lang.Exception){
//            AppLogger.log("compareDate error :${e.localizedMessage}")
        }
        return date!!
    }

    fun isNetworkConnected(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        val isConnected = activeNetwork != null && activeNetwork.isConnected
        return if (isConnected) {
            true
        } else {
            Log.d("Network", "Not Connected")
            false
        }
    }

    private fun showDate(year: Int, month: Int, day: Int,textView : TextView) {
        textView.text = getFormatedDate(StringBuilder().append(year).append("-").append(String.format("%02d",month)).append("-").append(String.format("%02d",day)).toString(),"dd-MMM-yyyy")
//        textView.text =
//            StringBuilder().append(year).append("-").append(String.format("%02d",month)).append("-").append(String.format("%02d",day))
    }


    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

    fun showLoader(){
        if (isNetworkConnected()) {
            if (!progressDialog.isShowing) progressDialog.show()
        }
    }

    fun hideLoader(){
        if (progressDialog.isShowing) progressDialog.dismiss()
    }
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount === 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }
}