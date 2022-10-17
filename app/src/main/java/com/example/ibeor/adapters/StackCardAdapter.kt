package com.example.ibeor.adapters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.ibeor.R
import com.example.ibeor.databinding.StackViewLayoutBinding
import com.example.ibeor.dataclasses.ImagesModel
import com.example.ibeor.dataclasses.UserPRofile
import com.example.ibeor.dataclasses.ViewPagerModel
import com.google.android.material.tabs.TabLayoutMediator

class StackCardAdapter(
    var requireActivity: FragmentActivity,
    var dataList: ArrayList<UserPRofile>,
    var view: View?
) :
    RecyclerView.Adapter<StackCardAdapter.StackViewHolder>() {


    class StackViewHolder(val binding: StackViewLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(
            requireActivity: FragmentActivity,
            img0: String?,
            img1: String?,
            img2: String?,
            img3: String?,
            img4: String?,
            img5: String?
        ) {

            var images : ArrayList<ViewPagerModel> = ArrayList()

            if(!(img0.equals("")) && !(img0.equals("null"))){
                images.add(ViewPagerModel(img0))
                }

            if(!(img1.equals("")) && !(img1.equals("null"))){
                images.add(ViewPagerModel(img1))
            }

            if(!(img2.equals("")) && !(img2.equals("null"))){
                images.add(ViewPagerModel(img2))
            }
            if(!(img3.equals("")) && !(img3.equals("null"))){
                images.add(ViewPagerModel(img3))
            }
            if(!(img4.equals("")) && !(img4.equals("null"))){
                images.add(ViewPagerModel(img4))
            }

            if(!(img5.equals("")) && !(img5.equals("null"))){
                images.add(ViewPagerModel(img5))
            }
           /* images.add(ViewPagerModel(img1))
            images.add(ViewPagerModel(img2))
            images.add(ViewPagerModel(img3))
            images.add(ViewPagerModel(img4))
            images.add(ViewPagerModel(img5))*/


            Log.e("CHECK++IMAGE== " ,images.toString())
            val adapter = ViewPagerAdapter(requireActivity,images)

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

     val data = dataList[position]
        Glide.with(requireActivity)
            .load(dataList[position].img0)
            .placeholder(R.drawable.img_two)
            .into(holder.binding.imageViewProfilePic)

        holder.setData(requireActivity ,dataList[position].img0,dataList[position].img1,dataList[position].img2,dataList[position].img3,dataList[position].img4,dataList[position].img5)

        holder.binding.userNameTv.text = (data.Firstname +" , " + data.age)

        holder.binding.openInfo.setOnClickListener {

            var images : ArrayList<ViewPagerModel> = ArrayList()

            if(!(dataList[position].img0.equals("")) && !(dataList[position].img0.equals("null"))){
                images.add(ViewPagerModel(dataList[position].img0))
            }

            if(!(dataList[position].img1.equals("")) && !(dataList[position].img1.equals("null"))){
                images.add(ViewPagerModel(dataList[position].img1))
            }

            if(!(dataList[position].img2.equals("")) && !(dataList[position].img2.equals("null"))){
                images.add(ViewPagerModel(dataList[position].img2))
            }
            if(!(dataList[position].img3.equals("")) && !(dataList[position].img3.equals("null"))){
                images.add(ViewPagerModel(dataList[position].img3))
            }
            if(!(dataList[position].img4.equals("")) && !(dataList[position].img4.equals("null"))){
                images.add(ViewPagerModel(dataList[position].img4))
            }

            if(!(dataList[position].img5.equals("")) && !(dataList[position].img5.equals("null"))){
                images.add(ViewPagerModel(dataList[position].img5))
            }

            val bundle = Bundle()
                  bundle.putString("userName",data.Firstname)
                  bundle.putString("userAge",data.age.toString())
                  bundle.putString("FirstCountry",data.firstCountry)
                  bundle.putString("SecCountry",data.SecCountry)
                  bundle.putSerializable("Images",images)
                  bundle.putParcelableArrayList("Images",images)

                  Log.e("SSSSVVVVSSS",images.size.toString())

              Navigation.findNavController( it as View).navigate(R.id.action_homeScreenFragment_to_openDetailsFragment,bundle)
        }

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}
