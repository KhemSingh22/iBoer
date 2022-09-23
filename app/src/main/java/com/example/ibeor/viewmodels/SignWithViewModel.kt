package com.example.ibeor.viewmodels
import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.ibeor.activities.AccountRecoveryActivity

class SignWithViewModel(var activity: Activity) : ViewModel() {
    fun phoneNumber(view: View) {
        var intent = Intent(activity, AccountRecoveryActivity::class.java)
        activity.startActivity(intent)
    }
    fun fb_login(view: View) {

    }
    fun applelogin(view: View) {

    }
}