package com.example.googlemapscompose.router


import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.googlemapscompose.ui.mapscreen.MapScreen
import com.example.googlemapscompose.ui.mapscreen.MapViewModel

@Composable
fun MapsRouter(
    mapViewModel: MapViewModel
) {

    val uiState by mapViewModel.uiState.collectAsState()

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Map.route
    ) {
        composable(route = Screen.Map.route){
            MapScreen(
                markers = uiState.markers,
                addMarker = { location->
                    mapViewModel.addMarker(location)
                },
                selectedAddress = mapViewModel.address.value.toString(),
                doRecompose = {
                    mapViewModel.doRecompose()
                },
                addTextToButton = {
                    mapViewModel.addTextFromButton(it)
                },
                testingText = uiState.testingData

            )
            Log.e("datalist",uiState.selectedAddress)
        }
    }

}