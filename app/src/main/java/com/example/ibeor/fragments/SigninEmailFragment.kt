package com.example.ibeor.activities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ibeor.R
import com.example.ibeor.activities.ViewModelFactries.SigningWithEmailViewModelFact
import com.example.ibeor.databinding.FragmentSigninEmailBinding
import com.example.ibeor.viewmodels.SigningWithEmailViewModel


class SigninEmailFragment : Fragment() {
    lateinit var signinEmailBinding: FragmentSigninEmailBinding
    lateinit var signingWithEmailViewModel: SigningWithEmailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signin_email, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signinEmailBinding = FragmentSigninEmailBinding.bind(view)
        signinEmailBinding.apply {
            signingWithEmailViewModel = ViewModelProvider(
                this@SigninEmailFragment,
                SigningWithEmailViewModelFact(requireActivity())
            ).get(SigningWithEmailViewModel::class.java)
            signinEmailBinding.signingwithemail = signingWithEmailViewModel
            signinEmailBinding.lifecycleOwner = this@SigninEmailFragment
        }
    }

}