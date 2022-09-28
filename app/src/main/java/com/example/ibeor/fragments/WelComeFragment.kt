package com.example.ibeor.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ibeor.R
import com.example.ibeor.ViewModelFactries.WelComeViewModelFact
import com.example.ibeor.adapters.AddPhotoAdapter
import com.example.ibeor.adapters.WelcomeAdapter
import com.example.ibeor.databinding.FragmentWelComeBinding
import com.example.ibeor.dataclasses.WelComeRules
import com.example.ibeor.viewmodels.WelComeViewModel


class WelComeFragment : Fragment() {
    lateinit var binding: FragmentWelComeBinding
    lateinit var viewmodel: WelComeViewModel
    lateinit var dataList: ArrayList<WelComeRules>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWelComeBinding.bind(
            inflater.inflate(
                R.layout.fragment_wel_come,
                container,
                false
            )
        )
        dataList = ArrayList()
        viewmodel = ViewModelProvider(
            this,
            WelComeViewModelFact(requireActivity())
        ).get(WelComeViewModel::class.java)
        binding.welomeviewmodel = viewmodel
        binding.lifecycleOwner = this@WelComeFragment

        setRecyclerView()
        return binding.root

    }

    private fun setRecyclerView() {
        val layoutManager = LinearLayoutManager(activity)

        dataList.add(WelComeRules("Be yourself ","Make sure your photos, age and bio are true who you are. "))
        dataList.add(WelComeRules("Stay safe","Don't be too quick to give out personal information Date safely."))
        dataList.add(WelComeRules("Play it cool","Respect others and treat them as you would like to be treated."))
        dataList.add(WelComeRules("Be proactive ","Alwaysreport bad behavior."))

        binding.rvHouserules.layoutManager = layoutManager
        val rvadapter = WelcomeAdapter( requireActivity(),dataList)
        binding.rvHouserules.adapter = rvadapter


    }

}