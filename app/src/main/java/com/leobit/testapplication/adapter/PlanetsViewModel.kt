package com.leobit.testapplication.adapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.leobit.testapplication.adapter.pagelistadapter.pagelist.data.PositionalCharacterDataSource
import com.leobit.testapplication.adapter.pagelistadapter.pagelist.data.PositionalPlanetDataSource
import com.leobit.testapplication.network.Location
import kotlinx.coroutines.launch
import retrofit2.HttpException


/**
 *  ** Locations ViewModel **
 *
 *
 * ViewModel class for obtained data from network  and setting up configuration for its needs
 *
 * */

class PlanetsViewModel :ViewModel() {
  val _properties = MutableLiveData<List<Location>>()


   val properties1: LiveData<List<Location>> = _properties

    val flow = Pager(
        // Configure how data is loaded by passing additional properties to
        // PagingConfig, such as prefetchDistance.
        PagingConfig(pageSize = 20)
    ) {
      PositionalPlanetDataSource()
    }.flow
        .cachedIn(viewModelScope)

}