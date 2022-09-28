package com.example.ibeor.ViewModelFactries

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ibeor.viewmodels.MadeViewModel

class MadeViewModelFact(var activity: Activity):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MadeViewModel::class.java!!)) {
            MadeViewModel(this.activity) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}