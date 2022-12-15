package com.smarthub.baseapplication.ui.fragments.profile

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.PopupWindow
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.BaseActivity
import com.smarthub.baseapplication.databinding.ActivityProfileEditBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.profile.UserProfileUpdate
import com.smarthub.baseapplication.model.register.Commucationaddess
import com.smarthub.baseapplication.network.ProfileDetails
import com.smarthub.baseapplication.ui.adapter.ProfileListItemAdapter
import com.smarthub.baseapplication.utils.AppConstants
import com.smarthub.baseapplication.viewmodels.ProfileViewModel

class EditProfileActivity : BaseActivity() {

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

        profileViewModel?.profileUpdate?.observe(this) {
            hideLoader()
            Log.d("status","Resource for profile update")
            if (it != null && it.data?.status?.isNotEmpty() == true) {
                if (it.status == Resource.Status.SUCCESS) {
                    AppPreferences.getInstance().saveString("data", it.data.status)
                    Log.d("status", "${it.message}")
                    if (it.data.status.equals("updated")) {
                        Toast.makeText(this@EditProfileActivity, "Profile Update Successful", Toast.LENGTH_LONG).show()
                    }
                    return@observe
                } else {
                    Log.d("status", "${it.message}")
                    Toast.makeText(this@EditProfileActivity, "error:" + it.message, Toast.LENGTH_LONG).show()

                }
            } else {
                Log.d("status", AppConstants.GENERIC_ERROR)
                Toast.makeText(
                    this@EditProfileActivity,
                    AppConstants.GENERIC_ERROR,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        Log.d("status","Creating Update profile button")
        dataBinding?.profileUpdate?.setOnClickListener {
            Log.d("status","Inside Update profile button")
            val userProfileData:UserProfileUpdate?=getProfileData()
            showLoader()
            profileViewModel?.updateProfileData(userProfileData)
        }

        showLoader()
        profileViewModel?.getProfileData()
        profileViewModel?.profileResponse?.observe(this) {
            hideLoader()
            if (it != null && it.data?.get(0)?.data?.isNotEmpty() == true) {
                if (it.status == Resource.Status.SUCCESS) {
                    AppPreferences.getInstance().saveString("data", it.data[0].data)
                    uiDataMapping(it.data[0])
                    Log.d("status", "${it.message}")
                    Toast.makeText(this@EditProfileActivity, "ProfileSuccessful", Toast.LENGTH_LONG).show()
                    return@observe
                } else {
                    Log.d("status", "${it.message}")
                    Toast.makeText(this@EditProfileActivity, "error:" + it.message, Toast.LENGTH_LONG).show()

                }
            } else {
                Log.d("status", AppConstants.GENERIC_ERROR)
                Toast.makeText(this@EditProfileActivity, AppConstants.GENERIC_ERROR, Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun uiDataMapping(profileDetails: ProfileDetails){
        Log.d("status","UI Data mapping")
        dataBinding?.firstName?.text = profileDetails.first_name.toEditable()
        dataBinding?.lastName?.text =  profileDetails.last_name.toEditable()
        dataBinding?.email?.text = profileDetails.email.toEditable()
        dataBinding?.editEmployeeId?.text = profileDetails.id.toEditable()
    }

    private fun getProfileData(): UserProfileUpdate {
        Log.d("status","getProfileData")
        val profileUpdate: UserProfileUpdate =UserProfileUpdate()
        profileUpdate.username = dataBinding?.firstName?.text.toString()
        profileUpdate.last_name =  dataBinding?.lastName?.text.toString()
        profileUpdate.email = dataBinding?.email?.text.toString()
        profileUpdate.communicationaddress = getCommunicationAddress()
        return profileUpdate
    }

    private fun getCommunicationAddress() : Commucationaddess {
        val commucationAddress = Commucationaddess()
        return commucationAddress
    }


}