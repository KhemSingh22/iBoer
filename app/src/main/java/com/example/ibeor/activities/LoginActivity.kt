package com.example.ibeor.activities.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.ibeor.R
import com.example.ibeor.activities.ViewModelFactries.LoginViewmodelFact
import com.example.ibeor.activities.activities.viewmodels.LoginViewModel
import com.example.ibeor.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var loginBinding: ActivityLoginBinding
    lateinit var loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        getSupportActionBar()?.hide()

        loginBinding = DataBindingUtil.setContentView(this@LoginActivity,R.layout.activity_login)

        loginBinding.apply {
            loginViewModel = ViewModelProvider(this@LoginActivity, LoginViewmodelFact(this@LoginActivity)).get(LoginViewModel::class.java)
            loginBinding.loginviewmodel = loginViewModel
            loginBinding.lifecycleOwner =this@LoginActivity
        }

    }

}