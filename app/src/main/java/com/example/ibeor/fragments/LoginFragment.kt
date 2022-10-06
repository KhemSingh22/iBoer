package com.example.ibeor.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ibeor.R
import com.example.ibeor.activities.activities.viewmodels.LoginViewModel
import com.example.ibeor.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    lateinit var loginBinding: FragmentLoginBinding
    lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        loginBinding =
            FragmentLoginBinding.bind(inflater.inflate(R.layout.fragment_login, container, false))

        loginBinding.apply {
            loginViewModel = ViewModelProvider(this@LoginFragment).get(LoginViewModel::class.java)
            loginBinding.loginviewmodel = loginViewModel
            loginBinding.lifecycleOwner = this@LoginFragment
        }
        return loginBinding.root
    }


}