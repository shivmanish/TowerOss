package com.smarthub.baseapplication.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.PopupWindow
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.adapter.ProfileListAdapter
import com.smarthub.baseapplication.adapter.ProfileListItemAdapter
import com.smarthub.baseapplication.adapter.ProfileListViewAdapter
import com.smarthub.baseapplication.databinding.ActivityProfileEditBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.profile.UserProfileGet
import com.smarthub.baseapplication.model.profile.UserProfileUpdate
import com.smarthub.baseapplication.network.ProfileDetails
import com.smarthub.baseapplication.utils.AppConstants
import com.smarthub.baseapplication.viewmodels.ProfileViewModel

class EditProfileActivity : AppCompatActivity() {

    private var dataBinding : ActivityProfileEditBinding?=null
    private var profileViewModel : ProfileViewModel?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("status", "Inside Edit function")
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dataBinding = ActivityProfileEditBinding.inflate(layoutInflater)
        profileViewModel= ViewModelProvider(this)[ProfileViewModel::class.java]
        setContentView(dataBinding?.root)
        initViews()
    }

    private var popupWindow: PopupWindow? = null
    private fun initViews(){
        Log.d("status", "Inside Edit init function")
        dataBinding?.profileItemsList?.setHasFixedSize(true)
        findViewById<View>(R.id.img_back).setOnClickListener {
            onBackPressed()
        }

        dataBinding?.profileItemsList?.adapter = ProfileListItemAdapter()
        dataBinding?.imgMenu?.setOnClickListener {
//            createPopWindow(it)
        }
        Log.d("status","Creating Update profile button")
        dataBinding?.profileUpdate?.setOnClickListener {
            Log.d("status","Inside Update profile button")
            var userProfileData:UserProfileUpdate?=uiDataMapping()
            profileViewModel?.updateProfileData(userProfileData)
            profileViewModel?.profileUpdate?.observe(this) {
                Log.d("status","Resource for profile update")
                if (it != null && it.data?.status?.isNotEmpty() == true) {
                    if (it.status == Resource.Status.SUCCESS && it.data != null) {
                        AppPreferences.getInstance().saveString("data", "${it.data?.status}")
                        Log.d("status", "${it.message}")
                        if (it.data.status.equals("updated")) {
                            Toast.makeText(
                                this@EditProfileActivity,
                                "Profile Update Successful",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        return@observe
                    } else {
                        Log.d("status", "${it.message}")
                        Toast.makeText(
                            this@EditProfileActivity,
                            "error:" + it.message,
                            Toast.LENGTH_LONG
                        ).show()

                    }
                } else {
                    Log.d("status", "${AppConstants.GENERIC_ERROR}")
                    Toast.makeText(
                        this@EditProfileActivity,
                        AppConstants.GENERIC_ERROR,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun uiDataMapping(): UserProfileUpdate? {
        Log.d("status","UI Data mapping")
        var profileUpdate: UserProfileUpdate?=UserProfileUpdate()
        profileUpdate?.username = dataBinding?.tCName?.text.toString()
        profileUpdate?.last_name =  dataBinding?.tDName?.text.toString()
        profileUpdate?.email = dataBinding?.tMName?.text.toString()

        return profileUpdate
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

    }

    fun menuItemClicked(id:Int){

    }


}