package com.leobit.testapplication.adapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leobit.testapplication.network.Location
import kotlinx.coroutines.launch
import retrofit2.HttpException

class PlanetsViewModel :ViewModel() {
  val _properties = MutableLiveData<List<Location>>()


   val properties1: LiveData<List<Location>> = _properties



     fun getCharacter() {
        viewModelScope.launch {
            val list = ArrayList<Location>()


            for (item in 1..108) {
                try {
                    list.add(CallsToApi.RickAndMortyService.getLocation(item))
                } catch (e: HttpException) {
                    break
                }

            }

            _properties.value=list
        }


    }
}