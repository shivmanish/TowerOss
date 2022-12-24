package com.smarthub.baseapplication.ui.dialog

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.utils.AppLogger
import java.util.*

open class BaseBottomSheetDialogFragment(id : Int) :BottomSheetDialogFragment(id) {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        dialog.behavior.skipCollapsed = false
        return dialog
    }

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
        textView.text = StringBuilder().append(year).append("-").append(month).append("-").append(day)
    }
}