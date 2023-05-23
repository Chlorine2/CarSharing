package com.example.carsharing.netwrok

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val BASE_URL = "https://spring-carsharing-demo.azurewebsites.net/"


    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()




object RetrofitCarSharingApi{

    val retrofitCarSharingService : CarSharingApiService by lazy { retrofit.create(CarSharingApiService::class.java) }
    val apiClient = CarSharingApiClient(retrofitCarSharingService)
}