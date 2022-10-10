package com.example.ibeor.viewmodels

import android.app.Activity
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.ibeor.R

class AccountRecoveryViewModel(var activity: Activity) : ViewModel() {
    fun signInWithemail(view: View) {
//        Navigation.findNavController(view).navigate(R.id.action_accountRecoeryFragment_to_signinEmailFragment)
    }

    fun backprees(view: View) {
        activity.onBackPressed()
    }

}