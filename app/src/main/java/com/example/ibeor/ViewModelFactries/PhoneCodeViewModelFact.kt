package com.example.ibeor.ViewModelFactries

import `in`.aabhasjindal.otptextview.OtpTextView
import android.app.Activity
import android.widget.ProgressBar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ibeor.viewmodels.PhoneCodeViewModel

class PhoneCodeViewModelFact(
    private var activity: Activity,
    private var otpView: OtpTextView,
    private var idPBLoading: ProgressBar
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(PhoneCodeViewModel::class.java!!)) {
            PhoneCodeViewModel(this.activity , this.otpView , this.idPBLoading) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}