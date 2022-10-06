package com.example.ibeor.adapters
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ibeor.databinding.HobbyItemsBinding
import com.example.ibeor.dataclasses.HoiesData

class HobbiesAdapter(var activity: FragmentActivity,var hoiesData: ArrayList<HoiesData>)
    : RecyclerView.Adapter<HobbiesAdapter.HobbViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HobbViewHolder {

        return HobbViewHolder(HobbyItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return hoiesData.size
    }


    override fun onBindViewHolder(holder: HobbViewHolder, position: Int) {

    holder.binding.titleTv.text = hoiesData[position].hobby
        Glide
            .with(activity)
            .load(hoiesData[position].image)
            .into(holder.binding.imgIcon)

    }

    class HobbViewHolder(var binding : HobbyItemsBinding) : RecyclerView.ViewHolder(binding.root) {

    }

}
