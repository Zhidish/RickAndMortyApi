package com.leobit.testapplication.network

import com.squareup.moshi.Json

data class CardHolder(

      @Json(name = "count")
      var count : String,
      @Json(name= "next")
      var next : String,
      @Json(name = "previous")
      var previous : String?,
      @Json(name = "results")
      var results: MutableList<CardObtainer>


   ) {






}