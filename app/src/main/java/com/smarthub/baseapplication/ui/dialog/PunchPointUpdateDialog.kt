package com.smarthub.baseapplication.ui.dialog

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.PunchPointDialogLayoutBinding
import com.smarthub.baseapplication.databinding.UpdatePunchPointDialogLayoutBinding
import java.text.SimpleDateFormat
import java.util.*


class PunchPointUpdateDialog(val cntx: Context) : Dialog(cntx, R.style.NewDialog) {
    private lateinit var updatePunchPointDialogLayoutBinding: UpdatePunchPointDialogLayoutBinding
    private lateinit var mCalender: Calendar
    private lateinit var view: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        updatePunchPointDialogLayoutBinding = UpdatePunchPointDialogLayoutBinding.inflate(layoutInflater)
        setContentView(updatePunchPointDialogLayoutBinding.root)

        window!!.setBackgroundDrawableResource(android.R.color.transparent)
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(window!!.getAttributes())
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.MATCH_PARENT
        lp.gravity = Gravity.CENTER
        window!!.setAttributes(lp)

        updatePunchPointDialogLayoutBinding.resolvedDate.setOnClickListener {
            mCalender = Calendar.getInstance()
            view = updatePunchPointDialogLayoutBinding.resolvedDate
            DatePickerDialog(
                cntx,
                listner,
                mCalender.get(Calendar.YEAR),
                mCalender.get(Calendar.MONTH),
                mCalender.get(Calendar.DAY_OF_MONTH)
            ).show()
        }


        updatePunchPointDialogLayoutBinding.date.setOnClickListener {
            mCalender = Calendar.getInstance()
            view = updatePunchPointDialogLayoutBinding.date
            DatePickerDialog(
                cntx,
                listner,
                mCalender.get(Calendar.YEAR),
                mCalender.get(Calendar.MONTH),
                mCalender.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    var listner =
        OnDateSetListener { view, year, month, day ->
            mCalender.set(Calendar.YEAR, year)
            mCalender.set(Calendar.MONTH, month)
            mCalender.set(Calendar.DAY_OF_MONTH, day)
            updateLabel()
        }

    private fun updateLabel() {
        val myFormat = "dd-MMM-yyyy"
        val dateFormat = SimpleDateFormat(myFormat, Locale.US)
        view.setText(dateFormat.format(mCalender.getTime()))
    }




}