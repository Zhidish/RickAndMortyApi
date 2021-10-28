package com.leobit.testapplication.adapter

import com.leobit.testapplication.network.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


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


interface SWApi {

    @GET("people")
    suspend fun getPeople(): CardHolder?

    @GET("people/{id}")
    suspend fun getConcretePeople(@Path("id") id:Int): CardObtainer?


}

interface RickAndMorty{

    @GET("character/1")
    suspend fun getCharacterI() : Character

    @GET("$CHARACTER/{id}")
    suspend fun getCharacter(@Path("id") id : Int) : Character

    @GET("$LOCATION/{id}")
    suspend fun getLocation(@Path("id") id : Int) : Location

    @GET("$EPISODE/{id}")
    suspend fun getEpisode(@Path("id") id : Int) : Episode


}


object CallsToApi {
    // setvice to obtain people

    val retrofitPeopleService: SWApi by lazy { retrofit.create(SWApi::class.java) }

    val RickAndMortyService : RickAndMorty by lazy { RickAndMortiRetrofit.create(RickAndMorty::class.java) }



}