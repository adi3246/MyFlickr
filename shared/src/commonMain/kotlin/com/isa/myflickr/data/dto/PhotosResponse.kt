package com.isa.myflickr.data.dto

import kotlinx.serialization.SerialName


@kotlinx.serialization.Serializable
internal data class PhotosResponse(
    val page: Int,
    val pages: Int,
    @SerialName("perpage")
    val perPage: Int,
    val photo: List<PhotoResponse>,
    val total: Int
)