package com.example.ibeor.activities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ibeor.R
import com.example.ibeor.ViewModelFactries.AllowLocationViewModelFact
import com.example.ibeor.databinding.FragmentAllowLocationBinding
import com.example.ibeor.viewmodels.AllowLocationViewModel

class AllowLocationFragment : Fragment() {
    lateinit var binding: FragmentAllowLocationBinding
    lateinit var viewmodel: AllowLocationViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAllowLocationBinding.bind(
            inflater.inflate(
                R.layout.fragment_allow_location,
                container,
                false
            )
        )
        viewmodel = ViewModelProvider(
            this@AllowLocationFragment,
            AllowLocationViewModelFact(requireActivity())
        ).get(AllowLocationViewModel::class.java)
        binding.allowLocation = viewmodel
        binding.lifecycleOwner = this@AllowLocationFragment

        return binding.root
    }

}