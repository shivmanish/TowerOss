package com.smarthub.baseapplication.ui.dialog.qat

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
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.PunchPointDialogLayoutBinding
import java.text.SimpleDateFormat
import java.util.*


class PunchPointCreateDialog(val cntx: Context) : Dialog(cntx, R.style.NewDialog) {
    private lateinit var punchPointDialogLayoutBinding: PunchPointDialogLayoutBinding
    private lateinit var mCalender: Calendar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        punchPointDialogLayoutBinding = PunchPointDialogLayoutBinding.inflate(layoutInflater)
        setContentView(punchPointDialogLayoutBinding.root)

        window!!.setBackgroundDrawableResource(android.R.color.transparent)
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(window!!.getAttributes())
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.MATCH_PARENT
        lp.gravity = Gravity.CENTER
        window!!.setAttributes(lp)
        mCalender = Calendar.getInstance()


        punchPointDialogLayoutBinding.date.setOnClickListener {
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
        punchPointDialogLayoutBinding.date.setText(dateFormat.format(mCalender.getTime()))
    }




}