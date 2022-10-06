package com.example.ibeor.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ibeor.R
import com.example.ibeor.ViewModelFactries.AddPhotoViewModelFact
import com.example.ibeor.adapters.AddPhotoAdapter
import com.example.ibeor.databinding.FragmentAddPhotoBinding
import com.example.ibeor.dataclasses.AddPhoto
import com.example.ibeor.viewmodels.AddPhotoViewModel


class AddPhotoFragment : Fragment() {
    var photoList: ArrayList<AddPhoto> = ArrayList()
    private lateinit var binding: FragmentAddPhotoBinding
    lateinit var viewModel: AddPhotoViewModel

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddPhotoBinding.bind(inflater.inflate(R.layout.fragment_add_photo, container, false))
        viewModel =
            ViewModelProvider(this@AddPhotoFragment, AddPhotoViewModelFact(requireActivity())).get(
                AddPhotoViewModel::class.java
            )
        binding.addPhotoVM = viewModel
        binding.lifecycleOwner = requireActivity()
        photoList.clear()
        setRecycler()
        binding.back.setOnClickListener {
            requireActivity().onBackPressed()
        }
        return binding.root
    }

    private fun setRecycler() {
        val layoutManager = GridLayoutManager(activity, 3)

        photoList.add(AddPhoto(R.drawable.img_two))
        photoList.add(AddPhoto(R.drawable.img_con))
        photoList.add(AddPhoto(0))
        photoList.add(AddPhoto(0))
        photoList.add(AddPhoto(0))
        photoList.add(AddPhoto(0))

        binding.rvLayout.layoutManager = layoutManager
        val rvadapter = AddPhotoAdapter(photoList, requireActivity())
        binding.rvLayout.adapter = rvadapter
        binding.rvLayout.setHasFixedSize(true)
        rvadapter.notifyDataSetChanged()

    }

}