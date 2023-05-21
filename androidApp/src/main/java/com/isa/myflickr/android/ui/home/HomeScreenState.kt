package com.isa.myflickr.android.ui.home

import com.isa.myflickr.domain.model.Photo

/**
 * Created by Isa Andi on 19/05/2023.
 */
data class HomeScreenState(
    var loading: Boolean = false,
    var refreshing: Boolean = false,
    var photos: List<Photo> = listOf(),
    var errorMessage: String? = null,
    var loadFinished: Boolean = false
)