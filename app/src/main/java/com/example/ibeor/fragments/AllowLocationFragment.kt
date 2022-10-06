package com.example.ibeor.activities.fragments

import android.location.LocationRequest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ibeor.R
import com.example.ibeor.ViewModelFactries.AllowLocationViewModelFact
import com.example.ibeor.databinding.FragmentAllowLocationBinding
import com.example.ibeor.viewmodels.AllowLocationViewModel
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.ResultCallback
import com.google.android.gms.location.LocationServices
import java.net.URI.create


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