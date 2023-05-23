package com.isa.myflickr.data.repository

import com.isa.myflickr.data.remote.RemoteDataSource
import com.isa.myflickr.data.util.toPhoto
import com.isa.myflickr.domain.model.Photo
import com.isa.myflickr.domain.repository.PhotoRepository

/**
 * Created by Isa Andi on 19/05/2023.
 *
 *  An implementation for PhotoRepository
 */
internal class PhotoRepositoryImpl(
    private val remoteDateSource: RemoteDataSource
): PhotoRepository {
    override suspend fun getPhotos(page: Int, name: String): List<Photo> {
        return remoteDateSource.getPhotos(page = page, name = name).photos.photo.map {
            it.toPhoto()
        }
    }
}