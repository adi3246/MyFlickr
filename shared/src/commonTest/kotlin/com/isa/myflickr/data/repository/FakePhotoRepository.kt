package com.isa.myflickr.data.repository

import com.isa.myflickr.domain.model.Photo
import com.isa.myflickr.domain.repository.PhotoRepository

/**
 * Created by Isa Andi on 22/05/2023.
 */
class FakePhotoRepository : PhotoRepository {

    private var photos = ArrayList<Photo>()

    override suspend fun getPhotos(page: Int, name: String): List<Photo> {
        return photos.toList()
    }

    fun addTestPhotos(photos: ArrayList<Photo>) {
        this.photos = photos
    }
}