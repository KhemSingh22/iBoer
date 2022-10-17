package com.example.ibeor.viewmodels

import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel

class EditProfileViewModel(var activity: FragmentActivity) : ViewModel() {

    fun backprees(view: View) {
        activity.onBackPressed()
    }

}