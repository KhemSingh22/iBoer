package com.example.ibeor.fragments
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.ibeor.R
import com.example.ibeor.ViewModelFactries.OpenDetailsViewModelFact
import com.example.ibeor.databinding.FragmentOpenDetailsBinding
import com.example.ibeor.viewmodels.OpenDetailsVM
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


class OpenDetailsFragment : Fragment() {
lateinit var binding: FragmentOpenDetailsBinding
lateinit var viewmodel : OpenDetailsVM
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentOpenDetailsBinding.bind(inflater.inflate(R.layout.fragment_open_details, container, false))
        binding.apply {
            viewmodel = ViewModelProvider(this@OpenDetailsFragment,OpenDetailsViewModelFact(requireActivity())).get(OpenDetailsVM::class.java)
            binding.openviewomdel = viewmodel
            binding.lifecycleOwner = this@OpenDetailsFragment
        }

        CoroutineScope(Dispatchers.Main).launch {
            viewmodel.setViewPager(binding.opTabLayout,binding.opViewpager)
        }
        CoroutineScope(Dispatchers.Main).launch {
            viewmodel.setRecyclerView(binding.OpRecyclerView)
        }

        return binding.root
    }

}