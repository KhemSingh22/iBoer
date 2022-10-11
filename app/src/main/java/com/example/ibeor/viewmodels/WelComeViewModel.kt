package com.example.ibeor.viewmodels

import android.app.Activity
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.ibeor.R

class WelComeViewModel(var activity: Activity) : ViewModel() {
    fun backprees(view: View) {
        activity.onBackPressed()
    }

    fun Continu(view: View) {
    Navigation.findNavController(view).navigate(R.id.action_welComeFragment_to_madeitFragment)
    }

}