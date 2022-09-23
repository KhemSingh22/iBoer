package com.example.ibeor.activities.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.ibeor.R
import com.example.ibeor.ViewModelFactries.YourEmailViewModelFact
import com.example.ibeor.databinding.FragmentEnterPhoneCodeBinding
import com.example.ibeor.databinding.FragmentYourMailBinding
import com.example.ibeor.viewmodels.YourEmailViewModel

class YourMailFragment : Fragment() {
    lateinit var binding: FragmentYourMailBinding
    lateinit var viewmodel : YourEmailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        binding = FragmentYourMailBinding.bind(inflater.inflate(R.layout.fragment_your_mail, container, false))
         viewmodel = ViewModelProvider(this@YourMailFragment,YourEmailViewModelFact(requireActivity())).get(YourEmailViewModel::class.java)
        binding.youremail = viewmodel
        return binding.root
    }


}