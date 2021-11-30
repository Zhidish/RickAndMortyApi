package com.leobit.testapplication.adapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.leobit.testapplication.adapter.pagelistadapter.pagelist.data.PositionalCharacterDataSource
import com.leobit.testapplication.network.Character

open class CharactersViewModel : ViewModel() {

    open val _properties = MutableLiveData<List<Character>>()

    open val properties: LiveData<List<Character>> = _properties

    val flow = Pager(
        // Configure how data is loaded by passing additional properties to
        // PagingConfig, such as prefetchDistance.



        PagingConfig(pageSize = 20)
    ) {
        PositionalCharacterDataSource()
    }.flow
        .cachedIn(viewModelScope)

}