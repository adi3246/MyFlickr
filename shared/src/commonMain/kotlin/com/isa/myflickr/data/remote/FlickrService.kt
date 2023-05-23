package com.isa.myflickr.data.remote

import com.isa.myflickr.data.dto.PhotoDto
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

/**
 * Created by Isa Andi on 19/05/2023.
 *
 * A class to retrieve data from Flickr API
 */
internal class FlickrService: KtorApi() {

    suspend fun getPhotos(page: Int = 1, name: String): PhotoDto = client.get {
        pathUrl("services/rest")
        parameter("page", page)
        parameter("tags", name)
    }.body()
}