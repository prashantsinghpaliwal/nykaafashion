package com.byjusexamprep.newapp.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.byjusexamprep.newapp.paging.ProductPagingSource
import com.byjusexamprep.newapp.retrofit.NykaaApi
import javax.inject.Inject

class ProductRepository @Inject constructor(val nykaaApi: NykaaApi) {

    fun getProducts() = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = 100
        ),
        pagingSourceFactory = { ProductPagingSource(nykaaApi) }
    ).flow


}