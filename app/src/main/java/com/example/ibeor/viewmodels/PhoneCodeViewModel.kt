package com.example.ibeor.viewmodels

import `in`.aabhasjindal.otptextview.OtpTextView
import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.navigation.Navigation
import com.example.ibeor.R
import com.example.ibeor.manager.SessionManager
import com.example.ibeor.utils.FirebaseUtils
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.SetOptions
import java.util.concurrent.TimeUnit


class PhoneCodeViewModel(
    var activity: Activity,
    var otpView: OtpTextView,
    var idPBLoading: ProgressBar
) :
    AndroidViewModel(activity.application) {

    private var mAuth: FirebaseAuth? = FirebaseAuth.getInstance()
    private var mVerificationId: String? = null
    private var mobile: String? = null
    private var userUid :String?=""
//    private var fireStoreDatabase = FirebaseFirestore.getInstance()

    @SuppressLint("StaticFieldLeak")
    private var view: View ? = null


    fun continu(v: View) {
        view = v
        if (otpView.toString().isNullOrEmpty()) {
            Toast.makeText(activity, "Enter valid OTP", Toast.LENGTH_SHORT).show()
        } else {
            if (mVerificationId != null) {
                verifyVerificationCode(otpView.otp.toString(),v)
            }
        }

    }

    fun back(view: View) {
        activity.onBackPressed()
    }

    fun sendVerificationCode(mob: String?, view: View?) {
        mobile = mob
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            "+91$mob",
            60,
            TimeUnit.SECONDS,
            activity,
            mCallbacks
        )
    }


    private val mCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks =
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                //Getting the code sent by SMS
                val otp = phoneAuthCredential.smsCode

                //sometime the code is not detected automatically
                //in this case the code will be null
                //so user has to manually enter the code
                if (otp != null) {
                    otpView.setOTP(otp)
//                    otpView.setText(code)
                    //verifying the code
                    verifyVerificationCode(otp, null)
                }
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Log.e("FAIL", e.message.toString())
                Toast.makeText(activity, e.message, Toast.LENGTH_LONG).show()
            }

            override fun onCodeSent(
                s: String,
                forceResendingToken: PhoneAuthProvider.ForceResendingToken
            ) {
                super.onCodeSent(s, forceResendingToken)
                mVerificationId = s
                var mResendToken = forceResendingToken
            }
        }

    fun verifyVerificationCode(otp: Any, v: View?) {
        val credential = PhoneAuthProvider.getCredential(mVerificationId!!, otp.toString())
        if (credential != null) {
            signInWithPhoneAuthCredential(credential,v!!)
        }

    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential, vIe: View) {
        mAuth!!.signInWithCredential(credential)
            .addOnCompleteListener(activity,
                OnCompleteListener<AuthResult?> { task ->
                    if (task.isSuccessful) {
                        //verification successful we will start the profile activity

                        val user: FirebaseUser? = task.result.user
                        Toast.makeText(activity, "Your number is verified ", Toast.LENGTH_LONG).show()
                        userUid = user!!.uid

                        FirebaseUtils().fireStoreDatabase .collection("Users").document(user.uid).get().addOnSuccessListener {
                            var data=it as DocumentSnapshot
                            if(data.exists()) {
                                readData(vIe)
                                Log.e("DATTT",data.toString())
                            }else {
                                uploadData(mobile, userUid.toString())
                                Log.e("DTO" ,"atus")
                            }

                        }

                        FirebaseUtils().fireStoreDatabase.collection("Users").document(user!!.uid).get().addOnFailureListener {
                            uploadData(mobile, userUid.toString())
                            Log.e("DTO" ,"atus")
                        }


                    } else {

                        Toast.makeText(activity, "ERROR", Toast.LENGTH_LONG).show()

                    }
                })
    }

    private fun uploadData(mobile: String?, userUid: String) {
        idPBLoading.visibility = View.VISIBLE
        var hashMap: HashMap<String, Any> = HashMap<String, Any>()
        hashMap.put("PhoneNumber", mobile.toString())
        hashMap.put("UserID", userUid)
        hashMap.put("statusOTP", 1)

          val sessionManager = SessionManager(activity)
           sessionManager.setUid(mobile,userUid)

        FirebaseUtils().fireStoreDatabase.collection("Users")
            .document(userUid)
            .set(hashMap, SetOptions.merge())
            .addOnSuccessListener {
                idPBLoading.visibility = View.GONE
                Navigation.findNavController(view!!).navigate(R.id.action_enterPhoneCodeFragment2_to_yourMailFragment2)
            }

            .addOnFailureListener {
                idPBLoading.visibility = View.GONE
                Toast.makeText(activity, "SOME ERROR ", Toast.LENGTH_LONG).show()
                Log.e("Error adding document", it.message.toString())

            }
    }

    fun readData(vi: View?) {
        FirebaseUtils().fireStoreDatabase.collection("Users")
            .document(FirebaseUtils().Uid)
            .get().addOnSuccessListener { documentSnapshot ->
                val user = documentSnapshot.data

                    val uid = user!!.get("UserID")
                    val phone = user.get("PhoneNumber")
                    val status = user.get("statusOTP")!!
                    val emaiL = user.get("email")
                    val gender = user.get("gender")
                    val dob = user.get("dob")
                    val age = user.get("age")
                    val profile = user.get("image")
                    val country = user.get("country")
                    val firstName = user.get("firstName")

                val sessionManager = SessionManager(activity)
                sessionManager.setUid(phone.toString(),uid.toString())
                sessionManager.saVeStatus(status.toString())
                sessionManager.loginUser(true)

                Log.e("DATASTAMP", user.toString())

                when(status.toString().toInt()){
                    1 -> Navigation.findNavController(vi!!).navigate(R.id.action_enterPhoneCodeFragment2_to_yourMailFragment2)
                    2 -> Navigation.findNavController(vi!!).navigate(R.id.action_enterPhoneCodeFragment2_to_firstNameFragment2)
                    3 -> Navigation.findNavController(vi!!).navigate(R.id.action_enterPhoneCodeFragment2_to_allowLocationFragment2)
                    4 -> Navigation.findNavController(vi!!).navigate(R.id.action_enterPhoneCodeFragment2_to_genderFragment2)
                    5 -> Navigation.findNavController(vi!!).navigate(R.id.action_enterPhoneCodeFragment2_to_addPhotoFragment2)
//                    5 -> Navigation.findNavController(vi!!).navigate(R.id.action_enterPhoneCodeFragment2_to_DOBFragment2)
                    6 -> Navigation.findNavController(vi!!).navigate(R.id.action_enterPhoneCodeFragment2_to_selectCountryFragment)
                    7  -> Navigation.findNavController(vi!!).navigate(R.id.action_enterPhoneCodeFragment2_to_welComeFragment2)
                    8  -> Navigation.findNavController(vi!!).navigate(R.id.action_enterPhoneCodeFragment2_to_madeitFragment)
                    10  -> Navigation.findNavController(vi!!).navigate(R.id.action_enterPhoneCodeFragment2_to_homeScreenFragment)
                }

            }
            .addOnFailureListener {
                Log.e("DATASTAMP", it.message.toString())
            }
    }
}

