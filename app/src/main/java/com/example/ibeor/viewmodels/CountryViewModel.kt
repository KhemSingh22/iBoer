package com.example.ibeor.viewmodels

import android.app.Activity
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.ibeor.R
import com.example.ibeor.manager.SessionManager
import com.example.ibeor.utils.FirebaseUtils
import com.google.firebase.firestore.SetOptions
import com.hbb20.CountryCodePicker

class CountryViewModel(var activity: Activity) : ViewModel() {

    var firstCountry: String? = ""
    var firstCountryCode: String? = ""
    var SecCountry: String? = ""
    var SecCountryCode: String? = ""

    fun onback(view: View) {
        activity.onBackPressed()
    }

    fun setCodePicker1(ccp1: CountryCodePicker) {

        ccp1.setOnCountryChangeListener {
            firstCountry = ccp1.selectedCountryName
            firstCountryCode = ccp1.selectedCountryCode
            Log.e(
                "CONTEXCT", "This is from OnCountryChangeListener. \n Country updated to "
                        + ccp1.selectedCountryName +
                        "(" + ccp1.selectedCountryCodeWithPlus
            )
        }
    }

    fun setCodePicker(ccp: CountryCodePicker, contnuiBtn: CardView, ccp1: CountryCodePicker) {

        ccp.setOnCountryChangeListener {

            ccp1.visibility = View.VISIBLE
            contnuiBtn.visibility = View.VISIBLE
            SecCountryCode = ccp.selectedCountryCode
            SecCountry = ccp.selectedCountryName

            Log.e(
                "CONTEXCT", "This is from OnCountryChangeListener. \n Country updated to "
                        + ccp.selectedCountryName +
                        "(" + ccp.selectedCountryCodeWithPlus
            )
        }
    }

    fun uploadCountry(view: View, countryProgress: ProgressBar) {
        countryProgress.visibility = View.VISIBLE
        Log.e("SSSSSSSSSSSS", firstCountry + "<<<==VVV==>>> " + SecCountry)
        var CountryCODE = HashMap<String, Any>()
        val status = 7
        CountryCODE.put("UserFirstCountry", firstCountry.toString())
        CountryCODE.put("UserFirstCountryCode", firstCountryCode.toString())
        CountryCODE.put("UserSecCountry", SecCountry.toString())
        CountryCODE.put("UserSecCountryCode", SecCountryCode.toString())
        CountryCODE.put("statusOTP", status)

        FirebaseUtils().fireStoreDatabase.collection("Users").document(FirebaseUtils().Uid)
            .set(CountryCODE, SetOptions.merge())
            .addOnSuccessListener {
                countryProgress.visibility = View.GONE
                Toast.makeText(activity, "Data Upload Sucess!!", Toast.LENGTH_SHORT).show()
                Log.e("LLLLLL>>>>>>", "Upload Sucess")
             val sessionManager = SessionManager(activity)
                 sessionManager.saVeStatus(status.toString())


                Navigation.findNavController(view).navigate(R.id.action_selectCountryFragment_to_welComeFragment)
            }
            .addOnFailureListener {
                Log.e("LLLLLL>>>>>>", "FAILD")
            }
    }
}