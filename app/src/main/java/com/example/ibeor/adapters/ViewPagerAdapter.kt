package com.example.ibeor.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ibeor.databinding.ViewPagerItemBinding
import com.example.ibeor.dataclasses.ViewPagerModel


class ViewPagerAdapter(
    var requireActivity: FragmentActivity,
    var dataList: ArrayList<ViewPagerModel>
) :
    RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        return ViewPagerViewHolder(
            ViewPagerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        holder.seTdata(requireActivity, dataList[position].image)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewPagerViewHolder(val binding: ViewPagerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun seTdata(requireActivity: FragmentActivity, image: Int) {
            Glide.with(requireActivity)
                .load(image)
                .into(binding.viewItemImage)

        }

    }


    /*override fun isViewFromObject(view: View, `object`: Any): Boolean {
  return view ==  `object`
 }

 override fun instantiateItem(container: ViewGroup, position: Int): Any {
     layoutInflater = requireActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
     val view: View = layoutInflater!!.inflate(R.layout.view_pager_item,null)
     val imageView = view.findViewById<ImageView>(R.id.view_item_image)

     Log.e("AAAAAAAAAAAAAAASSSSSS",dataList[position].image.toString())
     if(dataList[position].image!=null){
         Glide.with(requireActivity).load(dataList[position].image).into(imageView)
     }

     val viewPager = container as ViewPager
     viewPager.addView(view, 0)
     return view
 }

 override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
     val viewPager = container as ViewPager
     val view = `object` as View
     viewPager.removeView(view)
 }*/

}
