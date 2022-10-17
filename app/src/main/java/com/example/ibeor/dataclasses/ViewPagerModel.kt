package com.example.ibeor.dataclasses

import android.os.Parcel
import android.os.Parcelable

class ViewPagerModel (
    var image0 : String?=""/*,
    var image1 : String?="",
    var image2 : String?="",
    var image3 : String?="",
    var image4 : String?="",
    var image5 : String?="" */) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(image0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ViewPagerModel> {
        override fun createFromParcel(parcel: Parcel): ViewPagerModel {
            return ViewPagerModel(parcel)
        }

        override fun newArray(size: Int): Array<ViewPagerModel?> {
            return arrayOfNulls(size)
        }
    }
}