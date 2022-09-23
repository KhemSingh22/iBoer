package com.example.ibeor.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.ibeor.R
import com.example.ibeor.activities.ViewModelFactries.SigninWithFact
import com.example.ibeor.databinding.ActivitySignInWithBinding
import com.example.ibeor.viewmodels.SignWithViewModel

class SignInWithActivity : AppCompatActivity() {
    lateinit var signInWithBinding: ActivitySignInWithBinding
    lateinit var signWithViewModel: SignWithViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        signInWithBinding = DataBindingUtil.setContentView(this,R.layout.activity_sign_in_with)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        getSupportActionBar()?.hide()

        signInWithBinding.apply {
            signWithViewModel = ViewModelProvider(this@SignInWithActivity, SigninWithFact(this@SignInWithActivity)).get(SignWithViewModel::class.java)
            signInWithBinding.signinviewmodel = signWithViewModel
            signInWithBinding.lifecycleOwner = this@SignInWithActivity

        }
    }
}