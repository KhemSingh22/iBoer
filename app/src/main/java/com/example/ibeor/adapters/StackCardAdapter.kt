package com.example.ibeor.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.ibeor.R
import com.example.ibeor.databinding.StackViewLayoutBinding
import com.example.ibeor.dataclasses.Profile
import com.example.ibeor.dataclasses.ViewPagerModel
import com.google.android.material.tabs.TabLayoutMediator

class StackCardAdapter(
    var requireActivity: FragmentActivity,
    var dataList: ArrayList<Profile>,
    var view: View?
) :
    RecyclerView.Adapter<StackCardAdapter.StackViewHolder>() {


    class StackViewHolder(val binding: StackViewLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(requireActivity: FragmentActivity) {
            var images: ArrayList<ViewPagerModel> = ArrayList()

            images.add(ViewPagerModel(R.drawable.imgo))
            images.add(ViewPagerModel(R.drawable.imgt))
            images.add(ViewPagerModel(R.drawable.imgth))


            val adapter = ViewPagerAdapter(requireActivity, images)

            binding.viewPager.adapter = adapter

            TabLayoutMediator(
                binding.tabLayout,
                binding.viewPager,
                TabLayoutMediator.TabConfigurationStrategy({ tab, position ->

                })
            ).attach()

            binding.viewPager.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)

                }
            })

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
        holder.binding.openInfo.setOnClickListener {
//              Navigation.findNavController( it as View).navigate(R.id.action_homeScreenFragment_to_openDetailsFragment)
        }

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}
