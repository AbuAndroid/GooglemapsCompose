package com.example.googlemapscompose.router

sealed class Screen(val route:String) {
    object Map:Screen("map_screen")
}