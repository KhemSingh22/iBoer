package com.example.ibeor.viewmodels

import android.app.Activity
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.ibeor.R

class AllowLocationViewModel(private var activity: Activity) : ViewModel() {

    fun back(view: View) {
        activity.onBackPressed()
    }

    fun enableLocation(view: View) {
        Navigation.findNavController(view).navigate(R.id.action_allowLocationFragment_to_genderFragment)
    }

    fun desableLocation(view: View) {
        Toast.makeText(activity, "Your Location is desable", Toast.LENGTH_SHORT).show()

    }
}