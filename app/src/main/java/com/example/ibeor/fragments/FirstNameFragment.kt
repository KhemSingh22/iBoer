package com.example.ibeor.activities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ibeor.R
import com.example.ibeor.ViewModelFactries.FirstNameViewModelFact
import com.example.ibeor.databinding.FragmentFirstNameBinding
import com.example.ibeor.viewmodels.FirstNameViewModel


class FirstNameFragment : Fragment() {

    lateinit var bindin: FragmentFirstNameBinding
    lateinit var viewmodel: FirstNameViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        bindin = FragmentFirstNameBinding.bind(
            inflater.inflate(
                R.layout.fragment_first_name,
                container,
                false
            )
        )
        viewmodel = ViewModelProvider(
            this@FirstNameFragment,
            FirstNameViewModelFact(requireActivity())
        ).get(FirstNameViewModel::class.java)
        bindin.firestnameviewmodmel = viewmodel
        bindin.lifecycleOwner = this@FirstNameFragment

        return bindin.root
    }

}