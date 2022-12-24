package com.smarthub.baseapplication.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

        if (path == "task"){
            try {
                val url: String = appLinkData.path!!.substring(appLinkData.path!!.lastIndexOf('/') + 1)

                AppLogger.log("url:$url")
                val intent = Intent (this@HandleDeepLinksActivity, TaskDetailActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                intent.putExtra("url",url)
                startActivity(intent)
                finish()
            }catch (e:java.lang.Exception){
                AppLogger.log("e : "+e.localizedMessage)
            }

        }else{
            binding!!.linkText.text = "Data not parsed properly"
        }

    }
}