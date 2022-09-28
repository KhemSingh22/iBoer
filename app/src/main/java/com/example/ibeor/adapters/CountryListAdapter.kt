package com.example.ibeor.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ibeor.R
import com.example.ibeor.databinding.CountryListLayoutBinding
import com.example.ibeor.dataclasses.CountryList


class CountryListAdapter(var context: Context, var dataList: ArrayList<CountryList>) :
    BaseAdapter() {

    val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val vh: ItemRowHolder

        if (convertView == null) {
            view = mInflater.inflate(R.layout.country_list_layout, parent, false)
            vh = ItemRowHolder(view)
            view?.tag = vh
        } else {
            view = convertView
            vh = view.tag as ItemRowHolder
        }

        vh.label.text = dataList.get(position).name
        Glide
            .with(context)
            .load(dataList.get(position).image)
            .centerCrop()
            .into(vh.image)

        return view

    }

//    class ViewHolder(var bindng: CountryListLayoutBinding) : RecyclerView.ViewHolder(bindng.root) {
//        fun setdata(context: Context, countryList: CountryList) {
//            bindng.countryName.text = countryList.name
//            Glide
//                .with(context)
//                .load(countryList.image)
//                .centerCrop()
//                .placeholder(R.drawable.ic_launcher_foreground)
//                .into(bindng.countryImage)
//        }
//    }

/*    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var viewHolder: RecyclerView.ViewHolder? = null
        viewHolder = ViewHolder(
            CountryListLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setdata(context, dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }*/

    override fun getCount(): Int {
        return dataList.size
    }

    override fun getItem(p0: Int): Any? {
        return null
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    private class ItemRowHolder(row: View?) {

        val label: TextView
        var image : ImageView

        init {
            this.label = row?.findViewById(R.id.country_name) as TextView
            this.image = row?.findViewById(R.id.country_image) as ImageView
        }
    }

}