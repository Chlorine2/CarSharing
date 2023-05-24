package com.example.carsharing.models

data class RegistrationModel (
    val email : String,
    val password : String,
    val firstName : String,
    val lastName : String,
    val phone : String
)
data class AuthorizationModel (
    val email : String,
    val password : String,
)

data class Token(
    val token : String = ""
)

