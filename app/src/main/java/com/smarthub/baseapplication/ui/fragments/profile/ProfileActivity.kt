package com.smarthub.baseapplication.ui.fragments.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.BaseActivity
import com.smarthub.baseapplication.ui.adapter.ProfileListViewAdapter
import com.smarthub.baseapplication.databinding.ActivityProfileBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.login.UserLoginPost
import com.smarthub.baseapplication.model.profile.UserProfileGet
import com.smarthub.baseapplication.network.ProfileDetails
import com.smarthub.baseapplication.network.User
import com.smarthub.baseapplication.utils.AppConstants
import com.smarthub.baseapplication.viewmodels.ProfileViewModel

class ProfileActivity : BaseActivity() {

    private var dataBinding : ActivityProfileBinding?=null
    private var profileViewModel : ProfileViewModel?=null
    private var user : User?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dataBinding = ActivityProfileBinding.inflate(layoutInflater)
        profileViewModel= ViewModelProvider(this)[ProfileViewModel::class.java]
        setContentView(dataBinding?.root)
        showLoader()
        profileViewModel?.getProfileData(UserProfileGet("7269024641"))
        profileViewModel?.profileResponse?.observe(this) {
            hideLoader()
            if (it != null && it.data?.get(0)?.data?.isNotEmpty() == true) {
                if (it.status == Resource.Status.SUCCESS && it.data != null) {
                    AppPreferences.getInstance().saveString("data", "${it.data?.get(0)?.data}")
                    uiDataMapping(it.data?.get(0)!!)
                    Log.d("status", "${it.message}")
                    Toast.makeText(this@ProfileActivity, "ProfileSuccessful", Toast.LENGTH_LONG).show()
                    return@observe
                } else {
                    Log.d("status", "${it.message}")
                    Toast.makeText(this@ProfileActivity, "error:" + it.message, Toast.LENGTH_LONG).show()

                }
            } else {
                Log.d("status", "${AppConstants.GENERIC_ERROR}")
                Toast.makeText(this@ProfileActivity, AppConstants.GENERIC_ERROR, Toast.LENGTH_LONG).show()
            }
        }
        initViews()
    }

    private fun uiDataMapping(profileDetails: ProfileDetails){
        dataBinding?.textName?.text = "${profileDetails.first_name} ${profileDetails.last_name}"
        dataBinding?.textCall?.text = "${profileDetails.phone}"
        dataBinding?.textMessage?.text = "${profileDetails.email}"
        dataBinding?.textYellow?.text = "${profileDetails.id}"
        dataBinding?.textActive?.text = "${profileDetails.active}"

//        dataBinding?.textRole?.text = "${profileDetails?.}"
    }

    private var popupWindow: PopupWindow? = null
    private fun initViews(){
        findViewById<View>(R.id.img_back).setOnClickListener {
            onBackPressed()
        }

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
                var intent = Intent(this@ProfileActivity, EditProfileActivity::class.java)
                startActivity(intent)
            }
        }
    }

}