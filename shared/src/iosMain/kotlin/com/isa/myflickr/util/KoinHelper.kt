package com.isa.myflickr.util

import com.isa.myflickr.di.getSharedModules
import org.koin.core.context.startKoin

/**
 * Created by Isa Andi on 19/05/2023.
 */
fun initKoin(){
    startKoin {
        modules(getSharedModules())
    }
}