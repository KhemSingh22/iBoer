package com.example.ibeor.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ibeor.databinding.WelcomeItemsBinding
import com.example.ibeor.dataclasses.WelComeRules

class WelcomeAdapter(var context: Context, var dataList: ArrayList<WelComeRules>) :
    RecyclerView.Adapter<WelcomeAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            WelcomeItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.setData(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolder(var binding: WelcomeItemsBinding) :RecyclerView.ViewHolder(binding.root) {
        fun setData(welComeRules: WelComeRules) {
            binding.beuourself.text = welComeRules.title
            binding.description.text = welComeRules.Description
        }
    }
}