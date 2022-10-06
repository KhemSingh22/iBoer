package com.example.ibeor.viewmodels

import android.app.Activity
import android.content.IntentSender
import android.location.LocationRequest
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.ibeor.R
import com.example.ibeor.activities.fragments.AllowLocationFragment
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.location.LocationRequest.create
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY


class AllowLocationViewModel(private var activity: Activity) : ViewModel() {
    protected var mGoogleApiClient: GoogleApiClient? = null
    protected var locationRequest: LocationRequest? = null
    var REQUEST_CHECK_SETTINGS = 100

    fun back(view: View) {
        activity.onBackPressed()
    }

    fun enableLocation(view: View) {

        Navigation.findNavController(view).navigate(R.id.action_allowLocationFragment_to_genderFragment)
    }

    fun desableLocation(view: View) {
        Toast.makeText(activity, "Your Location is desable", Toast.LENGTH_SHORT).show()

    }
fun getLocation(){

//    fun showEnableLocationSetting() {
//        activity?.let {
//            val locationRequest = LocationRequest.create()
//            locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
//
//            val builder = LocationSettingsRequest.Builder()
//                .addLocationRequest(locationRequest)
//
//            val task = LocationServices.getSettingsClient(it)
//                .checkLocationSettings(builder.build())
//
//            task.addOnSuccessListener { response ->
//                val states = response.locationSettingsStates
//                if (states!!.isLocationPresent) {
//                    //Do something
//                }
//            }
//            task.addOnFailureListener { e ->
//                if (e is ResolvableApiException) {
//                    try {
//                        // Handle result in onActivityResult()
//                        e.startResolutionForResult(it,
//                            AllowLocationFragment.LOCATION_SETTING_REQUEST)
//                    } catch (sendEx: IntentSender.SendIntentException) { }
//                }
//            }
//        }
//    }
}
}