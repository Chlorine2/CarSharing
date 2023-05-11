package com.example.carsharing.Screens

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.carsharing.CarItem
import com.example.carsharing.viewModels.SharedViewModel
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun RentedCarDetailScreen(viewModel: SharedViewModel) {
    CarItem(viewModel.car.value, viewModel, OnListButton = {}, true)

    val selectedTime = remember { mutableStateOf(LocalTime.of(23, 20, 0)) }

}