package com.example.carsharing.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carsharing.models.AuthorizationModel
import com.example.carsharing.models.Cars
import com.example.carsharing.models.Token
import com.example.carsharing.netwrok.SharedRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


sealed interface AppUiState {
    object Success : AppUiState
    object Error : AppUiState
    object Loading : AppUiState
}

class SharedViewModel : ViewModel() {

    private var _signIn = MutableStateFlow("")
    val signIn: StateFlow<String> = _signIn.asStateFlow()

    private var _registry = MutableStateFlow("")
    val registry: StateFlow<String> = _registry.asStateFlow()

    private var _passUser = MutableStateFlow(0)
    val passUser : StateFlow<Int> = _passUser.asStateFlow()

    private var _car = MutableStateFlow(Cars())
    val car : StateFlow<Cars> = _car.asStateFlow()

    var appUiState: AppUiState by mutableStateOf(AppUiState.Loading)
        private set

    private val _searchCar = MutableStateFlow("")
    val searchCar : StateFlow<String> = _searchCar.asStateFlow()

    private val _searchCity = MutableStateFlow(-1)
    val searchCity : StateFlow<Int> = _searchCity.asStateFlow()

    private val _setProperties = List(4){MutableStateFlow("")}
    val setProperties: List<MutableStateFlow<String>> = _setProperties

    private val _availableCars = MutableStateFlow<List<Cars>>(emptyList())
    val availableCars : StateFlow<List<Cars>> = _availableCars.asStateFlow()

    private val _ownedCars = MutableStateFlow<List<Cars>>(emptyList())
    val ownedCars : StateFlow<List<Cars>> = _ownedCars.asStateFlow()

    private val _requestCar = MutableStateFlow<List<Cars>>(emptyList())
    val requestCar: StateFlow<List<Cars>> = _requestCar.asStateFlow()


    private val _token = MutableStateFlow(Token())
    val token : StateFlow<Token> = _token.asStateFlow()

    fun updateListCar(temp : Cars){
        _car.update { car -> car.copy(id = temp.id,
            description = temp.description, color = temp.color,
            model = temp.model, vendor = temp.vendor) }
    }
    init {
        connectInternet()
    }
    fun connectInternet(){

        viewModelScope.launch {
            appUiState = AppUiState.Loading
            appUiState = try {

                _token.value =
                    SharedRepository().postAuthorization(AuthorizationModel("andriy@gmail.com" , "1234"))!!

                if (token.value.token != null) {
                    _availableCars.value = SharedRepository().getAllCars("Bearer ${token.value.token}")!!
                }

                AppUiState.Success
            } catch (e: IOException) {
                AppUiState.Error
            } catch (e: HttpException) {
                AppUiState.Error
            }
        }
    }
    fun getOwnedCars(){

        viewModelScope.launch {

            if (token != null) {
                _ownedCars.value = SharedRepository().getOwnedCars("Bearer ${token.value.token}")!!
            }

        }
    }

    fun getRequestCars(){
        viewModelScope.launch {
            if(token != null) {
                _requestCar.value = SharedRepository().getRequestCars("Bearer ${token.value.token}")!!
            }
        }
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

    fun onChangePassUser(){
        _passUser.value = 50
    }
    fun onSignInChange(text: String){
        _signIn.value = text
    }

    fun onRegistryChange(text: String){
        _registry.value = text
    }

}