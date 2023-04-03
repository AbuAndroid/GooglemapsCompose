package com.example.googlemapscompose.utils

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.util.Log
import com.google.android.gms.maps.model.LatLng
import java.io.IOException

class AddressFinder(private val context: Context) {

    fun getAddress(latLng: LatLng):String{

        val geocoder = Geocoder(context)
        val addresses:List<Address>?
        var addressText = ""
        val address :String?

        try{
            addresses = geocoder.getFromLocation(latLng.latitude,latLng.longitude,1)

            if(addresses != null && addresses.isNotEmpty()){
                address = addresses[0].getAddressLine(0)
                addressText = address.toString()
            }
        }catch (e:IOException){
            Log.e("MapsActivity", e.localizedMessage)
        }
        return addressText
    }
}