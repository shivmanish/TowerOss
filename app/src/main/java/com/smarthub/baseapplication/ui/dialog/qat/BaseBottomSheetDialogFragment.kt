package com.smarthub.baseapplication.ui.dialog.qat

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.utils.AppLogger
import java.util.*

open class BaseBottomSheetDialogFragment() :BottomSheetDialogFragment() {
    var progressDialog : ProgressDialog?=null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        dialog.behavior.skipCollapsed = false
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressDialog = ProgressDialog(requireContext())
        progressDialog?.setMessage("Loading ...")
        progressDialog?.setCancelable(true)
    }

    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

    protected open fun setDatePickerView(view : TextView) {
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
        DatePickerDialog(
            requireContext(),
            { arg0, arg1, arg2, arg3 ->
                // arg1 = year
                // arg2 = month
                // arg3 = day
                showDate(arg1, arg2 + 1, arg3,view)
            }, year, month, day
        ).show()
    }

    private fun showDate(year: Int, month: Int, day: Int,textView : TextView) {
        textView.text = StringBuilder().append(year).append("-").append(String.format("%02d",month)).append("-").append(String.format("%02d",day))
    }

    fun showLoader(){
        if (progressDialog!=null && progressDialog?.isShowing == false){
            progressDialog?.show()
        }
    }

    open fun hideLoader(){
        if (progressDialog!=null && progressDialog?.isShowing == true){
            progressDialog?.hide()
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        hideLoader()
        super.onDismiss(dialog)
    }
}