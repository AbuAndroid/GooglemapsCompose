package com.example.googlemapscompose.di.module

import com.example.googlemapscompose.repository.MapsRepository
import com.example.googlemapscompose.ui.mapscreen.MapViewModel
import com.example.googlemapscompose.utils.AddressFinder
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object Test {
    fun modules() = repositoryModule + viewModelModule + commonModule
}


val repositoryModule = module {
    single {
        MapsRepository(get())
    }
}

val viewModelModule = module {
    viewModel {
        MapViewModel(get())
    }
}


val commonModule = module {
    single {
        AddressFinder(androidContext())
    }
}