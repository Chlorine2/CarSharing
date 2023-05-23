package com.example.carsharing.netwrok

import com.example.carsharing.models.AuthorizationModel
import com.example.carsharing.models.RegistrationModel
import com.example.carsharing.models.Token
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CarSharingApiService {
    @POST("auth/register")
    suspend fun postRegister(@Body data: RegistrationModel) : Response<Unit>

    @POST("auth/authenticate")
    suspend fun postAuthorization(@Body data: AuthorizationModel) : Response<Token>
}
