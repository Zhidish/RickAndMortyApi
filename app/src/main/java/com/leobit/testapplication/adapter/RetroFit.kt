package com.leobit.testapplication.adapter

import com.leobit.testapplication.network.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


private val BASE_URL = "https://swapi.dev/api/"


private val RICK_AND_MORTY = "https://rickandmortyapi.com/api/"
private const val CHARACTER = "character"
private const val  LOCATION = "location"
private const val EPISODE = "episode"



private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()


private val retrofit = Retrofit
    .Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


private val RickAndMortiRetrofit = Retrofit
    .Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(RICK_AND_MORTY)
    .build()



interface RickAndMorty{


    @GET("$CHARACTER/{id}")
    suspend fun getCharacter(@Path("id") id : Int) : Character

    @GET("$LOCATION/{id}")
    suspend fun getLocation(@Path("id") id : Int) : Location

    @GET("$EPISODE/{id}")
    suspend fun getEpisode(@Path("id") id : Int) : Episode


    @GET("$CHARACTER")
    suspend fun getAllCharacters() : Characters


    @GET("$CHARACTER/?")
   suspend fun getAllCharacters(@Query("page") id : Int) : Characters



   @GET("$LOCATION/?")
   suspend fun getAllLocation(@Query("page") id : Int) : Locations




}


object CallsToApi {
    // setvice to obtain people

    val RickAndMortyService : RickAndMorty by lazy { RickAndMortiRetrofit.create(RickAndMorty::class.java) }



}