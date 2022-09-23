package com.example.ibeor.activities.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.ibeor.R
import com.example.ibeor.activities.ViewModelFactries.AccountRecoveryViewModelFact
import com.example.ibeor.databinding.FragmentAccountRecoeryBinding
import com.example.ibeor.viewmodels.AccountRecoveryViewModel


class AccountRecoeryFragment : Fragment() {
    lateinit var accountRecoeryBinding: FragmentAccountRecoeryBinding
    lateinit var accountRecoveryViewModel: AccountRecoveryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account_recoery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        accountRecoeryBinding = FragmentAccountRecoeryBinding.bind(view)
        accountRecoeryBinding.apply {
            accountviewmodel = ViewModelProvider(this@AccountRecoeryFragment,AccountRecoveryViewModelFact(requireActivity())).get(AccountRecoveryViewModel::class.java)
            accountRecoeryBinding.accountviewmodel = accountviewmodel
        }
    }
}