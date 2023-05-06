package com.example.carsharing.viewModels

import androidx.lifecycle.ViewModel
import com.example.carsharing.models.Car
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SharedViewModel : ViewModel() {

    private var _car = MutableStateFlow(Car())
    val car : StateFlow<Car> = _car.asStateFlow()

    private var _showBottomBar = MutableStateFlow(true)
    val showBottomBar : StateFlow<Boolean> = _showBottomBar

    fun updateListCar(temp : Car){
        _car.update { car -> car.copy(id = temp.id, picture = temp.picture,
            description = temp.description, color = temp.color,
            model = temp.model, price = temp.price, vendor = temp.vendor) }
    }

    fun updateBottomBar(){

        _showBottomBar.value = !_showBottomBar.value
    }

}