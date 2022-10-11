package com.example.ibeor.viewmodels

import android.app.Activity
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.ibeor.R
import com.example.ibeor.manager.SessionManager
import com.example.ibeor.utils.FirebaseUtils
import com.google.firebase.firestore.SetOptions

class MadeViewModel(var activity: Activity) : ViewModel() {

    fun onback(view: View) {
        activity.onBackPressed()
    }

    fun continu(view: View) {

    }

    fun nextScreen(view: View?, madeProgress: ProgressBar){
        madeProgress.visibility = View.VISIBLE

        var CountryCODE = HashMap<String, Any>()
        val status = 10
        CountryCODE.put("statusOTP", status)

        FirebaseUtils().fireStoreDatabase.collection("Users").document(FirebaseUtils().Uid)
            .set(CountryCODE, SetOptions.merge())
            .addOnSuccessListener {
                madeProgress.visibility = View.GONE
                Log.e("LLLLLL>>>>>>", "Upload Sucess")
                val sessionManager = SessionManager(activity)
                sessionManager.saVeStatus(status.toString())

                Navigation.findNavController(view!!).navigate(R.id.action_madeitFragment_to_homeScreenFragment)
            }
            .addOnFailureListener {
                Log.e("LLLLLL>>>>>>", "FAILD")
            }
    }
}