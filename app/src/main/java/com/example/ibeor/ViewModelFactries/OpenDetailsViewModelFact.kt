package com.example.ibeor.ViewModelFactries

import android.app.Activity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ibeor.viewmodels.OpenDetailsVM

class OpenDetailsViewModelFact(private var activity: FragmentActivity) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(OpenDetailsVM::class.java!!)) {
            OpenDetailsVM(this.activity) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}