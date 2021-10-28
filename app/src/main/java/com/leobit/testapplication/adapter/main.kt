package com.leobit.testapplication.adapter

import android.util.Log
import com.leobit.testapplication.network.CardObtainer
import com.leobit.testapplication.network.Character
import retrofit2.HttpException
import java.lang.Exception
import java.util.ArrayList

suspend fun main() {
    val people = CallsToApi.retrofitPeopleService.getPeople()


    val listPeople: MutableList<CardObtainer?> = ArrayList()
    val listCharacter: MutableList<Character?> = ArrayList()
        var count =1
    while (true) {
       try{
           print("iteration ${count}")
        listCharacter.add(CallsToApi.RickAndMortyService.getCharacter(count++))
       } catch(e : Exception){
        break
       }

    }


    for(item in listCharacter){
        if (item != null) {
            print(item.name)
        }


    }

}