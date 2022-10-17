package com.example.ibeor.viewmodels

import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.ibeor.R
import com.example.ibeor.adapters.HobbiesAdapter
import com.example.ibeor.adapters.ViewPagerAdapter
import com.example.ibeor.dataclasses.HoiesData
import com.example.ibeor.dataclasses.ImagesModel
import com.example.ibeor.dataclasses.ViewPagerModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class OpenDetailsVM(var activity: FragmentActivity) : ViewModel() {
    var images: ArrayList<ViewPagerModel> = ArrayList()
    var hoiesData: ArrayList<HoiesData> = ArrayList()


    var name: MutableLiveData<String> = MutableLiveData()
    var Sharename: MutableLiveData<String> = MutableLiveData()
    var reportname: MutableLiveData<String> = MutableLiveData()
    var bloackname: MutableLiveData<String> = MutableLiveData()
    var privateMessage: MutableLiveData<String> = MutableLiveData()
    var age: MutableLiveData<String> = MutableLiveData()
    var firscountr: MutableLiveData<String> = MutableLiveData()
    var secCountr: MutableLiveData<String> = MutableLiveData()



    fun setRecyclerView(opRecyclerView: RecyclerView) {
        hoiesData.add(HoiesData(R.drawable.search_icon, "Girls"))
        hoiesData.add(HoiesData(R.drawable.panja, "Dogs"))
        hoiesData.add(HoiesData(R.drawable.child, "Want Someday"))
        hoiesData.add(HoiesData(R.drawable.pngaaa, "Capricorn"))
        hoiesData.add(HoiesData(R.drawable.temple, "Christian/Others"))
        hoiesData.add(HoiesData(R.drawable.fit, "Graduate/Degree"))
        hoiesData.add(HoiesData(R.drawable.search_icon, "Fit"))
        hoiesData.add(HoiesData(R.drawable.smoke, "Smoker"))
        hoiesData.add(HoiesData(R.drawable.drink, "Drink"))
        hoiesData.add(HoiesData(R.drawable.marijuana_icon, "Marijuana"))

        val layoutManager = GridLayoutManager(activity, 3)
        opRecyclerView.layoutManager = layoutManager
        val HobbiesAdapter = HobbiesAdapter(activity, hoiesData)
        opRecyclerView.adapter = HobbiesAdapter

    }

    fun setData(
        username: String?,
        userage: String?,
        firscountry: String?,
        secCountry: String?,
        images: ArrayList<ViewPagerModel>,
        opTabLayout: TabLayout,
        opViewpager: ViewPager2
    ) {
        name.value = username
        Sharename.value = "Share " + username + " Profile"
        reportname.value = "Report " + username
        privateMessage.value = "Sent " + username + " a \n private message"
        bloackname.value = "Block " + username
        age.value = userage
        firscountr.value = firscountry
        secCountr.value = secCountry

        setViewPager(opTabLayout,opViewpager,images)
        Log.e("OOOOO",images.size.toString() + " VVVVVV " + images.get(0).image0)
    }

    fun setViewPager(
        optab_layout: TabLayout,
        opViewpager: ViewPager2,
        images: ArrayList<ViewPagerModel> ) {

        val adapter = ViewPagerAdapter(activity, images)

        opViewpager.adapter = adapter

        TabLayoutMediator(
            optab_layout,
            opViewpager,
            TabLayoutMediator.TabConfigurationStrategy({ tab, position -> })
        ).attach()

        opViewpager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
        })

    }

}