package com.smarthub.baseapplication.activities

import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

open class BaseActivity : AppCompatActivity() {
    lateinit var progressDialog : ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Please Wait...")
        progressDialog.setCanceledOnTouchOutside(true)
    }

    protected open fun setDatePickerView(view : TextView) {
        view.setOnClickListener {
            openDatePicker(view)
        }
    }

    protected open fun openDatePicker(view : TextView) {
        var year = 0
        var month = 0
        var day = 0
        val calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog(
            this,
            { arg0, arg1, arg2, arg3 ->
                // arg1 = year
                // arg2 = month
                // arg3 = day
                showDate(arg1, arg2 + 1, arg3,view)
            }, year, month, day
        )
    }

    private fun showDate(year: Int, month: Int, day: Int,textView : TextView) {
        textView.text = StringBuilder().append(day).append("/").append(month).append("/").append(year)
    }


    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

    fun showLoader(){
        if (progressDialog!=null && !progressDialog.isShowing) progressDialog.show()
    }

    fun hideLoader(){
        if (progressDialog!=null && progressDialog.isShowing) progressDialog.dismiss()
    }
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount === 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }
}