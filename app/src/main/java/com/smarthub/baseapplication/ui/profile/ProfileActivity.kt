package com.smarthub.baseapplication.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.adapter.ProfileListAdapter
import com.smarthub.baseapplication.adapter.ProfileListViewAdapter
import com.smarthub.baseapplication.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private var dataBinding : ActivityProfileBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dataBinding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(dataBinding?.root)
        initViews()
    }

    private var popupWindow: PopupWindow? = null
    private fun initViews(){
        dataBinding?.profileItemsList?.setHasFixedSize(true)

        var list : ArrayList<Any> = ArrayList()
        list.add("single_item")
        list.add("default")
        list.add("double_item")
        list.add("double_half_item")
        dataBinding?.profileItemsList?.adapter = ProfileListViewAdapter()

        dataBinding?.imgMenu?.setOnClickListener {
            createPopWindow(it)
        }
    }

    private fun createPopWindow(view: View) {
        val layoutInflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val customView = layoutInflater.inflate(R.layout.profile_custom_menu, null)
        var childContainer : LinearLayout = customView.findViewById(R.id.child_container)
        if (popupWindow != null && popupWindow?.isShowing == true) popupWindow?.dismiss()

        //instantiate popup window
        popupWindow = PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        popupWindow?.elevation = 10.0f
        popupWindow?.showAsDropDown(view, 100, -160)
        // Closes the popup window when touch outside.
        popupWindow?.isOutsideTouchable = true
//        popupWindow?.isFocusable = true
        for (i in 0 until childContainer.childCount)
            childContainer.getChildAt(i).setOnClickListener {
                menuItemClicked(it.id)
            }

    }

    private fun menuItemClicked(id:Int){
        when(id){
            R.id.action_edit_profile->{
                var intent = Intent(this@ProfileActivity,EditProfileActivity::class.java)
                startActivity(intent)
            }
        }
    }

}