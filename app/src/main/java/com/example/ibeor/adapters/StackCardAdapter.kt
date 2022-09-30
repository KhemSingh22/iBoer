package com.example.ibeor.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.ibeor.R
import com.example.ibeor.databinding.StackViewLayoutBinding
import com.example.ibeor.dataclasses.Profile
import com.example.ibeor.dataclasses.ViewPagerModel

class StackCardAdapter(var requireActivity: FragmentActivity, var dataList: ArrayList<Profile>) :
    RecyclerView.Adapter<StackCardAdapter.StackViewHolder>() {


    class StackViewHolder(val binding: StackViewLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(requireActivity: FragmentActivity) {
            var images: ArrayList<ViewPagerModel> = ArrayList()

            images.add(ViewPagerModel(R.drawable.imgo))
            images.add(ViewPagerModel(R.drawable.imgt))
            images.add(ViewPagerModel(R.drawable.imgth))
            images.add(ViewPagerModel(R.drawable.imgf))
            images.add(ViewPagerModel(R.drawable.imgft))
            images.add(ViewPagerModel(R.drawable.imgs))
            images.add(ViewPagerModel(R.drawable.imgsv))

            val adapter = ViewPagerAdapter(requireActivity, images)
            binding.viewPager.adapter = adapter
//            binding.dot2.setViewPager(binding.viewPager)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = StackViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.stack_view_layout,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: StackViewHolder, position: Int) {

        holder.binding.imageViewProfilePic.setImageResource(dataList[position].profile_pic)
        holder.setData(requireActivity)

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}
