package com.example.ibeor.activities.ViewModelFactries

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ibeor.activities.activities.viewmodels.LoginViewModel
import com.example.ibeor.viewmodels.SignWithViewModel

class SigninWithFact (var activity: Activity):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SignWithViewModel::class.java!!)) {
            SignWithViewModel(this.activity) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}