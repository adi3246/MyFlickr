package com.isa.myflickr.data.util

import com.isa.myflickr.data.dto.PhotoResponse
import com.isa.myflickr.domain.model.Photo

/**
 * Created by Isa Andi on 19/05/2023.
 *
 * A function to map data object from Data Layer to Domain Layer
 */
internal fun PhotoResponse.toPhoto(): Photo {
    return Photo(
        farm = farm,
        heightM = height_m,
        id = id,
        isFamily = isFamily,
        isFriend = isFriend,
        isPublic = isPublic,
        owner = owner,
        secret = secret,
        server = server,
        title = title,
        urlM = url_m,
        widthM = width_m
    )
}