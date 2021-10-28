package com.leobit.testapplication.network

import com.squareup.moshi.Json

data class Location(
    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "type")
    val type : String,
    @Json(name = "dimension")
    val dimension : String,
    @Json(name = "residents")
    val residents : List<Any>,
    @Json(name = "url")
    val url: String,
    @Json(name = "created")
    val created  : String

)