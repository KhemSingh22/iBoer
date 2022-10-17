package com.example.ibeor.viewmodels

import android.app.Activity
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ibeor.dataclasses.UserPRofile
import com.example.ibeor.manager.SessionManager
import com.example.ibeor.utils.FirebaseUtils
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.toObject

class HomeScreenViewModel(var activty:Activity) : ViewModel() {

    var UserList : MutableLiveData<ArrayList<UserPRofile>> = MutableLiveData()
    var datalist : ArrayList<UserPRofile> = ArrayList()

    fun openProfile(view:View){

    }

    fun readData(): MutableLiveData<ArrayList<UserPRofile>> {
     FirebaseUtils().fireStoreDatabase.collection("Users")
         .get()
         .addOnSuccessListener {  DocumentSnapshot ->

             Log.e("SSSAA",DocumentSnapshot.documents.toString())

             var user =DocumentSnapshot

             for (snap in user!!.documents.iterator()){
                 val firstName = snap!!.get("firstName")
                 val dob = snap!!.get("dob")
                 val email = snap!!.get("email")
                 val age = snap!!.get("age")
                 val gender = snap!!.get("gender")
                 val UserID = snap!!.get("UserID")
                 val PhoneNumber = snap!!.get("PhoneNumber")
                 val statusOTP = snap!!.get("statusOTP")
                 val UserFirstCountry = snap!!.get("UserFirstCountry")
                 val UserFirstCountryCode = snap!!.get("UserFirstCountryCode")
                 val UserSecCountry = snap!!.get("UserSecCountry")
                 val UserSecCountryCode = snap!!.get("UserSecCountryCode")
                 val userImage_0 = snap!!.get("userImage_0")
                 val userImage_1 = snap!!.get("userImage_1")
                 val userImage_2 = snap!!.get("userImage_2")
                 val userImage_3 = snap!!.get("userImage_3")
                 val userImage_4 = snap!!.get("userImage_4")
                 val userImage_5 = snap!!.get("userImage_5")

                 val sessionManager = SessionManager(activty)
                  val UID =  sessionManager.getUid()
                 if (!(UID!!.equals(UserID))){
                     val data  = UserPRofile(firstName.toString(),email.toString(),age.toString().toInt(),dob.toString(),gender.toString(),UserID.toString(),PhoneNumber.toString(),
                         statusOTP.toString().toInt(),UserFirstCountry.toString(),UserFirstCountryCode.toString(),UserSecCountry.toString(),UserSecCountryCode.toString()
                         , userImage_0.toString(),userImage_1.toString(),userImage_2.toString(),userImage_3.toString(),userImage_4.toString(),userImage_5.toString(),)


                     datalist.add(data)
                     Log.e("AAAAAACCC",data.toString())
                 }
             }
             UserList.value = datalist

         }
         .addOnFailureListener {
             Log.e("SSSAA",it.message.toString())

         }

        return UserList
    }


}