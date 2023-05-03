package com.example.carsharing.models

import com.example.carsharing.R

data class CarItems (
    val id : Int = 0,
    val picture : Int,

)

class DataSource(){

    fun dataCars(): List<CarItems> {

        return listOf<CarItems>(

            CarItems(0, R.drawable.image1),
            CarItems(0, R.drawable.image2),
            CarItems(0, R.drawable.image3),
            CarItems(0, R.drawable.image4),
            CarItems(0, R.drawable.image5),
            CarItems(0, R.drawable.image6),
            CarItems(0, R.drawable.image7),
            CarItems(0, R.drawable.image8),
            CarItems(0, R.drawable.image9),
            CarItems(0, R.drawable.image10),

        )
    }

}