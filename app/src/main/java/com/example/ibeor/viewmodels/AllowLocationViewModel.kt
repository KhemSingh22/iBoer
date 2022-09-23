package com.example.ibeor.viewmodels

import android.app.Activity
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel

class AllowLocationViewModel(private var activity: Activity) : ViewModel() {

    fun back(view: View) {
        activity.onBackPressed()
    }

    fun enableLocation(view: View) {
        Toast.makeText(activity, "Your Location is enable", Toast.LENGTH_SHORT).show()
    }

    fun desableLocation(view: View) {
        Toast.makeText(activity, "Your Location is desable", Toast.LENGTH_SHORT).show()

    }
}