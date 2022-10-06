package com.example.ibeor.adapters

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ibeor.R
import com.example.ibeor.databinding.AddphotoLayoutBinding
import com.example.ibeor.dataclasses.AddPhoto
import com.google.android.material.bottomsheet.BottomSheetDialog


class AddPhotoAdapter(private val photos: ArrayList<AddPhoto>, var context: Context) : RecyclerView.Adapter<AddPhotoAdapter.AddPhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddPhotoViewHolder {
        val layInflater = LayoutInflater.from(parent.context)
        var viewHolder: RecyclerView.ViewHolder? = null

        viewHolder = AddPhotoViewHolder(AddphotoLayoutBinding.inflate(layInflater, parent, false))
        return viewHolder
    }

    override fun onBindViewHolder(holder: AddPhotoViewHolder, position: Int) {
        holder.setImage(context, photos[position].img)

        holder.bindining.itemImage.setOnClickListener {
            bottomPopup()
        }
    }

    private fun bottomPopup() {
        val bottomSheetDialog = BottomSheetDialog(context, R.style.NewDialog)
        bottomSheetDialog.setCancelable(true)
        bottomSheetDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog)
        val galary = bottomSheetDialog.findViewById<ImageView>(R.id.iv_galary)
        val camera = bottomSheetDialog.findViewById<ImageView>(R.id.iv_camera)

        galary!!.setOnClickListener {
            openCamera()
            bottomSheetDialog.dismiss()
        }
        camera!!.setOnClickListener {
            Toast.makeText(context, " CAMERA DONE", Toast.LENGTH_SHORT).show()
            bottomSheetDialog.dismiss()
        }
        bottomSheetDialog.show()
    }

    private fun openCamera() {

    }

    override fun getItemCount(): Int {
        return photos.size
    }

    class AddPhotoViewHolder(var bindining: AddphotoLayoutBinding) :
        RecyclerView.ViewHolder(bindining.root) {
        fun setImage(context: Context, img: Int) {

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


