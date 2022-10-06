package com.example.ibeor.activities.activities.viewmodels
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.ibeor.R


class LoginViewModel() : ViewModel() {

    fun createAccount(view : View) {
      Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_signInWithFragment)
    }

    fun signIn(view: View) {
        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_myPhoneNumberFragment2)

    }

    fun troubleSigning(view: View) {
        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_accountRecoeryFragment2)
    }

 }