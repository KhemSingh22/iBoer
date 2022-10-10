package com.example.ibeor.viewmodels

import android.app.Activity
import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.ibeor.R


class SigningWithEmailViewModel(var activity: Activity) : ViewModel() {

    var email_etd: String? = null
    var dialog = Dialog(activity)


    fun sendMail(view: View) {
        if (email_etd.toString().isNullOrEmpty()) {
            Toast.makeText(activity, "Please enter your mail", Toast.LENGTH_SHORT).show()
        } else {
            showDialog(view)
        }

    }

    private fun showDialog(view: View) {
        dialog.setContentView(R.layout.dialog_checkmail_layout)
        dialog.getWindow()!!.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(false)

        dialog.show()
//        Navigation.findNavController(view).navigate(R.id.action_signinEmailFragment_to_myPhoneNumberFragment)
        dialog.dismiss()

    }

    fun reSendMail(view: View) {

    }

    fun onbackpress(view: View) {
        activity.onBackPressed()
    }

}