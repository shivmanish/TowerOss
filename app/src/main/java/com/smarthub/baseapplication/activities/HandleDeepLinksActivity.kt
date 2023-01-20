package com.smarthub.baseapplication.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.smarthub.baseapplication.databinding.ActivityHandleDeepLinksBinding
import com.smarthub.baseapplication.ui.alert.SubmitAlertActivity
import com.smarthub.baseapplication.utils.AppLogger

class HandleDeepLinksActivity : AppCompatActivity() {
    var binding: ActivityHandleDeepLinksBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHandleDeepLinksBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        verifyLink()
    }

    private fun verifyLink(){
//        val path = "task/293"
        val path = "${intent.data?.path}"

        binding!!.linkText.text = path

        AppLogger.log("path:$path,url:${path}")

        try {
            val url: String = path.substring(path.lastIndexOf('/') + 1)
            if (path.contains("task")){

                AppLogger.log("task url:$url")
                val intent = Intent (this@HandleDeepLinksActivity, TaskDetailActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                intent.putExtra("url",url)
                startActivity(intent)
                finish()
            }else if (path.contains("sendalertdata")){

                AppLogger.log("alert url:$url")
                val intent = Intent (this@HandleDeepLinksActivity, SubmitAlertActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                intent.putExtra("id",url)
                startActivity(intent)
                finish()
            }

            AppLogger.log("path : $path")
            Toast.makeText(this,"url : $url",Toast.LENGTH_SHORT).show()
        }catch (e:java.lang.Exception){
            AppLogger.log("e : "+e.localizedMessage)
        }
    }
}