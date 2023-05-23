package com.example.carsharing.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carsharing.models.AuthorizationModel
import com.example.carsharing.models.Car
import com.example.carsharing.models.RegistrationModel
import com.example.carsharing.netwrok.SharedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {

    private var _car = MutableStateFlow(Car())
    val car : StateFlow<Car> = _car.asStateFlow()

    private var _showBottomBar = MutableStateFlow(true)
    val showBottomBar : StateFlow<Boolean> = _showBottomBar.asStateFlow()

    private val _searchCar = MutableStateFlow("")
    val searchCar : StateFlow<String> = _searchCar.asStateFlow()

    private val _searchCity = MutableStateFlow(-1)
    val searchCity : StateFlow<Int> = _searchCity.asStateFlow()

    private val _setProperties = List(4){MutableStateFlow("")}
    val setProperties: List<MutableStateFlow<String>> = _setProperties

    fun updateListCar(temp : Car){
        _car.update { car -> car.copy(id = temp.id, picture = temp.picture,
            description = temp.description, color = temp.color,
            model = temp.model, price = temp.price, vendor = temp.vendor) }
    }
    init {
        viewModelScope.launch {
            SharedRepository().postRegistry(RegistrationModel("nazstiksle283sr@gmail.com",
                "1234","Nazaar","Usshhak","+380483745078"))

            SharedRepository().postAuthorization(AuthorizationModel("andriy@gmail.com" , "1234"))
        }
    }

    fun updateBottomBar(){

        _showBottomBar.value = !_showBottomBar.value
    }


    fun onSearchCarChange(text: String) {
        _searchCar.value = text
    }

    fun onSearchCityChange(index: Int) {
        _searchCity.value = index
    }
    fun onSetPropertyChange(count : Int, text: String) {
        _setProperties[count].value = text
    }

}