package com.example.ibeor.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ibeor.R
import com.example.ibeor.ViewModelFactries.MadeViewModelFact
import com.example.ibeor.databinding.FragmentMadeitBinding
import com.example.ibeor.viewmodels.MadeViewModel


class MadeitFragment : Fragment() {
    lateinit var bindin : FragmentMadeitBinding
    lateinit var vm: MadeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bindin = FragmentMadeitBinding.bind(
            inflater.inflate(
                R.layout.fragment_madeit,
                container,
                false
            )
        )

        bindin.apply {

            vm = ViewModelProvider(this@MadeitFragment, MadeViewModelFact(requireActivity())).get(MadeViewModel::class.java)
            bindin.madeVM = vm
            bindin.lifecycleOwner = requireActivity()

            bindin.btnConti.setOnClickListener {
                vm.nextScreen(view,bindin.madeProgress)
            }
        }
        return bindin.root
    }

}