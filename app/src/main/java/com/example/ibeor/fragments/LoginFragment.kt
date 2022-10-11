package com.example.ibeor.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.ibeor.R
import com.example.ibeor.activities.ViewModelFactries.LoginViewmodelFact
import com.example.ibeor.activities.activities.viewmodels.LoginViewModel
import com.example.ibeor.databinding.FragmentLoginBinding
import com.example.ibeor.manager.SessionManager
import com.example.ibeor.utils.FirebaseUtils

class LoginFragment : Fragment() {
    lateinit var loginBinding: FragmentLoginBinding
    lateinit var loginViewModel: LoginViewModel
    private var uid: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        loginBinding =
            FragmentLoginBinding.bind(inflater.inflate(R.layout.fragment_login, container, false))

        loginBinding.apply {
            loginViewModel = ViewModelProvider(this@LoginFragment,LoginViewmodelFact(requireActivity())).get(LoginViewModel::class.java)
            loginBinding.loginviewmodel = loginViewModel
            loginBinding.lifecycleOwner = this@LoginFragment

        }

        return loginBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sessionManager = SessionManager(requireActivity())
        val loggedIn: Boolean = sessionManager.getlogin()

        if (loggedIn){
            val status = sessionManager.getStatus()
            val uid = sessionManager.getUid()
            Log.e("SDSDSD",uid.toString() + "XXXXXXXXXX===>>>>" + status)

            when(status.toString().toInt()){
                1 -> Navigation.findNavController(view).navigate(R.id.action_enterPhoneCodeFragment2_to_yourMailFragment2)
                2 -> Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_yourMailFragment2)
                3 -> Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_allowLocationFragment2)
                4 -> Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_genderFragment2)
                5 -> Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_addPhotoFragment2)
//              5 -> Navigation.findNavController(vi!!).navigate(R.id.action_enterPhoneCodeFragment2_to_DOBFragment2)
                6 -> Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_selectCountryFragment)
                7  -> Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_welComeFragment)
                8  -> Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_madeitFragment)
                10 -> Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_homeScreenFragment)
            }

        }
    }
}