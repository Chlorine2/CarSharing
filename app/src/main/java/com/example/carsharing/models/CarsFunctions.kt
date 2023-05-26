package com.example.carsharing.models

data class Cars(
    val color: String = "",
    val description: String = "",
    val id: Int = 0,
    val isRented: Boolean = false,
    val location: String = "",
    val model: String = "",
    val owner: Owner = Owner(),
    val renter: Owner = Owner(),
    val vendor: String = "",
    val year: Int = 0,
    val bookings : List<Booking> = emptyList()
)
data class Booking(
    val startTime : String = "",
    val endTime : String = ""
)

data class Owner(
    val email: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val phone: String = ""
)
