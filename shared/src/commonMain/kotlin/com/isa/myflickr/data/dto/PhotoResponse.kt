package com.isa.myflickr.data.dto

import kotlinx.serialization.SerialName


@kotlinx.serialization.Serializable
internal data class PhotoResponse(
    val farm: Int,
    val height_m: Int,
    val id: String,
    @SerialName("isfamily")
    val isFamily: Int,
    @SerialName("isfriend")
    val isFriend: Int,
    @SerialName("ispublic")
    val isPublic: Int,
    val owner: String,
    val secret: String,
    val server: String,
    val title: String,
    val url_m: String,
    val width_m: Int
)