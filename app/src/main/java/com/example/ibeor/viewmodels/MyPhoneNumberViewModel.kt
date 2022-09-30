package com.example.ibeor.viewmodels

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.ibeor.R

class MyPhoneNumberViewModel(var activity: Activity) : ViewModel() {
    var phone_Code: String? = null
    var phoneNumber: String? = null
    var dialog = Dialog(activity)

    fun backprees(view: View) {
        activity.onBackPressed()
    }

    fun continu(view: View) {
        showpopUp(view)
    }

    fun cancel(view: View) {
        showpopUp(view)
    }

    private fun showpopUp(view: View) {
        dialog.setContentView(R.layout.invalid_no_layout)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.getWindow()!!.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(true)
        val ok = dialog.findViewById<CardView>(R.id.okay)

        ok.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_myPhoneNumberFragment_to_enterPhoneCodeFragment)
            dialog.dismiss()
        }
        dialog.show()
    }

}