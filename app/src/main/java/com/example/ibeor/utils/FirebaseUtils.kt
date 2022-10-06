package com.example.ibeor.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseUtils {
    val fireStoreDatabase = FirebaseFirestore.getInstance()
    companion object var Uid = FirebaseAuth.getInstance().currentUser!!.uid
}