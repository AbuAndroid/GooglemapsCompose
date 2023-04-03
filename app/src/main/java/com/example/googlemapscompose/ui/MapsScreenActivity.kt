package com.example.googlemapscompose.ui

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.googlemapscompose.router.MapsRouter
import com.example.googlemapscompose.ui.mapscreen.MapViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val mapViewModel: MapViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LocationPermission()
            MapsRouter(
                mapViewModel = mapViewModel
            )
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LocationPermission() {
    val permissionState = rememberPermissionState(permission = Manifest.permission.ACCESS_FINE_LOCATION)
    val lifeCycleOwner = LocalLifecycleOwner.current
    DisposableEffect(key1 = lifeCycleOwner, effect = {
        val observer = LifecycleEventObserver{_,event ->
            when(event){
                Lifecycle.Event.ON_START ->{
                    permissionState.launchPermissionRequest()
                }
                else -> {}
            }
        }
        lifeCycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifeCycleOwner.lifecycle.removeObserver(observer)
        }
    })
}