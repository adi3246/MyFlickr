package com.isa.myflickr.domain.model

/**
 * Created by Isa Andi on 19/05/2023.
 */
data class Photo(
    val farm: Int,
    val heightM: Int,
    val id: String,
    val isFamily: Int,
    val isFriend: Int,
    val isPublic: Int,
    val owner: String,
    val secret: String,
    val server: String,
    val title: String,
    val urlM: String,
    val widthM: Int
)