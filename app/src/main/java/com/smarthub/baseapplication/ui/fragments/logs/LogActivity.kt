package com.smarthub.baseapplication.ui.fragments.logs

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarthub.baseapplication.databinding.ActivityLogBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.logs.LogsDataInfo
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class LogActivity : AppCompatActivity() {
    lateinit var viewmodel: HomeViewModel
    lateinit var binding: ActivityLogBinding
    lateinit var adapter: LogAdapter
    var id: String = "448"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogBinding.inflate(layoutInflater)
        viewmodel = ViewModelProvider(this).get(HomeViewModel::class.java)
        setContentView(binding.root)
        fetchChangeLogs()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        adapter = LogAdapter(this@LogActivity, ArrayList<LogsDataInfo>())
        binding.logList.adapter = adapter
        if (viewmodel.loglivedata?.hasActiveObservers()==true)
            viewmodel.loglivedata?.removeObservers(this)
        viewmodel.loglivedata!!.observe(this, Observer {
            if (it?.data != null && it.status == Resource.Status.SUCCESS) {
                AppLogger.log("Log Data fetched successfully===>: ${it.data.item}")
                AppLogger.log("size :${it.data.item!!.get(0).ChangeLog.size}")
                binding.logList.layoutManager = LinearLayoutManager(this)
                try {
                    adapter.addData(
                        it.data.item!!.get(0).ChangeLog as ArrayList<LogsDataInfo>
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

        })
        binding.swipeLayout.setOnRefreshListener {
            binding.swipeLayout.isRefreshing = false
            viewmodel?.fetchChangeLog(id)
            if(adapter!=null){
                adapter.removeData()
            }
        }

        binding.back.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    fun fetchChangeLogs() {
        viewmodel.fetchChangeLog(id)
    }

}