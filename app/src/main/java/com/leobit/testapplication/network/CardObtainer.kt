package com.leobit.testapplication.network

import com.squareup.moshi.Json

data class CardObtainer
    (
        @Json(name="name")
        val name : String,
        @Json(name = "height" )
        val height : String,
        @Json(name="mass")
        val mass : String,
        @Json(name="hair_color")
        val hair_color : String,
        @Json(name = "skin_color" )
        val skin_color : String,
        @Json(name = "eye_color" )
        val eye_color : String,
        @Json(name = "birth_year")
        val birth_year : String,
        @Json(name = "gender")
        val gender : String,
        @Json(name= "homeworld")
        val homeworld : String,
        @Json(name =  "films")
        val films : MutableList<String>,
        @Json(name = "species")
        val species : MutableList<String>,
        @Json(name = "vehicles" )
        val vehicles : MutableList<String>,
        @Json(name = "starships" )
        val starships : MutableList<String>,
        @Json(name = "created" )
        val created : String,
        @Json(name = "edited")
        val edited : String,
        @Json(name = "url")
        val url : String
) {


}