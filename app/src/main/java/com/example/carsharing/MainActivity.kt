package com.example.carsharing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.carsharing.ui.theme.CarSharingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarSharingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    ScaffoldSimple()
                }
            }
        }
    }
}

enum class ListOfScreens (){
    Search(),
    Ordered(),
    MyCars(),
    Profile()

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CarSharingTheme {
        ScaffoldSimple()
    }
}

@Composable
fun ScaffoldSimple() {


    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = { BottomBar(navController) },
        content = {
            NavHost(navController = navController, startDestination = ListOfScreens.Search.name){
                composable(route = ListOfScreens.Search.name){
                    CarsContent()
                }
                composable(route = ListOfScreens.Ordered.name){
                    Text(text = "Ordered")
                }
                composable(route = ListOfScreens.MyCars.name){
                    Text(text = "My Cars")
                }
                composable(route = ListOfScreens.Profile.name){
                    Text(text = "Prodile")
                }



            }
        },

    )

}



@Composable
fun BottomBar(navControler: NavHostController) {
    val selectedIndex = remember { mutableStateOf(0) }
    BottomNavigation(elevation = 1000.dp, backgroundColor = MaterialTheme.colors.surface) {

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Search,"")
        },
            label = { Text(text = "Cars") },
            selected = (selectedIndex.value == 0),
            selectedContentColor = MaterialTheme.colors.primary,
            unselectedContentColor = Color.Gray,

            onClick = {
                selectedIndex.value = 0
                navControler.navigate(ListOfScreens.Search.name)

            })

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Favorite,"")
        },
            label = { Text(text = "Ordered") },
            selected = (selectedIndex.value == 1),
            selectedContentColor = MaterialTheme.colors.primary,
            unselectedContentColor = Color.Gray,

            onClick = {
                selectedIndex.value = 1
                navControler.navigate(ListOfScreens.Ordered.name)

            })

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Favorite,"")
        },
            label = { Text(text = "My Cars") },
            selected = (selectedIndex.value == 2),
            selectedContentColor = MaterialTheme.colors.primary,
            unselectedContentColor = Color.Gray,
            onClick = {
                selectedIndex.value = 2
                navControler.navigate(ListOfScreens.MyCars.name)

            })

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Person,"")
        },
            label = { Text(text = "Profile") },
            selected = (selectedIndex.value == 3),
            selectedContentColor = MaterialTheme.colors.primary,
            unselectedContentColor = Color.Gray,
            onClick = {
                selectedIndex.value = 3
                navControler.navigate(ListOfScreens.Profile.name)
            })
    }
}

