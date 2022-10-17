package com.example.ibeor.fragments

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ibeor.R
import com.example.ibeor.ViewModelFactries.OpenDetailsViewModelFact
import com.example.ibeor.databinding.FragmentOpenDetailsBinding
import com.example.ibeor.dataclasses.ImagesModel
import com.example.ibeor.dataclasses.ViewPagerModel
import com.example.ibeor.viewmodels.OpenDetailsVM
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class OpenDetailsFragment : Fragment() {
    lateinit var binding: FragmentOpenDetailsBinding
    lateinit var viewmodel: OpenDetailsVM
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOpenDetailsBinding.bind(
            inflater.inflate(
                R.layout.fragment_open_details,
                container,
                false
            )
        )
        binding.apply {
            viewmodel = ViewModelProvider(
                this@OpenDetailsFragment,
                OpenDetailsViewModelFact(requireActivity())
            ).get(OpenDetailsVM::class.java)
            binding.openviewomdel = viewmodel
            binding.lifecycleOwner = this@OpenDetailsFragment
        }

        CoroutineScope(Dispatchers.Main).launch {
            viewmodel.setRecyclerView(binding.OpRecyclerView)
        }

        getUserdata()
        setbackground()
        return binding.root
    }

    fun getUserdata() {
        var images : ArrayList<ViewPagerModel> = ArrayList()

        val username = arguments?.getString("userName")
        val userage = arguments?.getString("userAge")
        val firscountry = arguments?.getString("FirstCountry")
        val secCountry = arguments?.getString("SecCountry")
        images = arguments?.getParcelableArrayList("Images")!!

        viewmodel.setData(username,userage,firscountry,secCountry,images,binding.opTabLayout, binding.opViewpager)
        Log.e("SSSSSSQQQQ", images.size.toString())

    }

    private fun setbackground() {
//        binding.chipSearchIcon.chipBackgroundColor = ColorStateList.valueOf(R.color.white)
        binding.chipSearchIcon.chipBackgroundColor = getColorStateList(requireActivity(),R.color.white)
        binding.chipPanja.chipBackgroundColor = getColorStateList(requireActivity(),R.color.white)
        binding.chipPngaaa.chipBackgroundColor = getColorStateList(requireActivity(),R.color.white)
        binding.chipSmoke.chipBackgroundColor = getColorStateList(requireActivity(),R.color.white)
        binding.chipDrink.chipBackgroundColor = getColorStateList(requireActivity(),R.color.white)
        binding.chipCall.chipBackgroundColor = getColorStateList(requireActivity(),R.color.white)
        binding.chipMessage.chipBackgroundColor = getColorStateList(requireActivity(),R.color.white)
        binding.chipAlarm.chipBackgroundColor = getColorStateList(requireActivity(),R.color.white)
        binding.chipTemple.chipBackgroundColor = getColorStateList(requireActivity(),R.color.white)
        binding.chipPhdGradu.chipBackgroundColor = getColorStateList(requireActivity(),R.color.white)
        binding.chipMusic .chipBackgroundColor = getColorStateList(requireActivity(),R.color.white)
        binding.chipMarijuana  .chipBackgroundColor = getColorStateList(requireActivity(),R.color.white)

    }


}