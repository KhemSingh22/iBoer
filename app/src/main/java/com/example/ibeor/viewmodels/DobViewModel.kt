package com.example.ibeor.viewmodels

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.ibeor.R
import com.google.android.material.bottomsheet.BottomSheetDialog

class DobViewModel(var activity: Activity) : ViewModel() {

    var dob: String? = null
    lateinit var dialog : Dialog
    fun Continu(view: View) {
        openPopup(view)
    }

    fun onbackpress(view: View) {
        activity.onBackPressed()
    }

    private fun openPopup(view: View) {


//      val   dialog = Dialog(activity,R.style.roundcorner_popup)
      val   dialog = Dialog(activity)
        dialog.setContentView(R.layout.confirm_info)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialog.getWindow()!!.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(false)
        val tv_edit = dialog.findViewById<CardView>(R.id.tv_edit)
        val tv_confirm = dialog.findViewById<CardView>(R.id.confirm)

        tv_confirm.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_DOBFragment_to_addPhotoFragment2)
            dialog.dismiss()
        }
        tv_edit.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_DOBFragment_to_genderFragment)
            dialog.dismiss()
        }
        dialog.show()
    }

    fun openCalender(view: View) {
        Toast.makeText(activity, "Open Calender", Toast.LENGTH_SHORT).show()
    }
}