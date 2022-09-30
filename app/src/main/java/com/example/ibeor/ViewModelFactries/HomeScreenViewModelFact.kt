package com.example.ibeor.ViewModelFactries
import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ibeor.viewmodels.HomeScreenViewModel

class HomeScreenViewModelFact (var activity: Activity) :ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeScreenViewModel::class.java!!)) {
            HomeScreenViewModel(this.activity) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}