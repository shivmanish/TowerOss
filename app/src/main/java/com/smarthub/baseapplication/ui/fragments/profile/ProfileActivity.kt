package com.smarthub.baseapplication.ui.fragments.profile

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.PopupWindow
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.BaseActivity
import com.smarthub.baseapplication.activities.LoginActivity
import com.smarthub.baseapplication.databinding.ActivityProfileBinding
import com.smarthub.baseapplication.databinding.ProfileCustomMenuBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.profile.viewProfile.newData.ProfileData
import com.smarthub.baseapplication.utils.AppConstants
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.ProfileViewModel


class ProfileActivity : BaseActivity() {

    lateinit var binding : ActivityProfileBinding
    private var profileViewModel : ProfileViewModel?=null
    lateinit var adapter: ProfilePageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        profileViewModel= ViewModelProvider(this)[ProfileViewModel::class.java]
        binding = ActivityProfileBinding.inflate(layoutInflater)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(binding.root)
        initViews()
        adapter=ProfilePageAdapter(supportFragmentManager)
        binding.viewpager.adapter=adapter
        binding.tabs.setupWithViewPager(binding.viewpager)
        tabCustomization()
        if (profileViewModel?.profileResponse?.hasActiveObservers()==true)
            profileViewModel?.profileResponse?.removeObservers(this)
        profileViewModel?.profileResponse?.observe(this) {
            hideLoader()
            if (it != null && it.data?.isNotEmpty() == true) {
                if (it.status == Resource.Status.SUCCESS) {
                    AppPreferences.getInstance().saveString("data", Gson().toJson(it.data[0]))
                    uiDataMapping(it.data[0])
                    adapter.setdata(it.data[0])
                    Log.d("status", "${it.message}")
                    Toast.makeText(this@ProfileActivity, "ProfileSuccessful", Toast.LENGTH_LONG).show()
                    return@observe
                } else {
                    Log.d("status", "${it.message}")
                    Toast.makeText(this@ProfileActivity, "error:" + it.message, Toast.LENGTH_LONG).show()

                }
            } else {
                Log.d("status", AppConstants.GENERIC_ERROR)
                Toast.makeText(this@ProfileActivity, AppConstants.GENERIC_ERROR, Toast.LENGTH_LONG).show()
            }
        }
        binding.refreshLayuot.apply {
            setOnRefreshListener {
                isRefreshing = false
                showLoader()
                profileViewModel?.getProfileData()
            }
        }
        showLoader()
        profileViewModel?.getProfileData()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

    }

    private fun uiDataMapping(profileDetails: ProfileData){
        try{
            binding.textName.text = String.format("%s %s",profileDetails.first_name,profileDetails.last_name)
            binding.textCall.text = profileDetails.phone
            binding.textMessage.text = profileDetails.email
            binding.textYellow.text = profileDetails.id
            binding.textActive.text = profileDetails.active
            binding.textRole.text = profileDetails.roles.get(0)
        }
        catch (e : Exception){
            AppLogger.log("Profile View Error ${e.localizedMessage}")
            Toast.makeText(this,"Profile View Error",Toast.LENGTH_LONG).show()
        }



    }

    private var popupWindow: PopupWindow? = null
    private fun initViews(){
        findViewById<View>(R.id.img_back).setOnClickListener {
            onBackPressed()
        }

//        binding.profileItemsList.setHasFixedSize(true)

        val list : ArrayList<Any> = ArrayList()
        list.add("single_item")
        list.add("default")
        list.add("double_item")
        list.add("double_half_item")
//        binding.profileItemsList.adapter = ProfileListViewAdapter()

        binding.imgMenu.setOnClickListener {
            createPopWindow(it)
        }
        
        binding.logout.setOnClickListener {
            AppPreferences.getInstance().clearSavedData()
            val intent = Intent(this@ProfileActivity,LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }


    private fun createPopWindow(view: View) {
        val layoutInflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        val customView = layoutInflater.inflate(R.layout.profile_custom_menu, null)
        var dialogBinding = ProfileCustomMenuBinding.inflate(layoutInflater)
        if (popupWindow != null && popupWindow?.isShowing == true) popupWindow?.dismiss()

        //instantiate popup window
        popupWindow = PopupWindow(dialogBinding.root, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        popupWindow?.elevation = 10.0f
        popupWindow?.showAsDropDown(view, 100, -160)
        // Closes the popup window when touch outside.
        popupWindow?.isOutsideTouchable = true

        for (i in 0 until dialogBinding.childContainer.childCount)
            dialogBinding.childContainer.getChildAt(i).setOnClickListener {
                menuItemClicked(it.id)
            }
    }

    private fun menuItemClicked(id:Int){
        when(id){
            R.id.action_edit_profile->{
                val intent = Intent(this@ProfileActivity, EditProfileActivity::class.java)
                startActivity(intent)
            }
        }
    }
    private fun tabCustomization(){
        if(binding.tabs.tabCount==1) {
            binding.tabs.setBackgroundColor(Color.parseColor("#ffffff"))
            binding.tabs.setSelectedTabIndicatorColor(Color.parseColor("#ffffff"))
        }
        if(binding.tabs.tabCount<=5)
            binding.tabs.tabMode = TabLayout.MODE_FIXED
        else
            binding.tabs.tabMode = TabLayout.MODE_SCROLLABLE
    }

}