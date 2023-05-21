package com.isa.myflickr.data.dto

@kotlinx.serialization.Serializable
internal data class PhotoDto(
    val photos: PhotosResponse,
    val stat: String
)