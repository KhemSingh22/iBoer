package com.example.ibeor.viewmodels

import android.app.Activity
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModel
import com.example.ibeor.dataclasses.UserPRofile
import com.example.ibeor.utils.FirebaseUtils
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.toObject

class HomeScreenViewModel(var activty:Activity) : ViewModel() {

    fun openProfile(view:View){

    }

    fun readData() {
     FirebaseUtils().fireStoreDatabase.collection("Users").document(FirebaseUtils().Uid)
         .get()
         .addOnSuccessListener {  DocumentSnapshot ->

             Log.e("SSSAA",DocumentSnapshot.data.toString())

             val userData = DocumentSnapshot.toObject<UserPRofile>()

             Log.e("KAAAAa",userData.toString())

         }
         .addOnFailureListener {
             Log.e("SSSAA",it.message.toString())

         }
    }

}