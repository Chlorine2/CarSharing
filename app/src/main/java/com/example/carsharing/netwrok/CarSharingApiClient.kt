package com.example.carsharing.netwrok

import retrofit2.Response

class CarSharingApiClient(carSharingApiService: CarSharingApiService) {




    private inline fun <T> safeApiCall(apiCall : () -> Response<T>) : ErrorHandler<T>{
        return try{
            ErrorHandler.success(apiCall.invoke())

        }catch (e : java.lang.Exception){

            ErrorHandler.failed<T>(e)
        }
    }
}
