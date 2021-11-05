package com.leobit.testapplication.adapter

import com.leobit.testapplication.network.Character
import com.leobit.testapplication.network.Characters
import java.lang.Exception
import java.util.ArrayList

suspend fun main() {


    val listCharacter: MutableList<Character?> = ArrayList()
    var obtainer: Obtainer? = null
    var count = 1


    var characters: Characters? = null

    try {

        characters = CallsToApi.RickAndMortyService.getAllCharacters()
        print("sdsds")
        obtainer = Obtainer(characters)

    } catch (e: Exception) {
        print(e.localizedMessage)
    }

    for(i in 0..19){
        if (obtainer != null) {
            println(obtainer.charactersList[i].name)
        }else{
            println("sdsdsdsdsdsdsds")

        }

    }

    print(characters?.info?.count)
}

class Obtainer(val characters: Characters) {

    var charactersList: MutableList<Character> = mutableListOf()

  init {
      print("sadads")
      characters.results.let { charactersList.addAll(it) }
  }

}



