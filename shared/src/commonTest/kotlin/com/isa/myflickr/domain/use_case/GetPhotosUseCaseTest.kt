package com.isa.myflickr.domain.use_case

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.isa.myflickr.data.repository.FakePhotoRepository
import com.isa.myflickr.domain.model.Photo
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test

/**
 * Created by Isa Andi on 22/05/2023.
 */
class GetPhotosUseCaseTest {

    private lateinit var getPhotosUseCase: GetPhotosUseCase
    private lateinit var fakeRepository: FakePhotoRepository
    private val photos = ArrayList<Photo>()

    @BeforeTest
    fun setUp() {
        fakeRepository = FakePhotoRepository()
        getPhotosUseCase = GetPhotosUseCase(fakeRepository)

        photos.add(
            Photo(
                66,
                298,
                "52851679147",
                0,
                0,
                0,
                "197824899@N07",
                "de50ce5079",
                "65535",
                "Máy giặt Electrolux báo lỗi LOC: Nguyên nhân & Cách sửa",
                "https://live.staticflickr.com/65535/52851679147_de50ce5079.jpg",
                500
            )
        )
        photos.add(
            Photo(
                6666,
                333,
                "52736789369",
                1,
                0,
                0,
                "150617593@N04",
                "d336ce611b",
                "65535",
                "Loc 2283 met buurtgoederentrein 55173 te Alphen a/d Rijn.  Mei, 1990.",
                "https://live.staticflickr.com/65535/52736789369_d336ce611b.jpg",
                500
            )
        )
        fakeRepository.addTestPhotos(photos)
    }

    @Test
    fun `Assert that position data list are correct`() = runBlocking {
        val photos = getPhotosUseCase.invoke(0, "")

        val expectedPhotoList = photos[0]
        assertThat(photos.first()).isEqualTo(expectedPhotoList)
    }
}