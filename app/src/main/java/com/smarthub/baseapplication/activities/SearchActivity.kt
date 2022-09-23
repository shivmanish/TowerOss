package com.smarthub.baseapplication.activities

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.smarthub.baseapplication.databinding.SearchActivityBinding

class SearchActivity : AppCompatActivity() {
    private var dataBinding : SearchActivityBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionBar: ActionBar? = this.supportActionBar
        actionBar?.hide()
        dataBinding = SearchActivityBinding.inflate(layoutInflater)
        setContentView(dataBinding?.root)

    }
}