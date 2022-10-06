package com.example.ibeor.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ibeor.R
import com.example.ibeor.activities.ViewModelFactries.SigninWithFact
import com.example.ibeor.databinding.ActivitySignInWithBinding
import com.example.ibeor.databinding.FragmentSignInWithBinding
import com.example.ibeor.viewmodels.SignWithViewModel


class SignInWithFragment : Fragment() {
    lateinit var signInWithBinding : FragmentSignInWithBinding
    lateinit var signWithViewModel : SignWithViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        signInWithBinding = FragmentSignInWithBinding.bind(inflater.inflate(R.layout.fragment_sign_in_with, container, false))

        signInWithBinding.apply {
            signWithViewModel = ViewModelProvider(this@SignInWithFragment,SigninWithFact(requireActivity())).get(SignWithViewModel::class.java)
            signInWithBinding.signinviewmodel = signWithViewModel
            signInWithBinding.lifecycleOwner = this@SignInWithFragment

        }

        return signInWithBinding.root

    }


}