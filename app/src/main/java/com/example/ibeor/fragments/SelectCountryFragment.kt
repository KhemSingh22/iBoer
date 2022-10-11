package com.example.ibeor.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ibeor.R
import com.example.ibeor.ViewModelFactries.CountryViewModelFact
import com.example.ibeor.databinding.FragmentSelectCountryBinding
import com.example.ibeor.dataclasses.CountryList
import com.example.ibeor.viewmodels.CountryViewModel


class SelectCountryFragment : Fragment() {
    lateinit var binding: FragmentSelectCountryBinding
    lateinit var viewmodel  :CountryViewModel
    lateinit var countryData: ArrayList<CountryList>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectCountryBinding.bind(
            inflater.inflate(
                R.layout.fragment_select_country,
                container,
                false
            )
        )
        viewmodel = ViewModelProvider(this@SelectCountryFragment, CountryViewModelFact(requireActivity())).get(CountryViewModel::class.java)

        binding.lifecycleOwner = this@SelectCountryFragment
        countryData = ArrayList()

        binding.back.setOnClickListener {
            countryData.clear()
            requireActivity().onBackPressed()
        }

        viewmodel.setCodePicker(binding.ccp,binding.cardView,binding.ccp1)
        viewmodel.setCodePicker1(binding.ccp1)

        binding.btnContinu.setOnClickListener {
            viewmodel.uploadCountry(view!!,binding.countryProgress)
        }
        return binding.root
    }


  /*  private fun setSpinner() {

        binding.spinnerAlgorithm.setOnItemSelectedListener(this)
        binding.selectCountrySpin.setOnItemSelectedListener(this)

        countryData.add(CountryList(0, "Select Country"))
        countryData.add(CountryList(R.drawable.nigiera, "nigiera"))
        countryData.add(CountryList(R.drawable.us_flag, "United States"))
        countryData.add(CountryList(R.drawable.nigiera, "nigiera"))
        countryData.add(CountryList(R.drawable.us_flag, "United States"))

        var countryadapter = CountryListAdapter(requireActivity(), countryData)

        binding.spinnerAlgorithm.setAdapter(countryadapter)
        binding.selectCountrySpin.setAdapter(countryadapter)

    }
*/



}