package com.example.ibeor.viewmodels

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.ibeor.R

class GenderViewModel(var activity: Activity) : ViewModel() {
    var icon = activity.getDrawable(R.drawable.apple_icon)
    var check = false

    lateinit var woman: TextView
    lateinit var man: TextView

    fun backprees(view: View) {
        activity.onBackPressed()

    }



    fun continu(view: View) {
        Navigation.findNavController(view).navigate(R.id.action_genderFragment_to_DOBFragment)
    }

    @SuppressLint("ResourceAsColor")
    fun woMan(view: View) {
        woman = TextView(activity)
        man = TextView(activity)
        woman = view as TextView

    /*    if (check.equals(true)) {
            woman!!.setBackgroundResource(R.drawable.textfield_back)
            woman!!.setTextColor(Color.BLACK)
            check = false
        } else {
            woman!!.setBackgroundResource(R.drawable.gender_back)
            woman!!.setTextColor(Color.WHITE)
            man!!.setBackgroundResource(R.drawable.textfield_back)
            man!!.setTextColor(Color.BLACK)
            check = true

        }*/
    }

    @SuppressLint("ResourceAsColor")
    fun maN(view: View) {
        woman = TextView(activity)
        man = TextView(activity)
        man = view as TextView

    /*    if (check.equals(true)) {
            man!!.setBackgroundResource(R.drawable.textfield_back)
            man!!.setTextColor(Color.BLACK)
            check = false

        } else {
            man!!.setBackgroundResource(R.drawable.gender_back)
            man!!.setTextColor(Color.WHITE)
            woman!!.setBackgroundResource(R.drawable.textfield_back)
            woman!!.setTextColor(Color.BLACK)
            check = true

        }*/
    }
}