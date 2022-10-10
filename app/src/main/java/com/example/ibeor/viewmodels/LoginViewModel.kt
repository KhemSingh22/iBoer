package com.example.ibeor.activities.activities.viewmodels
import android.app.Activity
import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.ibeor.R
import com.example.ibeor.activities.activities.LoginActivity
import com.example.ibeor.manager.SessionManager


class LoginViewModel(var application:Activity) : ViewModel() {

    fun createAccount(view : View) {

        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_signInWithFragment)


        /*  val sessionManager = SessionManager(application)
          val loggedIn: Boolean = sessionManager.getlogin()

          if (loggedIn){
              val status = sessionManager.getStatus()
              val uid = sessionManager.getUid()
              Log.e("SDSDSD",uid.toString() + status)

              when(status.toString().toInt()){

                  1 ->  Navigation.findNavController(view).navigate(R.id.action_enterPhoneCodeFragment2_to_yourMailFragment2)
                  2 ->  Navigation.findNavController(view).navigate(R.id.action_enterPhoneCodeFragment2_to_firstNameFragment2)
                  3 ->  Navigation.findNavController(view).navigate(R.id.action_enterPhoneCodeFragment2_to_allowLocationFragment2)
                  4 ->  Navigation.findNavController(view).navigate(R.id.action_enterPhoneCodeFragment2_to_genderFragment2)
                  5 ->Navigation.findNavController(view).navigate(R.id.action_enterPhoneCodeFragment2_to_addPhotoFragment2)
  //                    5 -> findNavController(R.id.action_enterPhoneCodeFragment2_to_allowLocationFragment2)  Navigation.findNavController(vi!!).navigate(R.id.action_enterPhoneCodeFragment2_to_DOBFragment2)
                  6 -> Navigation.findNavController(view).navigate(R.id.action_enterPhoneCodeFragment2_to_addPhotoFragment2)
                  7  -> Navigation.findNavController(view).navigate(R.id.action_enterPhoneCodeFragment2_to_addPhotoFragment2)
                  8  -> Navigation.findNavController(view).navigate(R.id.action_enterPhoneCodeFragment2_to_addPhotoFragment2)
              }

          }else{
              Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_signInWithFragment)
          }*/
    }

    fun signIn(view: View) {
        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_myPhoneNumberFragment2)

    }

    fun troubleSigning(view: View) {
        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_accountRecoeryFragment2)
    }

 }