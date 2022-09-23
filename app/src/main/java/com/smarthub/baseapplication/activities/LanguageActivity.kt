package com.smarthub.baseapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.smarthub.baseapplication.LanguageListAdapter
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ActivityDashboardBinding
import com.smarthub.baseapplication.model.LangModel

class LanguageActivity : AppCompatActivity() {

    private var dataBinding : ActivityDashboardBinding ?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dataBinding = ActivityDashboardBinding.inflate(layoutInflater)
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
    }
}