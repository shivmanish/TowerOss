package com.smarthub.baseapplication.activities

import android.os.Bundle
import android.view.Window
import com.example.trackermodule.homepage.BaseActivity
import com.smarthub.baseapplication.ui.adapter.LanguageListAdapter
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ActivityLanguageBinding
import com.smarthub.baseapplication.model.LangModel

class LanguageActivity : BaseActivity() {

    private var dataBinding : ActivityLanguageBinding ?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        dataBinding = ActivityLanguageBinding.inflate(layoutInflater)
        setContentView(dataBinding?.root)
        initViews()
    }

    //    initialize all view
    fun initViews(){
        var list = ArrayList<LangModel>()
        list.add(LangModel(false, R.drawable.ic_hindi, R.drawable.ic_selcted_hindi,"Hindi"))
        list.add(LangModel(false, R.drawable.ic_telugu, R.drawable.ic_selcted_telugu,"Telugu"))
        list.add(LangModel(false, R.drawable.ic_tamil, R.drawable.ic_selcted_tamil,"Tamil"))
        list.add(LangModel(false, R.drawable.ic_english, R.drawable.ic_selcted_english,"English"))
        list.add(LangModel(false, R.drawable.ic_bengali, R.drawable.ic_selcted_bengali,"Bengali"))
        list.add(LangModel(false, R.drawable.ic_marathi, R.drawable.ic_selcted_marathi,"Marathi"))
        list.add(LangModel(false, R.drawable.ic_odia, R.drawable.ic_selcted_odia,"Odia"))
        list.add(LangModel(false, R.drawable.ic_malayalam, R.drawable.ic_selcted_malayalam,"Malayalam "))
        list.add(LangModel(false, R.drawable.ic_gujarati, R.drawable.ic_selcted_gujrathi,"Gujarati"))
        list.add(LangModel(false, R.drawable.ic_punjabi, R.drawable.ic_selcted_punjabi,"Punjabi"))
        list.add(LangModel(false, R.drawable.ic_assamese, R.drawable.ic_selcted_assamese,"Assamese"))
        dataBinding?.langList?.adapter = LanguageListAdapter(list)

        dataBinding?.imgBack?.setOnClickListener {
            onBackPressed()
        }
    }
}