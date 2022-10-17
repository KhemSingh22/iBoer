package com.example.ibeor.ViewModelFactries

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ibeor.viewmodels.EditProfileViewModel

class EditProfileViewModelFact(var activity: FragmentActivity) :ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(EditProfileViewModel::class.java!!)) {
            EditProfileViewModel(this.activity) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}


