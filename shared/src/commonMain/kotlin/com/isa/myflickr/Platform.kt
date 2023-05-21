package com.isa.myflickr

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform