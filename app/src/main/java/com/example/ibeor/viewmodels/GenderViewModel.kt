package com.example.ibeor.viewmodels
import android.app.Activity
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.ibeor.R
import com.example.ibeor.manager.SessionManager
import com.example.ibeor.utils.FirebaseUtils
import com.google.firebase.firestore.SetOptions

class GenderViewModel(var activity: Activity) : ViewModel() {


    fun backprees(view: View) {
        activity.onBackPressed()

    }
    
    fun continu(view: View) {

//        Navigation.findNavController(view).navigate(R.id.action_genderFragment_to_DOBFragment)
    }

    fun uploadData(view : View?, meCheck: String?) {
        val status = 4
        val gender = HashMap<String, Any>()
        gender.put("statusOTP",status)

        if (meCheck!!.equals("1")) {
            gender.put("gender", "Male")
        }
        else if (meCheck.equals("2")) {
            gender.put("gender", "FeMale")
        }
        Log.e("DDDDDD",meCheck)

        FirebaseUtils().fireStoreDatabase.collection("Users").document(FirebaseUtils().Uid)
            .set(gender, SetOptions.merge())
            .addOnSuccessListener {

                Navigation.findNavController(view!!).navigate(R.id.action_genderFragment2_to_DOBFragment2)
                Log.e("DDDDDD",it.toString()+"")
                val sessionManager = SessionManager(activity)
                sessionManager.saVeStatus(status.toString())

            }
            .addOnFailureListener {
                Log.e("DDDDDD",it.toString()+"")

            }
    }
}