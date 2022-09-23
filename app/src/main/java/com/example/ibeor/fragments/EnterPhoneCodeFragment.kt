package com.example.ibeor.activities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ibeor.R
import com.example.ibeor.ViewModelFactries.PhoneCodeViewModelFact
import com.example.ibeor.databinding.FragmentEnterPhoneCodeBinding
import com.example.ibeor.viewmodels.PhoneCodeViewModel


class EnterPhoneCodeFragment : Fragment() {

    lateinit var bindi: FragmentEnterPhoneCodeBinding
    lateinit var viewmodel: PhoneCodeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bindi = FragmentEnterPhoneCodeBinding.bind(
            inflater.inflate(
                R.layout.fragment_enter_phone_code,
                container,
                false
            )
        )
        bindi.apply {
            viewmodel = ViewModelProvider(
                this@EnterPhoneCodeFragment,
                PhoneCodeViewModelFact(requireActivity())
            ).get(PhoneCodeViewModel::class.java)
            bindi.phonecodeviewmodel = viewmodel
            bindi.lifecycleOwner = this@EnterPhoneCodeFragment
        }
        return bindi.root
    }

}