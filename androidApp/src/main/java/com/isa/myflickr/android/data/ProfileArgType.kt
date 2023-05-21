package com.isa.myflickr.android.data

import com.google.gson.Gson

/**
 * Created by Isa Andi on 21/05/2023.
 */
class ProfileArgType : JsonNavType<Post>() {
    override fun fromJsonParse(value: String): Post = Gson().fromJson(value, Post::class.java)

    override fun Post.getJsonParse(): String = Gson().toJson(this)
}