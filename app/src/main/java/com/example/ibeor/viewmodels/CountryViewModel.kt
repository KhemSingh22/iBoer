package com.example.ibeor.viewmodels

import android.app.Activity
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.ibeor.R

class CountryViewModel(var activity: Activity) : ViewModel() {
    var spinnerValue: String? = null

    fun continu(view: View) {
//        Navigation.findNavController(view).navigate(R.id.action_selectCountryFragment_to_welComeFragment)
    }

    fun onback(view: View) {
        activity.onBackPressed()
    }
}