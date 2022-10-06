package com.example.ibeor.viewmodels

import android.app.Activity
import android.view.View
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.ibeor.R
import com.example.ibeor.utils.FirebaseUtils
import com.google.firebase.firestore.SetOptions

class FirstNameViewModel(var activity: Activity) : ViewModel() {
    var firstName = ObservableField<String?>()

    fun onback(view: View) {
        activity.onBackPressed()
    }

    fun continu(view: View) {
        if (firstName.get().toString().isEmpty()) {
            Toast.makeText(activity, "Please enter your name", Toast.LENGTH_LONG).show()
        } else {
            upLoadData(firstName.get(),view)
        }
    }

    private fun upLoadData(FirstName : String?, view: View) {

        var firstname = HashMap<String,Any>()
        firstname.put("firstName",FirstName.toString())
        firstname.put("statusOTP",3)

        FirebaseUtils().fireStoreDatabase.collection("Users")
            .document(FirebaseUtils().Uid)
            .set(firstname, SetOptions.merge())
            .addOnSuccessListener {
                Toast.makeText(activity,"Name Upload Success",Toast.LENGTH_SHORT).show()
                Navigation.findNavController(view).navigate(R.id.action_firstNameFragment2_to_allowLocationFragment2)
            }
            .addOnFailureListener {
                Toast.makeText(activity,"Name Upload Fail",Toast.LENGTH_SHORT).show()
            }

    }
}