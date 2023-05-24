package com.example.carsharing.netwrok

import com.example.carsharing.models.AuthorizationModel
import com.example.carsharing.models.Cars
import com.example.carsharing.models.Token
import com.example.carsharing.models.RegistrationModel
import retrofit2.Response
import retrofit2.http.Body

class CarSharingApiClient(private val carSharingApiService: CarSharingApiService) {

    suspend fun postRegister(data: RegistrationModel) : ErrorHandler<Unit>{

       return safeApiCall {carSharingApiService.postRegister(data)}

    }
    suspend fun postAuthorization(data: AuthorizationModel) : ErrorHandler<Token>{

        return safeApiCall {carSharingApiService.postAuthorization(data)}

    }

    suspend fun getAllCars(token : String) : ErrorHandler<List<Cars>>{

        return safeApiCall { carSharingApiService.getAllCars(token) }
    }

    suspend fun getOwnedCars(token : String) : ErrorHandler<List<Cars>>{

        return safeApiCall { carSharingApiService.getOwnedCars(token) }
    }

    private inline fun <T> safeApiCall(apiCall: () -> Response<T>) : ErrorHandler<T>{
        return try{
            ErrorHandler.success(apiCall.invoke())


        }catch (e : java.lang.Exception){

            ErrorHandler.failed<T>(e)
        }
    }
}
