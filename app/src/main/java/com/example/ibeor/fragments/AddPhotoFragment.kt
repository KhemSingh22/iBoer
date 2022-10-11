package com.example.ibeor.fragments

import android.R.attr
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.PorterDuff
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ibeor.R
import com.example.ibeor.ViewModelFactries.AddPhotoViewModelFact
import com.example.ibeor.adapters.AddPhotoAdapter
import com.example.ibeor.databinding.FragmentAddPhotoBinding
import com.example.ibeor.dataclasses.AddPhoto
import com.example.ibeor.listner.Listners
import com.example.ibeor.manager.SessionManager
import com.example.ibeor.utils.FirebaseUtils
import com.example.ibeor.viewmodels.AddPhotoViewModel
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.io.File
import java.util.*


class AddPhotoFragment : Fragment(), Listners {
    var photoList: ArrayList<AddPhoto> = ArrayList()
    private lateinit var binding: FragmentAddPhotoBinding
    lateinit var viewModel: AddPhotoViewModel
    lateinit var rvadapter: AddPhotoAdapter
    private var CameraPath: Uri? = null
    private var galaryPath: Uri? = null
    val REQUEST_CODE = 200
    val GALARY_REQUEST_CODE = 201
    private var imgCam: String? = ""
    lateinit var storage: FirebaseStorage
    lateinit var storageReference: StorageReference
    private val MY_CAMERA_PERMISSION_CODE = 100
    var photoFileName = "photo.jpg"
    var photoFile: File? = null

    private var pos: Int? = 0
    private lateinit var crossImg: ImageView
    private val REQUEST_PERMISSION = 100
    var count = 1

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
        storage = FirebaseStorage.getInstance()
        storageReference = storage.getReference()
        crossImg = ImageView(requireActivity())

        binding.back.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.btnSignIn.setOnClickListener {
            binding.addPhotoProgress.visibility = View.VISIBLE

            var filterPhotolist = rvadapter.photos.filter { it.img != "" } as ArrayList<AddPhoto>
            count = 0
            upLoad(filterPhotolist!!)


            val spinner = ProgressBar(context, null, attr.progressBarStyle)
            spinner.indeterminateDrawable.setColorFilter(-0x10000, PorterDuff.Mode.MULTIPLY)

        }
        return binding.root
    }

    private fun setRecycler() {
        val layoutManager = GridLayoutManager(activity, 3)

        photoList.add(AddPhoto(""))
        photoList.add(AddPhoto(""))
        photoList.add(AddPhoto(""))
        photoList.add(AddPhoto(""))
        photoList.add(AddPhoto(""))
        photoList.add(AddPhoto(""))

        binding.rvLayout.layoutManager = layoutManager
        rvadapter = AddPhotoAdapter(photoList, requireActivity(), this)
        binding.rvLayout.adapter = rvadapter
        binding.rvLayout.setHasFixedSize(true)
        rvadapter.notifyDataSetChanged()

    }

    override fun openCamera(camImagview: ImageView?, position1: Int, crossIv: ImageView) {
        pos = position1
        crossImg = crossIv

        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        photoFile = getPhotoFileUri(photoFileName)
//        val fileProvider = FileProvider.getUriForFile(requireActivity(), "com.codepath.fileprovider", photoFile!!)
//        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider)
        startActivityForResult(cameraIntent, REQUEST_CODE)


/*
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val f = File(Environment.getExternalStorageDirectory(), "temp.jpg")
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f))*/


    }

    override fun openGalary(galaryImg: ImageView, position: Int, crossIv: ImageView) {
        pos = position
        crossImg = crossIv

        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, GALARY_REQUEST_CODE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE && data != null) {
            Log.e("IMAGE22", data.toString())

            try {

                if (attr.data != null && data.data != null) {
                    Log.e("IMAGE22", attr.data.toString())
                    val image = data.extras?.get("data") as Bitmap
                    Log.e("IMAGE22", image.toString())

                    photoList.removeAt(pos!!)
                    photoList.add(pos!!, AddPhoto(image.toString()))
                    rvadapter.updateList(photoList)
                    rvadapter.notifyDataSetChanged()
                }

            } catch (e: Exception) {
                print(e)
            }

        } else if (resultCode == Activity.RESULT_OK && requestCode == GALARY_REQUEST_CODE && data != null) {

            val selectedImageURI: Uri = data.data!!

            photoList.removeAt(pos!!)
            photoList.add(pos!!, AddPhoto(selectedImageURI.toString()))
            rvadapter.updateList(photoList)
            rvadapter.notifyDataSetChanged()

        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        super.onRequestPermissionsResult(requestCode, permissions!!, grantResults)
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(requireActivity(), "camera permission granted", Toast.LENGTH_LONG)
                    .show()
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, REQUEST_CODE)
            } else {
                Toast.makeText(requireActivity(), "camera permission denied", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun upLoad(img: ArrayList<AddPhoto>) {
        val docData: MutableMap<String, Any> = HashMap()
        docData.put("userImage", img[count].img)

        val ref = storageReference!!.child("images/" + UUID.randomUUID().toString())
        ref.putFile(Uri.parse(img[count].img))
            .addOnSuccessListener(OnSuccessListener<UploadTask.TaskSnapshot> { taskSnapshot ->
                taskSnapshot.storage.downloadUrl.addOnSuccessListener {

                    val imageUrl=HashMap<String,Any>()
                    imageUrl.put("userImage_$count",it.toString())
                    imageUrl.put("statusOTP",6)

                    FirebaseUtils().fireStoreDatabase.collection("Users").document(FirebaseUtils().Uid)
                        .set(imageUrl, SetOptions.merge())
                        .addOnSuccessListener {
                            if(count==img.size-1)
                            {
                                binding.addPhotoProgress.visibility = View.GONE
                                Toast.makeText(requireActivity(), "Image Uploaded", Toast.LENGTH_SHORT).show()
                                Navigation.findNavController(view!!).navigate(R.id.action_addPhotoFragment2_to_selectCountryFragment)
                                val sessionManager =SessionManager(requireActivity())
                                val status =6
                                sessionManager.saVeStatus(status.toString())
                            }else {
                                count = count + 1
                                upLoad(img)
                            }
                        }
                        .addOnFailureListener {
                            Toast.makeText(context,"Failed ",Toast.LENGTH_SHORT).show()

                        }

                   /* if (count == img.size - 1) {


                    } else {
                        count = count + 1
                        upLoad(img)
                    }*/
                }
            })

            .addOnFailureListener(OnFailureListener { e ->
                print(e.message)
                Toast.makeText(
                    requireActivity(),
                    "Failed in Storage " + e.message,
                    Toast.LENGTH_SHORT
                ).show()

            })

    }
}