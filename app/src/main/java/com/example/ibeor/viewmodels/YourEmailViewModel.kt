package com.example.ibeor.viewmodels

import android.app.Activity
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.ibeor.R
import com.example.ibeor.manager.SessionManager
import com.example.ibeor.utils.FirebaseUtils
import com.google.firebase.firestore.SetOptions

class YourEmailViewModel(var activity: Activity) : ViewModel() {
    var email_etd = ObservableField<String?>()
    private var uId = FirebaseUtils().Uid

    fun continu(view: View) {
        if (isValidEmail(email_etd.get().toString())) {
            uploadEmail(email_etd.get(), view)
        } else {
            Toast.makeText(activity, "Please enter valid Email", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun uploadEmail(mail: String?, view: View) {
        val status = 2
        val emaiL = HashMap<String,Any>()
        emaiL.put("email",mail.toString())
        emaiL.put("statusOTP", status)

        Log.e("SSSSSSS", uId + "BBB==" + emaiL)

        FirebaseUtils().fireStoreDatabase.collection("Users").document(uId)
            .set(emaiL, SetOptions.merge())
            .addOnSuccessListener {
                Toast.makeText(activity, "Email Upload Success", Toast.LENGTH_SHORT).show()
                Navigation.findNavController(view).navigate(R.id.action_yourMailFragment2_to_firstNameFragment2)
                val sessionManager = SessionManager(activity)
                sessionManager.saVeStatus(status.toString())
            }
            .addOnFailureListener {
                Toast.makeText(activity, "Email Upload Fail", Toast.LENGTH_SHORT).show()
            }

    }

    fun backprees(view: View) {
        activity.onBackPressed()
    }
}