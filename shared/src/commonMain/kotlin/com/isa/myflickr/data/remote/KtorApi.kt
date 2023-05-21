package com.isa.myflickr.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.http.path
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * Created by Isa Andi on 19/05/2023.
 */
private const val BASE_URL = "https://api.flickr.com/"
private const val API_KEY = "b2854ca780721de012d220dcbffc951d"

internal abstract class KtorApi {
    val client = HttpClient {
        install(ContentNegotiation){
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
    }


    fun HttpRequestBuilder.pathUrl(path: String){
        url {
            takeFrom(BASE_URL)
            path(path)
            parameter("api_key", API_KEY)
            parameter("method", "flickr.photos.search")
            parameter("format", "json")
            parameter("nojsoncallback", true)
            parameter("extras", "media")
            parameter("extras", "url_m")
            parameter("per_page", 10)

        }
    }
}