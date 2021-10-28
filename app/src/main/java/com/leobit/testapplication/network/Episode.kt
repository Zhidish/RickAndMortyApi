package com.leobit.testapplication.network

import com.squareup.moshi.Json

data class Episode(
    @Json(name = "id")
    val id : String,
    @Json(name = "name")
    val name : String,
    @Json(name = "air_date")
    val air_date : String,
    @Json(name = "episode")
    val episode : String,
    @Json(name = "characters")
    val characters : List<Any>,
    @Json(name = "url")
    val url : String,
    @Json(name = "created")
    val created : String


)
