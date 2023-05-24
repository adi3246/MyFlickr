package com.isa.myflickr.android

import androidx.multidex.MultiDexApplication
import com.isa.myflickr.android.di.appModule
import com.isa.myflickr.di.getSharedModules
import org.koin.core.context.startKoin

/**
 * Created by Isa Andi on 19/05/2023.
 */
class AppClass: MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule + getSharedModules())
        }
    }
}