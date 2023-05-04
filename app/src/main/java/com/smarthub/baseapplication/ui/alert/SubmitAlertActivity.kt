package com.smarthub.baseapplication.ui.alert

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.example.trackermodule.homepage.BaseActivity
import com.smarthub.baseapplication.activities.DashboardActivity
import com.smarthub.baseapplication.databinding.SubmitAlertActivityBinding
import com.smarthub.baseapplication.ui.alert.dialog.ChatFragment
import com.smarthub.baseapplication.ui.alert.model.UpdateAlertData
import com.smarthub.baseapplication.ui.alert.model.newData.NewData
import com.smarthub.baseapplication.ui.alert.viewmodel.AlertViewModel
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils

class SubmitAlertActivity: BaseActivity() {
    lateinit var binding: SubmitAlertActivityBinding
    lateinit var viewmodel: AlertViewModel
    var reportedBy = "7269024641"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SubmitAlertActivityBinding.inflate(layoutInflater)
        viewmodel = ViewModelProvider(this)[AlertViewModel::class.java]
        setContentView(binding.root)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
            return
        }
        super.onBackPressed()
        val intent = Intent (this, DashboardActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    private fun addFragment(fragment: Fragment?) {
        val backStateName: String = supportFragmentManager.javaClass.name
        val manager = supportFragmentManager
        val fragmentPopped = manager.popBackStackImmediate(backStateName, 0)
        if (!fragmentPopped) {
            val transaction = manager.beginTransaction()
            transaction.setCustomAnimations(
                R.anim.enter,
                R.anim.exit,
                R.anim.pop_enter,
                R.anim.pop_exit
            )
            transaction.replace(R.id.container_layout, fragment!!)
            transaction.addToBackStack(backStateName)
            transaction.commit()
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        if (viewmodel.sendAlertResponseLivedata.hasActiveObservers())
            viewmodel.sendAlertResponseLivedata.removeObservers(this)
        viewmodel.sendAlertResponseLivedata.observe(this) {
            //response will get here
            hideLoader()
            if (it?.data != null && it.data.isNotEmpty()) {
                reportedBy = it.data[0].ReportedBy
                if (it.data[0].chats!=null && it.data[0].chats?.isNotEmpty() == true){
                    AppLogger.log("chat size :${it.data[0].chats?.size}")
                    if (chatFragment!=null)
                        chatFragment?.updateList(ArrayList(it.data[0].chats))
                }

                mapUiData(it.data[0])
            }else Toast.makeText(this@SubmitAlertActivity,"empty data",Toast.LENGTH_SHORT).show()
        }

        binding.close.setOnClickListener {
            AppLogger.log("date test :"+Utils.getCurrentFormatedDate())

            val update = UpdateAlertData("$action", Utils.getCurrentFormatedDate(),binding.detailedText.text.toString(),"${intent?.getStringExtra("id")}")
            viewmodel.updateAlertDetails(update)
            onBackPressed()
        }
        binding.chat.setOnClickListener {
            chatFragment = ChatFragment()
            val arg = Bundle()
            arg.putString("id","${intent?.getStringExtra("id")}")
            arg.putString("reportedBy","${intent?.getStringExtra("reportedBy")}")
            addFragment(chatFragment)
        }

        if (intent!=null && intent?.hasExtra("id")==true){
            showLoader()
            AppLogger.log("id : ${intent?.getStringExtra("id")}")
            intent?.getStringExtra("id")?.let { viewmodel.getAlertDetails(it) }
        }

        binding.refreshLayout.setOnRefreshListener {
            binding.refreshLayout.isRefreshing  =false
            if (intent!=null && intent?.hasExtra("id")==true){
                showLoader()
                AppLogger.log("id : ${intent?.getStringExtra("id")}")
                intent?.getStringExtra("id")?.let { viewmodel.getAlertDetails(it) }
            }
        }


        binding.actionTracker.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                binding.actionOngoing.isChecked = false
                binding.actionWorking.isChecked = false
                action = 1
            }
        }
        binding.actionWorking.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                binding.actionOngoing.isChecked = false
                binding.actionTracker.isChecked = false
                action = 2
            }
        }
        binding.actionOngoing.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                binding.actionTracker.isChecked = false
                binding.actionWorking.isChecked = false
                action = 3
            }
        }
    }
    var action = 0
    var chatFragment : ChatFragment?=null

    private fun mapUiData(data: NewData){
        when (data.ActionStatus) {
            "1" -> binding.actionTracker.isChecked = true
            "2" -> binding.actionWorking.isChecked = true
            "3" -> binding.actionOngoing.isChecked = true
        }
        binding.detailedText.text = data.FullDetails.toEditable()
        binding.troublemaker.text = data.SATroubleMaker
        binding.issuetype.text = data.SAIssueType
        binding.issuetypetwo.text = data.SAIssueType
        binding.severity.text = data.SASeverity
        binding.severitytwo.text = data.SASeverity
        binding.address.text = data.SADetails
        binding.name.text = data.sitename
        binding.senderName.text = data.sitename
        binding.sendDate.text = data.HeppenDateTime
        binding.date.text = data.HeppenDateTime
        binding.sendDate.text = "D1"
        var comma_string = ""
        for (dat in data.Sendalertsupportdata) {
            comma_string = if (comma_string.equals("")) {
                dat.SuRecepientusername
            } else {
                comma_string + "" + dat.SuRecepientusername
            }
        }

        binding.recipentaName.text = comma_string

    }
}