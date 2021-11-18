package com.leobit.testapplication.adapter.pagelistadapter.pagelist.data


import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.leobit.testapplication.adapter.CallsToApi
import com.leobit.testapplication.network.Character
import com.leobit.testapplication.network.Location
import retrofit2.HttpException
import java.io.IOException


class PositionalCharacterDataSource : PagingSource<Int, Character>() {
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition.let {
            if (it != null) {
                state.closestPageToPosition(it)?.prevKey?.plus(1) ?:
                state.closestPageToPosition(it)?.nextKey?.minus(1)?:
                state.closestPageToPosition(it)?.prevKey

            } else 1

        }


    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {


        return try {

            val pageNumber = params.key ?: 0


            val responds = CallsToApi.RickAndMortyService.getAllCharacters(pageNumber)


            val prevPage = if (pageNumber > 0) pageNumber - 1 else null


            val nextPage = if (pageNumber <= responds.info.pages) pageNumber + 1 else null

            LoadResult.Page(
                data = responds.results,
                prevKey = prevPage,
                nextKey = nextPage
            )

        } catch (e: IOException) {
            Log.e("IOException", "IOException")
            LoadResult.Error(e)
        } catch (e: HttpException) {
            Log.e("HttpException", "HttpException")
            LoadResult.Error(e)
        }catch (e : Exception){
            Log.e("Exception",e.localizedMessage)
            LoadResult.Error(e)

        }

    }


}


class PositionalPlanetDataSource : PagingSource<Int, Location>(){
    override fun getRefreshKey(state: PagingState<Int, Location>): Int? {
      return  state.anchorPosition.let{
            if (it != null) {
                state.closestPageToPosition(it)?.prevKey?.plus(1)?:
                state.closestPageToPosition(it)?.nextKey?.minus(1)?:
                state.closestPageToPosition(it)?.prevKey
            }else 1


        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Location> {

        return try {

            val pageNumber = params.key ?: 0

            Log.e("Planet Number", pageNumber.toString())

            val responds = CallsToApi.RickAndMortyService.getAllLocation(pageNumber)


            val prevPage = if (pageNumber > 0) pageNumber - 1 else null


            val nextPage = if (pageNumber < responds.info.pages) pageNumber + 1 else null

            Log.e("Planet Number", nextPage.toString())

            LoadResult.Page(
                data = responds.results,
                prevKey = prevPage,
                nextKey = nextPage
            )



        } catch (e: IOException) {
            Log.e("IOException", "IOException")
            LoadResult.Error(e)
        } catch (e: HttpException) {
            Log.e("HttpException", "HttpException")
            LoadResult.Error(e)

        }catch (e : Exception){
            Log.e("Exception",e.localizedMessage)
            LoadResult.Error(e)
        }



    }


}