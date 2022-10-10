package com.example.ibeor.adapters

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ibeor.R
import com.example.ibeor.databinding.AddphotoLayoutBinding
import com.example.ibeor.dataclasses.AddPhoto
import com.example.ibeor.listner.Listners
import com.google.android.material.bottomsheet.BottomSheetDialog


class AddPhotoAdapter(
    private var photos: ArrayList<AddPhoto>,
    var context: Context,
    var listners: Listners
) : RecyclerView.Adapter<AddPhotoAdapter.AddPhotoViewHolder>() {

    fun updateList(picturesList: ArrayList<AddPhoto>) {
        photos = picturesList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddPhotoViewHolder {
        val layInflater = LayoutInflater.from(parent.context)
        var viewHolder: RecyclerView.ViewHolder? = null

        viewHolder = AddPhotoViewHolder(AddphotoLayoutBinding.inflate(layInflater, parent, false))
        return viewHolder
    }

    override fun onBindViewHolder(holder: AddPhotoViewHolder, position: Int) {

        holder.setImage(context, photos[position].img)

        holder.bindining.itemImage.setOnClickListener {
            bottomPopup(position, holder.bindining.crossIv)
        }

    }

    fun bottomPopup(position: Int, crossIv: ImageView) {

        val bottomSheetDialog = BottomSheetDialog(context, R.style.NewDialog)
        bottomSheetDialog.setCancelable(false)
        bottomSheetDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog)
        val galary = bottomSheetDialog.findViewById<ImageView>(R.id.iv_galary)
        val camera = bottomSheetDialog.findViewById<ImageView>(R.id.iv_camera)

        galary!!.setOnClickListener {
            listners.openGalary(galary, position, crossIv)
            bottomSheetDialog.dismiss()
        }

        camera!!.setOnClickListener {
            val camImage = listners.openCamera(camera, position, crossIv)
            Log.e("AAAAAAA", camImage.toString())
            bottomSheetDialog.dismiss()

        }

        bottomSheetDialog.show()
    }

    override fun getItemCount(): Int {
        return 6
    }

    class AddPhotoViewHolder(var bindining: AddphotoLayoutBinding) :
        RecyclerView.ViewHolder(bindining.root) {

        fun setImage(context: Context, img: String) {

            Log.e("SSSSS", img)

            Glide
                .with(context)
                .load(img)
                .centerCrop()
                .placeholder(R.drawable.add_icons)
                .into(bindining.itemImage)
            bindining.crossIv.visibility = View.GONE

        }

    }

}


