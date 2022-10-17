package com.example.ibeor.fragments

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.ibeor.R
import com.example.ibeor.ViewModelFactries.EditProfileViewModelFact
import com.example.ibeor.databinding.FragmentEditProfileBinding
import com.example.ibeor.viewmodels.EditProfileViewModel


class EditProfileFragment : Fragment() {

    lateinit var binding: FragmentEditProfileBinding
    lateinit var viewmodel: EditProfileViewModel
    private var i:Int=0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentEditProfileBinding.inflate(layoutInflater)

        binding.apply {
            viewmodel = ViewModelProvider(this@EditProfileFragment,EditProfileViewModelFact(requireActivity())).get(EditProfileViewModel::class.java)
            binding.editviewmodel = viewmodel
            binding.lifecycleOwner = this@EditProfileFragment
        }
        SetProgress()
        return binding.root
    }
    private fun SetProgress() {
        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                // set the limitations for the numeric
                // text under the progress bar
                if (i <= 100) {
                    binding.progressText.setText("" + i + "% COMPLETE")
                    binding.progressBar.setProgress(i)
                    i++
                    handler.postDelayed(this, 200)
                } else {
                    handler.removeCallbacks(this)
                }
            }
        }, 200)
    }
}