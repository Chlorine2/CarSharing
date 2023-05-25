package com.example.carsharing

import PreviewTabbedApp
import RentedCarDetailScreen
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.carsharing.Screens.AddCarScreen
import com.example.carsharing.Screens.DetailedScreen
import com.example.carsharing.Screens.login.LoginPage
import com.example.carsharing.Screens.login.RegisterPage
import com.example.carsharing.Screens.login.ResetPage
import com.example.carsharing.graphs.HomeNavGraph
import com.example.carsharing.graphs.RootNavigationGraph
import com.example.carsharing.ui.theme.CarSharingTheme
import com.example.carsharing.viewModels.AppUiState
import com.example.carsharing.viewModels.SharedViewModel


enum class ListOfScreens (){
    Search(),
    Rented(),
    MyCars(),
    Profile(),
    Detail(),
    AddCar(),
    RentCar(),
    Login(),
    Registration(),
    Reset()

}
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarSharingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    val viewModel : SharedViewModel = viewModel()

                    HomeScreen(viewModel = viewModel)
                }
            }
        }
    }
}

//@RequiresApi(Build.VERSION_CODES.O)
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    CarSharingTheme {
//        ScaffoldSimple()
//    }
//}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ScaffoldSimple(viewModel: SharedViewModel, navController : NavHostController = rememberNavController()) {

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar =
        {

            BottomBar(navController, viewModel.passUser.value)

        },
        content = {
                innerPadding ->
            // Apply the padding globally to the whole BottomNavScreensController
            Box(modifier = Modifier.padding(innerPadding)) {
                HomeNavGraph(navController, viewModel = viewModel)
            }
        },

    )

}



@Composable
fun BottomBar(navController: NavHostController, height: Int) {
    val selectedIndex = remember { mutableStateOf(0) }


        BottomNavigation(elevation = 1000.dp, backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier.height(height = height.dp)) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            BottomNavigationItem(icon = {
                Icon(imageVector = Icons.Default.Search, "")
            },
                label = { Text(text = "Cars") },
                selected = (currentRoute == ListOfScreens.Search.name),
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = Color.Gray,

                onClick = {
                    selectedIndex.value = 0
                    navController.navigateSingleTopTo(ListOfScreens.Search.name)

                })

            BottomNavigationItem(icon = {
                Icon(imageVector = Icons.Default.Favorite, "")
            },
                label = { Text(text = "Rented") },
                selected = (currentRoute == ListOfScreens.Rented.name),
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = Color.Gray,

                onClick = {
                    selectedIndex.value = 1
                    navController.navigateSingleTopTo(ListOfScreens.Rented.name)

                })

            BottomNavigationItem(icon = {
                Icon(imageVector = Icons.Default.Favorite, "")
            },
                label = { Text(text = "My Cars") },
                selected = (currentRoute == ListOfScreens.MyCars.name),
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = Color.Gray,
                onClick = {
                    selectedIndex.value = 2
                    navController.navigateSingleTopTo(ListOfScreens.MyCars.name)

                })

            BottomNavigationItem(icon = {
                Icon(imageVector = Icons.Default.Person, "")
            },
                label = { Text(text = "Profile") },
                selected = (currentRoute == ListOfScreens.Profile.name),
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = Color.Gray,
                onClick = {
                    selectedIndex.value = 3
                    navController.navigateSingleTopTo(ListOfScreens.Profile.name)
                })
        }
    }


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    viewModel: SharedViewModel,
    //retryAction: () -> Unit,
) {
    when (viewModel.appUiState) {
        is AppUiState.Loading -> Text(text = "loading...", fontSize = 30.sp)
        is AppUiState.Success -> RootNavigationGraph(navController = rememberNavController(), viewModel = viewModel)
        is AppUiState.Error -> Text(text = "Error", fontSize = 30.sp)
    }
}
