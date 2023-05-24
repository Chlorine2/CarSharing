package com.example.carsharing.models

import android.os.Parcelable
import com.example.carsharing.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.parcelize.Parcelize

@Parcelize
data class Car (
    val id : Int = 0,
    val picture : Int = 0,
    val description: String = "",
    val color : String = "",
    val vendor : String = "",
    val model : String = "",
    val model_year : Int = 0,
    val price : Int = 0,
    val location : String = "",
    val VIN : Int = 0,
   //val owner : User = User(),
   //val renter : Renter = Renter()

) : Parcelable

@Parcelize
data class Renter(
    val name : User = User(),
    val start_date_hour : String = "",
    val end_date_hour : String = ""

) : Parcelable

@Parcelize
data class User(
    val id : Int = 0,
    val username : String = "",
    val password : String = "",
    val first_name : String = "",
    val second_name : String = "",
    val photo : String = "",
    val owned_cars : List<Car> = listOf<Car>(Car()),
    val rented_cars : List<Car> = listOf(Car())

) : Parcelable

class DataSource(){

    fun dataCars(): List<Cars> {

        return listOf<Cars>(

            Cars(id = 1, description = "Very cool Car yes", color = "black" ,vendor = "Hui", model =  "M22", price = 25.0),
            Cars(id = 2, description = "Very cool Car yes", color = "white" ,vendor = "BMW",model = "M22", price = 55.0),
            Cars(id = 3, description = "Very cool Car yes", color = "black" ,vendor = "Volkswagen",model = "M22", price = 45.0),
            Cars(id = 4, description = "Very cool Car yes", color = "white" ,vendor = "BMW", model ="M22", price = 25.0),
            Cars(id = 5, description = "Very cool Car yes", color = "black" ,vendor = "BMW",model = "M22", price = 65.0),
            Cars(id = 6, description = "Very cool Car yes", color = "green" ,vendor = "Toyota",model = "M22", price = 90.0),
            Cars(id = 7, description = "Very cool Car yes", color = "black" ,vendor = "Mercedes",model = "M22", price = 25.0),
            Cars(id = 8, description = "Very cool Car yes", color = "purple",vendor = "Volkswagen",model = "M22", price = 45.0),
            Cars(id = 9, description = "Very cool Car yes", color = "black" ,vendor = "Mercedes",model = "M22", price = 75.0),
            Cars(id = 10, description ="Very cool Car yes", color = "yellow",vendor = "Volkswagen",model = "M22", price = 25.0),
            Cars(id = 1, description = "Very cool Car yes", color = "black" ,vendor = "Hui", model ="M22", price = 25.0),
            Cars(id = 2, description = "Very cool Car yes", color = "white" ,vendor = "BMW", model ="M22", price = 55.0),
            Cars(id = 3, description = "Very cool Car yes", color = "black" ,vendor = "Volkswagen",model = "M22", price = 45.0),
            Cars(id = 4, description = "Very cool Car yes", color = "white" ,vendor = "BMW", model ="M22", price = 25.0),
            Cars(id = 5, description = "Very cool Car yes", color = "black" ,vendor = "BMW",model = "M22", price = 65.0),
            Cars(id = 6, description = "Very cool Car yes", color = "green" ,vendor = "Toyota", model ="M22", price = 90.0),
            Cars(id = 7, description = "Very cool Car yes", color = "black" ,vendor = "Mercedes",model = "M22", price = 25.0),
            Cars(id = 8, description = "Very cool Car yes", color = "purple",vendor = "Volkswagen",model = "M22", price = 45.0),
            Cars(id = 9, description = "Very cool Car yes", color = "black" ,vendor = "Mercedes",model = "M22", price = 75.0),
            Cars(id = 10, description ="Very cool Car yes", color = "yellow",vendor = "Volkswagen",model = "M22", price = 25.0),

        )
    }

}