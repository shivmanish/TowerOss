package com.smarthub.baseapplication.ui.fragments
import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.trackermodule.homepage.BaseActivity
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils
import java.lang.Exception
import java.util.*


open class BaseFragment : Fragment(){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        onViewPageSelected()
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
        AppLogger.log("function open date picker")
        val calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
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

    open fun showDate(year: Int, month: Int, day: Int,textView : TextView) {
        textView.text = Utils.getFormatedDate(StringBuilder().append(year).append("-").append(String.format("%02d",month)).append("-").append(String.format("%02d",day)).toString(),"dd-MMM-yyyy")
//        textView.text =
//            StringBuilder().append(year).append("-").append(String.format("%02d",month)).append("-").append(String.format("%02d",day))
    }

    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

    fun showLoader(){
        if (Utils.isNetworkConnected()) {
            try {
                (requireActivity() as BaseActivity).showLoader()
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

   open fun hideLoader(){
       try {
           (requireActivity() as BaseActivity).hideLoader()
       }catch (e:Exception){
           e.printStackTrace()
       }

    }

    open fun onViewPageSelected(){

    }

    override fun onStop() {
        super.onStop()
        hideLoader()
    }
}