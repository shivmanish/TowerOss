package com.smarthub.baseapplication.activities
import android.os.Bundle
import com.smarthub.baseapplication.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity() {

    lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}