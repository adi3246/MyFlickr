package com.isa.myflickr.domain.use_case

import com.isa.myflickr.domain.model.Photo
import com.isa.myflickr.domain.repository.PhotoRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * Created by Isa Andi on 23/05/2023.
 */
class GetPhotosUseCaseIos: KoinComponent {
    val repository by inject<PhotoRepository>()
    // private val repository = get<PhotoRepository>() // Immediate inject

    @Throws(Exception::class)
    suspend operator fun invoke(page: Int, name: String): List<Photo> = repository.getPhotos(page = page, name = name)
}
