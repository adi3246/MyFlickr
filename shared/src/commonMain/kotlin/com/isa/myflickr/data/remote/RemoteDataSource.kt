package com.isa.myflickr.data.remote

import com.isa.myflickr.util.Dispatcher
import kotlinx.coroutines.withContext

/**
 * Created by Isa Andi on 19/05/2023.
 */
internal class RemoteDataSource(
    private val apiService: FlickrService,
    private val dispatcher: Dispatcher
) {

    suspend fun getPhotos(page: Int, name: String) = withContext(dispatcher.io){
        apiService.getPhotos(page = page, name = name)
    }
}