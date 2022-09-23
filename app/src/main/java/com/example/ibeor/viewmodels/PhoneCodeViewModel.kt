package com.example.ibeor.viewmodels

import android.app.Activity
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.ibeor.R

class PhoneCodeViewModel(var activity: Activity) : AndroidViewModel(activity.application) {
    var otp : String? = null

    fun continu(view: View) {
        Navigation.findNavController(view)
            .navigate(R.id.action_enterPhoneCodeFragment_to_yourMailFragment)
    }

    fun backprees(view: View) {
        activity.onBackPressed()
    }

}