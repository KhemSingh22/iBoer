package com.example.ibeor.fragments

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.ibeor.R
import com.example.ibeor.ViewModelFactries.HomeScreenViewModelFact
import com.example.ibeor.adapters.StackCardAdapter
import com.example.ibeor.databinding.FragmentHomeScreenBinding
import com.example.ibeor.dataclasses.Profile
import com.example.ibeor.viewmodels.HomeScreenViewModel
import com.yuyakaido.android.cardstackview.*


class HomeScreenFragment : Fragment(), CardStackListener {
    lateinit var binding: FragmentHomeScreenBinding
    lateinit var viewmodel: HomeScreenViewModel
    private var TotalWidth = 0
    private var TotalHight = 0
    var i = 0
    lateinit var dataList: ArrayList<Profile>
    lateinit var adapter : StackCardAdapter
    private lateinit var layoutManager: CardStackLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentHomeScreenBinding.bind(
            inflater.inflate(R.layout.fragment_home_screen, container, false)
        )

        dataList = ArrayList()

        viewmodel = ViewModelProvider(
            this@HomeScreenFragment,
            HomeScreenViewModelFact(requireActivity())
        ).get(HomeScreenViewModel::class.java)
        binding.homeVM = viewmodel
        binding.lifecycleOwner = this@HomeScreenFragment

        SetProgress()
        SetStackView()
        getScreenWidth()
        getScreenHeight()
        layoutHigth()
        return binding.root
    }

    private fun setlautoutHight(cardHight: Int) {

        val params: ViewGroup.LayoutParams = binding.stackView.layoutParams
// Changes the height and width to the specified *pixels*
// Changes the height and width to the specified *pixels*
        params.height = cardHight
        binding.stackView.layoutParams = params
    }


    fun layoutHigth(){
    val viewTreeObserver: ViewTreeObserver = binding.consLy.getViewTreeObserver()
    if (viewTreeObserver.isAlive) {
        viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.consLy.getViewTreeObserver().removeOnGlobalLayoutListener(this)

              val layoutHight  = binding.consLy.getHeight() * 2
                actualHight(layoutHight)
                val viewWeight: Int = binding.consLy.getWidth()
                Log.e("Hight===",layoutHight.toString()+"width==" + viewWeight)
            }
        })
    }

//    val viewTreeObserver1 : ViewTreeObserver = binding.consLay2.getViewTreeObserver()
//    if (viewTreeObserver1.isAlive) {
//        viewTreeObserver1.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
//            override fun onGlobalLayout() {
//                binding.consLay2.getViewTreeObserver().removeOnGlobalLayoutListener(this)
//                layoutHight  = binding.consLay2.getHeight()
//                val viewWeight: Int = binding.consLay2.getWidth()
//                Log.e("Hight===",layoutHight.toString()+"width==" + viewWeight)
//            }
//        })
//    }

}

    private fun actualHight(layoutHight: Int) {
        val hight = layoutHight - 20
        val cardHight = TotalHight- hight
        setlautoutHight(cardHight)
        Log.e("CArdHight1" ,cardHight.toString())

    }

    fun getScreenWidth(): Int {
         TotalWidth = Resources.getSystem().displayMetrics.widthPixels
        Log.e("MMMMMMBN",TotalWidth.toString())
        return TotalWidth
    }

    fun getScreenHeight(): Int {
         TotalHight = Resources.getSystem().displayMetrics.heightPixels
        Log.e("SDSDSDS",TotalHight.toString())

        return TotalHight
    }

    private fun SetStackView() {

        dataList.add(Profile(0, 0, 0, "demo", R.drawable.img_two))
        dataList.add(Profile(0, 0, 0, "demo", R.drawable.img_con))
        dataList.add(Profile(0, 0, 0, "demo", R.drawable.img_two))
        dataList.add(Profile(0, 0, 0, "demo", R.drawable.imggggg))

        layoutManager = CardStackLayoutManager(context, this).apply {
            setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
            setOverlayInterpolator(LinearInterpolator())
        }


        val setting = SwipeAnimationSetting.Builder()
            .setDirection(Direction.Right)
            .setDuration(Duration.Normal.duration)
            .setInterpolator(AccelerateInterpolator())
            .build()

        binding.stackView.layoutManager =CardStackLayoutManager(context,this)
        layoutManager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
        layoutManager.setSwipeAnimationSetting(setting)

        binding.stackView.layoutManager = layoutManager
        binding.stackView.swipe()
        layoutManager.setCanScrollHorizontal(true)
        layoutManager.setCanScrollVertical(false)

        adapter = StackCardAdapter(requireActivity(),dataList,view)
        binding.stackView.adapter = adapter
        binding.stackView.itemAnimator.apply {

            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }

        }

    }

    private fun SetProgress() {
        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                // set the limitations for the numeric
                // text under the progress bar
                if (i <= 100) {
                    binding.progressText.setText("" + i + "% COMPLETE")
                    binding.progressBar.setProgress(i)
                    i++
                    handler.postDelayed(this, 200)
                } else {
                    handler.removeCallbacks(this)
                }
            }
        }, 200)
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {
        Log.e("DFD", "dcsds")
    }

    override fun onCardSwiped(direction: Direction?) {
        Log.e("DFD", "dcsds")
    }

    override fun onCardRewound() {

    }

    override fun onCardCanceled() {
        Log.e("DFD", "dcsds")
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCardAppeared(view: View?, position: Int) {

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCardDisappeared(view : View?, position: Int) {
        if (position==dataList.size-1){
            adapter = StackCardAdapter(requireActivity(), dataList, view)
            binding.stackView.adapter = adapter
            adapter.notifyDataSetChanged()
        }

        Log.e("WORKKKFFFF","POSITION" + position.toString() + "LISTPOSITION==" + dataList.size)    }
}