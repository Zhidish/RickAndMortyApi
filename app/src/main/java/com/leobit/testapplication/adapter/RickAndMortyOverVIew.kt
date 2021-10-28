package com.leobit.testapplication.adapter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leobit.testapplication.network.Character
import kotlinx.coroutines.launch
import retrofit2.HttpException

open class RickAndMortyOverVIew : ViewModel() {


    var start = 1
    var end = 10
    open val _properties = MutableLiveData<List<Character>>()

    open val properties: LiveData<List<Character>> = _properties


    init {
        getCharacter()
    }


    open fun getCharacter() {
        viewModelScope.launch {

            val list: MutableList<Character> = ArrayList()
            //тут я поставив до 20, бо при 671 потрібно  почекати декілька хвилин поки все завантажеться
            for (i in 1..20) {
                try {
                    list.add(CallsToApi.RickAndMortyService.getCharacter(i))
                } catch (e: HttpException) {
                    break
                }

            }


            /*
            while (true) {
                try {
                    list.add(CallsToApi.RickAndMortyService.getCharacter(count++))
                } catch (e: HttpException) {
                    break
                }
            }*/


            _properties.value = list
        }

        start = end
        end += 10

    }


}