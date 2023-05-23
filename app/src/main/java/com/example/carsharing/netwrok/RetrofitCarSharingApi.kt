package com.example.carsharing.netwrok

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

    private val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"


    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()




object RetrofitCarSharingApi{

    val retrofitService : CarSharingApiService by lazy { retrofit.create(CarSharingApiService::class.java) }
    val apiClient = CarSharingApiClient(retrofitService)
}