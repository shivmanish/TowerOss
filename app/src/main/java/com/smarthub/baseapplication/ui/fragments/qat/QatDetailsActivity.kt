package com.smarthub.baseapplication.ui.fragments.qat

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.BaseActivity
import com.smarthub.baseapplication.databinding.ActivityQatBinding
import com.smarthub.baseapplication.databinding.ActivityQatDetailsBinding
import com.smarthub.baseapplication.listeners.PunchPointListener
import com.smarthub.baseapplication.model.siteInfo.qat.QatCardItem
import com.smarthub.baseapplication.model.siteInfo.qat.QatTemplateModel
import com.smarthub.baseapplication.model.siteInfo.qat.qat_main.Category
import com.smarthub.baseapplication.model.siteInfo.qat.qat_main.Item
import com.smarthub.baseapplication.model.siteInfo.qat.qat_main.Subitem
import com.smarthub.baseapplication.ui.adapter.qat.QatPunchPointAdapter
import com.smarthub.baseapplication.ui.dialog.qat.PunchPointCreateDialog
import com.smarthub.baseapplication.ui.dialog.qat.PunchPointResolveDialog
import com.smarthub.baseapplication.ui.fragments.qat.adapter.PageAdapterQat

class QatDetailsActivity : BaseActivity(), PunchPointListener {
    companion object{
        var data : Subitem?=null
    }
    lateinit var binding: ActivityQatDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQatDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    fun init() {
        setRecyclerView()
    }

    private fun setRecyclerView() {
        val adapter = data?.checkpoint?.let { QatPunchPointAdapter(this, it) }
        binding.recyclerViewOpen.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewOpen.adapter = adapter
        binding.back.setOnClickListener {
           onBackPressed()
        }
    }

    override fun itemClicked() {

    }

    override fun addPunchPoint() {
        val dialog = PunchPointCreateDialog(this)
        dialog.show()
    }

    override fun punchPointClicked() {
        val dialog = PunchPointResolveDialog(this)
        dialog.show()
    }

}