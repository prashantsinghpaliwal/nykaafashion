package com.byjusexamprep.newapp.retrofit

import com.byjusexamprep.newapp.models.MainResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NykaaApi {

    @GET("/products")
    suspend fun getMainResponse(@Query("page") page : Int) : MainResponse
}