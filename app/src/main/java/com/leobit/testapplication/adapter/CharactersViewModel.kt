package com.leobit.testapplication.adapter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.leobit.testapplication.adapter.pagelistadapter.pagelist.data.PostionalCharacterDataSource
import com.leobit.testapplication.network.Character
import kotlinx.coroutines.launch

import retrofit2.HttpException

open class CharactersViewModel : ViewModel() {

/*

    var start = 1
    var end = 10
*/
    open val _properties = MutableLiveData<List<Character>>()

    open val properties: LiveData<List<Character>> = _properties

    val flow = Pager(
        // Configure how data is loaded by passing additional properties to
        // PagingConfig, such as prefetchDistance.
        PagingConfig(pageSize = 20)
    ) {
        PostionalCharacterDataSource()
    }.flow
        .cachedIn(viewModelScope)

/*

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



            _properties.value = list
        }

        start = end
        end += 10

    }
*/




}