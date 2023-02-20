package com.smarthub.baseapplication.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ActivityTaskDetailBinding
import com.smarthub.baseapplication.model.workflow.TaskDataListItem
import com.smarthub.baseapplication.ui.fragments.task.TaskSearchTabFragment
import com.smarthub.baseapplication.ui.fragments.task.adapter.TaskAdapter
import com.smarthub.baseapplication.viewmodels.TaskViewModel

class TaskDetailActivity : BaseActivity(), TaskAdapter.TaskLisListener {
    lateinit var binding: ActivityTaskDetailBinding
    lateinit var viewModel : TaskViewModel
    lateinit var siteId:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[TaskViewModel::class.java]
        binding = ActivityTaskDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.btnBack.setOnClickListener {
//            onBackPressed()
//        }
        if (intent.hasExtra("url")){
            val id = intent.getStringExtra("url")
//            binding.titleText.text = id
            showLoader()
            viewModel.fetchTaskDetails(id!!)
        }
        if (intent.hasExtra("siteId")){
            siteId = intent.getStringExtra("siteId")!!
        }


        if (viewModel.taskDataList?.hasActiveObservers() == true)
            viewModel.taskDataList?.removeObservers(this)
        viewModel.taskDataList?.observe(this){
            hideLoader()
            if (it?.data != null){
                if (it.data.isNotEmpty()){
                    mapUIData(it.data[0])
                }
//                else
//                    setFragment(TaskSearchTabFragment(siteId,))
                Toast.makeText(this@TaskDetailActivity,"task data fetched",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this@TaskDetailActivity,"Something went wrong",Toast.LENGTH_SHORT).show()
            }
        }
//        binding.refreshLayout.setOnRefreshListener {
//            binding.refreshLayout.isRefreshing = false
//            if (intent.hasExtra("url")){
//                val id = intent.getStringExtra("url")
////                binding.titleText.text = id
//                showLoader()
//                viewModel.fetchTaskDetails(id!!)
//            }else{
//                Toast.makeText(this,"Task id not found",Toast.LENGTH_SHORT).show()
//            }
//        }


    }

    private fun mapUIData(item : TaskDataListItem){
        setFragment(TaskSearchTabFragment(siteId,item.Taskid))
//        binding.titleText.text ="Task\n${item.Processname}"
//
//        binding.listItem.adapter = TaskAdapter(applicationContext,this)
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
    private fun setFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.placeholder, fragment)
        fragmentTransaction.commit()
    }

    override fun attachmentItemClicked() {

    }

    override fun detailsItemClicked() {

    }

    override fun requestinfoClicked() {

    }

    override fun operationInfoDetailsItemClicked() {
//       
    }

    override fun geoConditionsDetailsItemClicked() {
//       
    }

    override fun siteAccessDetailsItemClicked() {
//       
    }

    override fun EditInstallationAcceptence() {
       
    }

    override fun EditTowerItem() {
       
    }

    override fun editPoClicked(position: Int) {
       
    }

    override fun viewPoClicked(position: Int) {
       
    }

    override fun editConsumableClicked(position: Int) {
       
    }

    override fun viewConsumableClicked(position: Int) {
       
    }

    override fun editOffsetClicked(position: Int) {
       
    }

    override fun viewOffsetClicked(position: Int) {
       
    }
}