package com.example.ibeor.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.ibeor.R
import com.example.ibeor.ViewModelFactries.AddPhotoViewModelFact
import com.example.ibeor.adapters.AddPhotoAdapter
import com.example.ibeor.databinding.FragmentAddPhotoBinding
import com.example.ibeor.dataclasses.AddPhoto
import com.example.ibeor.listner.Listners
import com.example.ibeor.viewmodels.AddPhotoViewModel


class AddPhotoFragment : Fragment(), Listners {
    var photoList: ArrayList<AddPhoto> = ArrayList()
    private lateinit var binding: FragmentAddPhotoBinding
    lateinit var viewModel: AddPhotoViewModel
    lateinit var rvadapter: AddPhotoAdapter
     private var CameraPath : Uri? = null
     private var galaryPath : Uri? = null
    val REQUEST_CODE = 200
    val GALARY_REQUEST_CODE = 201
    private var imgCam: String? = ""
    private var pos: Int? = 0
    private lateinit var crossImg: ImageView
    private val REQUEST_PERMISSION = 100

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddPhotoBinding.bind(
            inflater.inflate(
                R.layout.fragment_add_photo,
                container,
                false
            )
        )
        viewModel =
            ViewModelProvider(this@AddPhotoFragment, AddPhotoViewModelFact(requireActivity())).get(
                AddPhotoViewModel::class.java
            )
        binding.addPhotoVM = viewModel
        binding.lifecycleOwner = requireActivity()
        photoList.clear()
        setRecycler()
        crossImg = ImageView(requireActivity())
        binding.back.setOnClickListener {
            requireActivity().onBackPressed()
        }
        return binding.root
    }

    private fun setRecycler() {
        val layoutManager = GridLayoutManager(activity, 3)

        photoList.add(AddPhoto(R.drawable.img_two.toString()))
        photoList.add(AddPhoto(R.drawable.img_con.toString()))
        photoList.add(AddPhoto(R.drawable.img_con.toString()))
        photoList.add(AddPhoto(R.drawable.img_con.toString()))
        photoList.add(AddPhoto(R.drawable.img_con.toString()))
        photoList.add(AddPhoto(R.drawable.img_con.toString()))


        binding.rvLayout.layoutManager = layoutManager
        rvadapter = AddPhotoAdapter(photoList, requireActivity(), this)
        binding.rvLayout.adapter = rvadapter
        binding.rvLayout.setHasFixedSize(true)
        rvadapter.notifyDataSetChanged()

    }

    override fun openCamera(camImagview: ImageView?, position1: Int, crossIv: ImageView) {
        pos = position1
        crossImg = crossIv
        if (getCameraPermission(context!!)) {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, REQUEST_CODE)
        }

    }

    override fun openGalary(galaryImg: ImageView, position: Int, crossIv: ImageView) {
        pos = position
        crossImg = crossIv
        if (getCameraPermission(context!!)) {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, GALARY_REQUEST_CODE)

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE && data != null) {
            try {
                val image :Uri = data.extras?.get("data") as Uri
                photoList.removeAt(pos!!)
                photoList.add(pos!!,AddPhoto(image.toString()))
                rvadapter.updateList(photoList)
                rvadapter.notifyDataSetChanged()
            }catch (e:Exception){
                print(e)
            }

        } else if (resultCode == Activity.RESULT_OK && requestCode == GALARY_REQUEST_CODE && data != null) {

            val selectedImageURI: Uri = data.data!!

            photoList.removeAt(pos!!)
            photoList.add(pos!!,AddPhoto(selectedImageURI.toString()))
            rvadapter.updateList(photoList)
            rvadapter.notifyDataSetChanged()

            Log.e("IMAGE22", galaryPath.toString())

        }

    }

    private fun getCameraPermission(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }
}