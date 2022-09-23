package com.example.ibeor.ViewModelFactries

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ibeor.activities.activities.viewmodels.LoginViewModel
import com.example.ibeor.viewmodels.YourEmailViewModel

class YourEmailViewModelFact(var activity: Activity):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(YourEmailViewModel::class.java!!)) {
            YourEmailViewModel(this.activity) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}