package com.example.carsharing.netwrok

import android.util.Log
import com.example.carsharing.models.AuthorizationModel
import com.example.carsharing.models.Cars
import com.example.carsharing.models.RegistrationModel
import com.example.carsharing.models.Token
import com.example.carsharing.netwrok.RetrofitCarSharingApi.apiClient

interface InterfaceSharedRepository{

    suspend fun postRegistry(data: RegistrationModel)
    suspend fun postAuthorization(data: AuthorizationModel) : Token?

    suspend fun getAllCars(token : String) : List<Cars>?

    suspend fun getOwnedCars(token : String) : List<Cars>?

}

class SharedRepository : InterfaceSharedRepository {

    override suspend fun postRegistry(data : RegistrationModel) {
        val request = apiClient.postRegister(data)
        Log.d("teg", request.toString())


    }

    override suspend fun postAuthorization(data: AuthorizationModel): Token? {

        val request = apiClient.postAuthorization(data)
        Log.d("teg", request.toString())
        if(request.failed){
            return null
        }

        if(!request.isSuccessful){
            return null

        }
        return request.body
    }

    override suspend fun getAllCars(token: String): List<Cars>? {
        val request = apiClient.getAllCars(token)
        Log.d("teg", request.toString())
        if(request.failed){
            return null
        }

        if(!request.isSuccessful){
            return null

        }
        return request.body
    }

    override suspend fun getOwnedCars(token: String): List<Cars>? {

        val request = apiClient.getOwnedCars(token)
        Log.d("teg", request.toString())
        if(request.failed){
            return null
        }

        if(!request.isSuccessful){
            return null

        }
        return request.body
    }


}