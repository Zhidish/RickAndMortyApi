package com.leobit.testapplication.network

import com.squareup.moshi.Json

data class Character(
    @Json(name = "id")
    val id : Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "status")
    val status : String,
    @Json(name = "species")
    val species : String,
    @Json(name = "type")
    val type: String,
    @Json(name = "gender")
    val gender:String,
    @Json(name = "origin")
    val origin : OriginX,
    @Json(name = "location")
    val location : LocationX,
    @Json(name = "image")
    val image : String,
    @Json(name = "episode")
    val episode : List<Any>,
    @Json(name = "url")
    val url : String,
    @Json(name = "created")
    val created : String

 ) {
}