package com.example.ibeor.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ibeor.R
import com.example.ibeor.ViewModelFactries.DobViewModelFact
import com.example.ibeor.databinding.FragmentDOBBinding
import com.example.ibeor.viewmodels.DobViewModel

class DOBFragment : Fragment() {
    lateinit var binding: FragmentDOBBinding
    lateinit var viewModel: DobViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding =
            FragmentDOBBinding.bind(inflater.inflate(R.layout.fragment_d_o_b, container, false))
        viewModel =
            ViewModelProvider(this@DOBFragment, DobViewModelFact(requireActivity())).get(
                DobViewModel::class.java
            )
        binding.dobViewmodel = viewModel
        binding.lifecycleOwner = requireActivity()
        return binding.root
    }
}