package com.isa.myflickr.android.ui.detail

import com.isa.myflickr.android.data.Post

/**
 * Created by Isa Andi on 19/05/2023.
 */
data class DetailScreenState(
    var loading: Boolean = false,
    var post: Post? = null,
    var errorMessage: String? = null
)