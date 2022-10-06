package com.example.ibeor.fragments

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ibeor.R
import com.example.ibeor.ViewModelFactries.GenderViewModelFact
import com.example.ibeor.databinding.FragmentGenderBinding
import com.example.ibeor.viewmodels.GenderViewModel


class GenderFragment : Fragment() {

    private var meCheck: String? = ""
    lateinit var binding: FragmentGenderBinding
    lateinit var viewmodel: GenderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        FragmentEnterPhoneCodeBinding.inflate(layoutInflater)
        binding = FragmentGenderBinding.inflate(layoutInflater)
        viewmodel =
            ViewModelProvider(this@GenderFragment, GenderViewModelFact(requireActivity())).get(
                GenderViewModel::class.java
            )
        binding.genderViewmodel = viewmodel
        binding.lifecycleOwner = this@GenderFragment
        val img: Drawable = binding.tvMan.getContext().getResources()
            .getDrawable(com.example.ibeor.R.drawable.right_tick)


        binding.tvMan.setOnClickListener {
            meCheck = "1"
            binding.tvTitle.text = "I am a "
            binding.tvMan!!.setBackgroundResource(R.drawable.gender_back)
            binding.tvMan!!.setTextColor(Color.WHITE)
            binding.tvMan.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null)

            binding.tvWoman!!.setBackgroundResource(R.drawable.textfield_back)
            binding.tvWoman!!.setTextColor(Color.BLACK)
        }

        binding.tvWoman.setOnClickListener {
            Log.e("SADDDDDD","HAAAAAAAAAAAAAAAAAAA")
            meCheck = "2"
            val title = "Seeking a "

            binding.tvTitle.text = title
            binding.tvWoman!!.setBackgroundResource(R.drawable.gender_back)
            binding.tvWoman!!.setTextColor(Color.WHITE)
            binding.tvWoman.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null)
            binding.tvMan!!.setBackgroundResource(R.drawable.textfield_back)
            binding.tvMan!!.setTextColor(Color.BLACK)

        }

        binding.btnContinue.setOnClickListener {
           Log.e("SADDDDDD","SDDS")
            if (!(meCheck.equals(""))) {
                Toast.makeText(requireActivity(), "Please select your gender ", Toast.LENGTH_SHORT).show()
            } else {
                viewmodel.uploadData(view,meCheck)
            }
        }

        return binding.root
    }




}
