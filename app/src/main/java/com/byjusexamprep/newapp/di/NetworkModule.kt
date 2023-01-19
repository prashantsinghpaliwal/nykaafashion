package com.byjusexamprep.newapp.di

import com.byjusexamprep.newapp.retrofit.NykaaApi
import com.byjusexamprep.newapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun getRetrofit(): Retrofit {
        val httpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val builder = OkHttpClient().newBuilder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(5, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)

        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(builder.build()).build()
    }


    @Singleton
    @Provides
    fun getNykaApi(retrofit: Retrofit): NykaaApi =
        retrofit.create(NykaaApi::class.java)

}