package com.isa.myflickr.domain.repository

import com.isa.myflickr.domain.model.Photo

/**
 * Created by Isa Andi on 19/05/2023.
 */
internal interface PhotoRepository {
    suspend fun getPhotos(page: Int, name: String): List<Photo>
}