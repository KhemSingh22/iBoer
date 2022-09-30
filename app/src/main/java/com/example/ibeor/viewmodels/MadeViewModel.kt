package com.example.ibeor.viewmodels

import android.app.Activity
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.ibeor.R

class MadeViewModel(var activity: Activity) : ViewModel() {

    fun onback(view: View) {
        activity.onBackPressed()
    }

    fun continu(view: View) {
        Navigation.findNavController(view).navigate(R.id.action_madeitFragment_to_mainActivity)
        Toast.makeText(activity, "Done", Toast.LENGTH_SHORT).show()
    }
}