package com.example.ibeor.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ibeor.ViewModelFactries.PhoneCodeViewModelFact
import com.example.ibeor.databinding.FragmentEnterPhoneCodeBinding
import com.example.ibeor.viewmodels.PhoneCodeViewModel
import com.google.firebase.auth.FirebaseAuth


class EnterPhoneCodeFragment : Fragment() {
    lateinit var bindi: FragmentEnterPhoneCodeBinding
    lateinit var viewmodel : PhoneCodeViewModel
    private var mAuth: FirebaseAuth? = null
    private var mVerificationId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

       // bindi = FragmentEnterPhoneCodeBinding.bind(inflater.inflate(R.layout.fragment_enter_phone_code, container, false))
        bindi = FragmentEnterPhoneCodeBinding.inflate(layoutInflater)
        mAuth = FirebaseAuth.getInstance()
        viewmodel = ViewModelProvider(this@EnterPhoneCodeFragment,
            PhoneCodeViewModelFact(requireActivity(),bindi.otpView,bindi.idPBLoading))[PhoneCodeViewModel::class.java]

        getData(view)

        bindi.apply {
             bindi.phonecodeviewmodel = viewmodel
             bindi.lifecycleOwner = this@EnterPhoneCodeFragment
         }
        verifyOTP()
        return bindi?.root
    }

    private fun verifyOTP() {
        bindi.btnConti.setOnClickListener {

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindi?.phonecodeviewmodel = viewmodel
    }

    private fun getData(view: View?) {
        if (arguments != null) {
            val argsData = arguments?.getString("phoneNumber")
            Log.e("DSFSSSSS",arguments?.getString("phoneNumber").toString())
            viewmodel.sendVerificationCode(argsData,view)
//            viewmodel.readData(view)

        }
    }

}