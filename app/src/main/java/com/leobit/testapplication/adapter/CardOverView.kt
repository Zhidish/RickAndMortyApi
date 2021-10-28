package com.leobit.testapplication.adapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leobit.testapplication.network.CardHolder
import com.leobit.testapplication.network.CardObtainer
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.lang.Exception

class CardOverView : ViewModel() {


    val _properties = MutableLiveData<List<CardObtainer>>()

    val properties: LiveData<List<CardObtainer>> = _properties



    init{
        getCharacters()
    }

    fun getCharacters() {

        viewModelScope.launch {
            val people = CallsToApi.retrofitPeopleService.getPeople()?.count
            val cards: MutableList<CardObtainer> = ArrayList()

                if (people != null) {

                    val count = Integer.parseInt(people)
                    for (item in 1..count) {
                        try {
                            CallsToApi.retrofitPeopleService.getConcretePeople(item)
                                ?.let { cards.add(it) }
                        }catch (e: HttpException){

                        }
                    }
                }
                _properties.value = cards


        }

    }


}