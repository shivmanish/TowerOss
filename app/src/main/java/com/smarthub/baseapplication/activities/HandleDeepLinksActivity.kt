package com.smarthub.baseapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.smarthub.baseapplication.databinding.ActivityHandleDeepLinksBinding
import com.smarthub.baseapplication.utils.AppLogger

class HandleDeepLinksActivity : AppCompatActivity() {
    var binding: ActivityHandleDeepLinksBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHandleDeepLinksBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        // ATTENTION: This was auto-generated to handle app links.
        val appLinkIntent = intent
        //        String appLinkAction = appLinkIntent.getAction();
        val appLinkData = appLinkIntent.data
        val path = appLinkData!!.pathSegments[0]

        binding!!.linkText.text = path

        AppLogger.log("path:$path,url:${appLinkData.path}")

        val intent = Intent (this@HandleDeepLinksActivity, DashboardActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        intent.putExtra("key",path)
        startActivity(intent)
        finish()

    }
}