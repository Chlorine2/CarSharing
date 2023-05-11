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

    fun dataCars(): List<Car> {

        return listOf<Car>(

            Car(1, R.drawable.image1, "Very cool Car yes", "black" , "Hui", "M22", price = 25),
            Car(2, R.drawable.image2, "Very cool Car yes", "white" , "BMW", "M22", price = 55),
            Car(3, R.drawable.image3, "Very cool Car yes", "black" , "Volkswagen", "M22", price = 45),
            Car(4, R.drawable.image4, "Very cool Car yes", "white" , "BMW", "M22", price = 25),
            Car(5, R.drawable.image5, "Very cool Car yes", "black" , "BMW", "M22", price = 65),
            Car(6, R.drawable.image6, "Very cool Car yes", "green" , "Toyota", "M22", price = 90),
            Car(7, R.drawable.image7, "Very cool Car yes", "black" , "Mercedes", "M22", price = 25),
            Car(8, R.drawable.image8, "Very cool Car yes", "purple", "Volkswagen", "M22", price = 45),
            Car(9, R.drawable.image9, "Very cool Car yes", "black" , "Mercedes", "M22", price = 75),
            Car(10,R.drawable.image10,"Very cool Car yes", "yellow", "Volkswagen", "M22", price = 25),
            Car(1, R.drawable.image1, "Very cool Car yes", "black" , "Hui", "M22", price = 25),
            Car(2, R.drawable.image2, "Very cool Car yes", "white" , "BMW", "M22", price = 55),
            Car(3, R.drawable.image3, "Very cool Car yes", "black" , "Volkswagen", "M22", price = 45),
            Car(4, R.drawable.image4, "Very cool Car yes", "white" , "BMW", "M22", price = 25),
            Car(5, R.drawable.image5, "Very cool Car yes", "black" , "BMW", "M22", price = 65),
            Car(6, R.drawable.image6, "Very cool Car yes", "green" , "Toyota", "M22", price = 90),
            Car(7, R.drawable.image7, "Very cool Car yes", "black" , "Mercedes", "M22", price = 25),
            Car(8, R.drawable.image8, "Very cool Car yes", "purple", "Volkswagen", "M22", price = 45),
            Car(9, R.drawable.image9, "Very cool Car yes", "black" , "Mercedes", "M22", price = 75),
            Car(10,R.drawable.image10,"Very cool Car yes", "yellow", "Volkswagen", "M22", price = 25),

        )
    }

}