package com.isa.myflickr.domain.use_case

import com.isa.myflickr.domain.model.Photo
import com.isa.myflickr.domain.repository.PhotoRepository

/**
 * Created by Isa Andi on 19/05/2023.
 *
 * A class to be use in Android and iOS view
 */
class GetPhotosUseCase(
    private val repository: PhotoRepository?
) {

    @Throws(Exception::class)
    suspend operator fun invoke(page: Int, name: String): List<Photo> = repository!!.getPhotos(page = page, name = name)
}
