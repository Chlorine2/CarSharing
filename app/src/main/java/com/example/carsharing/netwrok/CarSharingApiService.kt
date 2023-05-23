package com.example.carsharing.netwrok

import retrofit2.http.GET

interface CarSharingApiService {
    @GET("photos")
    suspend fun getPhotos()
}
