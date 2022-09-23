package com.example.ibeor.viewmodels

import android.app.Activity
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.ibeor.R

class FirstNameViewModel(var activity: Activity) : ViewModel() {
    var firstName: String? = null

    fun onback(view: View) {
        activity.onBackPressed()
    }

    fun continu(view: View) {
        if (firstName.toString().isNullOrEmpty()) {

        } else {
            Navigation.findNavController(view)
                .navigate(R.id.action_firstNameFragment_to_allowLocationFragment)
        }
    }
}