package com.example.ibeor.fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ibeor.R
import com.example.ibeor.ViewModelFactries.MyPhoneNumberViewModelFact
import com.example.ibeor.databinding.FragmentMyPhoneNumberBinding
import com.example.ibeor.viewmodels.MyPhoneNumberViewModel

class MyPhoneNumberFragment : Fragment() {
    lateinit var binding: FragmentMyPhoneNumberBinding
    lateinit var viewmomdel: MyPhoneNumberViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_phone_number, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMyPhoneNumberBinding.bind(view)
        binding.apply {
            viewmomdel = ViewModelProvider(this@MyPhoneNumberFragment, MyPhoneNumberViewModelFact(requireActivity())).get(MyPhoneNumberViewModel::class.java)
            binding.myphonenoviewmodel = viewmomdel
            binding.lifecycleOwner = this@MyPhoneNumberFragment
        }
    }

}