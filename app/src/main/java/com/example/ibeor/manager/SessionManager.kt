package com.example.ibeor.manager
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.example.ibeor.R

class SessionManager(context: Activity)  {
        private var pref: SharedPreferences? = null
        private var editor: SharedPreferences.Editor?=null

        init {
            try {
                pref = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }

        fun setUid(mobile : String?, userUid: String) {
            editor = pref!!.edit()
            editor!!.putString("PHONE", mobile.toString())
            editor!!.putString("USERID", userUid.toString())
            editor!!.apply()
        }

        fun getUid(): String? {
            return (pref!!.getString("USERID", null))
        }

    fun saVeStatus(Status :String){
        editor = pref!!.edit()
        editor!!.putString("STATUS", Status.toString())
        editor!!.apply()

    }
    fun loginUser(login :Boolean){
        editor = pref!!.edit()
        editor!!.putBoolean("LoginUSer", login)
        editor!!.apply()
    }
    fun getlogin(): Boolean{
        return (pref!!.getBoolean("LoginUSer", false))
    }

    fun getStatus(): String{
        return (pref!!.getString("STATUS", "").toString())
    }


}