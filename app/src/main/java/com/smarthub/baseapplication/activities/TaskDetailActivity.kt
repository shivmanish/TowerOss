package com.smarthub.baseapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.databinding.ActivityHandleDeepLinksBinding
import com.smarthub.baseapplication.databinding.ActivityTaskDetailBinding
import com.smarthub.baseapplication.model.workflow.TaskDataListItem
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel
import com.smarthub.baseapplication.viewmodels.TaskViewModel

class TaskDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityTaskDetailBinding
    lateinit var viewModel : TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[TaskViewModel::class.java]
        binding = ActivityTaskDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.hasExtra("url")){
            val id = intent.getStringExtra("url")
            binding.titleText.text = id
            viewModel.fetchSiteDropDownData(id!!)
        }

        if (viewModel.taskDataList?.hasActiveObservers() == true)
            viewModel.taskDataList?.removeObservers(this)
        viewModel.taskDataList?.observe(this){
            if (it?.data != null){
                if (it.data.isNotEmpty()){
                    mapUIData(it.data[0])
                }
                Toast.makeText(this@TaskDetailActivity,"task data fetched",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this@TaskDetailActivity,"Something went wrong",Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun mapUIData(item : TaskDataListItem){
        binding.titleText.text = item.Processname
    }

    override fun onStop() {
        super.onStop()
        if (viewModel.taskDataList?.hasActiveObservers() == true)
            viewModel.taskDataList?.removeObservers(this)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent (this@TaskDetailActivity, DashboardActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}