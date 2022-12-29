package com.smarthub.baseapplication.ui.fragments

import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import org.w3c.dom.Text
import java.util.*


open class BaseFragment : Fragment(){

    var progressDialog : ProgressDialog?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressDialog = ProgressDialog(requireContext())
        progressDialog?.setMessage("Loading ...")
        progressDialog?.setCancelable(true)
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
            requireContext(),
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
        if (progressDialog!=null && progressDialog?.isShowing == false){
            progressDialog?.show()
        }
    }

    fun hideLoader(){
        if (progressDialog!=null && progressDialog?.isShowing == true){
            progressDialog?.hide()
        }
    }

    open fun onViewPageSelected(){

    }

    override fun onStop() {
        super.onStop()
        hideLoader()
    }
}