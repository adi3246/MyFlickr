package com.isa.myflickr.domain.usecase

import com.isa.myflickr.domain.model.Photo
import com.isa.myflickr.domain.repository.PhotoRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * Created by Isa Andi on 19/05/2023.
 */
class GetPhotosUseCase: KoinComponent {
    private val repository: PhotoRepository by inject()

    @Throws(Exception::class)
    suspend operator fun invoke(page: Int, name: String): List<Photo> = repository.getPhotos(page = page, name = name)
}