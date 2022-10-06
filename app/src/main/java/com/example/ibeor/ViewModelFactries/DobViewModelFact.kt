package com.example.ibeor.ViewModelFactries
import android.app.Activity
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ibeor.viewmodels.DobViewModel

class DobViewModelFact(private var activity: Activity, private var tvDob: TextView, private var tvAge: TextView):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(DobViewModel::class.java!!)) {
            DobViewModel(this.activity , this.tvDob,this.tvAge) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}