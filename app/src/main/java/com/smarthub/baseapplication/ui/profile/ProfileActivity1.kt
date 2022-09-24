package com.smarthub.baseapplication.ui.profile

import android.os.Bundle
import android.view.*
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.adapter.ProfileListAdapter
import com.smarthub.baseapplication.databinding.ActivityProfile1Binding
import com.smarthub.baseapplication.databinding.ActivityProfileBinding

class ProfileActivity1 : AppCompatActivity() {

    private var dataBinding : ActivityProfile1Binding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dataBinding = ActivityProfile1Binding.inflate(layoutInflater)
        setContentView(dataBinding?.root)
        initViews()
    }

    private var popupWindow: PopupWindow? = null
    private fun initViews(){
        dataBinding?.profileItemsList?.setHasFixedSize(true)
        dataBinding?.profileItemsList?.adapter = ProfileListAdapter()
        dataBinding?.imgMenu?.setOnClickListener {
            createPopWindow(it)
        }
    }

    private fun createPopWindow(view: View) {
        val layoutInflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val customView = layoutInflater.inflate(R.layout.profile_custom_menu, null)
        if (popupWindow != null && popupWindow?.isShowing == true) popupWindow?.dismiss()

        //instantiate popup window
        popupWindow = PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        popupWindow?.elevation = 10.0f
        popupWindow?.showAsDropDown(view, 100, -160)
        // Closes the popup window when touch outside.
        popupWindow?.isOutsideTouchable = true
//        popupWindow?.isFocusable = true
//        for (i in 0 until childContainer.childCount)
//            childContainer.getChildAt(i).

    }

    fun menuItemClicked(id:Int){

    }

}