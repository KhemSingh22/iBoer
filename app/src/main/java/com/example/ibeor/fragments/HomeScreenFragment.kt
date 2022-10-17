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
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DefaultItemAnimator
import com.bumptech.glide.Glide
import com.example.ibeor.R
import com.example.ibeor.ViewModelFactries.HomeScreenViewModelFact
import com.example.ibeor.adapters.StackCardAdapter
import com.example.ibeor.databinding.FragmentHomeScreenBinding
import com.example.ibeor.dataclasses.UserPRofile
import com.example.ibeor.manager.SessionManager
import com.example.ibeor.utils.FirebaseUtils
import com.example.ibeor.viewmodels.HomeScreenViewModel
import com.google.firebase.firestore.SetOptions
import com.yuyakaido.android.cardstackview.*


class HomeScreenFragment : Fragment(), CardStackListener {
    lateinit var binding: FragmentHomeScreenBinding
    lateinit var viewmodel: HomeScreenViewModel
    private var TotalWidth = 0
    private var TotalHight = 0
    private var pos = 0
    lateinit var user: MutableLiveData<ArrayList<UserPRofile>>
    lateinit var datalist: ArrayList<UserPRofile>
    lateinit var Profile: ArrayList<UserPRofile>

    var i = 0
    lateinit var adapter: StackCardAdapter
    private lateinit var layoutManager: CardStackLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentHomeScreenBinding.bind(
            inflater.inflate(R.layout.fragment_home_screen, container, false)
        )

        datalist = ArrayList()
        user = MutableLiveData()
        viewmodel = ViewModelProvider(
            this@HomeScreenFragment,
            HomeScreenViewModelFact(requireActivity())
        ).get(HomeScreenViewModel::class.java)
        binding.homeVM = viewmodel
        binding.lifecycleOwner = this@HomeScreenFragment
        datalist = ArrayList()
        Profile = ArrayList()

        readData()
        SetProgress()
        getScreenWidth()
        getScreenHeight()
        layoutHigth()
        binding.photosIv.setOnClickListener {
            adapter = StackCardAdapter(requireActivity(), datalist, view)
            binding.stackView.adapter = adapter
            adapter.notifyDataSetChanged()
        }
        return binding.root
    }


    fun readData() {
        FirebaseUtils().fireStoreDatabase.collection("Users")
            .get()
            .addOnSuccessListener { DocumentSnapshot ->

                Log.e("SSSAA", DocumentSnapshot.documents.toString())

                var user = DocumentSnapshot

                for (snap in user!!.documents.iterator()) {
                    val firstName = snap!!.get("firstName")
                    val dob = snap!!.get("dob")
                    val email = snap!!.get("email")
                    val age = snap!!.get("age")
                    val gender = snap!!.get("gender")
                    val UserID = snap!!.get("UserID")
                    val PhoneNumber = snap!!.get("PhoneNumber")
                    val statusOTP = snap!!.get("statusOTP")
                    val UserFirstCountry = snap!!.get("UserFirstCountry")
                    val UserFirstCountryCode = snap!!.get("UserFirstCountryCode")
                    val UserSecCountry = snap!!.get("UserSecCountry")
                    val UserSecCountryCode = snap!!.get("UserSecCountryCode")
                    val userImage_0 = snap!!.get("userImage_0")
                    var userImage_1 = ""
                    snap!!.get("userImage_1").let {
                        userImage_1 = snap.get("userImage_1").toString()
                    }
                    var userImage_2 = ""
                    snap!!.get("userImage_2").let {
                        userImage_2 = snap.get("userImage_2").toString()
                    }

                    var userImage_3 = ""
                    snap!!.get("userImage_3").let {
                        userImage_3 = snap.get("userImage_3").toString()
                    }

                    var userImage_4 = ""
                    snap!!.get("userImage_4").let {
                        userImage_4 = snap.get("userImage_4").toString()
                    }

                    var userImage_5 = ""
                    snap!!.get("userImage_5").let {
                        userImage_5 = snap.get("userImage_5").toString()
                    }
                    //   val userImage_1 = snap!!.get("userImage_1")
                    //  val userImage_2 = snap!!.get("userImage_2")
                    //val userImage_3 = snap!!.get("userImage_3")
                    //  val userImage_4 = snap!!.get("userImage_4")
                    // val userImage_5 = snap!!.get("userImage_5")

                    val sessionManager = SessionManager(requireActivity())
                    val UID = sessionManager.getUid()

                    Log.e("CVLLLL", UID.toString() + "DDDDDDD" + UserID)

                    if (UID!!.equals(UserID)) {
                        Profile.add(
                            UserPRofile(
                                firstName.toString(),
                                email.toString(),
                                age.toString().toInt(),
                                dob.toString(),
                                gender.toString(),
                                UserID.toString(),
                                PhoneNumber.toString(),
                                statusOTP.toString().toInt(),
                                UserFirstCountry.toString(),
                                UserFirstCountryCode.toString(),
                                UserSecCountry.toString(),
                                UserSecCountryCode.toString(),
                                userImage_0.toString(),
                                userImage_1.toString(),
                                userImage_2.toString(),
                                userImage_3.toString(),
                                userImage_4.toString(),
                                userImage_5.toString(),
                            )
                        )

                        Glide.with(requireActivity())
                            .load(Profile.get(0).img0)
                            .placeholder(R.drawable.img_two)
                            .into(binding.profileImageIv)


                        binding.profileImageIv.setOnClickListener {
                            Navigation.findNavController(view!!)
                                .navigate(R.id.action_homeScreenFragment_to_editProfileFragment)
                        }
                        Log.e("OOOBBCCCC", Profile.toString())

                    }

                    if (!(UID!!.equals(UserID))) {

                        datalist.add(
                            UserPRofile(
                                firstName.toString(),
                                email.toString(),
                                age.toString().toInt(),
                                dob.toString(),
                                gender.toString(),
                                UserID.toString(),
                                PhoneNumber.toString(),
                                statusOTP.toString().toInt(),
                                UserFirstCountry.toString(),
                                UserFirstCountryCode.toString(),
                                UserSecCountry.toString(),
                                UserSecCountryCode.toString(),
                                userImage_0.toString(),
                                userImage_1.toString(),
                                userImage_2.toString(),
                                userImage_3.toString(),
                                userImage_4.toString(),
                                userImage_5.toString(),
                            )
                        )

                        Log.e("AAAAAACCC", datalist.toString())
                    }

                }
                if (datalist.size > 0) {
                    SetStackView(datalist)
                    Log.e("AAAAAACCC", datalist.toString())
                }

            }
            .addOnFailureListener {
                Log.e("SSSAA", it.message.toString())

            }

    }


/*
    private fun getDAta() {
        user =  viewmodel.readData()

        user.observe(requireActivity(), Observer {
            user = it as MutableLiveData<ArrayList<UserPRofile>>

        })
    }
*/

    private fun setlautoutHight(cardHight: Int) {

        val params: ViewGroup.LayoutParams = binding.stackView.layoutParams
        params.height = cardHight
        binding.stackView.layoutParams = params
    }

    fun layoutHigth() {
        val viewTreeObserver: ViewTreeObserver = binding.consLy.getViewTreeObserver()
        if (viewTreeObserver.isAlive) {
            viewTreeObserver.addOnGlobalLayoutListener(object :
                ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    binding.consLy.getViewTreeObserver().removeOnGlobalLayoutListener(this)

                    val layoutHight = binding.consLy.getHeight() * 2
                    actualHight(layoutHight)
                    val viewWeight: Int = binding.consLy.getWidth()
                    Log.e("Hight===", layoutHight.toString() + "width==" + viewWeight)
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
        val cardHight = TotalHight - hight
        setlautoutHight(cardHight)
        Log.e("CArdHight1", cardHight.toString())

    }

    fun getScreenWidth(): Int {
        TotalWidth = Resources.getSystem().displayMetrics.widthPixels
        Log.e("MMMMMMBN", TotalWidth.toString())
        return TotalWidth
    }

    fun getScreenHeight(): Int {
        TotalHight = Resources.getSystem().displayMetrics.heightPixels
        Log.e("SDSDSDS", TotalHight.toString())

        return TotalHight
    }

    private fun SetStackView(datalist: ArrayList<UserPRofile>) {

        Log.e("SSSSSS", datalist.toString())
        layoutManager = CardStackLayoutManager(context, this).apply {
            setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
            setOverlayInterpolator(LinearInterpolator())
        }


        val setting = SwipeAnimationSetting.Builder()
            .setDirection(Direction.Right)
            .setDuration(Duration.Normal.duration)
            .setInterpolator(AccelerateInterpolator())
            .build()

        binding.stackView.layoutManager = CardStackLayoutManager(context, this)
//        layoutManager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
        layoutManager.setOverlayInterpolator(LinearInterpolator())
        layoutManager.setMaxDegree(20.0f)
        layoutManager.setSwipeThreshold(0.3f)
        layoutManager.setSwipeAnimationSetting(setting)

        binding.stackView.layoutManager = layoutManager
        binding.stackView.swipe()
        layoutManager.setCanScrollHorizontal(true)
        layoutManager.setCanScrollVertical(false)

        adapter = StackCardAdapter(requireActivity(), datalist, view)
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

    override fun onCardSwiped(direction : Direction?) {
        Log.e("onswiped", pos.toString())

        var likedUser = HashMap<String,Any>()
        likedUser.put("status",1)
        likedUser.put("username",datalist.get(pos).Firstname.toString())
        likedUser.put("age",datalist.get(pos).age.toString())
        likedUser.put("userID",datalist.get(pos).Uid.toString())
        likedUser.put("email",datalist.get(pos).email.toString())
        likedUser.put("gender",datalist.get(pos).gender.toString())
        likedUser.put("img0",datalist.get(pos).img0.toString())
        likedUser.put("img1",datalist.get(pos).img1.toString())
        likedUser.put("img2",datalist.get(pos).img2.toString())
        likedUser.put("img3",datalist.get(pos).img3.toString())
        likedUser.put("img4",datalist.get(pos).img4.toString())
        likedUser.put("img5",datalist.get(pos).img5 .toString())

        if (direction!!.equals(Direction.Right)) {
            Log.e("SSSSS",Direction.Right.toString() + "MMMM<>><><><><><>" )
            FirebaseUtils().fireStoreDatabase.collection("Likes").document(FirebaseUtils().Uid)
                .set(likedUser , SetOptions.merge())
                .addOnSuccessListener {
                    Log.e("QQQQA",it.toString() )
                    Toast.makeText(requireActivity(),"Like",Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(requireActivity(),it.message,Toast.LENGTH_SHORT).show()
                }


        } else if (direction!!.equals(Direction.Left)) {
            Log.e("aaaaaaaa",Direction.Left.toString() + "XXXXXXXXXX<>><><><><><>" )

            FirebaseUtils().fireStoreDatabase.collection("Dislikes").document(FirebaseUtils().Uid)
                .set(likedUser , SetOptions.merge())
                .addOnSuccessListener {
                    Log.e("SSSSS",it.toString() )

                    Toast.makeText(requireActivity(),"Nope",Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(requireActivity(),it.message,Toast.LENGTH_SHORT).show()
                }
        }

    }

    override fun onCardRewound() {

    }

    override fun onCardCanceled() {
        Log.e("DFD", "dcsds")
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCardAppeared(view: View?, position: Int) {
            pos = position
                Log.e("KALAKAKA", pos.toString())

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCardDisappeared(view: View?, position: Int) {
//        if (position == datalist.size - 1) {
//            adapter = StackCardAdapter(requireActivity(), datalist, view)
//            binding.stackView.adapter = adapter
//            adapter.notifyDataSetChanged()
//        }

        Log.e("WORKKKFFFF", "POSITION" + position.toString() + "LISTPOSITION==" + datalist.size)
    }
}