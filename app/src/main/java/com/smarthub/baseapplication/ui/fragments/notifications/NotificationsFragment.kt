package com.smarthub.baseapplication.ui.fragments.notifications

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.ui.adapter.NotificationsListAdapter
import com.smarthub.baseapplication.databinding.FragmentNotificationsBinding
import java.text.DateFormatSymbols
import java.util.*

 class NotificationsFragment : Fragment() {
     private var _binding: FragmentNotificationsBinding? = null
     private lateinit var notificationViewModel: NotificationsViewModel
    private val binding get() = _binding!!
    private var layoutManager: RecyclerView.LayoutManager? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val notificationsViewModel = ViewModelProvider(this)[NotificationsViewModel::class.java]
        notificationViewModel = ViewModelProvider(requireActivity())[NotificationsViewModel::class.java]
       // notificationViewModel.isActionBarHide(false)
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }
    @SuppressLint("MissingInflatedId")
    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        var layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        _binding?.imgMenu?.setOnClickListener{
            // on below line we are creating a new bottom sheet dialog.
            val dialog = BottomSheetDialog(requireActivity())
            // on below line we are inflating a layout file which we have created.
            val view = layoutInflater.inflate(R.layout.bottom_sheet_dialog_layout, null)
            val btnClose = view.findViewById<Button>(R.id.btnReset)
            val btnFilter = view.findViewById<Button>(R.id.btnFilter)
            val close = view.findViewById<ImageView>(R.id.imgClose)
            val startDate = view.findViewById<TextView>(R.id.txtStartDate)
            val txtEndDate = view.findViewById<TextView>(R.id.txtEndDate)
            btnClose.setOnClickListener {
                // on below line we are calling a dismiss
                // method to close our dialog.
                dialog.dismiss()
            }
            startDate.setOnClickListener {
                val c: Calendar = Calendar.getInstance()
                val year: Int = c.get(Calendar.YEAR)
                val month: Int = c.get(Calendar.MONTH)
                val day: Int = c.get(Calendar.DAY_OF_MONTH)
                 val dpd = DatePickerDialog(
                    requireActivity(),
                    {
                            view, year, monthOfYear, dayOfMonth ->
                        // Display Selected date in textbox
                        // 12-Apl-2022, DD/MMM/YYYY
                        startDate.text = "" + dayOfMonth + " " + getMonthName(monthOfYear) + ", " + year
                    }, year, month, day
                )
                dpd.show()
            }
            txtEndDate.setOnClickListener {
                val c: Calendar = Calendar.getInstance()
                val year: Int = c.get(Calendar.YEAR)
                val month: Int = c.get(Calendar.MONTH)
                val day: Int = c.get(Calendar.DAY_OF_MONTH)
                 val dpd = DatePickerDialog(
                    requireActivity(),
                    {
                            view, year, monthOfYear, dayOfMonth ->
                        // Display Selected date in textbox
                        Log.d("date ","  $monthOfYear")
                        txtEndDate.text = "" + dayOfMonth + " " + getMonthName(monthOfYear) + ", " + year
                    }, year, month, day
                )
                dpd.show()
            }
            btnFilter.setOnClickListener {
                // on below line we are calling a dismiss
                // method to close our dialog.
                dialog.dismiss()
            }
            close.setOnClickListener {
                // on below line we are calling a dismiss
                // method to close our dialog.
                dialog.dismiss()
            }
            // below line is use to set cancelable to avoid
            // closing of dialog box when clicking on the screen.
            dialog.setCancelable(false)
            // on below line we are setting
            // content view to our view.
            dialog.setContentView(view)
            // on below line we are calling
            // a show method to display a dialog.
            dialog.show()
        }
        _binding?.rvNotificationList.apply {
            this?.layoutManager = layoutManager
            this?.adapter = NotificationsListAdapter()

        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

fun getMonthName(monthIndex: Int): String? {
    require(!(monthIndex < 0 || monthIndex > 11)) { "$monthIndex is not a valid month index." }
    return DateFormatSymbols().months[monthIndex].toString()
}

