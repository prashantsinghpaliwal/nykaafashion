package com.byjusexamprep.newapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.byjusexamprep.newapp.models.Product
import com.byjusexamprep.newapp.retrofit.NykaaApi

class ProductPagingSource(val nykaaApi: NykaaApi) : PagingSource<Int, Product>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        return try {
            val page = params.key ?: 1
            val response = nykaaApi.getMainResponse(page)
            val lastPage = response.response.total_found / response.response.product_count
            LoadResult.Page(
                data = response.response.products,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (page == lastPage) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    // Note :-

    // anchorPosition ->
    // Most recently accessed index in the list by paging library

    // closestPageToPosition(anchorPosition) ->
    // This function can be called with anchorPosition
    // to fetch the loaded page that is closest to the last accessed index in the list.

    // It is first checking for prev key, if it's there, it adds 1 and finds next one.
    // Otherwise, it checks for next key, if it's there, it subtracts 1 and find prev one.

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}