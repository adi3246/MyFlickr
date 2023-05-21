package com.isa.myflickr.android.data

import android.net.Uri
import com.google.gson.Gson

/**
 * Created by Isa Andi on 21/05/2023.
 */
data class Post(val title:String, val photoUrl:String){
    override fun toString(): String = Uri.encode(Gson().toJson(this))
}