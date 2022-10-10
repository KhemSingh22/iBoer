package com.example.ibeor.listner

import android.widget.ImageView

interface Listners {
    fun openCamera(position: ImageView?, position1: Int, crossIv: ImageView)
    fun openGalary(galary: ImageView, position: Int, crossIv: ImageView)
}