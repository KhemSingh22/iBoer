package com.example.ibeor.activities.ViewModelFactries

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ibeor.activities.activities.LoginActivity
import com.example.ibeor.activities.activities.viewmodels.LoginViewModel

class LoginViewmodelFact(private var activity:Activity) :ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(LoginViewModel::class.java!!)) {
            LoginViewModel(this.activity) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}