package com.isa.myflickr.util

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created by Isa Andi on 19/05/2023.
 *
 * A dispatcher that responsible swap
 * the network to a different thread between Android and iOS
 */
internal interface Dispatcher {
    val io: CoroutineDispatcher
}

internal expect fun provideDispatcher(): Dispatcher