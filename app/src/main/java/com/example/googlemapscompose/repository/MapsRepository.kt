package com.example.googlemapscompose.repository

import com.example.googlemapscompose.utils.AddressFinder
import com.google.android.gms.maps.model.LatLng

class MapsRepository(
    private val addressFinder: AddressFinder
) {
    fun getAddress(latLng: LatLng): String {
        return addressFinder.getAddress(latLng)
    }
}