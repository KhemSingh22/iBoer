package com.example.ibeor.viewmodels

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.ibeor.R
import com.example.ibeor.activities.AccountRecoveryActivity

class SignWithViewModel(var activity: Activity) : ViewModel() {
    fun phoneNumber(view: View) {
        Navigation.findNavController(view).navigate(R.id.action_signInWithFragment_to_myPhoneNumberFragment2)
    }

    fun fb_login(view: View) {

    }

    fun applelogin(view: View) {

    }

    fun onback(view: View) {
        activity.onBackPressed()
    }

}