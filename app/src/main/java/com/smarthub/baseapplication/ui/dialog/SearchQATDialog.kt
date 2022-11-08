package com.smarthub.baseapplication.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.*
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.SearchQatDialogLayoutBinding
import com.smarthub.baseapplication.ui.dialog.adapter.SearchSpinnerAdapter
import com.smarthub.baseapplication.ui.dialog.model.SearchQatModel
import java.util.*
class SearchQATDialog(val cntx: Context) : Dialog(cntx, R.style.NewDialog) {
    private lateinit var searchQATDialogLayoutBinding: SearchQatDialogLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        searchQATDialogLayoutBinding = SearchQatDialogLayoutBinding.inflate(layoutInflater)
        setContentView(searchQATDialogLayoutBinding.root)

        window!!.setBackgroundDrawableResource(android.R.color.transparent)
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(window!!.getAttributes())
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.MATCH_PARENT
        lp.gravity = Gravity.CENTER
        window!!.setAttributes(lp)
        searchQATDialogLayoutBinding.mask.setOnClickListener {
            searchQATDialogLayoutBinding.spinner.performClick()
        }
        val vto: ViewTreeObserver = searchQATDialogLayoutBinding.mask.getViewTreeObserver()
        vto.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    searchQATDialogLayoutBinding.mask.getViewTreeObserver().removeGlobalOnLayoutListener(this)
                } else {
                    searchQATDialogLayoutBinding.mask.getViewTreeObserver().removeOnGlobalLayoutListener(this)
                }
                val width: Int = searchQATDialogLayoutBinding.mask.getMeasuredWidth()
                val height: Int = searchQATDialogLayoutBinding.mask.getMeasuredHeight()
                searchQATDialogLayoutBinding.spinner.dropDownWidth = width

            }
        })
        searchQATDialogLayoutBinding.spinner.dropDownVerticalOffset = 120



        var datalist = ArrayList<SearchQatModel>()
        datalist.add(SearchQatModel("ATP Process Name 1", "10", "45"))
        datalist.add(SearchQatModel("ATP Process Name 2", "10", "76"))
        datalist.add(SearchQatModel("ATP Process Name 3", "10", "82"))
        datalist.add(SearchQatModel("ATP Process Name 4", "10", "13"))
        datalist.add(SearchQatModel("ATP Process Name 5", "10", "68"))


        var searchSpinnerAdapter = SearchSpinnerAdapter(context, datalist)
        searchQATDialogLayoutBinding.spinner.adapter = searchSpinnerAdapter
        searchQATDialogLayoutBinding.spinner.setOnItemSelectedListener(object :
            OnItemSelectedListener {
            override fun onItemSelected(arg0: AdapterView<*>?, itemView: View?, position: Int, arg3: Long) {
                searchQATDialogLayoutBinding.titel.text = datalist.get(position).titel
                searchQATDialogLayoutBinding.obj.text = datalist.get(position).obj_count
                searchQATDialogLayoutBinding.checkpoint.text = datalist.get(position).check_point
            }

            override fun onNothingSelected(arg0: AdapterView<*>?) {}
        })
    }


}