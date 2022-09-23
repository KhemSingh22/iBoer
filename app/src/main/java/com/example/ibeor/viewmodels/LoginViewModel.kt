package com.example.ibeor.activities.activities.viewmodels

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.ibeor.activities.SignInWithActivity

class LoginViewModel(var activity: Activity) : ViewModel() {
    fun createAccount(view: View) {
        var intent = Intent(activity, SignInWithActivity::class.java)
        activity.startActivity(intent)
    }

    fun signIn(view: View) {
        Toast.makeText(activity, "Working signIn ", Toast.LENGTH_SHORT).show()
    }

    fun troubleSigning(view: View) {
        Toast.makeText(activity, "Working troubleSigning", Toast.LENGTH_SHORT).show()
    }
}