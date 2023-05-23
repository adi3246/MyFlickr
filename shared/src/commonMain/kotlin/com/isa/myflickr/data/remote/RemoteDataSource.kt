package com.isa.myflickr.data.remote

import com.isa.myflickr.util.Dispatcher
import kotlinx.coroutines.withContext

/**
 * Created by Isa Andi on 19/05/2023.
 *
 * A class that responsible to providing the remote data to repository as well as switching
 * the network to a different thread using the dispatcher
 */
internal class RemoteDataSource(
    private val apiService: FlickrService,
    private val dispatcher: Dispatcher
) {

    // Retrieve a list of photos from Flickr API
    suspend fun getPhotos(page: Int, name: String) = withContext(dispatcher.io){
        apiService.getPhotos(page = page, name = name)
    }
}