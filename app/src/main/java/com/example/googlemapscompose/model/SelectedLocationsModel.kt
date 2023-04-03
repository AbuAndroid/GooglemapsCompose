package com.example.googlemapscompose.model

data class SelectedLocationsModel(
    val addressList:List<SelectedPlace>
)

data class SelectedPlace(
    val addressItem:String
)
