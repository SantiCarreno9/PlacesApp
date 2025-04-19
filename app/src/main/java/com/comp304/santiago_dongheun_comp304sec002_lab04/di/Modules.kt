package com.comp304.santiago_dongheun_comp304sec002_lab04.di

import com.comp304.santiago_dongheun_comp304sec002_lab04.data.PlacesRepository
import com.comp304.santiago_dongheun_comp304sec002_lab04.data.PlacesRepositoryImpl
import com.comp304.santiago_dongheun_comp304sec002_lab04.viewmodel.PlacesViewModel
import org.koin.dsl.module

val appModules = module {
    single<PlacesRepository> { PlacesRepositoryImpl() }
    single { PlacesViewModel(get()) }
}