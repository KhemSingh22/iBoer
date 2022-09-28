package com.example.ibeor.fragments

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ibeor.R
import com.example.ibeor.ViewModelFactries.GenderViewModelFact
import com.example.ibeor.databinding.FragmentGenderBinding
import com.example.ibeor.viewmodels.GenderViewModel


class GenderFragment : Fragment() {

    lateinit var binding: FragmentGenderBinding
    lateinit var viewmodel: GenderViewModel
    var check = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(com.example.ibeor.R.layout.fragment_gender, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGenderBinding.bind(view)
        viewmodel =
            ViewModelProvider(this@GenderFragment, GenderViewModelFact(requireActivity())).get(
                GenderViewModel::class.java
            )
        binding.genderViewmodel = viewmodel
        binding.lifecycleOwner = this@GenderFragment
        val img: Drawable = binding.tvMan.getContext().getResources().getDrawable(com.example.ibeor.R.drawable.right_tick)


        binding.tvMan.setOnClickListener {

            binding.tvTitle.text = "I am a "
            binding.tvMan!!.setBackgroundResource(R.drawable.gender_back)
            binding.tvMan!!.setTextColor(Color.WHITE)
            binding.tvMan.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null)

            binding.tvWoman!!.setBackgroundResource(R.drawable.textfield_back)
            binding.tvWoman!!.setTextColor(Color.BLACK)
        }

        binding.tvWoman.setOnClickListener {
            binding.tvTitle.text = "Seeking a "
            binding.tvWoman!!.setBackgroundResource(R.drawable.gender_back)
            binding.tvWoman!!.setTextColor(Color.WHITE)
            binding.tvWoman.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null)
            binding.tvMan!!.setBackgroundResource(R.drawable.textfield_back)
            binding.tvMan!!.setTextColor(Color.BLACK)
        }

    }

    /*  companion object {
          @JvmStatic
          @BindingAdapter("textColor")
          fun setTextColor(view: TextView, color: Int) {
              when(color){
                  R.color.green->{
                      view.setTextColor(Color.parseColor("#5abc6e"))
                  }
                  R.color.purple->{
                      view.setTextColor(Color.parseColor("#e62e30"))
                  }
              }
          }

      }*/
}
