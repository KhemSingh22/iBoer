package com.example.ibeor.viewmodels

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.ibeor.R
import java.util.*


class MyPhoneNumberViewModel(var activity: FragmentActivity, var phoneNo: EditText) : ViewModel() {
    var phone_Code=""
    var phoneNumber : String ? = null
//    var phoneNumber =ObservableField("")
    var dialog = Dialog(activity)

    fun backprees(view: View) {
        activity.onBackPressed()

    }

    fun continu(view: View) {
        PhoneNumberUtils.formatNumber(phoneNumber.toString() , Locale.getDefault().getCountry())

        if (phoneNumber.toString().isEmpty() || phoneNumber.toString().length<10) {
            showpopUp(view)
        } else {
                val bundle = Bundle()
                bundle.putString("phoneNumber", phoneNumber.toString())
                Navigation.findNavController(view).navigate(R.id.action_myPhoneNumberFragment2_to_enterPhoneCodeFragment2,bundle)

        }
    }

    fun cancel(view: View) {
    phoneNo.text.clear()
    }

    private fun showpopUp(view: View) {
        dialog.setContentView(R.layout.invalid_no_layout)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.getWindow()!!
            .setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(true)
        val ok = dialog.findViewById<CardView>(R.id.okay)

        ok.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

}