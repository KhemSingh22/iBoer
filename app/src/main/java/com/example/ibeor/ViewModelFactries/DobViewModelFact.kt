package com.example.ibeor.ViewModelFactries
import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ibeor.viewmodels.AllowLocationViewModel
import com.example.ibeor.viewmodels.DobViewModel

class DobViewModelFact(private var activity: Activity):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(DobViewModel::class.java!!)) {
            DobViewModel(this.activity) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}