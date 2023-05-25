package com.example.carsharing.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.carsharing.ListOfScreens
import com.example.carsharing.Screens.login.LoginPage
import com.example.carsharing.Screens.login.RegisterPage
import com.example.carsharing.Screens.login.ResetPage
import com.example.carsharing.navigateSingleTopTo
import com.example.carsharing.viewModels.SharedViewModel

fun NavGraphBuilder.authNavGraph(navController: NavHostController, viewModel : SharedViewModel) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = ListOfScreens.Login.name
    ) {

        composable(ListOfScreens.Login.name){

            LoginPage(

                viewModel = viewModel,
                onClickRegistration = {navController.navigateSingleTopTo(ListOfScreens.Registration.name)},
                onClickReset = {navController.navigateSingleTopTo(ListOfScreens.Reset.name)},
                onClickLogin = {
                    navController.navigate(Graph.HOME)
                }
            )
        }
        composable(ListOfScreens.Registration.name)
        {
            RegisterPage( onClickSignUp = {navController.navigateSingleTopTo(ListOfScreens.Login.name)},
                onClickReset = {navController.navigateSingleTopTo(ListOfScreens.Reset.name)})
        }
        composable(ListOfScreens.Reset.name)
        {
            ResetPage(onClickSignUp = {navController.navigateSingleTopTo(ListOfScreens.Login.name)})
        }
    }
}

