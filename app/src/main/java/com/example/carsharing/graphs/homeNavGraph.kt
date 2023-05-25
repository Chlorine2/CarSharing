package com.example.carsharing.graphs

import PreviewTabbedApp
import RentedCarDetailScreen
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.carsharing.CarsContent
import com.example.carsharing.ListOfScreens
import com.example.carsharing.MyCarsScreen
import com.example.carsharing.RentedCarScreen
import com.example.carsharing.Screens.AddCarScreen
import com.example.carsharing.Screens.DetailedScreen
import com.example.carsharing.navigateSingleTopTo
import com.example.carsharing.viewModels.SharedViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeNavGraph(navController: NavHostController, viewModel : SharedViewModel) {

    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = ListOfScreens.Search.name
    ){

            composable(route = ListOfScreens.Search.name) {
                CarsContent(viewModel, OnListButton = {navController.navigateSingleTopTo(ListOfScreens.Detail.name)})
            }
            composable(route = ListOfScreens.Rented.name) {
                RentedCarScreen(viewModel)
            }
            composable(route = ListOfScreens.MyCars.name) {
                MyCarsScreen(viewModel, OnAddCarButton = {navController.navigateSingleTopTo(ListOfScreens.AddCar.name)})
            }
            composable(route = ListOfScreens.Profile.name) {
                PreviewTabbedApp(viewModel, navController)
            }
            composable(route = ListOfScreens.Detail.name){
                DetailedScreen(viewModel, OnRentCarButton = {navController.navigateSingleTopTo(ListOfScreens.RentCar.name)})
            }
            composable(route = ListOfScreens.AddCar.name){
                AddCarScreen(viewModel)
            }
            composable(route = ListOfScreens.RentCar.name){
                RentedCarDetailScreen(viewModel)
            }

    }


}