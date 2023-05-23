package com.example.carsharing.netwrok

import retrofit2.Response
import java.lang.Exception

data class ErrorHandler<T> (
    val status: Status,
    val data : Response<T>?,
    val exception: java.lang.Exception?
){
    companion object{
        fun<T> success(data: Response<T>) : ErrorHandler<T>{
            return  ErrorHandler(
                status = Status.Success,
                data = data,
                exception = null
            )
        }
        fun <T>  failed(exception: Exception) : ErrorHandler<T>{
            return  ErrorHandler(
                status = Status.Failed,
                data = null,
                exception = exception
            )
        }
    }

    sealed class Status{
        object Success : Status()
        object Failed : Status()

    }

    val failed : Boolean
        get() = this.status == Status.Failed

    val isSuccessful :Boolean
        get() = !failed && this.data?.isSuccessful == true

    val body: T
        get() = this.data!!.body()!!
}