package com.example.ibeor.viewmodels

import android.app.Activity
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ibeor.R
import com.example.ibeor.dataclasses.AddPhoto

class AddPhotoViewModel(var activity: Activity):ViewModel() {

    fun onback(view: View) {
        activity.onBackPressed()
    }

    fun continu(view: View) {
    Navigation.findNavController(view).navigate(R.id.action_addPhotoFragment_to_selectCountryFragment)
    }
}