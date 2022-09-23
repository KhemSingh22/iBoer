package com.example.ibeor.viewmodels

import android.app.Activity
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.ibeor.R

class YourEmailViewModel(var activity: Activity) : ViewModel() {
    var email_etd : String? = null

    fun continu(view: View) {
        Navigation.findNavController(view)
            .navigate(R.id.action_yourMailFragment_to_firstNameFragment)
    }

    fun backprees(view: View) {
        activity.onBackPressed()

    }

}