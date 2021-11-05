package com.leobit.testapplication.adapter.pagelistadapter.pagelist.data


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.leobit.testapplication.adapter.CallsToApi
import com.leobit.testapplication.network.Character
import retrofit2.HttpException
import java.io.IOException


class PostionalCharacterDataSource : PagingSource<Int, Character>() {
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition.let {
            if (it != null) {
                //
                state.closestPageToPosition(it)?.prevKey?.plus(1) ?:
                //
                state.closestPageToPosition(it)?.nextKey?.minus(1)
            } else 0

        }


    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {


        return try {

            val pageNumber = params.key ?: 1


            val responds = CallsToApi.RickAndMortyService.getAllCharacters(pageNumber)


            val prevPage = if (pageNumber > 0) pageNumber - 1 else null


            val nextPage = if (pageNumber >= responds.info.pages) pageNumber + 1 else null

            LoadResult.Page(
                data = responds.results,
                prevKey = prevPage,
                nextKey = nextPage
            )

        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)

        }

    }


}