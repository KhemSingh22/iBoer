package com.example.ibeor.ViewModelFactries

import android.app.Activity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ibeor.viewmodels.MyPhoneNumberViewModel

class MyPhoneNumberViewModelFact(var activity: FragmentActivity):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MyPhoneNumberViewModel::class.java!!)) {
            MyPhoneNumberViewModel(this.activity) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}