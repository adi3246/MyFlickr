package com.isa.myflickr.domain.model

/**
 * Created by Isa Andi on 19/05/2023.
 */
data class Photos(
    val page: Int,
    val pages: Int,
    val perPage: Int,
    val photo: List<Photo>,
    val total: Int
)