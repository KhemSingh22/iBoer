package com.example.ibeor.viewmodels

import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.ibeor.R
import com.example.ibeor.utils.FirebaseUtils
import com.google.firebase.firestore.SetOptions
import java.util.*

class DobViewModel(var activity: Activity, var tvDob : TextView, var tvAge: TextView) : ViewModel() {

    var dob = ObservableField<String>()
    var Age = MutableLiveData<Any>()
    var dateofbirth = HashMap<String,Any>()

    fun Continu(view: View) {
        if (dob.get().equals("")) {
            Toast.makeText(activity, "Please enter your Date of birth", Toast.LENGTH_SHORT).show()
        } else {
            openPopup(view,dateofbirth)

        }
    }

    private fun UploadDAta(view: View) {
        dateofbirth.put("statusOTP",5)
        FirebaseUtils().fireStoreDatabase.collection("Users").document(FirebaseUtils().Uid)
            .set(dateofbirth, SetOptions.merge())
            .addOnSuccessListener {
                Log.e("UPLOAD==",it.toString()+"CVB")
                Navigation.findNavController(view).navigate(R.id.action_DOBFragment2_to_addPhotoFragment2)
                tvDob.clearComposingText()
                tvAge.clearComposingText()
            }
            .addOnFailureListener {
                Log.e("SDDDDD",it.toString())
            }
    }


    fun onbackpress(view: View) {
        activity.onBackPressed()
    }

    private fun openPopup(view: View, dateofbirth: HashMap<String, Any>) {

        var dob = dateofbirth.get("dob")
        var age = dateofbirth.get("age")

//      val   dialog = Dialog(activity,R.style.roundcorner_popup)
        val dialog = Dialog(activity)
        dialog.setContentView(R.layout.confirm_info)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialog.getWindow()!!.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.setCancelable(false)
        val tv_edit = dialog.findViewById<CardView>(R.id.tv_edit)
        val tv_confirm = dialog.findViewById<CardView>(R.id.confirm)
        val ageinfo = dialog.findViewById<TextView>(R.id.textView6)

        ageinfo.text = age.toString()+" years old \n Born $dob"

        tv_confirm.setOnClickListener {
            UploadDAta(view)
            dialog.dismiss()
        }
        tv_edit.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_DOBFragment2_to_genderFragment2)
            dialog.dismiss()
        }

        dialog.show()
    }

    fun openCalender(view: View) {
        val textView = view as TextView
        if (dob.get().equals("")) {
            Toast.makeText(activity, "Please enter your Date of birth", Toast.LENGTH_SHORT).show()
        } else {
            showpopUp(textView)
        }
    }

     fun showpopUp(textView: TextView) {

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            activity,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in TextView
                getAge(year,month,dayOfMonth)
                textView.setText(" " + dayOfMonth + " - " + month + " - " + year)

                dateofbirth.put("dob",(dayOfMonth.toString()  + " - " + month + " , " + year))

            },
            year,
            month,
            day
        )
        dpd.show()

        /*  dialog.setContentView(R.layout.open_calander_layout)
          dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
          dialog.getWindow()!!.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
          val datePicker = dialog.findViewById<DatePicker>(R.id.date_Picker)

          dialog.setCancelable(true)
          val today = Calendar.getInstance()
          datePicker.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH)) { view, year, month, day ->
              val month = month + 1
              val msg = "You Selected: $day/$month/$year"
              textView.text = msg
          }

          dialog.show()*/

    }

    private fun getAge(year: Int, month: Int, day: Int): String? {
        val dob = Calendar.getInstance()
        val today = Calendar.getInstance()
        dob[year, month] = day
        var age = today[Calendar.YEAR] - dob[Calendar.YEAR]
        if (today[Calendar.DAY_OF_YEAR] < dob[Calendar.DAY_OF_YEAR]) {
            age--
        }

        val ageInt = age
        Age.value=ageInt
        dateofbirth.put("age",ageInt)
        Log.e("AGEEE",ageInt.toString())
        return ageInt.toString()
    }
}