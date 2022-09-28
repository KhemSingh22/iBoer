package com.example.ibeor.viewmodels

import android.app.Activity
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel

class MadeViewModel(var activity: Activity) : ViewModel() {

    fun onback(view: View) {
        activity.onBackPressed()
    }

    fun continu(view: View) {
        Toast.makeText(activity, "Done", Toast.LENGTH_SHORT).show()
    }
}