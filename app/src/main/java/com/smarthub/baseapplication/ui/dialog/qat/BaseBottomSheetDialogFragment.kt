package com.smarthub.baseapplication.ui.dialog.qat

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.example.trackermodule.homepage.BaseActivity
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils
import java.util.*

open class BaseBottomSheetDialogFragment() :BottomSheetDialogFragment() {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        dialog.behavior.skipCollapsed = false
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

    protected open fun setDatePickerView(view : TextView) {
        AppLogger.log("setDatePickerView : view")
        view.setOnClickListener {
            AppLogger.log("setDatePickerView : clicked")
            openDatePicker(view)
        }
    }
    protected open fun setMonthYearView(view : TextView) {
        AppLogger.log("setDatePickerView : view")
        view.setOnClickListener {
            AppLogger.log("setDatePickerView : clicked")
            openDatePicker(view)
        }
    }

    protected open fun openDatePicker(view : TextView) {
        var year = 0
        var month = 0
        var day = 0
        val calendar = Calendar.getInstance()
        year = calendar.get(Calendar.YEAR)

        month = calendar.get(Calendar.MONTH)
        day = calendar.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(requireContext(), { arg0, arg1, arg2, arg3 ->
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

    protected open fun openMonthYearPicker(view : TextView) {
        var year = 0
        var month = 0
        var day = 0
        val calendar = Calendar.getInstance()
        year = calendar.get(Calendar.YEAR)

        month = calendar.get(Calendar.MONTH)
        day = calendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(
            requireContext(),
            { arg0, arg1, arg2, arg3 ->
                // arg1 = year
                // arg2 = month
                // arg3 = day
                monthYear(arg1, arg2 + 1, arg3,view)
            }, year, month, day
        ).show()
    }

    private fun showDate(year: Int, month: Int, day: Int,textView : TextView) {
        textView.text = Utils.getFormatedDate(StringBuilder().append(year).append("-").append(String.format("%02d",month)).append("-").append(String.format("%02d",day)).toString(),"dd-MMM-yyyy")
        textView.tag = Utils.getFormatedDate(StringBuilder().append(year).append("-").append(String.format("%02d",month)).append("-").append(String.format("%02d",day)).toString(),"yyyy-MM-dd")
    }
    private fun monthYear(year: Int, month: Int, day: Int,textView : TextView) {
        textView.text = Utils.getFormatedDate(StringBuilder().append(year).append("-").append(String.format("%02d",month)).append("-").append(String.format("%02d",day)).toString(),"MMM-yyyy")
        textView.tag = Utils.getFormatedDate(StringBuilder().append(year).append("-").append(String.format("%02d",month)).append("-").append(String.format("%02d",day)).toString(),"yyyy-MM-dd")
    }

    fun showLoader(){
        if (Utils.isNetworkConnected()) {
            try {
                (requireActivity() as BaseActivity).showLoader()
            }catch (e:Exception){
//                AppLogger.log("showLoader error:"+e.localizedMessage)
            }
        }
    }

    open fun hideLoader(){
        try {
            (requireActivity() as BaseActivity).hideLoader()
        }catch (e:Exception){
            AppLogger.log("hideLoader error:"+e.localizedMessage)
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        hideLoader()
        super.onDismiss(dialog)
    }
}