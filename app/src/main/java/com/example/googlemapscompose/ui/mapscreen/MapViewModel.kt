package com.example.googlemapscompose.ui.mapscreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.googlemapscompose.repository.MapsRepository
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.*

class MapViewModel(
    private val repository: MapsRepository
) : ViewModel() {

    val _address  = MutableLiveData<String>()
    val address:LiveData<String> = _address


    private val viewModelState = MutableStateFlow(MapViewModelState())

    val uiState = viewModelState.map { it.toUiState() }
        .stateIn(viewModelScope, SharingStarted.Eagerly, viewModelState.value.toUiState())

    fun addMarker(latLng: LatLng) {
        val markers = viewModelState.value.markers
        markers.add(latLng)
        _address.value = repository.getAddress(latLng)
        viewModelState.update {
            it.copy(
                markers = markers,
                selectedAddress = repository.getAddress(latLng),
                lastUpdate = System.currentTimeMillis()+1
            )
        }
    }

    fun doRecompose() {
        viewModelState.update { it.copy(lastUpdate = System.currentTimeMillis()+1) }
    }

    fun addTextFromButton(changeText: String) {
        viewModelState.update { it.copy(testingData = changeText, lastUpdate = System.currentTimeMillis()) }
    }
}

data class MapViewModelState(
    val markers: MutableList<LatLng> = mutableListOf(),
    val selectedAddress: String = "",
    val lastUpdate: Long = System.currentTimeMillis(),
    val testingData:String=""
) {
    fun toUiState(): MapViewUiModel {
        return MapViewUiModel(
            markers = markers,
            selectedAddress = selectedAddress,
            lastUpdate = System.currentTimeMillis(),
            testingData = testingData
        )
    }
}

data class MapViewUiModel(
    val markers: MutableList<LatLng>,
    val selectedAddress: String,
    val lastUpdate: Long = System.currentTimeMillis(),
    val testingData: String
)
