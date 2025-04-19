package com.comp304.santiago_dongheun_comp304sec002_lab04

import android.app.Application
import com.comp304.santiago_dongheun_comp304sec002_lab04.di.appModules
import org.koin.core.context.startKoin

class PlacesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModules)
        }
    }
}